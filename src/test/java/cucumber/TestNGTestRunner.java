package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/cucumber", glue = "onlinesystems.stepDefinitions", plugin = {
		"pretty", "html:target/cucumber-reports/cucumber.html" }, monochrome = true , tags = "@Regression")

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

	
	
}
