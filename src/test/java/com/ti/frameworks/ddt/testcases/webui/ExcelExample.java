package com.ti.frameworks.ddt.testcases.webui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

import static com.ti.frameworks.ddt.dataproviders.ExcelArrayData.getExcelTableArray;

public class ExcelExample {
    @DataProvider
    public Object[][] getExcelProviderData() throws IOException {
        return getExcelTableArray("Users.xlsx","ValidUsers");
    }

    @DataProvider
    public Object[][] getExcelProviderInvalidData() throws IOException {
        return getExcelTableArray("Users.xlsx","InvalidUsers");
    }

    @Test(enabled = false,dataProvider = "getExcelProviderData")
    void test(LinkedHashMap<String, String> data){
        data.forEach((s, s2) -> System.out.printf("Column %s Value %s",s,s2));
    }

    @Test(dataProvider = "getExcelProviderInvalidData")
    void testInvalid(LinkedHashMap<String, String> invalidData){
        Set<String> keys = invalidData.keySet();

        for (String key : keys) {
            System.out.println(key + " -- "
                    + invalidData.get(key));
        }
    }
}
