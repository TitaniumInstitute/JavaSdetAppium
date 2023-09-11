package com.ti.frameworks.ddt.testcases.webui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Set;

import static com.ti.frameworks.ddt.dataproviders.JSONArrayData.getJsonTableArray;

public class JsonExample {
    @DataProvider
    public Object[][] getJSONProviderData(){
        return getJsonTableArray("users.json","validUser");
    }

    @Test(dataProvider = "getJSONProviderData")
    void test(LinkedHashMap<String, String> linkedHashData){
        Set<String> keys = linkedHashData.keySet();

        keys.forEach(key -> {
            System.out.println(key + " -- " + linkedHashData.get(key));
        });
    }

}
