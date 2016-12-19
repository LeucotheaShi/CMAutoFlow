package com.cmsz.cmup.commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring 工具类
 * 
 * @author hule, wuhang
 *
 */
@Component
public class SpringUtil implements ApplicationContextAware {

	/**
	 * spring上下文
	 */
	private ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		context = contex;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public Object getBean(String name) {
		return context.getBean(name);
	}
	
	public <T> T getBean(String name, Class<T> clazz){
		return context.getBean(name, clazz);
	}
	
}