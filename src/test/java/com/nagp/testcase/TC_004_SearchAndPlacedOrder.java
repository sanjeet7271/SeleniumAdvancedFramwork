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
public class TC_004_SearchAndPlacedOrder implements SeleniumFramework, Pages {
	
	String xlFilePath = "./src/main/resources/testData/RegisterAndLogin.xlsx";
	String sheetName = "order";
	ExcelSheetReader ProvideData = new ExcelSheetReader();
	Object[][] data;
	
	@Test(priority = 1, description = "Navigation to Home Page")
	public void initialization() throws IOException {
		String url = FrameworkConstant.GLOBALCONFIG.get("URL");
		driver.get(url);
		logger.info(url);
	}
	
	@DataProvider(name = "orderplace")
	public Object[][] registerInfo() throws Exception {
		data = ProvideData.testData(xlFilePath, sheetName);
		return data;
	}

	@Test(priority = 2, dataProvider = "orderplace",description = "filter the price range")
	public void filter_PriceBar_And_Place_An_Order(String filterSize, String email, String password, String extraNote) {

		placeOder.filterPriceBar(Integer.parseInt(filterSize));
		placeOder.placeAnOrder(email,password,extraNote);

	}

}
