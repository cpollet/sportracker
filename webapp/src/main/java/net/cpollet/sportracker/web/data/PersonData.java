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

package net.cpollet.sportracker.web.data;

import java.util.Date;

/**
 * @author Christophe Pollet
 */
public class PersonData {
	public enum Gender {
		MALE, FEMALE
	}

	public enum HeartRateMethod {
		CUSTOM, AUTO;
	}

	private Date birthday;
	private Gender gender;
	private Quantity weight;
	private Quantity height;
	private Quantity minHeartRate;
	private Quantity maxHeartRate;
	private HeartRateMethod maxHeartRateMethod;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Quantity getWeight() {
		return weight;
	}

	public void setWeight(Quantity weight) {
		this.weight = weight;
	}

	public Quantity getHeight() {
		return height;
	}

	public void setHeight(Quantity height) {
		this.height = height;
	}

	public Quantity getMinHeartRate() {
		return minHeartRate;
	}

	public void setMinHeartRate(Quantity minHeartRate) {
		this.minHeartRate = minHeartRate;
	}

	public Quantity getMaxHeartRate() {
		return maxHeartRate;
	}

	public void setMaxHeartRate(Quantity maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}

	public HeartRateMethod getMaxHeartRateMethod() {
		return maxHeartRateMethod;
	}

	public void setMaxHeartRateMethod(HeartRateMethod maxHeartRateMethod) {
		this.maxHeartRateMethod = maxHeartRateMethod;
	}

	@Override
	public String toString() {
		return "PersonData{" +
				"birthday=" + birthday +
				", gender=" + gender +
				", weight=" + weight +
				", height=" + height +
				", minHeartRate=" + minHeartRate +
				", maxHeartRate=" + maxHeartRate +
				", maxHeartRateMethod=" + maxHeartRateMethod +
				'}';
	}
}
