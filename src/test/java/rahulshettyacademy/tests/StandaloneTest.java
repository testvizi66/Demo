package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import rahulshettyacademy.pageObjects.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;

public class StandaloneTest {
public static void main(String args[]) {
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
	LandingPage landingpage=new LandingPage(driver);
	String productname="ZARA COAT 3";
	String url="https://rahulshettyacademy.com/client/#/auth/login";
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	landingpage.goTo();
	landingpage.login("rameshtalekar15@gmail.com", "Ramesh@15");
	ProductCatalouge productcatalog=new ProductCatalouge(driver);
	List<WebElement>  products=productcatalog.getProductList();
	    productcatalog.addProductToCart(productname);
	
       /* 
        driver.findElement(By.cssSelector("button[routerlink*=\"cart\"]")).click();
        
         List<WebElement> cartproducts=driver.findElements(By.cssSelector("div.cartSection h3"));
         Boolean match= cartproducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector("li.totalRow button")).click();
        
        Actions act=new Actions(driver);
        
        act.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        
        String msg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
        */
}
}
