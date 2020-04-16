package com.nagp.utility.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * 
 * @author Sanjeet.Pandit
 *
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	/**
	 * @author Sanjeet.Pandit
	 */
	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annoattion, Class testClass, Constructor testConstructor, Method testMethod) {
		String testCase = testMethod.getName();
		if (testCase.equalsIgnoreCase("")) {
			annoattion.setAlwaysRun(true);
			annoattion.setEnabled(true);
		} else {
			annoattion.setAlwaysRun(false);
			annoattion.setEnabled(false);
		}
	}

}
