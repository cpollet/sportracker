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

package net.cpollet.sportracker.converter;

import net.cpollet.sportracker.test.support.Bean;
import org.fest.assertions.MapAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Christophe Pollet
 */
@RunWith(JUnit4.class)
public class TestObjectToMapConverter {
	private ObjectToMapConverter converter;

	@Before
	public void setUp() {
		converter = new ObjectToMapConverter();
	}

	@Test
	public void convertReturnsCorrectMap() {
		// GIVEN
		Bean bean = new Bean();
		bean.setStringField("stringValue");
		bean.setIntegerField(1);

		// WHEN
		Map<String, Object> actualMap = converter.convert(bean);

		// THEN
		assertThat(actualMap) //
				.hasSize(2) //
				.includes(MapAssert.entry("integerField", 1)) //
				.includes(MapAssert.entry("stringField", "stringValue"));
	}
}
