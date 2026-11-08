package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
          WebDriver driver;

		public CheckOutPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	By waitforCheckoutPageElement=By.xpath("//input[@placeholder='Select Country']");
	By result=By.cssSelector("section.ta-results");
	
	public void selectCountry(String countyName) {
		Actions act=new Actions(driver);
		//country.click();
        act.sendKeys(country, countyName).build().perform();
        waitForElementToAppear(result);
        selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
	}


	
}
