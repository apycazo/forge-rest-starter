package com.github.apycazo.forge.rest.core.components;

import com.github.apycazo.forge.rest.RestForge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Andres Picazo
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = RestForge.PROP_PREFIX, name = "interceptor-loader.enable", matchIfMissing = true)
public class InterceptorLoadConfig extends WebMvcConfigurerAdapter
{
    @Autowired(required = false)
    private HandlerInterceptor[] interceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        if (interceptors == null || interceptors.length == 0) {
            log.info("No interceptors to register");
        }
        else {
            for (HandlerInterceptor hi : interceptors) {
                registry.addInterceptor(hi);
                log.info("Registered '{}'", hi.getClass().getName());
            }
        }
    }
}
