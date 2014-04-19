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
import java.math.MathContext;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Christophe Pollet
 */
public abstract class AbstractQuantity<Q extends Quantity<Q>> implements Quantity<Q> {
	private static final BigDecimal MINUS_ONE = new BigDecimal(-1);

	private BigDecimal value;
	private Unit<Q> unit;

	public AbstractQuantity(BigDecimal value, Unit<Q> unit) {
		assert null != value;
		assert null != unit;

		this.value = value;
		this.unit = unit;
	}

	@Override
	public Quantity<Q> convertTo(Unit<Q> unit) {
		if (getUnit().equals(unit)) {
			return this;
		}

		return QuantityFactory.get(unit.getClass()).create(convert(unit), unit);
	}

	protected BigDecimal convert(Unit<Q> unit) {
		if (!unit.getClass().isInstance(getUnit())) {
			throw new IllegalArgumentException("Cannot convert quantity from " + getUnit() + " to " + unit + "");
		}

		return getValue().multiply(getFactor(unit));
	}

	protected void assertDivisionPossible(Unit divisorUnit) {
		for (Class possibleDivisorUnitClass : getPossibleDivisorUnitClasses()) {
			if (!possibleDivisorUnitClass.isInstance(divisorUnit)) {
				throw new IllegalArgumentException("Unable to divide quantity of " + getUnit() + " by a quantity of " + divisorUnit);
			}
		}
	}

	protected Map<Class, Unit> getDivisors() {
		return Collections.emptyMap();
	}

	protected Set<Class> getPossibleDivisorUnitClasses() {
		return getDivisors().keySet();
	}

	protected Unit getQuotientUnit(Quantity divisor) {
		return getDivisors().get(divisor.getUnit().getClass());
	}

	private BigDecimal getFactor(Unit<Q> unit) {
		return getUnit().getFactorToReferenceUnit().divide(unit.getFactorToReferenceUnit(), MathContext.DECIMAL64);
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public BigDecimal getScaledValue(int scale) {
		return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public BigDecimal getScaledValue() {
		return getScaledValue(unit.getScale());
	}

	@Override
	public Quantity<Q> scale() {
		return scale(unit.getScale());
	}

	@Override
	public Quantity<Q> scale(int scale) {
		return QuantityFactory.get(unit.getClass()).create(getScaledValue(scale), unit);
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
	public Quantity<Q> in(Unit<Q> unit) {
		return convertTo(unit);
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
		return multiply(new BigDecimal("1").divide(divisor, MathContext.DECIMAL64));
	}

	@Override
	public Quantity<Q> multiply(BigDecimal factor) {
		Quantity<Q> result = clone(this);

		result.setValue(result.getValue().multiply(factor));

		return result;
	}

	@Override
	public Quantity divide(Quantity divisor) {
		assertDivisionPossible(divisor.getUnit());

		Quantity dividendInReferenceUnit = convertToReferenceUnit();
		Quantity divisorInReferenceUnit = divisor.convertToReferenceUnit();

		BigDecimal quotient = dividendInReferenceUnit.getValue().divide(divisorInReferenceUnit.getValue(), MathContext.DECIMAL64);

		Unit quotientUnit = getQuotientUnit(divisor);
		return QuantityFactory.get(quotientUnit.getClass()).create(quotient, quotientUnit);
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
