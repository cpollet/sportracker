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

package net.cpollet.sportracker.units;

import net.cpollet.sportracker.quantities.DurationQuantity;

/**
 * @author Christophe Pollet
 */
public class DurationUnit extends AbstractUnit<Duration> implements Unit<Duration> {
	public static final DurationUnit h = new DurationUnit("h", "3600");
	public static final DurationUnit min = new DurationUnit("min", "60");
	public static final DurationUnit s = new DurationUnit("s", "1");

	public static DurationUnit REFERENCE = s;

	public DurationUnit(String name, String conversionFactor) {
		super(DurationQuantity.class, name, conversionFactor);
	}

	@Override
	public Unit<Duration> getSystemUnit() {
		return REFERENCE;
	}
}
