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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgwithPhonenoTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		 //Read data from Properties file
		FileInputStream fis=new FileInputStream("./configAppData/commondata.properties");
		
		Properties pobj=new Properties();
		pobj.load(fis);
		
		
		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
	    String PASSWORD = pobj.getProperty("password");
	    
	    //generate random data
      Random random=new Random();
		
		int randomint = random.nextInt(1000);
	    
	    //Read data from Excel sheet
	    
	    FileInputStream fis1=new FileInputStream("./testData/TestScripdata.xlsx");
	    Workbook wb=WorkbookFactory.create(fis1);
	    Sheet Sh=wb.getSheet("Org");
		Row row=Sh.getRow(7);
		String orgName=row.getCell(2).toString()+randomint;
		String phoneno=row.getCell(3).toString();
		
		wb.close();
		
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
		
	    driver.findElement(By.id("phone")).sendKeys(phoneno);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
						
		
		//Verify Header Phoneno info Expected Result
		
		String actPhoneno = driver.findElement(By.id("dtlview_Phone")).getText();
         if(actPhoneno.equals(phoneno)) {
			
			System.out.println(phoneno + "is created==pass");
			
		}else {
			
			System.out.println(phoneno + "is not created==failed");
		}
		
		
		
		// step 5: logout
		
		Thread.sleep(2000);
		
		Actions act=new Actions(driver);
		
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
		 
	    
	    
	
}
	

}
