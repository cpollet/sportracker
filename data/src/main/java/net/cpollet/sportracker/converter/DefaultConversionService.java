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

/**
 * @author Christophe Pollet
 */
public class DefaultConversionService implements ConversionService {
	private ConverterRepository converterRepository;

	@Override
	public <S, D> D convert(S object, Class<D> toClass) {
		if (object == null) {
			return null;
		}

		Converter<S, D> converter = converterRepository.get(getSourceClass(object), toClass);
		return converter.convert(object);
	}

	@Override
	public <S, D> D convert(S object, Class<S> fromClass, Class<D> toClass) {
		if (object == null) {
			return null;
		}

		Converter<S, D> converter = converterRepository.get(fromClass, toClass);
		return converter.convert(object);
	}

	@Override
	public <S> boolean hasConverter(S object, Class toClass) {
		return converterRepository.hasConverter(getSourceClass(object), toClass);
	}

	@SuppressWarnings("unchecked")
	private <S> Class<S> getSourceClass(S object) {
		return (Class<S>) object.getClass();
	}

	public void setConverterRepository(ConverterRepository converterRepository) {
		this.converterRepository = converterRepository;
	}
}
