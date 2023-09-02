package com.ti.restassured.commands;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PeekPrintExample {
    private static final String BaseURI = "https://api.github.com";

    @Test
    void peekTest(){
        RestAssured.get(BaseURI)
                .peek();
    }

    @Test
    void prettyPeekTest(){
        RestAssured.get(BaseURI)
                .prettyPeek();
    }

    @Test
    void printTest(){
        RestAssured.get(BaseURI)
                .print();
    }

    @Test
    void prettyPrintTest(){
        RestAssured.get(BaseURI)
                .prettyPrint();
    }
}
