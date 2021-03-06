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

import net.cpollet.sportracker.service.api.HashingService;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Christophe Pollet
 */
public class BCryptService implements HashingService {
	@Override
	public String hash(String plain) {
		return BCrypt.hashpw(plain, BCrypt.gensalt());
	}

	@Override
	public boolean verify(String hashed, String plain) {
		return BCrypt.checkpw(plain, hashed);
	}
}
