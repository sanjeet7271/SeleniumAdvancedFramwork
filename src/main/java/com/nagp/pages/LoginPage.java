package com.nagp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.nagp.utility.selenium.SeleniumFramework;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class LoginPage implements SeleniumFramework {

	String signIn = ".//div[@class='header_user_info']//a[@class='login']";
	String emailAddr = ".//input[@id='email']";
	String password = ".//input[@id='passwd']";
	String signInButton = ".//button[@id='SubmitLogin']";
	String logout = ".//div[@class='header_user_info']//a[@class='logout']";

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnSignIn() {
		if (element.waitForElement(By.xpath(String.format(signIn, "SignIn Button")))) {
			element.click(By.xpath(String.format(signIn, "SignIn Button")), "SignIn Button");
		}
		logger.info("--Successfully clicked	 to SignIn button.--");
	}

	@SuppressWarnings("static-access")
	public void loginFunctions(String email, String pWord) {
		commonUtility.validEmailTest(email);
		element.sendKeys(By.xpath(String.format(emailAddr, "insert email address")), email);
		element.sendKeys(By.xpath(String.format(password, "insert password")), pWord);
		element.click(By.xpath(String.format(signInButton, "click on SignIn Button")), "SignIn Button");
		element.click(By.xpath(String.format(logout, "click on logout Button")), "logout Button");
	}
}
