package com.github.apycazo.forge.rest.core.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;

/**
 * @author Andres Picazo
 */
@Slf4j
@Configuration
@EnableReactor
public class EventReactorConfig
{
    public static final String REACTOR_BEAN_NAME = "forge::reactor";

    @Autowired
    private reactor.core.Environment env;

    @Bean(name = REACTOR_BEAN_NAME)
    public Reactor reactor ()
    {
        return Reactors.reactor().env(env).dispatcher(Environment.EVENT_LOOP).get();
    }
}
