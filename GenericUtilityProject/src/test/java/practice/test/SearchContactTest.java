package practice.test;

import org.testng.annotations.Test;

import com.crm.basetest.BaseClass;
import com.crm.objectrepository.LoginPage;

/**
 * test class for contact module
 * @author rashmi
 *
 */

public class SearchContactTest extends BaseClass {
	/**
	 * Scenario :login()===>navigateContact====>createContact()====verify(provide high level scenario)
	 */
	
	@Test
	public void SearchContactTest() {
		
		LoginPage lp=new LoginPage(driver);
		lp.logintoapp("url", "username", "password");
		
	}
	
	
	

}
