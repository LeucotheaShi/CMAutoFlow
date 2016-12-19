/**
 * @Title: TaskService.java
 * @Description:
 * @Date:2016年12月12日 上午9:59:32
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service.impl;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.entity.Task;
import cmsz.autoflow.engine.helper.DateHelper;
import cmsz.autoflow.engine.helper.JsonHelper;
import cmsz.autoflow.engine.model.TaskModel;
import cmsz.autoflow.engine.service.AccessService;
import cmsz.autoflow.engine.service.ITaskService;

/**
 * @ClassName:cmsz.autoflow.engine.service.impl.TaskService
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class TaskService extends AccessService implements ITaskService {

	/**
	 * @Title: saveTask
	 * @Description:
	 * @param task
	 * @Date:2016年12月12日 上午9:59:32
	 * @Author:LeucotheaShi
	 */
	@Override
	public void saveTask(Task task) {
		// TODO Auto-generated method stub
		this.getAccess().saveTask(task);
	}

	/**
	 * @Title: createTask
	 * @Description:
	 * @param model
	 * @param execution
	 * @return
	 * @Date:2016年12月12日 上午9:59:32
	 * @Author:LeucotheaShi
	 */
	@Override
	public Task createTask(TaskModel model, Execution execution) {
		// TODO Auto-generated method stub
		Task task = new Task();
		task.setCreateTime(DateHelper.getTime());
		task.setCurrentTimes(1);
		task.setFinishTime(null);
		task.setFlowId(execution.getFlow().getId());
		task.setId(execution.getTask().getId());

		task.setProcessId(execution.getProcess().getId());

		task.setCreateTime(DateHelper.getTime());
		task.setUpdateTime(DateHelper.getTime());

		task.setTaskModel(model);
		task.setVariables(JsonHelper.toJson(execution.getArgs()));
		task.setStatus(ConfigNameConstant.STATUS_ACTIVE);

		this.saveTask(task);

		return task;
	}

	/**
	 * @Title: updateTask
	 * @Description:
	 * @param task
	 * @Date:2016年12月12日 上午9:59:32
	 * @Author:LeucotheaShi
	 */
	@Override
	public void updateTask(Task task) {
		// TODO Auto-generated method stub
		this.getAccess().updateTask(task);

	}

	/**
	 * @Title: getTask
	 * @Description:
	 * @param taskId
	 * @return
	 * @Date:2016年12月12日 上午9:59:32
	 * @Author:LeucotheaShi
	 */
	@Override
	public Task getTask(String taskId) {
		// TODO Auto-generated method stub
		return this.getAccess().getTask(taskId);
	}

	/**
	 * @Title: getTask
	 * @Description:
	 * @param flowId
	 * @param taskName
	 * @return
	 * @Date:2016年12月12日 上午9:59:32
	 * @Author:LeucotheaShi
	 */
	@Override
	public Task getTask(String flowId, String taskName) {
		// TODO Auto-generated method stub
		Task task = new Task();
		task.setFlowId(flowId);
		task.setName(taskName);
		return this.getAccess().getTask(task);
	}

	/**
	 * @Title: deleteTasks
	 * @Description:
	 * @param flowId
	 * @Date:2016年12月12日 上午9:59:32
	 * @Author:LeucotheaShi
	 */
	@Override
	public void deleteTasks(String flowId) {
		// TODO Auto-generated method stub
		this.getAccess().deleteTasksByFlowId(flowId);
	}

}
