package com.ti.appium.patronesdediseño;

import com.ti.appium.BaseTest;
import io.appium.java_client.AppiumDriver;
import org.baseiumobile.MobileDriverFactory;
import org.testng.annotations.Test;

public class MobileDriverFactoryExample extends BaseTest {
    AppiumDriver driver;
    @Test
    void exampleTest() throws InterruptedException {
        driver = MobileDriverFactory.getInstance().getMobileDriver();
        Thread.sleep(1500);
    }
}
