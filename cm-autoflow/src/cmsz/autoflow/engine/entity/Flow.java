/**
 * @Title: Flow.java
 * @Description:
 * @Date:2016年12月6日 上午10:51:25
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.entity;

import java.io.Serializable;

/**
 * @ClassName:cmsz.autoflow.engine.entity.Flow
 * @Description: 流程实例的实体类
 * @Date: 2016年12月6日
 * @Author: LeucotheaShi
 */
public class Flow implements Serializable {

	/**
	 * @Title: Flow.java
	 * @Description:
	 * @Date:2016年12月6日 上午10:51:34
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 3084464106695967255L;

	
	private String id;
	private String name;
	private String processId;
	private String status;
	private String variables;
	private String createTime;
	private String updateTime;
	private String finishTime;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the finishTime
	 */
	public String getFinishTime() {
		return finishTime;
	}
	/**
	 * @param finishTime the finishTime to set
	 */
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	/**
	 * @Title: toString
	 * @Description:
	 * @return
	 * @Date:2016年12月6日 下午2:26:00
	 * @Author:LeucotheaShi
	 */
	@Override
	public String toString() {
		return "Flow [id=" + id + ", name=" + name + ", processId=" + processId + ", status=" + status + ", variables="
				+ variables + ", createTime=" + createTime + ", updateTime=" + updateTime + ", finishTime=" + finishTime
				+ "]";
	}

	
	
	
	
	
	
	
}//Flow
