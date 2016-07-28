package com.github.apycazo.forge.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Andres Picazo
 */
@SpringBootApplication
public class RestForge
{
    public static final String PROP_PREFIX = "forge.";

    public static void main (String [] args)
    {
        SpringApplication springApp = new SpringApplication();
        springApp.setAdditionalProfiles("default", "config", "dev");
        springApp.run(RestForge.class, args);
    }
}
