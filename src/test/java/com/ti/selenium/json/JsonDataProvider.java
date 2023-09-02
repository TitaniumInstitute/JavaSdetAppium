package com.ti.selenium.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;

public class JsonDataProvider {

    private static JsonElement jsonElement = null;
    @DataProvider
    public Object[][] getData(){
        return readJson("./src/test/resources/testData3.json", "data 1");
    }

    // Parse JSON data
    private static void parseJSON(String filename){
        // Parse JSON data
        try {
            jsonElement = JsonParser.parseReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // This method uses the GSON library to parse JSON data
    public static Object[][] readJson(String filename, String jsonObj) {
        parseJSON(filename);

        assert jsonElement != null;
        // Get entire JSON object
        JsonObject jsonMainObj = jsonElement.getAsJsonObject();
        // Get individual JSON array object
        JsonArray jsonArray = jsonMainObj.get(jsonObj).getAsJsonArray();

        // Java array to store JSON data
        Object[][] testData = new Object[jsonArray.size()][1];

        // Read data inside JSON array and store it in Java array
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonInsideObj = jsonArray.get(i).getAsJsonObject();
            LinkedHashMap<Object, Object> map = new LinkedHashMap<>();

            jsonInsideObj.keySet().forEach(key->{
                map.put(key, jsonInsideObj.get(key).getAsString());
            });
            /*for (String key : jsonInsideObj.keySet()) {
                String value = jsonInsideObj.get(key).getAsString();
                map.put(key, value);
            }*/
            testData[i][0] = map;
        }
        return testData;
    }
}
