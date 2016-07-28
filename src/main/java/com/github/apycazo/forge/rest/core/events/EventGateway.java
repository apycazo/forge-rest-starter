package com.github.apycazo.forge.rest.core.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Reactor;
import reactor.event.Event;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Andres Picazo
 */
@Slf4j
@RestController
@RequestMapping(value = "/events")
@ConditionalOnBean(Reactor.class)
public class EventGateway
{
    @Autowired
    private Reactor reactor;

    @RequestMapping(
            value = "{topic}",
            method = POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void processTopic(
            @PathVariable("topic") String topic,
            @RequestBody String[] payload)
    {
        log.info("Received '{}' event", topic);
        reactor.notify(topic, Event.wrap(payload));
    }
}
