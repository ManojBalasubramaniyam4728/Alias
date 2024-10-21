package com.qa.alias.factory;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class SeleniumFactory {

	/**
	 * global Variables
	 */
	public static AndroidDriver driver;
	private Process emulatorProcess;

	/**
	 * User Defined Method To open application(apk) with app Packag,Activity
	 * @throws URISyntaxException 
	 */
	public AndroidDriver openAppication(String platformName,String deviceName, String automationName,
			String appPackage,String appActivity,boolean noReset,
			boolean autoGrantpermissions,String mobileHubUrl ) throws IOException, URISyntaxException {
	
		// Creating Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();

		// Setting Capabilities
		cap.setCapability("platformName",platformName );
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("automationName", automationName);
		cap.setCapability("appPackage", appPackage);
		cap.setCapability("appActivity", appActivity);
		cap.setCapability("noReset", noReset);
		cap.setCapability("autoGrantPermissions", autoGrantpermissions);

		// url To server
		URI uri = new URI(mobileHubUrl);
        URL url = uri.toURL();

		// open the app
		driver = new AndroidDriver(url, cap);
		return driver;
	}
	
	
	/**
	 *User Defined Method To open application with apk from file
	 * @throws URISyntaxException 
	 */
	public AndroidDriver openApkFile(String apkFilePath, String platformName,
			      String deviceName,String automationName, boolean noReset,boolean autoGrantpermissions,
			      String mobileHubUrl) throws IOException, URISyntaxException {

		// apk path
		File file = new File(apkFilePath);

		// Createing capbilitites to open the app
		DesiredCapabilities cap = new DesiredCapabilities();

		// Setting Capabilities
		cap.setCapability("platformName",platformName );
		cap.setCapability("deviceName", deviceName);
		cap.setCapability("automationName", automationName);
		cap.setCapability("app", file.getAbsolutePath());
		cap.setCapability("noReset", noReset);
		cap.setCapability("autoGrantPermissions", autoGrantpermissions);

		// url To server
		URI uri = new URI(mobileHubUrl);
		URL url = uri.toURL();
		
		// open the app
		driver = new AndroidDriver(url, cap);
		return driver;
	}
	
	
	/**
	 * User Defined Method To Explicit Wait
	 */
	public void waitUntilvisibilityOf(WebElement element, int explicitWaitTime) throws IOException {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitUntilPresenceOf(By locator, int explicitWaitTime) throws IOException {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	
	/**
	 * User Defined Method To Implicit Wait
	 */
	public void implicitlyWait(AndroidDriver driver ,int implicitWaitTime) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
	}
	
	
	/**
	 * User Defined Method To Tap on Element
	 */
	public void tap(WebElement element,AndroidDriver driver) throws IOException {
		Point sourceLocation=element.getLocation();
		Dimension sourceSize=element.getSize();
		int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
		int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;
		
		PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap=new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
				PointerInput.Origin.viewport(), centerX, centerY));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	    driver.perform(Arrays.asList(tap));
	}	
	
	
	/**
	 * User Defined Method To Tap on coordinate
	 */
	public void tapCoordinate(AndroidDriver driver,int xCordinate, int yCordinates) {
		PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap=new Sequence(finger, 1);
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
				PointerInput.Origin.viewport(), xCordinate, yCordinates));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	    driver.perform(Arrays.asList(tap));
	}	
	
	
	
	/**
	 * Enter Input Into The Element
	 */
	public void enterInputIntoTheElement(WebElement element, String input) {
		element.sendKeys(input);
	}
	
	
	/**
	 * User Defined Method To Swipe To The Element
	 */
	public void swipe(WebElement element,int maximumCount,AndroidDriver driver) throws IOException {
		//Taking mobile X and y center of screen
		int centerScreenX=driver.manage().window().getSize().getWidth()/2;
		int centerScreenY=driver.manage().window().getSize().getHeight()/2;
		
		//Frome center of the screen swiping 30% upword
		int endY=(int) (driver.manage().window().getSize().getHeight()*0.02);
		
		int maxCount=maximumCount;
		int count=0;
			for(int i = count; i < maxCount; i++) {
			PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH, "finger");
		    Sequence swipe=new Sequence(finger, 1);
		    
			try {
			if(element.isDisplayed()) {
				break;
			       }
			    }
			catch(NoSuchElementException e) {
				swipe.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), centerScreenX, centerScreenY));
				swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(600),PointerInput.Origin.viewport(), centerScreenX, endY));
				swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Arrays.asList(swipe));
			}
			
			if(count >= maxCount) {
				break;
			} 
		}
	}
	
	
	/**
	 * User Defined Method To Swipe To The Element useing AndroidUIAutomator
	 */
	public void swipeByAndroidUIAutomator(String text) {
		 driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\""+text+"\"))"));
	}
	
	/**
	 * Verify Element Is having Expected Text
	 */
	public boolean verifyElementIsHavingExpectedText(WebElement element, String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getText();
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.equalsIgnoreCase(expectedText);
		} else {
			isTextMatching = actualText.equals(expectedText);
		}
		return isTextMatching;
	}
	
	
	/**
	 * Verify Element Is Contains Expected Text
	 */
	public boolean verifyElementIsContainsExpectedText(WebElement element, String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getText();
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.toLowerCase().contains(expectedText);
		} else {
			isTextMatching = actualText.contains(expectedText);
		}

		return isTextMatching;
	}
	
	
	/**
	 * Verify Attribute Of Element Is having Expected Text
	 */
	public boolean verifyAttributOfElementIsHavingExpectedText(WebElement element, String attributName,
			String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getAttribute(attributName);
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.equalsIgnoreCase(expectedText);
		} else {
			isTextMatching = actualText.equals(expectedText);
		}

		return isTextMatching;
	}
	
	
    /**
     * Verify Attribute Of Element Is Contains Expected Text
     */
	public boolean verifyAttributOfElementIsContainsExpectedText(WebElement element, String attributName,
			String expectedText, boolean ignoreCase) {
		// Get the text from the element
		String actualText = element.getAttribute(attributName);
		// Perform text verification (case insensitive)
		boolean isTextMatching;

		if (ignoreCase == true) {
			isTextMatching = actualText.toLowerCase().contains(expectedText);
		} else {
			isTextMatching = actualText.contains(expectedText);
		}

		return isTextMatching;

	}
	
	
	/**
	 * Verify Element is displayed are Not
	 */
	public boolean verifyElementIsDisplayedAreNot(WebElement element) {
		boolean isDisplayed;
		try {
			isDisplayed = element.isDisplayed();
			isDisplayed = true;

		} catch (NoSuchElementException e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}
	
	
	/**
	 * Convert String to Boolean
	 */
	public  boolean stringToBoolean(String Value) {
		boolean booleanValue = Boolean.parseBoolean(Value);
		return booleanValue;
	}
	
	
	/**
	 * Convert String to Int
	 */
	public  int stringToInteger(String Value) {
		int booleanValue = Integer.parseInt(Value);
		return booleanValue;
	}
	
	/**
	 * User Defined Method Get Data FromExcel
	 */
	public LinkedHashMap<String, String> getDataFromExcel(String excelPath, String sheetName, String uniqueData,
			boolean isTableVertical) throws IOException {
		FileInputStream fisExcel = new FileInputStream(excelPath);
		Workbook workbook = WorkbookFactory.create(fisExcel);
		DataFormatter df = new DataFormatter();
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		Sheet sheet = workbook.getSheet(sheetName);
		int lastRowNumber = sheet.getLastRowNum(); // return index ==> index

		String value = "";
		String actualTestCaseName = "";
		String actualKey = "";

		// For Horizontal Data Featching In Key Value Pair
		if (isTableVertical == false) {
			for (int i = 0; i <= lastRowNumber; i++) {
				String actualTestcase = df.formatCellValue(sheet.getRow(i).getCell(0));
				if (actualTestcase.equals(uniqueData)) {
					short lastcellNumber = sheet.getRow(i).getLastCellNum(); // return count/size ==> count-1
					for (int j = 1; j < lastcellNumber - 1; j++) {
						actualKey = df.formatCellValue(sheet.getRow(i - 1).getCell(j));
						value = df.formatCellValue(sheet.getRow(i).getCell(j));
						map.put(actualKey, value);
					}
					break;
				}
			}
		}

		// For Vertical Data Featching In Key Value Pair
		else if (isTableVertical == true) {
			for (int i = 1; i <= sheet.getRow(i).getLastCellNum(); i++) {

				try {
					actualTestCaseName = df.formatCellValue(sheet.getRow(0).getCell(i));

				} catch (Exception e) {
				}
				if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
					for (int j = 0; j <= sheet.getLastRowNum(); j++) {

						try {
							actualKey = df.formatCellValue(sheet.getRow(j).getCell(i - 1));
							try {
								value = df.formatCellValue(sheet.getRow(j).getCell(i));
							} catch (Exception e) {
							}

							if ((actualKey.isEmpty() && value.isEmpty()) || actualKey.isEmpty()) {
							} else {
								map.put(actualKey, value);
							}
						} catch (Exception e) {
						}
					}
					break;
				}
			}
		}
		workbook.close();
		fisExcel.close();
		return map;
	}
	
	
	/**
	 * User Defined Method write Data FromExcel
	 */
	public void WriteDataInToExcel(String excelPath, String sheetName, String uniqueData, String header,
			String data, boolean isTableVertical) throws EncryptedDocumentException, IOException {
			FileInputStream excelFile = new FileInputStream(new File(excelPath));
			Workbook workbook = WorkbookFactory.create(excelFile);
			Sheet sheet = workbook.getSheet(sheetName);
			DataFormatter df = new DataFormatter();
			boolean flag = false;
			String actualTestCaseName = "";
			String actualKey = "";

			// For Horizontal Data Write Into Excel
			if (isTableVertical == false) {
				for (int i = 0; i <= sheet.getLastRowNum(); i++) {
					try {
						actualTestCaseName = df.formatCellValue(sheet.getRow(i).getCell(0));
					} catch (Exception e) {
					}
					if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
						for (int j = 1; j <= sheet.getRow(i).getLastCellNum(); j++) {
							try {
								actualKey = df.formatCellValue(sheet.getRow(i - 1).getCell(j));
							} catch (Exception e) {
							}
							if (actualKey.equalsIgnoreCase(header)) {
								try {
									sheet.getRow(i).createCell(j).setCellValue(data);
								} catch (Exception e) {
								}
								flag = true;
								break;
							}
						}
					}
					if (flag == true) {
						break;
					}
				}
			}
			// For Vertical Data Write Into Excel
			else if (isTableVertical == true) {
				for (int i = 1; i <= sheet.getRow(i).getLastCellNum(); i++) {

					try {
						actualTestCaseName = df.formatCellValue(sheet.getRow(0).getCell(i));

					} catch (Exception e) {
					}
					if (actualTestCaseName.equalsIgnoreCase(uniqueData)) {
						for (int j = 0; j <= sheet.getLastRowNum(); j++) {

							try {
								actualKey = df.formatCellValue(sheet.getRow(j).getCell(i - 1));
							} catch (Exception e) {
							}
							if (actualKey.equalsIgnoreCase(header)) {
								try {
									sheet.getRow(j).createCell(i).setCellValue(data);
								} catch (Exception e) {
								}
								flag = true;
								break;
							}
						}
					}
					if (flag == true) {
						break;
					}
				}
			}
			FileOutputStream fos = new FileOutputStream(excelPath);
			workbook.write(fos);
			fos.close();
			workbook.close();
			excelFile.close();
	}
	
	
	/**
	 * User Defined Method Get Data Map
	 */
	public String fetchDatFromMap(LinkedHashMap<String, String> testConfigKeyValue, String value) {
		return value = testConfigKeyValue.get(value);
	}
	
	
	/**
	 * This method is to take screenshot
	 * @throws IOException
	 */
	public static String takeScreenshot() throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		FileUtils.copyFile(src, new File(path));
		return path;
	}
	
	/**
	 * Launch the Emulator
	 * @throws InterruptedException 
	 */
	public void launchEmulatore(String emulatorPath, String avdName) throws InterruptedException {

		// Build the command to run the emulator
		ProcessBuilder processBuilder = new ProcessBuilder(emulatorPath + "\\emulator", "-avd", avdName);

		// Set the working directory to the emulator path
		processBuilder.directory(new File(emulatorPath));

		try {
			// Start the emulator
			emulatorProcess = processBuilder.start();
			Thread.sleep(5000);
			System.out.println("Emulator launched with AVD: " + avdName);
			BufferedReader reader=new BufferedReader(new InputStreamReader(emulatorProcess.getInputStream()));
			String line;
			while ((line=reader.readLine())!= null) {
				System.out.println(line);
				if (line.contains("Boot completed")||line.contains("Successfully loaded snapshot")) {
					Thread.sleep(15000);
					System.out.print("Emulator is ready!");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to launch the emulator.");
		}
	}
	
	/**
	 * User Defined Method For Closing Application
	 */
	public void closingApplication(AndroidDriver driver) {
	       driver.quit();
	       driver=null;
	} 
}
