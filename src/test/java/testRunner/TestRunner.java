package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = "api.tests", 
				
			monochrome=true, plugin= {"pretty", "html:target/cucumber-reports"},
					tags= "@get_all_users_and_validate"	

		
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}