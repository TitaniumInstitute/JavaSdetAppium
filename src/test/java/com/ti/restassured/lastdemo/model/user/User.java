package com.ti.restassured.lastdemo.model.user;

import com.ti.restassured.lastdemo.model.Support;
import org.apibase.BaseModel;

@lombok.Data
public class User extends BaseModel {
    private Data data;
    private Support support;
}
