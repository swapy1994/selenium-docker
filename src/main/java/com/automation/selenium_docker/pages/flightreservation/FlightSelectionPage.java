package com.automation.selenium_docker.pages.flightreservation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.selenium_docker.pages.AbstractPage;

import io.netty.util.internal.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {
	
	
	@FindBy(name = "departure-flight")
	private List<WebElement> departureFlightOptions;
	
	@FindBy(name = "arrival-flight")
	private List<WebElement> arrivalFlightOptions;
	
	@FindBy(id = "confirm-flights")
	private WebElement confirmFlightButton;
	
	public FlightSelectionPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(confirmFlightButton));
		return confirmFlightButton.isDisplayed();
	}
	
	public void selectFlights() {
		int random = ThreadLocalRandom.current().nextInt(0, departureFlightOptions.size());
		departureFlightOptions.get(random).click();
		arrivalFlightOptions.get(random).click();	
	}
	
	public void confirmFlight() {
		confirmFlightButton.click();
	}
}
