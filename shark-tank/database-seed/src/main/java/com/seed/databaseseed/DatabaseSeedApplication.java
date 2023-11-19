package com.seed.databaseseed;

import com.seed.databaseseed.parseCsvToRelational.CsvProcessor;
import com.seed.databaseseed.parseCsvToRelational.PitchDataService;
import com.seed.databaseseed.repositories.EpisodioRepository;
import com.seed.databaseseed.repositories.SharkRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = "com.seed.databaseseed")
public class DatabaseSeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseSeedApplication.class, args);

		String csvFilePath = "/home/robert/Projects/databases-training-projects/shark-tank/sharkTankDataSet.csv";
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

		PitchDataService service = new PitchDataService();
		service.managePitch();
	}
}



