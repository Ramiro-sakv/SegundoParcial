package com.ramiro.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {
    private final By title = By.cssSelector(".title");
    private final By bikeLight = By.id("item_0_title_link");
    private final By finish = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String title() {
        return text(title);
    }

    public String productName() {
        return text(bikeLight);
    }

    public CheckoutCompletePage finishPurchase() {
        click(finish);
        return new CheckoutCompletePage(driver);
    }
}
