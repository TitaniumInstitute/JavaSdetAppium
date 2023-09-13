package com.ti.restassured.lastdemo.test;

import com.ti.apibase.BaseModel;
import com.ti.apibase.IRestResponse;
import com.ti.apibase.RestAssuredUtils;
import com.ti.restassured.lastdemo.model.user.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.testng.annotations.BeforeTest;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseTest extends BaseModel {
    protected Map<String, String> expectedHeaders = Map.of("Content-Type","application/json");
    Map<String,String> newUser = new LinkedHashMap<>();
    protected ResponseOptions<Response> response;

    protected IRestResponse<User> userResponse;

    @BeforeTest
    public void setup(){
        newUser.put("username", "eve.holt@reqres.in");
        newUser.put("email", "eve.holt@reqres.in");
        newUser.put("password", "cityslicka");
        new RestAssuredUtils(BASE_URL,expectedHeaders);
    }
}
