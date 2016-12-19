/**
 * @Title: Context.java
 * @Description:
 * @Date:2016年12月12日 下午1:54:36
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.core;

import java.util.List;

/**
 * @ClassName:cmsz.autoflow.engine.core.Context
 * @Description: 用以实现服务可配置化，Context相当于服务池
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public interface Context {

	/**
	 * 根据服务名称、实例向服务工厂注册
	 * 
	 * @param name
	 * @param obj
	 */
	void put(String name, Object obj);

	/**
	 * 根据服务名称、类型向服务工厂注册
	 * 
	 * @param name
	 * @param clazz
	 */
	void put(String name, Class<?> clazz);

	/**
	 * 根据名字判断 服务是否存在
	 * 
	 * @param name
	 * @return
	 */
	boolean exist(String name);

	/**
	 * 根据类型查找服务实例
	 * 
	 * @param clazz
	 * @return
	 */
	<T> T find(Class<T> clazz);

	<T> List<T> findList(Class<T> clazz);

	/**
	 * 根据给定的服务名称、类型查找服务实例
	 * 
	 * @param name
	 * @param clazz
	 * @return
	 */

	<T> T findByName(String name, Class<T> clazz);

}
