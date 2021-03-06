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

package net.cpollet.sportracker.dozer;

import net.cpollet.sportracker.service.api.HashingService;
import org.dozer.CustomConverter;
import org.dozer.MappingException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author Christophe Pollet
 */
public class HashingDozerConverter implements CustomConverter, InitializingBean {
	private HashingService hashingService;

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}

		if (!(sourceFieldValue instanceof String)) {
			throw new MappingException("Converter HashingDozerConverter used incorrectly. Arguments passed were " //
					+ existingDestinationFieldValue + " and " + sourceFieldValue);
		}

		return hashingService.hash((String) sourceFieldValue);
	}

	public void setHashingService(HashingService hashingService) {
		this.hashingService = hashingService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(hashingService, "hashingService must be set");
	}
}
