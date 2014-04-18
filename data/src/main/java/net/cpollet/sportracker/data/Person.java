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

import java.util.Date;

/**
 * @author Christophe Pollet
 */
public class Person {
	private Gender gender;
	private Date birthdate;
	private MassQuantity weight;
	private LengthQuantity height;

	public enum Gender {
		MALE, FEMALE
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public MassQuantity getWeight() {
		return weight;
	}

	public void setWeight(MassQuantity weight) {
		this.weight = weight;
	}

	public LengthQuantity getHeight() {
		return height;
	}

	public void setHeight(LengthQuantity height) {
		this.height = height;
	}
}
