package com.nagp.testcase;

import java.io.IOException;
import org.testng.Assert;
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
public class TC_002_RegisterPage implements SeleniumFramework, Pages {

	String xlFilePath = "./src/main/resources/testData/RegisterAndLogin.xlsx";
	String sheetName = "register";
	ExcelSheetReader ProvideData = new ExcelSheetReader();
	Object[][] data;
	String text = "An account using this email address has already been registered. Please enter a valid password or request a new one.";

	@Test(priority = 1, description = "Navigation to Home Page")
	public void initialization() throws IOException {
		String url = FrameworkConstant.GLOBALCONFIG.get("URL");
		driver.get(url);
		logger.info(url);
	}

	@DataProvider(name = "registerpage")
	public Object[][] registerInfo() throws Exception {
		data = ProvideData.testData(xlFilePath, sheetName);
		return data;
	}

	@Test(priority = 2, dataProvider = "registerpage", description = "Register Page Page verification")
	public void verify_Register_Page(String title, String emailId, String fName, String lName, String pWord, String day,
			String month, String year, String cName, String Address1, String Address2, String City, String State,
			String zCode, String Country, String AInfo, String Phone, String Mobile, String Alias)
			throws InterruptedException {
		register.clickOnSignIn();
		register.enterEmailToRegister(emailId);
		register.clickOnSubmit();
		// System.out.println(alreadyExits);
		if (register.ifEmailIsAlreadyUsed().isDisplayed()) {
			report.log("email already exits.", "FAILED");
			Assert.assertTrue(false);
		} else {
			register.personalInformationRegisterPage(title, fName, lName, emailId, pWord, day, month, year);
			register.addressRegisterPage(cName, Address1, Address2, City, State, zCode, Country, AInfo, Phone, Mobile,
					Alias);
		}

	}

	@Test(priority = 3, description = "Register Page Page verification")
	public void verify_Already_Used() {
		register.clickOnSignIn();
		register.enterEmailToRegister("em1@gmail.com");
		register.clickOnSubmit();
		String alreadyExits = register.ifAlreadUsedAlert();
		System.out.println(alreadyExits);
		Assert.assertEquals(alreadyExits.trim(), text.trim());
		report.log("email already exits.", "FAILED");
		Assert.assertTrue(false);
	}

}
