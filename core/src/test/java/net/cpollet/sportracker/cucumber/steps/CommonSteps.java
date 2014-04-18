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

import cucumber.api.java.en.Given;
import net.cpollet.sportacker.Person;
import net.cpollet.sportacker.quantities.LengthQuantity;
import net.cpollet.sportacker.quantities.MassQuantity;
import net.cpollet.sportacker.units.LengthUnit;
import net.cpollet.sportacker.units.MassUnit;
import org.joda.time.LocalDate;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class CommonSteps {
	private Person person;

	public Person getPerson() {
		return person;
	}

	@Given("^A person$")
	public void createPerson() {
		person = new Person();
	}

	@Given("^A ([a-z]+) person$")
	public void createPersonWithGender(String gender) {
		person = new Person();
		person.setGender(Person.Gender.valueOf(gender.toUpperCase()));
	}

	@Given("^the person is (\\d+) years old$")
	public void setAge(int age) {
		LocalDate now = new LocalDate();
		person.setBirthdate(now.minusYears(age).toDate());
	}

	@Given("^the person weights (\\d+|\\d*(?:.\\d+)) kilograms$")
	public void setWeight(BigDecimal weight) {
		person.setWeight(new MassQuantity(weight, MassUnit.kg));
	}

	@Given("^the person is (\\d+|\\d*(?:.\\d{1,2})) meter tall$")
	public void setHeight(BigDecimal height) {
		person.setHeight(new LengthQuantity(height, LengthUnit.m));
	}

}
