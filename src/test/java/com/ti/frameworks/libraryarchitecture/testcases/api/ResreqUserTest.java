package com.ti.frameworks.libraryarchitecture.testcases.api;

import com.ti.frameworks.models.user.UsersModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.ti.apibase.ServiceFactory.getResponse;
import static com.ti.apibase.ServiceFactory.setEndPoint;

public class ResreqUserTest extends BaseApiTest {
    int id;
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
        validateGetSingleUser();
    }

    @Test(priority = 2)
    void verifyUserIsUpdated() {
        updateAnUser();
        validateUserIsUpdated();
    }

    @Test(priority = 3)
    void verifyUserIsDeleted() {
        deletUser();
        verifyUserIsDeletedFromUsers();

    }

    @Test(priority = 4)
    void verifyAllUsersAreRetrieved() {
        getAllUsers();
        verifyAUserExistOnAllUsers();
    }
}
