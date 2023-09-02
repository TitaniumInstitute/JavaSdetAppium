package com.ti.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

//@Listeners(Reports.class)
public class BaseTestClass {
    protected WebDriver driver;
    protected String demoSiteUrl = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
    String username = "admin";
    String password = "G3-ySzY%";

    @BeforeSuite
    public void setup() {
        //WebDriverManager.edgedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //WebDriverManager.chromedriver().setup();
        //driver = new EdgeDriver();
        //new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void turnDown() {
        driver.quit();
    }

    void wait(int seg) {
        try {
            Thread.sleep(seg * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }
}
