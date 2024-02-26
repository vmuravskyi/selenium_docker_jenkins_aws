package com.automation.framework.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.automation.framework.pages.BasePage;

public class RegistrationPage extends BasePage {

	private By firstNameInput = By.id("firstName");
	private By lastNameInput = By.id("lastName");
	private By emailInput = By.id("email");
	private By passwordInput = By.id("password");
	private By streetInput = By.name("street");
	private By cityInput = By.name("city");
	private By stateDropdown = By.id("inputState");
	private By zipInput = By.name("zip");
	private By registerButton = By.id("register-btn");

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		WebElement registerButtonElement = driver.findElement(this.registerButton);
		this.wait.until(ExpectedConditions.visibilityOf(registerButtonElement));
		return registerButtonElement.isDisplayed();
	}

	public RegistrationPage enterFirstName(String firstName) {
		driver.findElement(firstNameInput).sendKeys(firstName);
		return this;
	}

	public RegistrationPage enterLastName(String lastName) {
		driver.findElement(lastNameInput).sendKeys(lastName);
		return this;
	}

	public RegistrationPage enterEmail(String email) {
		driver.findElement(emailInput).sendKeys(email);
		return this;
	}

	public RegistrationPage enterPassword(String password) {
		driver.findElement(passwordInput).sendKeys(password);
		return this;
	}

	public RegistrationPage enterStreet(String street) {
		driver.findElement(streetInput).sendKeys(street);
		return this;
	}

	public RegistrationPage enterCity(String city) {
		driver.findElement(cityInput).sendKeys(city);
		return this;
	}

	public RegistrationPage selectState(String state) {
		new Select(driver.findElement(stateDropdown)).selectByValue(state);
		return this;
	}

	public RegistrationPage enterZip(String zip) {
		driver.findElement(zipInput).sendKeys(zip);
		return this;
	}

	public RegistrationConfirmationPage clickRegisterButton() {
		driver.findElement(registerButton).click();
		return new RegistrationConfirmationPage(driver);
	}

}
