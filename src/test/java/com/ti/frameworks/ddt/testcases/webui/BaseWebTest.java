package com.ti.frameworks.ddt.testcases.webui;

import org.baseuiweb.BasePage;
import org.baseuiweb.BrowserType;
import org.baseuiweb.DriverFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.sql.SQLException;

import static com.ti.frameworks.ddt.config.Constants.BASE_URL;
import static com.ti.frameworks.ddt.dataproviders.SQLArrayData.closeConnection;

public class BaseWebTest extends BasePage {
    @BeforeTest
    @Parameters("browser")
    void setup(String browser){
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));
        DriverFactory.getInstance().getDriver().navigate().to(BASE_URL);
    }

    @AfterTest
    void turnDown() throws SQLException {
        DriverFactory.getInstance().removeDriver();
        closeConnection();
    }
}
