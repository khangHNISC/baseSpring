package com.example.pattern;

import cucumber.api.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MyStepdefs {

    @Then("They should equals")
    public void they_should_equals() {
        assertNotNull(1);
    }
}
