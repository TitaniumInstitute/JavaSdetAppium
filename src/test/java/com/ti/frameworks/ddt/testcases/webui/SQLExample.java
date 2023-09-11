package com.ti.frameworks.ddt.testcases.webui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import static com.ti.frameworks.ddt.dataproviders.SQLArrayData.getQueryTableArrayNew;

public class SQLExample {
    @DataProvider
    public Object[][] getSQLProviderData() throws SQLException, IOException {
        return getQueryTableArrayNew("Students", "studentsdetail.sql");
    }

    @Test(dataProvider = "getSQLProviderData",enabled = false)
    void test(String val1, String val2, String val3, String val4, String val5, String val6, String val7, Long val8, String val9, Date val10){
        System.out.printf("Value 1: %s, Value 2: %s, Value 3: %s, Value 4: %s, Value 5: %s, Value 6: %s, Value 7: %s, Value 8: %s, Value 9: %s, Value 10: %s",
                val1,val2,val3,val4,val5,val6,val7,val8,val9,val10);
    }

    @Test(dataProvider = "getSQLProviderData", enabled = true)
    void test2(LinkedHashMap<Object, Object> data) throws SQLException, IOException {
        data.forEach((s, s2) -> System.out.printf("Column %s Value %s",s,s2));
    }
}
