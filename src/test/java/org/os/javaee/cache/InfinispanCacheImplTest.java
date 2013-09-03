package org.os.javaee.cache;

import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.os.javaee.cache.core.Cache;
import org.os.javaee.cache.impl.infinispan.CacheManagerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: InfinispanCacheImplTest</p>
 * <p><b>Description:</b> InfinispanCacheImplTest</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class InfinispanCacheImplTest {

	private static CacheManagerImpl cacheManager;
	private static org.infinispan.configuration.cache.Configuration cacheConfiguration;

	private static final String CACHE_NAME="MyCache";
	
	private static final String KEY_TWO = "Murali";
	private static final String VALUE_TWO = "K V M Krishna Reddy";
	
	private static final String KEY_ONE = "SOME KEY ONE";
	private static final String VALUE_ONE = "SOME VALUE FOR KEY ONE";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-infinispan-config.xml");
		cacheManager = ctx.getBean("cacheManager", CacheManagerImpl.class);
		//cacheConfiguration = ctx.getBean("cacheConfigurationOverrides", ConfigurationOverrides.class);
		cacheConfiguration = new org.infinispan.configuration.cache.ConfigurationBuilder().eviction().strategy(org.infinispan.eviction.EvictionStrategy.LIRS).maxEntries(10).build();
	}

	@Before
	public void init() throws Exception {
		cacheManager.createCache(CACHE_NAME,cacheConfiguration);
	}
	
	@After
	public void cleanUp() throws Exception {
		cacheManager.destroyCache(CACHE_NAME);
	}

	@Test
	public final void get() {
		Cache<Object,Object> cache = cacheManager.getCache(CACHE_NAME);
		cache.put(KEY_ONE, VALUE_ONE);
		cache.put(KEY_TWO, VALUE_TWO);
		
		Assert.assertNotNull(cache.containsKey(KEY_ONE));
		Assert.assertNotNull(cache.containsKey(KEY_TWO));
		
		Assert.assertTrue(cache.containsKey(KEY_ONE));
		Assert.assertTrue(cache.containsKey(KEY_TWO));
		
		Assert.assertNotNull(cache.get(KEY_ONE));
		Assert.assertNotNull(cache.get(KEY_TWO));
		
		Assert.assertSame(((String)cache.get(KEY_ONE)).toString(),VALUE_ONE);
		Assert.assertSame(((String)cache.get(KEY_TWO)).toString(),VALUE_TWO);
	}

	@Test
	public final void getAll() {
		Cache<Object,Object> cache = cacheManager.getCache(CACHE_NAME);
		cache.put(KEY_ONE, VALUE_ONE);
		cache.put(KEY_TWO, VALUE_TWO);

		Map<? extends Object,? extends Object> allCachedData = cache.getAll();
		Assert.assertNotNull(allCachedData);
		Assert.assertNotNull(allCachedData.size());
		Assert.assertNotNull(allCachedData.size() == 2);
		Assert.assertNotNull(allCachedData.containsKey(KEY_ONE));
		Assert.assertNotNull(allCachedData.containsKey(KEY_TWO));
		Assert.assertNotNull(allCachedData.containsValue(VALUE_ONE));
		Assert.assertNotNull(allCachedData.containsValue(VALUE_TWO));
		Assert.assertTrue(allCachedData.containsKey(KEY_ONE));
		Assert.assertTrue(allCachedData.containsKey(KEY_TWO));
		Assert.assertTrue(allCachedData.containsValue(VALUE_ONE));
		Assert.assertTrue(allCachedData.containsValue(VALUE_TWO));
	}

	@Test
	public final void put() {
		Cache<Object,Object> cache = cacheManager.getCache(CACHE_NAME);
		cache.put(KEY_ONE, VALUE_ONE);

		Map<? extends Object,? extends Object> allCachedData = cache.getAll();
		Assert.assertNotNull(allCachedData);
		Assert.assertNotNull(allCachedData.size());
		Assert.assertNotNull(allCachedData.size() == 1);
		Assert.assertNotNull(allCachedData.containsKey(KEY_ONE));
		Assert.assertNotNull(allCachedData.containsValue(VALUE_ONE));
		Assert.assertTrue(allCachedData.containsKey(KEY_ONE));
		Assert.assertTrue(allCachedData.containsValue(VALUE_ONE));
	}

	@Test
	public final void putAll() {
		Cache<Object,Object> cache = cacheManager.getCache(CACHE_NAME);
		cache.put(KEY_ONE, VALUE_ONE);
		cache.put(KEY_TWO, VALUE_TWO);

		Map<? extends Object,? extends Object> allCachedData = cache.getAll();
		Assert.assertNotNull(allCachedData);
		Assert.assertNotNull(allCachedData.size());
		Assert.assertNotNull(allCachedData.size() == 2);
		Assert.assertNotNull(allCachedData.containsKey(KEY_ONE));
		Assert.assertNotNull(allCachedData.containsKey(KEY_TWO));
		Assert.assertNotNull(allCachedData.containsValue(VALUE_ONE));
		Assert.assertNotNull(allCachedData.containsValue(VALUE_TWO));
		Assert.assertTrue(allCachedData.containsKey(KEY_ONE));
		Assert.assertTrue(allCachedData.containsKey(KEY_TWO));
		Assert.assertTrue(allCachedData.containsValue(VALUE_ONE));
		Assert.assertTrue(allCachedData.containsValue(VALUE_TWO));
	}

	@Test
	public final void remove() {
		Cache<Object,Object> cache = cacheManager.getCache(CACHE_NAME);
		cache.put(KEY_ONE, VALUE_ONE);
		cache.put(KEY_TWO, VALUE_TWO);
		
		Map<? extends Object,? extends Object> allCachedData = cache.getAll();
		Assert.assertNotNull(allCachedData);
		Assert.assertNotNull(allCachedData.size());
		Assert.assertNotNull(allCachedData.size() == 1);
		Assert.assertNotNull(allCachedData.containsKey(KEY_ONE));
		Assert.assertNotNull(allCachedData.containsValue(VALUE_ONE));
		Assert.assertTrue(allCachedData.containsKey(KEY_ONE));
		Assert.assertTrue(allCachedData.containsValue(VALUE_ONE));

		cache.remove(KEY_ONE);
		Map<? extends Object,? extends Object> allCachedDataPostRemoval = cache.getAll();
		Assert.assertNotNull(allCachedDataPostRemoval);
		Assert.assertNotNull(allCachedDataPostRemoval.size());
		Assert.assertNotNull(allCachedDataPostRemoval.size() == 0);
		Assert.assertNotNull(allCachedDataPostRemoval.containsKey(KEY_ONE));
		Assert.assertNotNull(allCachedDataPostRemoval.containsValue(VALUE_ONE));
		Assert.assertFalse(allCachedDataPostRemoval.containsKey(KEY_ONE));
		Assert.assertFalse(allCachedDataPostRemoval.containsValue(VALUE_ONE));
	}

	@Test
	public final void removeAll() {
		Cache<Object,Object> cache = cacheManager.getCache(CACHE_NAME);
		cache.put(KEY_ONE, VALUE_ONE);
		cache.put(KEY_TWO, VALUE_TWO);
		
		Map<? extends Object,? extends Object> allCachedData = cache.getAll();
		Assert.assertNotNull(allCachedData);
		Assert.assertNotNull(allCachedData.size());
		Assert.assertNotNull(allCachedData.size() == 1);
		Assert.assertNotNull(allCachedData.containsKey(KEY_ONE));
		Assert.assertNotNull(allCachedData.containsValue(VALUE_ONE));
		Assert.assertTrue(allCachedData.containsKey(KEY_ONE));
		Assert.assertTrue(allCachedData.containsKey(KEY_TWO));
		Assert.assertTrue(allCachedData.containsValue(VALUE_ONE));
		Assert.assertTrue(allCachedData.containsValue(VALUE_TWO));

		cache.remove(KEY_ONE);
		cache.remove(KEY_TWO);
		Map<? extends Object,? extends Object> allCachedDataPostRemoval = cache.getAll();
		Assert.assertNotNull(allCachedDataPostRemoval);
		Assert.assertNotNull(allCachedDataPostRemoval.size());
		Assert.assertNotNull(allCachedDataPostRemoval.size() == 0);
		Assert.assertNotNull(allCachedDataPostRemoval.containsKey(KEY_ONE));
		Assert.assertNotNull(allCachedDataPostRemoval.containsValue(VALUE_ONE));
		Assert.assertNotNull(allCachedDataPostRemoval.containsKey(KEY_TWO));
		Assert.assertNotNull(allCachedDataPostRemoval.containsValue(VALUE_TWO));
		Assert.assertFalse(allCachedDataPostRemoval.containsKey(KEY_ONE));
		Assert.assertFalse(allCachedDataPostRemoval.containsKey(KEY_TWO));
		Assert.assertFalse(allCachedDataPostRemoval.containsValue(VALUE_ONE));
		Assert.assertFalse(allCachedDataPostRemoval.containsValue(VALUE_TWO));
	}

	@Test
	public final void getCacheImpl() {
		Cache<Object,Object> cache = cacheManager.getCache(CACHE_NAME);
		Assert.assertTrue(cache.getCacheImpl() instanceof org.infinispan.Cache); 
	}
}