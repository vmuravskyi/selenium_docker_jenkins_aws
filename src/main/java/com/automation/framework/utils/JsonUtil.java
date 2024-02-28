package com.automation.framework.utils;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
	public static final ObjectMapper mapper = new ObjectMapper();

	public static <T> T getTestData(String path, Class<?> clazz) {
		try (InputStream resource = ResourceLoader.getResource(path)) {
			return (T) mapper.readValue(resource, clazz);
		} catch (IOException e) {
			LOGGER.error("Could not read test data file [{}]", path);
			throw new RuntimeException(e);
		}
	}

}
