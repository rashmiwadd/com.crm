package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	WebDriver driver;
	public ContactInfoPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="support_start_date") 
	private WebElement startdateEdt;
	
	@FindBy(name="support_end_date") 
	private WebElement enddateEdt;
	
	@FindBy(id="dtlview_Last Name") //after creating contact last name locator
	private WebElement lastNameinfo;
	
	@FindBy(className="dvHeaderText") 
	private WebElement getHeaderMsg;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgNameconcreateinfo;
	
			
	public WebElement getOrgNameconcreateinfo() {
		return orgNameconcreateinfo;
	}

	public WebElement getStartdateEdt() {
		return startdateEdt;
	}

	public WebElement getEnddateEdt() {
		return enddateEdt;
	}

	public WebElement getLastNameinfo() {
		return lastNameinfo;
	}

	public WebElement getGetHeaderMsg() {
		return getHeaderMsg;
	}
	
	
	
	

}
	

