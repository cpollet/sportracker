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

package net.cpollet.sportracker.data;

import net.cpollet.sportracker.quantities.LengthQuantity;
import net.cpollet.sportracker.quantities.Quantity;
import net.cpollet.sportracker.quantities.QuantityFactory;
import net.cpollet.sportracker.test.support.DateUtils;
import net.cpollet.sportracker.units.Length;
import org.fest.assertions.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Christophe Pollet
 */
@RunWith(JUnit4.class)
public class TestEvolvingQuantity {
	@Test
	public void getQuantityAfterReturnsNullWhenEmpty() {
		// GIVEN
		EvolvingQuantity evolvingQuantity = new EvolvingQuantity();

		// WHEN
		Quantity quantity = evolvingQuantity.getQuantityAtOrAfter(new Date());

		// THEN
		assertThat(quantity).isNull();
	}

	@Test
	public void getQuantityAfterReturnsNullWhenAfterIsBeforeFirstElement() {
		// GIVEN
		EvolvingQuantity<Quantity<Length>, Date> evolvingQuantity = new EvolvingQuantity<>();
		evolvingQuantity.addQuantity(QuantityFactory.LENGTH.create("1"), DateUtils.toDate("2000-01-01"));

		// WHEN
		Quantity quantity = evolvingQuantity.getQuantityAtOrAfter(DateUtils.toDate("1999-01-01"));

		// THEN
		assertThat(quantity).isNull();
	}

	@Test
	public void getQuantityAfterReturnsElementWhenAfterIsAfterLastElement() {
		// GIVEN
		Quantity<Length> expectedQuantity = QuantityFactory.LENGTH.create("1");
		EvolvingQuantity<Quantity<Length>, Date> evolvingQuantity = new EvolvingQuantity<>();
		evolvingQuantity.addQuantity(expectedQuantity, DateUtils.toDate("2000-01-01"));

		// WHEN
		Quantity actualQuantity = evolvingQuantity.getQuantityAtOrAfter(DateUtils.toDate("2001-01-01"));

		// THEN
		assertThat(actualQuantity) //
				.isNotNull() //
				.isSameAs(expectedQuantity);
	}

	@Test
	public void getQuantityOnReturnsElement() {
		// GIVEN
		Quantity<Length> expectedQuantity = QuantityFactory.LENGTH.create("1");
		EvolvingQuantity<Quantity<Length>, Date> evolvingQuantity = new EvolvingQuantity<>();
		evolvingQuantity.addQuantity(expectedQuantity, DateUtils.toDate("2000-01-01"));

		// WHEN
		Quantity actualQuantity = evolvingQuantity.getQuantityAtOrAfter(DateUtils.toDate("2000-01-01"));

		// THEN
		assertThat(actualQuantity) //
				.isNotNull() //
				.isSameAs(expectedQuantity);
	}


	@Test
	public void getQuantityAfterReturnsElementWhenAfterIsAfterSomeElement() {
		// GIVEN
		Quantity<Length> expectedQuantity = QuantityFactory.LENGTH.create("1");
		EvolvingQuantity<Quantity<Length>, Date> evolvingQuantity = new EvolvingQuantity<>();
		evolvingQuantity.addQuantity(expectedQuantity, DateUtils.toDate("2000-01-01"));
		evolvingQuantity.addQuantity(QuantityFactory.LENGTH.create("2"), DateUtils.toDate("2002-01-01"));

		// WHEN
		Quantity actualQuantity = evolvingQuantity.getQuantityAtOrAfter(DateUtils.toDate("2001-01-01"));

		// THEN
		assertThat(actualQuantity) //
				.isNotNull() //
				.isSameAs(expectedQuantity);
	}
}
