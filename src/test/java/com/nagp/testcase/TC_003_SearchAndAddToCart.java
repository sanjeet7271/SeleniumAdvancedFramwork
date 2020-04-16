package com.nagp.testcase;

import java.io.IOException;

import org.testng.annotations.Test;

import com.nagp.framework.constants.FrameworkConstant;
import com.nagp.pages.Pages;
import com.nagp.utility.selenium.SeleniumFramework;
/**
 * 
 * @author sanjeetpandit
 *
 */
public class TC_003_SearchAndAddToCart  implements SeleniumFramework, Pages{
	
	@Test(priority=1, description = "Navigation to Home Page")
	public void initialization() throws IOException {
		String url=FrameworkConstant.GLOBALCONFIG.get("URL");
		driver.get(url);
		logger.info(url);
	}
	
	@Test(priority=2, description = "Search a product and place order for it")
	public void verify_Search_Product_And_AddToCart() throws InterruptedException {
		//homePage.verifyHomePageTitle();
		homePage.hoverOnWomenLink();
		homePage.findTShirtlinkAndHover();
		homePage.clickOnTShirtLink();
		String productName=homePage.findFirstproductSearch();
		homePage.productSearch(productName);
		homePage.clickOnSearchButton();
		homePage.SearchProductAndAddToCart();
		homePage.findCartAndHover();
	}

}
