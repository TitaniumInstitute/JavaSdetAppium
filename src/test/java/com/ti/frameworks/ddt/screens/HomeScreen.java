package com.ti.frameworks.ddt.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.baseiumobile.BaseScreen;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.CacheLookup;

public class HomeScreen extends BaseScreen {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='PRODUCTOS']")
    @CacheLookup
    RemoteWebElement lblProducts;
    protected String getElementText(RemoteWebElement element) {
        return element.getTagName().equals("input") ? element.getAttribute("value") : element.getText();
    }

    protected String getProductsTitle(){
        return lblProducts.getText();
    }

    public HomeScreen verifyProductsText(){
        assert getProductsTitle().contains("PRODUCT");
        return this;
    }
}
