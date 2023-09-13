package com.ti.frameworks.ddt.testcases.webui;

import com.ti.frameworks.pages.LoginPage;
import com.ti.frameworks.pages.MainPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import static com.ti.frameworks.ddt.dataproviders.ExcelArrayData.getExcelTableArray;
import static com.ti.frameworks.ddt.dataproviders.JSONArrayData.getJsonTableArray;

public class SchoolLoginTestJSON extends BaseWebTest{
    static Object[][] testObjArray;
    @DataProvider
    public Object[][] getJSONProviderData(Method method) throws IOException {
        if (method.getName().equals("loginWithRightCredentials")){
            testObjArray = getJsonTableArray("WebUsers.json","validUser");
        }else if (method.getName().equals("loginWithWrongCredentials")){
            testObjArray = getExcelTableArray("Users.xlsx","InvalidUsers");
        }
        return testObjArray;
    }

    @Test(dataProvider = "getJSONProviderData", priority = 1)
    void loginWithWrongCredentials(LinkedHashMap<String, String> data){
        actualPage = getInstance(LoginPage.class)
                .loginAs(data.values().toArray()[0].toString())
                .withPassword(data.values().toArray()[1].toString())
                .andRememberMe(true)
                .submitLogin();

        actualPage = getInstance(LoginPage.class);
        actualPage.as(LoginPage.class).verifyErrorText(data.values().toArray()[2].toString());

    }

    @Test(dataProvider = "getJSONProviderData", priority = 2)
    void loginWithRightCredentials(LinkedHashMap<String, String> data){
        actualPage = getInstance(LoginPage.class)
                .loginAs(data.values().toArray()[0].toString())
                .withPassword(data.values().toArray()[1].toString())
                .andRememberMe(true)
                .submitLogin();

        actualPage.as(MainPage.class).verifySchoolName();
    }
}
