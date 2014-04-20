/*
 * Copyright 2014 Christophe Pollet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.cpollet.sportracker.cucumber.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.cpollet.sportracker.BmiCalculator;
import net.cpollet.sportracker.BmiCalculatorImpl;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Christophe Pollet
 */
public class BmiSteps {
	private long bmi;

	private BmiCalculator bmiCalculator;

	private CommonSteps commonSteps;

	public BmiSteps(CommonSteps commonSteps) {
		this.commonSteps = commonSteps;
	}

	@Before
	public void setUp() {
		bmiCalculator = new BmiCalculatorImpl();
	}

	@Given("^the BMI is computed$")
	public void compute() {
		bmi = bmiCalculator.compute(commonSteps.getPerson());
	}

	@Then("^the BMI is (\\d+)$")
	public void assewrtBmi(Long expectedBmi) throws Throwable {
		assertThat(bmi).isEqualTo(expectedBmi);
	}
}
