package com.automation.selenium_docker.vendorPortalApp;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseClass {
	
	protected final WebDriver driver;
	protected final WebDriverWait wait;
	protected static final Logger log = LoggerFactory.getLogger(BaseClass.class);
	
	public BaseClass(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}
	
	public abstract boolean isAt();
	
	
}
