package com.ti.frameworks.libraryarchitecture.testcases.web;

import com.ti.baseuiweb.BasePage;
import com.ti.frameworks.pages.LoginPage;
import com.ti.frameworks.pages.MainPage;
import com.ti.frameworks.pages.MenuPage;
import com.ti.frameworks.pages.StudentPage;

import java.util.HashMap;
import java.util.Map;

public class WebLibrary extends BasePage {

    public void login(){
        Map<String, String> userCredentials = new HashMap<>();

        userCredentials.put("username", "admin");
        userCredentials.put("userpass","G3-ySzY%");

        actualPage = getInstance(LoginPage.class)
                .loginAs(userCredentials.get("username"))
                .withPassword(userCredentials.get("userpass"))
                .andRememberMe(true)
                .submitLogin();
    }

    public void verifySchoolTitle(){
        actualPage.as(MainPage.class).verifySchoolName();
    }

    public void addAnStudent(){
        actualPage = getInstance(MenuPage.class);
        actualPage.as(MenuPage.class).goToStudents().andCreateNew();

        actualPage.as(StudentPage.class)
                .genderAs("Female")
                .withFirstName("Tyffany")
                .andLastName("Sanders")
                .withDayOfBirth("14")
                .andCurrentAddress("TestAddress 01")
                .emailAddressAs("test+98@email.com")
                .withUserName("testuser01")
                .withPassword("test789")
                .andConfirmPassword("test789")
                .schoolDetails("012");
    }

    public void verifyUserIsAdded(){
        actualPage = getInstance(StudentPage.class);
        actualPage.as(StudentPage.class).validateStudentIsAdded("Sanders");
    }

    public void deleteAnStudent(){
        actualPage = getInstance(StudentPage.class);
        actualPage.as(StudentPage.class).deleteLastRow().andConfirmWindow();
    }

}
