package com.cmsz.cmup.commons.logging.system;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmsz.cmup.commons.logging.handler.BaseLogger;
import com.cmsz.cmup.commons.logging.utils.LogLocation;
import com.cmsz.cmup.commons.utils.DateUtil;
import com.cmsz.cmup.commons.utils.StringUtilsCmup;

/**
 * 系统运行日志
 * 
 * @author lijh,Jinchao
 * 
 */
public class SystemLogHandler extends BaseLogger {

	private static Logger logger = LoggerFactory.getLogger(SystemLogHandler.class);

	public static SystemLogHandler getLogger(final Class<?> clazz) {
		return new SystemLogHandler(clazz);
	}

	private SystemLogHandler(final Class<?> clazz) {
		super(clazz);
	}

	// debug日志不做格式要求
	public void debug(String msgContext) {
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		logger.debug(msgContext);
	}

	public void info(String msgContext) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_INFO, msgContext, logLocation.getLineNumber());
		logger.info(tmp);
	}

	public void warn(String msgContext) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_WARNING, msgContext, logLocation.getLineNumber());
		logger.warn(tmp);
	}

	public void warn(String msgContext, Throwable t) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_WARNING, msgContext, logLocation.getLineNumber());
		logger.warn(tmp, t);
	}

	public void error(String msgContext) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_ERROR, msgContext, logLocation.getLineNumber());
		logger.error(tmp);
	}

	public void error(String msgContext, Throwable t) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_ERROR, msgContext, logLocation.getLineNumber());
		logger.error(tmp, t);
	}

	public void info(String msgContext, Map<String, Object> variableMap) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_INFO, msgContext, logLocation.getLineNumber(), variableMap);
		logger.info(tmp);
	}

	public void warn(String msgContext, Map<String, Object> variableMap) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_WARNING, msgContext, logLocation.getLineNumber(), variableMap);
		logger.warn(tmp);
	}

	public void warn(String msgContext, Map<String, Object> variableMap, Throwable t) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_WARNING, msgContext, logLocation.getLineNumber(), variableMap);
		logger.warn(tmp, t);
	}

	public void error(String msgContext, Map<String, Object> variableMap) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_ERROR, msgContext, logLocation.getLineNumber(), variableMap);
		logger.error(tmp);
	}

	public void error(String msgContext, Map<String, Object> variableMap, Throwable t) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		if (StringUtils.isNotEmpty(msgContext)) {
			msgContext = msgContext.replaceAll("\t|\r|\n", "");
		}
		String tmp = convMessage(LOG_LEVEL_ERROR, msgContext, logLocation.getLineNumber(), variableMap);
		logger.error(tmp, t);
	}

	/**
	 * 格式化日志
	 * 
	 * @param p_loglevel
	 * @param msgContext
	 * @param lineNumber
	 * @return
	 */
	private String convMessage(final String p_loglevel, final String msgContext, final String lineNumber) {
		Map<String, Object> variableMap = getThreadVariableMap().get();
		return convMessage(p_loglevel, msgContext, lineNumber, variableMap);

	}

	/**
	 * 格式化日志
	 * 
	 * @param p_loglevel
	 * @param msgContext
	 * @param lineNumber
	 * @return
	 */
	private String convMessage(final String p_loglevel, final String msgContext, final String lineNumber,
			Map<String, Object> variableMap) {
		StringBuilder sb_tmp = new StringBuilder();
		sb_tmp.append(getHeaderMessage(p_loglevel, msgContext, lineNumber, variableMap));
		sb_tmp.append(super.getFooterMessage(msgContext));
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
	private String getHeaderMessage(final String p_loglevel, final String msgContext, final String lineNumber,
			Map<String, Object> variableMap) {

		String flowId = "";
		String taskId = "";
		String provString = "";
		String busiLine = "";
		if (variableMap != null && variableMap.size() > 0) {
			busiLine = StringUtilsCmup.trimNull2Blank(variableMap.get("busiLine").toString());
			provString = this.getProvString(variableMap.get("province").toString());
			flowId = StringUtilsCmup.trimNull2Blank(variableMap.get("flowId").toString());
			taskId = StringUtilsCmup.trimNull2Blank(variableMap.get("taskId").toString());

		}

		StringBuilder sb_tmp = new StringBuilder();
		// 日志级别
		sb_tmp.append(p_loglevel);
		sb_tmp.append("#");
		// 系统时间
		sb_tmp.append(DateUtil.getCurrentTimeDescByPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		sb_tmp.append("#");
		// 应用标识
		sb_tmp.append(getAppId());
		sb_tmp.append("#");

		// 唯一标识（能唯一标识交易的流水号或订单号） Serial:${唯一标识符}
		// Serial:4d5c01842f37d90651f9693783c6564279fed6f4
		sb_tmp.append("Serial:").append(flowId).append("|").append(taskId);
		sb_tmp.append("#");

		// 交易码（业务代码） ActivityCode:${交易码} ActivityCode:012003
		sb_tmp.append("ActivityCode:").append(busiLine);
		sb_tmp.append("#");

		// 发起方系统标识 ReqSys:${发起方系统标识} ReqSys:0064
		sb_tmp.append("ReqSys:").append(busiLine);
		sb_tmp.append("#");
		// 省代码 Province:${省代码} Province:100
		sb_tmp.append("Province:").append(provString);
		sb_tmp.append("#");
		// 包名.类名[行数]
		sb_tmp.append(getClassname()).append("[").append(lineNumber).append("]");
		// 不能用#号，#认为是日志格式字段与字段的分割符，而系统日志没有要求打印包名.类名[行数]，在此让包名.类名[行数]与后面的日志描述为一个字段
		sb_tmp.append(":");

		return sb_tmp.toString();
	}

}
