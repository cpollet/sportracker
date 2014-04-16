package net.cpollet.sportracker.cucumber.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Christophe Pollet
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:cucumber/harris-benedict.feature"},
		glue = {"net.cpollet.sportracker.cucumber.steps"})
public class TestHarrisBenedict {
	// nothing
}
