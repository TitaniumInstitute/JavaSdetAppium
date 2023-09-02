package com.ti.restassured.lastdemo.model.user;

import com.ti.restassured.lastdemo.model.Pages;

import java.util.List;

@lombok.Data
public class Users extends Pages {
    private List<Data> data;
}
