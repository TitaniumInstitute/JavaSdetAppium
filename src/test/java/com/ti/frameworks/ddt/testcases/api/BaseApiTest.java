package com.ti.frameworks.ddt.testcases.api;

import com.ti.apibase.BaseModel;
import com.ti.apibase.RestAssuredUtils;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.testng.annotations.BeforeTest;

import java.util.Map;

public class BaseApiTest extends BaseModel {
    protected Map<String, String> expectedHeaders = Map.of("Content-Type", "application/json");
    protected ResponseOptions<Response> response;

    @BeforeTest
    public void setup() {
        new RestAssuredUtils(BASE_URL, expectedHeaders);
    }
}
