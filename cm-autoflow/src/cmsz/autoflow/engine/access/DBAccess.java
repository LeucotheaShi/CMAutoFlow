/**
 * @Title: DBAccess.java
 * @Description:
 * @Date:2016年12月6日 下午4:28:45
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.access;

import java.util.List;

import cmsz.autoflow.engine.entity.Flow;
import cmsz.autoflow.engine.entity.Schedule;
import cmsz.autoflow.engine.entity.Task;
import cmsz.autoflow.engine.entity.Process;

/**
 * @ClassName:cmsz.autoflow.engine.access.DBAccess
 * @Description: TODO
 * @Date: 2016年12月6日
 * @Author: LeucotheaShi
 */
public interface DBAccess {
	
	/**
	 * 保存任务对象
	 * @param task
	 */
	public void saveTask(Task task);
	
	/**
	 * 保存流程实例
	 * @param flow
	 */
	public void saveFlow(Flow flow);
	
	/**
	 * 保存流程定义
	 * @param process
	 */
	public void saveProcess(Process process);
	
	/**
	 * 更新任务对象
	 * @param task
	 */
	public void updateTask(Task task);
	
	/**
	 * 更新流程实例对象
	 * @param flow
	 */
	public void updateFlow(Flow flow);
	
	/**
	 * 更新流程定义对象
	 * @param process
	 */
	public void updateProcess(Process process);
	
	/**
	 * 获取任务对象
	 * @param taskId
	 * @return
	 */
	public Task getTask(String taskId);
	
	
	/**
	 * 根据流程实例id和任务名，获取任务实例。
	 * @param flowId
	 * @param taskName
	 * @return
	 */
	public Task getTask(Task task);
	
	/**
	 * 获取流程实例对象
	 * @param flowId
	 * @return
	 */
	public Flow getFlow(String flowId);
	
	public List<Flow>	getFlow(String flowName, String settleDate);
	
	
	/**
	 * 获取流程定义对象
	 * @param processId
	 * @return
	 */
	public Process getProcess(String processId);
	
	public void deleteProcess(Process process);
	
	public void deleteTasksByFlowId(String flowId);
	
	public List<Schedule> getAllSchedule();
}//DBAccess
