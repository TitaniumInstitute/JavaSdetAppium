package com.ti.appium;

import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindingElements extends BaseTest{

    @Test
    void accessibilityExample() throws InterruptedException {
       androidDriver.findElement(AppiumBy.accessibilityId("Preference")).click();
       androidDriver.findElement(AppiumBy.xpath("//*[@content-desc='3. Preference dependencies']")).click();
       androidDriver.findElement(AppiumBy.id("android:id/checkbox")).click();
       androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='WiFi settings']")).click();
       //Clase 30 Agosto
        String alertTitle = androidDriver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
       androidDriver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Gilberto WiFi");
       androidDriver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();

       Assert.assertEquals(alertTitle,"WiFi settings");
       Thread.sleep(1500);
    }
}
