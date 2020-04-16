
package com.nagp.utility.selenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author sanjeet.pandit
 *
 */
public class Dropdown implements SeleniumFramework {

	private static Dropdown instance = null;
	private List<WebElement> optionsList;

	protected Dropdown() {

	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public static Dropdown getInstance() {
		if (null == instance) {
			instance = new Dropdown();
		}
		return instance;
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void selectValueFromList(By elementLocator, String value) {
		if (element.waitForElement(elementLocator)) {
			// Select options = new Select(driver.findElement(elementLocator));
			// optionsList = options.getOptions();
			optionsList = driver.findElements(elementLocator);
			outerloop: for (WebElement option : optionsList) {
				if (option.getText().trim().equalsIgnoreCase(value)) {
					option.click();
					break outerloop;
				}
			}
			logger.debug("Element locator found..." + elementLocator);
		} else {
			logger.error(" Dropdown Element locator not found..." + elementLocator);
		}
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void SelectDate(By elementLocator, String value) {
		if (element.waitForElement(elementLocator)) {
			List<WebElement> allDates = driver.findElements(elementLocator);
			outerloop: for (WebElement ele : allDates) {
				String date = ele.getText().trim();
				if (date.equalsIgnoreCase(value)) {
					ele.click();
					System.out.println(date);
					break outerloop;
				}

			}
			logger.debug("Element locator found..." + elementLocator);
		} else {
			logger.error(" Dropdown Element locator not found..." + elementLocator);
		}
	}
}
