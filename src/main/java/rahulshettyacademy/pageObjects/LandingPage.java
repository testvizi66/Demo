package rahulshettyacademy.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	
	//pagefactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
    @FindBy(id="login")
    WebElement loginbutton;
    
    @FindBy(css="div[class*='flyInOut ']")
    WebElement errormsg;
    
    public ProductCatalouge login(String email, String pass) {
    	userEmail.sendKeys(email);
    	password.sendKeys(pass);
    	 loginbutton.click();
    	 ProductCatalouge Productcatalog=new ProductCatalouge(driver);
    	 return Productcatalog;
    }
   
    
    
	public void goTo() {
		// TODO Auto-generated method stub
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	public String errorMessage() {
		waitForWebElementToAppear(errormsg);
		return errormsg.getText();
	}
}
