package com.github.apycazo.forge.rest.core.domains;

import java.io.IOException;

/**
 * @author Andres Picazo
 */
public class ConfigurationException extends IOException
{

    public ConfigurationException () {
        super();
    }

    public ConfigurationException (String msg) {
        super(msg);
    }
}
