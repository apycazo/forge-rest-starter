package com.github.apycazo.forge.rest.core.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.apycazo.forge.rest.core.interfaces.ServiceSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Reactor;
import reactor.event.Event;

import java.io.IOException;

/**
 * @author Andres Picazo
 */
@Slf4j
@Service
public class Ops
{

    private final ObjectMapper mapper;
    private final ObjectMapper prettyMapper;
    @Autowired(required = false)
    private ServiceSettings settings;
    @Autowired(required = false)
    private Reactor reactor;

    public Ops ()
    {
        mapper = new ObjectMapper();
        prettyMapper = new ObjectMapper();
        prettyMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public String toJson (Object obj) throws JsonProcessingException
    {
        return mapper.writeValueAsString(obj);
    }

    public String toJson (Object obj, boolean pretty) throws JsonProcessingException
    {
        return (pretty ? prettyMapper : mapper).writeValueAsString(obj);
    }

    public <T> T fromJson (String string, Class<T> clazz) throws IOException
    {
        return mapper.readValue(string, clazz);
    }

    public void logReport()
    {
        // TODO: Log system and service information

        log.info("Reporting in");
    }

    public ServiceSettings getServiceSettings ()
    {
        return settings;
    }

    public void sendEvent (String target, String topic, Object payload)
    {

    }

    public void generateEvent (String topic)
    {
        if (reactor != null) {
            reactor.notify(topic);
        }
    }

    public void generateEvent (String topic, Object payload)
    {
        if (reactor != null) {
            reactor.notify(topic, Event.wrap(payload));
        }
    }

}
