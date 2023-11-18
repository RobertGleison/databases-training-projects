package com.seed.databaseseed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class DatabaseSeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseSeedApplication.class, args);
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



