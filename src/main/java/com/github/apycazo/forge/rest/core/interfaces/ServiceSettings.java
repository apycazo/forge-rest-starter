package com.github.apycazo.forge.rest.core.interfaces;

/**
 * @author Andres Picazo
 */
public interface ServiceSettings
{
    Class getSettingsClass();
    <T> T getSettings();
}
