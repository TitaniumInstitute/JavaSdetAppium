package com.ti.cucumber.runner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"com/ti/cucumber/features"},
        glue = {"com/ti/cucumber/steps_definitions"}
)
public class TestRunner {
}
