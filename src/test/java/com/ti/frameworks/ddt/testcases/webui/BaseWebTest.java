package com.ti.frameworks.ddt.testcases.webui;

import com.ti.baseuiweb.BasePage;
import com.ti.baseuiweb.BrowserType;
import com.ti.baseuiweb.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.sql.SQLException;

import static com.ti.frameworks.config.Constants.BASE_URL;
import static com.ti.frameworks.ddt.dataproviders.SQLArrayData.closeConnection;

public class BaseWebTest extends BasePage {
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser){
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));
        DriverFactory.getInstance().getDriver().navigate().to(BASE_URL);
    }

    @AfterClass
    public void turnDown() throws SQLException {
        DriverFactory.getInstance().removeDriver();
        closeConnection();
    }
}
