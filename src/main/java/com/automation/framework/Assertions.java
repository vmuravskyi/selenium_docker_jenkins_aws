package com.automation.framework;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.automation.framework.pages.flightreservation.FlightsConfirmationPage;

public class Assertions {

	public void verifyFlightConfirmationNumberExists(FlightsConfirmationPage flightsConfirmationPage) {
		String pattern = "Flight Confirmation #\\s*(?<confirmationNumber>\\d{4}-\\d{1,2}-\\d{13})";

		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(flightsConfirmationPage.getFlightsConfirmationSection().getText());

		// Check if a match is found before getting the confirmation number
		if (matcher.find()) {
			String confirmationNumber = matcher.group("confirmationNumber");
			assert !confirmationNumber.isEmpty() && !confirmationNumber.isBlank();
		} else {
			assert false;
		}
	}

}
