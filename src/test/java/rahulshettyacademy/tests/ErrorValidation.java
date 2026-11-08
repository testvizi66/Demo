package rahulshettyacademy.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalouge;

public class ErrorValidation extends BaseTest{

	@Test(retryAnalyzer = Retry.class)
	//@Test(groups = "Errorhandling")
	public void submitOrder() throws IOException,InterruptedException{
		
		ProductCatalouge Productcatalog=landingpage.login("rameshtalekar15@gmail.com", "Ramesh@1");
		//assertEquals("Incorrect email or password.", Landingpage.errorMessage());
		assertEquals("Incorrect email orpassword.", landingpage.errorMessage());
	}
}
