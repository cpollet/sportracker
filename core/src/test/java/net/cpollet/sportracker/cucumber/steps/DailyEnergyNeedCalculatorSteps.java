package net.cpollet.sportracker.cucumber.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.cpollet.sportacker.DailyEnergyNeedCalculator;
import net.cpollet.sportacker.HarrisBenedict;
import net.cpollet.sportracker.quantities.EnergyQuantity;
import net.cpollet.sportracker.units.EnergyUnit;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Christophe Pollet
 */
public class DailyEnergyNeedCalculatorSteps {
	private DailyEnergyNeedCalculator.ActivityLevel activityLevel;

	private HarrisBenedict harrisBenedictCalculator;

	private EnergyQuantity result;

	private CommonSteps commonSteps;

	public DailyEnergyNeedCalculatorSteps(CommonSteps commonSteps) {
		this.commonSteps = commonSteps;
	}

	@Before
	public void setUp() {
		harrisBenedictCalculator = new HarrisBenedict();
	}

	@Given("^the person's activity level is ([a-z]+)$")
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = DailyEnergyNeedCalculator.ActivityLevel.valueOf(activityLevel.toUpperCase());
	}

	@When("^the daily used energy is computed$")
	public void computeDailyUsedEnergy() {
		result = harrisBenedictCalculator.compute(commonSteps.getPerson(), activityLevel);
	}

	@Then("^the daily used energy is (\\d+) kcal$")
	public void assertDailyUsedEnergyIs(BigDecimal expectedResult) {
		EnergyQuantity expected = new EnergyQuantity(expectedResult, EnergyUnit.kcal);
		assertThat(result.compareTo(expected)).isEqualTo(0);
	}
}
