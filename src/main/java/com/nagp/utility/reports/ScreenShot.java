package com.nagp.utility.reports;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.nagp.framework.DateUtility;
import com.nagp.framework.Driver;
import com.nagp.framework.constants.FrameworkConstant;
import com.nagp.utility.selenium.Timer;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class ScreenShot {

	private static Logger logger = Logger.getLogger(ScreenShot.class);
	private DateUtility dateUtility = new DateUtility();
	private Timer timer = new Timer();
	private WebDriver driver = Driver.getInstance().getSelenium();

	/**
	 * @author sanjeetpandit
	 * @return
	 */
	public String takeSnapShot() {
		String screenShotPath = "";
		String fileName = dateUtility.getCurrentTime("ddMMyyyyhhmmss");
		try {
			driver.manage().window().maximize();
			timer.pause(1);
			screenShotPath = FrameworkConstant.SCREENSHOT_LOCATION + File.separator + fileName + ".png";
			File pngFile = new File(screenShotPath);
			File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			logger.info("Screenshot save at location: " + screenShotPath);
			FileUtils.copyFile(screenShot, pngFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName + ".png";
	}
}
