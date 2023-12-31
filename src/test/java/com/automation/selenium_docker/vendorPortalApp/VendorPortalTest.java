package com.automation.selenium_docker.vendorPortalApp;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.selenium_docker.basepackege.AbstractTest;
import com.automation.selenium_docker.utilities.Config;
import com.automation.selenium_docker.utilities.Constants;
import com.automation.selenium_docker.utilities.JsonUtil;
import com.automation.selenium_docker.vendorPortalApp.model.VendorPortalTestData;

public class VendorPortalTest extends AbstractTest {
	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private VendorPortalTestData vpTestData;
	@BeforeTest
	@Parameters("testDataPath")
	public void setPageObjects(String testDataPath) {
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		vpTestData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
	}
	
	@Test
	public void loginTest() {
		loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
		Assert.assertTrue(loginPage.isAt());
		loginPage.enterCredentials(vpTestData.username(), vpTestData.password());
		loginPage.login();
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void dashBoardTest() {
		Assert.assertTrue(dashboardPage.isAt());
		
		Assert.assertEquals(dashboardPage.getMonthlyEarning(), vpTestData.monthlyEarning());
		Assert.assertEquals(dashboardPage.getAnnualEarning(), vpTestData.annualEarnings());
		Assert.assertEquals(dashboardPage.getProfitMargin(), vpTestData.profitMargin());
		
		Assert.assertEquals(dashboardPage.getAvailableInventory(), vpTestData.availableInventory());
		Assert.assertEquals(dashboardPage.searchOrderHistoryBy(vpTestData.searchKeyword()), vpTestData.searchResultsCount());
		
	}
	
	@Test(dependsOnMethods = "dashBoardTest")
	public void logoutTest() {
		dashboardPage.logout();
		Assert.assertTrue(loginPage.isAt());
	}

}
