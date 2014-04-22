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

import com.garmin.fit.Decode;
import com.garmin.fit.FitRuntimeException;
import com.garmin.fit.MesgBroadcaster;
import com.garmin.fit.RecordMesgListener;
import net.cpollet.sportracker.aggregator.DefaultTrackPointAggregator;
import net.cpollet.sportracker.aggregator.TrackPointAggregator;
import net.cpollet.sportracker.data.TrackPoint;
import net.cpollet.sportracker.units.LengthUnit;
import net.cpollet.sportracker.units.SpeedUnit;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Christophe Pollet
 */
public class Main {
	public static void main(String[] argv) throws IOException {
		Decode decode = new Decode();
		MesgBroadcaster mesgBroadcaster = new MesgBroadcaster(decode);

		Listener listener = new Listener();
		mesgBroadcaster.addListener((RecordMesgListener) listener);

		try (FileInputStream in = new FileInputStream("/Users/cpollet/Downloads/sample.fit")) {
			if (!Decode.checkIntegrity(in)) {
				throw new RuntimeException("FIT file integrity check failed");
			}
		}

		try (FileInputStream in = new FileInputStream("/Users/cpollet/Downloads/sample.fit")) {
			try {
				mesgBroadcaster.run(in);

				for (TrackPoint trackPoint : listener.getTrack()) {
					System.out.println(trackPoint);
				}

				TrackPointAggregator aggregator = new DefaultTrackPointAggregator(listener.getTrack());

				System.out.println("Min speed: " + aggregator.getMinSpeed().in(SpeedUnit.kmh).scale(1));
				System.out.println("Max speed: " + aggregator.getMaxSpeed().in(SpeedUnit.kmh).scale(1));
				System.out.println("Avg speed: " + aggregator.getAverageSpeed().in(SpeedUnit.kmh).scale(1));

				System.out.println("Min alt:   " + aggregator.getMinAltitude().in(LengthUnit.m).scale(1));
				System.out.println("Max alt:   " + aggregator.getMaxAltitude().in(LengthUnit.m).scale(1));
				System.out.println("Avg alt:   " + aggregator.getAverageAltitude().in(LengthUnit.m).scale(1));
			} catch (FitRuntimeException e) {
				System.err.print("Exception decoding file: ");
				System.err.println(e.getMessage());
				throw e;
			}
		}
	}
}
