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

import net.cpollet.sportracker.quantities.SpeedQuantity;

/**
 * @author Christophe Pollet
 */
public class SpeedUnit extends AbstractUnit<Speed> implements Unit<Speed> {
	public static SpeedUnit ms = new SpeedUnit("m/s", "1");
	public static SpeedUnit kmh = new SpeedUnit("km/h", inverse("3.6"));

	public static SpeedUnit REFERENCE = ms;

	public SpeedUnit(String name, String conversionFactor) {
		super(SpeedQuantity.class, name, conversionFactor);
	}

	@Override
	public Unit<Speed> getSystemUnit() {
		return REFERENCE;
	}
}
