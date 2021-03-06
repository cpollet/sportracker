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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Christophe Pollet
 */
public class Track implements Iterable<TrackPoint> {
	private List<TrackPoint> trackPoints;

	public Track() {
		this.trackPoints = new LinkedList<>();
	}

	public int size() {
		return trackPoints.size();
	}

	public boolean isEmpty() {
		return trackPoints.isEmpty();
	}

	@Override
	public Iterator<TrackPoint> iterator() {
		return trackPoints.iterator();
	}

	public boolean add(TrackPoint trackPoint) {
		return trackPoints.add(trackPoint);
	}

	public TrackPoint get(int index) {
		return trackPoints.get(index);
	}
}
