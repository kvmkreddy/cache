package org.os.javaee.cache.impl.infinispan;

import org.os.javaee.cache.core.Cache;
import org.os.javaee.cache.core.CacheManager;

/**
 * <p>Title: CacheManagerImpl</p>
 * <p><b>Description:</b> CacheManagerImpl</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class CacheManagerImpl implements CacheManager<org.infinispan.configuration.cache.Configuration> {

	private org.infinispan.manager.EmbeddedCacheManager cacheManager;
	
	public CacheManagerImpl(){
		this.cacheManager = new org.infinispan.manager.DefaultCacheManager();
	}
	
	public CacheManagerImpl(org.infinispan.manager.EmbeddedCacheManager cacheManager){
		this.cacheManager = cacheManager;
	}
	
	public CacheManagerImpl(org.infinispan.configuration.cache.Configuration config){
		this.cacheManager = new org.infinispan.manager.DefaultCacheManager(config);
	}
	
	@Override
	public Cache<Object, Object> createCache(String cacheName,org.infinispan.configuration.cache.Configuration configuration) {
		cacheManager.defineConfiguration(cacheName,configuration);
		return getCache(cacheName);
	}

	@Override
	public void destroyCache(String cacheName) {
		cacheManager.removeCache(cacheName);
	}

	@Override
	public Cache<Object, Object> getCache(String cacheName) {
		return new CacheImpl(cacheManager.getCache(cacheName));
	}

	@Override
	public Iterable<String> getCacheNames() {
		return cacheManager.getCacheNames();
	}
}