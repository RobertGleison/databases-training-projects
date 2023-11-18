import java.util.*;

public class CsvProcessor {

    public static List<PitchData> pitches = new ArrayList<>();

    public static void createVariables(String[] values) throws RuntimeException {
        Integer season = Integer.parseInt(values[0]);
        Integer episode = calculateGlobalEpisodeNumber(Integer.parseInt(values[1]), season);
        Integer picht = Integer.parseInt(values[2]);
        String projectName = values[3];
        String category = values[4];
        String description = values[5];
        String entrepeneurGender = values[6];
        String entrepeneurName = values[7];
        List<String> entrepeneurNames = new ArrayList<>(entrepeneurName.contains("_") ? Arrays.asList(entrepeneurName.split("_")) : Collections.singletonList(entrepeneurName));
        String website = values[8];
        Double askedValue = Double.parseDouble(values[9]);
        Boolean deal = (values[11].equals("1"));
        Double dealValue = deal ? Double.parseDouble(values[12]) : null;
        Double percentageOfProject = deal ? Double.parseDouble(values[13]) : null;
        Integer numberOfSharksInDeal = deal ? Integer.parseInt(values[15]) : null;
        Double percentageOfCompanyPerShark = deal ? percentageOfProject / numberOfSharksInDeal : null;
        Double investmentAmountPerShark = deal ? dealValue / numberOfSharksInDeal : null;

        Set<String> sharks = new HashSet<>();
        for (int i = 43; i < 50; i++) {
            if (!values[32].isEmpty()) sharks.add(values[32]);
            if (Integer.parseInt(values[33]) == 1) sharks.add("Barbara Corcoran");
            if (Integer.parseInt(values[34]) == 1) sharks.add("Mark Cuban");
            if (Integer.parseInt(values[35]) == 1) sharks.add("Lori Greiner");
            if (Integer.parseInt(values[36]) == 1) sharks.add("Robert Herjavec");
            if (Integer.parseInt(values[37]) == 1) sharks.add("Daymond John");
            if (Integer.parseInt(values[38]) == 1) sharks.add("Kevin O Leary");
        }
        PitchData p = new PitchData(episode, season, picht, projectName, category, description, entrepeneurGender, entrepeneurNames, website, askedValue, deal, dealValue, percentageOfProject, numberOfSharksInDeal, percentageOfCompanyPerShark, investmentAmountPerShark, sharks);
        pitches.add(p);
        //        System.out.println(p);
    }

    private static Integer calculateGlobalEpisodeNumber(Integer episode, Integer season){
//        Number of episodes of each season:
//        1 = 15
//        2 = 9
//        3 =15
        if(season == 1) return episode;
        else if(season == 2) return episode + 14;
        else if(season == 3) return episode + 23;
        return episode + 38;
    }
}
