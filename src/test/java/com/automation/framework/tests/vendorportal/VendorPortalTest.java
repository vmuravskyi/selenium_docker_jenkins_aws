package com.automation.framework.tests.vendorportal;

import com.automation.framework.utils.TestGroups;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.framework.models.VendorPortalTestData;
import com.automation.framework.pages.vendorportal.DashboardPage;
import com.automation.framework.pages.vendorportal.LoginPage;
import com.automation.framework.tests.BaseTest;
import com.automation.framework.utils.Config;
import com.automation.framework.utils.Constants;
import com.automation.framework.utils.JsonUtil;

public class VendorPortalTest extends BaseTest {

	private LoginPage loginPage;
	private DashboardPage dashboardPage;

	private VendorPortalTestData testData;

	@BeforeClass
	@Parameters("testDataPath")
	public void setPageObjects(String testDataPath) {
		this.loginPage = new LoginPage(driver);
		this.dashboardPage = new DashboardPage(driver);
		this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
	}

	@Test(groups = {TestGroups.REGRESSION, TestGroups.SMOKE})
	public void loginTest() {
		loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
		Assert.assertTrue(loginPage.isAt());
		loginPage.login(testData.username(), testData.password());
	}

	@Test(dependsOnMethods = "loginTest", groups = {TestGroups.REGRESSION, TestGroups.DASHBOARD})
	public void dashboardTest() {
		Assert.assertTrue(dashboardPage.isAt());

		// finance metrics
		Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
		Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
		Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
		Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

		// order history search
		dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
		Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());
	}

	@Test(dependsOnMethods = "dashboardTest")
	public void logoutTest() {
		dashboardPage.logout();
		Assert.assertTrue(loginPage.isAt());
	}

}
