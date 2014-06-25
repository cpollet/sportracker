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

package net.cpollet.sportracker.repository.mongodb.converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import net.cpollet.sportracker.converter.Converter;
import net.cpollet.sportracker.data.Person;
import net.cpollet.sportracker.quantities.Quantity;

import java.util.Map;

/**
 * @author Christophe Pollet
 */
public class PersonToDBObjectConverter extends BaseConverter implements Converter<Person, DBObject> {
	@Override
	public DBObject convert(Person object) {
		DBObject dbObject = new BasicDBObject();

		Map map = getConversionService().convert(object, Object.class, Map.class);

		map.put("gender", getConversionService().convert(object.getGender(), Enum.class, String.class));
		map.put("weight", getConversionService().convert(object.getWeight(), Quantity.class, String.class));
		map.put("height", getConversionService().convert(object.getHeight(), Quantity.class, String.class));

		dbObject.putAll(map);

		return dbObject;
	}

	@Override
	public Class from() {
		return Person.class;
	}

	@Override
	public Class to() {
		return DBObject.class;
	}
}
