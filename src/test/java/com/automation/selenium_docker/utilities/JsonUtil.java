package com.automation.selenium_docker.utilities;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
//	public static VendorPortalTestData getTestData(String path) {
//		
//		try(InputStream stream = ResourceLoader.getResource(path)) {
//			return mapper.readValue(stream, VendorPortalTestData.class);
//		} catch (Exception e) {
//			log.error("Unable to read test data {}", path, e);
//		}
//		return null;
//	}
//	
//	public static FlightReservationTestData getPassengerData(String path) {
//		
//		try(InputStream stream = ResourceLoader.getResource(path)) {
//			return mapper.readValue(stream, FlightReservationTestData.class);
//		} catch (Exception e) {
//			log.error("Unable to read test data {}", path, e);
//		}
//		return null;
//	}
	
	public static <T> T getTestData(String path, Class<T> type) {
		
		try(InputStream stream = ResourceLoader.getResource(path)) {
			return mapper.readValue(stream, type);
		} catch (Exception e) {
			log.error("Unable to read test data {}", path, e);
		}
		return null;
	}
}
