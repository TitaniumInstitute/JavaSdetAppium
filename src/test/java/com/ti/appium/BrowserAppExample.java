package com.ti.appium;

import com.ti.appium.android.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class BrowserAppExample extends AndroidBaseTest {
    //public AndroidDriver androidDriver;

    @BeforeClass
    void loginCookies(){
        androidDriver.get("https://www.saucedemo.com/");
        androidDriver.manage().deleteAllCookies();
        ((JavascriptExecutor)androidDriver).executeScript("localStorage.clear();");
        Cookie loginCookie = new Cookie("session-username", "standard_user");
        androidDriver.manage().addCookie(loginCookie);
        ((JavascriptExecutor)androidDriver).executeScript("localStorage.setItem('cart-contents','[4,1]')");
        androidDriver.navigate().refresh();
    }

    @Test
    void validateShippingCartItemsAtomicTest(){

        androidDriver.navigate().to("https://www.saucedemo.com/cart.html");
        WebElement spnShoppingCart = new WebDriverWait(androidDriver, Duration.ofSeconds(10))
                .until(androidDriver -> androidDriver.findElement(By.className("shopping_cart_badge")));
        Assert.assertEquals(Integer.valueOf(spnShoppingCart.getText()),2);
    }

    @Test
    void validateUserCanCheckOutAtomicTest(){
        androidDriver.navigate().to("https://www.saucedemo.com/checkout-step-one.html");
        new WebDriverWait(androidDriver, Duration.ofSeconds(8))
                .until(androidDriver -> androidDriver.findElement(By.id("first-name"))).sendKeys("Gilberto");
        androidDriver.findElement(By.id("last-name")).sendKeys("Sanchez");
        androidDriver.findElement(By.id("postal-code")).sendKeys("20200");
        androidDriver.findElement(By.id("continue")).click();

        WebElement dvTotal = androidDriver.findElement(By.xpath("(//div[contains(text(),'Total')])[2]"));
        Assert.assertTrue(dvTotal.getText().contains("49.66"));
    }
}
