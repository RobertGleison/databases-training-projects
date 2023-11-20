package com.seed.databaseseed.parseCsvToRelational;

import com.seed.databaseseed.entities.PitchData;
import com.seed.databaseseed.entities.relationalModel.Entrepeneur;
import com.seed.databaseseed.entities.relationalModel.Project;
import com.seed.databaseseed.entities.relationalModel.Shark;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

@Component
public class CsvProcessor {
    private static final Logger logger = Logger.getLogger(CsvProcessor.class.getName());
    private static final List<PitchData> pitches = new ArrayList<>();
    public static List<PitchData> getPitches() {
        return pitches;
    }

    public static void createVariables(String[] values) throws RuntimeException {
        Integer season = Integer.parseInt(values[0]);
        Integer episode = calculateGlobalEpisodeNumber(Integer.parseInt(values[1]), season);
        Integer picht = Integer.parseInt(values[2]);
        String projectName = values[3];
        String category = values[4];
        String description = values[5];
        String entrepeneurGender = values[6];
        String entrepeneurName = values[7];
        String website = values[8];
        List<String> entrepeneurNames = new ArrayList<>(entrepeneurName.contains("_") ? Arrays.asList(entrepeneurName.split("_")) : Collections.singletonList(entrepeneurName));
        Double valuation = Double.parseDouble(values[10]);
        Boolean deal = (values[11].equals("1"));
        Double dealValue = deal ? Double.parseDouble(values[12]) : null;
        Double percentageOfProject = deal ? Double.parseDouble(values[13]) : null;
        Integer numberOfSharksInDeal = deal ? Integer.parseInt(values[15]) : null;
        Double percentageOfCompanyPerShark = deal ? percentageOfProject / numberOfSharksInDeal : null;
        Double investmentAmountPerShark = deal ? dealValue / numberOfSharksInDeal : null;
        Set<Shark> sharks = new HashSet<>();
        Set<Shark> investors = new HashSet<>();
        Project project = new Project(picht, projectName, website, valuation, category, description);
        List<Entrepeneur> entrepeneurs = parseEntrepeneur(entrepeneurNames, entrepeneurGender, project);

        //Fill the sharks and investors.
        for (int i = 43; i < 50; i++) {
            if (Integer.parseInt(values[33]) == 1) sharks.add(new Shark(1, "Barbara Corcoran"));
            if (Integer.parseInt(values[34]) == 1) sharks.add(new Shark(2, "Mark Cuban"));
            if (Integer.parseInt(values[35]) == 1) sharks.add(new Shark(3, "Lori Greiner"));
            if (Integer.parseInt(values[36]) == 1) sharks.add(new Shark(4, "Robert Herjavec"));
            if (Integer.parseInt(values[37]) == 1) sharks.add(new Shark(5, "Daymond John"));
            if (Integer.parseInt(values[38]) == 1) sharks.add(new Shark(6, "Kevin O Leary"));
            if (!values[32].isEmpty()) {
                if (values[32].equals("Kevin Harrington")) sharks.add(new Shark(7, values[32]));
                if (values[32].equals("Jeff Foxworthy")) sharks.add(new Shark(8, values[32]));
            }
        }
        for (int i = 18; i < 31; i+=2) {
            if (values[18] != "") investors.add(new Shark(1, "Barbara Corcoran"));
            if (values[20]!= "")  investors.add(new Shark(2, "Mark Cuban"));
            if (values[22]!= "")  investors.add(new Shark(3, "Lori Greiner"));
            if (values[24]!= "")  investors.add(new Shark(4, "Robert Herjavec"));
            if (values[26]!= "")  investors.add(new Shark(5, "Daymond John"));
            if (values[28]!= "")  investors.add(new Shark(6, "Kevin O Leary"));
            if (values[30]!= "" && values[32].equals("Kevin Harrington"))  investors.add(new Shark(7, "Kevin Harrington"));
            if (values[30]!= "" && values[32].equals("Jeff Foxworthy"))  investors.add(new Shark(8, "Jeff Foxworthy"));
        }
        PitchData p = new PitchData(episode, season, picht, projectName, category, description, entrepeneurGender, entrepeneurs,
                                    website, valuation, deal, dealValue, percentageOfProject, numberOfSharksInDeal,
                                    percentageOfCompanyPerShark, investmentAmountPerShark, sharks, investors);
        pitches.add(p);
    }

    private static List<Entrepeneur> parseEntrepeneur(List<String> names, String gender, Project project) {
        List<Entrepeneur> entrepeneurs = new ArrayList<>();
                List<Integer> codes = EntrepeneurCodeManager.getEntrepeneurCode(names);
        for (int i = 0; i < names.size(); i++) {
            entrepeneurs.add(new Entrepeneur(codes.get(i), names.get(i), gender, project));
        }
        return entrepeneurs;
    }

    //Create the episode id
    private static Integer calculateGlobalEpisodeNumber(Integer episode, Integer season) {
        //Number of episodes of each season:
        //1 = 15
        //2 = 9
        //3 =15
        if (season == 1) return episode;
        else if (season == 2) return episode + 14;
        else if (season == 3) return episode + 23;
        return episode + 38;
    }
}
