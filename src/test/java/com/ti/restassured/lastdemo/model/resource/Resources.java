package com.ti.restassured.lastdemo.model.resource;

import com.ti.restassured.lastdemo.model.Pages;

import java.util.List;

@lombok.Data
public class Resources extends Pages {
    private List<Data> data;
}
