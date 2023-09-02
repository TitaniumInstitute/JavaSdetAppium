package com.ti.selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ExtentReportExample{

    String destination;
    String dateOption = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    ExtentReports extentReports;
    ExtentSparkReporter extentSparkReporter;
    ExtentTest extentTest;

    public String getScreenshot(WebElement element, String screenShotName) throws IOException {

        destination = createFolder(System.getProperty("user.dir") + "/results/screenshots") + "/" + screenShotName+dateOption+".png";
        try {
            FileHandler.copy((element == null?
                            ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE)
                            :element.getScreenshotAs(OutputType.FILE)),
                    new File(destination));
        }catch (Exception e){
            /*Screenshot screenshot = new AShot().takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "png", new File(destination));*/
            getScreenshot(null, "WebDriverException");
            Assert.fail(e.getMessage());
        }
        return destination;
    }

    private static String createFolder(String folderName) {
        File theDir = new File(folderName);
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                new Exception("Class SeleniumUtils | Method createFolder | Exception: " + se.getMessage());
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
        return theDir.toString();
    }

    protected WebDriver driver;
    protected String demoSiteUrl = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";

    @BeforeTest
    public void setup() throws IOException {
        //driver = WebDriverManager.edgedriver().create();
        driver = new ChromeDriver();
        driver.get(demoSiteUrl);
        driver.manage().window().maximize();
        extentReports = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter("results/TIReport.html");
        extentSparkReporter.loadJSONConfig(new File("./src/main/resources/extent-report-testData.json"));
        extentReports.attachReporter(extentSparkReporter);
    }

    @Test(priority = 2)
    void testVerifyRegisterPage() throws IOException {
        extentTest = extentReports.createTest("testVerifyRegisterPage");
        WebElement lnkRegister = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.findElement(By.linkText("Register")));
        lnkRegister.click();
        extentTest.pass(String.valueOf(Status.PASS)).addScreenCaptureFromPath(getScreenshot(null,"demosite"));
        Assert.assertTrue(driver.getTitle().contains("Registration Form"));
    }

    @Test(priority = 1)
    void testVerifyLostPassPage() throws IOException {
        extentTest = extentReports.createTest("testVerifyLostPassPage");
        try {
            driver.findElement(By.linkText("Lost your passw")).click();
            Assert.assertTrue(driver.getTitle().contains("Lost Password"));
        }catch (WebDriverException we){
            extentTest.fail(we.getMessage()).addScreenCaptureFromPath(getScreenshot(null, "WebDriverException"));
            Assert.fail();
        }
    }

    @AfterTest
    void closeBrowser(){
        driver.quit();
        extentReports.flush();
    }
}
