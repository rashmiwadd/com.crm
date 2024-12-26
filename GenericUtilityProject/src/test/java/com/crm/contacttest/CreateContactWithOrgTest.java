package com.crm.contacttest;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Create Object
				FileUtility flib = new FileUtility();
				ExcelUtility elib=new ExcelUtility();
				JavaUtility jlib=new JavaUtility();
				WebDriverUtility wlib=new WebDriverUtility();
		
		
		
		
		 //Read data from Properties file
		
		
		
				String BROWSER = flib.getDataFromProperties("browser");
				String URL = flib.getDataFromProperties("url");
				String USERNAME = flib.getDataFromProperties("username");
			    String PASSWORD = flib.getDataFromProperties("password");
			    
	    //generate random data
      
	    
	    //Read data from Excel sheet
			    
		String orgName=elib.getDataFromExcel("Contact", 7, 2)+ jlib.getRandonNumber();
		String ContactLastName=elib.getDataFromExcel("Contact", 7, 3)+ jlib.getRandonNumber();
	   
	   		
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
		
		// step 2: navigate to organization
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 3 :click on create organization button
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4: enter all the details and click on create button
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify Header msg Expected Result
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(orgName)) {
			
			System.out.println(orgName + "header is verified==pass");
			
		}else {
			
			System.out.println(orgName + "header is not created==failed");
		}
		
		//Verify Header orgName info Expected Result
		String actualorgnameinfo = driver.findElement(By.id("dtlview_Organization Name")).getText();
         if(actualorgnameinfo.equals(orgName)) {
			
			System.out.println(orgName + "is created==pass");
			
		}else {
			
			System.out.println(orgName + "is not created==failed");
		}
         
         
         //since it is is integration test case we try create contact
         
         //step 5 : navigate to Contact module
         driver.findElement(By.linkText("Contacts")).click();
         
         //step 6: Click on "Create Contact" button
         
         driver.findElement( By.xpath("//img[@title='Create Contact...']")).click();
         
         // Step: 7 enter all the details and  create contact
         
         driver.findElement(By.name("lastname")).sendKeys(ContactLastName);
         driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
         
         
         //swith to child window
         
         wlib.switchToTabOnURL(driver, "module=Accounts");
         
               	
        	driver.findElement(By.name("search_text")).sendKeys(orgName);
        	driver.findElement(By.name("search")).click();
        	driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click(); 
        	
        	//this is dynamic xpath which takes orgname during time 
        	//it is static now xpath("//a[text()='facebook12']") 
        	//it is static now xpath("//a[text()='facebook12']")
        	
        	//switch to parent window
        	
        	wlib.switchToTabOnURL(driver, "Contacts&action");
           
                   	
            driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
            	
        	       //verify the contact last name info expected result
            String headerinfo1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
            if(headerinfo1.equals(ContactLastName)) {
   			
   			System.out.println(ContactLastName + "is created==pass");
   			
   		}else {
   			
   			System.out.println(ContactLastName + "is not created==failed");
   		}
        	
	
            //verify the orgname in contacts module info expected result	
        
            String actorgnameinfo = driver.findElement(By.id("mouseArea_Organization Name")).getText();
            if(actorgnameinfo.trim().equals(orgName)) {
  			
  			System.out.println(orgName + "is created==pass");
  			
  		}else {
  			
  			System.out.println(orgName + "is not created==failed");
  		}
  		
		
		
		
		// step : logout
		
		Thread.sleep(2000);
		
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
            }
	}


	
	

	
	


