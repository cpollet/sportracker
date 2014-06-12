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

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author Christophe Pollet
 */
public class MongoDbFactoryBean implements InitializingBean, FactoryBean<DB> {
	private static final Logger logger = LoggerFactory.getLogger(MongoDbFactoryBean.class);

	private String host;
	private Integer port;
	private String databaseName;

	private DB db;

	@Override
	public DB getObject() throws Exception {
		return db;
	}

	@Override
	public Class<?> getObjectType() {
		return DB.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(databaseName, "databaseName must be set");
		Assert.notNull(host, "host must be set");
		Assert.notNull(port, "port must be set");

		logger.info("Connecting to {}:{}", new Object[]{host, port});
		logger.info("Using database {}", databaseName);

		MongoClient mongoClient = new MongoClient(host, port);

		db = mongoClient.getDB(databaseName);
	}
}
