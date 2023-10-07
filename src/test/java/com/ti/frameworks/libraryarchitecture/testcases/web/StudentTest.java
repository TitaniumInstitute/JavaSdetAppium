package com.ti.frameworks.libraryarchitecture.testcases.web;

import org.testng.annotations.Test;

public class StudentTest extends BaseWebTest{

    @Test
    void verifyNewStudentIsCreated() {
        addAnStudent();
        verifyUserIsAdded();
        deleteAnStudent();
    }
}
