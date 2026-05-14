package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	private By usernameField = By.id("user-name");

	private By passwordField = By.id("password");

	private By loginButton = By.id("login-button");

	private By errorMessage = By.cssSelector("[data-test='error']");

	public LoginPage(WebDriver driver) {

		super(driver);
	}

	public void login(String username, String password) {

		type(usernameField, username);

		type(passwordField, password);

		click(loginButton);
	}

	public String getErrorMessageText() {

		return getText(errorMessage);
	}
}