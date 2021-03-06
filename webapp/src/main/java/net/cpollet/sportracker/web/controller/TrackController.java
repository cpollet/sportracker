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

package net.cpollet.sportracker.web.controller;

import net.cpollet.sportracker.data.Track;
import net.cpollet.sportracker.data.TrackPoint;
import net.cpollet.sportracker.fit.Parser;
import net.cpollet.sportracker.fit.ParserImpl;
import net.cpollet.sportracker.units.AngleUnit;
import net.cpollet.sportracker.web.data.TrackData;
import net.cpollet.sportracker.web.data.TrackPointData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Christophe Pollet
 */
@Controller
public class TrackController {
	private static final Logger logger = LoggerFactory.getLogger(TrackController.class);

	@RequestMapping(value = "/api/v1/track", method = RequestMethod.GET)
	@ResponseBody
	public TrackData track(@RequestParam(value="filePath", required=true) String filePath) {
		Parser parser = new ParserImpl();
		Track track = parser.parse(new File(filePath));

		List<TrackPointData> trackPointDatas = new LinkedList<>();
		for (TrackPoint trackPoint : track) {
			TrackPointData webTrackPointData = new TrackPointData(
					trackPoint.getTimestamp().toDate(), //
					trackPoint.getSpeed().getScaledValue(1).toString(), //
					trackPoint.getAltitude().getScaledValue(0).toString(), //
					trackPoint.getLatitude().in(AngleUnit.deg).getValue().toString(), //
					trackPoint.getLongitude().in(AngleUnit.deg).getValue().toString());

			trackPointDatas.add(webTrackPointData);
		}

		TrackData webTrackData = new TrackData("Some random ride", trackPointDatas);

		return webTrackData;
	}
}
