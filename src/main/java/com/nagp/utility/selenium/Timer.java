package com.nagp.utility.selenium;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagp.utility.listeners.AdditionalConditions;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class Timer implements SeleniumFramework {

	private static Timer instance = null;

	public Timer() {
		// TODO Auto-generated constructor stub
	}

	public static Timer getInstance() {
		if (null == instance) {
			instance = new Timer();
		}
		return instance;
	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public void pause(int sleep) {
		try {
			TimeUnit.SECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitForSpinner() {
		WebDriverWait wait = new WebDriverWait(driver, 120, 100);
		wait.until(AdditionalConditions.angularHasFinishedProcessing());
	}
}
