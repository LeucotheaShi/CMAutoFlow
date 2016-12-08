/**
 * 
 */
package com.cmsz.cmup.commons.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.cmsz.cmup.commons.logging.system.SystemLogHandler;


/**
 * @author liaoruiyi 用于解密配置文件中jdbc相关参数的实现类
 */
public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private static SystemLogHandler systemLogHandler = SystemLogHandler.getLogger(EncryptablePropertyPlaceholderConfigurer.class);
	private String key = "0002000200020002";
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
	        throws BeansException {
		try {
			
			// 配置文件中是否加密的字段
			String isEncry = props.getProperty("isEncry.isEncryable");
			boolean isEncryOpen = true;
			// 参数校验
			if (StringUtils.isBlank(isEncry) || !Boolean.TRUE.equals(Boolean.valueOf(isEncry))){
				//super.processProperties(beanFactory, props);
				isEncryOpen = false;
				//return;//加密开关未打开，则直接返回，不用处理
			}
			
			// 如果有配置key值，则使用配置的key
			if (StringUtils.isNotBlank(props.getProperty("security_key")))
				key = props.getProperty("security_key");

			Map<String, String> propertiesValueMap = new HashMap<>();//properties工具类初始化
			// 处理需要解密的字段
			for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
				String name = (String)e.nextElement();
				if (name.endsWith(".encrypt")&&isEncryOpen) {
					// 重新覆盖加密后的相关数据
					String decrypt = props.getProperty(name);
					decrypt = Des.Decrypt(decrypt, Des.hex2byte(key));
					props.setProperty(name,decrypt);
					propertiesValueMap.put(name, decrypt);
				}else {
					propertiesValueMap.put(name, props.getProperty(name));
				}
			}
			PropertiesUtil.propertiesMap = propertiesValueMap;//properties工具类Map初始化
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			systemLogHandler.error("初始化密码解密类EncryptablePropertyPlaceholderConfigurer时发生异常",e);
			throw e;
		}
	}
}