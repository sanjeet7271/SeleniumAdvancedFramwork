package com.nagp.utility.selenium;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

import com.nagp.framework.constants.SeleniumConstant;
import com.nagp.utility.reports.Report;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class Elements implements SeleniumFramework {

	private SeleniumUtils seleniumUtils = new SeleniumUtils();
	private static Elements instance = null;
	private Actions actions = new Actions(driver);
	private Report report = new Report();

	protected Elements() {
	}

	public static Elements getInstance() {
		if (null == instance) {
			instance = new Elements();
		}
		return instance;
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public boolean waitForElement(By elementLocator) {
		return seleniumUtils.waitForElement(elementLocator);
	}

	public void hoverCurser(By elementLocator) {
		WebElement element = driver.findElement(elementLocator);
		actions.moveToElement(element).perform();
		report.log("Hover to text ", "PASS", driver.findElement(elementLocator));
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public boolean elementToBeClickable(By elementLocator) {
		return seleniumUtils.elementToClickable(elementLocator);
	}

	@SuppressWarnings("deprecation")
	protected void fluentWaitForElement(final By elementLocator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(SeleniumConstant.DEFAULTTIMEOUT, TimeUnit.SECONDS);
		wait.withTimeout(2, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class); // We need to ignore this
														// exception.

		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(elementLocator);
				if (element != null) {
					System.out.println("A new dynamic object is found.");
				}
				return element;
			}
		};

		wait.until(function);
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	@SuppressWarnings("deprecation")
	protected void fluentWaitInTimeForElement(final By elementLocator, int time) {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(time, TimeUnit.SECONDS);
		wait.withTimeout(2, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class); // We need to ignore this
														// exception.

		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(elementLocator);
				if (element != null) {
					System.out.println("A new dynamic object is found.");
				}
				return element;
			}
		};

		wait.until(function);
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */

	public String getText(By elementLocator) {
		report.log("Get text value ", "PASS", driver.findElement(elementLocator));
		return seleniumUtils.getText(elementLocator);
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public String getAttribute(By elementLocator, String value) {
		report.log("Get attribute value ", "PASS", driver.findElement(elementLocator));
		return seleniumUtils.getAttribute(elementLocator, value);
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void sendKeys(By elementLocator, String value) {
		if (waitForElement(elementLocator)) {
			seleniumUtils.elementToClickable(elementLocator);
			driver.findElement(elementLocator).clear();
			driver.findElement(elementLocator).sendKeys(value);
			report.log("Enter value ", "PASS", driver.findElement(elementLocator));
			logger.debug("Enter value " + value + " of " + elementLocator);
		}
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void sendKeys(By elementLocator, String value, String message) {
		if (waitForElement(elementLocator)) {
			seleniumUtils.elementToClickable(elementLocator);
			driver.findElement(elementLocator).clear();
			driver.findElement(elementLocator).sendKeys(value);
			report.log(message, "PASS", driver.findElement(elementLocator));
			logger.debug("Enter value " + value + " of " + elementLocator);
		}
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void click(By elementLocator) {
		if (waitForElement(elementLocator)) {
			if (elementToBeClickable(elementLocator)) {
				report.log("Click on element .", "PASS", driver.findElement(elementLocator));
				driver.findElement(elementLocator).click();
			}
		}
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void click(By elementLocator, String message) {
		if (waitForElement(elementLocator)) {
			if (elementToBeClickable(elementLocator)) {
				report.log(message, "PASS", driver.findElement(elementLocator));
				driver.findElement(elementLocator).click();
			}
		}
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void clear(By elementLocator) {
		if (waitForElement(elementLocator)) {
			if (elementToBeClickable(elementLocator)) {
				driver.findElement(elementLocator).clear();
			}
		}
	}
}
