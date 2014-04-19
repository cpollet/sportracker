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

import net.cpollet.sportracker.units.Duration;
import net.cpollet.sportracker.units.DurationUnit;
import net.cpollet.sportracker.units.Energy;
import net.cpollet.sportracker.units.EnergyUnit;
import net.cpollet.sportracker.units.Frequency;
import net.cpollet.sportracker.units.FrequencyUnit;
import net.cpollet.sportracker.units.Length;
import net.cpollet.sportracker.units.LengthUnit;
import net.cpollet.sportracker.units.Mass;
import net.cpollet.sportracker.units.MassUnit;
import net.cpollet.sportracker.units.Speed;
import net.cpollet.sportracker.units.SpeedUnit;
import net.cpollet.sportracker.units.Temperature;
import net.cpollet.sportracker.units.TemperatureUnit;
import net.cpollet.sportracker.units.Unit;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Christophe Pollet
 */
public class QuantityFactory<Q extends Quantity<Q>> {
	public static final QuantityFactory<Duration> DURATION = new QuantityFactory<>();
	public static final QuantityFactory<Energy> ENERGY = new QuantityFactory<>();
	public static final QuantityFactory<Frequency> FREQUENCY = new QuantityFactory<>();
	public static final QuantityFactory<Length> LENGTH = new QuantityFactory<>();
	public static final QuantityFactory<Mass> MASS = new QuantityFactory<>();
	public static final QuantityFactory<Speed> SPEED = new QuantityFactory<>();
	public static final QuantityFactory<Temperature> TEMPERATURE = new QuantityFactory<>();

	private static final Map<Class, QuantityFactory> factories;

	static {
		Map<Class, QuantityFactory> factoriesMap = new HashMap<>();
		factoriesMap.put(DurationUnit.class, DURATION);
		factoriesMap.put(EnergyUnit.class, ENERGY);
		factoriesMap.put(FrequencyUnit.class, FREQUENCY);
		factoriesMap.put(LengthUnit.class, LENGTH);
		factoriesMap.put(MassUnit.class, MASS);
		factoriesMap.put(SpeedUnit.class, SPEED);
		factoriesMap.put(TemperatureUnit.class, TEMPERATURE);
		factories = Collections.unmodifiableMap(factoriesMap);
	}

	private QuantityFactory() {
	}

	public static QuantityFactory get(Class unitClass) {
		return factories.get(unitClass);
	}

	public Quantity<Q> create(BigDecimal value, Unit unit) {
		try {
			return (Quantity<Q>) unit.getQuantityClass() //
					.getConstructor(BigDecimal.class, Unit.class) //
					.newInstance(value, unit);
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public Quantity<Q> create(String value, Unit<Q> unit) {
		return create(new BigDecimal(value), unit);
	}

	public Quantity<Q> create(Float value, Unit<Q> unit) {
		return create(new BigDecimal(value), unit);
	}

	public Quantity<Q> create(Short value, Unit<Q> unit) {
		return create(new BigDecimal(value), unit);
	}

	public Quantity<Q> create(Byte value, Unit<Q> unit) {
		return create(new BigDecimal(value), unit);
	}

	public Quantity<Q> create(Integer value, Unit<Q> unit) {
		return create(new BigDecimal(value), unit);
	}
}
