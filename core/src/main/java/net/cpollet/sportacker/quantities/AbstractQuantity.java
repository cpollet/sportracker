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

import net.cpollet.sportacker.units.Unit;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Christophe Pollet
 */
public abstract class AbstractQuantity<Q extends Quantity<Q>> implements Quantity<Q> {
	private static final BigDecimal MINUS_ONE = new BigDecimal(-1);

	private BigDecimal value;
	private Unit<Q> unit;

	public AbstractQuantity(BigDecimal value, Unit<Q> unit) {
		assert value != null;
		assert unit != null;

		this.value = value;
		this.unit = unit;
	}

	protected BigDecimal convert(Unit<Q> unit) {
		return getValue().multiply(getFactor(unit));
	}

	private BigDecimal getFactor(Unit<Q> unit) {
		return getUnit().getFactorToReferenceUnit().divide(unit.getFactorToReferenceUnit(), MathContext.DECIMAL64);
	}

	@Override
	public BigDecimal getValue() { return value; }

	@Override
	public BigDecimal getScaledValue() {
		return value.setScale(unit.getScale(), BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public Unit<Q> getUnit() {
		return unit;
	}

	@Override
	public Quantity<Q> convertToReferenceUnit() {
		return convertTo(getReferenceUnit());
	}

	@Override
	public int compareTo(Quantity<Q> other) {
		Quantity<Q> referenceThis = this.convertToReferenceUnit();
		Quantity<Q> referenceOther = other.convertToReferenceUnit();

		return referenceThis.getValue().compareTo(referenceOther.getValue());
	}

	@Override
	public Quantity<Q> add(Quantity<Q> quantity) {
		Quantity<Q> result = quantity.convertTo(getUnit());

		if (result == quantity) {
			result = clone(result);
		}

		result.setValue(result.getValue().add(getValue()));

		return result;
	}

	private static Quantity clone(Quantity quantity) {
		try {
			return (Quantity) quantity.clone();
		}
		catch (CloneNotSupportedException e) {
			// Should never happen
			throw new RuntimeException(e);
		}
	}

	@Override
	public Quantity<Q> subtract(Quantity<Q> quantity) {
		return add(quantity.multiply(MINUS_ONE));
	}

	@Override
	public Quantity<Q> divide(BigDecimal divisor) {
		return this;
	}

	@Override
	public Quantity<Q> multiply(BigDecimal factor) {
		Quantity<Q> result = clone(this);

		result.setValue(result.getValue().multiply(factor));

		return result;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		AbstractQuantity that = (AbstractQuantity) o;

		if (!unit.equals(that.unit)) {
			return false;
		}
		if (!value.equals(that.value)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = value.hashCode();
		result = 31 * result + unit.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return value.toString() + " [" + unit.getName() + "]";
	}
}
