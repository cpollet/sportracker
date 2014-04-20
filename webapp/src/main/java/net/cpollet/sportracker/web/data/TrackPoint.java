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
public class TrackPoint {
	private final Date timestamp;
	private final String speed;
	private final String altitude;
	private final String latitude;
	private final String longitude;

	public TrackPoint(Date timestamp, String speed, String altitude, String latitude, String longitude) {
		this.timestamp = timestamp;
		this.speed = speed;
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getSpeed() {
		return speed;
	}

	public String getAltitude() {
		return altitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
}
