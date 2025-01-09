package com.example;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ApiTest {
    @Test
    @Description("UI test example")
    public void testGetRequest() {
        RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/todos/1")
                .then()
                .statusCode(200);
    }
}
