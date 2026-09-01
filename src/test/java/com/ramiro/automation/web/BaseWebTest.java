package com.ramiro.automation.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.ramiro.automation.pages.InventoryPage;
import com.ramiro.automation.pages.LoginPage;

public abstract class BaseWebTest {
    protected WebDriver driver;
    protected InventoryPage inventory;

    @BeforeEach
    void prepareBrowser() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--window-size=1365,768");

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        if (headless) {
            options.addArguments("--headless=new");
        }

        driver = new EdgeDriver(options);
        driver.get("https://www.saucedemo.com/");

        // Cada prueba inicia sin sesion ni carrito de una ejecucion anterior.
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.clear(); window.sessionStorage.clear(); return true;");
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        // El login es preparacion y no se cuenta como escenario de prueba.
        inventory = new LoginPage(driver).login("standard_user", "secret_sauce");
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
