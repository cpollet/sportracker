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

package net.cpollet.sportracker.service;

import net.cpollet.sportracker.data.Person;
import net.cpollet.sportracker.data.User;
import net.cpollet.sportracker.repository.UserRepository;
import net.cpollet.sportracker.repository.mongodb.MongoDbSpecification;

/**
 * @author Christophe Pollet
 */
public class MongoDbPersonService implements PersonService {
	private UserRepository<MongoDbSpecification<User>> userRepository;

	@Override
	public void update(Person person, User user) {
		user.setPerson(person);
		userRepository.add(user);
	}

	@Override
	public Person get(User user) {
		return user.getPerson();
	}

	public void setUserRepository(UserRepository<MongoDbSpecification<User>> userRepository) {
		this.userRepository = userRepository;
	}
}
