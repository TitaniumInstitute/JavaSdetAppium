package com.ti.restassured.services;

import com.ti.apibase.RestAssuredUtilsOld;
import com.ti.restassured.lastdemo.model.user.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import com.ti.apibase.IRestResponse;
import com.ti.apibase.RestResponse;

import java.util.Map;

public class UserService extends RestAssuredUtilsOld {

    private static ResponseOptions<Response> response;

    public UserService(String baseUri, Map<String,String> headers) {
        super(baseUri, headers);
    }

    public static IRestResponse<User> getUserInformation(String id){
        response = getElements("users/" + id);
        return new RestResponse(User.class,response);
    }
}
