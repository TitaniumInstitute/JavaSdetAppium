package com.ti.frameworks.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends MainPage {
    @FindBy(linkText = "Students")
    private WebElement navStudents;

    public StudentPage goToStudents() {
        navStudents.click();
        return (StudentPage) (actualPage = getInstance(StudentPage.class));
    }

    /*public TeacherPage gotToTeachers(){
        navTeachers.click();
        return new TeachersPage();
    }*/
}
