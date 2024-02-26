package com.automation.framework.pages.vendorportal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.framework.pages.BasePage;

public class LoginPage extends BasePage {

	private By userInput = By.id("username");
	private By passwordInput = By.id("password");
	private By loginButton = By.id("login");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		WebElement userInputElement = driver.findElement(userInput);
		wait.until(ExpectedConditions.visibilityOf(userInputElement));
		return userInputElement.isDisplayed();
	}

	public void goTo(String url){
		this.driver.get(url);
	}

	public DashboardPage login(String username, String password) {
		driver.findElement(userInput).sendKeys(username);
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(loginButton).click();
		return new DashboardPage(driver);
	}

}
