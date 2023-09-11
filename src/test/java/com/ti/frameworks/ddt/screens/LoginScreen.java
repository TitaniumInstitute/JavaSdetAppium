package com.ti.frameworks.ddt.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.baseiumobile.BaseScreen;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.CacheLookup;

public class LoginScreen extends BaseScreen {

    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(accessibility = "test-Usuario")
    @CacheLookup
    private RemoteWebElement txtUserName;

    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(accessibility = "test-Contraseña")
    @CacheLookup
    private RemoteWebElement txtPassword;

    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    @CacheLookup
    private RemoteWebElement btnLogin;

    public LoginScreen loginAs(String username){
        txtUserName.clear();
        txtUserName.sendKeys(username);
        return this;
    }

    public LoginScreen withPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
        return this;
    }

    public HomeScreen submitLogin(){
        btnLogin.click();
        return (HomeScreen) (actualScreen = getInstance(HomeScreen.class));
    }
}
