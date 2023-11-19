package com.seed.databaseseed.parseCsvToRelational;

import com.seed.databaseseed.entities.relationalModel.*;
import com.seed.databaseseed.entities.PitchData;
import com.seed.databaseseed.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PitchDataService {

    private static final Logger logger = Logger.getLogger(PitchDataService.class.getName());

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
        List<PitchData> pitches = getAllPitches();
        for (PitchData p : pitches) {
            Episode episode = insertEpisode(p.getSeason(), p.getEpisode(), p.getSharks());
            Set<Shark> sharks = insertSharks(p.getSharks());
            Project project = insertProject(p.getPicht(), p.getProjectName(), p.getWebsite(), p.getValuation(), p.getCategory(), p.getDescription(), episode, p.getEntrepeneurNames());
            List<Entrepeneur> emp = insertEntrepeneur(p.getEntrepeneurNames(), project);
            List<Investment> investments = insertInvestments(project, p.getInvestors(), p.getInvestmentAmountPerShark(), p.getPercentageOfCompanyPerShark());

            episodeRepository.save(setProjectToEpisode(project, episode));
            sharkRepository.saveAll(setEpisodesToShark(episode));
            projectRepository.save(project);
            entrepeneurRepository.saveAll(emp);
            investmentRepository.saveAll(investments);

            logger.info("I am in the final of the code");
        }
    }

    private Set<Shark> insertSharks(Set<Shark> sharks) {
        return new HashSet<>(sharkRepository.saveAll(sharks));
    }

    private Episode insertEpisode(Integer season, Integer number, Set<Shark> sharks) {
        Episode episode = new Episode(number, season);
        episode.setSharks(sharks);
        return episode;
    }

    private List<Entrepeneur> insertEntrepeneur(List<Entrepeneur> emp, Project project) {
        emp.forEach(e -> e.setProject(project));
        return emp;
    }

    private Project insertProject(Integer picht, String projectName, String website, Double valuation, String category, String description, Episode episode, List<Entrepeneur> entrepeneurNames) {
        Project project = new Project(picht, projectName, website, valuation, category, description);
        project.setEntrepeneurs(entrepeneurNames);
        project.setEpisode(episode);
        return project;
    }

    private List<Investment> insertInvestments(Project project, Set<Shark> investors, Double investmentAmountPerShark, Double percentageOfCompanyPerShark) {
        return investors.stream()
                .map(s -> new Investment(s, project, investmentAmountPerShark, percentageOfCompanyPerShark))
                .collect(Collectors.toList());
    }

    private Episode setProjectToEpisode(Project project, Episode episode) {
        episode.addProject(project);
        return episode;
    }

    private Set<Shark> setEpisodesToShark(Episode episode) {
        return episode.getSharks().stream()
                .peek(s -> s.addEpisode(episode))
                .collect(Collectors.toSet());
    }
//    @Transactional
//    public void managePitch() {
//        //Persist all ER model for each pitch
//        List<PitchData> pitches = getAllPitches();
//        for (PitchData p : pitches) {
//            Episode episode = insertEpisode(p.getSeason(), p.getEpisode(), p.getSharks());
//            Set<Shark> sharks = setEpisodesToShark(episode);
//            Project project = insertProject(p.getPicht(), p.getProjectName(), p.getWebsite(), p.getValuation(),
//                    p.getCategory(), p.getDescription(), episode, p.getEntrepeneurNames());
//            Episode episode2 = setProjectToEpisode(project, episode);
//            List<Entrepeneur> emp = setProjectToEntrepeneur(project);
//            emp = insertEntrepeneur(p.getEntrepeneurNames(), project);
//            List<Investment> investments = insertInvestiment(project, p.getInvestors(), p.getInvestmentAmountPerShark(), p.getPercentageOfCompanyPerShark());
//            episodeRepository.save(episode2);
//            sharkRepository.saveAll(sharks);
//            projectRepository.save(project);
//            entrepeneurRepository.saveAll(emp);
//            investmentRepository.saveAll(investments);
//            logger.info("I am in the final of the code");
//        }
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
//    private List<Entrepeneur> insertEntrepeneur(List<Entrepeneur> emp, Project project){
//        for (Entrepeneur e : emp) {
//            e.setProject(project);
//        }
//        return emp;
//
//    }
//    private Project insertProject(Integer picht, String projectName, String website, Double valuation, String category, String description, Episode episode, List<Entrepeneur> entrepeneurNames) {
//        logger.info("I'm inside insertProject");
//        Project project = new Project(picht, projectName, website, valuation, category, description);
//        project.setEntrepeneurs(entrepeneurNames);
//        project.setEpisode(episode);
//        return project;
//    }
//
//    private List<Investment> insertInvestiment(Project project, Set<Shark> investors, Double investmentAmountPerShark, Double percentageOfCompanyPerShark) {
//        logger.info("I'm inside insertInvestment");
//        List<Investment> investments = new ArrayList<>();
//        for (Shark s : investors) {
//            investments.add(new Investment(s, project, investmentAmountPerShark, percentageOfCompanyPerShark));
//        }
//        return investments;
//    }
//
//    private Episode setProjectToEpisode(Project project, Episode episode) {
//        logger.info("I'm inside setProjectToEpisode");
//        episode.addProject(project);
//        return episode;
//    }
//
//    private Set<Shark> setEpisodesToShark(Episode episode) {
//        logger.info("I'm inside setEpisodesToShark");
//        Set<Shark> sharksInEpisode = episode.getSharks();
//        for (Shark s : sharksInEpisode) {
//            s.addEpisode(episode);
//        }
//        return sharksInEpisode;
//    }
//
//    private List<Entrepeneur> setProjectToEntrepeneur(Project project) {
//        logger.info("I'm inside setProjectToEntrepeneur");
//        List<Entrepeneur> entrepeneurs = project.getEntrepeneurs();
//        for (Entrepeneur emp : entrepeneurs) {
//            emp.setProject(project);
//        }
//        return entrepeneurs;
//    }

//    @Transactional
//    public void managePitch() {
//        //Persist all ER model for each pitch
//        List<PitchData> pitches = getAllPitches();
//        for (PitchData p : pitches) {
//            Episode episode = insertEpisode(p.getSeason(), p.getEpisode(), p.getSharks());
//            setEpisodesToShark(episode);
//            Project project = insertProject(p.getPicht(), p.getProjectName(), p.getWebsite(), p.getValuation(),
//                    p.getCategory(), p.getDescription(), episode, p.getEntrepeneurNames());
//            if(project != null)  logger.info("Created project:" +
//                                                project.getCategory()+ " " +
//                                                project.getName());
//            projectRepository.save(project);
//            setProjectToEpisode(project, episode);
//            setProjectToEntrepeneur(project);
//            List<Investment> investments = insertInvestiment(project, p.getInvestors(), p.getInvestmentAmountPerShark(), p.getPercentageOfCompanyPerShark());
//            logger.info("I am in the final of the code");
//        }
//    }
//
//    private Set<Shark> insertSharks(Set<Shark> sharks) {
//        return new HashSet<>(sharkRepository.saveAll(sharks));
//    }
//
//    private Episode insertEpisode(Integer season, Integer number, Set<Shark> sharks) {
//        Episode episode = new Episode(number, season);
//        episode.setSharks(insertSharks(sharks));
//        return episodeRepository.save(episode);
//    }
//
//    private Project insertProject(Integer picht, String projectName, String website, Double valuation, String category, String description, Episode episode, List<Entrepeneur> entrepeneurNames) {
//        logger.info("I'm inside insertProject");
//        Project project = new Project(picht, projectName, website, valuation, category, description);
//        project.setEntrepeneurs(entrepeneurNames);
//        project.setEpisode(episode);
//        return project;
//    }
//
//    private List<Investment> insertInvestiment(Project project, Set<Shark> investors, Double investmentAmountPerShark, Double percentageOfCompanyPerShark) {
//        logger.info("I'm inside insertInvestment");
//        List<Investment> investments = new ArrayList<>();
//        for (Shark s : investors) {
//            investments.add(new Investment(s, project, investmentAmountPerShark, percentageOfCompanyPerShark));
//        }
//        return investments;
//    }
//
//    private void setProjectToEpisode(Project project, Episode episode) {
//        logger.info("I'm inside setProjectToEpisode");
//        episode.addProject(project);
//    }
//
//    private void setEpisodesToShark(Episode episode) {
//        logger.info("I'm inside setEpisodesToShark");
//        Set<Shark> sharksInEpisode = episode.getSharks();
//        for (Shark s : sharksInEpisode) {
//            s.addEpisode(episode);
//        }
//    }
//
//    private void setProjectToEntrepeneur(Project project) {
//        logger.info("I'm inside setProjectToEntrepeneur");
//        List<Entrepeneur> empreendedores = project.getEntrepeneurs();
//        for (Entrepeneur emp : empreendedores) {
//            emp.setProject(project);
//        }
//    }


}
