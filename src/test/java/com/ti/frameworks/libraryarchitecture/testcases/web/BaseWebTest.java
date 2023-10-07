package com.ti.frameworks.libraryarchitecture.testcases.web;

import com.ti.baseuiweb.BrowserType;
import com.ti.baseuiweb.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static com.ti.frameworks.config.Constants.BASE_URL;

public class BaseWebTest extends WebLibrary {
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser){
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));
        DriverFactory.getInstance().getDriver().navigate().to(BASE_URL);
        login();
    }

    @AfterClass
    public void turnDown(){
        DriverFactory.getInstance().removeDriver();
    }
}
