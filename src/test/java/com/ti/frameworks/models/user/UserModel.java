package com.ti.frameworks.models.user;

import com.ti.apibase.BaseModel;
import com.ti.frameworks.models.SupportModel;
import lombok.Data;

@Data
public class UserModel extends BaseModel {
    private DataModel data;
    private SupportModel support;
}
