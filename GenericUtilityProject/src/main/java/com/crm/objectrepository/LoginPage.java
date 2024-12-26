package com.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.webdriverutility.WebDriverUtility;

/**
 * 
 * @author rashmi
 * Contains Login Page elements and business methods like login
 *
 */
public class LoginPage extends WebDriverUtility{
	WebDriver driver;//Global Variable
		
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
    @FindBy(name="user_name") 
    private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement LoginBtn;

	
	
	//Rule :3 Object Encapsulation
	
	public WebElement getUsernameEdt1() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return LoginBtn;
	}
	
	//rule 5 Provide Action
	
	/**
	 * Login to application based on url, username, password arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	
	public void logintoapp(String url ,String username, String password) {
		waitForPagetoLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		LoginBtn.click();
	}
	

}
