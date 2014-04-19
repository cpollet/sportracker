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

package net.cpollet.sportracker.data.builder;

import net.cpollet.sportracker.data.TrackPoint;
import net.cpollet.sportracker.quantities.Quantity;
import net.cpollet.sportracker.units.Frequency;
import net.cpollet.sportracker.units.Length;
import net.cpollet.sportracker.units.Speed;
import net.cpollet.sportracker.units.Temperature;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class TrackPointBuilder {
	private DateTime timestamp;
	private Quantity<Speed> speed;
	// private PowerQuantity power;
	private Quantity<Frequency> cadence;
	private Quantity<Frequency> heartRate;
	private Quantity<Length> altitude;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private Quantity<Temperature> temperature;

	private TrackPointBuilder() {
	}

	public static TrackPointBuilder aTrackPoint() {
		return new TrackPointBuilder();
	}

	public TrackPointBuilder withTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public TrackPointBuilder withSpeed(Quantity<Speed> speed) {
		this.speed = speed;
		return this;
	}

	public TrackPointBuilder withCadence(Quantity<Frequency> cadence) {
		this.cadence = cadence;
		return this;
	}

	public TrackPointBuilder withHeartRate(Quantity<Frequency> heartRate) {
		this.heartRate = heartRate;
		return this;
	}

	public TrackPointBuilder withAltitude(Quantity<Length> altitude) {
		this.altitude = altitude;
		return this;
	}

	public TrackPointBuilder withLongitude(BigDecimal longitude) {
		this.longitude = longitude;
		return this;
	}

	public TrackPointBuilder withLatitude(BigDecimal latitude) {
		this.latitude = latitude;
		return this;
	}

	public TrackPointBuilder withTemperature(Quantity<Temperature> temperature) {
		this.temperature = temperature;
		return this;
	}

	public TrackPoint build() {
		TrackPoint trackPoint = new TrackPoint();
		trackPoint.setTimestamp(timestamp);
		trackPoint.setSpeed(speed);
		trackPoint.setCadence(cadence);
		trackPoint.setHeartRate(heartRate);
		trackPoint.setAltitude(altitude);
		trackPoint.setLongitude(longitude);
		trackPoint.setLatitude(latitude);
		trackPoint.setTemperature(temperature);
		return trackPoint;
	}
}
