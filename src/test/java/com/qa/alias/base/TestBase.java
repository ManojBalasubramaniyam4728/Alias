package com.qa.alias.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.qa.alias.constants.AppConstants;
import com.qa.alias.factory.SeleniumFactory;
import com.qa.alias.pages.HomeScreen;
import com.qa.alias.pages.LandingScreen;
import com.qa.alias.pages.ProductDescriptionScreen;
import com.qa.alias.pages.SearchScreen;
import com.qa.alias.pages.SignInScreen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TestBase {

	/**
	 * Global variable
	 */
	public SeleniumFactory sf;
	public AndroidDriver driver;
	private AppiumDriverLocalService service;
	public static Logger logger;
	protected HomeScreen homescreen;
	protected LandingScreen landingScreen;
	protected ProductDescriptionScreen productDescriptionScreen;
	protected SearchScreen searchScreen;
	protected SignInScreen signInScreen;

	/**
	 * This is to start the appium server in befoure suite
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	@BeforeSuite
	public void startAppiumServer() throws IOException, InterruptedException {
		sf = new SeleniumFactory();

		AppConstants.testConfigKeyValue = sf.getDataFromExcel(AppConstants.excelPath, AppConstants.testConfigSheetName,
				AppConstants.uniqueDataTestConfig, AppConstants.isTableVertical);

		String withIPAddress = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "withIPAddress");
		String usingPortInString = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "usingPort");
		int port = sf.stringToInteger(usingPortInString);
		String basePathe = "--" + sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "basePathe");
		String remortHostEndPoint = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "remortHostEndPoint");
		String emulatorPath = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "emulatorPath");
		String avdName = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "avdName");

		/**
		 * This to on the appium serevrs
		 */
		try {
			AppiumServiceBuilder builder = new AppiumServiceBuilder();
			builder.withIPAddress(withIPAddress);
			builder.usingPort(port);
			builder.withArgument(() -> basePathe, remortHostEndPoint);
			service = AppiumDriverLocalService.buildService(builder);
			service.start();
			Thread.sleep(5000);

			if (service.isRunning()) {
				System.out.println("Appium Server started on: " + service.getUrl());
			} else {
				throw new RuntimeException("Appium Server failed to start!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * This to on the emulator
		 * If Your running in the physical device then uncomment the 88 line
		 */
         sf.launchEmulatore(emulatorPath, avdName);
	}

	/**
	 * This is to open the application
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 */

	@BeforeTest
	public void openTheAppliaction() throws IOException, URISyntaxException, InterruptedException {
		sf = new SeleniumFactory();

		AppConstants.testConfigKeyValue = sf.getDataFromExcel(AppConstants.excelPath, AppConstants.testConfigSheetName,
				AppConstants.uniqueDataTestConfig, AppConstants.isTableVertical);

		String platformName = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "Platform_Name");
		String deviceName = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "Device_Name");
		String automationName = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "automationName");
		String appPackage = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "App_Package");
		String appActivity = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "App_Activity");
		String noResetInString = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "No_Reset");
		boolean noReset = sf.stringToBoolean(noResetInString);
		String autoGrantPermissionsInString = sf.fetchDatFromMap(AppConstants.testConfigKeyValue,
				"Auto_Grantpermissions");
		boolean autoGrantPermissions = sf.stringToBoolean(autoGrantPermissionsInString);
		String mobileHubUrl = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "Mobile_Hub_Url");
		String implicitWaitTimeInString = sf.fetchDatFromMap(AppConstants.testConfigKeyValue, "Implicit_Wait_Time");
		int implicitWaitTime = sf.stringToInteger(implicitWaitTimeInString);

		// driver=sf.openAppication(platformName, deviceName,automationName, appPackage,
		// appActivity, noReset, autoGrantPermissions, mobileHubUrl);
		Thread.sleep(2000);
		/**
		 * If User want to launch the application from apk then uncomment the 127 and
		 * comment the 122
		 */
		driver = sf.openApkFile(AppConstants.apkPath, platformName, deviceName, automationName, noReset,
				autoGrantPermissions, mobileHubUrl);
		sf.implicitlyWait(driver, implicitWaitTime);
		homescreen = new HomeScreen(driver);
		landingScreen = new LandingScreen(driver);
		productDescriptionScreen = new ProductDescriptionScreen(driver);
		searchScreen = new SearchScreen(driver);
		signInScreen = new SignInScreen(driver);

	}

	/**
	 * close the application
	 */
	@AfterTest
	public void tearDown() {
		sf.closingApplication(driver);
	}

	/**
	 * Close the server
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@AfterSuite
	public void stopAppiumServer() throws IOException, InterruptedException {
		/**
		 * this is to close the appium serever
		 */
		if (service != null && service.isRunning()) {
			service.stop();
			System.out.println("Appium Server stopped.");
		}

	}

}
