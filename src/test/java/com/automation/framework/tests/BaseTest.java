package com.automation.framework.tests;

import static com.automation.framework.utils.Constants.BROWSER;
import static com.automation.framework.utils.Constants.FIREFOX;
import static com.automation.framework.utils.Constants.GRID_HUB_HOST;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.automation.framework.utils.Config;
import com.automation.framework.utils.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
	protected WebDriver driver;

	@BeforeSuite
	public void setupConfig() {
		Config.initialize();
	}

	@BeforeTest
	public void setDriver() throws MalformedURLException {
		this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();

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
		if (Config.get(BROWSER).equalsIgnoreCase(FIREFOX)) {
			capabilities = new FirefoxOptions();
		} else {
			capabilities = new ChromeOptions();
		}

		// build selenium grid hub url
		String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
		String hubHost = Config.get(GRID_HUB_HOST);
		String url = String.format(urlFormat, hubHost);
		LOGGER.info("Selenium grid URL: {}", url);
		return new RemoteWebDriver(new URL(url), capabilities);
	}

}
