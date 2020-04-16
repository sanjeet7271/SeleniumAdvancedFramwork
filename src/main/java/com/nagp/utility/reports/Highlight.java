package com.nagp.utility.reports;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.nagp.utility.selenium.SeleniumFramework;

/**
 * @author sanjeet.pandit
 *
 */
public class Highlight implements SeleniumFramework {

	private static Logger logger = Logger.getLogger(Highlight.class);
	private static Highlight instance = null;
	private JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

	public Highlight() {

	}

	public static Highlight getInstance() {
		if (null == instance) {
			instance = new Highlight();
		}
		return instance;
	}

	public void highligthElement(By locator) {
		logger.info("Highlighting element " + locator);
		WebElement element = driver.findElement(locator);
		jsExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void highligthElement(WebElement element) {
		logger.info("Highlighting element " + element);
		jsExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void unHighligthElement(By locator) {
		logger.info("Highlighting element " + locator);
		WebElement element = driver.findElement(locator);
		jsExecutor.executeScript("arguments[0].style.border='0px'", element);
	}

	public void unHighligthElement(WebElement element) {
		logger.info("Highlighting element " + element);
		jsExecutor.executeScript("arguments[0].style.border='0px'", element);
	}

}
