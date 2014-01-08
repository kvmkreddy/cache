package org.os.javaee.cache.impl.ehcache;

import java.util.Collection;
import java.util.Map;
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
public class EHCacheImpl implements Cache<Object, Element> {

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
    public Element get(final Object key) {
	return ehCache.getQuiet(key);
    }

    @Override
    public Map<? extends Object, ? extends Element> getAll(
	    final Set<? extends Object> keys) {
	return ehCache.getAll(keys);
    }

    @Override
    public Map<? extends Object, ? extends Element> getAll() {
	return ehCache.getAll(ehCache.getKeys());
    }

    @Override
    public void put(final Object key, final Element value) {
	ehCache.putQuiet(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void putAll(final Map<? extends Object, ? extends Element> map) {
	ehCache.putAll((Collection<Element>) map.values());
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
    public boolean putIfAbsent(final Object key, final Element value) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean remove(final Object key, final Element oldValue) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean replace(final Object key, final Element oldValue,
	    final Element newValue) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean replace(final Object key, final Element value) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public <T> T getCacheImpl() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Element getAndPut(final Object key, final Element value) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Element getAndRemove(final Object key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Element getAndReplace(final Object key, final Element value) {
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