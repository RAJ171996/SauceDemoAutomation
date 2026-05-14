package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

	private By productHeader = By.cssSelector(".title");

	public InventoryPage(WebDriver driver) {

		super(driver);
	}

	public boolean isProductHeaderDisplayed() {

		return isDisplayed(productHeader);
	}

	public String getHeaderText() {

		return getText(productHeader);
	}
}