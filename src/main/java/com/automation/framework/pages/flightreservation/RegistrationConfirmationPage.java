package com.automation.framework.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.framework.pages.BasePage;

public class RegistrationConfirmationPage extends BasePage {

	private By goToFlightSearch = By.id("go-to-flights-search");

	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		WebElement goToFlightSearchElement = driver.findElement(this.goToFlightSearch);
		this.wait.until(ExpectedConditions.visibilityOf(goToFlightSearchElement));
		return goToFlightSearchElement.isDisplayed();
	}

	public FlightSearchPage clickGoToFlightSearch() {
		driver.findElement(goToFlightSearch).click();
		return new FlightSearchPage(driver);
	}

}
