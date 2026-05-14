package com.saucedemo.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

	public static String captureScreenshot(WebDriver driver, String testName) {

		String dir = "screenshots";

		new File(dir).mkdirs(); // auto create folder

		String screenshotPath = dir + "/" + testName + ".png";

		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destinationFile = new File(screenshotPath);

		try {
			FileUtils.copyFile(sourceFile, destinationFile);
		} catch (IOException e) {
			throw new RuntimeException("Failed to capture screenshot", e);
		}

		return screenshotPath;
	}
}