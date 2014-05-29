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

import com.google.common.base.Splitter;
import net.cpollet.sportracker.web.data.RestResponse;
import net.cpollet.sportracker.web.data.RestResponseBuilder;
import net.cpollet.sportracker.web.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Christophe Pollet
 */
@ControllerAdvice
public class ExceptionHandlingController {
	private static final String MESSAGE_INVALID_TOKEN = "InvalidToken";
	private static final String MESSAGE_VALIDATION_ERROR = "ValidationError";

	@ExceptionHandler({InvalidTokenException.class})
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ResponseBody
	public RestResponse tokenError(HttpServletRequest request, Exception exception) {
		return RestResponseBuilder.aRestResponse() //
				.withHttpStatus(HttpStatus.FORBIDDEN.value()) //
				.withErrorStatus(MESSAGE_INVALID_TOKEN) //
				.withErrorDescription(exception.getMessage()) //
				.build();
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RestResponse validationError(HttpServletRequest request, Exception exception) {
		MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
		BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();

		List<ValidationError> errors = new ArrayList<>(bindingResult.getErrorCount());

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String field = fieldError.getObjectName() + "." + fieldError.getField();
			String error = fieldError.getCode();
			Map<String, String> params = parseValidationParameters(fieldError);
			Object value = fieldError.getRejectedValue();

			errors.add(new ValidationError(field, error, params, value));
		}

		for (ObjectError objectError : bindingResult.getGlobalErrors()) {
			String field = objectError.getObjectName();
			String error = objectError.getCode();
			Map<String, String> params = parseValidationParameters(objectError);

			errors.add(new ValidationError(field, error, params));
		}

		return RestResponseBuilder.aRestResponse() //
				.withHttpStatus(HttpStatus.BAD_REQUEST.value()) //
				.withErrorStatus(MESSAGE_VALIDATION_ERROR) //
				// .withErrorDescription(exception.getMessage()) //
				.withObject(errors) //
				.build();
	}

	private Map<String, String> parseValidationParameters(ObjectError error) {
		if (error.getDefaultMessage().isEmpty()) {
			return Collections.emptyMap();
		}

		return Splitter.on('&').trimResults().withKeyValueSeparator('=').split(error.getDefaultMessage());
	}

	private class ValidationError {
		public String field;
		public String validation;
		public Map<String, String> params;
		public Object value;

		private ValidationError(String field, String validation, Map<String, String> params, Object value) {
			this.field = field;
			this.validation = validation;
			this.params = params;
			this.value = value;
		}

		public ValidationError(String field, String validation, Map<String, String> params) {
			this.field = field;
			this.validation = validation;
			this.params = params;
		}
	}
}
