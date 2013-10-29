
package com.wy.shopping.http.serverce;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Hashtable;
import java.util.Map;

import com.wy.shopping.http.HttpCenter;


public class ServiceFactory {
	
	/**
	 * service实例缓存
	 */
	private static final Map<Class<?>,Object> services = new Hashtable<Class<?>,Object>();

	/**
	 * 取得Service
	 * 
	 * @param <T>
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> cls) {
		Object service = services.get(cls);
		if( service == null ){
			InvocationHandler handler = new ServiceInvocationHandler(HttpCenter.getInstance());
			service = Proxy.newProxyInstance(cls.getClassLoader(), new Class[] { cls }, handler);
			services.put(cls, service);
		}
		return (T) service;
	}
}
