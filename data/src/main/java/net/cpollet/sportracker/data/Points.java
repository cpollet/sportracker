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
import net.cpollet.sportracker.quantities.SpeedQuantity;
import net.cpollet.sportracker.units.Speed;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Christophe Pollet
 */
public class Points implements Iterable<Point> {
	private List<Point> points;

	public Points() {
		this.points = new LinkedList<>();
	}

	public int size() {
		return points.size();
	}

	public boolean isEmpty() {
		return points.isEmpty();
	}

	@Override
	public Iterator<Point> iterator() {
		return points.iterator();
	}

	public boolean add(Point point) {
		return points.add(point);
	}

	public Point get(int index) {
		return points.get(index);
	}

	public Quantity<Speed> getAverageSpeed() {
		Quantity<Speed> totalSpeed = new SpeedQuantity(BigDecimal.valueOf(0));
		int count = 0;

		for (Point point : points) {
			if (point.getSpeed().getValue().compareTo(BigDecimal.valueOf(0)) > 0) {
				count++;
				totalSpeed = totalSpeed.add(point.getSpeed());
			}
		}

		return totalSpeed.divide(BigDecimal.valueOf(count));
	}
}
