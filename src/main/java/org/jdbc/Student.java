package org.jdbc;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private String lastname;
    private String fatherName;
    private String gender;
    private String address;
    private long contact;
    private int courseId;
    private byte semester;
    private String dob;
}
