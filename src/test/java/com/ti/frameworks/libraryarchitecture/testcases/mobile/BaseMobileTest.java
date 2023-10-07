package com.ti.frameworks.libraryarchitecture.testcases.mobile;

import com.ti.baseiumobile.DeviceOSType;
import com.ti.baseiumobile.MobileDriverFactory;
import com.ti.baseiumobile.model.DriverOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;

public class BaseMobileTest extends MobileLibrary {
    AppiumDriverLocalService service;
    @BeforeSuite
    public void startService(){
        try {
            service = new AppiumServiceBuilder()
                    .withArgument(() -> "--use-plugins", "element-wait,gestures")
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .build();
            service.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @BeforeTest
    @Parameters({"deviceOsType","appName","ipAddress","deviceName","port","udid"})
    public void setup(String deviceOsType, String appName, String ipAddress, String deviceName, @Optional("port")String port, @Optional("udid")String udid){

        DriverOptions options = new DriverOptions();
        options.setAppName(appName)
                .setDeviceName(deviceName)
                .setAppActivity("com.swaglabsmobileapp.MainActivity")
                .setPort(port)
                .setUdid(udid);

        MobileDriverFactory.getInstance().setMobileDriver(DeviceOSType.valueOf(deviceOsType),options);
        login();
    }

    @AfterTest
    public void turndown(){
        MobileDriverFactory.getInstance().removeMobileDriver();
    }

    @AfterSuite
    public void stopServer(){
        if (service!=null){
            service.stop();
        }
    }
}
