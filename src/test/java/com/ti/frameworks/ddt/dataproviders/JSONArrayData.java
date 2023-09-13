package com.ti.frameworks.ddt.dataproviders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;

import static com.ti.frameworks.config.Constants.JSONS_FOLDER;

public class JSONArrayData {

    private static JsonElement jsonElement = null;
    private static JsonArray jsonArray;

    // Parse JSON data
    private static JsonArray getJSONArray(String filename, String jsonObj){
        // Parse JSON data
        try {
            return JsonParser.parseReader(new FileReader(JSONS_FOLDER+filename))
                    .getAsJsonObject()
                    .get(jsonObj)
                    .getAsJsonArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // This method uses the GSON library to parse JSON data
    public static Object[][] getJsonTableArray(String filename, String jsonObj) {
        // Get individual JSON array object
        jsonArray = getJSONArray(filename,jsonObj);

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
