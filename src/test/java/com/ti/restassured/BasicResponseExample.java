package com.ti.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BasicResponseExample {
    private static final String BaseURI = "https://api.github.com";
    Response response;

    @BeforeTest
    void setUp(){
        response = RestAssured.get(BaseURI);
    }

    @Test
    void ConvenienceMethods(){
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
    }

    //Headers
    @Test
    void verifyGenericHeader(){
        assertThat(response.getHeader("server"), equalTo("GitHub.com"));
        assertThat(response.getHeader("x-ratelimit-limit"), equalTo("60"));
        assertThat(Integer.parseInt(response.getHeader("x-ratelimit-limit")), equalTo(60));
    }

    @Test
    void verifyGetHeaders(){
        Headers headers = response.getHeaders();
        String headerOne = headers.getValue("X-GitHub-Media-Type");
        System.out.println("The value of header 1 is " + headerOne);
        int headerSize = headers.size();
        System.out.println("The size of the header is " + headerSize);
        List<Header> headersList = headers.asList();
        headersList.forEach(System.out::println);
        boolean headerIsPresent = headers.hasHeaderWithName("header2");
        Assert.assertTrue(!headerIsPresent, "Header is not pressent");
    }

    @Test
    void verifyResponseTime(){
        System.out.println(response.time());
        System.out.println(response.getTime());
        System.out.println(response.timeIn(TimeUnit.MINUTES));
        System.out.println(response.getTimeIn(TimeUnit.MINUTES));
    }

    @Test
    void basicValidateExample(){
        RestAssured.get(BaseURI)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                //.header("x-ratelimit-limit",60); -- need to use the 3-arg overloaded method
                .header("x-ratelimit-limit","60");
    }

    @Test
    void basicValidateExampleSugar(){
        RestAssured.get(BaseURI)
                .then()
                .assertThat()
                    .statusCode(200)
                .and()
                    .contentType(ContentType.JSON)
                .and().assertThat()
                    .header("x-ratelimit-limit","60");
    }

    /*@Test
    void a(){

    }

    @Test
    void a(){

    }

    @Test
    void a(){

    }

    @Test
    void a(){

    }
     */

}
