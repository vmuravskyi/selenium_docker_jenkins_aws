package com.automation.framework.tests.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.automation.framework.utils.Constants;

public class TestListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute(Constants.DRIVER);
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		String screenshot = takesScreenshot.getScreenshotAs(OutputType.BASE64);
		String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
		String htmlImage = String.format(htmlImageFormat, screenshot);
		Reporter.log(htmlImage); // embed image to html report
	}

}
