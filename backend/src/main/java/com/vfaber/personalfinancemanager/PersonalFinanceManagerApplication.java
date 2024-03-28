package com.vfaber.personalfinancemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.vfaber.personalfinancemanager"})
public class PersonalFinanceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceManagerApplication.class, args);
	}

}
