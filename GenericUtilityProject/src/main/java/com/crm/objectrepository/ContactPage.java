package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	WebDriver driver;
	
	public  ContactPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
		

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement contactbtn;
	
	
	public WebElement getContactbtn() {
		return contactbtn;
	}
	
	



	
}


