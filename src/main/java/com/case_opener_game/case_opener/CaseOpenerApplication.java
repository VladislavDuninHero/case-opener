package com.case_opener_game.case_opener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CaseOpenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseOpenerApplication.class, args);

		System.out.println(123);
	}

}
