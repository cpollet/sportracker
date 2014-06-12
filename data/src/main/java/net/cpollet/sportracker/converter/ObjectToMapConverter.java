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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Christophe Pollet
 */
public class ObjectToMapConverter implements Converter<Object, Map<String, Object>> {
	@Override
	public Map<String, Object> convert(Object object) {
		Map<String, Object> result = new HashMap<>();

		Field[] declaredFields = object.getClass().getDeclaredFields();

		for (Field field : declaredFields) {
			result.put(field.getName(), getFieldValue(object, field));
		}

		return result;
	}

	private Object getFieldValue(Object object, Field field) {
		try {
			field.setAccessible(true);
			return field.get(object);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
