package com.ti.frameworks.ddt.testcases.mobileui;

import com.ti.frameworks.screens.HomeScreen;
import com.ti.frameworks.screens.LoginScreen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import static com.ti.frameworks.ddt.dataproviders.JSONArrayData.getJsonTableArray;

public class SauceLabLoginTestsJSON extends BaseMobileTest {
    static Object[][] testObjArray;
    @DataProvider
    public Object[][] getJSONProviderData(Method method) throws IOException {
        if (method.getName().equals("loginWithRightCredentials")){
            testObjArray = getJsonTableArray("MobileUsers.json","validUser");
        }else if (method.getName().equals("loginWithWrongCredentials")){
            testObjArray = getJsonTableArray("MobileUsers.json","invalidUser");
        }
        return testObjArray;
    }

    @Test(dataProvider = "getJSONProviderData", priority = 1)
    void loginWithWrongCredentials(LinkedHashMap<String, String> data){
        actualScreen = getInstance(LoginScreen.class)
                .loginAs(data.values().toArray()[0].toString())
                .withPassword(data.values().toArray()[1].toString())
                .submitLogin();

        actualScreen = getInstance(LoginScreen.class);
        String errorMessage = actualScreen.as(LoginScreen.class).getError();
        if (errorMessage.toLowerCase().contains("password")||errorMessage.toLowerCase().contains("username")){
            actualScreen.as(LoginScreen.class).verifyErrorText(data.values().toArray()[3].toString());
        }else {
            actualScreen.as(LoginScreen.class).verifyErrorText(data.values().toArray()[2].toString());
        }
    }

    @Test(dataProvider = "getJSONProviderData", priority = 2)
    void loginWithRightCredentials(LinkedHashMap<String, String> data){
        actualScreen = getInstance(LoginScreen.class)
                .loginAs(data.values().toArray()[0].toString())
                .withPassword(data.values().toArray()[1].toString())
                .submitLogin();

        actualScreen.as(HomeScreen.class).verifyProductsText();
    }
}
