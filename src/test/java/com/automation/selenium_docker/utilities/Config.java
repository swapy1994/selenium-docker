package com.automation.selenium_docker.utilities;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	private static Logger log =LoggerFactory.getLogger(Config.class);
	private static final String DEFAULT_PROPERTIES = "config/default.properties";
	private static Properties properties;
	
	
	public static void initialize() {
		
		//Load Default properties
		properties = readProperties();
		
		//Check for any override i.e. if there is any input from command line
		for(String key: properties.stringPropertyNames()) {
			if(System.getProperties().containsKey(key)) {
				properties.setProperty(key, System.getProperty(key));
			}
		}
		
		//print
		log.info("Test properties");
		log.info("<<==============================================================>>");
		for(String key: properties.stringPropertyNames()) {
			log.info("{}:{}", key, properties.getProperty(key));
		}
		log.info("<<==============================================================>>");
		
	}
	
	
	//To fetch the properties in another class we use below method
	public static String get(String key) {
		return properties.getProperty(key);		
	}
	
	public static Properties readProperties() {
		Properties properties = new Properties();
		try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
			properties.load(stream);
		} catch (Exception e) {
			log.error("Unable to read the property file {}", DEFAULT_PROPERTIES, e);
		}
		return properties;
		
	}
}
