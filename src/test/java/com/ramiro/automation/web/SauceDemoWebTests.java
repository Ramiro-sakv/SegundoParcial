package com.ramiro.automation.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ramiro.automation.pages.CartPage;
import com.ramiro.automation.pages.CheckoutCompletePage;
import com.ramiro.automation.pages.CheckoutInformationPage;
import com.ramiro.automation.pages.CheckoutOverviewPage;
import com.ramiro.automation.pages.ProductDetailsPage;

@DisplayName("Pruebas web de Sauce Demo")
class SauceDemoWebTests extends BaseWebTest {

    @Test
    @DisplayName("WEB-05 Ordenar productos por precio de menor a mayor")
    void sortProductsByPriceLowToHigh() {
        inventory.sortByPriceLowToHigh();

        assertEquals("Sauce Labs Onesie", inventory.firstProductName());
        assertEquals("$7.99", inventory.firstProductPrice());
        assertEquals("Sauce Labs Fleece Jacket", inventory.lastProductName());
        assertEquals("$49.99", inventory.lastProductPrice());
    }

    @Test
    @DisplayName("WEB-06 Agregar producto al carrito")
    void addProductToCart() {
        inventory.addBackpackToCart();
        assertEquals("1", inventory.cartCount());

        CartPage cart = inventory.openCart();
        assertEquals("Sauce Labs Backpack", cart.backpackName());
    }

    @Test
    @DisplayName("WEB-07 Eliminar producto del carrito")
    void removeProductFromCart() {
        inventory.addBackpackToCart();
        CartPage cart = inventory.openCart();
        cart.removeBackpack();
        assertTrue(cart.isEmpty());
    }

    @Test
    @DisplayName("WEB-20 Abrir detalle de Sauce Labs Backpack")
    void viewProductDetails() {
        ProductDetailsPage details = inventory.openBackpackDetails();

        assertEquals("Sauce Labs Backpack", details.productName());
        assertEquals("$29.99", details.productPrice());
        assertTrue(details.hasDescription());
        assertTrue(details.canBeAddedToCart());
    }

    @Test
    @DisplayName("WEB-09 Completar compra con datos validos")
    void completePurchase() {
        inventory.addBikeLightToCart();
        assertEquals("1", inventory.cartCount());

        CheckoutInformationPage information = inventory.openCart().checkout();
        CheckoutOverviewPage overview = information.completeForm("Ramiro", "Estudiante", "0000");

        assertEquals("Checkout: Overview", overview.title());
        assertEquals("Sauce Labs Bike Light", overview.productName());

        CheckoutCompletePage complete = overview.finishPurchase();
        assertEquals("Thank you for your order!", complete.confirmationMessage());
        assertTrue(complete.hasBackToProductsButton());
    }
}
