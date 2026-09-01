package com.ramiro.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    private final By confirmation = By.cssSelector(".complete-header");
    private final By backToProducts = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String confirmationMessage() {
        return text(confirmation);
    }

    public boolean hasBackToProductsButton() {
        return isPresent(backToProducts);
    }
}
