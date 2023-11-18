import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CsvProcessor {
    public static void createVariables(String[] values) throws RuntimeException{
        Double dealValue;
        Double percentageOfProject;
        Integer numberOfSharksInDeal;
        Double percentageOfCompanyPerShark;
        Double investmentAmountPerShark;
        String[] entrepeneurNames = new String[3];
        Set<String> sharks = new HashSet<>();

        Integer season = Integer.parseInt(values[0]);
        Integer episode = Integer.parseInt(values[1]);
        Integer pichts = Integer.parseInt(values[2]);
        String projectName = values[3];
        String category = values[4];
        String description = values[5];
        String entrepeneurGender = values[6];
        String entrepeneurName = values[7];
        if (entrepeneurName.contains(",") || entrepeneurName.contains(" and "))
            entrepeneurNames = entrepeneurName.split(", | and ");
        else entrepeneurName = values[7];
        String website = values[8];
        Double askedValue = Double.parseDouble(values[9]);
        Boolean deal = (values[11].equals("1"));
        if (deal) {
            dealValue = Double.parseDouble(values[12]);
            percentageOfProject = Double.parseDouble(values[13]);
            numberOfSharksInDeal = Integer.parseInt(values[15]);
            percentageOfCompanyPerShark = percentageOfProject / numberOfSharksInDeal;
            investmentAmountPerShark = dealValue / numberOfSharksInDeal;
        } else {
            dealValue = null;
            percentageOfProject = null;
            numberOfSharksInDeal = null;
            percentageOfCompanyPerShark = null;
            investmentAmountPerShark = null;
        }
        for (int i = 43; i < 50; i++) {
            if (!values[32].isEmpty()) sharks.add(values[32]);
            if (Integer.parseInt(values[33]) == 1) sharks.add("Barbara Corcoran");
            if (Integer.parseInt(values[34]) == 1) sharks.add("Mark Cuban");
            if (Integer.parseInt(values[35]) == 1) sharks.add("Lori Greiner");
            if (Integer.parseInt(values[36]) == 1) sharks.add("Robert Herjavec");
            if (Integer.parseInt(values[37]) == 1) sharks.add("Daymond John");
            if (Integer.parseInt(values[38]) == 1) sharks.add("Kevin O Leary");
        }

        System.out.print("Número de Episódio: " + episode);
        System.out.print(", Temporada: " + season);
        System.out.print(", Pichts ID: " + pichts);
        System.out.print(", Project Name: " + projectName);
        System.out.print(", Category: " + category);
        System.out.print(", Description: " + description);
        System.out.print(", Entrepreneur Gender: " + entrepeneurGender);
        System.out.print(", Entrepreneur Name(s): " + Arrays.toString(entrepeneurNames));
        System.out.print(", Website: " + website);
        System.out.print(", Asked Value: " + askedValue);
        System.out.print(", Deal: " + deal);
        System.out.print(", Deal Value: " + dealValue);
        System.out.print(", Percentage of Project: " + percentageOfProject);
        System.out.print(", Number of Sharks in Deal: " + numberOfSharksInDeal);
        System.out.print(", Percentage of Company per Shark: " + percentageOfCompanyPerShark);
        System.out.print(", Investment Amount per Shark: " + investmentAmountPerShark);
        System.out.println();
    }

}
