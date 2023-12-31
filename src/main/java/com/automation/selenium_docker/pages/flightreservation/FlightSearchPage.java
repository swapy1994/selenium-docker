package com.automation.selenium_docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.selenium_docker.pages.AbstractPage;

public class FlightSearchPage extends AbstractPage {
	
	@FindBy(id = "oneway")
	private WebElement oneWayButton;
	
	@FindBy(id = "twoway")
	private WebElement twowayButton;
	
	@FindBy(id = "passengers")
	private WebElement passengersInput;
	
	@FindBy(id = "depart-from")
	private WebElement startLoactionInput;
	
	@FindBy(id = "arrive-in")
	private WebElement arrivalLocationInput;
	
	@FindBy(id = "service-class1")
	private WebElement economyClassButton;
	
	@FindBy(id = "service-class2")
	private WebElement firstClassButton;
	
	@FindBy(id = "service-class3")
	private WebElement busineessClassButton;
	
	@FindBy(id = "search-flights")
	private WebElement search_flightsButton;
	
	public FlightSearchPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(search_flightsButton));
		return search_flightsButton.isDisplayed();
	}
	
	public void selectTripType(String tripType) {
		if(tripType == "one way") {
			oneWayButton.click();
		}else {
			twowayButton.click();
		}
	}
	
	public void selectNumberOfPassengers(String noOfPassengers) {
		Select passenger = new Select(passengersInput);
		passenger.selectByValue(noOfPassengers);
		log.info("Number of passengers: {}", noOfPassengers);
	}
	
	public void selectDepartureLocation(String departureLocation) {
		Select startLoaction = new Select(startLoactionInput);
		startLoaction.selectByValue(departureLocation);
	}
	
	public void selectArrivealLocation(String arrivalLocation) {
		Select startLoaction = new Select(arrivalLocationInput);
		startLoaction.selectByValue(arrivalLocation);
	}
	
	public void selectServiceClass(String serviceClass) {
		if(serviceClass.equalsIgnoreCase("Economy")) {
			economyClassButton.click();
		}else if(serviceClass.equalsIgnoreCase("First")) {
			firstClassButton.click();
		}else {
			busineessClassButton.click();
		}
	}
	
	public void searchFlights() {
		search_flightsButton.click();
	}

}
