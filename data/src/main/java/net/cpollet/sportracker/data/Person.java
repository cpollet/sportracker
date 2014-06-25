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

package net.cpollet.sportracker.data;

import net.cpollet.sportracker.quantities.LengthQuantity;
import net.cpollet.sportracker.quantities.MassQuantity;
import net.cpollet.sportracker.quantities.Quantity;
import net.cpollet.sportracker.units.Length;
import net.cpollet.sportracker.units.Mass;

import java.util.Date;

/**
 * @author Christophe Pollet
 */
public class Person {
	public static final int version = 1;

	private String firstName;
	private String lastName;
	private Gender gender;
	private Date birthday;
	// private EvolvingQuantity<MassQuantity, Date> weights;
	private MassQuantity weight;
	private LengthQuantity height;

	public enum Gender {
		MALE, FEMALE
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

//	public void addWeight(MassQuantity mass, Date date) {
//		if (weights == null) {
//			weights = new EvolvingQuantity<>();
//		}
//
//		weights.addQuantity(mass, date);
//	}

//	public EvolvingQuantity<MassQuantity, Date> getWeights() {
//		return weights;
//	}

	public MassQuantity getWeight() {
		//return weights.getLastQuantity();
		return weight;
	}

	public void setWeight(MassQuantity weight) {
		//addWeight(mass, new Date());
		this.weight = weight;
	}

	public Quantity<Length> getHeight() {
		return height;
	}

	public void setHeight(LengthQuantity height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Person{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", gender=" + gender +
				", birthday=" + birthday +
				", weight=" + getWeight() +
				", height=" + getHeight() +
				'}';
	}
}
