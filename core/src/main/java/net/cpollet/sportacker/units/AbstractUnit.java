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

package net.cpollet.sportacker.units;

import net.cpollet.sportacker.quantities.Quantity;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Christophe Pollet
 */
public abstract class AbstractUnit<Q extends Quantity<Q>> implements Unit<Q> {
	private String name;
	private BigDecimal conversionFactor;
	private int scale;

	public AbstractUnit(String name, String conversionFactor) {
		this(name, conversionFactor, 4);
	}

	public AbstractUnit(String name, String conversionFactor, int scale) {
		this.name = name;
		this.conversionFactor = new BigDecimal(conversionFactor);
		this.scale = scale;
	}

	protected static String inverse(String number) {
		return BigDecimal.ONE.divide(new BigDecimal(number), MathContext.DECIMAL64).toString();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public BigDecimal getFactorToReferenceUnit() {
		return conversionFactor;
	}

	@Override
	public int getScale() {
		return scale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		AbstractUnit that = (AbstractUnit) o;

		if (!conversionFactor.equals(that.conversionFactor)) {
			return false;
		}
		if (!name.equals(that.name)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + conversionFactor.hashCode();
		return result;
	}
}
