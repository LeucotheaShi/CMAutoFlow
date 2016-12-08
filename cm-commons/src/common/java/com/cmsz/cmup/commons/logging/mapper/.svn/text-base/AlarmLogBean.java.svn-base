package com.cmsz.cmup.commons.logging.mapper;

import java.io.Serializable;
import java.util.Map;

import com.cmsz.cmup.commons.utils.ClassUtil;
import com.cmsz.cmup.commons.utils.DateUtil;
import com.cmsz.cmup.commons.utils.IpUtil;

/**
 * 给业务的告警日志实体bean
 * @author JinChao
 * 
 * @date 2015年11月30日 下午3:39:01
 *
 */
public class AlarmLogBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**具体的告警信息**/
	private String message;
	/**告警级别(1:ERROR/0:WARING)，默认为0(WARING)**/
	private int levels=0;
	/**告警发生的系统，如clearing、filecore等**/
	private String system;
	/**业务线编码**/
	private String businessline;
	/**省编码**/
	private String province;
	/**告警发生时间**/
	private String time;
	/**主机ip**/
	private String ip;
	
	/**
	 * 此构造函数主要是为mybatis使用。为了强制给system赋值，
	 * 所以略去了空构造函数，故增加此构造函数给mybatis
	 * @param message 告警信息
	 * @param systemName 系统名称
	 */
	public AlarmLogBean(String message,String system) {
		this.message = message;
		this.system = system;
		this.ip = IpUtil.getIp();
		this.time = DateUtil.getCurrentDateTimeMS();
	}
	
	/**
	 * @param message 告警信息
	 * @param businessline 业务线
	 * @param province 省编码
	 * @param clazz 当前Class，提取系统简称用
	 */
	public AlarmLogBean(String message,String businessline, String province,final Class<?> clazz) {
		this.message = message;
		this.system = ClassUtil.getSystemNameByClass(clazz);
		this.businessline = businessline;
		this.province = province;
		this.ip = IpUtil.getIp();
		this.time = DateUtil.getCurrentDateTimeMS();
	}
	
	/**
	 * @param message 告警信息
	 * @param clazz 当前Class，提取系统简称用
	 */
	public AlarmLogBean(String message,final Class<?> clazz) {
		this.message = message;
		this.system = ClassUtil.getSystemNameByClass(clazz);
		this.ip = IpUtil.getIp();
		this.time = DateUtil.getCurrentDateTimeMS();
	}

	/**
	 * 注意：此构造方法只有当你的逻辑代码为Dispatcher触发的流程时，才能使用，系统会从map中取业务线等信息自动记录。
	 * 		应map中的key为：省编码-province，业务线编码busiLine
	 * @param message 告警信息
	 * @param variableMap variableMap只有当逻辑为Dispatcher触发的流程时，才能使用，系统会从map中取业务线等信息
	 * 				<p>对应map中的key为：省编码-province，业务线编码busiLine,你也可以使用以上两个key自己构造此map后使用</p>
	 * @param clazz 当前Class，提取系统简称用
	 */
	public AlarmLogBean(String message, Map<String, String> variableMap,final Class<?> clazz) {
		this.message = message;
		this.businessline = variableMap.get("busiLine");
		this.province = variableMap.get("province");
		this.system = ClassUtil.getSystemNameByClass(clazz);
		this.ip = IpUtil.getIp();
		this.time = DateUtil.getCurrentDateTimeMS();
	}
	
	
	/**
	 * @param message 告警信息
	 * @param levels 告警级别(1:ERROR/0:WARING)，默认为0(WARING)
	 * @param businessline 业务线
	 * @param province 省编码
	 * @param clazz 当前Class，提取系统简称用
	 */
	public AlarmLogBean(String message,int levels,String businessline, String province,final Class<?> clazz) {
		this.message = message;
		this.levels = levels;
		this.system = ClassUtil.getSystemNameByClass(clazz);
		this.businessline = businessline;
		this.province = province;
		this.ip = IpUtil.getIp();
		this.time = DateUtil.getCurrentDateTimeMS();
	}
	
	/**
	 * @param message 告警信息
	 * @param levels 告警级别(1:ERROR/0:WARING)，默认为0(WARING)
	 * @param clazz 当前Class，提取系统简称用
	 */
	public AlarmLogBean(String message,int levels,final Class<?> clazz) {
		this.message = message;
		this.levels = levels;
		this.system = ClassUtil.getSystemNameByClass(clazz);
		this.ip = IpUtil.getIp();
		this.time = DateUtil.getCurrentDateTimeMS();
	}

	/**
	 * 注意：此构造方法只有当你的逻辑代码为Dispatcher触发的流程时，才能使用，系统会从map中取业务线等信息自动记录。
	 * 		<p>对应map中的key为：省编码-province，业务线编码busiLine,你也可以使用以上两个key自己构造此map后使用</p>
	 * @param message 告警信息
	 * @param levels 告警级别(1:ERROR/0:WARING)，默认为0(WARING)
	 * @param variableMap variableMap只有当逻辑为Dispatcher触发的流程时，才能使用，系统会从map中取业务线等信息
	 * 				对应map中的key为：省编码-province，业务线编码busiLine
	 * @param clazz 当前Class，提取系统简称用
	 */
	public AlarmLogBean(String message,int levels, Map<String, String> variableMap,final Class<?> clazz) {
		this.message = message;
		this.levels = levels;
		this.businessline = variableMap.get("busiLine");
		this.province = variableMap.get("province");
		this.system = ClassUtil.getSystemNameByClass(clazz);
		this.ip = IpUtil.getIp();
		this.time = DateUtil.getCurrentDateTimeMS();
	}
	/**
	 * @author liutao
	 * @param message 告警信息
	 * @param levels  告警级别(1:ERROR/0:WARING)
	 * @param businessline 业务线
	 * @param clazz 当前Class，提取系统简称用
	 */
	public AlarmLogBean (String message,int levels,String businessline,final Class<?> clazz) {
		this.message = message;
		this.levels = levels;
		this.businessline = businessline;
		this.system = ClassUtil.getSystemNameByClass(clazz);
		this.ip = IpUtil.getIp();
		this.time = DateUtil.getCurrentDateTimeMS();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getBusinessline() {
		return businessline;
	}
	public void setBusinessline(String businessline) {
		this.businessline = businessline;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("[system:").append(system).append(",time:").append(time)
		        .append(",levels:").append(levels).append(",ip:").append(ip).append(",businessline:")
		        .append(businessline).append(",province:").append(province).append(",message:").append(message)
		        .append("]");
		return stringBuilder.toString();
	}
}
