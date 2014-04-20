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
public class AngleUnit extends AbstractUnit<Angle> implements Unit<Angle> {
	public static final AngleUnit deg = new AngleUnit("deg", "1", 10);
	public static final AngleUnit semicircle = new AngleUnit("semicircle", "8.38190317e-8", 0);

	public static final AngleUnit REFERENCE = deg;

	public AngleUnit(String name, String conversionFactor, int scale) {
		super(DurationQuantity.class, name, conversionFactor, scale);
	}

	@Override
	public Unit<Angle> getSystemUnit() {
		return REFERENCE;
	}
}
