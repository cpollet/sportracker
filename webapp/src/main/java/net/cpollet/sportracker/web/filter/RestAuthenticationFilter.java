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

package net.cpollet.sportracker.web.filter;

import net.cpollet.sportracker.service.TokenService;
import net.cpollet.sportracker.spring.security.DefaultAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Christophe Pollet
 */
public class RestAuthenticationFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationFilter.class);

	private static final String HEADER_SECURITY_TOKEN = "X-ST-Token";

	private TokenService tokenService;


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// nothing
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		logger.trace("Filtering request");

		if (servletRequest instanceof HttpServletRequest) {
			HttpServletRequestWrapper request = new HttpServletRequestWrapper((HttpServletRequest) servletRequest);

			String token = request.getHeader(HEADER_SECURITY_TOKEN);

			logger.debug("{}: {}", new Object[]{HEADER_SECURITY_TOKEN,  token});

			Authentication authentication = DefaultAuthentication.NOT_AUTHENTICATED;

			if (null != token) {
				String userid = tokenService.getUserid(token);
				if (null != userid) {
					authentication = new DefaultAuthentication(userid, token);
				}
			}

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		// nothing
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}
}
