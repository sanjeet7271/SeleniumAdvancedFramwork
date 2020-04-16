package com.nagp.utility.reports;

import java.io.File;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.nagp.framework.constants.FrameworkConstant;
import com.nagp.framework.constants.ReportConstant;
import com.nagp.framework.constants.SeleniumConstant;
import com.nagp.framework.constants.TestcaseConstant;
import com.nagp.utility.selenium.SeleniumFramework;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class Report implements SeleniumFramework {
	private static Logger logger = Logger.getLogger(Report.class);
	private String appendScreenshot = "";
	private static Report instance = null;

	public Report() {

	}

	/**
	 * 
	 * @author sanjeet.pandit
	 *
	 */
	public static Report getInstance() {
		if (null == instance) {
			instance = new Report();
		}
		return instance;
	}

	/**
	 * @author sanjeet.pandit
	 * @param message
	 * @param value
	 */
	public void log(String message, String value) {
		String screenShotPath = "";
		logger.info("Step : " + TestcaseConstant.STEP_COUNTER + " : " + message + " Paramter : " + value);
		if (SeleniumConstant.SCREEN_SHOT.equalsIgnoreCase("Yes")) {
			screenShotPath = screenShot.takeSnapShot();
			appendScreenshot = "<td>" + "<a href=\"" + FrameworkConstant.SCREENSHOT_LOCATION + File.separator
					+ screenShotPath + "\">   Screenshot</a></td>";
			if (ReportConstant.HTML_REPORT.equalsIgnoreCase("Yes")) {
				ReportConstant.STEP.log(Status.PASS,
						message + " " + "<span style='font-weight:bold;'>" + value + "</span>" + appendScreenshot);
			}
		} else {
			if (ReportConstant.HTML_REPORT.equalsIgnoreCase("Yes")) {
				ReportConstant.STEP.log(Status.PASS,
						message + " " + "<span style='font-weight:bold;'>" + value + "</span>");
			}
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param message
	 * @param value
	 */
	public void log(String message, String value, WebElement element) {
		String screenShotPath = "";
		TestcaseConstant.STEP_COUNTER++;
		logger.info("Step : " + TestcaseConstant.STEP_COUNTER + " : " + message + " Paramter : " + value);
		highlights.highligthElement(element);
		if (SeleniumConstant.SCREEN_SHOT.equalsIgnoreCase("Yes")) {
			screenShotPath = screenShot.takeSnapShot();
			appendScreenshot = "<td>" + "<a href=\"" + FrameworkConstant.SCREENSHOT_LOCATION + File.separator
					+ screenShotPath + "\">   Screenshot</a></td>";
			if (ReportConstant.HTML_REPORT.equalsIgnoreCase("Yes")) {
				ReportConstant.STEP.log(Status.PASS,
						message + " " + "<span style='font-weight:bold;'>" + value + "</span>" + appendScreenshot);
			}
		} else {
			if (ReportConstant.HTML_REPORT.equalsIgnoreCase("Yes")) {
				ReportConstant.STEP.log(Status.PASS,
						message + " " + "<span style='font-weight:bold;'>" + value + "</span>");
			}
		}
		highlights.unHighligthElement(element);
	}
}
