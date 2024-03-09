package com.automation.framework.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {

	private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);
	private static final String DEFAULT_PROPERTIES = "src/test/resources/config/default.properties";
	private static Properties properties;

	public static void initialize() {
		// load default properties
		properties = loadProperties();

		// check for any overrides (e.g. mvn test -Dkey=value)
		for (String key : properties.stringPropertyNames()) {
			if (System.getProperties().containsKey(key)) {
				properties.setProperty(key, System.getProperty(key));
			}
		}

		// log properties
		LOGGER.info("Running with properties");
		properties.stringPropertyNames().forEach(key -> LOGGER.info("{}={}", key, properties.getProperty(key)));
	}

	private static Properties loadProperties() {
		Properties properties = new Properties();
		try {
			properties.load(ResourceLoader.getResource(DEFAULT_PROPERTIES));
		} catch (IOException e) {
			LOGGER.error("Unable to read property file {}", DEFAULT_PROPERTIES, e);
		}
		return properties;
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

}
