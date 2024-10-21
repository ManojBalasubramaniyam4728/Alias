package com.qa.alias.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.alias.constants.AppConstants;
import com.qa.alias.factory.SeleniumFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomeScreen {
	/*
	 * Global Variable
	 */
	AndroidDriver driver;
	SeleniumFactory sf =new SeleniumFactory();

	/*
	 *  Giving life to driver by Constracor
	 */
	public HomeScreen(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
	 * Locaters and Storing In Variable
	 */
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='HOME']/..//android.widget.TextView[@selected='true']")
	private WebElement homeText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Account & Balance Updates']")
	private WebElement accountAndBalanceUpdates;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Close']")
	private WebElement closeIcon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/navigation_bar_item_small_label_view' and @text='SEARCH']")
	private WebElement searchIcon;
	
	
	/**
	 * User Defined Method To UI Actions
	 * @throws IOException 
	 */
	public boolean verifyHomeTextIsPresentInTheScreen(String homeTextScreenVerification) {
		return sf.verifyElementIsHavingExpectedText(homeText, homeTextScreenVerification, AppConstants.casesenstive);
	}
	
	public void ifAccountAndBalanceUpdatesIsPresentTapOnCancel() throws IOException {
	    boolean result=sf.verifyElementIsDisplayedAreNot(accountAndBalanceUpdates);
	    if (result==true) {
			sf.tap(closeIcon, driver);
		}
	}
	
	public void tapOnSearchIcon() throws IOException {
		sf.tap(searchIcon, driver);
	}
	

}
