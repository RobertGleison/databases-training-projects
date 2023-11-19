package com.seed.databaseseed.parseCsvToRelational;

import com.seed.databaseseed.entities.relationalModel.*;
import com.seed.databaseseed.entities.PitchData;
import com.seed.databaseseed.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PitchDataService {

    public List<PitchData> getAllPitches() {
        return CsvProcessor.getPitches();
    }

    private final InvestmentRepository investmentRepository;
    private final EntrepeneurRepository entrepeneurRepository;
    private final ProjectRepository projectRepository;
    private final SharkRepository sharkRepository;
    private final EpisodeRepository episodeRepository;
    @Autowired
    public PitchDataService(
            InvestmentRepository investmentRepository,
            EntrepeneurRepository entrepeneurRepository,
            ProjectRepository projectRepository,
            SharkRepository sharkRepository,
            EpisodeRepository episodeRepository) {
        this.investmentRepository = investmentRepository;
        this.entrepeneurRepository = entrepeneurRepository;
        this.projectRepository = projectRepository;
        this.sharkRepository = sharkRepository;
        this.episodeRepository = episodeRepository;
    }

    @Transactional
    public void managePitch() {
        //Persist all ER model for each pitch
        List<PitchData> pitches = getAllPitches();
        for (PitchData p : pitches) {
            Episode episode = insertEpisode(p.getSeason(), p.getEpisode(), p.getSharks());
            setEpisodesToShark(episode);
            Project project = insertProject(p.getPicht(), p.getProjectName(), p.getWebsite(), p.getValuation(),
                    p.getCategory(), p.getDescription(), episode, p.getEntrepeneurNames());
            setProjectToEpisode(project, episode);
            setProjectToEntrepeneur(project);
            List<Investment> investments = insertInvestiment(project, p.getInvestors(), p.getInvestmentAmountPerShark(), p.getPercentageOfCompanyPerShark());
        }
    }

    private Set<Shark> insertSharks(Set<Shark> sharks) {
        return new HashSet<>(sharkRepository.saveAll(sharks));
    }

    private Episode insertEpisode(Integer season, Integer number, Set<Shark> sharks) {
        Episode episode = new Episode(number, season);
        episode.setSharks(insertSharks(sharks));
        return episodeRepository.save(episode);
    }

    private Project insertProject(Integer picht, String projectName, String website, Double valuation, String category, String description, Episode episode, List<Entrepeneur> entrepeneurNames) {
        Project project = new Project(picht, projectName, website, valuation, category, description);
        project.setEntrepeneurs(entrepeneurNames);
        project.setEpisode(episode);
        return project;
    }

    private List<Investment> insertInvestiment(Project project, Set<Shark> investors, Double investmentAmountPerShark, Double percentageOfCompanyPerShark) {
        List<Investment> investments = new ArrayList<>();
        for (Shark s : investors) {
            investments.add(new Investment(s, project, investmentAmountPerShark, percentageOfCompanyPerShark));
        }
        return investments;
    }

    private void setProjectToEpisode(Project project, Episode episode) {
        episode.addProject(project);
    }

    private void setEpisodesToShark(Episode episode) {
        Set<Shark> sharksInEpisode = episode.getSharks();
        for (Shark s : sharksInEpisode) {
            s.addEpisode(episode);
        }
    }

    private void setProjectToEntrepeneur(Project project) {
        List<Entrepeneur> empreendedores = project.getEntrepeneurs();
        for (Entrepeneur emp : empreendedores) {
            emp.setProject(project);
        }
    }


}
