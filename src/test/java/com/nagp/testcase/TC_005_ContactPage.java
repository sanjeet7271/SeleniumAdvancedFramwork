package com.nagp.testcase;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nagp.framework.constants.FrameworkConstant;
import com.nagp.pages.Pages;
import com.nagp.utility.excel.ExcelSheetReader;
import com.nagp.utility.selenium.SeleniumFramework;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class TC_005_ContactPage implements SeleniumFramework, Pages {

	String xlFilePath = "./src/main/resources/testData/RegisterAndLogin.xlsx";
	String sheetName = "contact";
	ExcelSheetReader ProvideData = new ExcelSheetReader();
	Object[][] data;

	@Test(priority = 1, description = "Navigation to Home Page")
	public void initialization() throws IOException {
		String url = FrameworkConstant.GLOBALCONFIG.get("URL");
		driver.get(url);
		logger.info(url);
	}

	@DataProvider(name = "contactPage")
	public Object[][] registerInfo() throws Exception {
		data = ProvideData.testData(xlFilePath, sheetName);
		return data;
	}

	@Test(priority = 2, dataProvider = "contactPage", description = "filter the price range")
	public void contact_The_Website(String subjectHeading, String email, String orderRef, String message) {
		contact.clickOnContact();
		contact.selectSubjectHeading(subjectHeading);
		contact.emailAddress(email);
		contact.orderRef(orderRef);
		contact.message(message);
		contact.clickOnSend();
	}

}
