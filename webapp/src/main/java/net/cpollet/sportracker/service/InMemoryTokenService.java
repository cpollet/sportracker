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

import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Christophe Pollet
 */
public class InMemoryTokenService implements TokenService, InitializingBean {
	private Map<String, String> tokens;

	@Override
	public boolean isValid(String userid, String token) {
		return tokens.containsKey(token) && tokens.get(token).equals(userid);
	}

	@Override
	public String createToken(String userid) {
		String token = UUID.randomUUID().toString();
		tokens.put(token, userid);
		return token;
	}

	@Override
	public String getUserid(String token) {
		return tokens.get(token);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		tokens = new HashMap<>();
	}
}
