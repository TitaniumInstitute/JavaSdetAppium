package com.ti.restassured.lastdemo.test;

import com.ti.apibase.ServiceFactory;
import com.ti.restassured.lastdemo.model.user.User;
import com.ti.restassured.lastdemo.model.user.Users;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ti.apibase.RestAssuredUtils.putWithBody;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class UserTest extends BaseTest{
    int id;
    Map<String,String> updatedUser;

    @BeforeClass
    void classSetup(){
        updatedUser = new LinkedHashMap<>();
        updatedUser.put("name","morpheus");
        updatedUser.put("job", "zion resident");
    }

    @BeforeMethod
    void setEndPoint(){
        ServiceFactory.setEndPoint("users/");
        actualModel = getInstance(Users.class);
        id = actualModel.as(Users.class).getData().get(1).getId();
        ServiceFactory.setEndPoint("users/"+id);
        response = ServiceFactory.getResponse();
        actualModel.as(Users.class).getData().forEach(System.out::println);
    }

    @Test(priority = 1)
    void verifySingleUserIsRetrieved(){
        //setEndPoint("users/"+id);
        actualModel = getInstance(User.class);
        //System.out.println(actualModel.as(User.class).getData().getFirstName());
    }

    @Test(priority = 2)
    void verifyUserIsUpdated(){
        //setEndPoint("users/"+id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        ServiceFactory.setResponse(putWithBody(updatedUser));
        //Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> response.statusCode() == HttpStatus.SC_OK);
        response = ServiceFactory.getResponse();

        //response = putWithBody(updatedUser);
        String updatedAt = response.body().jsonPath().get("updatedAt").toString();

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        //System.out.printf("The update time is %s and actual date is: %s"
          //      ,updatedAt,dtf.format(now));
        assertThat(updatedAt, containsString(dtf.format(now)));
    }

    @Test(priority = 3)
    void verifyUserIsDeleted(){
        //setEndPoint("users/"+id);
        //ServiceFactory.setResponse(deleteItem());
        response = ServiceFactory.getResponse();
        //response = deleteItem();
        //System.out.println(response.getStatusCode());
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }

    @Test(priority = 4)
    void verifyAllUsersAreRetrieved(){
        ServiceFactory.setEndPoint("users/");
        //actualModel = getInstance(Users.class);
        String firstName = actualModel.as(Users.class).getData().get(id).getFirstName();
        assertThat(firstName, equalTo("Emma"));
    }
}
