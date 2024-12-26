package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InvoiceTest {
	@Test(retryAnalyzer=com.crm.listenerutility.RetryLinstenerImp.class)
	public void createinvoic() {
		System.out.println("Excute cc");
	   Assert.assertEquals("", "Login234");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");

	}	
}
