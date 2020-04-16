package com.nagp.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.nagp.utility.selenium.SeleniumFramework;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class SearchAndAddToCart implements SeleniumFramework {

	private static Logger logger = Logger.getLogger(SearchAndAddToCart.class);
	/**
	 * Xpaths
	 */
	String hoverOnWomenXpath = ".//li//a[@class='sf-with-ul' and @title='Women']";
	String tShirtXpth = ".//ul[@class='submenu-container clearfix first-in-line-xs']//li//li//a[contains(text(),'T-shirts')]";
	String productsXpath = ".//ul[contains(@class,'product_list grid row')]";

	String firstProductXpath = ".//div[@class='right-block']//h5//a[@class='product-name']";
	String searchBoxXpath = ".//input[@class='search_query form-control ac_input']";
	String searchButtonXpath = ".//button[@class='btn btn-default button-search']";
	String productBoxXpath = ".//ul[contains(@class,'product_list grid row')]";

	String clickOnProduct = ".//a[@class='product-name' and @title='Faded Short Sleeve T-shirts']";
	String clickOnPlus = ".//i[@class='icon-plus']";
	String clickOnMinus = ".//i[@class='icon-minus']";
	String price = ".//div[@class='price']";
	String totalItems = ".//input[@id='quantity_wanted']";
	String selectSize = ".//select[@id='group_1']//option";
	String selectColor = ".//li[@class='selected']//a[@id='color_14']";
	String addToCart = ".//button[@class='exclusive']";

	String expectedTotalPrice = ".//div[@class='layer_cart_row']//span[@class='ajax_block_products_total']";
	String continueShoping = ".//span[@class='continue btn btn-default button exclusive-medium']//span";
	String hoverOnCart = ".//div[@class='shopping_cart']//a";
	String removeItems = ".//span[@class='remove_link']//a";
	// CommonUtilty common = new CommonUtilty();

	String firstProduct = "Faded Short Sleeve T-shirts";

	public SearchAndAddToCart() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * 
	 * @param driver
	 * @return title of the page
	 * @throws InterruptedException
	 */
	public String verifyHomePageTitle() {
		String title = driver.getTitle();
		System.out.println(title);
		return title;
	}

	/**
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */

	public void hoverOnWomenLink() throws InterruptedException {
		if (element.waitForElement(By.xpath(String.format(hoverOnWomenXpath, "Women")))) {
			element.hoverCurser(By.xpath(hoverOnWomenXpath));

		}
		logger.info("--Successfully Navigated to New Payee Page.--");
	}

	public void findTShirtlinkAndHover() {
		if (element.waitForElement(By.xpath(String.format(tShirtXpth, "T-shirts")))) {
			element.hoverCurser(By.xpath(tShirtXpth));
		}
	}

	public void clickOnTShirtLink() {
		if (element.waitForElement(By.xpath(String.format(tShirtXpth, "T-shirts")))) {
			element.click(By.xpath(String.format(tShirtXpth, "T-shirts")), "T-shirts");
		}
	}

	public String findFirstproductSearch() {
		String productName = null;
		if (element.waitForElement(By.xpath(String.format(firstProductXpath, "T-shirts")))) {
			productName = element.getText(By.xpath(firstProductXpath));
			System.out.println(productName);
			Assert.assertEquals(productName, firstProduct, "alert is not same as expected.");
		}
		return productName;
	}

	public void productSearch(String productName) {
		if (element.waitForElement(By.xpath(String.format(searchBoxXpath, "1st Product name")))) {
			element.sendKeys(By.xpath(String.format(searchBoxXpath, "1st Product name")), productName);
			System.out.println(productName);
			Assert.assertEquals(productName, firstProduct, "alert is not same as expected.");
		}
	}

	public void clickOnSearchButton() {
		if (element.waitForElement(By.xpath(String.format(searchButtonXpath, "click on search button")))) {
			element.click(By.xpath(String.format(searchButtonXpath, "Search button")), "Search button");
		}
	}

	public void SearchProductAndAddToCart() {
		element.click(By.xpath(String.format(clickOnProduct, "click on product")), "click on product");
		for (int i = 0; i < 5; i++) {
			element.click(By.xpath(String.format(clickOnPlus, "add more item")), "add more item");
		}
		for (int i = 0; i < 2; i++) {
			element.click(By.xpath(String.format(clickOnMinus, "add more item")), "add more item");
		}
		String priceOfItem = driver.findElement(By.xpath(price)).getText();
		System.out.println(priceOfItem);
		String noOfItems = driver.findElement(By.xpath(totalItems)).getAttribute("value");
		System.out.println(noOfItems);
		double totalPrice = commonUtility.calculatePrice(priceOfItem, noOfItems);
		System.out.println(totalPrice);
		dropdown.selectValueFromList(By.xpath(selectSize), "M");

		element.click(By.xpath(String.format(addToCart, "click on Add to cart")), "click on Add to cart");

		String expectedPrice = element.getText(By.xpath(expectedTotalPrice));
		System.out.println(expectedPrice);
		double expectedDoublePrice = Double.parseDouble(commonUtility.onlyPrice(expectedPrice));
		System.out.println(expectedDoublePrice);
		Assert.assertEquals(totalPrice, expectedDoublePrice);

		element.click(By.xpath(String.format(continueShoping, "click on continue shopping")),
				"click on  continue shopping");
	}

	public void findCartAndHover() {
		if (element.waitForElement(By.xpath(String.format(hoverOnCart, "Cart")))) {
			element.hoverCurser(By.xpath(hoverOnCart));
		}

		element.click(By.xpath(String.format(removeItems, "click oto remove item from cart")), "remove item from cart");

	}

}
