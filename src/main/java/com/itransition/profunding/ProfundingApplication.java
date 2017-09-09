package com.itransition.profunding;

import com.itransition.profunding.controller.WebConfiguration;
import com.itransition.profunding.security.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Import({SecurityConfiguration.class, WebConfiguration.class})
@PropertySource({"classpath:custom.properties", "classpath:application.properties"})
public class ProfundingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfundingApplication.class, args);
	}
}
