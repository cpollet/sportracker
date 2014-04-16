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

package net.cpollet.sportacker.quantities;

import net.cpollet.sportacker.units.Length;
import net.cpollet.sportacker.units.LengthUnit;
import net.cpollet.sportacker.units.Unit;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class LengthQuantity extends AbstractQuantity<Length> implements Quantity<Length> {
	public static final LengthUnit REFERENCE = LengthUnit.REFERENCE;

	public LengthQuantity(BigDecimal value) {
		super(value, REFERENCE);
	}

	public LengthQuantity(BigDecimal value, Unit<Length> unit) {
		super(value, unit);
	}

	@Override
	public Quantity<Length> convertTo(Unit<Length> unit) {
		if (getUnit().equals(unit)) {
			return this;
		}

		return new LengthQuantity(convert(unit), unit);
	}

	@Override
	public Unit<Length> getReferenceUnit() {
		return REFERENCE;
	}
}
