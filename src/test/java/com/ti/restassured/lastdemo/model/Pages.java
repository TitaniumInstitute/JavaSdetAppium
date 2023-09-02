package com.ti.restassured.lastdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apibase.BaseModel;

@Data
public class Pages extends BaseModel {
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    private Support support;
}
