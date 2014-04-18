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
import net.cpollet.sportracker.units.SpeedUnit;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class Point {
	private DateTime timestamp;
	private SpeedQuantity speed;
	// private PowerQuantity power;
	private FrequencyQuantity cadence;
	private FrequencyQuantity heartRate;
	private LengthQuantity altitude;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private TemperatureQuantity temperature;

	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	public SpeedQuantity getSpeed() {
		return speed;
	}

	public void setSpeed(SpeedQuantity speed) {
		this.speed = speed;
	}

	public FrequencyQuantity getCadence() {
		return cadence;
	}

	public void setCadence(FrequencyQuantity cadence) {
		this.cadence = cadence;
	}

	public FrequencyQuantity getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(FrequencyQuantity heartRate) {
		this.heartRate = heartRate;
	}

	public LengthQuantity getAltitude() {
		return altitude;
	}

	public void setAltitude(LengthQuantity altitude) {
		this.altitude = altitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public TemperatureQuantity getTemperature() {
		return temperature;
	}

	public void setTemperature(TemperatureQuantity temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "Point{" +
				"timestamp=" + timestamp +
				", speed=" + speed.in(SpeedUnit.kmh).scale(1) +
				", cadence=" + cadence +
				", heartRate=" + heartRate +
				", altitude=" + altitude +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", temperature=" + temperature +
				'}';
	}
}
