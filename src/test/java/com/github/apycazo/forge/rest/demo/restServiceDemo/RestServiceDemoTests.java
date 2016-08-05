package com.github.apycazo.forge.rest.demo.restServiceDemo;

import com.github.apycazo.forge.rest.demo.restServiceDemo.sources.RestServiceDemo;
import com.github.apycazo.forge.rest.demo.restServiceDemo.sources.RestServiceDemoConfig;
import com.jayway.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Andres Picazo
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = RestServiceDemoConfig.class,
        properties = {"demo.rest-service.mapping:/api"}
)
public class RestServiceDemoTests
{

    @LocalServerPort
    private int port;

    @Value("${" + RestServiceDemo.MAPPING_PROP + "}")
    private String mapping;

    @Autowired(required = false)
    private RestServiceDemo rsd;

    @Before
    public void setup()
    {
        RestAssured.port = port;
    }

    @Test
    public void serviceIsLoaded()
    {
        Assert.assertTrue("Service '" + RestServiceDemo.class.getName() + "' not wired", rsd != null);
    }

    @Test
    public void getRequestIsSuccessful()
    {
        given()
                .queryParam("param", "value")
                .when()
                .get(mapping)
                .then()
                .statusCode(200);
    }

    @Test
    public void postEntryIsSuccessful()
    {
        given()
                .body("value-a")
                .when()
                .post(mapping + "/{id}", "a")
                .then()
                .statusCode(201);
    }

    @Test
    public void getNonEmptyResult ()
    {
        postEntryIsSuccessful();
        when()
            .get(mapping + "/{id}", "a")
            .then()
            .body(equalTo("value-a"))
            .statusCode(200);
    }

    @Test
    public void removeAll ()
    {
        // Manually clear elements
        rsd.removeAll();

        getNonEmptyResult();
        when()
                .delete(mapping)
                .then()
                .body(equalTo("1"))
                .statusCode(200);
    }

}
