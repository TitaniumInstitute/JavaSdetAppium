package com.ti.restassured.testcases;

import com.ti.restassured.dao.UserGorest;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.List;

import static com.ti.apibase.RestAssuredUtilsOld.*;

public class UsersExampleTest extends BaseTestClass {

    /*@AfterMethod
    void getListOfUsers(){
        response = postWithBody("users", postUserData);
        System.out.printf("The user created is %s", response.getBody().prettyPrint());
    }*/
    @Test(priority = 1)
    void postExample(){
        response = postWithBody("users", postUserData);
        System.out.printf("The user created is %s", response.getBody().prettyPrint());
    }

    @Test(priority = 2)
    void getExample(){
        List<UserGorest> users = RestAssured.get("https://gorest.co.in/public/v2/users")
                .jsonPath().getList(".", UserGorest.class);

        System.out.println(users.size());
        users.forEach(System.out::println);
    }

    @Test(priority = 3)
    void putExample(){
        response = putWithBody("users/" + userGorests.get(0).getId(), putUserData);
        response = getElements("users");
        userGorests = response.getBody().jsonPath().getList(".", UserGorest.class);
    }

    @Test(priority = 4)
    void deleteExample(){
        System.out.printf("The user deleted is %s", response.getBody().prettyPrint());
        response = deleteElement("users/" + userGorests.get(0).getId());
    }
}
