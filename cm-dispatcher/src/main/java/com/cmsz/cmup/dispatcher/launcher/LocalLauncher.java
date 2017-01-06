/**
 * @Title: LocalLauncher.java
 * @Description:
 * @Date:2016年12月23日 下午8:13:05
 * @Author:LeucotheaShi
 */
package com.cmsz.cmup.dispatcher.launcher;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.RpcException;
import com.cmsz.cmup.commons.logging.alarm.AlarmLogHandler;
import com.cmsz.cmup.commons.logging.system.SystemLogHandler;
import com.cmsz.cmup.commons.utils.SpringUtil;
import com.cmsz.cmup.frame.constant.FrameConstant;
import com.cmsz.cmup.frame.constant.Result;
import com.cmsz.cmup.frame.dubbo.service.LauncherService;
import com.cmsz.cmup.frame.model.ReturnResult;

import cmsz.autoflow.engine.constant.Constant;
import cmsz.autoflow.engine.core.DelegateTask;
import cmsz.autoflow.engine.core.Launcher;
import cmsz.autoflow.engine.helper.JsonHelper;

/**
 * @ClassName:com.cmsz.cmup.dispatcher.launcher.LocalLauncher
 * @Description: TODO
 * @Date: 2016年12月23日
 * @Author: LeucotheaShi
 */
@Component("LocalLauncher")
public class LocalLauncher implements Launcher {

	private static AlarmLogHandler alarmLogger = AlarmLogHandler.getLogger(LocalLauncher.class);
	private static SystemLogHandler sysLogger = SystemLogHandler.getLogger(LocalLauncher.class);

	@Autowired
	@Qualifier("springUtil")
	private SpringUtil springUtil;

	@Autowired
	@Qualifier("LocalServiceImpl")
	private LauncherService launcherService;

	/**
	 * @Title: launch
	 * @Description:
	 * @param dtask
	 * @return
	 * @Date:2016年12月23日 下午8:13:05
	 * @Author:LeucotheaShi
	 */
	@Override
	public String launch(DelegateTask dtask) {
		String launcherServiceId = dtask.getRefBean();
		if (StringUtils.isEmpty(launcherServiceId)) {
			String message = "默认LocalLauncher执行launch方法时出错，DelegateTask的launcherServiceId字段不能为空！";
			alarmLogger.error(message);
			sysLogger.error(message);
			return Constant.State.ERROR;
		}
		ReturnResult ret = null;
		try {

			// LauncherService launcherService = (LauncherService)
			// springUtil.getBean(launcherServiceId);
			Map<String, Object> varMap = JsonHelper.fromJson(dtask.getVariables(), Map.class);
			Map<String, Object> comMap = (Map<String, Object>) varMap.get("Common");
			varMap.remove("Common");
			for (String key : varMap.keySet()) {
				comMap.put(key, (String) varMap.get(key));
			}
			comMap.put(FrameConstant.SERVICENAME, dtask.getComponentId());

			// 增加 flowId, flowName, taskId, taskName 参数
			comMap.put("flowId", dtask.getFlowId());
			comMap.put("flowName", dtask.getFlowName());
			comMap.put("taskId", dtask.getId());
			comMap.put("taskName", dtask.getName());
			try {
				ret = launcherService.doService(comMap);
			} catch (NullPointerException e) {
				String message = "执行默认LocalLauncher.LocalService时发生异常，可能是流程依赖的Local服务[" + launcherServiceId
						+ "]没有就绪,请检查";
				alarmLogger.error(message, e);
				sysLogger.error(message, e);
				return Constant.State.ERROR;
			}
		} catch (NoSuchBeanDefinitionException e) {
			StringBuilder messageBuilder = new StringBuilder("默认LocalLauncher执行launch方法时出错，框架spring容器中没有查找到id为[");
			messageBuilder.append(launcherServiceId);
			messageBuilder.append("]的Local 接口 bean!");
			String message = messageBuilder.toString();
			alarmLogger.error(message, e);
			sysLogger.error(message, e);
			return Constant.State.ERROR;
		} catch (ClassCastException e) {
			StringBuilder messageBuilder = new StringBuilder("默认LocalLauncher执行launch方法时出错，框架spring容器中子系统Local接口bean[");
			messageBuilder.append(launcherServiceId);
			messageBuilder.append("]没有实现接口com.cmsz.cmup.frame.Local.service.LocalService");
			String message = messageBuilder.toString();
			alarmLogger.error(message, e);
			sysLogger.error(message, e);
			return Constant.State.ERROR;
		} catch (BeanCreationException e) {
			String exceptionMessage = e.getMessage();
			String message = null;
			if (exceptionMessage.contains("No provider available for")) {
				message = "执行默认LocalLauncher.LocalService时发生异常，可能是流程依赖的Local服务[" + launcherServiceId
						+ "]没有就绪，请检查.更多信息请查看堆栈";
			} else {
				message = "执行默认LocalLauncher.LocalService时发生异常：" + e.getMessage();
			}
			alarmLogger.error(message, e);
			sysLogger.error(message, e);
			return Constant.State.ERROR;
		} catch (RpcException e) {
			String message = "执行默认LocalLauncher.LocalService时发生RpcException，可能是Local服务[" + launcherServiceId + "]已经停止了";
			alarmLogger.error(message, e);
			sysLogger.error(message, e);
			return Constant.State.ERROR;
		} catch (Exception e) {
			String message = "执行默认LocalLauncher.LocalService时捕获到异常:" + e.getMessage();
			alarmLogger.error(message, e);
			sysLogger.error(message, e);
			return Constant.State.EXCEPTION;
		}

		// 更新参数
		Map<String, Object> tmap = new HashMap<String, Object>();
		// 将执行任务的IP, 以及返回description 写入到 返回的Map参数中
		if (StringUtils.isNotEmpty(ret.getIp()) && StringUtils.isNotBlank(ret.getIp())) {
			tmap.put(Constant.ARGS_R_NODE, ret.getIp());
		}
		if (StringUtils.isNotEmpty(ret.getDescription()) && StringUtils.isNotBlank(ret.getDescription())) {
			String message = ret.getDescription();
			if (message.length() >= 128) {
				message = message.substring(0, 127);
			}
			tmap.put(Constant.ARGS_R_MESSAGE, message);
		}
		if (ret.getVariableMap() != null) {
			Map<String, Object> vMap = ret.getVariableMap();
			Map<String, Object> map = new HashMap<String, Object>();
			for (String ke : vMap.keySet()) {
				map.put(ke, vMap.get(ke));
			}
			tmap.putAll(map);
		}
		dtask.updateVariables(tmap);

		if (Result.ERROR.equals(ret.getResult())) {
			return Constant.State.ERROR;
		} else if (Result.FAILED.equals(ret.getResult())) {
			return Constant.State.FAILED;
		} else {
			return Constant.State.SUCCESS;
		}
	}

}
