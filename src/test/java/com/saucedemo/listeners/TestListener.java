package com.saucedemo.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.saucedemo.reports.ExtentManager;
import com.saucedemo.tests.BaseTest;
import com.saucedemo.utils.ScreenshotUtils;

import io.qameta.allure.Attachment;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.IOException;

public class TestListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.getInstance();

	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest extentTest = extent.createTest(result.getName());

		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.get().log(Status.FAIL, result.getThrowable());

		String screenshotPath = ScreenshotUtils.captureScreenshot(BaseTest.getDriver(), result.getName());

		// Extent Screenshot
		try {
			test.get().addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Allure Screenshot
		attachScreenshotToAllure(screenshotPath);
	}

	@Attachment(value = "Failure Screenshot", type = "image/png")
	public byte[] attachScreenshotToAllure(String path) {

		try (FileInputStream fis = new FileInputStream(path)) {

			return fis.readAllBytes();

		} catch (IOException e) {

			return new byte[0];
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}