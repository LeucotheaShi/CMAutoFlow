/**
 * @Title: Schedule.java
 * @Description:
 * @Date:2016年12月6日 下午2:32:38
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.entity;

import java.io.Serializable;

/**
 * @ClassName:cmsz.autoflow.engine.entity.Schedule
 * @Description: 调度表的实体类
 * @Date: 2016年12月6日
 * @Author: LeucotheaShi
 */
public class Schedule implements Serializable{

	/**
	 * @Title: Schedule.java
	 * @Description:
	 * @Date:2016年12月6日 下午2:33:30
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = -2926193752915584049L;

	private String id;
	private String processId;
	private String flowName;
	private String cron;
	private String variables;
	private String status;
	private String createTime;
	private String updateTime;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}
	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * @return the flowName
	 */
	public String getFlowName() {
		return flowName;
	}
	/**
	 * @param flowName the flowName to set
	 */
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	/**
	 * @return the cron
	 */
	public String getCron() {
		return cron;
	}
	/**
	 * @param cron the cron to set
	 */
	public void setCron(String cron) {
		this.cron = cron;
	}
	/**
	 * @return the variables
	 */
	public String getVariables() {
		return variables;
	}
	/**
	 * @param variables the variables to set
	 */
	public void setVariables(String variables) {
		this.variables = variables;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @Title: toString
	 * @Description:
	 * @return
	 * @Date:2016年12月6日 下午2:54:59
	 * @Author:LeucotheaShi
	 */
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", processId=" + processId + ", flowName=" + flowName + ", cron=" + cron
				+ ", variables=" + variables + ", status=" + status + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
	
	
	
}//Schedule
