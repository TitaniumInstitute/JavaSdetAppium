package com.ti.frameworks.libraryarchitecture.testcases.mobile;

import com.ti.appium.patronesdediseño.pf.screens.HomeScreen;
import com.ti.appium.patronesdediseño.pf.screens.LoginScreen;
import com.ti.baseiumobile.BaseScreen;

import java.util.HashMap;
import java.util.Map;

public class MobileLibrary extends BaseScreen {
    public void login(){
        Map<String, String> userCredentials = new HashMap<>();

        userCredentials.put("username", "standard_user");
        userCredentials.put("password", "secret_sauce");

        actualScreen = getInstance(LoginScreen.class);
        actualScreen.as(LoginScreen.class)
                .loginAs(userCredentials.get("username"))
                .withPassword(userCredentials.get("password"))
                .submitLogin();
    }

    public void verifyProductPageIsDisplayed(){
        actualScreen = getInstance(HomeScreen.class);
        actualScreen.as(HomeScreen.class).verifyProductsText();
    }
}
