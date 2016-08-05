package com.github.apycazo.forge.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class, if used as a template. Will automatically add profiles: 'config' and 'dev'.
 * <p>
 * The following property files will be automatically picked:
 * <ul>
 *     <li>application-config.[yml|properties]</li>
 *     <li>application-dev.[yml|properties]</li>
 * </ul>
 *
 * @author
 *      Andres Picazo
 */
@SpringBootApplication
public class RestForge
{
    /**
     * A common prefix for the internal properties. Value is 'forge'.
     */
    public static final String PROP_PREFIX = "forge";
    /**
     * Same as <strong>PROP_PREFIX</strong>, but ending in a dot character.
     */
    public static final String PROP_PREFIX_DOT = PROP_PREFIX + ".";

    /**
     * Starts the application, including profiles 'config' and 'dev'.
     *
     * @param args Any arguments the package has been provided with.
     */
    public static void main (String [] args)
    {
        SpringApplication springApp = new SpringApplication();
        springApp.setAdditionalProfiles("config", "dev");
        springApp.run(RestForge.class, args);
    }
}
