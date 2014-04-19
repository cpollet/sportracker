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

package net.cpollet.sportracker.quantities;

import net.cpollet.sportracker.units.DurationUnit;
import net.cpollet.sportracker.units.Length;
import net.cpollet.sportracker.units.LengthUnit;
import net.cpollet.sportracker.units.Unit;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Christophe Pollet
 */
public class LengthQuantity extends AbstractQuantity<Length> implements Quantity<Length> {
	public static final LengthUnit REFERENCE = LengthUnit.REFERENCE;

	public static final Map<Class, Unit> DIVISORS;
	static {
		Map<Class, Unit> divisors = new HashMap<>();
		divisors.put(DurationUnit.class, SpeedQuantity.REFERENCE);
		DIVISORS = Collections.unmodifiableMap(divisors);
	}

	public LengthQuantity(BigDecimal value) {
		super(value, REFERENCE);
	}

	public LengthQuantity(BigDecimal value, Unit<Length> unit) {
		super(value, unit);
	}

	@Override
	public Unit<Length> getReferenceUnit() {
		return REFERENCE;
	}

	@Override
	protected Map<Class, Unit> getDivisors() {
		return DIVISORS;
	}
}
