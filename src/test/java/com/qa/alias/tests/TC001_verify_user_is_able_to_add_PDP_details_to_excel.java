package com.qa.alias.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.alias.base.TestBase;
import com.qa.alias.constants.AppConstants;

public class TC001_verify_user_is_able_to_add_PDP_details_to_excel extends TestBase{

	@Test
	public void verifyUserIsAbleToAddPDPDetailsToExcel() throws IOException, InterruptedException {
		
		// Adding logger
		logger = Logger.getLogger("Verify User Is Able To Add PDP Details To Excel");
		PropertyConfigurator.configure(AppConstants.log4jfile);
		
		//Setting Base to intarct to excel
		AppConstants.tc001KeyValue= sf.getDataFromExcel(AppConstants.excelPath, AppConstants.testDataSheetName, AppConstants.uniqueDataTC001, AppConstants.isTableHorizontal);
		
		//Stroing the data which got from excel
		String landingScreenVerification = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Landing_Screen_Verification");
		String signInScreenVerification = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Sign_In_Screen_Verification");
		String emailID = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Email_ID");
		String password = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Password");
		String homeScreenVerification = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Home_Screen_Verification");
		String searchScreenVerification = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Search_Screen_Verification");
		String productName = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Product_Name");
		String expectedProductName = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Expected_Product_Name");
		String productDescriptionScreenVerification = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Product_Description_Screen_Verification");
		String startListingButtonVerification = sf.fetchDatFromMap(AppConstants.tc001KeyValue,"Start_Listing_Button_Verification");
		
		boolean signUpText=landingScreen.verifySignUpTextIsPresentInTheScreen(landingScreenVerification);
		Assert.assertTrue(signUpText);
		logger.info("Sign Up Text Is Present In The screen");
		landingScreen.tapOnSignInLink(250,2227);
		logger.info("Succssfully taped on the Sigin Link");
		
		boolean signInText=signInScreen.verifySignInTextIsPresentInTheScreen(signInScreenVerification);
		Assert.assertTrue(signInText);
		logger.info("Sign In Text Is Present In The screen");
		signInScreen.loginWithUserNameAndPassword(emailID, password);
		logger.info("Successfully loggined to the application");
		
		homescreen.ifAccountAndBalanceUpdatesIsPresentTapOnCancel();
		boolean homeText=homescreen.verifyHomeTextIsPresentInTheScreen(homeScreenVerification);
		Assert.assertTrue(homeText);
		logger.info("Home screen Text Is Present In The screen");
		homescreen.tapOnSearchIcon();
		logger.info("Succssfully taped on the Search Icon");
		
		boolean searchBarText=searchScreen.verifysearchBarIconIsPresentInTheScreen(searchScreenVerification);
		Assert.assertTrue(searchBarText);
		logger.info("search BarText Text Is Present In The screen");
		searchScreen.searchForTheProduct(productName);
		logger.info("Succssfully saerche "+productName+" Name");
		searchScreen.swipeTillProductNameVisableAndTapOnIt(expectedProductName);
		logger.info("Succssfully Swiped till expected Product Name is visable");
		
		boolean detailsText=productDescriptionScreen.verifyDetailsTextIsPresentInTheScreen(productDescriptionScreenVerification);
		Assert.assertTrue(detailsText);
		logger.info("Details Text Is Present In The screen");
		productDescriptionScreen.swipeUntillNickNameIsVisable();
		logger.info("Succssfully Swiped till Nick name Name is visable");
		String skuValue=productDescriptionScreen.getSKUValue();
		String releaseDateValue=productDescriptionScreen.getReleaseDateValue();
		String retailPriceValue=productDescriptionScreen.getRetailPriceValue();
		String colorwayValue=productDescriptionScreen.getColorwayValue();
		String brandValue=productDescriptionScreen.getBrandValue();
		String nickNameValue=productDescriptionScreen.getNicknameValue();
		logger.info("Succssfully featched value text from PDP screen");
		
		
		//writeing back the PDP Details to excel
		sf.WriteDataInToExcel(AppConstants.excelPath, AppConstants.testDataSheetName, AppConstants.uniqueDataTC001, "PDP_SKU", skuValue, AppConstants.isTableHorizontal);
		sf.WriteDataInToExcel(AppConstants.excelPath, AppConstants.testDataSheetName, AppConstants.uniqueDataTC001, "PDP_RetailPrice", releaseDateValue, AppConstants.isTableHorizontal);
		sf.WriteDataInToExcel(AppConstants.excelPath, AppConstants.testDataSheetName, AppConstants.uniqueDataTC001, "PDP_ReleaseDate", retailPriceValue, AppConstants.isTableHorizontal);
		sf.WriteDataInToExcel(AppConstants.excelPath, AppConstants.testDataSheetName, AppConstants.uniqueDataTC001, "PDP_Colorway", colorwayValue, AppConstants.isTableHorizontal);
		sf.WriteDataInToExcel(AppConstants.excelPath, AppConstants.testDataSheetName, AppConstants.uniqueDataTC001, "PDP_Brand", brandValue, AppConstants.isTableHorizontal);
		sf.WriteDataInToExcel(AppConstants.excelPath, AppConstants.testDataSheetName, AppConstants.uniqueDataTC001, "PDP_Nickname", nickNameValue, AppConstants.isTableHorizontal);
		logger.info("Succssfully Entered featched PDP data to execl");
		
		boolean startListing=productDescriptionScreen.verifyStartListingTextIsPresentInTheScreen(startListingButtonVerification);
		Assert.assertTrue(startListing);
		logger.info("Start Listing Is Present In The screen");
	}
}
