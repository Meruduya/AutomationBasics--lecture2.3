package ru.netology.rest;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostmanEchoTest {

    @Test
    void shouldReturnPostedPlainText() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("https://postman-echo.com")
                .body("some data") // отправляемые данные (заголовки и query можно выставлять аналогично)
                // Выполняемые действия
                .when()
                .post("/post")
                // Проверки
                .then()
                .statusCode(200)
                // JSONPath выражение к верхнеуровневому полю 'data'
                .body("data", equalTo("some data"));
    }
    @Test
    void shouldReturnPostedUtf8Text() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("Ласточка") // проверяем кириллицу
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("Ласточка"));
    }
}
