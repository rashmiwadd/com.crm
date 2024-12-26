package practice.test;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.basetest.BaseClass;


public class invoivecreate extends BaseClass {

	@Test
	public void createinvoic() {
		System.out.println("Excute cc");
		String acttitle = driver.getTitle();
		Assert.assertEquals(acttitle, "Login234");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");

	}	 


	@Test
	public void createinvoicwithphone() {
		System.out.println("execution ccphoneno");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");





	}


}


