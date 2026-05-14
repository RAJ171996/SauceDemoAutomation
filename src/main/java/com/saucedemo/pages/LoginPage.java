package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	private WebDriver driver;

	private By usernameField = By.id("user-name");

	private By passwordField = By.id("password");

	private By loginButton = By.id("login-button");

	private By errorMessage = By.cssSelector("[data-test='error']");

	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}

	public void enterUsername(String username) {

		logger.info("Entering Username: {}", username);

		driver.findElement(usernameField).sendKeys(username);
	}

	public void enterPassword(String password) {

		logger.info("Entering Password");

		driver.findElement(passwordField).sendKeys(password);
	}

	public void clickLogin() {

		logger.info("Clicking Login Button");

		driver.findElement(loginButton).click();
	}

	public void login(String username, String password) {

		logger.info("Performing Login");

		enterUsername(username);

		enterPassword(password);

		clickLogin();
	}

	public String getErrorMessageText() {

		logger.warn("Fetching Login Error Message");

		return driver.findElement(errorMessage).getText();
	}
}