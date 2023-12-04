package com.seed.databaseseed.parseCsvToRelational;

import com.seed.databaseseed.entities.relationalModel.*;
import com.seed.databaseseed.entities.PitchData;
import com.seed.databaseseed.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class PitchDataService {

    Logger logger = Logger.getLogger(PitchDataService.class.getName());

    public List<PitchData> getAllPitches() {
        return CsvProcessor.getPitches();
    }

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private EntrepeneurRepository entrepeneurRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SharkRepository sharkRepository;

    @Autowired
    private EpisodeRepository episodeRepository;


//    @Transactional
//    public void managePitch() {
//        //Persist all ER model for each pitch
//        List<PitchData> pitches = getAllPitches();
//        for (PitchData p : pitches) {
//
//            List<Integer> entrepeneurCodes = new ArrayList<>();
//            for (int i = 0; i < p.getEntrepeneurNames().size(); i++) {
//                entrepeneurCodes.set(i, EntrepeneurCodeManager.getEntrepeneurCode(Collections.singletonList(p.getEntrepeneurNames().get(i).getName())));
//            }
//
//            Episode episode = new Episode(p.getEpisode(), p.getSeason());
//            Project project = new Project(p.getPicht(),p.getProjectName(),p.getWebsite(),p.getCategory(),p.getDescription());
//
//            Set<Shark> sharks = p.getSharks();
//            List<Investment> investments;
//
//            episode = setEpisodeShark(p.getSeason(), p.getEpisode(), sharks);
//            project = setEpisodeProject(p.getPicht(), p.getProjectName(), p.getWebsite(), p.getValuation(), p.getCategory(), p.getDescription(), episode);
//            entrepeneurs = setProjectEntrepeneur(project, p.getEntrepeneurNames());
//            investments = insertInvestment(project, p.getInvestors(), p.getInvestmentAmountPerShark(), p.getPercentageOfCompanyPerShark(), sharks);
//
//            episodeRepository.save(episode);
//            sharkRepository.saveAll(episode.getSharks());
//            projectRepository.save(project);
//            entrepeneurRepository.saveAll(entrepeneurs);
//            investmentRepository.saveAll(investments);
//            System.out.print("Persisted");
//        }
//    }

    @Transactional
    public void managePitch() {
        //Persist all ER model for each pitch
        List<PitchData> pitches = getAllPitches();
        for (PitchData p : pitches) {
            Episode episode;
            Project project;
            List<Entrepeneur> entrepeneurs;
            Set<Shark> sharks = p.getSharks();
            List<Investment> investments;

            episode = setEpisodeShark(p.getSeason(), p.getEpisode(), sharks);
            project = setEpisodeProject(p.getPicht(), p.getProjectName(), p.getWebsite(), p.getValuation(), p.getCategory(), p.getDescription(), episode);
            entrepeneurs = setProjectEntrepeneur(project, p.getEntrepeneurNames());
//            investments = insertInvestment(project, p.getInvestors(), p.getInvestmentAmountPerShark(), p.getPercentageOfCompanyPerShark(), sharks);

            episodeRepository.save(episode);
            sharkRepository.saveAll(episode.getSharks());
            projectRepository.save(project);
            entrepeneurRepository.saveAll(entrepeneurs);
//            investmentRepository.saveAll(investments);
            System.out.print("Persisted");
        }
    }

    private Episode setEpisodeShark(Integer season, Integer number, Set<Shark> sharks) {
        Episode episode = new Episode(number, season);
        episode.setSharks(sharks);
        for (Shark s : sharks) s.addEpisode(episode);
        return episode;
    }

    private Project setEpisodeProject(Integer picht, String projectName, String website, Double valuation, String category, String description, Episode episode) {
        Project project = new Project(picht, projectName, website, valuation, category, description);
        project.setEpisode(episode);
        episode.addProject(project);
        return project;
    }

    private List<Entrepeneur> setProjectEntrepeneur(Project project, List<Entrepeneur> entrepeneurs) {
        project.setEntrepeneurs(entrepeneurs);
        for (Entrepeneur e : entrepeneurs) e.setProject(project);
        return entrepeneurs;
    }

    private List<Investment> insertInvestment(Project project, Set<Shark> investors, Double investmentAmountPerShark, Double percentageOfCompanyPerShark, Set<Shark> sharks) {
        List<Investment> investments = new ArrayList<>();
        for (Shark s : investors) {
            investments.add(new Investment(s, project, investmentAmountPerShark, percentageOfCompanyPerShark));
        }
        for (Shark s : sharks) {
            for (Investment i : investments) {
                if (i.getShark() == s) s.addInvestment(i);
            }
        }
        project.setInvestments(investments);
        return investments;
    }
}
