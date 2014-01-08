package org.os.javaee.cache.core;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Title: Cache
 * </p>
 * <p>
 * <b>Description:</b> Cache
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
public interface Cache<K, V> {

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
     * @param value
     * @return
     */
    public boolean putIfAbsent(K key, V value);

    /**
     * @param key
     * @return
     */
    public boolean remove(K key);

    /**
     * @param key
     * @param oldValue
     * @return
     */
    boolean remove(K key, V oldValue);

    /**
     * @param map
     * @return
     */
    public void removeAll(Collection<? extends K> collection);

    /**
     * @return
     */
    public void removeAll();

    /**
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     */
    boolean replace(K key, V oldValue, V newValue);

    /**
     * @param key
     * @param value
     * @return
     */
    boolean replace(K key, V value);

    /**
     * @param clazz
     * @return
     */
    public <T> T getCacheImpl();

    /**
     * @param key
     * @param value
     * @return
     */
    public V getAndPut(K key, V value);

    /**
     * @param key
     * @return
     */
    V getAndRemove(K key);

    /**
     * @param key
     * @param value
     * @return
     */
    V getAndReplace(K key, V value);

    /**
     * 
     */
    void close();

    /**
     * @return
     */
    boolean isClosed();
}
