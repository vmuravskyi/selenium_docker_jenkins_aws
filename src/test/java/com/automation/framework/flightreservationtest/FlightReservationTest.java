package com.automation.framework.flightreservationtest;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.framework.Assertions;
import com.automation.framework.pages.flightreservation.FlightsConfirmationPage;
import com.automation.framework.pages.flightreservation.RegistrationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlightReservationTest {

	private WebDriver driver;
	private String noOfPassengers;
	private String expectedPrice;

	@BeforeMethod
	@Parameters({"noOfPassengers", "expectedPrice"})
	public void setup(String noOfPassengers, String expectedPrice) {
		this.noOfPassengers = noOfPassengers;
		this.expectedPrice = expectedPrice;
		// driver setup
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions().addArguments("--headless");
		this.driver = new ChromeDriver(options);
//		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		this.driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.get("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
	}

	@AfterMethod
	public void tearDown() {
		this.driver.close();
		this.driver.quit();
	}

	@Test
	public void reserveFlightTest() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		FlightsConfirmationPage flightsConfirmationPage = registrationPage.enterFirstName("first name")
			.enterLastName("last name")
			.enterEmail("email@gmail.com")
			.enterPassword("password")
			.enterStreet("street")
			.enterCity("city")
			.selectState("Delaware")
			.enterZip("12345")
			.clickRegisterButton()
			.clickGoToFlightSearch()
			.chooseNumberOfPassengers(noOfPassengers)
			.clickSearchFlights()
			.clickConfirmFlights();

		Assertions assertions = new Assertions();
		assertions.verifyFlightConfirmationNumberExists(flightsConfirmationPage);
		assertions.verifyTotalPriceIsEqualTo(flightsConfirmationPage, expectedPrice);
	}

	@Test()
	public void failTest() {
		assert false;
	}

}
