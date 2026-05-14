package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private static final Logger logger =
            LogManager.getLogger(
                    InventoryPage.class
            );

    private WebDriver driver;

    private By productHeader =
            By.cssSelector(".title");

    public InventoryPage(WebDriver driver) {

        this.driver = driver;
    }

    public boolean isProductHeaderDisplayed() {

        logger.info(
                "Validating Inventory Page Header"
        );

        return !driver.findElements(
                productHeader
        ).isEmpty();
    }

    public String getHeaderText() {

        logger.info(
                "Fetching Inventory Header Text"
        );

        return driver.findElement(productHeader)
                .getText();
    }
}