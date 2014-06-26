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

package net.cpollet.sportracker.dozer;

import net.cpollet.sportracker.service.api.HashingService;
import org.dozer.MappingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Christophe Pollet
 */
@RunWith(MockitoJUnitRunner.class)
public class TestHashingDozerConverter {
	private HashingDozerConverter hashingDozerConverter;

	@Mock
	private HashingService hashingService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		Mockito.when(hashingService.hash("plain")).thenReturn("hash");

		hashingDozerConverter = new HashingDozerConverter();
		hashingDozerConverter.setHashingService(hashingService);
	}

	@Test
	public void convertReturnsNullWhenNullPassed() {
		// GIVEN
		String plain = null;

		// WHEN
		String hash = (String) hashingDozerConverter.convert(null, plain, null, null);

		// THEN
		assertThat(hash).isNull();
	}

	@Test
	public void convertThrowsExceptionWhenNoStringPassed() {
		// GIVEN
		Object plain = new Object();

		// THEN
		expectedException.expect(MappingException.class);
		expectedException.expectMessage("Converter HashingDozerConverter used incorrectly. Arguments passed were " //
				+ "null and " + plain.toString());

		// WHEN
		hashingDozerConverter.convert(null, plain, null, null);

	}

	@Test
	public void convertHashesTheInputStringAndReturnsTheHash() {
		// GIVEN
		String plain = "plain";

		// WHEN
		String hash = (String) hashingDozerConverter.convert(null, plain, null, null);

		// THEN
		assertThat(hash).isEqualTo("hash");
	}
}
