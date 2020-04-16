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
public class TC_001_LoginPage implements SeleniumFramework, Pages {

	String xlFilePath = "./src/main/resources/testData/RegisterAndLogin.xlsx";
	String sheetName = "login";
	ExcelSheetReader ProvideData = new ExcelSheetReader();
	Object[][] data;

	@DataProvider(name = "loginpage")
	public Object[][] registerInfo() throws Exception {
		data = ProvideData.testData(xlFilePath, sheetName);
		return data;
	}

	@Test(priority = 1, description = "validate users")
	public void initialization() throws IOException {
		String url = FrameworkConstant.GLOBALCONFIG.get("URL");
		driver.get(url);
		logger.info(url);
	}

	@Test(priority = 2, dataProvider = "loginpage", description = "validate user credentials")
	public void verify_Valide_Users(String email, String password) {
		login.clickOnSignIn();
		login.loginFunctions(email, password);
	}

}
