package org.os.javaee.cache;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.os.javaee.cache.core.Cache;
import org.os.javaee.cache.impl.infinispan.CacheManagerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: InfinispanCacheManagerImplTest</p>
 * <p><b>Description:</b> InfinispanCacheManagerImplTest</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @version 1.0
 * @author Murali Reddy
 */
public class InfinispanCacheManagerImplTest {

	private static CacheManagerImpl cacheManager;
	private static org.infinispan.configuration.cache.Configuration cacheConfiguration;

	private static final String CACHE_NAME="MyCache";
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void initClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-infinispan-config.xml");
		cacheManager = ctx.getBean("cacheManager", CacheManagerImpl.class);
		//cacheConfiguration = ctx.getBean("cacheConfigurationOverrides", ConfigurationOverrides.class);
		cacheConfiguration = new org.infinispan.configuration.cache.ConfigurationBuilder().eviction().strategy(org.infinispan.eviction.EvictionStrategy.LIRS).maxEntries(10).build();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception { }

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception { }

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void clearCache() throws Exception {
		cacheManager.destroyCache(CACHE_NAME);
	}

	/**
	 * Test method for {@link org.os.javaee.cache.impl.infinispan.CacheManagerImpl#createCache(java.lang.String, org.infinispan.configuration.cache.Configuration)}.
	 */
	@Test
	public final void createCache() {
		Cache<?,?> cache = cacheManager.createCache(CACHE_NAME, cacheConfiguration);
		Assert.assertNotNull(cache);
	}

	/**
	 * Test method for {@link org.os.javaee.cache.impl.infinispan.CacheManagerImpl#destroyCache(java.lang.String)}.
	 */
	@Test
	public final void destroyCache() {
		cacheManager.createCache(CACHE_NAME, cacheConfiguration);
		Cache<?,?> cache = cacheManager.getCache(CACHE_NAME);
		Assert.assertNotNull(cache);
		cacheManager.destroyCache(CACHE_NAME);
		Cache<?,?> destoryedCache = cacheManager.getCache(CACHE_NAME);
		//TODO --> Test case is failing here. Needs to fix it.
		Assert.assertNull(destoryedCache);
	}

	/**
	 * Test method for {@link org.os.javaee.cache.impl.infinispan.CacheManagerImpl#getCache(java.lang.String)}.
	 */
	@Test
	public final void getCache() {
		cacheManager.createCache(CACHE_NAME, cacheConfiguration);
		Cache<?,?> cache = cacheManager.getCache(CACHE_NAME);
		Assert.assertNotNull(cache);
	}

	/**
	 * Test method for {@link org.os.javaee.cache.impl.infinispan.CacheManagerImpl#getCacheNames()}.
	 */
	@Test
	public final void getCacheNames() {
		cacheManager.createCache(CACHE_NAME, cacheConfiguration);
		Iterable<String> cacheNames = cacheManager.getCacheNames();
		Assert.assertNotNull(cacheNames);
		Assert.assertNotNull(cacheNames.iterator());
		for(Iterator<String> iter=cacheNames.iterator();iter.hasNext();){
			String cacheName = iter.next();
			Assert.assertNotNull(cacheName);
			Assert.assertEquals(cacheName, CACHE_NAME);
		}
	}
}