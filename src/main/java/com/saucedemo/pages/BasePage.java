package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected Logger logger =
            LogManager.getLogger(this.getClass());

    public BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );
    }

    // Wait Until Element Visible
    protected WebElement waitForVisibility(By locator) {

        logger.info(
                "Waiting For Element Visibility: {}",
                locator
        );

        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(locator)
        );
    }

    // Click Element
    protected void click(By locator) {

        logger.info(
                "Clicking Element: {}",
                locator
        );

        waitForVisibility(locator).click();
    }

    // Enter Text
    protected void type(By locator, String text) {

        logger.info(
                "Entering Text Into Element: {}",
                locator
        );

        WebElement element =
                waitForVisibility(locator);

        element.clear();

        element.sendKeys(text);
    }

    // Get Text
    protected String getText(By locator) {

        logger.info(
                "Getting Text From Element: {}",
                locator
        );

        return waitForVisibility(locator)
                .getText();
    }

    // Check Displayed
    protected boolean isDisplayed(By locator) {

        logger.info(
                "Checking Element Displayed: {}",
                locator
        );

        return !driver
                .findElements(locator)
                .isEmpty();
    }
}
