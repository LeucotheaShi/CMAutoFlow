package com.cmsz.cmup.commons.logging.alarm;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmsz.cmup.commons.logging.handler.BaseLogger;
import com.cmsz.cmup.commons.logging.utils.LogLocation;
import com.cmsz.cmup.commons.utils.DateUtil;
import com.cmsz.cmup.commons.utils.StringUtilsCmup;
import com.cmsz.cmup.commons.utils.constant.BusinessLineMapConstant;

/**
 * 告警日志
 * 
 * @author lijh,Jinchao
 *
 */
public class AlarmLogHandler extends BaseLogger {

	private static Logger logger = LoggerFactory.getLogger(AlarmLogHandler.class);
	public static final String LOG_LEVEL_ALARM_ERROR = "SERIOUS";

	public static AlarmLogHandler getLogger(final Class<?> clazz) {
		return new AlarmLogHandler(clazz);
	}

	private AlarmLogHandler(final Class<?> clazz) {
		super(clazz);
	}

	/*
	 * 根据新的日志修改规范必须传入流程Ｍａｐ参数（业务线）－－－由业务线找到应用名称信息 ２０１６－０６－０２ public void
	 * warn(String msgContext) { LogLocation logLocation = new LogLocation(new
	 * Throwable(), getClassname()); String tmp =
	 * convAlarmMessage(LOG_LEVEL_WARNING, msgContext,
	 * logLocation.getLineNumber()); logger.warn(tmp);
	 * 
	 * } public void warn(String msgContext,Throwable t) { LogLocation
	 * logLocation = new LogLocation(new Throwable(), getClassname()); String
	 * tmp = convAlarmMessage(LOG_LEVEL_WARNING, msgContext,
	 * logLocation.getLineNumber()); logger.warn(tmp,t);
	 * 
	 * }
	 */

	public void warn(String msgContext, Map<String, Object> variableMap) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		String tmp = convAlarmMessage(LOG_LEVEL_WARNING, msgContext, logLocation.getLineNumber(), variableMap);
		logger.warn(tmp);

	}

	/**
	 * 
	 * @param msgContext
	 * @param variableMap
	 *            传variableMap给日志框架，日志框架会自动从map中取到省编码、业务线等信息
	 * @param t
	 */
	public void warn(String msgContext, Map<String, Object> variableMap, Throwable t) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		String tmp = convAlarmMessage(LOG_LEVEL_WARNING, msgContext, logLocation.getLineNumber(), variableMap);
		logger.warn(tmp, t);

	}
	/*
	 * 根据新的日志修改规范必须传入流程Ｍａｐ参数（业务线）－－－由业务线找到应用名称信息 ２０１６－０６－０２ public void
	 * error(String msgContext) { LogLocation logLocation = new LogLocation(new
	 * Throwable(), getClassname()); String tmp =
	 * convAlarmMessage(LOG_LEVEL_ALARM_ERROR, msgContext,
	 * logLocation.getLineNumber()); logger.error(tmp); } public void
	 * error(String msgContext, Throwable t) { LogLocation logLocation = new
	 * LogLocation(new Throwable(), getClassname()); String tmp =
	 * convAlarmMessage(LOG_LEVEL_ALARM_ERROR, msgContext,
	 * logLocation.getLineNumber()); logger.error(tmp,t); }
	 * 
	 * private String convAlarmMessage(final String p_loglevel,final String
	 * msgContext,final String lineNumber) { Map<String, Object> variableMap =
	 * getThreadVariableMap().get(); return
	 * convAlarmMessage(p_loglevel,msgContext,lineNumber,variableMap); }
	 * 
	 */

	/**
	 * 
	 * @param msgContext
	 * @param variableMap
	 *            传variableMap给日志框架，日志框架会自动从map中取到省编码、业务线等信息
	 */
	public void error(String msgContext, Map<String, Object> variableMap) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		String tmp = convAlarmMessage(LOG_LEVEL_ALARM_ERROR, msgContext, logLocation.getLineNumber(), variableMap);
		logger.error(tmp);
	}

	public void error(String msgContext) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		String tmp = convAlarmMessage(LOG_LEVEL_ALARM_ERROR, msgContext, logLocation.getLineNumber(), null);
		logger.error(tmp);
	}

	public void error(String msgContext, Throwable throwable) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		String tmp = convAlarmMessage(LOG_LEVEL_ALARM_ERROR, msgContext, logLocation.getLineNumber(), null);
		logger.error(tmp);
	}

	/**
	 * 
	 * @param msgContext
	 * @param variableMap
	 *            传variableMap给日志框架，日志框架会自动从map中取到省编码、业务线等信息
	 * @param t
	 */

	public void error(String msgContext, Map<String, Object> variableMap, Throwable t) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		String tmp = convAlarmMessage(LOG_LEVEL_ALARM_ERROR, msgContext, logLocation.getLineNumber(), variableMap);
		logger.error(tmp, t);
	}

	/**
	 * 格式化日志
	 * 
	 * @param p_loglevel
	 * @param msgContext
	 * @param lineNumber
	 * @param variableMap
	 *            传@variableMap给日志框架，日志框架会自动从map中取到省编码、业务线等信息
	 * @return
	 */
	private String convAlarmMessage(final String p_loglevel, final String msgContext, final String lineNumber,
			Map<String, Object> variableMap) {
		StringBuilder sb_tmp = new StringBuilder();
		sb_tmp.append(getAlarmHeaderMessage(p_loglevel, msgContext, lineNumber, variableMap));
		sb_tmp.append(getAlarmFooterMessage(msgContext));
		return sb_tmp.toString().replaceAll("(\r\n|\r|\n|\n\r)", "");
	}

	/**
	 * 日志前半部分
	 * 
	 * @param p_loglevel
	 * @param msgContext
	 * @param lineNumber
	 * @return
	 */
	private String getAlarmHeaderMessage(final String p_loglevel, final String msgContext, final String lineNumber,
			Map<String, Object> variableMap) {

		String busiLine = "";
		String provString = "";
		String flowId = "";
		String taskId = "";

		if (variableMap != null && variableMap.size() > 0) {
			busiLine = StringUtilsCmup.trimNull2Blank(variableMap.get("busiLine").toString());
			provString = this.getProvString(variableMap.get("province").toString());

			flowId = StringUtilsCmup.trimNull2Blank(variableMap.get("flowId").toString());
			taskId = StringUtilsCmup.trimNull2Blank(variableMap.get("taskId").toString());
		}

		StringBuilder sb_tmp = new StringBuilder();

		// 日志以“BL##”开头，以“##LB”结尾，字段之间采用“#”符号进行分隔。
		sb_tmp.append("BL##");
		// 日志级别
		sb_tmp.append(p_loglevel);
		sb_tmp.append("#");
		// 系统时间
		sb_tmp.append(DateUtil.getCurrentTimeDescByPattern("yyyyMMddHHmmss"));
		sb_tmp.append("#");
		// 主机名称
		sb_tmp.append(getHostName());
		sb_tmp.append("#");
		// 业务系统名称
		String appName = BusinessLineMapConstant.getAppName(busiLine);
		sb_tmp.append(appName);
		sb_tmp.append("#");
		// 子系统名称
		sb_tmp.append(getSubSystem());
		sb_tmp.append("#");
		// 模块名称
		sb_tmp.append(getModule());
		sb_tmp.append("#");
		// 实例名称
		sb_tmp.append(getInstance());
		sb_tmp.append("#");
		// 包名.类名[行数]
		sb_tmp.append(getClassname()).append("[").append(lineNumber).append("]");
		sb_tmp.append("#");
		// 业务线名称
		sb_tmp.append(busiLine);
		sb_tmp.append("#");
		// 发起方系统，值可以为空
		sb_tmp.append(busiLine);
		sb_tmp.append("#");
		// 交易流水号,填写能唯一标识交易的流水号或订单号,值可以为空
		sb_tmp.append(flowId).append("|").append(taskId);
		sb_tmp.append("#");

		// 省代码|省中文缩写，值可以为空
		sb_tmp.append(provString);
		sb_tmp.append("#");
		return sb_tmp.toString();
	}

	/**
	 * 日志后半部分,日志描述
	 * 
	 * @param msgContext
	 * @return
	 */
	protected String getAlarmFooterMessage(String msgContext) {
		StringBuilder sb_tmp = new StringBuilder();
		sb_tmp.append(msgContext);
		sb_tmp.append("##LB");
		return sb_tmp.toString();
	}

}
