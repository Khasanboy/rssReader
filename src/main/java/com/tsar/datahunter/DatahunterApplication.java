package com.tsar.datahunter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatahunterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatahunterApplication.class, args);
	}
}
