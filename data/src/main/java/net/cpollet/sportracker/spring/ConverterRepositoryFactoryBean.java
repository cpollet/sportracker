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

package net.cpollet.sportracker.spring;

import net.cpollet.sportracker.converter.Converter;
import net.cpollet.sportracker.converter.ConverterRepository;
import net.cpollet.sportracker.converter.DefaultConverterRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Christophe Pollet
 */
public class ConverterRepositoryFactoryBean implements ApplicationContextAware, FactoryBean<ConverterRepository> {
	private ApplicationContext context;
	private ConverterRepository converterRepository;

	@Override
	public ConverterRepository getObject() throws Exception {
		if (converterRepository == null) {
			buildObject();
		}

		return converterRepository;
	}

	private void buildObject() {
		converterRepository = new DefaultConverterRepository();

		for (Converter converter : context.getBeansOfType(Converter.class).values()) {
			converterRepository.add(converter.from(), converter.to(), converter);
		}
	}

	@Override
	public Class<?> getObjectType() {
		return ConverterRepository.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}
