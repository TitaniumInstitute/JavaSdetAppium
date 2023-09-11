package com.ti.appium.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumActionsExample extends AndroidBaseTest {
    @Test
    void Miscellaneous(){
        //App package & App Activity
        // Windows: adb shell dumpsys window | find "mCurrentFocus"
        // Mac: adb shell dumpsys window | grep -E 'mCurrentFocus'
        androidDriver.executeScript("mobile: startActivity", ImmutableMap.of("intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));

        //androidDriver.findElement(AppiumBy.accessibilityId("Preference")).click();
        //androidDriver.findElement(AppiumBy.xpath("//*[@content-desc='3. Preference dependencies']")).click();

        //Turn screen
        DeviceRotation landScape = new DeviceRotation(0,0,180);
        androidDriver.rotate(landScape);

        androidDriver.findElement(AppiumBy.id("android:id/checkbox")).click();
        androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='WiFi settings']")).click();
        String alertTitle = androidDriver.findElement(AppiumBy.id("android:id/alertTitle")).getText();

        //copy paste
        //copy to clipboard - past it clipboard
        androidDriver.setClipboardText("Gilberto Wifi Copied");
        androidDriver.findElement(AppiumBy.id("android:id/edit")).clear();
        androidDriver.findElement(AppiumBy.id("android:id/edit")).sendKeys(androidDriver.getClipboardText());

        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));

        androidDriver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        Assert.assertEquals(alertTitle,"WiFi settings");

        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
