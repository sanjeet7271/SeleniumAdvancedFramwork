package com.nagp.framework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.testng.Assert;

import com.nagp.framework.constants.FrameworkConstant;
import com.nagp.framework.constants.ReportConstant;
import com.nagp.framework.constants.SeleniumConstant;
import com.nagp.utility.selenium.SeleniumFramework;
import com.nagp.utility.selenium.ZipUtils;

/**
 * 
 * @author sanjeet.pandit
 *
 */

public class Setup implements SeleniumFramework {
	private FolderUtility folderUtility = new FolderUtility();
	private BrowserProcessID browserutility = new BrowserProcessID();
	private DateUtility dateUtility = new DateUtility();
	private PropertiesFiles prop = new PropertiesFiles();
	private ZipUtils zipUtils = new ZipUtils();
	private static Logger logger = Logger.getLogger(Setup.class);
	protected static HashMap<String, String> dbConfig = new HashMap<String, String>();

	/**
	 * @author sanjeet.pandit
	 * @return
	 */
	private String getLogFileName() {
		String timeFormat = "dd-MM-yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		Calendar cal = Calendar.getInstance();
		return "Log_" + sdf.format(cal.getTime()) + ".txt";

	}

	/**
	 * TBD
	 */
	public void initialConfiguration() {
		zipUtils.createZipFolder();
		logger.info("Last Report created into zip file");
		FrameworkConstant.LOGFILENAME = getLogFileName();
		logger.info("Log file " + FrameworkConstant.LOGFILENAME + " is created.");
		setupFolders();
		setupConstants();
		logger.info("Initialisation completed.");
	}

	/**
	 * @author sanjeet.pandit
	 */
	private void setupConstants() {
		FrameworkConstant.GLOBALCONFIG = prop.readProperties(FrameworkConstant.CONFIGFILE);
		SeleniumConstant.DEFAULTTIMEOUT = Integer.parseInt(FrameworkConstant.GLOBALCONFIG.get("DEFAULT_TIMEOUT"));
		SeleniumConstant.PAGELOADTIMEOUT = Integer.parseInt(FrameworkConstant.GLOBALCONFIG.get("PAGELOAD_TIMEOUT"));
		SeleniumConstant.SCREEN_SHOT = FrameworkConstant.GLOBALCONFIG.get("SCREEN_SHOT");
		FrameworkConstant.EXECUTION_MODE = FrameworkConstant.GLOBALCONFIG.get("EXECUTION_MODE");
		ReportConstant.EXCEL_REPORT = FrameworkConstant.GLOBALCONFIG.get("EXCEL_REPORT");
		ReportConstant.HTML_REPORT = FrameworkConstant.GLOBALCONFIG.get("HTML_REPORT");
	}

	/**
	 * @author sanjeet.pandit
	 */
	private void setupFolders() {
		logger.debug("Creating folder structure.");
		Assert.assertTrue(
				(folderUtility.checkAndCreateFolder(FrameworkConstant.LOGDIR)
						&& folderUtility.checkAndCreateFolder(FrameworkConstant.TMP)
						&& folderUtility.checkAndCreateFolder(FrameworkConstant.SCREENSHOT_LOCATION)),
				"Error for initialsing setup...");

	}

	/**
	 * @author sanjeet.pandit
	 * @throws Exception
	 */
	public void loadTestCaseData() throws Exception {
		logger.info("Loading test case data...");
		FrameworkConstant.START_DATE = dateUtility.getCurrentTime("MM/dd/yyyy");
		FrameworkConstant.START_TIME = dateUtility.getCurrentTime("hh:mm:ss a");
	}

	/**
	 * @author sanjeet.pandit
	 */
	public void tearDown() {
		if (ReportConstant.HTML_REPORT.equalsIgnoreCase("Yes")) {
			ReportConstant.EXTENT.flush();
		}
		if (!(driver == null)) {
			TemporaryFilesystem tempFS = TemporaryFilesystem.getDefaultTmpFS();
			tempFS.deleteTemporaryFiles();
			driver.quit();
		}
		browserutility.killProcess(SeleniumConstant.BROWSER_PID);
	}
}
