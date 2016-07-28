package com.github.apycazo.forge.rest.core.interfaces;

import java.net.URL;

/**
 * @author Andres Picazo
 */
public interface ServiceLocator
{
    URL resolveUrl (String serviceName, String endpoint, String method);
    URL resolveUrl (String serviceName, Class<?> endpoint, String method);
}
