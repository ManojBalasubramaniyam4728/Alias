package com.qa.alias.pages;

import java.io.IOException;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.alias.constants.AppConstants;
import com.qa.alias.factory.SeleniumFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LandingScreen {
	
	/*
	 * Global Variable
	 */
	AndroidDriver driver;
	SeleniumFactory sf =new SeleniumFactory();

	/*
	 *  Giving life to driver by Constracor
	 */
	public LandingScreen(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
	 * Locaters and Storing In Variable
	 */
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign Up']")
	private WebElement signUpText;
	
	
	/**
	 * User Defined Method To UI Actions
	 * @throws IOException 
	 */
	public boolean verifySignUpTextIsPresentInTheScreen(String signUpTextScreenVerification) throws IOException {
		return sf.verifyElementIsHavingExpectedText(signUpText, signUpTextScreenVerification, AppConstants.casesenstive);
	}
	
	public void tapOnSignInLink(int xCoordinate, int yCoordinate) {
		sf.tapCoordinate(driver, xCoordinate, yCoordinate);
	}
	
}
