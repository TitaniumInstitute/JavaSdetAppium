package com.ti.restassured.lastdemo.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apibase.BaseModel;

@lombok.Data
public class Data extends BaseModel {
    private int id;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String avatar;
}
