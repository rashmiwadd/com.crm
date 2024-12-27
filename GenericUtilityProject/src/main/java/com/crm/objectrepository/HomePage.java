package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;//Global Variable
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Organizations") 
    private WebElement orglink;
	
	@FindBy(linkText="Contacts") 
    private WebElement contactlink;
	
	@FindBy(linkText="Products") 
    private WebElement prodlink;
	
	@FindBy(linkText="Campaigns") 
    private WebElement Campaignlink;
	
	@FindBy(linkText="More") 
    private WebElement morelink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']") 
    private WebElement adminimage;
	
	@FindBy(linkText="Sign Out") 
    private WebElement signOut;
	
		
	
	
		
	 public WebElement getProdlink() {
		return prodlink;
	}



	public WebElement getAdminimage() {
		return adminimage;
	}

	

	public WebElement getSignOut() {
		return signOut;
	}

	public WebElement getMorelink() {
		return morelink;
	}
	



	public WebElement getOrglink() {
		return orglink;
	}


	public WebElement getContactlink() {
		return contactlink;
	}


	public WebElement getCampaignlink() {
		return Campaignlink;
	}




	public void navigateToCampaginPage() {
		 
		 Actions act=new Actions(driver);
		 act.moveToElement(morelink).perform();
		 Campaignlink.click();
	 } 
	
   public void logout() {
	Actions act=new Actions(driver);
	act.moveToElement(adminimage).perform();
	signOut.click();
	
}
	
	
}
