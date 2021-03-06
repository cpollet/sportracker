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

import com.mongodb.DBObject;
import net.cpollet.sportracker.converter.Converter;
import net.cpollet.sportracker.data.Person;
import net.cpollet.sportracker.data.User;
import org.bson.types.ObjectId;

/**
 * @author Christophe Pollet
 */
public class DBObjectToUserConverter extends BaseConverter implements Converter<DBObject, User> {
	@Override
	public User convert(DBObject object) {
		User user = new User();

		user.setUsername((String) object.get("username"));
		user.setPassword((String) object.get("password"));
		user.setId(((ObjectId) object.get("_id")).toHexString());

		user.setPerson(getConversionService().convert((DBObject) object.get("person"), DBObject.class, Person.class));

		return user;
	}

	@Override
	public Class from() {
		return DBObject.class;
	}

	@Override
	public Class to() {
		return User.class;
	}
}
