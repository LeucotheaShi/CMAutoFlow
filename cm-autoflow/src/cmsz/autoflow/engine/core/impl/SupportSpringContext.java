/**
 * @Title: SupportSpringContext.java
 * @Description:
 * @Date:2016年12月12日 下午2:28:12
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.core.impl;

import org.springframework.context.ApplicationContext;

import cmsz.autoflow.engine.core.Context;

/**
 * @ClassName:cmsz.autoflow.engine.core.impl.SupportSpringContext
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class SupportSpringContext extends SimpleContext implements Context {

	private ApplicationContext applicationContext;

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public SupportSpringContext() {
		super();
	}

	public <T> T findByName(String name, Class<T> clazz) {
		T obj = super.findByName(name, clazz);
		if (obj != null)
			return obj;
		else if (applicationContext != null) {
			return applicationContext.getBean(name, clazz);
		} else
			return null;
	}

	public <T> T find(Class<T> clazz) {
		T obj = super.find(clazz);
		if (obj != null)
			return obj;
		else if (applicationContext != null) {
			return applicationContext.getBean(clazz);
		} else
			return null;
	}

}// SupportSpringContext
