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

import net.cpollet.sportracker.units.Speed;
import net.cpollet.sportracker.units.SpeedUnit;
import net.cpollet.sportracker.units.Unit;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class SpeedQuantity extends AbstractQuantity<Speed> implements Quantity<Speed> {
	public static final SpeedUnit REFERENCE = SpeedUnit.REFERENCE;

	public SpeedQuantity(BigDecimal value) {
		super(value, REFERENCE);
	}

	public SpeedQuantity(BigDecimal value, Unit<Speed> unit) {
		super(value, unit);
	}

	@Override
	public Quantity<Speed> convertTo(Unit<Speed> unit) {
		if (getUnit().equals(unit)) {
			return this;
		}

		return new SpeedQuantity(convert(unit), unit);
	}

	@Override
	public Unit<Speed> getReferenceUnit() {
		return REFERENCE;
	}
}