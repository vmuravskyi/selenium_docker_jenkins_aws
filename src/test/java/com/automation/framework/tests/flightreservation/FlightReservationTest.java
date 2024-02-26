package com.automation.framework.tests.flightreservation;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.framework.Assertions;
import com.automation.framework.pages.flightreservation.FlightsConfirmationPage;
import com.automation.framework.pages.flightreservation.RegistrationPage;
import com.automation.framework.tests.BaseTest;

public class FlightReservationTest extends BaseTest {

	private String noOfPassengers;
	private String expectedPrice;

	@BeforeMethod
	@Parameters({"noOfPassengers", "expectedPrice"})
	public void setup(String noOfPassengers, String expectedPrice) {
		this.noOfPassengers = noOfPassengers;
		this.expectedPrice = expectedPrice;
	}

	@Test
	public void reserveFlightTest() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
		Assert.assertTrue(registrationPage.isAt());

		FlightsConfirmationPage flightsConfirmationPage = registrationPage.enterFirstName("first name")
			.enterLastName("last name")
			.enterEmail("email@gmail.com")
			.enterPassword("password")
			.enterStreet("street")
			.enterCity("city")
			.selectState("Delaware")
			.enterZip("12345")
			.clickRegisterButton()
			.clickGoToFlightSearch()
			.chooseNumberOfPassengers(noOfPassengers)
			.clickSearchFlights()
			.clickConfirmFlights();

		Assertions assertions = new Assertions();
		assertions.verifyFlightConfirmationNumberExists(flightsConfirmationPage);
		assertions.verifyTotalPriceIsEqualTo(flightsConfirmationPage, expectedPrice);
	}

}
