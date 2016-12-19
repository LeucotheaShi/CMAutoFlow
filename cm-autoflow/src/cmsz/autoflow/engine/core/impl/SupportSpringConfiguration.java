/**
 * @Title: SupportSpringConfiguration.java
 * @Description:
 * @Date:2016年12月12日 下午3:56:34
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.core.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cmsz.autoflow.engine.core.Configuration;
import cmsz.autoflow.engine.core.ServiceContext;

/**
 * @ClassName:cmsz.autoflow.engine.core.impl.SupportSpringConfiguration
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class SupportSpringConfiguration extends Configuration implements ApplicationContextAware {

	// spring的applicationContext，没用上
	private ApplicationContext applicationContext;
	// 用户自定义的context，用以支持spring
	private SupportSpringContext context;

	public SupportSpringConfiguration() {
		super(null);
		// 实例化SupportSpringContext，并放置到属性中
		this.context = new SupportSpringContext();// 空构造函数
		// 把该context设置到ServiceContext中
		ServiceContext.setContext(this.context);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {

		this.context.setApplicationContext(arg0);
	}

}