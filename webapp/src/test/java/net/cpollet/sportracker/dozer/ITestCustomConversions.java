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
import net.cpollet.sportracker.web.data.UserData;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Christophe Pollet
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-root-context.xml"})
public class ITestCustomConversions {
	@Autowired
	private Mapper dozer;

	@Autowired
	private HashingService hashingService;

	@Test
	public void convertWebUserToModelUser() {
		// GIVEN
		UserData webUserData = new UserData();
		webUserData.setUsername("username");
		webUserData.setPassword1("password1");
		webUserData.setPassword2("password2");

		// WHEN
		net.cpollet.sportracker.data.User user = dozer.map(webUserData, net.cpollet.sportracker.data.User.class);

		// THEN
		assertThat(user.getUsername()).isEqualTo("username");
		assertThat(hashingService.verify(user.getPassword(), webUserData.getPassword1())).isTrue();
	}
}
