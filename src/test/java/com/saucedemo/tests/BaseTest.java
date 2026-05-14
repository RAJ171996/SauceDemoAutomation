package com.saucedemo.tests;

import com.saucedemo.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

	private static final Logger logger = LogManager.getLogger(BaseTest.class);

	private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	@BeforeMethod
	public void setUp() {

		String browserType = System.getProperty("browser", "chrome");

		// Default Headless = TRUE for CI/CD
		String headless = System.getProperty("headless", "true");

		WebDriver driverInstance;

		logger.info("Launching Browser: {}", browserType);

		logger.info("Headless Mode: {}", headless);

		if (browserType.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();

			if (headless.equalsIgnoreCase("true")) {

				options.addArguments("--headless=new");
			}

			// Recommended for Linux CI/CD runners
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");

			driverInstance = new ChromeDriver(options);

		} else if (browserType.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions options = new FirefoxOptions();

			if (headless.equalsIgnoreCase("true")) {

				options.addArguments("-headless");
			}

			driverInstance = new FirefoxDriver(options);

		} else if (browserType.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();

			EdgeOptions options = new EdgeOptions();

			if (headless.equalsIgnoreCase("true")) {

				options.addArguments("--headless=new");
			}

			// Recommended for Linux CI/CD runners
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");

			driverInstance = new EdgeDriver(options);

		} else {

			logger.error("Unsupported browser: {}", browserType);

			throw new IllegalArgumentException("Browser option not supported: " + browserType);
		}

		tlDriver.set(driverInstance);

		logger.info("Maximizing Browser Window");

		getDriver().manage().window().maximize();

		long timeout = Long.parseLong(ConfigReader.getProperty("timeout"));

		logger.info("Applying Implicit Wait: {} seconds", timeout);

		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

		String url = ConfigReader.getProperty("url");

		logger.info("Opening Application URL: {}", url);

		getDriver().get(url);

		logger.info("Application Opened Successfully");
	}

	public static WebDriver getDriver() {

		return tlDriver.get();
	}

	@AfterMethod
	public void tearDown() {

		if (getDriver() != null) {

			logger.info("Closing Browser");

			getDriver().quit();

			tlDriver.remove();

			logger.info("Browser Closed Successfully");
		}
	}
}