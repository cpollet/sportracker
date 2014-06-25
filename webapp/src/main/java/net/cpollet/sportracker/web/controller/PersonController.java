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

package net.cpollet.sportracker.web.controller;

/**
 * @author Christophe Pollet
 */

import net.cpollet.sportracker.data.Person;
import net.cpollet.sportracker.data.User;
import net.cpollet.sportracker.quantities.LengthQuantity;
import net.cpollet.sportracker.quantities.MassQuantity;
import net.cpollet.sportracker.service.PersonService;
import net.cpollet.sportracker.service.UserService;
import net.cpollet.sportracker.units.Length;
import net.cpollet.sportracker.units.LengthUnit;
import net.cpollet.sportracker.units.MassUnit;
import net.cpollet.sportracker.web.data.PersonData;
import net.cpollet.sportracker.web.data.Quantity;
import net.cpollet.sportracker.web.data.RestResponse;
import net.cpollet.sportracker.web.data.RestResponseBuilder;
import net.cpollet.sportracker.web.data.builder.QuantityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@Controller
public class PersonController {
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/api/v1/users/{userId}/person", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse getPerson(@PathVariable("userId") Serializable userId) {
		User user = userService.get(userId);
		Person person = personService.get(user);

		PersonData personData = null;

		if (person != null) {
			personData = new PersonData();
			personData.setBirthday(person.getBirthday());
			if (person.getGender() != null) {
				personData.setGender(PersonData.Gender.valueOf(person.getGender().name()));
			}
			if (person.getWeight() != null) {
				Quantity quantity = QuantityBuilder.aQuantity().withValue(person.getWeight().in(MassUnit.kg).getValue()).withUnit("kg").build();
				personData.setWeight(quantity);
			}
			if (person.getHeight() != null) {
				Quantity quantity = QuantityBuilder.aQuantity().withValue(person.getHeight().in(LengthUnit.cm).getValue()).withUnit("cm").build();
				personData.setHeight(quantity);
			}
		}

		return RestResponseBuilder.aRestResponse() //
				.withHttpStatus(HttpStatus.OK.value()) //
				.withObject(personData) //
				.build();
	}

	@RequestMapping(value = "/api/v1/users/{userId}/person", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestResponse savePerson(@PathVariable("userId") Serializable userId, @RequestBody(required = true) PersonData personData) {
		logger.info("savePerson(" + userId + ")");
		logger.info(personData.toString());

		User user = userService.get(userId);

		Person person = new Person();
		if (personData.getGender() != null) {
			person.setGender(Person.Gender.valueOf(personData.getGender().name()));
		}
		person.setWeight(new MassQuantity(personData.getWeight().getValue(), MassUnit.kg));
		person.setHeight(new LengthQuantity(personData.getHeight().getValue(), LengthUnit.cm));
		person.setBirthday(personData.getBirthday());

		personService.update(person, user);

		return RestResponseBuilder.aRestResponse() //
				.withHttpStatus(HttpStatus.OK.value()) //
				.build();
	}
}
