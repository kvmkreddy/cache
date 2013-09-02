package org.os.javaee.cache;

import net.sf.ehcache.config.CacheConfiguration;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.os.javaee.cache.core.Cache;
import org.os.javaee.cache.impl.ehcache.EHCacheManagerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: EHCacheManagerImplTest</p>
 * <p><b>Description:</b> EHCacheManagerImplTest</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class EHCacheManagerImplTest {

	private static EHCacheManagerImpl cacheManager;
	private static CacheConfiguration cacheConfiguration;
	
	private static final String CACHE_NAME="MyCache";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-ehCache-config.xml");
		cacheManager = ctx.getBean("cacheManager", EHCacheManagerImpl.class);
		cacheConfiguration = ctx.getBean("cacheConfiguration", CacheConfiguration.class);
	}
	
	
	@After
	public void cleanCache(){
		cacheManager.destroyCache(CACHE_NAME);
	}
	
	@Test
	public void createCache(){
		Cache<?,?> cache = cacheManager.createCache(CACHE_NAME, cacheConfiguration);
		Assert.assertNotNull(cache);
	}

	@Test
	public void readCache(){
		cacheManager.createCache(CACHE_NAME, cacheConfiguration);
		Cache<?,?> cache = cacheManager.getCache(CACHE_NAME);
		Assert.assertNotNull(cache);
	}
	
	@Test
	public void deleteCache(){
		cacheManager.createCache(CACHE_NAME, cacheConfiguration);
		Cache<?,?> cache = cacheManager.getCache(CACHE_NAME);
		Assert.assertNotNull(cache);
		cacheManager.destroyCache(CACHE_NAME);
		Cache<?,?> destoryedCache = cacheManager.getCache(CACHE_NAME);
		Assert.assertNull(destoryedCache);
	}
}