package org.os.javaee.cache.impl.ehcache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.ehcache.Element;

import org.os.javaee.cache.core.Cache;

/**
 * <p>
 * Title: EHCacheImpl
 * </p>
 * <p>
 * <b>Description:</b> EHCacheImpl
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
public class EHCacheImpl implements Cache<Object, Object> {

    private net.sf.ehcache.Ehcache ehCache = null;

    public EHCacheImpl(
	    final net.sf.ehcache.config.CacheConfiguration configuration) {
	ehCache = new net.sf.ehcache.Cache(configuration);
    }

    public EHCacheImpl(final net.sf.ehcache.Ehcache ehCache) {
	this.ehCache = ehCache;
    }

    @Override
    public boolean containsKey(final Object key) {
	return ehCache.isKeyInCache(key);
    }

    @Override
    public Object get(final Object key) {
	return ehCache.getQuiet(key);
    }

    @Override
    public Map<? extends Object, ? extends Object> getAll(
	    final Set<? extends Object> keys) {
	return ehCache.getAll(keys);
    }

    @Override
    public Map<? extends Object, ? extends Object> getAll() {
	return ehCache.getAll(ehCache.getKeys());
    }

    @Override
    public void put(final Object key, final Object value) {
	ehCache.putQuiet(new Element(key, value));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void putAll(final Map<? extends Object, ? extends Object> map) {
	@SuppressWarnings("unused")
	final List<Element> elementList = new ArrayList<>();
	for (final Iterator<?> iter = map.entrySet().iterator(); iter.hasNext();) {
	    @SuppressWarnings("rawtypes")
	    final Entry entry = (Entry) iter.next();
	    elementList.add(new Element(entry.getKey(), entry.getValue()));
	}
	ehCache.putAll(elementList);
    }

    @Override
    public boolean remove(final Object key) {
	return ehCache.remove(key);
    }

    @Override
    public void removeAll(final Collection<? extends Object> collection) {
	ehCache.removeAll(collection);
    }

    @Override
    public void removeAll() {
	ehCache.removeAll();
    }

    @SuppressWarnings("unchecked")
    @Override
    public net.sf.ehcache.Ehcache getCacheImpl() {
	return ehCache;
    }

    @Override
    public boolean putIfAbsent(final Object key, final Object value) {
	synchronized (ehCache) {
	    if (!ehCache.isKeyInCache(key)) {
		ehCache.putIfAbsent(new Element(key, value), true);
		return true;
	    }
	}
	return false;
    }

    @Override
    public boolean remove(final Object key, final Object oldValue) {
	synchronized (ehCache) {
	    if (ehCache.isKeyInCache(key) && ehCache.get(key).equals(oldValue)) {
		ehCache.remove(key);
		return true;
	    }
	}
	return false;
    }

    @Override
    public boolean replace(final Object key, final Object oldValue,
	    final Object newValue) {
	synchronized (ehCache) {
	    if (ehCache.isKeyInCache(key) && ehCache.get(key).equals(oldValue)) {
		ehCache.put(new Element(key, newValue));
		return true;
	    }
	}
	return false;
    }

    @Override
    public boolean replace(final Object key, final Object value) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Element getAndPut(final Object key, final Object value) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Element getAndRemove(final Object key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Element getAndReplace(final Object key, final Object value) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void close() {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean isClosed() {
	// TODO Auto-generated method stub
	return false;
    }
}