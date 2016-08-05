package com.github.apycazo.forge.rest.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Configuration for the core package. Will be picked either by the 'RestForge' main class, or by the
 * <strong>src/main/resources/META_INF/spring-factories</strong> configuration file.
 *
 * @author
 *      Andres Picazo
 */
@Configuration
@ComponentScan(basePackageClasses = { ForgeRestAutoConfig.class })
public class ForgeRestAutoConfig
{
}
