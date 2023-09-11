package com.ti.appium.patronesdediseño.pf.testcases;

import com.ti.appium.BaseTest;
import com.ti.appium.patronesdediseño.pf.screens.HomeScreen;
import org.testng.annotations.Test;

public class SauceLabLoginTests extends BaseTest {

    @Test
    void verifyLoginCorrectly(){
        actualScreen.as(HomeScreen.class).verifyProductsText();
    }
}
