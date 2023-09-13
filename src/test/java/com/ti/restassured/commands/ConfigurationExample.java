package com.ti.restassured.commands;

import com.ti.restassured.dao.AnotherUser;
import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.listener.ResponseValidationFailureListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.config.RedirectConfig.redirectConfig;
import static com.ti.apibase.ConfigFactory.getDefaultConfig;
import static org.hamcrest.CoreMatchers.equalTo;

public class ConfigurationExample {
    public static final String BASE_URL = "https://api.github.com/";

    @Test(enabled = false)
    void maxRedirects(){
        RestAssured.config = RestAssured.config()
                            .redirect(redirectConfig().followRedirects(true).maxRedirects(1));
                        //.redirect(redirectConfig().followRedirects(false));

        RestAssured.get(BASE_URL + "repos/twitter/bootstrap")
                .then()
                .statusCode(200);
    }

    @Test(enabled = false)
    void failureConfig(){

        ResponseValidationFailureListener failListener = (reqSpec, resSpec, response)->
                System.out.printf("API failure: response status was %s and the body contains: %s",
                        response.getStatusCode(),response.body().asPrettyString());

        RestAssured.config = RestAssured.config()
                        .failureConfig(FailureConfig.failureConfig().failureListeners(failListener));

        RestAssured.get(BASE_URL + "userss/mojombo")
                .then()
                .body("some.path", equalTo("some"));
    }

    @BeforeTest
    void setup(){
        RestAssured.config = getDefaultConfig();
    }

    @Test
    void cleanTestWithHiddingConfig(){
        AnotherUser user = RestAssured.get(BASE_URL + "users/macournoyer")
                .as(AnotherUser.class);
        Assert.assertEquals(user.getLogin(), "macournoyer");
    }
}
