package com.automation.selenium_docker.utilities;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    A simple utility to read file.
    first we check classpath. if found , it is used.
    if not, then we check the file system.
  
 */


public class ResourceLoader {
	
	private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);
	
	public static InputStream getResource(String path) throws Exception{
		log.info("Reading resource from location: {}", path);
		InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		if(Objects.nonNull(stream)) {
			return stream;
		}
		return Files.newInputStream(Path.of(path));
	}
	
}
