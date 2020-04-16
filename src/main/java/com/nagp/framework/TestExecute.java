package com.nagp.framework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

import com.nagp.framework.constants.ReportConstant;
import com.nagp.utility.listeners.AnnotationTransformer;
import com.nagp.utility.listeners.DotTestListener;
import com.nagp.utility.reports.ExtentManager;

/**
 * 
 * @author sanjeet.pandit
 *
 */

public class TestExecute {
	@SuppressWarnings("deprecation")
	public void executeXML(String xmlPath) {
		TestNG testNG = new TestNG();
		ReportConstant.EXTENT = ExtentManager.GetExtent();
		File file = new File(xmlPath);
		String absolutePath = file.getAbsolutePath();
		List<String> suite = new ArrayList<String>();
		DotTestListener testListener = new DotTestListener();
		@SuppressWarnings("unused")
		AnnotationTransformer annotation = new AnnotationTransformer();
		testNG.addListener(testListener);
		// testNG.setAnnotationTransformer(annotation);
		suite.add(absolutePath);
		testNG.setTestSuites(suite);
		testNG.run();
	}
}
