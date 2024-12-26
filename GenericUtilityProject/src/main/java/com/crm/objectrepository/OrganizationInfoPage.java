package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(className="dvHeaderText")
	private WebElement getHeaderMsg;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgNameinfo;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industryinfo;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phoneinfo;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement orghederinfo;
	
	
	
	
	
	public WebElement getOrghederinfo() {
		return orghederinfo;
	}




	public WebElement getPhoneinfo() {
		return phoneinfo;
	}




	public WebElement getIndustryinfo() {
		return industryinfo;
	}




	public WebElement getOrgNameinfo() {
		return orgNameinfo;
	}




	public WebElement getGetHeaderMsg() {
		return getHeaderMsg;
	}
	
	
	
	

}
