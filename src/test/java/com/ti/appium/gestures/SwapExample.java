package com.ti.appium.gestures;

import com.ti.appium.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class SwapExample extends BaseTest {
    @Test
    public void swipeDemoTest() {
        androidDriver.findElement(AppiumBy.accessibilityId("Views")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='1. Photos']")).click();

        //WebElement firstImage=androidDriver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
        RemoteWebElement firstImage = (RemoteWebElement) androidDriver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
        // Before perform the swipe
        String firstTimeValue=firstImage.getAttribute("focusable");
        Assert.assertEquals("true", firstTimeValue);

        //perform swipe action


        ((JavascriptExecutor)androidDriver).executeScript("mobile: swipeGesture", Map.of("elementId", firstImage.getId(), "percent", 0.75, "direction", "left"));


        // After perform the swipe
        String secondTimeValue=firstImage.getAttribute("focusable");
        Assert.assertEquals("false", secondTimeValue);


    }
}
