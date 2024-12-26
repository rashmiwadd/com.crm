package com.crm.contacttest;

import java.io.FileInputStream;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

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


public class CreateContactWithSupportDataTest {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Create Object
		FileUtility flib = new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();

        //Read data from Properties file

		String BROWSER = flib.getDataFromProperties("browser");
		String URL = flib.getDataFromProperties("url");
		String USERNAME = flib.getDataFromProperties("username");
	    String PASSWORD = flib.getDataFromProperties("password");
	    
	    
	    //Read data from Excel sheet
	    
	    String LastName=elib.getDataFromExcel("Contact", 1, 2)+ jlib.getRandonNumber();
		  
	        
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
	    
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    
	    driver.get(URL);
	    driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		// step 2: navigate to Contacts
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 3 :click on create contact button
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		
		String startdate = jlib.getSystemDateYYYYDDMM();
		String enddate = jlib.getRequiredDateYYYYDDMM(30);
		
		
		
		//step 4: enter all the details and click on create button
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startdate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(enddate);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		
		
		//Verify Header Start support and end support date info Expected Result
		String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
        if(actstartdate.equals(startdate)) {
			
			System.out.println(actstartdate + "is Verified==pass");
			
		}else {
			
			System.out.println(actstartdate + "is not Verified==failed");
		}
        
        
        String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
        if(actstartdate.equals(enddate)) {
			
			System.out.println(enddate + "is Verified==pass");
			
		}else {
			
			System.out.println(enddate + "is not Verified==failed");
		}
		
		
		
		// step 5: logout
		
		Thread.sleep(2000);
		
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
		 
	    
	    
	
}
	

}
