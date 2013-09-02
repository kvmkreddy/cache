package org.os.javaee.cache.core;

import java.util.Map;
import java.util.Set;

/**
 * <p>Title: Cache</p>
 * <p><b>Description:</b> Cache</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0 
 */
public interface Cache<K,V> {

	/**
	 * @param key
	 * @return
	 */
	public boolean containsKey(K key);
	
	/**
	 * Returns the value for the provided key
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key);
	
	/**
	 * @return
	 */
	public Map<? extends K, ? extends V> getAll(Set<? extends K> keys);
	
	/**
	 * @return
	 */
	public Map<? extends K, ? extends V> getAll();
	/**
	 * @param key
	 * @param value
	 */
	public void put(K key, V value);
	
	/**
	 * @param map
	 */
	public void putAll(Map<? extends K, ? extends V> map);
	/**
	 * @param key
	 * @return
	 */
	public boolean remove(K key);
	
	/**
	 * @param map
	 * @return
	 */
	public void removeAll(java.util.Map<? extends K, ? extends V> map);

	/**
	 * @return
	 */
	public void removeAll();
	
	/**
	 * @param clazz
	 * @return
	 */
	public <T> T getCacheImpl();
}
