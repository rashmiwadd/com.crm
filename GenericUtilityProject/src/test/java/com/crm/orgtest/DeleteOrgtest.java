package com.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.objectrepository.CreatingNewOrganizationPage;
import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.LoginPage;
import com.crm.objectrepository.OrganizationInfoPage;
import com.crm.objectrepository.OrganizationPage;

public class DeleteOrgtest {

	
public static void main(String[] args) throws IOException, InterruptedException {
		
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
			    
	    
	    //generate random data
      	    
	    //Read data from Excel sheet
	    
	   
		String orgName=elib.getDataFromExcel("Org", 1, 2)+jlib.getRandonNumber();
		
		
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
	        	    
	    driver.get(URL);
	    
	   LoginPage lp = new  LoginPage(driver);
	   
	   lp.logintoapp(URL,USERNAME, PASSWORD);;
	   	   
		
		//step 2: navigate to organization
		 HomePage hp=new HomePage(driver);
		 hp.getOrglink().click();
	
			
		//step 3 :click on create organization button
		OrganizationPage cnp= new OrganizationPage(driver);
		cnp.getCreateNewOrgbtn().click();
				
		//step 4: enter all the details and click on create button
		 CreatingNewOrganizationPage cno=new CreatingNewOrganizationPage(driver);
		 cno.createOrg(orgName);
		  	
				
		//Verify Header msg Expected Result
		 
		 OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actorgName = oip.getGetHeaderMsg().getText();
		 
		if(actorgName.contains(orgName)) {
			System.out.println(orgName + "name is verified==pass");
		}else {
			System.out.println(orgName + "name is not verified==fail");
		}
			
		
		//go back to organization page
		hp.getOrglink().click();
		
		//search for  organization
		cnp.getSearchedit().sendKeys(orgName);
		wlib.select(cnp.getSearchDD(),"Organization Name");
		
		//dynamic webtable select and delete
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../..//td[8]/a[text()='del']")).click();
		
		
		
		// step 5: logout
		
		Thread.sleep(2000);
		
		hp.logout();
	    
		
		driver.quit();
		
		 
}
}
