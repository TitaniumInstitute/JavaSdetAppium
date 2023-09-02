package com.ti.restassured.commands;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.apibase.ConfigFactory.getDefaultConfig;

public class RequestSpecificationDemo {
    private static final String BASE_URL = "https://gorest.co.in/public/v2/";

    @Test
    void usingLocalRequestSpec(){
        RestAssured
                .given()
                .spec(getDefaultRequestSpec())
                .when()
                    .get()
                .then()
                    .statusCode(200);
    }

    private static RequestSpecification getDefaultRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL+"users")
                .setConfig(getDefaultConfig())
                .build();
    }
}
