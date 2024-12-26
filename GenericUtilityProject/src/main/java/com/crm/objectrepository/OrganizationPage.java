package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	WebDriver driver;
	public OrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);	
	}
	
	
	@FindBy(name="search_text") 
	private WebElement searchedit;
	
	@FindBy(name="search_field") 
	private WebElement searchDD;
	
	@FindBy(name="submit") 
	private WebElement searhBtn;
		
 	
	public WebElement getSearchedit() {
		return searchedit;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSearhBtn() {
		return searhBtn;
	}

	@FindBy(xpath="//img[@title='Create Organization...']") 
	private WebElement createNewOrgbtn;
	
	public WebElement getCreateNewOrgbtn() {
		return createNewOrgbtn;
	}

	
	
	
	
	}
	
	
	


