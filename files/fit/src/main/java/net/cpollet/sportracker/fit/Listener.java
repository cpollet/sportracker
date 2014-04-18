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
import net.cpollet.sportracker.data.Point;
import net.cpollet.sportracker.data.PointBuilder;
import net.cpollet.sportracker.quantities.FrequencyQuantity;
import net.cpollet.sportracker.quantities.LengthQuantity;
import net.cpollet.sportracker.quantities.SpeedQuantity;
import net.cpollet.sportracker.units.FrequencyUnit;
import net.cpollet.sportracker.units.LengthUnit;
import net.cpollet.sportracker.units.SpeedUnit;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Christophe Pollet
 */
public class Listener implements RecordMesgListener {
	private List<Point> points;

	public Listener() {
		points = new LinkedList<>();
	}

	@Override
	public void onMesg(RecordMesg recordMesg) {
		Point point = PointBuilder.aPoint() //
				.withTimestamp(new DateTime(recordMesg.getTimestamp().getDate())) //
				.withSpeed(new SpeedQuantity(BigDecimal.valueOf(recordMesg.getSpeed()), SpeedUnit.ms)) //
				.withAltitude(new LengthQuantity(BigDecimal.valueOf(recordMesg.getAltitude()), LengthUnit.m)) //
				.withLatitude(BigDecimal.valueOf(recordMesg.getPositionLat())) //
				.withLongitude(BigDecimal.valueOf(recordMesg.getPositionLong())) //
				.withCadence(new FrequencyQuantity(BigDecimal.valueOf(recordMesg.getCadence()), FrequencyUnit.fpm)) //
				.withHeartRate(new FrequencyQuantity(BigDecimal.valueOf(recordMesg.getHeartRate()))) //
				.build();

		points.add(point);
	}


	public List<Point> getPoints() {
		return points;
	}
}
