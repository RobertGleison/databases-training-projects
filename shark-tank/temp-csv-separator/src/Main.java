import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        String csvFilePath = "/home/robert/Projects/databases-training-projects/shark-tank/sharkTankDataSet.csv";
        String line;
        boolean first = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            while ((line = reader.readLine()) != null) {
                //First line is column names
                if (first) {
                    first = false;
                    continue;
                }
                String[] values = line.split(",");
                try {
                    CsvProcessor.createVariables(values);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter: " + e.getMessage());
                    e.getStackTrace();
                    continue;
                } catch (NullPointerException e) {
                    System.out.println("oi");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}