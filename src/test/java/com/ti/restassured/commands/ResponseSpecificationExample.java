package com.ti.restassured.commands;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResponseSpecificationExample {
    private static final String BASE_URL = "https://gorest.co.in/public/v2/";

    @BeforeClass
    void setUp(){
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectContentType("text/html; charset=utf-8")
                .build();
    }

    @AfterClass
    void cleanUp(){
        RestAssured.responseSpecification = null;
    }

    ResponseSpecification commonResponSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectContentType("text/html; charset=utf-8")
            .build();

    @Test
    void testWithSpecOne(){
        RestAssured.get(BASE_URL + "non/exist/url")
                        .then()
                        .spec(commonResponSpec)
                /*.then()
                .statusCode(404)
                .contentType("text/html; charset=utf-8")*/;
    }

    @Test
    void testWithSpecTwo(){
        RestAssured.get(BASE_URL + "non/exist/url")
                        .then()
                        .spec(commonResponSpec)
                /*.then()
                .statusCode(404)
                .contentType("text/html; charset=utf-8")*/;
    }
}
