package com.ti.frameworks.ddt.testcases.mobileui;

import com.ti.frameworks.screens.HomeScreen;
import com.ti.frameworks.screens.LoginScreen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import static com.ti.frameworks.ddt.dataproviders.ExcelArrayData.getExcelTableArray;
import static com.ti.frameworks.ddt.dataproviders.JSONArrayData.getJsonTableArray;

public class SauceLabLoginTestsExcel extends BaseMobileTest {
    static Object[][] testObjArray;
    @DataProvider
    public Object[][] getJSONProviderData(Method method) throws IOException {
        if (method.getName().equals("loginWithRightCredentials")){
            testObjArray = getJsonTableArray("MobileUsers.json","validUser");
        }else if (method.getName().equals("loginWithWrongCredentials")){
            testObjArray = getExcelTableArray("Users.xlsx","InvalidUsers");
        }
        return testObjArray;
    }

    /*@Test(dataProvider = "getExcelProviderData", priority = 1)
    void loginWithWrongCredentials(LinkedHashMap<String, String> data){
        actualPage = getInstance(LoginPage.class)
                .loginAs(data.values().toArray()[0].toString())
                .withPassword(data.values().toArray()[1].toString())
                .andRememberMe(true)
                .submitLogin();

        actualPage = getInstance(LoginPage.class);
        actualPage.as(LoginPage.class).verifyErrorText(data.values().toArray()[2].toString());

    }*/

    @Test(dataProvider = "getJSONProviderData", priority = 2)
    void loginWithRightCredentials(LinkedHashMap<String, String> data){
        actualScreen = getInstance(LoginScreen.class)
                .loginAs(data.values().toArray()[0].toString())
                .withPassword(data.values().toArray()[1].toString())
                .submitLogin();

        actualScreen.as(HomeScreen.class).verifyProductsText();
    }
}
