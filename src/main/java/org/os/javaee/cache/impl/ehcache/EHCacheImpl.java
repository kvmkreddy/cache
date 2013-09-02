package org.os.javaee.cache.impl.ehcache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.Element;

import org.os.javaee.cache.core.Cache;

/**
 * <p>Title: EHCacheImpl</p>
 * <p><b>Description:</b> EHCacheImpl</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class EHCacheImpl implements Cache<Object, Element> {

	private net.sf.ehcache.Ehcache ehCache = null;
	
	public EHCacheImpl(net.sf.ehcache.config.CacheConfiguration configuration){
		ehCache = new net.sf.ehcache.Cache(configuration); 
	}
	
	public EHCacheImpl(net.sf.ehcache.Ehcache ehCache){
		this.ehCache = ehCache;
	}
	
	@Override
	public boolean containsKey(Object key) {
		return ehCache.isKeyInCache(key);
	}

	@Override
	public Element get(Object key) {
		return ehCache.getQuiet(key);
	}

	@Override
	public Map<? extends Object, ? extends Element> getAll(Set<? extends Object> keys) {
		return ehCache.getAll(keys);
	}

	@Override
	public Map<? extends Object, ? extends Element> getAll() {
		return ehCache.getAll(ehCache.getKeys());
	}

	@Override
	public void put(Object key, Element value) {
		ehCache.putQuiet(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putAll(Map<? extends Object, ? extends Element> map) {
		ehCache.putAll((Collection<Element>)map.values());
	}

	@Override
	public boolean remove(Object key) {
		return ehCache.remove(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeAll(Map<? extends Object, ? extends Element> map) {
		ehCache.removeAll((Collection<Element>)map.values());
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
}