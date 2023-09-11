package com.ti.appium.ios;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class IOSBaseTest {

    //Ejecutar emulador desde linea de comandos
    // emulator -avd Pixel_7_Pro_API_33
    // emulator -avd nightwatch-android-11
    public IOSDriver iosDriver;
    AppiumDriverLocalService service;

    @BeforeTest
    public void setUp() throws URISyntaxException, MalformedURLException {
        service = new AppiumServiceBuilder()
                .withArgument(()->"--use-plugins","element-wait@2.0.3,gestures@3.0.0")
                .withEnvironment(System.getenv())
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();

        service.start();
        XCUITestOptions options = new XCUITestOptions();
        options.setAutomationName(AutomationName.IOS_XCUI_TEST);
        options.setDeviceName("iPhone 14");
        //options.setApp(System.getProperty("user.dir")+"/src/test/resources/apps/ApiDemos-debug.apk");
        //options.setApp(System.getProperty("user.dir")+"/src/test/resources/apps/UIKitCatalog.app");

        iosDriver = new IOSDriver(new URI("http://127.0.0.1:4723/").toURL(),options);
    }

    @AfterTest
    public void turnDown(){
        iosDriver.removeApp("com.apple.mobileslideshow");
        iosDriver.quit();
        service.stop();
    }
}
