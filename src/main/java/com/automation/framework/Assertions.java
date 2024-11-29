package com.automation.framework;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.framework.pages.flightreservation.FlightsConfirmationPage;

public class Assertions {

	private static final Logger LOGGER = LoggerFactory.getLogger(Assertions.class);

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

	public void verifyTotalPriceIsEqualTo(FlightsConfirmationPage flightsConfirmationPage, String expectedTotalPrice) {
		String pattern = "Total Price\\s+\\$((?<totalPrice>[0-9]+)\\s+USD)";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(flightsConfirmationPage.getFlightsConfirmationSection().getText());

		if (matcher.find()) {
			String totalPrice = matcher.group("totalPrice");
			LOGGER.info("Verifying expected price {} is equal to {}", expectedTotalPrice, totalPrice);
			assert totalPrice.equals(expectedTotalPrice);
		} else {
			LOGGER.info("Could not find total price");
			assert false;
		}
	}

}
