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

import net.cpollet.sportracker.units.Energy;
import net.cpollet.sportracker.units.EnergyUnit;
import net.cpollet.sportracker.units.Unit;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class EnergyQuantity extends AbstractQuantity<Energy> implements Quantity<Energy> {
	public static final EnergyUnit REFERENCE = EnergyUnit.REFERENCE;

	public EnergyQuantity(BigDecimal value) {
		super(value, REFERENCE);
	}

	public EnergyQuantity(BigDecimal value, Unit<Energy> unit) {
		super(value, unit);
	}

	@Override
	public Quantity<Energy> convertTo(Unit<Energy> unit) {
		if (getUnit().equals(unit)) {
			return this;
		}

		return new EnergyQuantity(convert(unit), unit);
	}

	@Override
	public Unit<Energy> getReferenceUnit() {
		return REFERENCE;
	}
}