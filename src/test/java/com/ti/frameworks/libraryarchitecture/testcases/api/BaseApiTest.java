package com.ti.frameworks.libraryarchitecture.testcases.api;

import com.ti.apibase.RestAssuredUtils;
import org.testng.annotations.BeforeTest;

import java.util.Map;

public class BaseApiTest extends ApiLibrary {
    protected Map<String, String> expectedHeaders = Map.of("Content-Type", "application/json");
    @BeforeTest
    public void setup() {
        new RestAssuredUtils(BASE_URL, expectedHeaders);
    }
}
