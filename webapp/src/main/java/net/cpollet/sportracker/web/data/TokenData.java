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

package net.cpollet.sportracker.web.data;

import java.io.Serializable;

/**
 * @author Christophe Pollet
 */
public class TokenData {
	private String token;
	private Serializable userId;

	public TokenData(String token, Serializable userId) {
		this.token = token;
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Serializable getUserId() {
		return userId;
	}

	public void setUserId(Serializable userId) {
		this.userId = userId;
	}
}
