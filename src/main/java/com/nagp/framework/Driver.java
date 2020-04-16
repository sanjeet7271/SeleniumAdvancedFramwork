package com.nagp.framework;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.nagp.framework.constants.FrameworkConstant;
import com.nagp.framework.constants.ReportConstant;
import com.nagp.framework.constants.SeleniumConstant;

/**
 * 
 * @author Sanjeet.Pandit
 *
 */
public class Driver {
	private static Driver instance = null;
	private WebDriver driver;
	private List<String> processes = new ArrayList<String>();
	private static Logger logger = Logger.getLogger(Driver.class);
	private ClassLoader classLoader = getClass().getClassLoader();
	private BrowserProcessID browserutility = new BrowserProcessID();
	private File file = null;

	private enum browsers {
		IE, CHROME, FIREFOX
	};

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	protected Driver() {
		processes = browserutility.getBrowserProcess();
		logger.info("Execution mode is " + FrameworkConstant.EXECUTION_MODE);
		try {
			if (FrameworkConstant.EXECUTION_MODE.equalsIgnoreCase("GRID")) {
				setGridDriver();
			} else {
				setLocalDriver();
			}
			logger.info("Exeuction is started on " + FrameworkConstant.GLOBALCONFIG.get("BROWSER") + " browser.");
			setDriverParameters();
			browserutility.getNewBrowserPID(FrameworkConstant.GLOBALCONFIG.get("BROWSER").toUpperCase(), processes);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static synchronized Driver getInstance() {

		if (null == instance) {
			instance = new Driver();
		}
		return instance;
	}

	public WebDriver getSelenium() {
		return driver;
	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	@SuppressWarnings("deprecation")
	private void setLocalDriver() {
		switch (browsers.valueOf(FrameworkConstant.GLOBALCONFIG.get("BROWSER").toUpperCase())) {
		case IE:
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities = getIECapabilities(ieCapabilities);
			driver = new InternetExplorerDriver(ieCapabilities);
			break;
		case CHROME:
			DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
			chromeCapabilities = getChromeCapabilities(chromeCapabilities);
			driver = new ChromeDriver(chromeCapabilities);
			break;
		case FIREFOX:
			DesiredCapabilities fireFoxcapabilities = DesiredCapabilities.firefox();
			fireFoxcapabilities = getFireFoxCapabilities(fireFoxcapabilities);
			driver = new FirefoxDriver(fireFoxcapabilities);
			break;
		default:
			ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities = getIECapabilities(ieCapabilities);
			driver = new InternetExplorerDriver(ieCapabilities);
			break;
		}
	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private void setGridDriver() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		switch (browsers.valueOf(FrameworkConstant.GLOBALCONFIG.get("BROWSER").toUpperCase())) {
		case IE:
			capabilities = getIECapabilities(capabilities);
			break;

		case CHROME:
			capabilities = getChromeCapabilities(capabilities);
			capabilities.setPlatform(Platform.VISTA);
			break;

		case FIREFOX:
			capabilities = getFireFoxCapabilities(capabilities);
			break;

		default:
			capabilities = getIECapabilities(capabilities);
			break;

		}
		driver = new RemoteWebDriver(new URL(FrameworkConstant.GLOBALCONFIG.get("REMOTE_URL")), capabilities);
	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private DesiredCapabilities getIECapabilities(DesiredCapabilities ieCapabilities) {
		file = new File(classLoader.getResource(FrameworkConstant.GLOBALCONFIG.get("IE_DRIVER")).getFile());
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
		ieCapabilities.setCapability("requireWindowFocus", true);
		ieCapabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());

		ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);

		ieCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		// Accept unexpected alerts
		ieCapabilities.setCapability("requireWindowFocus", true);
		ieCapabilities.setCapability("enablePersistentHover", false);
		ieCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		ieCapabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
		ieCapabilities.setCapability("disable-popup-blocking", true);
		ieCapabilities.setJavascriptEnabled(true);

		return ieCapabilities;
	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private DesiredCapabilities getChromeCapabilities(DesiredCapabilities chromeCapabilities) {
		file = new File(classLoader.getResource(FrameworkConstant.GLOBALCONFIG.get("CHROME_DRIVER")).getFile());
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		options.addArguments("--test-type");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-plugins");
		chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
		chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		chromeCapabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
		chromeCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		return chromeCapabilities;
	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private DesiredCapabilities getFireFoxCapabilities(DesiredCapabilities fireFoxcapabilities) {
		file = new File(classLoader.getResource(FrameworkConstant.GLOBALCONFIG.get("FIREFOX_DRIVER")).getFile());
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);

		fireFoxcapabilities.setBrowserName("firefox");
		fireFoxcapabilities.setCapability("marionette", true);
		// System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,
		// "false");
		fireFoxcapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		fireFoxcapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		profile.setPreference("browser.private.browsing.autostart", true);
		fireFoxcapabilities.setCapability(FirefoxDriver.PROFILE, profile);
		return fireFoxcapabilities;

	}

	/**
	 * 
	 * @author Sanjeet.Pandit
	 *
	 */
	private void setDriverParameters() {
		logger.info("Setting default Execution parameters");
		logger.info("EXECUTION MODE : " + FrameworkConstant.EXECUTION_MODE.equalsIgnoreCase("GRID"));
		logger.info("DEFAULT TIMEOUT : " + SeleniumConstant.DEFAULTTIMEOUT);
		logger.info("PAGELOAD TIMEOUT : " + SeleniumConstant.PAGELOADTIMEOUT);
		logger.info("BROWSER : " + FrameworkConstant.GLOBALCONFIG.get("BROWSER").toUpperCase());
		logger.info("SCREEN SHOT : " + SeleniumConstant.SCREEN_SHOT);
		logger.info("HTML REPORT : " + ReportConstant.HTML_REPORT);
		logger.info("EXCEL REPORT : " + ReportConstant.EXCEL_REPORT);
		logger.info("Browser PID  : " + SeleniumConstant.BROWSER_PID);
		logger.info("----------------------------------------");
		driver.manage().timeouts().implicitlyWait(SeleniumConstant.DEFAULTTIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(SeleniumConstant.PAGELOADTIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
	}

}
