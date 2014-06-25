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

package net.cpollet.sportracker.converter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Christophe Pollet
 */
public class DefaultConverterRepository implements ConverterRepository {
	private Map<ConverterSignature, Converter> converters;

	public DefaultConverterRepository() {
		this.converters = new HashMap<>();
	}

	@Override
	public <S, D> Converter<S, D> get(Class<S> source, Class<D> destination) {
		ConverterSignature converterSignature = new ConverterSignature(source, destination);
		Converter converter = converters.get(converterSignature);

		if (null == converter) {
			throw new ConverterNotFoundException("Converter from " + source.getName() + " to " + destination.getName() + " not found");
		}

		return converter;
	}

	@Override
	public <S, D> void add(Class<S> source, Class<D> destination, Converter<S, D> converter) {
		converters.put(new ConverterSignature(source, destination), converter);
	}


	@Override
	public boolean hasConverter(Class source, Class destination) {
		return converters.get(new ConverterSignature(source, destination)) != null;
	}

	private static class ConverterSignature {
		private Class from;
		private Class to;

		private ConverterSignature(Class from, Class to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (null == o || getClass() != o.getClass()) {
				return false;
			}

			ConverterSignature that = (ConverterSignature) o;

			if (!from.equals(that.from)) {
				return false;
			}
			if (!to.equals(that.to)) {
				return false;
			}

			return true;
		}

		@Override
		public int hashCode() {
			int result = from.hashCode();
			result = 31 * result + to.hashCode();
			return result;
		}
	}
}
