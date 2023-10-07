package com.ti.frameworks.libraryarchitecture.testcases.web;

import org.testng.annotations.Test;

public class LoginTest extends BaseWebTest{
    @Test
    void loginWithRightCredentials() {
        //Assert
        verifySchoolTitle();
    }
}
