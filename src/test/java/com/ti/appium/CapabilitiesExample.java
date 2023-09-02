package com.ti.appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class CapabilitiesExample {
    AndroidDriver androidDriver;
    @Test
     void androidLaunchTest() throws URISyntaxException, MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName("Pixel 7 Pro API 33");
        options.setApp(System.getProperty("user.dir")+"/src/test/resources/apps/ApiDemos-debug.apk");

        androidDriver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(),options);
        Thread.sleep(1000);
        androidDriver.quit();
    }
}
