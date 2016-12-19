/**
 * @Title: SimpleContext.java
 * @Description:
 * @Date:2016年12月12日 下午1:57:18
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cmsz.autoflow.engine.core.Context;
import cmsz.autoflow.engine.helper.ClassHelper;

/**
 * @ClassName:cmsz.autoflow.engine.core.impl.SimpleContext
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class SimpleContext implements Context {

	private Map<String, Object> servicePool = new HashMap<String, Object>();

	/**
	 * @Title: put
	 * @Description:
	 * @param name
	 * @param obj
	 * @Date:2016年12月12日 下午1:58:27
	 * @Author:LeucotheaShi
	 */
	@Override
	public void put(String name, Object obj) {
		// TODO Auto-generated method stub
		servicePool.put(name, obj);
	}

	/**
	 * @Title: put
	 * @Description:
	 * @param name
	 * @param clazz
	 * @Date:2016年12月12日 下午1:58:27
	 * @Author:LeucotheaShi
	 */
	@Override
	public void put(String name, Class<?> clazz) {
		// TODO Auto-generated method stub

		servicePool.put(name, ClassHelper.instantiate(clazz));

	}

	/**
	 * @Title: exist
	 * @Description:
	 * @param name
	 * @return
	 * @Date:2016年12月12日 下午1:58:27
	 * @Author:LeucotheaShi
	 */
	@Override
	public boolean exist(String name) {
		// TODO Auto-generated method stub
		return (servicePool.get(name) != null);
	}

	/**
	 * @Title: find
	 * @Description:
	 * @param clazz
	 * @return
	 * @Date:2016年12月12日 下午1:58:27
	 * @Author:LeucotheaShi
	 */
	@Override
	public <T> T find(Class<T> clazz) {
		// TODO Auto-generated method stub

		for (Entry<String, Object> entry : servicePool.entrySet()) {

			if (clazz.isInstance(entry.getValue())) {
				return clazz.cast(entry.getValue());
			}

		} // for

		return null;
	}

	/**
	 * @Title: findList
	 * @Description:
	 * @param clazz
	 * @return
	 * @Date:2016年12月12日 下午1:58:27
	 * @Author:LeucotheaShi
	 */
	@Override
	public <T> List<T> findList(Class<T> clazz) {
		// TODO Auto-generated method stub
		List<T> serviceList = new ArrayList<T>();

		for (Entry<String, Object> entry : servicePool.entrySet()) {

			if (clazz.isInstance(entry.getValue())) {
				serviceList.add(clazz.cast(entry.getValue()));
			}

		} // for

		return serviceList;
	}

	/**
	 * @Title: findByName
	 * @Description:
	 * @param name
	 * @param clazz
	 * @return
	 * @Date:2016年12月12日 下午1:58:27
	 * @Author:LeucotheaShi
	 */
	@Override
	public <T> T findByName(String name, Class<T> clazz) {
		// TODO Auto-generated method stub

		for (Entry<String, Object> entry : servicePool.entrySet()) {
			if (entry.getKey().equals(name) && clazz.isInstance(entry.getValue())) {
				return clazz.cast(entry.getValue());
			} // if
		} // for

		return null;
	}

	/**
	 * @return the servicePool
	 */
	public Map<String, Object> getServicePool() {
		return servicePool;
	}

	/**
	 * @param servicePool
	 *            the servicePool to set
	 */
	public void setServicePool(Map<String, Object> servicePool) {
		this.servicePool = servicePool;
	}

}// SimpleContext
