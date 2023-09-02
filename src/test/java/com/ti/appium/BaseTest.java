package com.ti.appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class BaseTest {

    //Ejecutar emulador desde linea de comandos
    // emulator -avd Pixel_7_Pro_API_33
    // emulator -avd nightwatch-android-11
    public AndroidDriver androidDriver;
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
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAppWaitActivity("com.androidsample.generalstore.SplashActivity");//"com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity");
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName("Pixel 7 Pro API 33");
        //options.setApp(System.getProperty("user.dir")+"/src/test/resources/apps/ApiDemos-debug.apk");
        options.setApp(System.getProperty("user.dir")+"/src/test/resources/apps/General-Store.apk");

        androidDriver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(),options);
    }

    @AfterTest
    public void turnDown(){
        androidDriver.quit();
        service.stop();
    }
}
