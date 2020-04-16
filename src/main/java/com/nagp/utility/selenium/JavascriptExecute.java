package com.nagp.utility.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class JavascriptExecute implements SeleniumFramework {

	private static JavascriptExecute instance = null;
	private JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

	protected JavascriptExecute() {

	}

	public static JavascriptExecute getInstance() {
		if (null == instance) {
			instance = new JavascriptExecute();
		}
		return instance;
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void scrollToView(By elementLocator) {

		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElement(elementLocator));
	}

	public void scrollToBottom() {
		jsExecutor.executeScript(
				"window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.body.clientHeight));");
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void scrollToElementClick(By elementLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView();", driver.findElement(elementLocator));
	}
}
