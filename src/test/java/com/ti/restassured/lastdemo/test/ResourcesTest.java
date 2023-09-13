package com.ti.restassured.lastdemo.test;

import com.ti.restassured.lastdemo.model.resource.Resource;
import com.ti.restassured.lastdemo.model.resource.Resources;
import org.apache.http.HttpStatus;
import com.ti.apibase.ServiceFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.ti.apibase.RestAssuredUtils.deleteElement;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ResourcesTest extends BaseTest {
    int id;

    @BeforeMethod
    void setEndPoint(){
        ServiceFactory.setEndPoint("unknown/");
        actualModel = getInstance(Resources.class);
        id = actualModel.as(Resources.class).getData().get(1).getId();
        ServiceFactory.setEndPoint("unknown/"+id);
        response = ServiceFactory.getResponse();
        actualModel.as(Resources.class).getData().forEach(System.out::println);
    }

    @Test(priority = 1)
    void verifySingleResourceIsRetrieved(){
        actualModel = getInstance(Resource.class);
    }

    @Test(priority = 2)
    void verifyResourceIsUpdated(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        //ServiceFactory.setResponse(putItem());

        response = ServiceFactory.getResponse();
        String updatedAt = response.body().jsonPath().get("updatedAt").toString();

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));
        //System.out.printf("The update time is %s and actual date is: %s"
        //      ,updatedAt,dtf.format(now));
        assertThat(updatedAt, containsString(dtf.format(now)));
    }

    @Test(priority = 3)
    void verifyResourceIsDeleted(){
        ServiceFactory.setResponse(deleteElement());
        response = ServiceFactory.getResponse();
        //response = deleteItem();
        //System.out.println(response.getStatusCode());
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_NO_CONTENT));

    }

    @Test(priority = 4)
    void verifyAllResourcesAreRetrieved(){
        ServiceFactory.setEndPoint("unknown/");
        String firstName = actualModel.as(Resources.class).getData().get(id).getName();
        assertThat(firstName, equalTo("true red"));
    }
}
