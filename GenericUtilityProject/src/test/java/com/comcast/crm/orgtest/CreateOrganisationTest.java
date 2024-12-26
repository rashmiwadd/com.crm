package com.comcast.crm.orgtest;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.crm.basetest.BaseClass;
import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.listenerutility.UtilityClassObject;
import com.crm.objectrepository.CreatingNewOrganizationPage;
import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.LoginPage;
import com.crm.objectrepository.OrganizationInfoPage;
import com.crm.objectrepository.OrganizationPage;

@Listeners(com.crm.listenerutility.ListImpClass.class)
public class CreateOrganisationTest extends BaseClass {

	@Test(groups="smokeTest")
	public void CreateOrganizationTest() throws IOException, InterruptedException {

		//Create Object

		//Read data from Properties file
		//Read data from Properties file

		//generate random data

		//Read data from Excel sheet

		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel sheet");
		String orgName=elib.getDataFromExcel("Org", 1, 2)+jlib.getRandonNumber();


		//step 1 :Login to the app(from base class @BM)

		wlib.waitForPagetoLoad(driver);
		//step 2: navigate to organization

		UtilityClassObject.getTest().log(Status.INFO, "navigate to organization");  
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();


		//step 3 :click on create organization button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Create org organization Page");  
		OrganizationPage cnp= new OrganizationPage(driver);
		cnp.getCreateNewOrgbtn().click();

		//step 4: enter all the details and click on create button
		UtilityClassObject.getTest().log(Status.INFO, "Create organization");  
		CreatingNewOrganizationPage cno=new CreatingNewOrganizationPage(driver);
		cno.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName+"organization Created");  
		


		//Verify Header msg Expected Result

		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actorgName = oip.getGetHeaderMsg().getText();
		boolean status = actorgName.contains(orgName);
		Assert.assertEquals(status, true);

		/*

		if(actorgName.contains(orgName)) {
			System.out.println(orgName + "name is verified==pass");
		}else {
			System.out.println(orgName + "name is not verified==fail");

		 */
	}



	@Test(groups="regressionTest")
	public void CreateOganisationWithIndustriesTest() throws EncryptedDocumentException, IOException, InterruptedException {

		//Read data from Excel sheet

		String orgName1=elib.getDataFromExcel("Org",4, 2)+jlib.getRandonNumber();
		String industry=elib.getDataFromExcel("Org", 4, 3);
		String type=elib.getDataFromExcel("Org", 4, 4);

		//implicitly wait
		wlib.waitForPagetoLoad(driver);

		//navigate to organization and click on create organization button
		HomePage hp1 = new HomePage(driver);
		hp1.getOrglink().click();

		//click on create new org
		OrganizationPage cn=new OrganizationPage(driver);
		cn.getCreateNewOrgbtn().click();

		//step 4: enter all the details and click on create button

		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createorgwithindusty(orgName1, industry,type);
		//cnop.createorgwithindusty(orgName1, type );

		//Verify the industry info
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);

		String actindustry = oip.getIndustryinfo().getText();
		SoftAssert assertobj=new SoftAssert();
		assertobj.assertEquals(actindustry,industry);
		assertobj.assertAll();
		/*
	        if(actindustry.equals(industry)) {

				System.out.println(industry + "is verified==pass");

			}else {

				System.out.println(industry + "is not verified==failed");
			}
		 */

	}


	@Test(groups="regressionTest") 
	public void CreateOrgwithPhonenoTest() throws EncryptedDocumentException, IOException {

		wlib.waitForPagetoLoad(driver);
		// get the data from excel sheet
		String orgName=elib.getDataFromExcel("Org", 7, 2)+jlib.getRandonNumber();
		String phoneno= elib.getDataFromExcel("Org", 7, 3);


		//step 2: navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();


		//step 3 :click on create organization button
		OrganizationPage cnp= new OrganizationPage(driver);
		cnp.getCreateNewOrgbtn().click();	

		//step 4: enter all the details and click on create button

		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createorgwithphone(orgName, phoneno);

		//Verify Header Phoneno info Expected Result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);

		String actPhoneno = oip.getPhoneinfo().getText();
		SoftAssert assertobj=new SoftAssert();
		assertobj.assertEquals(actPhoneno,phoneno);
		assertobj.assertAll();


		/*
		         if(actPhoneno.equals(phoneno)) {

					System.out.println(phoneno + "is created==pass");

				}else {

					System.out.println(phoneno + "is not created==failed");
				}
		 */



	}





}
