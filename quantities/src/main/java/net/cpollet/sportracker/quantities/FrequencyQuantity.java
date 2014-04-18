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

import net.cpollet.sportracker.units.Frequency;
import net.cpollet.sportracker.units.FrequencyUnit;
import net.cpollet.sportracker.units.Unit;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class FrequencyQuantity extends AbstractQuantity<Frequency> implements Quantity<Frequency> {
	public static final FrequencyUnit REFERENCE = FrequencyUnit.fps;

	public FrequencyQuantity(BigDecimal value) {
		this(value, REFERENCE);
	}

	public FrequencyQuantity(BigDecimal value, Unit<Frequency> unit) {
		super(value, unit);
	}

	@Override
	public Quantity<Frequency> convertTo(Unit<Frequency> unit) {
		if (getUnit() == unit) {
			return this;
		}

		return new FrequencyQuantity(convert(unit), unit);
	}

	@Override
	public Unit<Frequency> getReferenceUnit() {
		return REFERENCE;
	}
}
