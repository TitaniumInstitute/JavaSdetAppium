package com.ti.restassured.services;

import com.ti.restassured.dao.UserGorest;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.apibase.IRestResponse;
import org.apibase.RestAssuredUtils;
import org.apibase.RestResponse;

import java.util.Map;

public class UserResponse extends RestAssuredUtils {

    private static ResponseOptions<Response> response;

    public UserResponse(String baseUri, Map<String, String> headers) {
        super(baseUri, headers);
    }

    public static IRestResponse<UserGorest> getUsers(){
        response = getItems("users");
        return new RestResponse(UserGorest.class, response);
    }

    public static IRestResponse<UserGorest> postUser(Map<String,String> postBody){
        response = postWithBody("users/",postBody);
        return new RestResponse(UserGorest.class, response);
    }

    public static IRestResponse<UserGorest> putUser(String user, Map<String,String> putBody){
        response = putWithBody("users/"+ user,putBody);
        return new RestResponse(UserGorest.class, response);
    }

    public static IRestResponse<UserGorest> deleteUser(String user){
        response = deleteItem("users/"+user);
        return new RestResponse(UserGorest.class, response);
    }
}
