package com.automation.framework.pages.vendorportal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.framework.pages.BasePage;

public class DashboardPage extends BasePage {

	private By monthlyEarning = By.id("monthly-earning");

	private By annualEarning = By.id("annual-earning");

	private By profitMargin = By.id("profit-margin");

	private By availableInventory = By.id("available-inventory");

	private By search = By.cssSelector("#dataTable_filter input");

	private By searchResultsCount = By.id("dataTable_info");

	private By userProfilePicture = By.cssSelector("img.img-profile");

	private By logoutLink = By.linkText("Logout"); // prefer id / name / css

	private By modalLogoutButton = By.cssSelector("#logoutModal a");

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isAt() {
		WebElement monthlyEarningElement = driver.findElement(this.monthlyEarning);
		this.wait.until(ExpectedConditions.visibilityOf(monthlyEarningElement));
		return monthlyEarningElement.isDisplayed();
	}

	public String getMonthlyEarning() {
		WebElement monthlyEarningElement = driver.findElement(monthlyEarning);
		return monthlyEarningElement.getText();
	}

	public String getAnnualEarning() {
		WebElement annualEarningElement = driver.findElement(annualEarning);
		return annualEarningElement.getText();
	}

	public String getProfitMargin() {
		WebElement profitMarginElement = driver.findElement(profitMargin);
		return profitMarginElement.getText();
	}

	public String getAvailableInventory() {
		WebElement availableInventoryElement = driver.findElement(availableInventory);
		return availableInventoryElement.getText();
	}

	public void searchOrderHistoryBy(String keyword) {
		WebElement searchInput = driver.findElement(search);
		searchInput.sendKeys(keyword);
	}

	/*
		Showing 1 to 10 of 32 entries (filtered from 99 total entries)
		arr[0] = "Showing"
		arr[1] = "1"
		arr[2] = "to"
		arr[3] = "10"
		arr[4] = "of"
		arr[5] = "32"
		...
		...
	 */
	public int getSearchResultsCount() {
		WebElement searchResultsCountElement = driver.findElement(searchResultsCount);
		String resultsText = searchResultsCountElement.getText();
		String[] arr = resultsText.split(" ");
		// if we do not have 5th item, it would throw exception.
		// we would want our test to fail anyway in that case!
		int count = Integer.parseInt(arr[5]);
		LOGGER.info("Results count: {}", count);
		return count;
	}

	public void logout() {
		WebElement userProfilePictureElement = driver.findElement(userProfilePicture);
		userProfilePictureElement.click();
		WebElement logoutLinkElement = driver.findElement(logoutLink);
		wait.until(ExpectedConditions.visibilityOf(logoutLinkElement));
		logoutLinkElement.click();
		WebElement modalLogoutButtonElement = driver.findElement(modalLogoutButton);
		wait.until(ExpectedConditions.visibilityOf(modalLogoutButtonElement));
		modalLogoutButtonElement.click();
	}

}
