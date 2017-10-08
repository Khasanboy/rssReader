package com.tsar.datahunter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatahunterApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DatahunterApplication.class);
	

	public static void main(String[] args) {
		
		SpringApplication.run(DatahunterApplication.class, args);
		
		logger.debug("Application started");
	}
}
