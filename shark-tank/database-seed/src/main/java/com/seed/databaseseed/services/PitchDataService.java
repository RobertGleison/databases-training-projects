package com.seed.databaseseed.services;

import com.seed.databaseseed.CsvProcessor;
import com.seed.databaseseed.entities.PitchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PitchDataService {

    @Autowired
    private PitchData pitch;

    public List<PitchData> getAllPitches() {
        return CsvProcessor.getPitches();
    }
    }
