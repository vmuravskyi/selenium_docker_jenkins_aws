package com.automation.framework.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.framework.pages.BasePage;

public class SelectFlightsPage extends BasePage {

	private By confirmFlights = By.id("confirm-flights");

	public SelectFlightsPage(WebDriver driver) {
		super(driver);
	}

	public FlightsConfirmationPage clickConfirmFlights() {
		driver.findElement(confirmFlights).click();
		return new FlightsConfirmationPage(driver);
	}

}
