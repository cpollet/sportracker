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

import net.cpollet.sportracker.units.Unit;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public interface Quantity<Q extends Quantity<Q>> extends Cloneable {
	BigDecimal getValue();

	BigDecimal getScaledValue();

	void setValue(BigDecimal value);

	Unit<Q> getUnit();

	Quantity<Q> convertTo(Unit<Q> unit);

	Quantity<Q> in(Unit<Q> unit);

	Quantity<Q> convertToReferenceUnit();

	Unit<Q> getReferenceUnit();

	int compareTo(Quantity<Q> other);

	Quantity<Q> add(Quantity<Q> quantity);

	Quantity<Q> subtract(Quantity<Q> quantity);

	Quantity<Q> multiply(BigDecimal factor);

	Quantity<Q> divide(BigDecimal divisor);

	Object clone() throws CloneNotSupportedException;
}
