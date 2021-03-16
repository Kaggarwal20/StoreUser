package com.dangalgames.webengage.utility.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Value("${token}")
	private String token;
	
	@Value("${csvRows}")
	private int csvRows;


	public String getToken() {
		return token;
	}

	public int getCsvRows() {
		return csvRows;
	}	
	
	//TestComment
	

}
