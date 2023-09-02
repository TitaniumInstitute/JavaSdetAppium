package com.ti.restassured.services;

import com.ti.restassured.lastdemo.model.user.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.apibase.IRestResponse;
import org.apibase.RestAssuredUtils;
import org.apibase.RestResponse;

import java.util.Map;

public class UserService extends RestAssuredUtils {

    private static ResponseOptions<Response> response;

    public UserService(String baseUri, Map<String,String> headers) {
        super(baseUri, headers);
    }

    public static IRestResponse<User> getUserInformation(String id){
        response = getItems("users/" + id);
        return new RestResponse(User.class,response);
    }
}
