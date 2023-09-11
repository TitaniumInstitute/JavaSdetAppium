package com.ti.appium;

import com.ti.appium.patronesdediseño.pf.screens.LoginScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.baseiumobile.BaseScreen;
import org.baseiumobile.DeviceOSType;
import org.baseiumobile.MobileDriverFactory;
import org.baseiumobile.model.DriverOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.Map;

public class BaseTest extends BaseScreen {
    AppiumDriverLocalService service;
    Map<String, String> userCredentials = new HashMap<>();
    @BeforeTest
    @Parameters({"deviceOsType","appName","ipAddress","deviceName","port","udid"})
    public void setup(String deviceOsType, String appName, String ipAddress, String deviceName, @Optional("port")String port, @Optional("udid")String udid){
    //public void setup(String ... deviceSetup){
        DriverOptions options = new DriverOptions();
        options.setAppName(appName)
                .setDeviceName(deviceName)
                .setAppActivity("com.swaglabsmobileapp.MainActivity")
                .setPort(port)
                .setUdid(udid);

        service = new AppiumServiceBuilder()
                .withArgument(() -> "--use-plugins", "element-wait@2.0.3,gestures@3.0.0")
                .withIPAddress(ipAddress)
                .usingAnyFreePort()
                .build();
        service.start();


        MobileDriverFactory.getInstance().setMobileDriver(DeviceOSType.valueOf(deviceOsType),options);

        //Arrange
        userCredentials.put("username", "standard_user");
        userCredentials.put("password", "secret_sauce");

        actualScreen = getInstance(LoginScreen.class);
        actualScreen.as(LoginScreen.class)
                .loginAs(userCredentials.get("username"))
                .withPassword(userCredentials.get("password"))
                .submitLogin();
    }

    @AfterTest
    public void turndown(){
        MobileDriverFactory.getInstance().removeMobileDriver();
        service.stop();
    }
}
