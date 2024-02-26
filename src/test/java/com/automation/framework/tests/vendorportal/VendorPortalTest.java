package com.automation.framework.tests.vendorportal;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.framework.pages.vendorportal.DashboardPage;
import com.automation.framework.pages.vendorportal.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VendorPortalTest {

	private WebDriver driver;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		this.driver.manage().window().setSize(new Dimension(1920, 1080));
	}

	@AfterTest
	public void tearDown() {
		this.driver.close();
		this.driver.quit();
	}

	@Test
	public void loginTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
		Assert.assertTrue(loginPage.isAt());
		loginPage.login("sam", "sam");
	}

	@Test(dependsOnMethods = "loginTest")
	public void dashboardTest() {
		DashboardPage dashboardPage = new DashboardPage(driver);
		Assert.assertTrue(dashboardPage.isAt());

		// finance metrics
		Assert.assertEquals(dashboardPage.getMonthlyEarning(), "$40,000");
		// ...

		// order history search
		dashboardPage.searchOrderHistoryBy("adams");
		Assert.assertEquals(dashboardPage.getSearchResultsCount(), 8);

		// logout
		dashboardPage.logout();
	}

}
