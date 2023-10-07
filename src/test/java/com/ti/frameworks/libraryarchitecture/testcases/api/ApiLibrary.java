package com.ti.frameworks.libraryarchitecture.testcases.api;

import com.ti.apibase.BaseModel;
import com.ti.apibase.ServiceFactory;
import com.ti.frameworks.models.user.UserModel;
import com.ti.frameworks.models.user.UsersModel;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.apache.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ti.apibase.RestAssuredUtils.deleteElement;
import static com.ti.apibase.RestAssuredUtils.putWithBody;
import static com.ti.apibase.ServiceFactory.setEndPoint;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ApiLibrary extends BaseModel {

    protected ResponseOptions<Response> response;
    DateTimeFormatter dtf ;
    LocalDateTime now;

    public void validateGetSingleUser(){
        actualModel = getInstance(UserModel.class);
        assertThat(actualModel.as(UserModel.class).getData().getFirstName(),equalTo("Janet"));
    }

    public void updateAnUser(){
        Map<String, String> updateUser = new LinkedHashMap<>();
        updateUser.put("name", "morpheus");
        updateUser.put("job", "zion resident");

        dtf = DateTimeFormatter.ofPattern("uuuu-MM");//"uuuu-MM-dd");
        now = LocalDateTime.now();

        ServiceFactory.setResponse(putWithBody(updateUser));
        response = ServiceFactory.getResponse();

    }

    public void validateUserIsUpdated(){
        String updatedAt = response.body().jsonPath().get("updatedAt").toString();
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        assertThat(updatedAt, containsString(dtf.format(now)));
    }

    public void deletUser(){
        ServiceFactory.setResponse(deleteElement());
        response = ServiceFactory.getResponse();
    }

    public void verifyUserIsDeletedFromUsers(){
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }

    public void getAllUsers(){
        setEndPoint("users/");
        actualModel = getInstance(UsersModel.class);
        actualModel.as(UsersModel.class).getData().forEach(System.out::println);
    }

    public void verifyAUserExistOnAllUsers(){
        assertThat(actualModel.as(UsersModel.class).getData().get(2).getFirstName(), equalTo("Emma"));
    }

}
