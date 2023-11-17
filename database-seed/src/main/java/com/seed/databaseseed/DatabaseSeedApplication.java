package com.seed.databaseseed;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//@SpringBootApplication
public class DatabaseSeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseSeedApplication.class, args);
		String caminhoArquivoCSV = "/home/robert/Projects/databases-training-projects/sharkTankDataSet";

		try (CSVReader reader = new CSVReader(new FileReader(caminhoArquivoCSV))) {
			List<String[]> linhas = reader.readAll();

			for (String[] linha : linhas) {
				for (String dado : linha) {
					System.out.print(dado + " ");
				}
				System.out.println();
			}
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}
	}

	/*

	*/


}



