package org.os.javaee.cache;

import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.os.javaee.cache.impl.ehcache.EHCacheImpl;
import org.os.javaee.cache.impl.ehcache.EHCacheManagerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: EHCacheImplTest</p>
 * <p><b>Description:</b> EHCacheImplTest</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class EHCacheImplTest{

	private static final String KEY_TWO = "Murali";
	private static final String VALUE_TWO = "K V M Krishna Reddy";
	
	private static final String KEY_ONE = "SOME KEY ONE";
	private static final String VALUE_ONE = "SOME VALUE FOR KEY ONE";
	
	private static final String CACHE_NAME="MyCache";
	
	private static CacheConfiguration cacheConfiguration;
	private static EHCacheManagerImpl cacheManager;
	
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
	public void crdCache(){
		EHCacheImpl cache = (EHCacheImpl)cacheManager.createCache(CACHE_NAME, cacheConfiguration);
		Assert.assertNotNull(cache);
		
		cache.put(KEY_ONE, new Element(KEY_ONE,VALUE_ONE));
		cache.put(KEY_TWO, new Element(KEY_TWO,VALUE_TWO));
		
		Assert.assertNotNull(cache.containsKey(KEY_ONE));
		Assert.assertNotNull(cache.containsKey(KEY_TWO));
		
		Assert.assertTrue(cache.containsKey(KEY_ONE));
		Assert.assertTrue(cache.containsKey(KEY_TWO));
		
		Assert.assertNotNull(cache.get(KEY_ONE));
		Assert.assertNotNull(cache.get(KEY_TWO));
		
		Assert.assertSame(((Element)cache.get(KEY_ONE)).getObjectValue().toString(),VALUE_ONE);
		Assert.assertSame(((Element)cache.get(KEY_TWO)).getObjectValue().toString(),VALUE_TWO);
		
		Assert.assertTrue(cache.remove(KEY_ONE));
		Assert.assertTrue(cache.remove(KEY_TWO));
	}	
}