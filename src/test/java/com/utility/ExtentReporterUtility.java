package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public static void setUpSparkReporter(String fileName) {

		ExtentSparkReporter extentsparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/"+ fileName);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentsparkReporter);
	}

	public static void createExtentTest(String testName) {

		ExtentTest test = extentReports.createTest(testName);
		extentTest.set(test);
	}

	public static ExtentTest getExtentTest() {

		return extentTest.get();
	}

	public static void flushReport() {
		extentReports.flush();
	}
}
