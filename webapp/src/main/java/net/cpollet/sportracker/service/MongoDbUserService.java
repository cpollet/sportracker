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

import net.cpollet.sportracker.data.User;
import net.cpollet.sportracker.exception.UsernameNotAvailableException;
import net.cpollet.sportracker.repository.UserRepository;
import net.cpollet.sportracker.repository.exception.DuplicateKeyException;
import net.cpollet.sportracker.repository.mongodb.MongoDbSpecification;
import net.cpollet.sportracker.service.specification.user.MongoDbUserSpecificationByUsername;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Christophe Pollet
 */
public class MongoDbUserService extends BaseUserService implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(MongoDbUserService.class);

	private UserRepository<MongoDbSpecification<User>> userRepository;

	@Override
	public void create(User user) {
		try {
			userRepository.add(user);
		} catch (DuplicateKeyException e) {
			throw new UsernameNotAvailableException("Username already used");
		}
	}

	@Override
	public boolean areCredentialsValid(String username, String password) {
		List<User> users = userRepository.query(new MongoDbUserSpecificationByUsername(username));

		logger.info("Got {} users", users.size());

		if (users.size() == 0) {
			return false;
		}

		if (users.size() > 1) {
			logger.error("Username [{}] found twice in database", username);
			return false;
		}

		return passwordMatch(password, users.get(0));
	}

	public void setUserRepository(UserRepository<MongoDbSpecification<User>> userRepository) {
		this.userRepository = userRepository;
	}
}
