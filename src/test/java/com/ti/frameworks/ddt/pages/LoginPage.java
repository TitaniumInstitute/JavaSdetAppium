package com.ti.frameworks.ddt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends MainPage {

    //private By txtUsername = By.id("user_login");
    @FindBy(id = "user_login")
    private WebElement txtUsername;

    @FindBy(name = "pwd")
    private WebElement txtPassword;

    @FindBy(css = "#rememberme")
    private WebElement chkRememberMe;

    @FindBy(xpath = "//input[contains(@value,'Log')]")
    private WebElement btnLogin;

    @FindBy(id = "login_error")
    private WebElement lblError;

    public LoginPage loginAs(String username) {
        txtUsername.clear();
        txtUsername.sendKeys(username);
        return this;
    }

    //Syntatic Sugar
    public LoginPage with() {
        return this;
    }


    public LoginPage withPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
        return this;
    }

    public LoginPage andRememberMe(boolean checked) {
        if (checked) {
            chkRememberMe.click();
        }
        return this;
    }

    public MainPage submitLogin() {
        btnLogin.click();
        return (MainPage) (actualPage = getInstance(MainPage.class));
    }

    private String getError(){
        return lblError.getText();
    }

    public void verifyErrorText(String error){
        Assert.assertEquals(getError(), error);
    }

}
