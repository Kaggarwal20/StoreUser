package com.dangalgames.webengage.utility.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangalgames.webengage.utility.configuration.Config;
import com.dangalgames.webengage.utility.model.User;

@Component
public class UserService {

	@Autowired
	private Config config;

	int count = 1;
	int fileNumber = 1;
	Logger log = LoggerFactory.getLogger(this.getClass());

	public Boolean storeUser(User u) throws Exception {

		String CSV_FILE = "./UserList[" + fileNumber + "].csv";

		if (u.getUserId() == null || u.getUserId().isBlank() || u.getName() == null || u.getName().isBlank()) {
			log.error("Invalid Data");
			return false;
		}

		CSVPrinter csvPrinter = null;
		BufferedWriter writer = null;
		File file = new File(CSV_FILE);
		try {
			if (file.exists()) {
				writer = Files.newBufferedWriter(Paths.get(CSV_FILE), StandardOpenOption.APPEND,
						StandardOpenOption.CREATE);
				csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

			} else {

				writer = Files.newBufferedWriter(Paths.get(CSV_FILE), StandardOpenOption.APPEND,
						StandardOpenOption.CREATE);
				csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
				csvPrinter.printRecord("AME", "USER_ID");
			}

			csvPrinter.printRecord(u.getName(), u.getUserId());
			count++;

			if (count >= config.getCsvRows()) {
				fileNumber++;
				count = 1;
			}

			log.info("User recieved successfully.");

			return true;

		} catch (IOException e) {

			log.error("Error while creating CSV", e);

			return false;
		} finally {
			csvPrinter.close();
			writer.close();

		}

	}

}