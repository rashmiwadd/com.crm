package com.comcast.crm.contacttest;

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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.basetest.BaseClass;
import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutility.JavaUtility;
import com.crm.objectrepository.ContactInfoPage;
import com.crm.objectrepository.ContactPage;
import com.crm.objectrepository.CreatingNewContactPage;
import com.crm.objectrepository.CreatingNewOrganizationPage;
import com.crm.objectrepository.HomePage;
import com.crm.objectrepository.OrganizationInfoPage;
import com.crm.objectrepository.OrganizationPage;


public class CreateContactTest extends BaseClass {

	@Test(groups="smokeTest")
	public void createcontactTest() throws EncryptedDocumentException, IOException {
		//Create Object

		//Read data from Properties file

		//Read data from Excel sheet

		String LastName = elib.getDataFromExcel("contact", 1, 2)+ jlib.getRandonNumber();

		//step 1 :Login to the app

		// step 2: navigate to Contacts
		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		//step 3 :click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getContactbtn().click();

		//step 4: enter all the details and click on create button

		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(LastName);

		//Verify Header Last Name info Expected Result

		ContactInfoPage cip=new ContactInfoPage(driver);

		String actheaderinfo = cip.getGetHeaderMsg().getText();

		boolean status = actheaderinfo.contains(LastName);
		Assert.assertEquals(status, true);

		//Verify Last Name info Expected Result

		String actLastName = cip.getLastNameinfo().getText();
		SoftAssert assertobj=new SoftAssert();

		assertobj.assertEquals(actLastName, LastName);
		assertobj.assertAll();



		/*
		if(actLastName.equals(LastName)) {

			System.out.println(LastName + "is created==pass");

		}else {

			System.out.println(LastName + "is not created==failed");
		}
		 */

	}


	@Test(groups="regressionTest")
	public void createcontactwithsupportdateTest() throws EncryptedDocumentException, IOException, InterruptedException {
		//step 1 read TS from excel

		String LastName=elib.getDataFromExcel("Contact", 1, 2)+ jlib.getRandonNumber();

		//step 2 navigate to contact module

		HomePage hp = new HomePage(driver);
		hp.getContactlink().click();

		//step 3 :click on create contact button

		ContactPage cp = new ContactPage(driver);
		cp.getContactbtn().click();

		//step 4 enter all the & create new contact

		String startdate = jlib.getSystemDateYYYYDDMM();
		System.out.println(startdate);

		String enddate = jlib.getRequiredDateYYYYDDMM(30);
		System.out.println(enddate);

		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContactNewwithsupportdate(LastName, startdate, enddate);

		//Verify Header Start support and end support date info Expected Result
		CreatingNewContactPage ccp1 = new CreatingNewContactPage(driver);
		String actstartdate=ccp1.getSupportstartEdt().getText();
		//String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();

		SoftAssert assertobj=new SoftAssert();
		assertobj.assertEquals(actstartdate,startdate);
		assertobj.assertAll();




		/*
		if(actstartdate.equals(startdate)) {
			System.out.println(startdate + "is Verified==pass");
		}else {
			System.out.println(startdate + "is not Verified==failed");
		}
		 */
		String actenddate=ccp1.getSupportendEdt().getText();
		//String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();

		SoftAssert assertobj1=new SoftAssert();
		assertobj1.assertEquals(actenddate,enddate);
		assertobj.assertAll();


		/*
		if(actenddate.equals(enddate)) {
			System.out.println(enddate + "is Verified==pass");
		}else {
			System.out.println(enddate + "is not Verified==failed");
		}
		 */
	}



	@Test(groups="regressionTest")
	public void createcontactwithorgTest() throws EncryptedDocumentException, IOException {

		//Read data from Excel sheet

		String orgName=elib.getDataFromExcel("Contact", 7, 2)+ jlib.getRandonNumber();
		String ContactLastName=elib.getDataFromExcel("Contact", 7, 3)+ jlib.getRandonNumber();

		// step 2: navigate to organization
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		//step 3 click on create organization button
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgbtn().click();

		// Step: 4 enter all the details and  create organization
		
		CreatingNewContactPage ccnp=new CreatingNewContactPage(driver);
		ccnp.createContactWithOrg(orgName, ContactLastName);

		//CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		//cnop.createOrg(orgName);

		//Verify Header msg Expected Result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		
		String headerinfo = oip.getOrghederinfo().getText();
		Assert.assertEquals(headerinfo, orgName);

		/*if(headerinfo.contains(orgName)) {

			System.out.println(orgName + "header is verified==pass");

		}else {

			System.out.println(orgName + "header is not created==failed");
		}
		 */


		//Verify Header orgName info Expected Result

		OrganizationInfoPage oip1=new OrganizationInfoPage(driver); 
		String actualorgnameinfo = oip1.getOrgNameinfo().getText();
		Assert.assertEquals(actualorgnameinfo, orgName);


		/*
		if(actualorgnameinfo.equals(orgName)) {

			System.out.println(orgName + "is created==pass");

		}else {

			System.out.println(orgName + "is not created==failed");
		}
		 */

		//since it is is integration test case we try create contact

		//step 5 : navigate to Contact module
		//hp.getContactlink().click();
		//step 6: Click on "Create Contact" button
		//ContactPage cp = new ContactPage(driver);

		//cp.getContactbtn().click();

		// Step: 7 enter last name and click on "+" the details and  create contact

		//ContactPage cp1 = new ContactPage(driver);
		//cp1.getLastNameEdt().sendKeys(ContactLastName);

		//click insert + img from contact create page

		//CreatingNewContactPage ccp11 = new CreatingNewContactPage(driver);
		//ccp11.getInsertOrgimgBtn().click();

		//swith to child window

		//wlib.switchToTabOnURL(driver, "module=Accounts");

		//ccp11.getSearchEdt().sendKeys(orgName);
		//ccp11.getSearchbtn().click();
		//driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

		//switch to parent window

		//wlib.switchToTabOnURL(driver, "Contacts&action");

		//ccp11.getSaveBtn().click();


		//step :5 verify the orgname in contacts module info expected result	
		ContactInfoPage cip=new ContactInfoPage(driver);

		String actorgnameinfo = cip.getOrgNameconcreateinfo().getText();
		SoftAssert assertobj=new SoftAssert();
		assertobj.assertEquals(actualorgnameinfo, orgName);
		assertobj.assertAll();

		/*
		//System.out.println(actorgnameinfo);added to check what data we got
		if(actorgnameinfo.trim().equals(orgName)) {

			System.out.println(orgName + "is created==pass");

		}else {

			System.out.println(orgName + "is not created==failed");
		}
		 */


	}
}







// step 5: logout





