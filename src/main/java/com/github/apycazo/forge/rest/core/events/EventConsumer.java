package com.github.apycazo.forge.rest.core.events;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.Reactor;
import reactor.spring.context.annotation.Consumer;

/**
 * @author Andres Picazo
 */
@Consumer
public class EventConsumer
{
    @Autowired
    protected Reactor reactor;
}
