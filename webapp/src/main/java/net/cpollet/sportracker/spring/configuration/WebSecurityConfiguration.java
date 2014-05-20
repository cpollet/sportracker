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

package net.cpollet.sportracker.spring.configuration;

import net.cpollet.sportracker.service.TokenService;
import net.cpollet.sportracker.spring.security.InvalidTokenAuthenticationEntryPoint;
import net.cpollet.sportracker.web.controller.GenericErrorsController;
import net.cpollet.sportracker.web.filter.RestAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutFilter;


/**
 * @author Christophe Pollet
 */
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private TokenService tokenService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http //
				.csrf().disable() //
				.httpBasic().disable() //
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //
				.exceptionHandling().authenticationEntryPoint(invalidTokenAuthenticationEntryPoint());

		http.addFilterAfter(restAuthenticationFilter(), LogoutFilter.class);

		http.authorizeRequests() //
				.anyRequest().permitAll() //
				.antMatchers("/api/**").authenticated();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring() //
				.antMatchers("/*", "/webjars/**", "/css/**", "/js/**", "/partials/**" , "/vendor/**") //
				.antMatchers("/api/*/token") //
				.antMatchers(HttpMethod.POST, "/api/*/user");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	@Bean
	public RestAuthenticationFilter restAuthenticationFilter() {
		RestAuthenticationFilter restAuthenticationFilter = new RestAuthenticationFilter();
		restAuthenticationFilter.setTokenService(tokenService);
		return restAuthenticationFilter;
	}

	@Bean
	public InvalidTokenAuthenticationEntryPoint invalidTokenAuthenticationEntryPoint() {
		return new InvalidTokenAuthenticationEntryPoint(GenericErrorsController.URL_INVALID_TOKEN);
	}
}
