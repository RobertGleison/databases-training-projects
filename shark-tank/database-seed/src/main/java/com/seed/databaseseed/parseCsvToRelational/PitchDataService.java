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
            investments = insertInvestment(project, p.getInvestors(), p.getInvestmentAmountPerShark(), p.getPercentageOfCompanyPerShark(),sharks);

            sharkRepository.saveAll(episode.getSharks());
            episodeRepository.save(episode);
            projectRepository.save(project);
            entrepeneurRepository.saveAll(entrepeneurs);
            investmentRepository.saveAll(investments);
            System.out.print("Teste");
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
            for(Investment i:investments){
                if(i.getShark() == s) s.addInvestment(i);
            }
        }
        project.setInvestments(investments);
        return investments;
    }
}

//    @Transactional
//    public void managePitch() {
//        //Persist all ER model for each pitch
//        List<PitchData> pitches = getAllPitches();
//        for (PitchData p : pitches) {
//            Episode episode = insertEpisode(p.getSeason(), p.getEpisode(), p.getSharks());
//            setEpisodesToShark(episode);
//            Project project = insertProject(p.getPicht(), p.getProjectName(), p.getWebsite(), p.getValuation(), p.getCategory(), p.getDescription(), episode, p.getEntrepeneurNames());
//            setProjectToEpisode(project, episode);
//            List<Entrepeneur> entrepeneurs = setProjectToEntrepeneur(project);
//            List<Investment> investments = insertInvestment(project, p.getInvestors(), p.getInvestmentAmountPerShark(), p.getPercentageOfCompanyPerShark());
//            insertInvestimentsToProject(investments, project);
//            insertProjectToInvestment(investments, project);
//            insertSharkToInvestmentAndViceVersa(investments, p.getSharks());
//            episodeRepository.save(episode);
//            sharkRepository.saveAll(episode.getSharks());
//            projectRepository.save(project);
//            entrepeneurRepository.saveAll(entrepeneurs);
//            investmentRepository.saveAll(investments);
//            System.out.print("Teste");
//        }
//    }
//
//    private void insertSharkToInvestmentAndViceVersa(List<Investment> investments, Set<Shark> sharks) {
//        for(Investment i : investments){
//            for(Shark s : sharks){
//                i.addSharks(s);
//                s.addInvestment(i);
//            }
//        }
//    }
//
//
//    private List<Investment> insertProjectToInvestment(List<Investment> investments, Project project) {
//        for(Investment i : investments){
//            i.addProject(project);
//        }
//        return investments;
//    }
//
//    private Set<Shark> insertSharks(Set<Shark> sharks) {
//        return new HashSet<>(sharkRepository.saveAll(sharks));
//    }
//
//    private Episode insertEpisode(Integer season, Integer number, Set<Shark> sharks) {
//        Episode episode = new Episode(number, season);
//        episode.setSharks(insertSharks(sharks));
//        return episode;
//    }
//
//    private Project insertInvestimentsToProject(List<Investment> investments, Project project){
//        project.setInvestments(investments);
//        return project;
//    }
//    private Project insertProject(Integer picht, String projectName, String website, Double valuation, String category, String description, Episode episode, List<Entrepeneur> entrepeneurNames) {
//        Project project = new Project(picht, projectName, website, valuation, category, description);
//        project.setEntrepeneurs(entrepeneurNames);
//        project.setEpisode(episode);
//        return project;
//    }
//
//    private List<Investment> insertInvestment(Project project, Set<Shark> investors, Double investmentAmountPerShark, Double percentageOfCompanyPerShark) {
//        List<Investment> investments = new ArrayList<>();
//        for (Shark s : investors) {
//            investments.add(new Investment(s, project, investmentAmountPerShark, percentageOfCompanyPerShark));
//        }
//        logger.info("Tamanho dos investidores");
//        logger.info(String.valueOf(investors.size()));
//        return investments;
//    }
//
//    private Episode setProjectToEpisode(Project project, Episode episode) {
//        episode.addProject(project);
//        return episode;
//    }
//
//    private void setEpisodesToShark(Episode episode) {
//        Set<Shark> sharksInEpisode = episode.getSharks();
//        for (Shark s : sharksInEpisode) {
//            s.addEpisode(episode);
//        }
//    }
//
//    private List<Entrepeneur> setProjectToEntrepeneur(Project project) {
//        List<Entrepeneur> empreendedores = project.getEntrepeneurs();
//        for (Entrepeneur emp : empreendedores) {
//            emp.setProject(project);
//        }
//        return empreendedores;
//    }
//}

