package com.ti.frameworks.ddt.testcases.webui;

import com.ti.frameworks.ddt.pages.LoginPage;
import com.ti.frameworks.ddt.pages.MainPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Set;

import static com.ti.frameworks.ddt.dataproviders.ExcelArrayData.getExcelTableArray;

public class SchoolLoginTest extends BaseWebTest{
    static Object[][] testObjArray;
    @DataProvider
    public Object[][] getExcelProviderData(Method method) throws IOException {
        if (method.getName().equals("loginWithRightCredentials")){
            testObjArray = getExcelTableArray("Users.xlsx","ValidUsers");
        }else if (method.getName().equals("loginWithWrongCredentials")){
            testObjArray = getExcelTableArray("Users.xlsx","InvalidUsers");
        }
        return testObjArray;
    }

    @Test(enabled = false, dataProvider = "getExcelProviderData", priority = 1)
    void loginWithWrongCredentials(LinkedHashMap<String, String> data){

        data.forEach((key, value) -> {
            actualPage = getInstance(LoginPage.class)
                    .loginAs(data.get(key))
                    .withPassword(data.get(key))
                    .andRememberMe(true)
                    .submitLogin();

            actualPage = getInstance(LoginPage.class);
            actualPage.as(LoginPage.class).verifyErrorText(data.get(key));
        });

    }

    @Test(dataProvider = "getExcelProviderData", priority = 2)
    void loginWithRightCredentials(LinkedHashMap<String, String> data){

        Set<String> keys = data.keySet();

        for (String key : keys) {
            actualPage = getInstance(LoginPage.class)
                    .loginAs(data.get(key))
                    .withPassword(data.get(key))
                    .andRememberMe(true)
                    .submitLogin();

            actualPage.as(MainPage.class).verifySchoolName();

        }
    }
}
