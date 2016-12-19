/**
 * @Title: ServiceContext.java
 * @Description:
 * @Date:2016年12月12日 下午2:29:51
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.core;

import java.util.List;

/**
 * @ClassName:cmsz.autoflow.engine.core.ServiceContext
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public abstract class ServiceContext {

	/**
	 * 上下文服务接口
	 */
	private static Context context;

	/**
	 * 流程引擎的引用
	 */
	private static AutoEngine engine;

	/**
	 * @return the context
	 */
	public static Context getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public static void setContext(Context context) {
		ServiceContext.context = context;
	}

	/**
	 * @return the engine
	 */
	public static AutoEngine getEngine() {
		if (engine == null) {
			engine = context.find(AutoEngine.class);
		}

		return engine;
	}

	/**
	 * @param engine
	 *            the engine to set
	 */
	public static void setEngine(AutoEngine engine) {
		ServiceContext.engine = engine;
	}

	/**
	 * 向上下文添加服务实例
	 * 
	 * @param name
	 * @param obj
	 */

	public static void put(String name, Object obj) {
		context.put(name, obj);
	}

	/**
	 * 向上下文添加服务实例
	 * 
	 * @param name
	 * @param clazz
	 */
	public static void put(String name, Class<?> clazz) {
		context.put(name, clazz);
	}

	/**
	 * 根据服务名称判断是否存在服务实例
	 * 
	 * @param name
	 *            服务名称
	 * @return
	 */
	public static boolean exist(String name) {
		return context.exist(name);
	}

	/**
	 * 对外部提供的查找对象方法，根据class类型查找
	 * 
	 * @param clazz
	 *            服务类型
	 * @return
	 */
	public static <T> T find(Class<T> clazz) {
		return context.find(clazz);
	}

	/**
	 * 对外部提供的查找对象实例列表方法，根据class类型查找集合
	 * 
	 * @param clazz
	 *            服务类型
	 * @return
	 */
	public static <T> List<T> findList(Class<T> clazz) {
		return context.findList(clazz);
	}

	/**
	 * 对外部提供的查找对象方法，根据名称、class类型查找
	 * 
	 * @param name
	 *            服务名称
	 * @param clazz
	 *            服务类型
	 * @return
	 */
	public static <T> T findByName(String name, Class<T> clazz) {
		return context.findByName(name, clazz);
	}

}
