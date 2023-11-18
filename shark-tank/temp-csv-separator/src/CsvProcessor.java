import java.util.*;

public class CsvProcessor {

    public static void createVariables(String[] values) throws RuntimeException {
        Integer season = Integer.parseInt(values[0]);
        Integer episode = Integer.parseInt(values[1]);
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
        System.out.println(p);
    }
}
