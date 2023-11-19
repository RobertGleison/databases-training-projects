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

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    private EmpreendedorRepository empreendedorRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private SharkRepository sharkRepository;

    @Autowired
    private EpisodioRepository episodioRepository;

    @Transactional
    public void managePitch() {
        List<PitchData> pitches = getAllPitches();
        for (PitchData p : pitches) {
            Episodio episodio = insertEpisode(p.getSeason(), p.getEpisode(), p.getSharks());
            setEpisodeToShark(episodio);
            Projeto projeto = insertProject(p.getPicht(), p.getProjectName(), p.getWebsite(), p.getValuation(),
                    p.getCategory(), p.getDescription(), episodio, p.getEntrepeneurNames());
            setProjectToEpisode(projeto, episodio);
            setProjectToEntrepeneur(projeto);
            List<Investimento> investimentos = insertInvestimento(projeto, p.getInvestors(), p.getInvestmentAmountPerShark(), p.getPercentageOfCompanyPerShark());
        }
    }

    private List<Investimento> insertInvestimento(Projeto projeto, Set<Shark> investors, Double investmentAmountPerShark, Double percentageOfCompanyPerShark) {
        List<Investimento> investimentos = new ArrayList<>();
        for (Shark s : investors) {
            investimentos.add(new Investimento(s, projeto, investmentAmountPerShark, percentageOfCompanyPerShark));
        }
        return investimentos;
    }

    private void setProjectToEntrepeneur(Projeto projeto) {
        List<Empreendedor> empreendedores = projeto.getEmpreendedores();
        for (Empreendedor emp : empreendedores) {
            emp.setProjeto(projeto);

        }
    }

    private Projeto insertProject(Integer picht, String projectName, String website, Double valuation, String category, String description, Episodio episode, List<Empreendedor> entrepeneurNames) {
        Projeto projeto = new Projeto(picht, projectName, website, valuation, category, description);
        projeto.setEmpreendedores(entrepeneurNames);
        projeto.setEpisodio(episode);
        return projeto;
    }

    private void setProjectToEpisode(Projeto project, Episodio episode) {
        episode.addProject(project);
    }

    private void setEpisodeToShark(Episodio episode) {
        Set<Shark> sharksInEpisode = episode.getSharks();
        for (Shark s : sharksInEpisode) {
            s.addEpisodio(episode);
        }

    }

    private Episodio insertEpisode(Integer season, Integer number, Set<Shark> sharks) {
        Episodio episodio = new Episodio(number, season);
        episodio.setSharks(insertSharks(sharks));
        return episodioRepository.save(episodio);
    }

    private Set<Shark> insertSharks(Set<Shark> sharks) {
        Set<Shark> sharksToSave = new HashSet<>();
        for (Shark s : sharks) {
            if (!sharkRepository.existsById(s.getId())) sharksToSave.add(s);
        }
        return new HashSet<>(sharkRepository.saveAll(sharksToSave));
    }
}