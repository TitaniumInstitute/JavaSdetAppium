package com.ti.appium.android.gestures;

import com.google.common.collect.ImmutableMap;
import com.ti.appium.android.AndroidBaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongPressExample extends AndroidBaseTest {
    @Test
    void longPressExample() throws InterruptedException {
        androidDriver.findElement(AppiumBy.accessibilityId("Views")).click();
        androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        RemoteWebElement longPress = (RemoteWebElement) androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));

        ((JavascriptExecutor)androidDriver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", longPress.getId(), "pressure", 0.5, "duration", 1500));
        WebElement ele = androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sample action']"));
        Assert.assertTrue(ele.isDisplayed());
        Thread.sleep(1500);
    }
}
