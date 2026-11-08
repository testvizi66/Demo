package rahulshettyacademy.TestComponents;

import org.openqa.selenium.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;

public class BaseTest {
 
	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException {	
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions op=new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
		op.addArguments("headless");
		}
		driver=new ChromeDriver(op);
		driver.manage().window().setSize(new Dimension(1440, 900));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "gecko.exe");
		}
		else if(browserName.equalsIgnoreCase("edge")){
			System.setProperty("webdriver.edge.driver", "edge.exe");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplicatiion() throws IOException {
		driver=initializeDriver();
		ChromeOptions op=new ChromeOptions();
		op.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		 driver.manage().deleteAllCookies();
		 op.setAcceptInsecureCerts(true);
		landingpage=new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		//read json to string
	String jsonContent= FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
	//convert String to hashmap
	ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
	return data;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	
}
