package com.ti.restassured.lastdemo.model.resource;

import com.ti.restassured.lastdemo.model.Support;
import org.apibase.BaseModel;

@lombok.Data
public class Resource extends BaseModel {
    private Data data;
    private Support support;
}
