package ru.netology.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostmanEchoTest {

    @Test
    void shouldValidateSchemaFromPostmanEcho() {
        given()
                .baseUri("https://postman-echo.com")
                .basePath("/get")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get()
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("postman-echo-schema.json"));
    }
}