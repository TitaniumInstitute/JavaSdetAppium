package com.ti.cucumber.steps_definitions;

import com.ti.cucumber.models.ContactDetailData;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class CustomerCollectionSteps {
    @DataTableType
    public ContactDetailData convert(Map<String, String> contacts){
        return new ContactDetailData(
                contacts.get("contactDetail"),
                contacts.get("time_of_order"),
                contacts.get("preparation_time")
        );
    }

    @Given("the customer is Unauthenticated")
    public void theCustomerIsUnauthenticated() {
        System.out.println("the customer is Unauthenticated");
    }

    @And("they've chosen to collect their order")
    public void theyVeChosenToCollectTheirOrder() {
        System.out.println("they've chosen to collect their order");
    }

    @When("they provide {string}")
    public void theyProvide(String acceptableContactData) {
        System.out.println("they provide %s".formatted(acceptableContactData));
    }

    @Then("they should be asked to supply payment details")
    public void theyShouldBeAskedToSupplyPaymentDetails() {
        System.out.println("they should be asked to supply payment details");
    }

  //*********************************************************************

    @Given("the customer placed an order")
    public void theCustomerPlacedAnOrder(List<ContactDetailData> contactDetailDataList) {
        System.out.println(contactDetailDataList.get(0).timeOrder);
        System.out.println(contactDetailDataList.get(0).preparationTime);
    }

    @When("they choose to collect their order")
    public void theyChooseToCollectTheirOrder() {
    }

    @Then("the estimated time of order completion should be displayed as 18:30")
    public void theStimatedTimeOfOrderCompletionShouldBeDisplayedAs() {
    }

}
