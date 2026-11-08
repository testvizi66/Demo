package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css="div.cartSection h3")
	List<WebElement> cartproducts;
	
	@FindBy(css="li.totalRow button")
	WebElement checkoutButton;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyProductnameDisplay(String productName) {
		Boolean match=cartproducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	return match;
	}
   
	public CheckOutPage gotToCheckout() {
		checkoutButton.click();
		CheckOutPage CheckOutPage=new CheckOutPage(driver);
		return CheckOutPage;
	}
}
