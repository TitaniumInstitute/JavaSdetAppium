package com.ti.frameworks.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.apibase.BaseModel;
import lombok.Data;

@Data
public class DataModel extends BaseModel {
    private int id;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String avatar;
}
