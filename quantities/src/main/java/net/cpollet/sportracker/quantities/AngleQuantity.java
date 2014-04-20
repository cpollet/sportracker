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

import net.cpollet.sportracker.units.Angle;
import net.cpollet.sportracker.units.AngleUnit;
import net.cpollet.sportracker.units.Unit;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class AngleQuantity extends AbstractQuantity<Angle> implements Quantity<Angle> {
	public final static AngleUnit REFERENCE = AngleUnit.REFERENCE;

	public AngleQuantity(BigDecimal value, Unit<Angle> unit) {
		super(value, unit);
	}

	@Override
	public Unit<Angle> getReferenceUnit() {
		return REFERENCE;
	}
}
