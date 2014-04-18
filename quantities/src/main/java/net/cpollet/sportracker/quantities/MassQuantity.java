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

import net.cpollet.sportracker.units.Mass;
import net.cpollet.sportracker.units.MassUnit;
import net.cpollet.sportracker.units.Unit;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class MassQuantity extends AbstractQuantity<Mass> implements Quantity<Mass> {
	public static final MassUnit REFERENCE = MassUnit.REFERENCE;

	public MassQuantity(BigDecimal value, Unit<Mass> unit) {
		super(value, unit);
	}

	@Override
	public Quantity<Mass> convertTo(Unit<Mass> unit) {
		return new MassQuantity(convert(unit), unit);
	}

	@Override
	public Unit<Mass> getReferenceUnit() {
		return REFERENCE;
	}
}