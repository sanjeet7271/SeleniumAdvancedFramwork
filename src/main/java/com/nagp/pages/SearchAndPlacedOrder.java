package com.nagp.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.nagp.utility.selenium.SeleniumFramework;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class SearchAndPlacedOrder implements SeleniumFramework {

	// private static Logger logger = Logger.getLogger(SearchAndPlacedOrder.class);
	String hoverOnWomenXpath = ".//li//a[@class='sf-with-ul' and @title='Women']";
	String filterfromLeft = ".//a[@class='ui-slider-handle ui-state-default ui-corner-all'][1]";
	String dressCode = ".//a[@class='product-name' and @title='Printed Dress']";

	String clickOnPlus = ".//i[@class='icon-plus']";
	String clickOnMinus = ".//i[@class='icon-minus']";
	String price = ".//div[@class='price']";
	String totalItems = ".//input[@id='quantity_wanted']";
	String selectSize = ".//select[@id='group_1']//option";
	String selectColor = ".//li[@class='selected']//a[@id='color_14']";
	String addToCart = ".//button[@class='exclusive']";

	String expectedTotalPrice = ".//div[@class='layer_cart_row']//span[@class='ajax_block_products_total']";
	String continueShoping = ".//span[@class='continue btn btn-default button exclusive-medium']//span";
	String proceedToCheckout = ".//a[@class='btn btn-default button button-medium']";

	String proceedToCheckout1 = ".//a[@class='button btn btn-default standard-checkout button-medium']";

	String emailAddr = ".//input[@id='email']";
	String password = ".//input[@id='passwd']";
	String signInButton = ".//button[@id='SubmitLogin']";
	String addComment = ".//div[@id='ordermsg']//textarea";

	String submit = ".//button[@class='button btn btn-default button-medium']";
	String iAgree = ".//div[@id='uniform-cgv']";
	String closePopUp = ".//a[@class='fancybox-item fancybox-close']";
	String shippingButton = ".//button[@class='button btn btn-default standard-checkout button-medium']";
	String payment = ".//a[@class='bankwire']";
	String paymentConfirm = ".//button[@class='button btn btn-default button-medium']";

	String cMessage = ".//div[@class='box']//p[@class='cheque-indent']";

	public SearchAndPlacedOrder() {
		PageFactory.initElements(driver, this);
	}

	public void filterPriceBar(int filterSize) {
		element.click(By.xpath(String.format(hoverOnWomenXpath, "click on women link")), "click on women link");
		WebElement slider = driver.findElement(By.xpath(filterfromLeft));
		javascriptExecute.scrollToView(By.xpath(filterfromLeft));
		Actions builder = new Actions(driver);
		int iCount = 0;
		int iRange = filterSize;
		if (iRange > 0) {
			for (iCount = 0; iCount < iRange; iCount++) {
				builder.moveToElement(slider).click(slider).sendKeys(Keys.ARROW_UP).perform();
			}
		} else {
			for (iCount = 0; iCount > iRange; iCount--) {
				builder.click(slider).sendKeys(Keys.ARROW_DOWN).perform();
			}
		}

	}

	public void placeAnOrder(String email, String pWord, String extraNote) {
		List<WebElement> list = driver.findElements(By.xpath(dressCode));
		list.get(1).click();

		for (int i = 0; i < 5; i++) {
			element.click(By.xpath(String.format(clickOnPlus, "add more item")), "add more item");
		}
		for (int i = 0; i < 2; i++) {
			element.click(By.xpath(String.format(clickOnMinus, "add more item")), "add more item");
		}
		String priceOfItem = driver.findElement(By.xpath(price)).getText();
		System.out.println(priceOfItem);
		String noOfItems = element.getAttribute(By.xpath(totalItems), "value");
		// String
		// noOfItems=driver.findElement(By.xpath(totalItems)).getAttribute("value");
		System.out.println(noOfItems);
		double totalPrice = commonUtility.calculatePrice(priceOfItem, noOfItems);
		System.out.println(totalPrice);
		dropdown.selectValueFromList(By.xpath(selectSize), "M");

		// element.click(By.xpath(String.format(selectColor, "select color")),"select
		// color");
		javascriptExecute.scrollToView(By.xpath(addToCart));
		element.click(By.xpath(String.format(addToCart, "click on Add to cart")), "click on Add to cart");

		String expectedPrice = element.getText(By.xpath(expectedTotalPrice));
		System.out.println(expectedPrice);
		double expectedDoublePrice = Double.parseDouble(commonUtility.onlyPrice(expectedPrice));
		System.out.println(expectedDoublePrice);
		Assert.assertEquals(totalPrice, expectedDoublePrice);

		element.click(By.xpath(String.format(proceedToCheckout, "click on proceed to checkout")),
				"proceed to checkout");

		javascriptExecute.scrollToView(By.xpath(proceedToCheckout1));
		element.click(By.xpath(String.format(proceedToCheckout1, "click on proceed to checkout")),
				"proceed to checkout");

		element.sendKeys(By.xpath(String.format(emailAddr, "insert email address")), email);
		element.sendKeys(By.xpath(String.format(password, "insert password")), pWord);
		element.click(By.xpath(String.format(signInButton, "click on SignIn Button")), "SignIn Button");
		element.sendKeys(By.xpath(String.format(addComment, "Add extra note")), extraNote);
		element.click(By.xpath(String.format(submit, "click on address submit Button")), "address submit Button");
		javascriptExecute.scrollToView(By.xpath(shippingButton));
		element.click(By.xpath(String.format(shippingButton, "click on shipping submit Button")),
				"shipping submit Button without agree click on terms and conditions check box");
		element.click(By.xpath(String.format(closePopUp, "close pop up")), "close pop up");
		element.click(By.xpath(String.format(iAgree, "click on agree checkbox")), "agree checkbox");

		element.click(By.xpath(String.format(shippingButton, "click on shipping submit Button")),
				"shipping submit Button with agree click on terms and conditions check box");
		javascriptExecute.scrollToView(By.xpath(payment));
		element.click(By.xpath(String.format(payment, "click on payment option")), "payment option");
		element.click(By.xpath(String.format(paymentConfirm, "click on confirm payment option")),
				"confirm payment option");

		String confimationMessage = element.getText(By.xpath(cMessage));
		Assert.assertEquals(confimationMessage, "Your order on My Store is complete.");

	}

}
