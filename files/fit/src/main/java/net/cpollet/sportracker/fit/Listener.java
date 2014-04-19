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

package net.cpollet.sportracker.fit;

import com.garmin.fit.RecordMesg;
import com.garmin.fit.RecordMesgListener;
import net.cpollet.sportracker.data.TrackPoint;
import net.cpollet.sportracker.data.Track;
import net.cpollet.sportracker.data.builder.TrackPointBuilder;
import net.cpollet.sportracker.quantities.QuantityFactory;
import net.cpollet.sportracker.units.FrequencyUnit;
import net.cpollet.sportracker.units.LengthUnit;
import net.cpollet.sportracker.units.SpeedUnit;
import net.cpollet.sportracker.units.TemperatureUnit;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class Listener implements RecordMesgListener {
	private Track track;

	public Listener() {
		track = new Track();
	}

	@Override
	public void onMesg(RecordMesg recordMesg) {
		TrackPoint trackPoint = TrackPointBuilder.aTrackPoint() //
				.withTimestamp(new DateTime(recordMesg.getTimestamp().getDate())) //
				.withSpeed(QuantityFactory.SPEED.create(recordMesg.getSpeed(), SpeedUnit.ms)) //
				.withAltitude(QuantityFactory.LENGTH.create(recordMesg.getAltitude(), LengthUnit.m)) //
				.withCadence(QuantityFactory.FREQUENCY.create(recordMesg.getCadence(), FrequencyUnit.fpm)) //
				.withHeartRate(QuantityFactory.FREQUENCY.create(recordMesg.getHeartRate(), FrequencyUnit.fps)) //
				.withTemperature(QuantityFactory.TEMPERATURE.create(recordMesg.getTemperature(), TemperatureUnit.C)) //
				.withLatitude(BigDecimal.valueOf(recordMesg.getPositionLat())) //
				.withLongitude(BigDecimal.valueOf(recordMesg.getPositionLong())) //
				.build();

		track.add(trackPoint);
	}

	public Track getTrack() {
		return track;
	}
}
