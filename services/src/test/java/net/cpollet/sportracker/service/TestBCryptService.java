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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Christophe Pollet
 */
@RunWith(JUnit4.class)
public class TestBCryptService {
	private HashingService hashingService;

	@Before
	public void setUp() {
		hashingService = new BCryptService();
	}

	@Test
	public void hashReturnsHash() {
		// GIVEN
		String plain = "plain";

		// WHEN
		String hash = hashingService.hash(plain);

		// THEN
		assertThat(hash) //
				.isNotNull() //
				.isNotEmpty() //
				.isNotEqualTo(plain);
	}

	@Test
	public void verifyReturnsTrueIfHashMatchesPlain() {
		// GIVEN
		String plain = "plain";
		String hash = hashingService.hash(plain);

		// WHEN
		Boolean result = hashingService.verify(hash, plain);

		// THEN
		assertThat(result).isTrue();
	}
}
