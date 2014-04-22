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
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Christophe Pollet
 */
public class InMemoryUserService implements UserService , InitializingBean {
	private Map<String, User> users;

	@Override
	public void create(User user) {
		if (users.containsKey(user.getUsername())) {
			throw new UsernameNotAvailableException("Username already used");
		}

		users.put(user.getUsername(), user);
	}

	@Override
	public boolean areCredentialsValid(String username, String password) {
		if (!users.containsKey(username)) {
			return false;
		}

		return (users.get(username).getPassword().equals(password));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		users = new HashMap<>();
	}
}
