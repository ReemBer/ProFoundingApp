package com.itransition.profunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 18:48
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Resource
    private Environment environment;

    private static final String PROP_MAX_AGE = "security.authentication.token.expiration_time";

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        String a = environment.getProperty(PROP_MAX_AGE);
        long b = Long.parseLong(a);
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                .allowCredentials(false)
                .maxAge(Long.parseLong(environment.getProperty(PROP_MAX_AGE)));
    }
}
