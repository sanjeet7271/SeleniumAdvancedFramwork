package com.nagp.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.nagp.utility.selenium.SeleniumFramework;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class RegisterPage implements SeleniumFramework {

	private static Logger logger = Logger.getLogger(RegisterPage.class);

	String signIn = ".//div[@class='header_user_info']//a[@class='login']";
	String emailBox = ".//input[@id='email_create']";
	String submitButton = ".//button[@id='SubmitCreate']";

	String titletext = ".//div[@class='radio-inline'][1]";

	String mensTitle = ".//input[@id='id_gender1']";
	String womensTitle = ".//input[@id='id_gender2']";

	String firstName = ".//input[@id='customer_firstname']";
	String lastName = ".//input[@id='customer_lastname']";
	String password = ".//input[@id='passwd']";

	String emailXpath = ".//input[@id='email']";

	String days = "//div[@id='uniform-days']//select[@id='days']//option";
	String months = ".//div[@id='uniform-months']//select[@id='months']//option";
	String years = ".//div[@id='uniform-years']//select[@id='years']//option";

	String newletter = ".//input[@id='newsletter']";
	String specialOffer = ".//input[@id='optin']";

	String getFirstName = ".//input[@id='firstname']";
	String getLastName = ".//input[@id='lastname']";

	String company = ".//input[@id='company']";
	String address1 = ".//input[@id='address1']";
	String address2 = ".//input[@id='address2']";
	String city = ".//input[@id='city']";
	String state = ".//select[@id='id_state']//option";
	String pincode = ".//input[@id='postcode']";
	String country = ".//div[@id='uniform-id_country']//select[@id='id_country']//option";

	String additionalInfo = ".//p[@class='textarea form-group']//textarea[@id='other']";
	String phone = ".//input[@id='phone']";
	String mobile = ".//input[@id='phone_mobile']";
	String aliadAddr = ".//input[@id='alias']";

	String submit = ".//button[@id='submitAccount']//span";
	String welcome = ".//div[@id='center_column']//p[@class='info-account']";
	String home = ".//li//a[@class='btn btn-default button button-small']";

	String logoutAfterRegister = ".//a[@class='logout']";

	String createPage = ".//div[@class='alert alert-danger']";
	String alert = ".//div[@class='alert alert-danger']";

	public RegisterPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnSignIn() {
		if (element.waitForElement(By.xpath(String.format(signIn, "SignIn Button")))) {
			element.click(By.xpath(String.format(signIn, "SignIn Button")), "SignIn Button");
		}
		logger.info("--Successfully clicked	 to SignIn button.--");
	}

	public void enterEmailToRegister(String email) {
		if (element.waitForElement(By.xpath(String.format(emailBox, "Email Address to Register")))) {
			element.sendKeys(By.xpath(String.format(emailBox, "Email Address to Register")), email);
			// Assert.assertEquals(productName, firstProduct,"alert is not same as
			// expected.");
		}
	}

	public void clickOnSubmit() {
		if (element.waitForElement(By.xpath(String.format(submitButton, "Click on Create an Account")))) {
			element.click(By.xpath(String.format(submitButton, "Create an Account")), "Click on Create an Account");
		}
	}

	public void personalInformationRegisterPage(String title, String fName, String lName, String emailId, String pWord,
			String day, String month, String year) {

		String getTitle = driver.findElement(By.xpath(titletext)).getText();
		System.out.println(getTitle);
		if (getTitle.equals(title)) {

			element.click(By.xpath(String.format(mensTitle, "click on Mr.")), "click on Mr. radio button");

		} else {
			element.click(By.xpath(String.format(womensTitle, "click on Mr.")), "click on Mr. radio button");

		}

		element.sendKeys(By.xpath(String.format(firstName, "send first name into textbox")), fName);
		element.sendKeys(By.xpath(String.format(lastName, "send first name into textbox")), lName);

		String email = driver.findElement(By.xpath(emailXpath)).getAttribute("value");
		System.out.println(email + " ------------" + emailId);
		Assert.assertEquals(email, emailId);

		element.sendKeys(By.xpath(String.format(password, "send first name into textbox")), pWord);

		dropdown.SelectDate(By.xpath(days), day);
		dropdown.SelectDate(By.xpath(months), month);
		dropdown.SelectDate(By.xpath(years), year);

		// element.click(By.xpath(String.format(newletter, "click on News letter")));
		// element.click(By.xpath(String.format(specialOffer, "click on special
		// offer")));
		String firstNametext = driver.findElement(By.xpath(getFirstName)).getAttribute("value");
		System.out.println(firstNametext);
		Assert.assertEquals(firstNametext, fName);
		String lastNametext = driver.findElement(By.xpath(getLastName)).getAttribute("value");
		Assert.assertEquals(lastNametext, lName);
	}

	public void addressRegisterPage(String cName, String Address1, String Address2, String City, String State,
			String zCode, String Country, String AInfo, String Phone, String Mobile, String Alias) {

		element.sendKeys(By.xpath(String.format(company, "send company name")), cName);
		element.sendKeys(By.xpath(String.format(address1, "send address1")), Address1);
		element.sendKeys(By.xpath(String.format(address2, "send address2")), Address2);
		element.sendKeys(By.xpath(String.format(city, "send city")), City);

		dropdown.selectValueFromList(By.xpath(state), State);

		element.sendKeys(By.xpath(String.format(pincode, "send picode")), zCode);

		dropdown.selectValueFromList(By.xpath(country), Country);

		element.sendKeys(By.xpath(String.format(additionalInfo, "send additional information")), AInfo);

		element.sendKeys(By.xpath(String.format(phone, "send phone number")), Phone);
		element.sendKeys(By.xpath(String.format(mobile, "send mobile number")), Mobile);
		element.sendKeys(By.xpath(String.format(aliadAddr, "send Alias information")), Alias);
		element.click(By.xpath(String.format(submit, "click on Register button")), "Register button");

		String registerMssge = driver.findElement(By.xpath(welcome)).getText().trim();
		System.out.println(registerMssge);
		Assert.assertEquals(registerMssge,
				"Welcome to your account. Here you can manage all of your personal information and orders.");
		element.click(By.xpath(String.format(home, "click on home to navigate home page")),
				"click on home to navigate home page");

		logger.info("Registration successfully!");

		element.click(By.xpath(String.format(logoutAfterRegister, "click on SignOut")), "Sign out");

	}

	public String ifAlreadUsedAlert() {
		String createPageText = driver.findElement(By.xpath(alert)).getText();
		System.out.println(createPageText);
		return createPageText;
	}

	public WebElement ifEmailIsAlreadyUsed() {
		WebElement alertEle = driver.findElement(By.xpath(alert));
		return alertEle;
	}

}
