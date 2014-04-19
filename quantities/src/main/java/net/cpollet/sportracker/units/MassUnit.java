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

package net.cpollet.sportracker.units;

import net.cpollet.sportracker.quantities.MassQuantity;

/**
 * @author Christophe Pollet
 */
public class MassUnit extends AbstractUnit<Mass> implements Unit<Mass> {
	public static final MassUnit kg = new MassUnit("kg", "1");
	public static final MassUnit g = new MassUnit("g", "0.001");

	public static final MassUnit REFERENCE = kg;

	public MassUnit(String name, String conversionFactor) {
		super(MassQuantity.class, name, conversionFactor);
	}

	@Override
	public Unit<Mass> getSystemUnit() {
		return REFERENCE;
	}
}
