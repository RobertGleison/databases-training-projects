import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "/home/robert/Projects/databases-training-projects/shark-tank/sharkTankDataSet.csv";
//        Scanner sc = new Scanner(System.in);
//        String csvFilePath = sc.nextLine();
        String line;
        boolean first = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            while ((line = reader.readLine()) != null) {
                //First line is for column names
                if (first) {
                    first = false;
                    continue;
                }
                String[] values = line.split(",");
                try {
                    CsvProcessor.createVariables(values);
                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        sc.close();
    }
}