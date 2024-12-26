package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.generic.fileutility.ExcelUtility;

public class getProductinfoTest {
	
	@Test(dataProvider="getData" )
	public void getProductinfo(String brandname,String productname) {
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		driver.manage().window().maximize();
		//search the product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandname,Keys.ENTER);
		//capture product info
		String price = driver.findElement(By.xpath("//span[text()='"+productname+"']/../../../..//span[@class='a-price-whole']")).getText();
		System.out.println(price);
		driver.quit();
	}
		
	
		@DataProvider
		public Object[][] getData() throws IOException{
		ExcelUtility elib = new ExcelUtility();
		int rowcount = elib.getRowCount("product");
		
			Object[][] objarr=new Object[rowcount][2];
			
			for(int i=0;i<rowcount;i++) {  //helps to create object array
							
			objarr[i][0]=elib.getDataFromExcel("product", i+1, 0);
			objarr[i][1]=elib.getDataFromExcel("product", i+1, 1);
			}
			return objarr;
			
		}
		
		
		}	





