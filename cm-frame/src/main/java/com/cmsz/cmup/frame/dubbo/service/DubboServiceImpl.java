package com.cmsz.cmup.frame.dubbo.service;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cmsz.cmup.commons.logging.alarm.AlarmLogHandler;
import com.cmsz.cmup.commons.logging.system.SystemLogHandler;
import com.cmsz.cmup.commons.utils.SpringUtil;
import com.cmsz.cmup.frame.base.service.BaseInterface;
import com.cmsz.cmup.frame.constant.FrameConstant;
import com.cmsz.cmup.frame.constant.Result;
import com.cmsz.cmup.frame.model.ReturnResult;

/**
 * 子系统对外dubbo接口通用实现类 所有子系统对外提供的dubbo接口实现类需要继承此类，不需要自己添加和修改任何方法，
 * 只需实现自己的dubuo接口（同样不需要写任何代码），参考DubboService
 * 
 * @see com.cmsz.cmup.frame.dubbo.service.DubboService
 * @author JinChao
 * 
 * @date 2015年11月11日 上午10:03:31
 *
 */
public class DubboServiceImpl implements DubboService {
	private static SystemLogHandler sysLogger = SystemLogHandler.getLogger(DubboServiceImpl.class);
	private static AlarmLogHandler alarmLogger = AlarmLogHandler.getLogger(DubboServiceImpl.class);

	@Autowired
	@Qualifier("springUtil")
	private SpringUtil springUtil;

	/**
	 * 执行dubbo服务
	 * 
	 * @see com.cmsz.cmup.frame.dubbo.service.BaseDubboService#doDubboService(java.util.Map)
	 */
	@Override
	public ReturnResult doDubboService(Map<String, Object> variableMap) {
		sysLogger.debug("执行doDubboService方法");
		if (variableMap == null || variableMap.isEmpty()) {
			String message = "执行doDubboService方法出错，variableMap不能为空!";
			sysLogger.error(message);
			return new ReturnResult(Result.ERROR, message);
		}
		// 取到服务名
		String serviceName = variableMap.get(FrameConstant.SERVICENAME).toString();
		if (StringUtils.isEmpty(serviceName)) {
			String message = "执行框架的doDubboService方法出错，从variableMap中取到的serviceName为空!";
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
			StringBuilder messageBuilder = new StringBuilder("执行框架的doDubboService方法出错，Spring容器中没有查找到id为[");
			messageBuilder.append(serviceName);
			messageBuilder.append("]的bean!");
			String message = messageBuilder.toString();
			returnResult = new ReturnResult(Result.ERROR, message);
			alarmLogger.error(message, variableMap, e);
			sysLogger.error(message, variableMap, e);
		} catch (ClassCastException e) {
			StringBuilder messageBuilder = new StringBuilder("执行框架的doDubboService方法出错，转换bean为BaseInterface类型时出错，bean[");
			messageBuilder.append(serviceName);
			messageBuilder.append("]没有实现接口com.cmsz.cmup.frame.base.service.BaseInterface");
			String message = messageBuilder.toString();
			returnResult = new ReturnResult(Result.ERROR, message);
			alarmLogger.error(message, variableMap, e);
			sysLogger.error(message, variableMap, e);
		} catch (Exception e) {
			String message = "执行组件的doService方法时捕获到异常:" + e.getMessage();
			returnResult = new ReturnResult(Result.ERROR, message);
			alarmLogger.error(message, variableMap, e);
			sysLogger.error(message, variableMap, e);
		}
		sysLogger.debug("执行doDubboService完毕");
		return returnResult;
	}

}
