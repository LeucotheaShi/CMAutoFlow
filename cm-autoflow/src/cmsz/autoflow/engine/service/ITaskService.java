/**
 * @Title: ITaskService.java
 * @Description:
 * @Date:2016年12月12日 上午9:57:45
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service;

import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.entity.Task;
import cmsz.autoflow.engine.model.TaskModel;

/**
 * @ClassName:cmsz.autoflow.engine.service.ITaskService
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public interface ITaskService {

	/**
	 * 保存任务，在数据库中插入数据
	 * 
	 * @param task
	 */
	public void saveTask(Task task);

	/**
	 * 根据TaskModel， Execution创建任务
	 * 
	 * @param model
	 * @param execution
	 * @return
	 */
	public Task createTask(TaskModel model, Execution execution);

	/**
	 * 更新task
	 * 
	 * @param task
	 */
	public void updateTask(Task task);

	/**
	 * 根据TaskId从数据库获取Task对象
	 * 
	 * @param id
	 * @return
	 */
	public Task getTask(String taskId);

	/**
	 * 根据流程实例名、任务名，查找任务
	 * 
	 * @param flowId
	 * @param taskName
	 * @return
	 */
	public Task getTask(String flowId, String taskName);

	/**
	 * 删除流程
	 * 
	 * @param flowId
	 */
	public void deleteTasks(String flowId);

}// ITaskService
