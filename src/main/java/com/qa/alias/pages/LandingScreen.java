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
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign In if you already have an alias account']")
	private WebElement signInLink;
	
	
	/**
	 * User Defined Method To UI Actions
	 * @throws IOException 
	 */
	public boolean verifySignUpTextIsPresentInTheScreen(String signUpTextScreenVerification) throws IOException {
		return sf.verifyElementIsHavingExpectedText(signUpText, signUpTextScreenVerification, AppConstants.casesenstive);
	}
	
	public void tapOnSignInLink() {
		int elementX = signInLink.getLocation().getX();
	    int elementY = signInLink.getLocation().getY();
	    int elementWidth = signInLink.getSize().getWidth();
	    int elementHeight = signInLink.getSize().getHeight();

	    // Calculate the tap coordinates
	    // Assuming "Sign In" link is at the beginning
	    int tapX = elementX + (elementWidth /10);
	    int tapY = elementY + (elementHeight / 2);
		sf.tapCoordinate(driver, tapX, tapY);
	}
	
}
