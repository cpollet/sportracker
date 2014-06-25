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

package net.cpollet.sportracker.web.data.builder;

import net.cpollet.sportracker.web.data.Quantity;

import java.math.BigDecimal;

/**
 * @author Christophe Pollet
 */
public class QuantityBuilder {
	private BigDecimal value;
	private String unit;

	private QuantityBuilder() {
	}

	public static QuantityBuilder aQuantity() {
		return new QuantityBuilder();
	}

	public QuantityBuilder withValue(int value) {
		return withValue(new BigDecimal(value));
	}

	public QuantityBuilder withValue(BigDecimal value) {
		this.value = value;
		return this;
	}

	public QuantityBuilder withUnit(String unit) {
		this.unit = unit;
		return this;
	}

	public Quantity build() {
		Quantity quantity = new Quantity();
		quantity.setValue(value);
		quantity.setUnit(unit);
		return quantity;
	}
}
