package com.nagp.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.nagp.framework.constants.SeleniumConstant;

/**
 * 
 * @author sanjeet.pandit
 *
 */
public class BrowserProcessID {
	private List<String> processes = new ArrayList<String>();

	/**
	 * @author sanjeet.pandit
	 * @param processName
	 * @return
	 */
	public List<String> getProcess(String processName) {
		try {
			String line;
			Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (!line.trim().equals("")) {
					line = line.substring(1);
					String[] pids = line.replaceAll("\"", "").split(",");
					String pid = "";

					String proces = pids[0].trim();
					pid = pids[1];
					if (proces.equalsIgnoreCase(processName)) {
						processes.add(pid);
					}
				}

			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
		return processes;
	}

	/**
	 * @author sanjeet.pandit
	 * @param upperCase
	 * @param processes
	 */
	public void getNewBrowserPID(String upperCase, List<String> processes) {
		getBrowserProcess();
		try {
			String line;
			Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (!line.trim().equals("")) {
					line = line.substring(1);
					String[] pids = line.replaceAll("\"", "").split(",");
					String processName = "";
					String pid = "";

					processName = pids[0].trim();
					pid = pids[1];
					if (processName.equalsIgnoreCase("chromedriver.exe")) {
						SeleniumConstant.BROWSER_PID = pid;
						break;
					}
				}
			}
			input.close();

		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param processID
	 */
	public void killProcess(String processID) {
		String cmd = "taskkill /F /PID " + processID;
		try {
			Runtime.getRuntime().exec(cmd);
			verifyKilledProcess(processID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author sanjeet.pandit
	 * @param processID
	 */
	private void verifyKilledProcess(String processID) {
		processes = getBrowserProcess();
		// Assert.assertTrue(!processes.contains(processID), "Process not gets
		// killed.");

	}

	/**
	 * @author sanjeet.pandit
	 * @return
	 */
	public List<String> getBrowserProcess() {
		processes = getProcess("chromedriver.exe");
		return processes;
	}
}
