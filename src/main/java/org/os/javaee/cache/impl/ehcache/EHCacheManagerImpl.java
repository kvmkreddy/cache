package org.os.javaee.cache.impl.ehcache;

import java.util.Arrays;

import net.sf.ehcache.config.CacheConfiguration;

import org.os.javaee.cache.core.Cache;
import org.os.javaee.cache.core.CacheManager;

/**
 * <p>
 * Title: EHCacheManagerImpl
 * </p>
 * <p>
 * <b>Description:</b> EHCacheManagerImpl
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Open Source Development.
 * </p>
 * 
 * @author Murali Reddy
 * @version 1.0
 */
public class EHCacheManagerImpl implements
	CacheManager<net.sf.ehcache.config.CacheConfiguration> {

    private net.sf.ehcache.CacheManager cacheManager = null;

    /**
	 * 
	 */
    public EHCacheManagerImpl() {
	cacheManager = new net.sf.ehcache.CacheManager();
    }

    /**
     * @param cacheManager
     */
    public EHCacheManagerImpl(final net.sf.ehcache.CacheManager cacheManager) {
	this.cacheManager = cacheManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.os.javaee.cache.core.CacheManager#createCache(java.lang.String,
     * java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Cache<Object, Object> createCache(final String cacheName,
	    final CacheConfiguration configuration) {
	configuration.setName(cacheName);
	final Cache<Object, Object> cache = new EHCacheImpl(configuration);
	cacheManager.addCache((net.sf.ehcache.Ehcache) cache.getCacheImpl());
	return cache;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.os.javaee.cache.core.CacheManager#destroyCache(java.lang.String)
     */
    @Override
    public void destroyCache(final String cacheName) {
	cacheManager.removeCache(cacheName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.os.javaee.cache.core.CacheManager#getCache(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Cache<Object, Object> getCache(final String cacheName) {
	final net.sf.ehcache.Ehcache ehCache = cacheManager.getCache(cacheName);
	return (ehCache != null) ? new EHCacheImpl(ehCache) : null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.os.javaee.cache.core.CacheManager#getCacheNames()
     */
    @Override
    public Iterable<String> getCacheNames() {
	return Arrays.asList(cacheManager.getCacheNames());
    }
}