package com.itransition.profunding.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 18:48
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    private static final int MAX_AGE = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(false).maxAge(MAX_AGE);
    }
}
