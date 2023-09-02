package com.ti.restassured.commands;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.hamcrest.number.OrderingComparison;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

    //Hamcrest example

    @Test
    void simpleHamcrestMatchers(){
        RestAssured.get(BaseURI)
                .then()
                .statusCode(200)
                .statusCode(equalTo(200))
                .statusCode(lessThan(300))
                .statusCode(anyOf(equalTo(200), equalTo(202)))
                .header("cache-control", containsString("max-age=60"))
                .time(lessThan(2L),TimeUnit.SECONDS)
                .header("etag", not(emptyString()));
    }

    @Test
    void complexHamcrestMatchers(){
        RestAssured.get(BaseURI)
                .then()
                .header("x-ratelimit-limit", Integer::parseInt, equalTo(60))
                .header("date", date-> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now()))
                .header("x-ratelimit-limit",
                        res -> greaterThan(res.header("x-ratelimit-remaining")));
    }

    @Test
    void usingMapsToTestHeaders(){
        Map<String, String> expectedHeaders = Map.of("content-encoding","gzip",
                                                    "access-control-allow-origin", "*");

        RestAssured.get(BaseURI)
                .then()
                .headers(
                        "content-encoding", "gzip",
                        "access-control-allow-origin", "*",
                        "cache-control", containsString("public"))
                .headers(expectedHeaders);
    }

    /*@Test
    void a(){

    }
     */

}
