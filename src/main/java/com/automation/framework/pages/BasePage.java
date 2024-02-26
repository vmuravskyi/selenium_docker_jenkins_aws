package com.automation.framework.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

	protected final static Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitForJavaScriptToComplete();
	}

	public abstract boolean isAt();

	public void waitForJavaScriptToComplete() {
		// Wait for JavaScript to be ready
		this.wait.until((ExpectedCondition<Boolean>) wd ->
			((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

}
