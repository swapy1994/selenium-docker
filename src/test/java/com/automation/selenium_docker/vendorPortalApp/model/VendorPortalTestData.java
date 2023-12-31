package com.automation.selenium_docker.vendorPortalApp.model;

public record VendorPortalTestData(String username,
								   String password, 
								   String monthlyEarning, 
								   String annualEarnings,
								   String profitMargin, 
								   String availableInventory, 
								   String searchKeyword, 
								   int searchResultsCount) {

}
