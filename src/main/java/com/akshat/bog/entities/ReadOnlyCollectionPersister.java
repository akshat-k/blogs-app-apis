package com.akshat.bog.entities;

import org.hibernate.mapping.Collection;

import org.hibernate.boot.MappingException;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.access.CollectionDataAccess;
import org.hibernate.persister.collection.BasicCollectionPersister;
import org.hibernate.persister.spi.PersisterCreationContext;

public class ReadOnlyCollectionPersister extends BasicCollectionPersister {
	private static Collection asInverse(Collection collection) {
		collection.setInverse(true);
		return collection;
	}

	public ReadOnlyCollectionPersister(Collection collectionBinding, CollectionDataAccess cacheAccessStrategy,
			PersisterCreationContext creationContext) throws MappingException, CacheException {
		super(asInverse(collectionBinding), cacheAccessStrategy, creationContext);
	}
}
