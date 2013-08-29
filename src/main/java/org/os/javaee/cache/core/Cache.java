package org.os.javaee.cache.core;

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
	 * 
	 */
	public void clear();
	
	/**
	 * @param key
	 * @return
	 */
	public boolean containsKey(K key);
	
	/**
	 * @param key
	 * @return
	 */
	public V get(K key);
	
	/**
	 * @param key
	 * @param value
	 */
	public void put(K key, V value);
	
	/**
	 * @param key
	 * @return
	 */
	public boolean remove(K key);
}
