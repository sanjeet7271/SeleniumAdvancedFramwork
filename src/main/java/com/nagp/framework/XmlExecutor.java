package com.nagp.framework;

import org.apache.log4j.Logger;

/**
 * @author sanjeet.pandit
 *
 */
public class XmlExecutor {
	private static Setup setUp = new Setup();
	private static TestExecute execute = new TestExecute();
	private static Logger logger = Logger.getLogger(XmlExecutor.class);

	/**
	 * @author sanjeet.pandit
	 * @param xmlPath
	 */
	public static void executeTestNGXML(String xmlPath) {
		try {
			logger.info("Initialize setup..");
			setUp.initialConfiguration();
			setUp.loadTestCaseData();
			execute.executeXML(xmlPath);
			setUp.tearDown();
		} catch (Exception e) {
			logger.error("Exception...", e);
		}

	}
}
