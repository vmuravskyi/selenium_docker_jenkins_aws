package com.automation.framework.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.framework.pages.BasePage;

public class FlightsConfirmationPage extends BasePage {

	private By flightsConfirmationSection = By.id("flights-confirmation-section");

	public FlightsConfirmationPage(WebDriver driver) {
		super(driver);
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(flightsConfirmationSection),
			"Flights Confirmation"));
	}

	@Override
	public boolean isAt() {
		WebElement flightsConfirmationSectionElement = driver.findElement(this.flightsConfirmationSection);
		this.wait.until(ExpectedConditions.visibilityOf(flightsConfirmationSectionElement));
		return flightsConfirmationSectionElement.isDisplayed();
	}

	public WebElement getFlightsConfirmationSection() {
		return driver.findElement(flightsConfirmationSection);
	}

}
