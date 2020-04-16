package com.nagp.runner;

import org.testng.annotations.Test;

import com.nagp.framework.XmlExecutor;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class SuiteRunner extends XmlExecutor {
	/*
	 * public static void main(String[] args) {
	 * executeTestNGXML("src/main/resources/xml/testNg.xml"); }
	 */
	@Test
	public void ExecuteTestCases() {
		executeTestNGXML("src/main/resources/xml/testNg.xml");
	}

}
