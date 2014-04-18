package net.cpollet.sportracker.cucumber.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Christophe Pollet
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		format = { "html:target/cucumber-report/TestHarrisBenedict", "pretty" }, //
		features = {"classpath:cucumber/daily-energy-need-calculator.feature"},
		glue = {"net.cpollet.sportracker.cucumber.steps"})
public class TestHarrisBenedict {
	// nothing
}
