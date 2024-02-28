package com.automation.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceLoader.class);

	public static InputStream getResource(String path) throws IOException {
		LOGGER.info("Reading resource from location [{}]", path);
		InputStream io = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		if (Objects.nonNull(io)) {
			return io;
		}
		return Files.newInputStream(Path.of(path));
	}

}
