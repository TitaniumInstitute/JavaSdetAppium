package com.ti.appium.android.gestures;

import com.google.common.collect.ImmutableMap;
import com.ti.appium.android.AndroidBaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScrollExample extends AndroidBaseTest {

    @BeforeTest
    void goToView(){
        androidDriver.findElement(AppiumBy.accessibilityId("Views")).click();
    }

    @Test(enabled = false)
    void scrollUIAutomatorTest() throws InterruptedException {
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"));
        Thread.sleep(1500);
    }

    @Test
    void scrollUsingGestureTest(){
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) androidDriver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));

        } while(canScrollMore);
    }
}
