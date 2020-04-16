package com.nagp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.nagp.utility.selenium.SeleniumFramework;

/**
 * 
 * @author sanjeetpandit
 *
 */
public class ContactPage implements SeleniumFramework {

	String contactLink = ".//div[@id='contact-link']";
	String subjectHeading = ".//select[@id='id_contact']//option";
	String email = ".//input[@class='form-control grey validate']";
	String OrderRef = ".//input[@class='form-control grey']";
	String message = ".//div[@class='form-group']//textarea";
	String sendButton = ".//button[@id='submitMessage']";
	String successfulText = ".//div[@id='center_column']//p";
	String home = ".//a[@class='btn btn-default button button-small']//span//i[@class='icon-chevron-left']";

	String successMessage = "Your message has been successfully sent to our team.";

	public ContactPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnContact() {
		if (element.waitForElement(By.xpath(String.format(contactLink, "Contact")))) {
			element.click(By.xpath(String.format(contactLink, "Contact")), "Contact");
		}
	}

	public void selectSubjectHeading(String subjectHead) {
		if (element.waitForElement(By.xpath(String.format(subjectHeading, "subject Heading")))) {
			dropdown.selectValueFromList(By.xpath(subjectHeading), subjectHead);
		}
	}

	public void emailAddress(String emailA) {
		if (element.waitForElement(By.xpath(String.format(email, "Email Address")))) {
			element.sendKeys(By.xpath(String.format(email, "Email Address")), emailA);
		}
	}

	public void orderRef(String orderRefer) {
		if (element.waitForElement(By.xpath(String.format(OrderRef, "Order reference")))) {
			element.sendKeys(By.xpath(String.format(OrderRef, "send Order Reference")), orderRefer);
		}
	}

	public void message(String iMessage) {
		if (element.waitForElement(By.xpath(String.format(message, "Message")))) {
			element.sendKeys(By.xpath(String.format(message, "Message")), iMessage);
		}
	}

	public void clickOnSend() {
		if (element.waitForElement(By.xpath(String.format(sendButton, "send button")))) {
			element.click(By.xpath(String.format(sendButton, "click on Send button")), "Send button");
		}
	}

	public void getSuccessfulMessage() {
		if (element.waitForElement(By.xpath(String.format(successfulText, "Successful Message")))) {
			String expectedPrice = element.getText(By.xpath(successfulText));
			Assert.assertEquals(expectedPrice, successMessage);
		}
	}
}
