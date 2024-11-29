package com.automation.framework.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.framework.pages.BasePage;

public class SelectFlightsPage extends BasePage {

	private By confirmFlights = By.id("confirm-flights");

	public SelectFlightsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		WebElement confirmFlightsElement = driver.findElement(this.confirmFlights);
		this.wait.until(ExpectedConditions.visibilityOf(confirmFlightsElement));
		return confirmFlightsElement.isDisplayed();
	}

	public FlightsConfirmationPage clickConfirmFlights() {
		driver.findElement(confirmFlights).click();
		return new FlightsConfirmationPage(driver);
	}

}
