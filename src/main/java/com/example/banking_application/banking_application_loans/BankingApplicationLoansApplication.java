package com.example.banking_application.banking_application_loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class BankingApplicationLoansApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BankingApplicationLoansApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
		app.run(args);
	}

}
