package com.ti.restassured.lastdemo.model.user;

import com.ti.restassured.lastdemo.model.Support;
import com.ti.apibase.BaseModel;

@lombok.Data
public class User extends BaseModel {
    private Data data;
    private Support support;
}
