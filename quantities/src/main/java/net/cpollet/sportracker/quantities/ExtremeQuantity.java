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
public abstract class ExtremeQuantity<Q extends Quantity<Q>> implements Quantity<Q>{
	public static final Quantity MAX = new ExtremeQuantity() {
		@Override
		public int compareTo(Quantity other) {
			return -1;
		}
	};

	public static final Quantity MIN = new ExtremeQuantity() {
		@Override
		public int compareTo(Quantity other) {
			return 1;
		}
	};

	private ExtremeQuantity() {
		// nothing
	}

	@Override
	public BigDecimal getValue() {
		throw new IllegalAccessError();
	}

	@Override
	public BigDecimal getScaledValue() {
		throw new IllegalAccessError();
	}

	@Override
	public BigDecimal getScaledValue(int scale) {
		throw new IllegalAccessError();
	}

	@Override
	public Quantity<Q> scale() {
		throw new IllegalAccessError();
	}

	@Override
	public Quantity<Q> scale(int scale) {
		throw new IllegalAccessError();
	}

	@Override
	public void setValue(BigDecimal value) {
		throw new IllegalAccessError();
	}

	@Override
	public Unit<Q> getUnit() {
		throw new IllegalAccessError();
	}

	@Override
	public Quantity<Q> convertTo(Unit<Q> unit) {
		return this;
	}

	@Override
	public Quantity<Q> in(Unit<Q> unit) {
		throw new IllegalAccessError();
	}

	@Override
	public Quantity<Q> convertToReferenceUnit() {
		return this;
	}

	@Override
	public Unit<Q> getReferenceUnit() {
		throw new IllegalAccessError();
	}

	@Override
	public Quantity<Q> add(Quantity<Q> quantity) {
		throw new IllegalAccessError();
	}

	@Override
	public Quantity<Q> subtract(Quantity<Q> quantity) {
		throw new IllegalAccessError();
	}

	@Override
	public Quantity<Q> multiply(BigDecimal factor) {
		throw new IllegalAccessError();
	}

	@Override
	public Quantity<Q> divide(BigDecimal divisor) {
		throw new IllegalAccessError();
	}

	@Override
	public Quantity divide(Quantity quantity) {
		throw new IllegalAccessError();
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return this;
	}
}
