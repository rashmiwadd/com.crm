package com.crm.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewContactPage extends WebDriverUtility{

//POM Class Creation
	WebDriver driver;//Global Variable
	//constructor to initialize
	public CreatingNewContactPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Object Identification
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="support_start_date")
	private WebElement supportstartEdt;
	
	@FindBy(name="support_end_date")
	private WebElement supportendEdt;
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement InsertOrgimgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search")
	private WebElement searchbtn;
		
	
	
	// Object Encapsulation		

	
	
	
	public WebElement getSupportstartEdt() {
		return supportstartEdt;
	}



	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}



	public WebElement getSearchEdt() {
		return searchEdt;
	}



	public WebElement getSearchbtn() {
		return searchbtn;
	}



	public WebElement getInsertOrgimgBtn() {
		return InsertOrgimgBtn;
	}



	public WebElement getSupportendEdt() {
		return supportendEdt;
	}



	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	

	public WebElement getSaveBtn() {
		return saveBtn;
		
	}

	
	//Business metd to create contact
	
	
	//businessmethod to create contact

	public void createContact(String lastName) {
		waitForPagetoLoad(driver);
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void createContactNewwithsupportdate(String lastName, String startdate , String enddate) throws InterruptedException {
		waitForPagetoLoad(driver);
		lastNameEdt.sendKeys(lastName);
		supportstartEdt.clear();
		Thread.sleep(2000);
		supportstartEdt.sendKeys(startdate);
		Thread.sleep(2000);
		supportendEdt.clear();
		Thread.sleep(2000);
		supportendEdt.sendKeys(enddate);
		
		saveBtn.click();
		
	}
	
	public void createContactWithOrg(String OrgName, String lastName) {//this is integration Test case
		waitForPagetoLoad(driver);
		//HomePage hp=new HomePage(driver);
		//hp.getOrglink().click();
		//OrganizationPage op=new OrganizationPage(driver);
		//op.getCreateNewOrgbtn().click();
		//orgNameEdt.sendKeys(OrgName);//first create Oganization  
		//saveBtn.click();
		//hp.getContactlink().click();
		//ContactPage cp=new ContactPage(driver);
		//cp.getContactbtn().click();
		lastNameEdt.sendKeys(lastName);//and next go to create contact with orgname
		InsertOrgimgBtn.click();
		switchToTabOnURL(driver, "module=Accounts");
		searchEdt.sendKeys(OrgName);
		searchbtn.click();
		driver.findElement(By.xpath("//a[text()='\"+orgName+\"']")).click();
		switchToTabOnURL(driver, "Contacts&action");
		saveBtn.click();
	
		
		
	}
	
	
	
	
}
