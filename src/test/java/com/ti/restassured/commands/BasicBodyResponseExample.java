package com.ti.restassured.commands;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class BasicBodyResponseExample {

    private static final String BASE_URL = "https://api.github.com/";
    private static final String REQRES_BASE_URL = "https://reqres.in/api/";
    Response response;

    @BeforeTest
    void setUp(){
        response = RestAssured.get(BASE_URL + "rate_limit");
    }
    @Test
    void jsonPathReturnsMap(){
        ResponseBody<?> body = response.body();
        JsonPath jPath = body.jsonPath();

        JsonPath jPath2 = body.jsonPath();

        Map<String, String> fullJson = jPath2.get();
        Map<String, String> subMap = jPath2.get("resources");
        Map<String, String> subMap2 = jPath2.get("resources.core");

        int coreLimit = jPath.get("resources.core.limit");
        int remaining = jPath.get("resources.graphql.remaining");

        System.out.println(fullJson);
        System.out.println(subMap);
        System.out.println(subMap2);
        System.out.println(coreLimit);
        System.out.println(remaining);

        Assert.assertEquals(coreLimit, 60);
        Assert.assertEquals(remaining, 0);
    }

    @Test
    public void castingFailure(){
        JsonPath jsonPath = RestAssured.get(BASE_URL).body().jsonPath();

        Map<String,String> isNull = jsonPath.get("incorrect.path");
        //int aMap = jsonPath.get("resources.core");
        //String value = jsonPath.get("resources.core.limit");
    }

   @Test
    void matcherExample(){
        RestAssured.get(BASE_URL)
                .then()
                .body("current_user_url", equalTo(BASE_URL + "user"))
                .body(containsString("feeds_url"))
                .body(containsString("feeds_url"), containsString("current_user_url"));
    }


    @Test
    void complexBodyExample(){
        RestAssured.get(BASE_URL + "users/mojombo")
                .then()
                .body("url", res -> containsString("mojombo"))
                .body("url", res -> containsString(res.body().jsonPath().get("login")));
    }

    @DataProvider
    Object[][] names(){
        return new Object[][]{
                {"mojombo"},
                {"defunkt"},
                {"pjhyett"},
                {"wycats"}
        };
    }

    @Test(dataProvider = "names")
    void complexBodyWithDataProvider(String name){
        RestAssured.get(BASE_URL + "users/" + name)
                .then()
                .body("url", res -> containsString(res.body().jsonPath().get("login")));
    }

    @Test
    void nestedBodyValidation(){
        /*RestAssured.get(BASE_URL + "rate_limit")
                .then()
                .body("resources.core.limit", equalTo(60))
                .body("resources.core.remaining", lessThanOrEqualTo(60))
                .body("resources.core.reset", notNullValue());*/

        RestAssured.get(BASE_URL + "rate_limit")
                .then()
                .rootPath("resources.core")
                    .body("limit", equalTo(60))
                    .body("remaining", lessThanOrEqualTo(60))
                    .body("reset", notNullValue())
                .rootPath("resources.search")
                    .body("limit", equalTo(10))
                    .body("remaining", lessThanOrEqualTo(10))
                .noRootPath()
                    .body("resources.graphql.limit", is(0));
    }

    @Test
    void repeatingItems(){
        RestAssured.get(REQRES_BASE_URL + "users?page=1")
                .then()
                //.body("data.id", equalTo(1));
                .body("data.id[0]", equalTo(1))
                .body("data.id[1]", equalTo(2))
                .body("data.first_name[2]", equalTo("Emma"))
                .body("data.first_name[3]", equalTo("Eve"))
                //.body("data.first_name", containsInAnyOrder("Eve", "Emma"));
                .body("data.first_name", hasItem("Emma"))
                .body("data.first_name", hasItem("Eve"))
                .body("data.first_name", hasItem(endsWith("ma")));
    }

    @Test
    void parseAndSyntacticSugar(){
        RestAssured.get(BASE_URL)
                .then()
                .using()
                    .defaultParser(Parser.JSON)
                .body("current_user_url", equalTo(BASE_URL + "user"));
    }
}
