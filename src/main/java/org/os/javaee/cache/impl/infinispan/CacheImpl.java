package org.os.javaee.cache.impl.infinispan;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title: CacheImpl</p>
 * <p><b>Description:</b> CacheImpl</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
public class CacheImpl implements org.os.javaee.cache.core.Cache<Object, Object>{

	private org.infinispan.Cache<Object,Object> cache = null;

	public CacheImpl(org.infinispan.Cache<Object,Object> _cache){
		this.cache = _cache;
	}
	
	public CacheImpl(String name){
		this.cache = new org.infinispan.CacheImpl<Object,Object>(name);
	}
	
	@Override
	public boolean containsKey(Object key) {
		return cache.containsKey(key);
	}

	@Override
	public Object get(Object key) {
		return cache.get(key);
	}

	@Override
	public Map<? extends Object, ? extends Object> getAll(Set<? extends Object> keys) {
		//TODO Needs to improve below code
		Map<Object,Object> returnMap = new HashMap<Object,Object>();
		if(keys != null && keys.size()>0){
			for(Object key:keys){
				if(cache.containsKey(key)){
					returnMap.put(key, cache.get(key));
				}
			}
		}
		return returnMap;
	}

	@Override
	public Map<? extends Object, ? extends Object> getAll() {
		//Here we are returning new Map which contians all the cached data.
		return new HashMap<Object,Object>(getCacheImpl());
	}

	@Override
	public void put(Object key, Object value) {
		cache.put(key,value);
	}

	@Override
	public void putAll(Map<? extends Object, ? extends Object> map) {
		cache.putAll(map);
	}

	@Override
	public boolean remove(Object key) {
		//Here assumption is, the cache does not hold the NULL values.
		if(key != null){
			return (cache.remove(key) != null)? true:false;
		}else{
			return false;
		}
	}

	@Override
	public void removeAll(Collection<? extends Object> collection) {
		for(Object key:collection){
			cache.remove(key);
		}
	}

	@Override
	public void removeAll() {
		cache.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public org.infinispan.Cache<Object,Object> getCacheImpl() {
		return cache;
	}
}