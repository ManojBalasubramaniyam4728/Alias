package com.qa.alias.constants;

import java.util.LinkedHashMap;

public class AppConstants {
	
	public  static final String excelPath = System.getProperty("user.dir") + "/src/test/resource/config/alias.xlsx";
	public  static final String apkPath = System.getProperty("user.dir")+"/src/test/resource/config/org_alias_v1.36.1.apk.support.apk";
	public  static final String log4jfile = System.getProperty("user.dir")+"/src/test/resource/config/log4j.properties";
	public  static final String testConfigSheetName = "Test Configuration";
	public  static final String testDataSheetName = "Test Data";
	public  static final String uniqueDataTestConfig = "Test";
	public  static final String uniqueDataTC001 = "TC01";
	public  static LinkedHashMap<String, String> testConfigKeyValue = new LinkedHashMap<String, String>();
	public  static LinkedHashMap<String, String> tc001KeyValue = new LinkedHashMap<String, String>();
	public  static final boolean isTableVertical = true;
	public  static final boolean isTableHorizontal = false;
	public  static final boolean casesenstive  = true;

}
