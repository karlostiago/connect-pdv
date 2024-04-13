package com.connectpdv.pdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationStarter {

	public static void main(String[] args) {
		Teste teste = new Teste();
		SpringApplication.run(ApplicationStarter.class, args);
	}

}
