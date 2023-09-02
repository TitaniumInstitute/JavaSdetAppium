package com.ti.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotExample extends BaseTestClass {
    String destination;
    String dateOption = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    public void getScreenshot(WebElement element, String screenShotName) {

        destination = createFolder(System.getProperty("user.dir") + "/results/screenshots") + "/" + screenShotName+dateOption+".png";
        try {
            FileHandler.copy((element == null?
                            ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE)
                            :element.getScreenshotAs(OutputType.FILE)),
                    new File(destination));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @BeforeTest
    void navigateToWebSite(){
        driver.navigate().to(demoSiteUrl);
    }

    @BeforeMethod
    void checkDemoSiteHome(){
        driver.navigate().to(demoSiteUrl);
        Assert.assertTrue(driver.getTitle().contains("Log In"));
    }

    @AfterMethod
    void navigateBack(){
        driver.navigate().back();
    }

    @Test(priority = 2)
    void testVerifyRegisterPage() throws IOException {
        WebElement lnkRegister = driver.findElement(By.linkText("Register"));
        getScreenshot(lnkRegister,"Selenium4");
        lnkRegister.click();
        Assert.assertTrue(driver.getTitle().contains("Registration Form"));
    }

    @Test(priority = 1)
    void testVerifyLostPassPage() {
        try {
            driver.findElement(By.linkText("Lost your passw")).click();
            Assert.assertTrue(driver.getTitle().contains("Lost Password"));
        }catch (WebDriverException we){
            getScreenshot(null, "WebDriverException");
            Assert.fail(we.getMessage());
        }
    }

    private static String createFolder(String folderName) {
        File theDir = new File(folderName);
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                new Exception("Class SeleniumUtils | Method createFolder | Exception: " + se.getMessage());
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
        return theDir.toString();
    }
}
