package com.nagp.framework;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
/**
 * 
 * @author sanjeetpandit
 *
 */
public class FolderUtility {

	private static Logger logger = Logger.getLogger(FolderUtility.class);

	/**
	 * @author sanjeet.pandit
	 * @Create New folder and delete folder if exist .
	 * @param folderName
	 * @return
	 */
	public boolean checkAndCreateFolder(String folderName) {
		File folder = new File(folderName);
		logger.debug("Creating new folder " + folderName);
		try {
			if (!folder.exists()) {
				if (folder.mkdir()) {
					logger.debug("Directory is created!");
					return true;
				} else {
					logger.error("Problem while creating folder " + folderName);
					return false;
				}
			} else {
				logger.debug("Deleting directory.!");
				FileUtils.deleteDirectory(folder);
				if (folder.mkdir()) {
					logger.debug("Directory is created!");
					return true;
				} else {
					logger.error("Problem while creating folder " + folderName);
					return false;
				}
			}
		} catch (Exception e) {
			logger.error("Exception..!", e);
			logger.error("Problem while creating folder " + folderName);
			return false;
		}
	}
}
