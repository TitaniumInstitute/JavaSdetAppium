package com.ti.restassured.services;

import com.ti.apibase.IRestResponse;
import com.ti.apibase.RestAssuredUtilsOld;
import com.ti.apibase.RestResponse;
import com.ti.restassured.dao.UserGorest;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.Map;

public class UserResponse extends RestAssuredUtilsOld {

    private static ResponseOptions<Response> response;

    public UserResponse(String baseUri, Map<String, String> headers) {
        super(baseUri, headers);
    }

    public static IRestResponse<UserGorest> getUsers(){
        response = getElements("users");
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
        response = deleteElement("users/"+user);
        return new RestResponse(UserGorest.class, response);
    }
}
