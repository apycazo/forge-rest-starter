package com.github.apycazo.forge.rest.core.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Andres Picazo
 */
@Component
public class OriginHeaderInterceptor implements ClientHttpRequestInterceptor
{
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public ClientHttpResponse intercept(
            HttpRequest httpRequest,
            byte[] bytes,
            ClientHttpRequestExecution clientHttpRequestExecution)
            throws IOException
    {
        HttpHeaders headers = httpRequest.getHeaders();
        String appName = Optional.ofNullable(applicationContext.getId()).orElse("rest-service");
        headers.add("X-Source-Name", appName);
        headers.add("X-Request-Id", UUID.randomUUID().toString());
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
