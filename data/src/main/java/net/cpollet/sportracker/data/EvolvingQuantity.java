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

import net.cpollet.sportracker.quantities.Quantity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Christophe Pollet
 */
public class EvolvingQuantity<Q extends Quantity, C extends Comparable<C>> {
	private List<StampedQuantity<Q, C>> quantities = new LinkedList<>();

	public void addQuantity(Q quantity, C comparable) {
		quantities.add(new StampedQuantity<>(quantity, comparable));
	}

	public Q getQuantityAtOrAfter(Comparable<C> comparable) {
		if (quantities.isEmpty()) {
			return null;
		}

		int index = searchForComparableInList(comparable);

		if (exactComparableMatch(index)) {
			return matchedQuantity(index);
		}

		if (comparableIsBeforeFirstInList(index)) {
			return null;
		}

		if (comparableIsAfterLastInList(index)) {
			return lastQuantity();
		}

		// comparable is after some element
		return previousQuantity(index);
	}

	public Q getLastQuantity() {
		sortQuantities();
		return quantities.get(quantities.size() - 1).getQuantity();
	}

	public List<StampedQuantity<Q, C>> getQuantities() {
		return quantities;
	}

	private int searchForComparableInList(Comparable<C> comparable) {
		sortQuantities();
		StampedQuantity<Q, C> stampedQuantityForComparison = createStampedQuantityForComparison(comparable);
		return Collections.binarySearch(quantities, stampedQuantityForComparison);
	}

	private void sortQuantities() {
		Collections.sort(quantities);
	}

	private boolean exactComparableMatch(int index) {
		return index >= 0;
	}

	private Q matchedQuantity(int index) {
		return quantities.get(index).getQuantity();
	}

	private boolean comparableIsBeforeFirstInList(int index) {
		return index == -1;
	}

	private boolean comparableIsAfterLastInList(int index) {
		return -index > quantities.size();
	}

	private Q lastQuantity() {
		return quantities.get(quantities.size() - 1).getQuantity();
	}

	private Q previousQuantity(int index) {
		return quantities.get(-index - 2).getQuantity();
	}

	@SuppressWarnings("unchecked")
	private StampedQuantity<Q, C> createStampedQuantityForComparison(Comparable<C> comparable) {
		return new StampedQuantity(comparable);
	}

	@Override
	public String toString() {
		return quantities.toString();
	}

	public static class StampedQuantity<Q extends Quantity, C extends Comparable<C>> implements Comparable<StampedQuantity<Q, C>> {
		private Q quantity;
		private C stamp;

		private StampedQuantity(C stamp) {
			this.stamp = stamp;
		}

		private StampedQuantity(Q quantity, C stamp) {
			this.quantity = quantity;
			this.stamp = stamp;
		}

		public Q getQuantity() {
			return quantity;
		}

		public C getStamp() {
			return stamp;
		}


		@Override
		public int compareTo(StampedQuantity<Q, C> o) {
			return getStamp().compareTo(o.getStamp());
		}

		@Override
		public String toString() {
			return "{" +
					"quantity=" + quantity +
					", stamp=" + stamp +
					'}';
		}
	}
}
