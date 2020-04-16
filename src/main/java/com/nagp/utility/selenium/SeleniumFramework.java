package com.nagp.utility.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.nagp.framework.BrowserProcessID;
import com.nagp.framework.DateUtility;
import com.nagp.framework.Driver;
import com.nagp.framework.FolderUtility;
import com.nagp.framework.PropertiesFiles;
import com.nagp.utility.reports.Highlight;
import com.nagp.utility.reports.Report;
import com.nagp.utility.reports.ScreenShot;
import com.nagp.utility.validators.commonUtility;

/**
 * 
 * @author sanjeet.pandit Description : Definition of classes.
 */
public interface SeleniumFramework {
	public static Logger logger = Logger.getLogger(SeleniumFramework.class);
	WebDriver driver = Driver.getInstance().getSelenium();
	Elements element = new Elements();
	Timer timer = new Timer();
	JavascriptExecute javascriptExecute = new JavascriptExecute();
	Dropdown dropdown = new Dropdown();
	Report report = new Report();
	BrowserProcessID seleniumutility = new BrowserProcessID();
	DateUtility dateUtility = new DateUtility();
	ZipUtils zipUtils = new ZipUtils();
	ScreenShot screenShot = new ScreenShot();
	Highlight highlights = new Highlight();
	SeleniumUtils seleniumUtils = new SeleniumUtils();
	FolderUtility folderUtility = new FolderUtility();
	BrowserProcessID browserutility = new BrowserProcessID();
	PropertiesFiles prop = new PropertiesFiles();
	commonUtility commonUtility = new commonUtility();

}
