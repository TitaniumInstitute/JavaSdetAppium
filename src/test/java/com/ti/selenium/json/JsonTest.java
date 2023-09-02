package com.ti.selenium.json;

import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Set;

public class JsonTest {
    @Test(dataProviderClass = JsonDataProvider.class, dataProvider = "getData")
    void dataProviderJsonTest(LinkedHashMap<String, String> linkedHashData){
        Set<String> keys = linkedHashData.keySet();

        keys.forEach(key -> {
            System.out.println(key + " -- " + linkedHashData.get(key));
        });
    }
}
