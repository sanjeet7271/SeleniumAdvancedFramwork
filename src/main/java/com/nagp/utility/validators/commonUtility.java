package com.nagp.utility.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.testng.Assert;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class commonUtility {

	private static Pattern pattern;
	private static Matcher matcher;
	private static Logger logger = Logger.getLogger(commonUtility.class);
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String PRICE_PATTERN = "([0-9]+)+(.)+([0-9]+)";

	public commonUtility() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public static boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

	/**
	 * 
	 * @param email
	 * @utility to Email validation
	 */
	public static void validEmailTest(String email) {
		try {
			boolean valid = validate(email);
			logger.info("Email is valid : " + email + " , " + valid);
			Assert.assertEquals(valid, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double calculatePrice(String price, String noOfItems) {
		double doublePrice = Double.parseDouble(onlyPrice(price));
		Double totalPrice = doublePrice * Double.parseDouble(noOfItems);
		return totalPrice;
	}

	public String onlyPrice(String price) {
		Matcher matcher = Pattern.compile(PRICE_PATTERN).matcher(price);
		while (matcher.find()) {
			return matcher.group();
		}
		return null;

	}
}
