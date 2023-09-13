package com.ti.baseiumobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BaseScreen {
    public static BaseScreen actualScreen;

    public <TScreen extends BaseScreen> TScreen getInstance(Class<TScreen> screen){
        Object obj = initElements(MobileDriverFactory.getInstance().getMobileDriver(),screen);
        return screen.cast(obj);
    }

    private static <T> T initElements(AppiumDriver appiumDriver, Class<T> pageClass){
        T page = null;
        try{
            page = pageClass.getConstructor().newInstance();
            PageFactory.initElements(
                    new AppiumFieldDecorator(appiumDriver),page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageClass.cast(page);
    }

    public <TScreen extends BaseScreen> TScreen as(Class<TScreen> screenInstance){
        try{
            return (TScreen)this;
        }catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }
}
