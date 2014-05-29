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

import net.cpollet.sportracker.web.validator.PasswordsMatchConstraint;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Christophe Pollet
 */
@PasswordsMatchConstraint
public class User {
	@NotNull(message = "")
	@Size(min = 3, max = 20, message = "min={min}&max={max}")
	private String username;

	@NotNull(message = "")
	@Size(min = 6, message = "min={min}")
	private String password1;

	@NotNull(message = "")
	@Size(min = 6, message = "min={min}")
	private String password2;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
