package com.nagp.utility.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagp.framework.constants.SeleniumConstant;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class SeleniumUtils implements SeleniumFramework {
	private static Logger logger = Logger.getLogger(SeleniumUtils.class);
	private WebDriverWait wait = new WebDriverWait(driver, SeleniumConstant.DEFAULTTIMEOUT);
	private static SeleniumUtils instance = null;

	protected SeleniumUtils() {

	}

	public static SeleniumUtils getInstance() {
		if (null == instance) {
			instance = new SeleniumUtils();
		}
		return instance;
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @return
	 */
	// An expectation for checking that an element is present on the DOM of a page
	// and visible.
	protected boolean waitForElement(By elementLocator) {
		wait = new WebDriverWait(driver, SeleniumConstant.DEFAULTTIMEOUT);
		logger.debug("Looking for element locator..." + elementLocator);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
			logger.debug("Elemnt locator found..." + elementLocator);
			return true;
		} catch (Exception e) {
			logger.error("Element locator id found..." + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @return
	 */
	// An expectation for checking an element is visible and enabled such that you
	// can click it
	protected boolean elementToClickable(By elementLocator) {
		logger.debug("Looking for element locator to clickable " + elementLocator);
		wait = new WebDriverWait(driver, SeleniumConstant.DEFAULTTIMEOUT);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
			return true;
		} catch (Exception e) {
			logger.error("Element is not clickable" + elementLocator);
			return false;
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @return
	 */
	// Get all text present on locators
	protected String getText(By elementLocator) {
		if (waitForElement(elementLocator)) {
			logger.debug("Elemnt locator get text ..." + driver.findElement(elementLocator).getText());
			return driver.findElement(elementLocator).getText();
		} else {
			logger.error("Elemnt locator not found..." + elementLocator);
			return "";
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param elementLocator
	 * @param attribute
	 * @return
	 */
	// Gets attribute present inside tag
	protected String getAttribute(By elementLocator, String attribute) {
		if (waitForElement(elementLocator)) {
			logger.debug("Elemnt locator get text ..." + driver.findElement(elementLocator).getAttribute(attribute));
			return driver.findElement(elementLocator).getAttribute(attribute);
		} else {
			logger.error("Elemnt locator not found..." + elementLocator);
			return "";
		}
	}

}
