package com.ti.frameworks.models.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.apibase.BaseModel;
import lombok.Data;

@Data
public class DataModel extends BaseModel {
    private int id;
    private String name;
    private int year;
    private String color;
    @JsonProperty("pantone_value")
    private String pantoneValue;

}
