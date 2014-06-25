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

import net.cpollet.sportracker.data.EvolvingQuantity;
import net.cpollet.sportracker.quantities.Quantity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Christophe Pollet
 */
public class EvolvingQuantityToListConverter implements Converter<EvolvingQuantity, List>, InitializingBean {
	private ConversionService conversionService;

	@Override
	public List<Map<String, Object>> convert(EvolvingQuantity object) {
		List<Map<String, Object>> list = new LinkedList<>();

		for (EvolvingQuantity.StampedQuantity quantity : getQuantities(object)) {
			Map<String, Object> map = new HashMap<>(2);

			map.put("quantity", conversionService.convert(quantity.getQuantity(), Quantity.class, String.class));

			if (conversionService.hasConverter(quantity.getStamp(), String.class)) {
				map.put("comparable",conversionService.convert(quantity.getStamp(), String.class));
			}else {
				map.put("comparable",conversionService.convert(quantity.getStamp(), Object.class, String.class));
			}

			list.add(map);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	private List<EvolvingQuantity.StampedQuantity> getQuantities(EvolvingQuantity object) {
		return (List<EvolvingQuantity.StampedQuantity>) object.getQuantities();
	}

	@Override
	public Class from() {
		return EvolvingQuantity.class;
	}

	@Override
	public Class to() {
		return List.class;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(conversionService, "conversionService must be set");
	}
}
