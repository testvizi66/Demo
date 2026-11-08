package rahulshettyacademy.stepDefinition;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalouge;

public class SubmitOrder extends BaseTest{
	public LandingPage landingpage;
	public ProductCatalouge Productcatalog;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException{
		landingpage=launchApplicatiion();
	}

	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String name,String password)
	{
		Productcatalog=landingpage.login(name,password);
	}
	
	@When("^Add product (.+) in cart$")
	public void Add_product_in_cart(String productName) {
		
	}
	
	@When("^Checkout (.+) and submit the order.$")
	public void Checkout_and_submit_the_order(String productName) {
		
	}
	
	@Then("{string} is displayed on confirmation page.")
	public void msg_is_displayed_on_confirmation_page(String msg) {
		
	}
}
