package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement TypeDD;
	
	@FindBy(id="phone")
	private WebElement phoneEdt;
	
	
	
	
	
	

	public WebElement getPhoneEdt() {
		return phoneEdt;
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getTypeDD() {
		return TypeDD;
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//business method
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
		
	}
	
	
	public void createOrg12(String orgName) {
		orgNameEdt.sendKeys(orgName);
	}	
	public	void createorgwithindusty(String orgName ,String industry, String Type ) throws InterruptedException {
		
		orgNameEdt.sendKeys(orgName);
		Select sel=new Select(industryDD);
		sel.selectByValue(industry);
		Thread.sleep(2000);
		Select sel1=new Select(TypeDD);
		sel1.selectByValue(Type);
		saveBtn.click();
		
		
	}
	
	public	void createorgwithphone(String orgName,String phone) {
		orgNameEdt.sendKeys(orgName);
		phoneEdt.sendKeys(phone);
		saveBtn.click();
				
	}
	
	
	
	
	
	
	

}
