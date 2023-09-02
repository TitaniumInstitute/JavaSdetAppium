package com.ti.restassured.testcases;

import com.ti.restassured.services.UserResponse;
import com.ti.restassured.dao.UserGorest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class UsersExample extends BaseTestClass {

    @AfterMethod
    void getListOfUsers(){
        try {
            userResponse = UserResponse.getUsers();
            users = (List<UserGorest>) userResponse.getBody();
            System.out.printf("User name is: %s \nUser id is: %d \nUser email is: %s",
                    users.get(0).getName(), users.get(0).getId(),  users.get(0).getEmail());
        }catch (Exception e){

        }
    }
    @Test(priority = 1)
    void postExample(){
        UserResponse.postUser(postUserData);
        //userResponse = UserResponse.getUsers();
        //System.out.printf("The user created is %s", users.get(0).getId());
        /*response = postWithBody("users", postUser) ;
        System.out.println("The user created is " + response.getBody().prettyPrint());*/
    }

    @Test(priority = 2)
    void getExample(){
        userResponse = UserResponse.getUsers();
        //users = (List<UserGorest>) userResponse.getBody();
        //System.out.printf("User id is: %s", users.get(0).getId());
        /*response = getItems("users");
        users = response.getBody().jsonPath().getList(".",UserGorest.class);
        System.out.printf("User id is: %s", users.get(0).getId());*/
    }

    @Test(priority = 3)
    void putExample(){
        UserResponse.putUser(String.valueOf(users.get(0).getId()), putUserData);
        userResponse = UserResponse.getUsers();
        users = (List<UserGorest>) userResponse.getBody();

        System.out.printf("\nAfter user data changed status code is: %d",userResponse.getStatusCode());
        System.out.printf("\nUser id is: %s", users.get(0).getId());
       /* response = putWithBody("users/"+users.get(0).getId(),putUser);

        response = getItems("users");
        users = response.getBody().jsonPath().getList(".",UserGorest.class);

        System.out.printf("\nAfter user data changed status code is: %d",response.getStatusCode());
        System.out.printf("\nAfter user data changed the username is: %s",
                users.get(0).getName());*/
    }

    @Test(priority = 4)
    void deleteExample(){
        userResponse = UserResponse.deleteUser(String.valueOf(users.get(0).getId()));
        //response = delete("users/"+users.get(0).getId());
        System.out.printf("\nAfter user data changed status code is: %d",userResponse.getStatusCode());

    }
}
