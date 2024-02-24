package com.automation.framework.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.framework.pages.BasePage;

public class RegistrationConfirmationPage extends BasePage {

	private By goToFlightSearch = By.id("go-to-flights-search");

	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}

	public FlightSearchPage clickGoToFlightSearch() {
		driver.findElement(goToFlightSearch).click();
		return new FlightSearchPage(driver);
	}

}
