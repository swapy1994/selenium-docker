package com.automation.selenium_docker.vendorPortalApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseClass{
	
	@FindBy(id = "username")
	private WebElement usernameInput;
	
	@FindBy(id = "password")
	private WebElement passwordInput;
	
	@FindBy(id = "login")
	private WebElement loginButton;
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	@Override
	public boolean isAt() {
		wait.until(ExpectedConditions.visibilityOf(usernameInput));
		return usernameInput.isDisplayed();
	}
	
	public void enterCredentials(String userName, String password) {
		usernameInput.sendKeys(userName);
		passwordInput.sendKeys(password);
	}
	
	public void login() {
		loginButton.click();
	}

}
