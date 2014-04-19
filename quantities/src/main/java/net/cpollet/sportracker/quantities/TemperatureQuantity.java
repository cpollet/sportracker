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

import net.cpollet.sportracker.units.Temperature;
import net.cpollet.sportracker.units.TemperatureUnit;
import net.cpollet.sportracker.units.Unit;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class TemperatureQuantity extends AbstractQuantity<Temperature> implements Quantity<Temperature> {
	public static TemperatureUnit REFERENCE = TemperatureUnit.REFERENCE;

	public TemperatureQuantity(BigDecimal value, Unit<Temperature> unit) {
		super(value, unit);
	}

	@Override
	public Unit<Temperature> getReferenceUnit() {
		return REFERENCE;
	}
}
