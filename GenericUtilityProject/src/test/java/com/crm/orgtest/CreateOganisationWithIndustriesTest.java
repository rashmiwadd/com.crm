package com.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutility.JavaUtility;

public class CreateOganisationWithIndustriesTest {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Create Object
		FileUtility flib = new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();


 //Read data from Properties file
		//Read data from Properties file
		String BROWSER = flib.getDataFromProperties("browser");
		String URL = flib.getDataFromProperties("url");
		String USERNAME = flib.getDataFromProperties("username");
	    String PASSWORD = flib.getDataFromProperties("password");
	    
	    
	    //generate random data
      
	    
	    //Read data from Excel sheet
	    
	    String orgName=elib.getDataFromExcel("Org",4, 1)+jlib.getRandonNumber();
	    String industry=elib.getDataFromExcel("Org", 4, 3);
	    String type=elib.getDataFromExcel("Org", 4, 4);
			   
	    
		
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
		
		// step 2: navigate to organization
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 3 :click on create organization button
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4: enter all the details and click on create button
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement wbsele1 = driver.findElement(By.name("industry"));
		Select sel1 = new Select(wbsele1);
		sel1.selectByVisibleText(industry);
		
		WebElement wbsele2 = driver.findElement(By.name("accounttype"));
		Select sel2 = new Select(wbsele2);
		sel2.selectByVisibleText(type);
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Verify the industry and Type info
		
		String actindustry = driver.findElement(By.id("dtlview_Industry")).getText();
		
        if(actindustry.equals(industry)) {
			
			System.out.println(industry + "is verified==pass");
			
		}else {
			
			System.out.println(industry + "is not verified==failed");
		}
		
        //Type
        
        String actType = driver.findElement(By.id("dtlview_Type")).getText();
		
        if(actType.equals(type)) {
			
			System.out.println(actType + "is verified==pass");
			
		}else {
			
			System.out.println(actType + "is not verified==failed");
		}
		
		
		
		// step 5: logout
		
		Thread.sleep(2000);
		
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
	}

}
