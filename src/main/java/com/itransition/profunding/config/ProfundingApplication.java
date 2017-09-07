package com.itransition.profunding.config;

import com.itransition.profunding.security.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SecurityConfiguration.class})
public class ProfundingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfundingApplication.class, args);
	}
}
