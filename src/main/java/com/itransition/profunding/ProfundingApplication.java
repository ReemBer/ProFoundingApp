package com.itransition.profunding;

import com.itransition.profunding.controller.WebConfiguration;
import com.itransition.profunding.security.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SecurityConfiguration.class, WebConfiguration.class})
public class ProfundingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfundingApplication.class, args);
	}
}
