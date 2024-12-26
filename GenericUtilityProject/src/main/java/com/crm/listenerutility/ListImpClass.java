package com.crm.listenerutility;

import java.io.File;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.basetest.BaseClass;
import com.google.common.io.Files;

public class ListImpClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public static ExtentReports reports;
	ExtentTest	test;
	//make it static so that using this ListImpClass name we can use  in every TC and using variable insert logs

	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		//Extent report configuration
		//Spark report config
		//create object of ExtentSparkReporter and provide file path where want to generate report
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		spark=new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM TEST suite result");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);

		//add env information and create test
		//create object of ExtentReports
		reports=new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", "windows-10");
		reports.setSystemInfo("BROWSER", "CHROME-100");

	}


	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup"); 
		reports.flush();
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("===== ===="+result.getMethod().getMethodName()+"===START====");
		 	test=reports.createTest(result.getMethod().getMethodName());
		 	UtilityClassObject.setTest(test);
	    test.log(Status.INFO,result.getMethod().getMethodName( )+"======STARTED====");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("===== ===="+result.getMethod().getMethodName()+"===END====");
		test.log(Status.PASS,result.getMethod().getMethodName( )+"======COMPLETED====");
	
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName=result.getMethod().getMethodName();

		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;

		//step 2 use getscreenshotas method
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filepath, testName+"_"+time);
		test.log(Status.FAIL,result.getMethod().getMethodName( )+"======FAILED====");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}









}
