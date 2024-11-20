package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.BaseTest;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {
	
	Logger logger=LoggerUtility.getLogger(this.getClass());
	ExtentSparkReporter extentsparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	
	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " +"PASSED");
		ExtentReporterUtility.getExtentTest().log(Status.PASS , result.getMethod().getMethodName() + " " +"PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " +"FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentReporterUtility.getExtentTest().log(Status.FAIL , result.getMethod().getMethodName() + " " +"FAILED");
		ExtentReporterUtility.getExtentTest().log(Status.FAIL,result.getThrowable().getMessage());
		
		Object testclass =result.getInstance();
		
		BrowserUtility browserUtility=((BaseTest)testclass).getInstance();
		logger.info("capturing the screenshot for failed tests");
		
		String screenshotPath=browserUtility.takeScreenshot(result.getMethod().getMethodName());
		logger.info("Adding screenshot to the HTML report");
		
		ExtentReporterUtility.getExtentTest().addScreenCaptureFromPath(screenshotPath);
		
	}
	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " +"SKIPPED");
		ExtentReporterUtility.getExtentTest().log(Status.SKIP , result.getMethod().getMethodName() + " " +"SKIPPED");
	}

	public void onStart(ITestContext context) {
		logger.info("Test Suite Started");
		ExtentReporterUtility.setUpSparkReporter("report.html");
	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suite Finished");
		ExtentReporterUtility.flushReport();
		
	}
}
