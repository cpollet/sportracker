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

import net.cpollet.sportacker.units.Duration;
import net.cpollet.sportacker.units.DurationUnit;
import net.cpollet.sportacker.units.Unit;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class DurationQuantity extends AbstractQuantity<Duration> implements Quantity<Duration> {
	public static final DurationUnit REFERENCE = DurationUnit.REFERENCE;

	public DurationQuantity(BigDecimal value) {
		super(value, REFERENCE);
	}

	public DurationQuantity(BigDecimal value, Unit<Duration> unit) {
		super(value, unit);
	}

	@Override
	public Quantity<Duration> convertTo(Unit<Duration> unit) {
		return new DurationQuantity(convert(unit), unit);
	}

	@Override
	public Unit<Duration> getReferenceUnit() {
		return REFERENCE;
	}
}
