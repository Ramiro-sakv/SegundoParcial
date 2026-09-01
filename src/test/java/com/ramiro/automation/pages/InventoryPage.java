package com.ramiro.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {
    private final By inventory = By.cssSelector(".inventory_list");
    private final By sort = By.cssSelector(".product_sort_container");
    private final By firstProductName = By.cssSelector(".inventory_item:first-child .inventory_item_name");
    private final By firstProductPrice = By.cssSelector(".inventory_item:first-child .inventory_item_price");
    private final By lastProductName = By.cssSelector(".inventory_item:last-child .inventory_item_name");
    private final By lastProductPrice = By.cssSelector(".inventory_item:last-child .inventory_item_price");
    private final By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By addBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private final By backpackDetails = By.id("item_4_title_link");
    private final By cartLink = By.cssSelector(".shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        super(driver);
        visible(inventory);
    }

    public void sortByPriceLowToHigh() {
        new Select(visible(sort)).selectByValue("lohi");
    }

    public String firstProductName() {
        return text(firstProductName);
    }

    public String firstProductPrice() {
        return text(firstProductPrice);
    }

    public String lastProductName() {
        return text(lastProductName);
    }

    public String lastProductPrice() {
        return text(lastProductPrice);
    }

    public void addBackpackToCart() {
        click(addBackpack);
    }

    public void addBikeLightToCart() {
        click(addBikeLight);
    }

    public String cartCount() {
        return text(cartBadge);
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public ProductDetailsPage openBackpackDetails() {
        click(backpackDetails);
        return new ProductDetailsPage(driver);
    }
}
