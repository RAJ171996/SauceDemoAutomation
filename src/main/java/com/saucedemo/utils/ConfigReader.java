package com.saucedemo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static final Logger logger = LogManager.getLogger(ConfigReader.class);

	private static Properties properties;

	static {

		try {

			logger.info("Loading Configuration Properties");

			FileInputStream file = new FileInputStream("src/main/resources/config.properties");

			properties = new Properties();

			properties.load(file);

			file.close();

			logger.info("Configuration Loaded Successfully");

		} catch (IOException e) {

			logger.error("Failed To Load Config File", e);

			throw new RuntimeException("Configuration properties file could not be found or loaded.", e);
		}
	}

	public static String getProperty(String key) {

		logger.info("Fetching Property: {}", key);

		return properties.getProperty(key);
	}
}