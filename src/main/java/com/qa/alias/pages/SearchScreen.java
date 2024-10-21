package com.qa.alias.pages;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.alias.constants.AppConstants;
import com.qa.alias.factory.SeleniumFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchScreen {
	/*
	 * Global Variable
	 */
	AndroidDriver driver;
	SeleniumFactory sf =new SeleniumFactory();

	/*
	 *  Giving life to driver by Constracor
	 */
	public SearchScreen(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
	 * Locaters and Storing In Variable
	 */
	@AndroidFindBy(id = "org.alias:id/searchBarEditText")
	private WebElement searchBarIcon;
	
	
	/**
	 * User Defined Method To UI Actions
	 * @throws IOException 
	 */
	public boolean verifysearchBarIconIsPresentInTheScreen(String searchBarIconScreenVerification) {
		return sf.verifyElementIsHavingExpectedText(searchBarIcon, searchBarIconScreenVerification, AppConstants.casesenstive);
	}
	
	public void searchForTheProduct(String productName) throws IOException, InterruptedException {
		sf.tap(searchBarIcon, driver);
		searchBarIcon.clear();
		sf.enterInputIntoTheElement(searchBarIcon, productName);
		Thread.sleep(2000);
	}
	
	public void swipeTillProductNameVisableAndTapOnIt(String expectedPoductName) throws IOException {
		driver.hideKeyboard();
		sf.swipe(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='org.alias:id/searchResultName' and @text=\""+expectedPoductName+"\"]")), 20, driver);
		By expectedProductLocator = By.xpath("//android.widget.TextView[@resource-id='org.alias:id/searchResultName' and @text=\""+expectedPoductName+"\"]");
		WebElement expectedProduct =driver.findElement(expectedProductLocator);
		sf.tap(expectedProduct, driver);
	}
	
}
