/**
 * @Title: Task.java
 * @Description:
 * @Date:2016��12��6�� ����2:56:10
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cmsz.autoflow.engine.constant.Constant;
import cmsz.autoflow.engine.core.DelegateTask;
import cmsz.autoflow.engine.helper.JsonHelper;
import cmsz.autoflow.engine.helper.StringHelper;
import cmsz.autoflow.engine.model.TaskModel;

/**
 * @ClassName:cmsz.autoflow.engine.entity.Task
 * @Description: ����ʵ���ڵ������ʵ����
 * @Date: 2016��12��6��
 * @Author: LeucotheaShi
 */
public class Task implements Serializable, DelegateTask {

	/**
	 * @Title: Task.java
	 * @Description:
	 * @Date:2016��12��6�� ����2:56:15
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 1183843624040764499L;

	private String id;
	private String name;
	private String flowId;
	private String processId;
	private String status;
	private String variables;
	private String node;
	private int currentTimes;
	private int maxTimes;
	private String message;
	private String createTime;
	private String updateTime;
	private String finishTime;
	private TaskModel taskModel;

	/**
	 * 流程实例名字
	 */
	private String flowName;

	public String getComponentId() {
		return taskModel.getRefComponent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cmsz.autoflow.engine.DelegateTask#getDubboId()
	 */
	@Override
	public String getDubboId() {
		return taskModel.getRefDubbo();
	}

	@Override
	public String getFlowName() {
		return flowName;
	}

	/**
	 * @return the taskModel
	 */
	public TaskModel getTaskModel() {
		return taskModel;
	}

	/**
	 * @param taskModel
	 *            the taskModel to set
	 */
	public void setTaskModel(TaskModel taskModel) {
		this.taskModel = taskModel;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the flowId
	 */
	public String getFlowId() {
		return flowId;
	}

	/**
	 * @param flowId
	 *            the flowId to set
	 */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}

	/**
	 * @param processId
	 *            the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the variables
	 */
	public String getVariables() {
		return variables;
	}

	/**
	 * @param variables
	 *            the variables to set
	 */
	public void setVariables(String variables) {
		this.variables = variables;
	}

	/**
	 * @return the node
	 */
	public String getNode() {
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(String node) {
		this.node = node;
	}

	/**
	 * @return the currentTimes
	 */
	public int getCurrentTimes() {
		return currentTimes;
	}

	/**
	 * @param currentTimes
	 *            the currentTimes to set
	 */
	public void setCurrentTimes(int currentTimes) {
		this.currentTimes = currentTimes;
	}

	/**
	 * @return the maxTimes
	 */
	public int getMaxTimes() {
		return maxTimes;
	}

	/**
	 * @param maxTimes
	 *            the maxTimes to set
	 */
	public void setMaxTimes(int maxTimes) {
		this.maxTimes = maxTimes;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
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
	 * @param updateTime
	 *            the updateTime to set
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
	 * @param finishTime
	 *            the finishTime to set
	 */
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @Title: toString
	 * @Description:
	 * @return
	 * @Date:2016��12��6�� ����3:02:17
	 * @Author:LeucotheaShi
	 */
	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", flowId=" + flowId + ", processId=" + processId + ", status="
				+ status + ", variables=" + variables + ", node=" + node + ", currentTimes=" + currentTimes
				+ ", maxTimes=" + maxTimes + ", message=" + message + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", finishTime=" + finishTime + "]";
	}

	public Map<String, Object> getVariableMap() {
		return this.variables == null ? new HashMap<String, Object>() : JsonHelper.fromJson(this.variables, Map.class);
	}

	/**
	 * for temporary store
	 */
	private Map<String, Object> updateVariables = null;

	public void updateVariables(Map<String, Object> args) {
		if (args == null)
			return;
		if (this.updateVariables == null) {
			this.updateVariables = new HashMap<String, Object>();
		}
		this.updateVariables.putAll(args);
		args.remove(Constant.ARGS_COMMON);
		if (StringHelper.isNotEmpty(this.variables)) {
			Map<String, Object> vars = JsonHelper.fromJson(this.variables, Map.class);
			vars.putAll(args);
			this.variables = JsonHelper.toJson(vars);
		} else {
			this.variables = JsonHelper.toJson(args);
		}
	}

	public void setUpdateVariables(Map<String, Object> updateVariables) {
		this.updateVariables = updateVariables;
	}

	public Map<String, Object> getUpdateVariables() {
		return this.updateVariables;
	}

	/**
	 * @Title: getRefBean
	 * @Description:
	 * @return
	 * @Date:2016年12月23日 下午8:51:06
	 * @Author:LeucotheaShi
	 */
	@Override
	public String getRefBean() {
		// TODO Auto-generated method stub
		return taskModel.getRefBean();
	}

}// Task
