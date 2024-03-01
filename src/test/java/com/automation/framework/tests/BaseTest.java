package com.automation.framework.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

	protected WebDriver driver;

	@BeforeTest
	public void setDriver() throws MalformedURLException {
		if (Boolean.valueOf(System.getProperty("selenium.grid.enabled"))) {
			this.driver = getRemoteDriver();
		} else {
			this.driver = getLocalDriver();
		}
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		this.driver.manage().window().setSize(new Dimension(1920, 1080));
	}

	@AfterTest(alwaysRun = true)
	public void quitDriver() {
		this.driver.quit();
	}

	private WebDriver getLocalDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}

	private WebDriver getRemoteDriver() throws MalformedURLException {
		Capabilities capabilities;
		if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
			capabilities = new ChromeOptions();
		} else {
			capabilities = new FirefoxOptions();
		}
		return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
	}

}
