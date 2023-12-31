package com.automation.selenium_docker.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.selenium_docker.pages.AbstractPage;

public class RegistrationPage extends AbstractPage {
	
	@FindBy(id = "firstName")
	private WebElement firstNameInput;
	
	@FindBy(id = "lastName")
	private WebElement lastNameInput;
	
	@FindBy(id = "email")
	private WebElement emailInput;
	
	@FindBy(id = "password")
	private WebElement passwordInput;
	
	@FindBy(name = "street")
	private WebElement streetInput;
	
	@FindBy(name = "city")
	private WebElement cityInput;
	
	@FindBy(id = "firstName")
	private WebElement stateInput;
	
	@FindBy(name = "zip")
	private WebElement zipCodeInput;
	
	@FindBy(id = "register-btn")
	private WebElement registerButton;
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(firstNameInput));
		return firstNameInput.isDisplayed();
	}
	
	public void enterUserDetails(String firstName, String LastName) {
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(LastName);
	}
	
	public void enterUSerCredentials(String email, String password) {
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
	}
	
	public void enterAddress(String street, String city, String zip_code) {
		streetInput.sendKeys(street);
		cityInput.sendKeys(city);
		zipCodeInput.sendKeys(zip_code);
	}
	
	public void register() {
		registerButton.click();
	}
	
}
