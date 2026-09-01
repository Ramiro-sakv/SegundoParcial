package com.ramiro.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {
    private final By name = By.cssSelector(".inventory_details_name");
    private final By price = By.cssSelector(".inventory_details_price");
    private final By description = By.cssSelector(".inventory_details_desc");
    private final By addButton = By.cssSelector("[data-test='add-to-cart']");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String productName() {
        return text(name);
    }

    public String productPrice() {
        return text(price);
    }

    public boolean hasDescription() {
        return !text(description).isBlank();
    }

    public boolean canBeAddedToCart() {
        return isPresent(addButton);
    }
}
