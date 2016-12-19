/**
 * 
 */
package com.cmsz.cmup.commons.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.cmsz.cmup.commons.logging.system.SystemLogHandler;

/**
 * @author liaoruiyi 用于解密配置文件中jdbc相关参数的实现类
 */
public class PropertyFileConfigurer extends PropertyPlaceholderConfigurer {
		
	private static SystemLogHandler systemLogHandler = SystemLogHandler.getLogger(PropertyFileConfigurer.class);
	@Override
	@SuppressWarnings("unchecked")
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		try {
			Map<String, String> propertiesValueMap = new HashMap<>();
			for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
				String name = (String)e.nextElement();
				propertiesValueMap.put(name, props.getProperty(name));
			}
			PropertiesUtil.propertiesMap = propertiesValueMap;
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			systemLogHandler.warn("处理properties文件时发生异常"+e.getMessage());
			throw new BeanInitializationException(e.getMessage());
		}
	}
}