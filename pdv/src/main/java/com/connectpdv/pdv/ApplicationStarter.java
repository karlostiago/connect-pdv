package com.connectpdv.pdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@EntityScan("com.util.commons.*")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}

}
