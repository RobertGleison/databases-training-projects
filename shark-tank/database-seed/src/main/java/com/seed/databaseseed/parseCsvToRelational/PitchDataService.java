package com.seed.databaseseed.parseCsvToRelational;

import com.seed.databaseseed.entities.relationalModel.*;
import com.seed.databaseseed.entities.PitchData;
import com.seed.databaseseed.repositories.EpisodioRepository;
import com.seed.databaseseed.repositories.SharkRepository;
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
    private SharkRepository sharkRepository;

    @Autowired
    private EpisodioRepository episodioRepository;

    @Transactional
    public void managePitch() {
        List<PitchData> pitches = getAllPitches();
        for (PitchData p : pitches) {
            Episodio episodio = insertEpisode(p.getSeason(), p.getEpisode(),p.getSharks());
            insertProject(p.getPicht(), p.getProjectName(), p.getWebsite(),p.getValuation(),p.getCategory(),
                    p.getDescription(), episodio, p.getEntrepeneurNames(),p.getInvestmentAmountPerShark(),p.getPercentageOfCompanyPerShark());
        }
    }

    private void insertProject(Integer picht, String projectName, String website, Double valuation, String category, String description, Episodio episode, List<Empreendedor> entrepeneurNames, Double investmentAmountPerShark, Double percentageOfCompanyPerShark) {
        Projeto projeto = new Projeto(picht, projectName, website, valuation, category, description);
        projeto.setEmpreendedores(entrepeneurNames);
        projeto.setEpisodio(episode);


    }

    private Episodio insertEpisode(Integer season, Integer number, Set<Shark> sharks){
        Episodio episodio = new Episodio(number, season);
        episodio.setSharks(insertSharks(sharks));
        return episodioRepository.save(episodio);
    }

    private Set<Shark> insertSharks(Set<Shark> sharks) {
        Set<Shark> sharksToSave = new HashSet<>();
        for (Shark s: sharks) {
            if(!sharkRepository.existsById(s.getId())) sharksToSave.add(s);
        }
        return new HashSet<>(sharkRepository.saveAll(sharksToSave));
    }
}
