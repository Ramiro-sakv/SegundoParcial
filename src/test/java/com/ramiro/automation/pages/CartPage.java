package com.ramiro.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By backpackTitle = By.id("item_4_title_link");
    private final By removeBackpack = By.id("remove-sauce-labs-backpack");
    private final By cartItem = By.cssSelector(".cart_item");
    private final By checkout = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String backpackName() {
        return text(backpackTitle);
    }

    public void removeBackpack() {
        click(removeBackpack);
    }

    public boolean isEmpty() {
        return !isPresent(cartItem);
    }

    public CheckoutInformationPage checkout() {
        click(checkout);
        return new CheckoutInformationPage(driver);
    }
}
