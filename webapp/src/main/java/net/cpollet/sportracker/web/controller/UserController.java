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

import net.cpollet.sportracker.service.api.HashingService;
import net.cpollet.sportracker.service.api.UserService;
import net.cpollet.sportracker.service.exception.UsernameNotAvailableException;
import net.cpollet.sportracker.web.data.UserData;
import net.cpollet.sportracker.web.http.RestResponse;
import net.cpollet.sportracker.web.http.RestResponseBuilder;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Christophe Pollet
 */
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static final String MESSAGE_USERNAME_NOT_AVAILABLE = "UsernameNotAvailable";

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	private Mapper dozer;

	@Autowired
	private UserService userService;

	@Autowired
	private HashingService hashingService;

	@RequestMapping(value = "/api/v1/user", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public RestResponse create(@RequestBody(required = true) @Valid UserData userData) {
		logger.info("Adding new userData: {}", userData.getUsername());

		userService.create(dozer.map(userData, net.cpollet.sportracker.data.User.class));

		return RestResponseBuilder.aRestResponse() //
				.withHttpStatus(HttpStatus.CREATED.value()) //
				.withObject(userData) //
				.build();
	}

	@ExceptionHandler({UsernameNotAvailableException.class})
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ResponseBody
	public RestResponse usernameError(HttpServletRequest req, Exception exception) {
		return RestResponseBuilder.aRestResponse() //
				.withHttpStatus(HttpStatus.CONFLICT.value()) //
				.withErrorStatus(MESSAGE_USERNAME_NOT_AVAILABLE) //
				.withErrorDescription(exception.getMessage()) //
				.build();
	}
}
