package com.automation.selenium_docker.flightreservation.model;

public record FlightReservationTestData(String firstname,
									    String lastName, 
									    String email, 
									    String password,
									    String street, 
									    String city, 
									    String zip,
									    String tripType,
									    String passengerCount,
									    String departureLocation,
									    String arrivalLocation,
									    String serviceClass,
									    String expectedPrice  ) {

}
