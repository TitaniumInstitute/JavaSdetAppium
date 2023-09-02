package com.ti.restassured.testcases;

import com.ti.restassured.dao.UserGorest;
import io.restassured.RestAssured;
import org.apibase.IRestResponse;
import org.apibase.RestAssuredUtils;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apibase.ConfigFactory.getDefaultConfig;

public class BaseTestClass {
    private static final String BASE_URL = "https://gorest.co.in/public/v2/";
    private static final String TOKEN = "7128aed3be37c995335454a1f30b5c5e641096f2310fd855a5bc912c947cddb7";
    protected Map<String, String> expectedHeaders = Map.of("Authorization","Bearer " + TOKEN,
            "Content-Type","application/json",
            "Accept","*/*");

    protected Map<String,String> postUserData = Map.of("name","Gilberto Sanchez",
            "gender","male",
            "email","gilberto.sanchez@gmail.com",
            "status","active");

    protected Map<String,String> putUserData = Map.of("name","Gil Sanchez",
            "gender","male",
            "email","gsanchez@gmail.com",
            "status","active");

    protected List<UserGorest> users = new ArrayList<>();

    protected IRestResponse<UserGorest> userResponse;
    //private ResponseOptions<Response> response;

    @BeforeTest
    public void setup(){
        RestAssuredUtils restAssuredUtils = new RestAssuredUtils(BASE_URL, expectedHeaders);
        RestAssured.config = getDefaultConfig();
    }
}
