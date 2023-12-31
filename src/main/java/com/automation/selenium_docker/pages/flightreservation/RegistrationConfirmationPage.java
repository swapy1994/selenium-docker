package com.automation.selenium_docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.selenium_docker.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {
	
	//private WebDriver driver;
	
	@FindBy(id="go-to-flights-search")
	private WebElement flightSearchButton;
	
	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(flightSearchButton));
		return flightSearchButton.isDisplayed();
	}
	
	public void goToFlightSearch() {
		flightSearchButton.click();
	}
	
}
