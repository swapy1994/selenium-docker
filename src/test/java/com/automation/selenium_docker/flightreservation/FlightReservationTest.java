package com.automation.selenium_docker.flightreservation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.selenium_docker.basepackege.AbstractTest;
import com.automation.selenium_docker.flightreservation.model.FlightReservationTestData;
import com.automation.selenium_docker.pages.flightreservation.FlightConfirmationPage;
import com.automation.selenium_docker.pages.flightreservation.FlightSearchPage;
import com.automation.selenium_docker.pages.flightreservation.FlightSelectionPage;
import com.automation.selenium_docker.pages.flightreservation.RegistrationConfirmationPage;
import com.automation.selenium_docker.pages.flightreservation.RegistrationPage;
import com.automation.selenium_docker.utilities.Config;
import com.automation.selenium_docker.utilities.Constants;
import com.automation.selenium_docker.utilities.JsonUtil;


public class FlightReservationTest extends AbstractTest{
	private FlightReservationTestData passengerData;
	
	
	@BeforeTest
	@Parameters({"testDataPath"})
	public void setParameters( String testDataPath) {
		passengerData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
	}
	
	@Test
	public void userRegistrationTest() {
		RegistrationPage regPage = new RegistrationPage(driver);
		regPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
		Assert.assertTrue(regPage.isAt());
		
		regPage.enterUserDetails(passengerData.firstname(), passengerData.lastName());
		regPage.enterUSerCredentials(passengerData.email(), passengerData.password());
		regPage.enterAddress(passengerData.street(), passengerData.city(), passengerData.zip());
		regPage.register();
	}
	
	@Test(dependsOnMethods = "userRegistrationTest")
	public void registrationConfirmationTest() {
		RegistrationConfirmationPage rcPage = new RegistrationConfirmationPage(driver);
		Assert.assertTrue(rcPage.isAt());
		
		rcPage.goToFlightSearch();
	}
	
	@Test(dependsOnMethods = "registrationConfirmationTest")
	public void flightSearchTest() {
		FlightSearchPage fsPage = new FlightSearchPage(driver);
		Assert.assertTrue(fsPage.isAt());
		
		fsPage.selectTripType(passengerData.tripType());
		fsPage.selectNumberOfPassengers(passengerData.passengerCount());
		fsPage.selectDepartureLocation(passengerData.departureLocation());
		fsPage.selectArrivealLocation(passengerData.arrivalLocation());
		fsPage.selectServiceClass(passengerData.serviceClass());
		fsPage.searchFlights();
	}
	
	@Test(dependsOnMethods = "flightSearchTest")
	public void flightSelectionTest() {
		FlightSelectionPage flightSelectPage = new FlightSelectionPage(driver);
		Assert.assertTrue(flightSelectPage.isAt());
		flightSelectPage.selectFlights();
		flightSelectPage.confirmFlight();
	}
	
	@Test(dependsOnMethods = "flightSelectionTest")
	public void flightReservationConfirmationTest() {
		FlightConfirmationPage fcPage = new FlightConfirmationPage(driver);
		Assert.assertTrue(fcPage.isAt());
		fcPage.getConfirmationId();
		Assert.assertEquals(fcPage.getPrice(), passengerData.expectedPrice());
	}
	
}
