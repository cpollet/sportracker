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

package net.cpollet.sportracker.repository.mongodb;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import net.cpollet.sportracker.converter.ConversionService;
import net.cpollet.sportracker.data.User;
import net.cpollet.sportracker.repository.UserRepository;
import net.cpollet.sportracker.repository.exception.DuplicateKeyException;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Christophe Pollet
 */
public class MongoDbUserRepository implements UserRepository<MongoDbSpecification<User>> {
	private ConversionService conversionService;

	private DBCollection collection;

	@Override
	public String add(User user) {
		DBObject dbObject = conversionService.convert(user, DBObject.class);

		try {
			collection.save(dbObject);
		} catch (MongoException.DuplicateKey e) {
			throw new DuplicateKeyException();
		}

		return ((ObjectId) dbObject.get("_id")).toHexString();
	}

	@Override
	public void remove(User user) {

	}

	@Override
	public void update(User user) {

	}

	@Override
	public List<User> query(MongoDbSpecification<User> specification) {
		List<User> users = new LinkedList<>();
		DBCursor cursor = collection.find(specification.toMongoDbQuery());

		while (cursor.hasNext()) {
			users.add(conversionService.convert(cursor.next(), DBObject.class, User.class));
		}

		return users;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public void setCollection(DBCollection collection) {
		this.collection = collection;
	}
}
