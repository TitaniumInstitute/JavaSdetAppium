package com.ti.appium;

import com.ti.appium.android.AndroidBaseTest;
import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class BridgeAppExample extends AndroidBaseTest {
    String gender = "Famale";
    String country = "Canada";
    @Test
    void fillForm() throws InterruptedException {
        androidDriver.findElement(AppiumBy.id("android:id/text1")).click();
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+country+"\"))"));
        androidDriver.findElement(AppiumBy.xpath("android.widget.TextView[@text='"+country+"']")).click();
        androidDriver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Test Name");
        androidDriver.hideKeyboard();
        androidDriver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='"+gender+"']")).click();
        androidDriver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(2000);
    }
}
