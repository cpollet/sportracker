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
import com.mongodb.DBCollection;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author Christophe Pollet
 */
public class MongoDbCollectionFactoryBean implements InitializingBean, FactoryBean<DBCollection> {
	private DB db;
	private String collectionName;

	private DBCollection dbCollection;

	@Override
	public DBCollection getObject() throws Exception {
		return dbCollection;
	}

	@Override
	public Class<?> getObjectType() {
		return DBCollection.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(db, "db must be set");
		Assert.notNull(collectionName, "collectionName must be set");

		dbCollection = db.getCollection(collectionName);
	}

	public void setDb(DB db) {
		this.db = db;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
