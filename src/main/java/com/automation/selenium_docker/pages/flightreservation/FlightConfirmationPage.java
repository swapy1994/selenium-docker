package com.automation.selenium_docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.selenium_docker.pages.AbstractPage;

public class FlightConfirmationPage extends AbstractPage{
	
	
	@FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
	private WebElement flightCOnfirmationElement;
	
	@FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
	private WebElement totalPriceElement;
	
	
	public FlightConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(flightCOnfirmationElement));
		return flightCOnfirmationElement.isDisplayed();
	}
	
	public String getConfirmationId() {
		String confirmationNo = flightCOnfirmationElement.getText();
		log.info("Flight Confirmation Number : {}", confirmationNo);
		return confirmationNo;
	}
	
	public String getPrice() {
		String totalPrice = totalPriceElement.getText();
		log.info("Total price : {}", totalPrice);
		return totalPrice;
	}

}
