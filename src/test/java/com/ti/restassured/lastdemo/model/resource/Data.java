package com.ti.restassured.lastdemo.model.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.restassured.lastdemo.model.Support;
import com.ti.apibase.BaseModel;

@lombok.Data
public class Data extends BaseModel {
    private int id;
    private String name;
    private String year;
    private String color;
    @JsonProperty("pantone_value")
    private String pantoneValue;
    private Support support;
}
