package com.ramiro.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By error = By.cssSelector(".error-message-container h3");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String value) {
        type(firstName, value);
    }

    public void enterLastName(String value) {
        type(lastName, value);
    }

    public void enterPostalCode(String value) {
        type(postalCode, value);
    }

    public void continueCheckout() {
        click(continueButton);
    }

    public String errorMessage() {
        return text(error);
    }

    public CheckoutOverviewPage completeForm(String name, String surname, String postal) {
        enterFirstName(name);
        enterLastName(surname);
        enterPostalCode(postal);
        continueCheckout();
        return new CheckoutOverviewPage(driver);
    }
}
