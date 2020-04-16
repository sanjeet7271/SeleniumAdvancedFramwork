package com.nagp.utility.reports;

import java.net.InetAddress;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = "./extentreport.html";

	private static InetAddress ip;

	/**
	 * @author sanjeet.pandit
	 * @return
	 */
	public static ExtentReports GetExtent() {
		if (extent != null)
			return extent; // avoid creating new instance of html file
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	/**
	 * @author sanjeet.pandit
	 * @return
	 */
	private static ExtentHtmlReporter getHtmlReporter() {
		try {
			ip = InetAddress.getLocalHost();

			htmlReporter = new ExtentHtmlReporter(filePath);

			htmlReporter.config().setDocumentTitle("Recon automation report");
			htmlReporter.config().setReportName("Build_");

			htmlReporter.config().setReportName("Build_");
			htmlReporter.config().setTheme(Theme.STANDARD);
			extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
			htmlReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss a");

			extent.setSystemInfo("OS", System.getProperty("os.name").toUpperCase());
			extent.setSystemInfo("Host Name", ip.getHostName());
			extent.setSystemInfo("IP ", ip.toString());
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");

			return htmlReporter;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlReporter;
	}

	/**
	 * @author sanjeet.pandit
	 * @param name
	 * @param description
	 * @return
	 */
	public static ExtentTest createTest(String name, String description) {
		test = extent.createTest(name, description);
		return test;
	}
}
