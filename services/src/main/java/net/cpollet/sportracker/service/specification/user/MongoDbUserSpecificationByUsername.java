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

package net.cpollet.sportracker.service.specification.user;

import com.mongodb.BasicDBObject;
import net.cpollet.sportracker.data.User;
import net.cpollet.sportracker.repository.Specification;
import net.cpollet.sportracker.repository.mongodb.MongoDbSpecification;

/**
 * @author Christophe Pollet
 */
public class MongoDbUserSpecificationByUsername implements Specification<User>, MongoDbSpecification<User> {
	private String username;

	public MongoDbUserSpecificationByUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isSatisfiedBy(User object) {
		return username.equals(object.getUsername());
	}

	@Override
	public BasicDBObject toMongoDbQuery() {
		return new BasicDBObject("username", username);
	}
}
