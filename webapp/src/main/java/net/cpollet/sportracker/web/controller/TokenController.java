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

import net.cpollet.sportracker.service.TokenService;
import net.cpollet.sportracker.service.UserService;
import net.cpollet.sportracker.web.data.Credentials;
import net.cpollet.sportracker.web.data.RestResponse;
import net.cpollet.sportracker.web.data.RestResponseBuilder;
import net.cpollet.sportracker.web.data.Token;
import net.cpollet.sportracker.web.exception.InvalidCredentialsException;
import net.cpollet.sportracker.web.exception.InvalidTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author Christophe Pollet
 */
@Controller
public class TokenController {
	private static final Logger logger = LoggerFactory.getLogger(TokenController.class);

	private static final String MESSAGE_INVALID_CREDENTIALS = "InvalidCredentials";

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/api/v1/token", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public RestResponse create(@RequestBody(required = true) Credentials credentials) {
		logger.info("Attempt login: {}", credentials.getUsername());

		if (!userService.areCredentialsValid(credentials.getUsername(), credentials.getPassword())) {
			throw new InvalidCredentialsException("Invalid username and/or password");
		}

		String token = tokenService.createToken(credentials.getUsername());
		Serializable userId = userService.getIdForUsername(credentials.getUsername());

		return RestResponseBuilder.aRestResponse() //
				.withHttpStatus(HttpStatus.CREATED.value()) //
				.withObject(new Token(token, userId))
				.build();
	}

	@ExceptionHandler({InvalidCredentialsException.class})
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ResponseBody
	public RestResponse usernameError(HttpServletRequest request, Exception exception) {
		return RestResponseBuilder.aRestResponse() //
				.withHttpStatus(HttpStatus.FORBIDDEN.value()) //
				.withErrorStatus(MESSAGE_INVALID_CREDENTIALS) //
				.withErrorDescription(exception.getMessage()) //
				.build();
	}

	@RequestMapping(value = "/api/v1/token", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestResponse validate(@RequestParam(required = true) String username, @RequestParam(required = true) String token) {
		logger.info("Attempt to validate token for: {}", username);

		if (!tokenService.isValid(username, token)) {
			throw new InvalidTokenException("The provided token is invalid");
		}

		Serializable userId = userService.getIdForUsername(username);

		return RestResponseBuilder.aRestResponse() //
				.withObject(new Token(token, userId)) //
				.build();
	}
}
