package com.automation.framework.tests;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;

	@BeforeTest
	@Parameters("headless")
	public void setup(Boolean headless) {
		WebDriverManager.chromedriver().setup();
		if (headless) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			this.driver = new ChromeDriver(options);
		} else {
			this.driver = new ChromeDriver();
		}
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		this.driver.manage().window().setSize(new Dimension(1920, 1080));
	}

	@AfterTest
	public void tearDown() {
		this.driver.close();
		this.driver.quit();
	}

}
