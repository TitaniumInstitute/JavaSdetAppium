package com.ti.jdbc;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            ResultSet studentsResult = SQLUtils.getResultSet("Students", "studentsdetail.sql");

            List<Student> students = new ArrayList<>();

            while (studentsResult.next()){
                Student student = new Student();
                student.setId(studentsResult.getInt("studid"));
                student.setName(studentsResult.getString("studfname"));
                student.setLastname(studentsResult.getString("studlname"));
                student.setFatherName(studentsResult.getString("fathername"));
                student.setGender(studentsResult.getString("gender"));
                student.setAddress(studentsResult.getString("address"));
                student.setContact(studentsResult.getLong("contactno"));
                student.setCourseId(studentsResult.getInt("courseid"));
                student.setSemester(studentsResult.getByte("semester"));
                student.setDob(studentsResult.getString("dob"));
                students.add(student);
            }

            students.stream()
                    .collect(Collectors.toList()).forEach(System.out::println);
                    /*.forEach(student -> {
                        System.out.println(
                                String.format("Student Info => Name = %s, LastName = %s, Gender = %s",
                                        student.getName(),
                                        student.getLastname(),
                                        student.getGender()));
                    });*/

            SQLUtils.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
