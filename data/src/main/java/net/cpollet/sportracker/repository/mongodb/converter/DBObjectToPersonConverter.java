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
import net.cpollet.sportracker.quantities.LengthQuantity;
import net.cpollet.sportracker.quantities.MassQuantity;
import net.cpollet.sportracker.quantities.QuantityFactory;

import java.util.Date;

/**
 * @author Christophe Pollet
 */
public class DBObjectToPersonConverter extends BaseConverter implements Converter<DBObject, Person> {
	@Override
	public Person convert(DBObject object) {
		Person person = new Person();

		person.setGender(Person.Gender.valueOf((String)object.get("gender")));
		person.setBirthday((Date) object.get("birthday"));
		person.setWeight((MassQuantity) QuantityFactory.MASS.create((String) object.get("weight")));
		person.setHeight((LengthQuantity) QuantityFactory.LENGTH.create((String) object.get("height")));

		return person;
	}

	@Override
	public Class from() {
		return DBObject.class;
	}

	@Override
	public Class to() {
		return Person.class;
	}
}
