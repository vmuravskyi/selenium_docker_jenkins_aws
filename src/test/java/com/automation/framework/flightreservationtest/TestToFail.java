package com.automation.framework.flightreservationtest;

import org.testng.annotations.Test;

public class TestToFail {

	@Test()
	public void failTest() {
		assert false;
	}

}
