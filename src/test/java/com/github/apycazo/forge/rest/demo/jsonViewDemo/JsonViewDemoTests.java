package com.github.apycazo.forge.rest.demo.jsonViewDemo;

import com.github.apycazo.forge.rest.demo.jsonViewDemo.sources.JsonViewDemoConfig;
import com.github.apycazo.forge.rest.demo.jsonViewDemo.sources.JsonViewRestController;
import com.jayway.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Andres Picazo
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = JsonViewDemoConfig.class
)
public class JsonViewDemoTests
{
    @LocalServerPort
    private int port;

    @Autowired
    private JsonViewRestController rc;

    @Before
    public void setup()
    {
        RestAssured.port = port;
    }

    @Test
    public void testPublicViewResponse()
    {
        when()
                .get("public")
                .then()
                .body("id", equalTo(rc.getUserInfo().getId()))
                .body("name", equalTo(rc.getUserInfo().getName()))
                .body("password", equalTo(rc.getUserInfo().getPassword()))
                .statusCode(200);
    }

    @Test
    public void testEditorViewResponse()
    {
        when()
                .get("editor")
                .then()
                .body("id", equalTo(null))
                .body("name", equalTo(rc.getUserInfo().getName()))
                .body("password", equalTo(null))
                .statusCode(200);
    }

    @Test
    public void testAdminViewResponse()
    {
        when()
                .get("admin")
                .then()
                .body("id", equalTo(null))
                .body("name", equalTo(rc.getUserInfo().getName()))
                .body("password", equalTo(rc.getUserInfo().getPassword()))
                .statusCode(200);
    }
}
