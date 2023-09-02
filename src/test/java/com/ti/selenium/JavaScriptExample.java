package com.ti.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JavaScriptExample extends BaseTestClass{
    JavascriptExecutor js;
    String pageLoadStatus = "";

    private void highLight(WebElement element){
        js = (JavascriptExecutor)driver;
        for (byte iCnt=0; iCnt<3; iCnt++){
            try {
                js.executeScript("arguments[0].setAttribute('style','background:red')",element);
                Thread.sleep(500);
                js.executeScript("arguments[0].setAttribute('style','background:')",element);
            }catch (JavascriptException jse){
                System.err.println("Class: EjemploJavaScript | Method: highLight | Exception desc: " + jse.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void scrollWindow(String scroll){
        try {
            js = (JavascriptExecutor)driver;
            switch (scroll){
                case "up" -> {js.executeScript("window.scrollBy(0,-250)");}
                case "down" -> {js.executeScript("window.scrollBy(0,250)");}
                default -> {System.err.println("Bad option");}
            }
        }catch (JavascriptException jse){
            System.err.println("Class: EjemploJavaScript | Method: scrollWindow | Exception desc: " + jse.getMessage());
        }
    }

    private void waitForPageToLoad(){
        try {
            js = (JavascriptExecutor)driver;
            do{
                pageLoadStatus = (String)js.executeScript("return document.readyState");
            }while (!pageLoadStatus.equals("complete"));
        }catch (JavascriptException jse){
            System.err.println("Class: EjemploJavaScript | Method: waitForPageToLoad | Exception desc: " + jse.getMessage());
        }
    }

    @Test
    void javascriptTest(){
        driver.navigate().to(demoSiteUrl);
        WebElement txtLoginUserName = driver.findElement(By.id("user_login"));
        highLight(txtLoginUserName);
        txtLoginUserName.clear();
        txtLoginUserName.sendKeys(username);

        WebElement txtPassword = driver.findElement(By.name("pwd"));
        highLight(txtPassword);
        txtPassword.clear();
        txtPassword.sendKeys(password);

        WebElement btnLogin = driver.findElement(By.xpath("//input[contains(@value,'Log')]"));
        highLight(btnLogin);
        js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",btnLogin);

        waitForPageToLoad();

        scrollWindow("down");

        WebElement spnTeacher = driver.findElement(By.xpath("(//span[contains(text(),'Teach')])[1]"));
        highLight(spnTeacher);
        Assert.assertTrue(spnTeacher.getText().contains("Teach"));
    }
}
