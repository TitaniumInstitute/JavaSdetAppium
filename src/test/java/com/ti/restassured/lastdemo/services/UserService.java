package com.ti.restassured.lastdemo.services;

import com.ti.apibase.IRestResponse;
import com.ti.apibase.RestAssuredUtils;
import com.ti.apibase.RestResponse;
import com.ti.apibase.ServiceFactory;
import com.ti.restassured.lastdemo.model.user.User;

import java.util.Map;

public class UserService extends RestAssuredUtils {

    public UserService(String baseUri, Map<String, String> headers) {
        super(baseUri, headers);
    }

    public static IRestResponse<User> getUserInformation(){
       // ServiceFactory.setResponse(getItems());
        //response = getItems("users/" + id);
        return new RestResponse(User.class,ServiceFactory.getResponse());
    }
}
