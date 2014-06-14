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
import net.cpollet.sportracker.converter.ConversionService;
import net.cpollet.sportracker.converter.Converter;
import net.cpollet.sportracker.converter.ConverterRepository;
import net.cpollet.sportracker.data.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author Christophe Pollet
 */
public class UserToDBObjectConverter extends BaseConverter implements Converter<User, DBObject> {
	@Override
	public DBObject convert(User object) {
		DBObject dbObject = new BasicDBObject();

		Map map = getConversionService().convert(object, Object.class, Map.class);
		map.remove("person");

		dbObject.putAll(map);

		return dbObject;
	}

	@Override
	public Class from() {
		return User.class;
	}

	@Override
	public Class to() {
		return DBObject.class;
	}
}
