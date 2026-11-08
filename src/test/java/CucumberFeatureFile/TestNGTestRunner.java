package CucumberFeatureFile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
features="src/test/java/CucumberFeatureFile",
glue="rahulshettyacademy.stepDefinition", 
monochrome = true,
plugin= {"pretty","html:target/cucumber.html"} )

public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
