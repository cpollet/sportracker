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

package net.cpollet.sportracker.web.http;

/**
 * @author Christophe Pollet
 */
public class RestResponseBuilder {
	private Integer httpStatus;
	private String errorStatus;
	private String errorDescription;
	private Object object;

	private RestResponseBuilder() {
		httpStatus = 200;
		errorStatus = RestResponse.MESSAGE_OK;
	}

	public static RestResponseBuilder aRestResponse() {
		return new RestResponseBuilder();
	}

	public RestResponseBuilder withHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
		return this;
	}

	public RestResponseBuilder withErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
		return this;
	}

	public RestResponseBuilder withErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
		return this;
	}

	public RestResponseBuilder withObject(Object object) {
		this.object = object;
		return this;
	}

	public RestResponse build() {
		RestResponse restResponse = new RestResponse();
		restResponse.setHttpStatus(httpStatus);
		restResponse.setErrorStatus(errorStatus);
		restResponse.setErrorDescription(errorDescription);
		restResponse.setObject(object);
		return restResponse;
	}
}
