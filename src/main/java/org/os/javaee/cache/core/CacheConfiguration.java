package org.os.javaee.cache.core;

/**
 * <p>Title: CacheConfiguration</p>
 * <p><b>Description:</b> CacheConfiguration</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Open Source Development.</p>
 * @author Murali Reddy
 * @version 1.0
 */
@Deprecated
public interface CacheConfiguration<T> {

	/**
	 * @param configKey
	 * @return
	 */
	public Object getConfigValue(String configKey);
	/**
	 * @param configKey
	 * @param configValue
	 */
	public void setConfigValue(String configKey, Object configValue);
	/**
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> T unwrap(java.lang.Class<T> clazz);
}
