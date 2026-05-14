package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.ExcelUtils;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

	@DataProvider(name = "loginData")
	public Object[][] loginDataProvider() {

		return ExcelUtils.getTestData("LoginData");
	}

	@Test(dataProvider = "loginData")
	public void testLogin(String username, String password) {

		LoginPage loginPage = new LoginPage(getDriver());

		loginPage.login(username, password);

		// Negative Scenario
		if (username.equals("locked_out_user")) {

			Assert.assertTrue(loginPage.getErrorMessageText().contains("Epic sadface"));

		} else {

			// Positive Scenario
			InventoryPage inventoryPage = new InventoryPage(getDriver());

			Assert.assertTrue(inventoryPage.isProductHeaderDisplayed());

//			// Intentional Failure
//			if (username.equals("standard_user")) {
//
//				Assert.fail("Intentional Failure To Test Screenshot Capture");
//			}
		}
	}
}