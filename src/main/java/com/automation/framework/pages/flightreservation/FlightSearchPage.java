package com.automation.framework.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.framework.pages.BasePage;

public class FlightSearchPage extends BasePage {

	private By oneWayRadioButton = By.id("oneway");
	private By roundTripRadioButton = By.id("twoway");
	private By passengersDropdown = By.id("passengers");
	private By departingFromDropdown = By.id("depart-from");
	private By arrivingInDropdown = By.id("arrive-in");
	private By serviceClassEconomy = By.id("service-class1");
	private By serviceClassBusiness = By.id("service-class3");
	private By searchFlightsButton = By.id("search-flights");

	public FlightSearchPage(WebDriver driver) {
		super(driver);
	}

	public FlightSearchPage chooseNumberOfPassengers(String input) {
		WebElement passengersDropdown = driver.findElement(this.passengersDropdown);
		wait.until(ExpectedConditions.elementToBeClickable(passengersDropdown));
		new Select(driver.findElement(this.passengersDropdown)).selectByVisibleText(input);
		return this;
	}

	public SelectFlightsPage clickSearchFlights() {
		driver.findElement(searchFlightsButton).click();
		return new SelectFlightsPage(driver);
	}

}
