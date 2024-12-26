package com.crm.basetest;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.LoginPage;

public class BaseClass1 {
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public WebDriver driver =null;

	@BeforeSuite(groups= {"smokeTest","regresionTest"})
	public void configBS() {
		System.out.println("===Conecct to db, Report congig==");		

	}

	//@Parameters("BROWSER")  //when fetching parameter from suite (xml) for cross browser execution
	@BeforeClass(groups= {"smokeTest","regresionTest"})
	//public void configBc(String browserName) throws IOException { //apply this
		public void configBc() throws IOException { //comment this
		System.out.println("==Launch Browser==");
		String browser = //browserName;    //apply this
				flib.getDataFromProperties("browser");  //comment this
		if(browser.equalsIgnoreCase("Chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("Firefox")) {
			driver=new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}

	}

	@BeforeMethod(groups= {"smokeTest","regresionTest"})
	public void configBm() throws IOException {
		System.out.println("==Login==");
		String URL = flib.getDataFromProperties("url");
		String USERNAME = flib.getDataFromProperties("username");
		String PASSWORD = flib.getDataFromProperties("password");
		LoginPage lp = new LoginPage(driver);
		lp.logintoapp(URL,USERNAME, PASSWORD);

	}

	@AfterMethod(groups= {"smokeTest","regresionTest"})
	public void configAm() {
		System.out.println("==Logout==");
		HomePage hp = new HomePage(driver);
		hp.logout();

	}

	@AfterClass(groups= {"smokeTest","regresionTest"})
	public void configAc() {
		System.out.println("==Close the browser==");
		driver.quit();

	}

	@AfterSuite(groups= {"smokeTest","regresionTest"})
	public void configAS() {
		System.out.println("===close DB, Report backup==");		

	}



}
