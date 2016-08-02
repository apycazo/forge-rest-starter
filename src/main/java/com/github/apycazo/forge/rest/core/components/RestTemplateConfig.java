package com.github.apycazo.forge.rest.core.components;

import com.github.apycazo.forge.rest.RestForge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Instances a netty factory, a rest template and an async rest template using it.
 * If another 'ClientHttpRequestFactory' is found, then it will be used for both.
 * @author Andres Picazo
 */
@Configuration
@ConfigurationProperties(prefix = RestForge.PROP_PREFIX + "rest-template")
public class RestTemplateConfig
{
    int timeout = 5000;

    @Autowired(required = false)
    ClientHttpRequestInterceptor [] requestInterceptors;

    @Bean(name = "forge::netty-request-factory")
    @ConditionalOnMissingBean(ClientHttpRequestFactory.class)
    protected Netty4ClientHttpRequestFactory nettyFactory ()
    {
        Netty4ClientHttpRequestFactory factory = new Netty4ClientHttpRequestFactory();
        factory.setConnectTimeout(timeout);
        return factory;
    }

    @Bean(name = "forge::rest-template")
    @ConditionalOnMissingBean(RestTemplate.class)
    protected RestTemplate instanceRestTemplate()
    {
        RestTemplate rt = new RestTemplate(nettyFactory());
        if (requestInterceptors != null && requestInterceptors.length > 0) {
            List<ClientHttpRequestInterceptor> list = Arrays.asList(requestInterceptors);
            rt.setInterceptors(list);
        }

        return rt;
    }

    @Bean(name = "forge::rest-template")
    @ConditionalOnMissingBean(AsyncRestTemplate.class)
    protected AsyncRestTemplate instanceAsyncRestTemplate()
    {
        return new AsyncRestTemplate(nettyFactory());
    }
}
