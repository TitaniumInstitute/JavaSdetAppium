package com.ti.cucumber.steps_definitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.NoSuchElementException;

public class PayOnCollectionSteps {
    @When("they choose to pay on collection")
    public void theyChooseToPayOnCollection() {
        // Write code here that turns the phrase above into concrete actions
        throw new NoSuchElementException("No encontrè el pay on collection item"); //System.out.println("When they choose to pay on collection");
    }
    @Then("they should be provided with an order confirmation")
    public void theyShouldBeProvidedWithAnOrderConfirmation() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Then they should be provided with an order confirmation");
    }
}
