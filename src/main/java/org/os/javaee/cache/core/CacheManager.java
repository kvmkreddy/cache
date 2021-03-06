package org.os.javaee.cache.core;

/**
 * <p>Title: CacheManager</p>
 * <p><b>Description:</b> CacheManager</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public interface CacheManager<T>{

    /**
     * @param cacheName
     * @param configuration
     * @return
     */
    <K, V> Cache<K, V> createCache(String cacheName,T configuration);
    /**
     * @param cacheName
     */
    public void destroyCache(String cacheName);

    /**
     * @param cacheName
     * @return
     */
    public <K, V> Cache<K, V> getCache(String cacheName);
    
    /**
     * @return
     */
    Iterable<String> getCacheNames();
}
