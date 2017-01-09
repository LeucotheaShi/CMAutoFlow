/**
 * @Title: LocalServiceImpl.java
 * @Description:
 * @Date:2016年12月23日 下午8:43:13
 * @Author:LeucotheaShi
 */
package com.cmsz.cmup.dispatcher.service;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cmsz.cmup.commons.logging.alarm.AlarmLogHandler;
import com.cmsz.cmup.commons.logging.system.SystemLogHandler;
import com.cmsz.cmup.commons.utils.SpringUtil;
import com.cmsz.cmup.frame.base.service.BaseInterface;
import com.cmsz.cmup.frame.constant.FrameConstant;
import com.cmsz.cmup.frame.constant.Result;
import com.cmsz.cmup.frame.dubbo.service.LauncherService;
import com.cmsz.cmup.frame.model.ReturnResult;

/**
 * @ClassName:com.cmsz.cmup.frame.dubbo.service.LocalServiceImpl
 * @Description: TODO
 * @Date: 2016年12月23日
 * @Author: LeucotheaShi
 */
@Component("LocalServiceImpl")
public class LocalServiceImpl implements LauncherService {

	private static SystemLogHandler sysLogger = SystemLogHandler.getLogger(LocalServiceImpl.class);
	private static AlarmLogHandler alarmLogger = AlarmLogHandler.getLogger(LocalServiceImpl.class);

	@Autowired
	@Qualifier("springUtil")
	private SpringUtil springUtil;

	/**
	 * @Title: doLocalService
	 * @Description:
	 * @param variableMap
	 * @return
	 * @Date:2016年12月23日 下午8:43:13
	 * @Author:LeucotheaShi
	 */
	@Override
	public ReturnResult doService(Map<String, Object> variableMap) throws Exception {
		sysLogger.debug("执行doLocalService方法");
		if (variableMap == null || variableMap.isEmpty()) {
			String message = "执行doLocalService方法出错，variableMap不能为空!";
			sysLogger.error(message);
			return new ReturnResult(Result.ERROR, message);
		}
		// 取到服务名
		String serviceName = variableMap.get(FrameConstant.SERVICENAME).toString();
		if (StringUtils.isEmpty(serviceName)) {
			String message = "执行框架的doLocalService方法出错，从variableMap中取到的serviceName为空!";
			alarmLogger.error(message, variableMap);
			sysLogger.error(message, variableMap);
			return new ReturnResult(Result.ERROR, message);
		}
		ReturnResult returnResult = null;
		try {

			// 从spring容器中取到服务组件
			BaseInterface baseInterface = (BaseInterface) springUtil.getBean(serviceName);
			// 执行服务组件的doService方法
			returnResult = baseInterface.doService(variableMap);
		} catch (NoSuchBeanDefinitionException e) {
			StringBuilder messageBuilder = new StringBuilder("执行框架的doLocalService方法出错，Spring容器中没有查找到id为[");
			messageBuilder.append(serviceName);
			messageBuilder.append("]的bean!");
			String message = messageBuilder.toString();
			returnResult = new ReturnResult(Result.ERROR, message);
			alarmLogger.error(message, variableMap, e);
			sysLogger.error(message, variableMap, e);

			// 抛出异常，在上层处理
			throw e;
		} catch (ClassCastException e) {
			StringBuilder messageBuilder = new StringBuilder("执行框架的doLocalService方法出错，转换bean为BaseInterface类型时出错，bean[");
			messageBuilder.append(serviceName);
			messageBuilder.append("]没有实现接口com.cmsz.cmup.frame.base.service.BaseInterface");
			String message = messageBuilder.toString();
			returnResult = new ReturnResult(Result.ERROR, message);
			alarmLogger.error(message, variableMap, e);
			sysLogger.error(message, variableMap, e);
			// 抛出异常，在上层处理
			throw e;
		} catch (Exception e) {
			String message = "执行组件的doService方法时捕获到异常:" + e.getMessage();
			returnResult = new ReturnResult(Result.ERROR, message);
			alarmLogger.error(message, variableMap, e);
			sysLogger.error(message, variableMap, e);
			// 抛出异常，在上层处理
			throw e;
		}
		sysLogger.debug("执行doLocalService完毕");
		return returnResult;
	}

}
