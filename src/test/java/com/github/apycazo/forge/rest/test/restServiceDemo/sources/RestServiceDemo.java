package com.github.apycazo.forge.rest.test.restServiceDemo.sources;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Andres Picazo
 */
@RestController
@RequestMapping(value = "${" + RestServiceDemo.MAPPING_PROP + "}", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestServiceDemo
{
    public static final String MAPPING_PROP = "demo.rest-service.mapping";

    private Map<String,String> map = new ConcurrentHashMap<>();

    @GetMapping
    public Collection<String> findAll () {
        return map.values();
    }

    @GetMapping("{id}")
    public String findOne (@PathVariable String id)
    {
        return map.get(id);
    }

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String save (@PathVariable String id, @RequestBody String value)
    {
        map.put(id, value);
        return value;
    }

    @PutMapping("{id}")
    public String update (@PathVariable String id, @RequestBody String value) {
        String oldValue = map.get(id);
        map.put(id,value);
        return oldValue;
    }

    @DeleteMapping
    public int removeAll ()
    {
        int count = map.size();
        map.clear();
        return count;
    }

}
