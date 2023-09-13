package com.ti.frameworks.ddt.testcases.api;

import com.ti.apibase.ServiceFactory;
import com.ti.frameworks.models.user.UserModel;
import com.ti.frameworks.models.user.UsersModel;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ti.apibase.RestAssuredUtils.deleteElement;
import static com.ti.apibase.RestAssuredUtils.putWithBody;
import static com.ti.apibase.ServiceFactory.getResponse;
import static com.ti.apibase.ServiceFactory.setEndPoint;
import static com.ti.frameworks.ddt.dataproviders.JSONArrayData.getJsonTableArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ResreqUserTestJSON extends BaseApiTest {
    Map<String, String> updateUser;
    int id;

    /*@BeforeClass
    void classSetup() {
        updateUser = new LinkedHashMap<>();
        updateUser.put("name", "morpheus");
        updateUser.put("job", "zion resident");
    }*/
    @DataProvider
    public Object[][] getJSONProviderData(){
        return getJsonTableArray("ApiUser.json","update user");
    }

    @BeforeMethod
    @Parameters("endpoint")
    void setupEndPoint(String endpoint) {
        setEndPoint(endpoint);
        actualModel = getInstance(UsersModel.class);
        id = actualModel.as(UsersModel.class).getData().get(1).getId();
        setEndPoint(endpoint + id);
        response = getResponse();
        actualModel.as(UsersModel.class).getData().forEach(System.out::println);
    }

    @Test(priority = 1)
    void verifySingleUserIsRetrieved() {

        actualModel = getInstance(UserModel.class);
        String userFirstName = actualModel.as(UserModel.class).getData().getFirstName();
        System.out.println(userFirstName);
        assertThat(userFirstName, equalTo("Janet"));
    }

    @Test(dataProvider = "getJSONProviderData",priority = 2)
    void verifyUserIsUpdated(LinkedHashMap<String, String> linkedHashData) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM");//"uuuu-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        ServiceFactory.setResponse(putWithBody(linkedHashData));
        response = ServiceFactory.getResponse();
        String updatedAt = response.body().jsonPath().get("updatedAt").toString();

        System.out.println(updatedAt);
        assertThat(response.statusCode(), equalTo(HttpStatus.SC_OK));

        assertThat(updatedAt, containsString(dtf.format(now)));
    }

    @Test(priority = 3)
    void verifyUserIsDeleted() {
        ServiceFactory.setResponse(deleteElement());
        response = ServiceFactory.getResponse();

        assertThat(response.statusCode(), equalTo(HttpStatus.SC_NO_CONTENT));

    }

    @Test(priority = 4)
    void verifyAllUsersAreRetrieved() {
        setEndPoint("users/");
        actualModel = getInstance(UsersModel.class);
        actualModel.as(UsersModel.class).getData().forEach(System.out::println);
        System.out.println(actualModel.as(UsersModel.class).getData().size());
        assertThat(actualModel.as(UsersModel.class).getData().get(2).getFirstName(), equalTo("Emma"));
    }
}
