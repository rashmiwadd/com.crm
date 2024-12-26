package com.crm.contacttest;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.crm.basetest.BaseClass;
import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.objectrepository.ContactPage;
import com.crm.objectrepository.CreatingNewContactPage;
import com.crm.objectrepository.CreatingNewOrganizationPage;
import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.OrganizationPage;


public class CreateContactTest{

		public static void main(String[] args) throws IOException {
		//Create Object

		//Read data from Properties file

		//Create Object
		FileUtility flib = new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();


 //Read data from Properties file
		//Read data from Properties file
		String BROWSER = flib.getDataFromProperties("browser");
		String URL = flib.getDataFromProperties("url");
		String USERNAME = flib.getDataFromProperties("username");
	    String PASSWORD = flib.getDataFromProperties("password");
	    
		//Read data from Excel sheet

		String LastName = elib.getDataFromExcel("contact", 1, 2)+ jlib.getRandonNumber();

WebDriver driver =null;
	    
	    if(BROWSER.equals("Chrome")) {
	    	driver=new ChromeDriver();
	    }else if(BROWSER.equals("Firefox")) {
	    	driver=new FirefoxDriver();
	     }else if(BROWSER.equals("edge")) {
		    	driver=new EdgeDriver();
	     }else {
	    	 driver=new ChromeDriver();
	     }
		
		

//step 1 :Login to the app
	    
	    wlib.waitForPagetoLoad(driver);
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    
	    driver.get(URL);
	    driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
	    
		// step 2: navigate to Contacts
		
        driver.findElement(By.linkText("Contacts")).click();
		
		//step 3 :click on create contact button
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
	    //step 4: enter all the details and click on create button
				driver.findElement(By.name("lastname")).sendKeys(LastName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				

		//Verify Header Last Name info Expected Result
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actLastName.equals(LastName)) {

			System.out.println(LastName + "is created==pass");

		}else {

			System.out.println(LastName + "is not created==failed");
		}
	}


}



