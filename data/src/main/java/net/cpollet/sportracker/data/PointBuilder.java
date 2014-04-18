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

import net.cpollet.sportracker.quantities.FrequencyQuantity;
import net.cpollet.sportracker.quantities.LengthQuantity;
import net.cpollet.sportracker.quantities.SpeedQuantity;
import net.cpollet.sportracker.quantities.TemperatureQuantity;
import net.cpollet.sportracker.units.FrequencyUnit;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class PointBuilder {
	private DateTime timestamp;
	private SpeedQuantity speed;
	// private PowerQuantity power;
	private FrequencyQuantity cadence;
	private FrequencyQuantity heartRate;
	private LengthQuantity altitude;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private TemperatureQuantity temperature;

	private PointBuilder() {
	}

	public static PointBuilder aPoint() {
		return new PointBuilder();
	}

	public PointBuilder withTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public PointBuilder withSpeed(SpeedQuantity speed) {
		this.speed = speed;
		return this;
	}

	public PointBuilder withCadence(FrequencyQuantity cadence) {
		this.cadence = cadence;
		return this;
	}

	public PointBuilder withHeartRate(FrequencyQuantity heartRate) {
		this.heartRate = heartRate;
		return this;
	}

	public PointBuilder withAltitude(LengthQuantity altitude) {
		this.altitude = altitude;
		return this;
	}

	public PointBuilder withLongitude(BigDecimal longitude) {
		this.longitude = longitude;
		return this;
	}

	public PointBuilder withLatitude(BigDecimal latitude) {
		this.latitude = latitude;
		return this;
	}

	public PointBuilder withTemperature(TemperatureQuantity temperature) {
		this.temperature = temperature;
		return this;
	}

	public Point build() {
		Point point = new Point();
		point.setTimestamp(timestamp);
		point.setSpeed(speed);
		point.setCadence(cadence);
		point.setHeartRate(heartRate);
		point.setAltitude(altitude);
		point.setLongitude(longitude);
		point.setLatitude(latitude);
		point.setTemperature(temperature);
		return point;
	}
}
