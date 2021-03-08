package com.dangalgames.webengage.utility.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import com.dangalgames.webengage.utility.model.User;

@Component
public class UserService {

	int count = 1;
	int fileNumber = 1;

	public Boolean storeUser(User u) {
		String CSV_FILE = "./UserList[" + fileNumber + "].csv";
		
		if(u.getUserId() == null || u.getUserId().isBlank() || u.getName() == null || u.getName().isBlank()) {
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
		
				
				writer = Files.newBufferedWriter(Paths.get(CSV_FILE), 
						StandardOpenOption.APPEND,
						StandardOpenOption.CREATE);
				csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
				csvPrinter.printRecord("NAME","USER_ID");
			}
			csvPrinter.printRecord(u.getName(), u.getUserId());
			count++;
			
			if (count >= 1048576) {	
				fileNumber++;
				count = 1;	
			}
			
			csvPrinter.close();
			writer.close();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;			
		}
	}
}