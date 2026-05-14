package com.saucedemo.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {

		if (extent == null) {

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");

			sparkReporter.config().setReportName("SauceDemo Automation Report");

			sparkReporter.config().setDocumentTitle("Automation Test Results");

			extent = new ExtentReports();

			extent.attachReporter(sparkReporter);
		}

		return extent;
	}
}