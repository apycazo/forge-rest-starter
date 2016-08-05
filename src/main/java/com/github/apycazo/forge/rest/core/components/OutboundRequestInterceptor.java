package com.github.apycazo.forge.rest.core.components;

import com.github.apycazo.forge.rest.RestForge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
 * If enabled, this interceptor will include two additional headers on every RestTemplate request:
 * <ul>
 * <li>X-Request-Origin : Indicating the service sending the request</li>
 * <li>X-Request-Id : A randomly created id for the request</li>
 * </ul>
 * <p>
 * To disable user property: <strong>forge.outbound-interceptor.enable: false</strong>.
 *
 * @author Andres Picazo
 */
@Component
@ConditionalOnProperty(prefix = RestForge.PROP_PREFIX, value = "outbound-interceptor.enable", matchIfMissing = true)
public class OutboundRequestInterceptor implements ClientHttpRequestInterceptor
{
    @Autowired
    private ApplicationContext applicationContext;

    private final String requestOriginHeader = "X-Request-Origin";
    private final String requestIdHeader = "X-Request-Id";

    @Override
    public ClientHttpResponse intercept(
            HttpRequest httpRequest,
            byte[] bytes,
            ClientHttpRequestExecution clientHttpRequestExecution)
            throws IOException
    {
        HttpHeaders headers = httpRequest.getHeaders();

        if (!headers.containsKey(requestOriginHeader)) {
            String appName = Optional.ofNullable(applicationContext.getId()).orElse("rest-service");
            headers.add(requestOriginHeader, appName);
        }
        if (!headers.containsKey(requestIdHeader)) {
            headers.add(requestIdHeader, UUID.randomUUID().toString());
        }

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
