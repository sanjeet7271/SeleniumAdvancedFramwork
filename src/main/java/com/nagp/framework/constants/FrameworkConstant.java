package com.nagp.framework.constants;

import java.io.File;
import java.util.HashMap;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class FrameworkConstant {
	public static final String CONFIGFILE = "configuration/configuration.properties";
	public static final String TMP = System.getProperty("user.dir") + File.separator + "runtime";
	public static final String SCREENSHOT_LOCATION = TMP + File.separator + "screenshots" + File.separator;
	public static HashMap<String, String> GLOBALCONFIG = new HashMap<String, String>();
	public static String LOGDIR = "Logs";
	public static String LOGFILENAME = "";
	public static String EXECUTION_MODE = "";
	public static String START_DATE = "";
	public static String START_TIME = "";
}
