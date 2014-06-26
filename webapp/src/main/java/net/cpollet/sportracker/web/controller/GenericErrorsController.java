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

import net.cpollet.sportracker.web.http.RestResponse;
import net.cpollet.sportracker.web.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Christophe Pollet
 */
@Controller
public class GenericErrorsController {
	public static final String URL_INVALID_TOKEN = "/403";

	@RequestMapping(value = "/403")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public RestResponse invalid() {
		throw new InvalidTokenException("The provided token is invalid");
	}
}
