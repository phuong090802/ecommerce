package com.ute.ecwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EcWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcWebAppApplication.class, args);
	}

}
