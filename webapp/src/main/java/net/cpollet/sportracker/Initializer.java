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

package net.cpollet.sportracker;

import net.cpollet.sportracker.data.User;
import net.cpollet.sportracker.service.TokenService;
import net.cpollet.sportracker.service.api.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author Christophe Pollet
 */
public class Initializer implements InitializingBean {
	private static final String DEFAULT_USERNAME = "cpollet";
	private static final String DEFAULT_PASSWORD = "password";

	private TokenService tokenService;
	private UserService userService;

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (!userService.areCredentialsValid(DEFAULT_USERNAME, DEFAULT_PASSWORD)) {
			User user = new User();

			user.setUsername(DEFAULT_USERNAME);
			user.setPassword(BCrypt.hashpw(DEFAULT_PASSWORD, BCrypt.gensalt()));
			userService.create(user);
		}
	}
}
