package com.ti.restassured.lastdemo.test;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.ti.apibase.RestAssuredUtils.postWithBody;
import static com.ti.apibase.ServiceFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginAndRegisterTest extends BaseTest{

    @Test
    void verifyNewUserIsCreated(){
        setEndPoint("register/");
        setResponse(postWithBody(newUser));
        response = getResponse();
        //response = postWithBody(newUser);
        String token = response.body().jsonPath().get("token").toString();
        assertThat(token, equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void verifyLoginSuccess(){
        setEndPoint("login/");
        setResponse(postWithBody(newUser));
        response = getResponse();
        //response = postWithBody(newUser);
        String token = response.body().jsonPath().get("token").toString();
        assertThat(token, equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void verifyLogoutSuccess(){
        setEndPoint("logout/");
        //setResponse(postItem());
        response = getResponse();
        //response = postItem();
        System.out.println(response.getStatusCode());
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
    }
}
