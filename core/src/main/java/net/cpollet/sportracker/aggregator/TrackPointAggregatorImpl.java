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

package net.cpollet.sportracker.aggregator;

import net.cpollet.sportracker.data.Track;
import net.cpollet.sportracker.data.TrackPoint;
import net.cpollet.sportracker.quantities.ExtremeQuantity;
import net.cpollet.sportracker.quantities.Quantity;
import net.cpollet.sportracker.quantities.QuantityFactory;
import net.cpollet.sportracker.units.Length;
import net.cpollet.sportracker.units.LengthUnit;
import net.cpollet.sportracker.units.Speed;
import net.cpollet.sportracker.units.SpeedUnit;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Christophe Pollet
 */
public class TrackPointAggregatorImpl implements TrackPointAggregator {
	private Quantity<Speed> minSpeed;
	private Quantity<Speed> maxSpeed;
	private Quantity<Speed> averageSpeed;

	private Quantity<Length> minAltitude;
	private Quantity<Length> maxAltitude;
	private Quantity<Length> averageAltitude;

	public TrackPointAggregatorImpl(Track track) {
		aggregate(track);
	}

	public TrackPointAggregatorImpl(List<TrackPoint> trackPointList) {
		aggregate(trackPointList);
	}

	private void aggregate(Iterable<TrackPoint> iterable) {
		if (!iterable.iterator().hasNext()) {
			return;
		}

		minSpeed = ExtremeQuantity.MIN;
		maxSpeed = ExtremeQuantity.MAX;
		averageSpeed = QuantityFactory.SPEED.create(0, SpeedUnit.ms);
		int speedPointsCount = 0;

		minAltitude = ExtremeQuantity.MIN;
		maxAltitude = ExtremeQuantity.MAX;
		averageAltitude = QuantityFactory.LENGTH.create(0, LengthUnit.m);
		int altitudePointsCount = 0;

		for (TrackPoint trackPoint : iterable) {
			Quantity<Speed> speed = trackPoint.getSpeed();
			Quantity<Length> altitude = trackPoint.getAltitude();

			if (!speed.isZero()) {
				if (minSpeed.compareTo(speed) > 0) {
					minSpeed = speed;
				}

				if (maxSpeed.compareTo(speed) < 0) {
					maxSpeed = speed;
				}

				averageSpeed = averageSpeed.add(speed);
				speedPointsCount++;
			}

			if (minAltitude.compareTo(altitude) > 0) {
				minAltitude = altitude;
			}

			if (maxAltitude.compareTo(altitude) < 0) {
				maxAltitude = altitude;
			}

			averageAltitude = averageAltitude.add(altitude);
			altitudePointsCount++;
		}

		if (speedPointsCount > 0) {
			averageSpeed = averageSpeed.divide(BigDecimal.valueOf(speedPointsCount));
		}

		averageAltitude = averageAltitude.divide(BigDecimal.valueOf(altitudePointsCount));
	}

	@Override
	public Quantity<Speed> getAverageSpeed() {
		return averageSpeed;
	}

	@Override
	public Quantity<Speed> getMaxSpeed() {
		return maxSpeed;
	}

	@Override
	public Quantity<Speed> getMinSpeed() {
		return minSpeed;
	}

	@Override
	public Quantity<Length> getAverageAltitude() {
		return averageAltitude;
	}

	@Override
	public Quantity<Length> getMinAltitude() {
		return minAltitude;
	}

	@Override
	public Quantity<Length> getMaxAltitude() {
		return maxAltitude;
	}
}
