package com.nagp.utility.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.nagp.framework.DateUtility;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class ZipUtils {

	private static List<String> fileList;
	private static String OUTPUT_ZIP_FILE = "D:\\NAGPTraining";
	private static String SOURCE_FOLDER = System.getProperty("user.dir");
	private DateUtility dateUtility = new DateUtility();

	public ZipUtils() {
		fileList = new ArrayList<String>();
	}

	/**
	 * 
	 */

	public void createZipFolder() {
		String fileName = dateUtility.getCurrentDateTime();
		OUTPUT_ZIP_FILE = OUTPUT_ZIP_FILE + "-" + fileName + ".zip";
		generateFileList(new File(SOURCE_FOLDER));
		zipIt(OUTPUT_ZIP_FILE);
	}

	private void zipIt(String zipFile) {
		byte[] buffer = new byte[1024];
		String source = new File(SOURCE_FOLDER).getName();
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);

			FileInputStream in = null;

			for (String file : ZipUtils.fileList) {

				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try {
					in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
					int len;
					while ((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				} finally {
					in.close();
				}
			}
			zos.closeEntry();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void generateFileList(File node) {
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.toString()));
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename));
			}
		}
	}

	private String generateZipEntry(String file) {
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}
}