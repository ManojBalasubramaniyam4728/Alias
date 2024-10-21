package com.qa.alias.pages;

import java.io.IOException;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.alias.constants.AppConstants;
import com.qa.alias.factory.SeleniumFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductDescriptionScreen {

	/*
	 * Global Variable
	 */
	AndroidDriver driver;
	SeleniumFactory sf =new SeleniumFactory();

	/*
	 *  Giving life to driver by Constracor
	 */
	public ProductDescriptionScreen(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
	 * Locaters and Storing In Variable
	 */
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='DETAILS']")
	private WebElement detailsText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactLabel' and @text='Nickname']")
	private WebElement nickName;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactLabel' and @text='SKU']/..//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactValue']")
	private WebElement skuValue;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactLabel' and @text='Retail Price']/..//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactValue']")
	private WebElement retailPriceValue;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactLabel' and @text='Release Date']/..//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactValue']")
	private WebElement releaseDateValue;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactLabel' and @text='Colorway']/..//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactValue']")
	private WebElement colorwayValue;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactLabel' and @text='Brand']/..//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactValue']")
	private WebElement brandValue;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactLabel' and @text='Nickname']/..//android.widget.TextView[@resource-id='org.alias:id/pdpInfoNutritionFactValue']")
	private WebElement nicknameValue;
	
	@AndroidFindBy(xpath =  "//android.widget.Button[@resource-id='org.alias:id/pdpStartListingButton']")
	private WebElement startListingText;
	
	
	
	/**
	 * User Defined Method To UI Actions
	 * @throws IOException 
	 */
	public boolean verifyDetailsTextIsPresentInTheScreen(String detailsTextScreenVerification) {
		return sf.verifyElementIsHavingExpectedText(detailsText, detailsTextScreenVerification, AppConstants.casesenstive);
	}
	
	public void swipeUntillNickNameIsVisable() throws IOException {
		sf.swipe(nickName, 5, driver);
	}
	
	public String getSKUValue() {
		return skuValue.getText();
	}
	
	public String getRetailPriceValue() {
		return retailPriceValue.getText();
	}
	
	public String getReleaseDateValue() {
		return releaseDateValue.getText();
	}
	
	public String getColorwayValue() {
		return colorwayValue.getText();
	}
	
	public String getBrandValue() {
		return brandValue.getText();
	}
	
	public String getNicknameValue() {
		return nicknameValue.getText();
	}
	
	public boolean verifyStartListingTextIsPresentInTheScreen(String startListingTextScreenVerification) {
		return sf.verifyElementIsHavingExpectedText(startListingText, startListingTextScreenVerification, AppConstants.casesenstive);
	}
}
