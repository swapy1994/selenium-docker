package com.automation.selenium_docker.vendorPortalApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.selenium_docker.pages.AbstractPage;

public class DashboardPage extends AbstractPage{
	
	@FindBy(xpath = "//h1[text()='Dashboard']")
	private WebElement dashBoardTextElement;
	
	@FindBy(id = "monthly-earning")
	private WebElement monthlyEarningsElement;
	
	@FindBy(id = "annual-earning")
	private WebElement annualEarningsElement;
	
	@FindBy(id = "profit-margin")
	private WebElement profitMarginElement;
	
	@FindBy(id = "available-inventory")
	private WebElement inventoryElement;
	
	@FindBy(css = "#dataTable_filter input")
	private WebElement searchElementElement;
	
	@FindBy(id = "dataTable_info")
	private WebElement searchResultInfoElement;
	
	@FindBy(css = "#userDropdown img")
	private WebElement userProfilePictureElement;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutLink;
	
	@FindBy(css = "#logoutModal a")
	private WebElement logoutButton;
	
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		// TODO Auto-generated method stub
		wait.until(ExpectedConditions.visibilityOf(dashBoardTextElement));
		return dashBoardTextElement.isDisplayed();
	}
	
	public String getMonthlyEarning() {
		return monthlyEarningsElement.getText();
	}
	
	public String getAnnualEarning() {
		return annualEarningsElement.getText();
	}
	
	public String getProfitMargin() {
		return profitMarginElement.getText();
	}
	
	public String getAvailableInventory() {
		return inventoryElement.getText();
	}
	
	
	public int searchOrderHistoryBy(String keyword) {
		searchElementElement.sendKeys(keyword);
		String[] searchResultText = searchResultInfoElement.getText().split(" ");
		int searchCount = Integer.parseInt(searchResultText[5]);
		log.info("Result count: {}", searchCount);
		return searchCount;
	}
	
	
	public void logout() {
		userProfilePictureElement.click();
		wait.until(ExpectedConditions.visibilityOf(logoutLink));
		logoutLink.click();
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
		logoutButton.click();
	}
}
