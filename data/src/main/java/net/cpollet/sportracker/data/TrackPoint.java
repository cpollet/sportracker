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

import net.cpollet.sportracker.quantities.Quantity;
import net.cpollet.sportracker.units.Angle;
import net.cpollet.sportracker.units.Frequency;
import net.cpollet.sportracker.units.Length;
import net.cpollet.sportracker.units.Speed;
import net.cpollet.sportracker.units.Temperature;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class TrackPoint {
	private DateTime timestamp;
	private Quantity<Speed> speed;
	// private PowerQuantity power;
	private Quantity<Frequency> cadence;
	private Quantity<Frequency> heartRate;
	private Quantity<Length> altitude;
	private Quantity<Angle> longitude;
	private Quantity<Angle> latitude;
	private Quantity<Temperature> temperature;

	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Quantity<Speed> getSpeed() {
		return speed;
	}

	public void setSpeed(Quantity<Speed> speed) {
		this.speed = speed;
	}

	public Quantity<Frequency> getCadence() {
		return cadence;
	}

	public void setCadence(Quantity<Frequency> cadence) {
		this.cadence = cadence;
	}

	public Quantity<Frequency> getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Quantity<Frequency> heartRate) {
		this.heartRate = heartRate;
	}

	public Quantity<Length> getAltitude() {
		return altitude;
	}

	public void setAltitude(Quantity<Length> altitude) {
		this.altitude = altitude;
	}

	public Quantity<Angle> getLongitude() {
		return longitude;
	}

	public void setLongitude(Quantity<Angle> longitude) {
		this.longitude = longitude;
	}

	public Quantity<Angle> getLatitude() {
		return latitude;
	}

	public void setLatitude(Quantity<Angle> latitude) {
		this.latitude = latitude;
	}

	public Quantity<Temperature> getTemperature() {
		return temperature;
	}

	public void setTemperature(Quantity<Temperature> temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "TrackPoint{" +
				"timestamp=" + timestamp +
				", speed=" + speed +
				", cadence=" + cadence +
				", heartRate=" + heartRate +
				", altitude=" + altitude +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", temperature=" + temperature +
				'}';
	}
}
