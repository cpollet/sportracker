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

package net.cpollet.sportacker.units;

/**
 * @author Christophe Pollet
 */
public class LengthUnit extends AbstractUnit<Length> implements Unit<Length> {
	public static final LengthUnit km = new LengthUnit("km", "1000");
	public static final LengthUnit m = new LengthUnit("m", "1");
	public static final LengthUnit cm = new LengthUnit("cm", "0.01");

	public static final LengthUnit REFERENCE = m;

	public LengthUnit(String name, String conversionFactor) {
		super(name, conversionFactor);
	}

	@Override
	public Unit<Length> getSystemUnit() {
		return REFERENCE;
	}
}
