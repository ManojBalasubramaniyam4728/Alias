package com.qa.alias.pages;

import java.io.IOException;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.alias.constants.AppConstants;
import com.qa.alias.factory.SeleniumFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignInScreen {
	/*
	 * Global Variable
	 */
	AndroidDriver driver;
	SeleniumFactory sf =new SeleniumFactory();

	/*
	 *  Giving life to driver by Constracor
	 */
	public SignInScreen(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
	 * Locaters and Storing In Variable
	 */
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign In']")
	private WebElement signInText;
	
	@AndroidFindBy(id = "org.alias:id/signInUsernameEditText")
	private WebElement userNameTextField;
	
	@AndroidFindBy(id = "org.alias:id/signInPasswordEditText")
	private WebElement passwordTextField;
	
	@AndroidFindBy(id = "org.alias:id/signInButton")
	private WebElement signInButton;
	
	/**
	 * User Defined Method To UI Actions
	 * @throws IOException 
	 */
	
	public boolean verifySignInTextIsPresentInTheScreen(String signInTextScreenVerification) throws IOException {
		return sf.verifyElementIsHavingExpectedText(signInText, signInTextScreenVerification, AppConstants.casesenstive);
	}
	
	public void loginWithUserNameAndPassword(String userName, String password) throws IOException {
		sf.tap(userNameTextField, driver);
		userNameTextField.clear();
		sf.enterInputIntoTheElement(userNameTextField, userName);
		sf.tap(passwordTextField, driver);
		passwordTextField.clear();
		sf.enterInputIntoTheElement(passwordTextField, password);
		sf.tap(signInButton, driver);
	}
	
	
}
