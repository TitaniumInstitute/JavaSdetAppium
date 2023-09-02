package com.ti.appium;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

public class CompleteFunctionalityExample extends BaseTest{

    @Test
    void fillFirstPage(){
        //Scroll and select Canada option from dropDown
        androidDriver.findElement(AppiumBy.id("android:id/text1")).click();
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));"));
        androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Canada']")).click();

        //Type name in a text field
        androidDriver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Jeniffer");
        androidDriver.hideKeyboard(); // hide keyboard

        //Select radio option
        androidDriver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
    }

    @Test
    void errorUserName(){

    }
}
