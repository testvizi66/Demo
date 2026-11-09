package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import rahulshettyacademy.AbstractComponent.AbstractComponent;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.ConfirmationPage;

public class SubmitOrderTest extends BaseTest{
     //changes from X person with some change
	//Submit order test flow
	//chnages from y persn
	String productname="ZARA COAT 3";
	String countryName="India";
	
	//@Test(dataProvider = "getdata", groups = "purchase")
	@Test(dataProvider = "getdata")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
	//String url="https://rahulshettyacademy.com/client/#/auth/login";
	ProductCatalouge Productcatalog=landingpage.login(input.get("email"),input.get("password") );
	List<WebElement>  products=Productcatalog.getProductList();
	    Productcatalog.addProductToCart(input.get("productname"));
	    CartPage CartPage=Productcatalog.goTocartPage();
	    Boolean match=CartPage.verifyProductnameDisplay(input.get("productname"));
	    Assert.assertTrue(match);
	    CheckOutPage CheckOutPage=CartPage.gotToCheckout();
	    CheckOutPage.selectCountry(countryName);
	    ConfirmationPage ConfirmationPage= CheckOutPage.submitOrder();
	    String confirmMsg=ConfirmationPage.getTextMessage();
	    Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
        
        /*
         List<WebElement> cartproducts=driver.findElements(By.cssSelector("div.cartSection h3"));
         Boolean match= cartproducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector("li.totalRow button")).click();
        ---------------------------------
        Actions act=new Actions(driver);
        
        act.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
        driver.findElement(By.cssSelector(".action__submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String msg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
        */
}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalouge Productcatalog=landingpage.login("rameshtalekar15@gmail.com", "Ramesh@15");
		OrderPage OrderPage=Productcatalog.goToOrderPage();
		Assert.assertTrue(OrderPage.verifyOrderDisplay(productname));
	}
	
	@DataProvider
	public Object[][] getdata() throws IOException {
		/*
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("email", "rameshtalekar15@gmail.com");
		map.put("password", "Ramesh@15");
		map.put("productname", "ZARA COAT 3");
		
		HashMap<String, String> map1=new HashMap<String, String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productname", "iphone 13 pro");
		*/
		//return new Object[][] {{"rameshtalekar15@gmail.com", "Ramesh@15","ZARA COAT 3"},{"shetty@gmail.com", "Iamking@000","iphone 13 pro"}};
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}};
	}
}
