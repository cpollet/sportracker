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
import cucumber.api.java.en.When;
import net.cpollet.sportacker.quantities.DurationQuantity;
import net.cpollet.sportacker.quantities.EnergyQuantity;
import net.cpollet.sportacker.quantities.LengthQuantity;
import net.cpollet.sportacker.quantities.MassQuantity;
import net.cpollet.sportacker.quantities.Quantity;
import net.cpollet.sportacker.quantities.SpeedQuantity;
import net.cpollet.sportacker.units.DurationUnit;
import net.cpollet.sportacker.units.EnergyUnit;
import net.cpollet.sportacker.units.LengthUnit;
import net.cpollet.sportacker.units.MassUnit;
import net.cpollet.sportacker.units.SpeedUnit;
import net.cpollet.sportacker.units.Unit;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Christophe Pollet
 */
public class QuantitySteps {
	private Class<? extends Quantity> initialQuantityClass;
	private Quantity initialQuantity;
	private Unit initialUnit;
	private Quantity resultQuantity;
	private Unit resultUnit;

	private Map<String, Class<? extends Quantity>> quantityClasses;
	private Map<String, Unit> unitInstances;

	@Before
	public void setUp() {
		quantityClasses = new HashMap<>();
		quantityClasses.put("length", LengthQuantity.class);
		quantityClasses.put("energy", EnergyQuantity.class);
		quantityClasses.put("duration", DurationQuantity.class);
		quantityClasses.put("speed", SpeedQuantity.class);
		quantityClasses.put("mass", MassQuantity.class);

		unitInstances = new HashMap<>();
		insertUnit(LengthUnit.cm);
		insertUnit(LengthUnit.m);
		insertUnit(LengthUnit.km);

		insertUnit(EnergyUnit.J);
		insertUnit(EnergyUnit.kcal);
		insertUnit(EnergyUnit.cal);

		insertUnit(DurationUnit.s);
		insertUnit(DurationUnit.min);
		insertUnit(DurationUnit.h);

		insertUnit(SpeedUnit.ms);
		insertUnit(SpeedUnit.kmh);

		insertUnit(MassUnit.kg);
		insertUnit(MassUnit.g);
	}

	private void insertUnit(Unit unit) {
		unitInstances.put(unit.getName(), unit);
	}

	@Given("^a (.+) quantity of (\\d+|\\d*(?:.\\d+)) (.+)$")
	public void createQuantity(String quantityName, BigDecimal value, String unitName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		initialQuantityClass = quantityClasses.get(quantityName);
		initialUnit = unitInstances.get(unitName);

		initialQuantity = initialQuantityClass //
				.getDeclaredConstructor(BigDecimal.class, Unit.class) //
				.newInstance(value, initialUnit);
	}

	@When("^the quantity is converted to (.+)$")
	public void convertQuantityTo(String unitName) throws Throwable {
		resultUnit = unitInstances.get(unitName);
		resultQuantity = initialQuantity.convertTo(resultUnit);
	}

	@Then("^the resulting value is (\\d+|\\d*(?:.\\d+))$")
	public void assertResultingValueIs(BigDecimal result) throws Throwable {
		assertThat(resultQuantity.getUnit()).isEqualTo(resultUnit);
		assertThat(resultQuantity.getScaledValue().compareTo(result)) //
				.overridingErrorMessage("got " + resultQuantity.getScaledValue() + " but expecting " + result) //
				.isEqualTo(0);
	}

	@When("^adding (\\d+|\\d*(?:.\\d+)) (.+)$")
	public void addingValue(BigDecimal value, String unitName) throws Throwable {
		Quantity addedQuantity = initialQuantityClass //
				.getDeclaredConstructor(BigDecimal.class, Unit.class) //
				.newInstance(value, unitInstances.get(unitName));

		resultQuantity = initialQuantity.add(addedQuantity);
	}

	@When("^subtracting (\\d+|\\d*(?:.\\d+)) (.+)$")
	public void subtractingValue(BigDecimal value, String unitName) throws Throwable {
		Quantity addedQuantity = initialQuantityClass //
				.getDeclaredConstructor(BigDecimal.class, Unit.class) //
				.newInstance(value, unitInstances.get(unitName));

		resultQuantity = initialQuantity.subtract(addedQuantity);
	}

	@Then("^the resulting quantity is (\\d+|\\d*(?:.\\d+)) in original unit$")
	public void assertResultingQuantity(BigDecimal result) throws Throwable {
		assertThat(resultQuantity.getUnit()).isEqualTo(initialUnit);
		assertThat(resultQuantity.getScaledValue().compareTo(result)) //
				.overridingErrorMessage("got " + resultQuantity.getScaledValue() + " but expecting " + result) //
				.isEqualTo(0);
	}


	@When("^the quantity is divided by a (.+) of (\\d+|\\d*(?:.\\d+)) (.+)$")
	public void divideQuantityByAnotherOne(String quantityName, BigDecimal value, String unitName) throws Throwable {
		Class divisorQuantityClass = quantityClasses.get(quantityName);
		Unit divisorUnit = unitInstances.get(unitName);

		Quantity divisorQuantity = (Quantity) divisorQuantityClass //
				.getDeclaredConstructor(BigDecimal.class, Unit.class) //
				.newInstance(value, divisorUnit);

		resultQuantity = ((LengthQuantity) this.initialQuantity).divide(divisorQuantity);
	}

	@Then("^the resulting quantity is a (.+) of (\\d+|\\d*(?:.\\d+)) (.+)$")
	public void assertResultingQuantity(String quantityName, BigDecimal value, String unitName) throws Throwable {
		Class expectedQuantityClass = quantityClasses.get(quantityName);

		assertThat(resultQuantity).isInstanceOf(expectedQuantityClass);
		assertThat(resultQuantity.getUnit().getName()).isEqualTo(unitName);
		assertThat(resultQuantity.getScaledValue().compareTo(value))
				.overridingErrorMessage("got " + resultQuantity.getScaledValue() + " but expecting " + value)
				.isEqualTo(0);
	}

	@When("^scaled$")
	public void scaleQuantity() throws Throwable {
		resultQuantity = initialQuantityClass //
				.getConstructor(BigDecimal.class, Unit.class) //
				.newInstance(initialQuantity.getScaledValue(), initialUnit);
	}
}
