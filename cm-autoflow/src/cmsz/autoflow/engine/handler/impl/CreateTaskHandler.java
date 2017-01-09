package cmsz.autoflow.engine.handler.impl;

import java.util.List;
import java.util.Map;

import cmsz.autoflow.engine.constant.Constant;
import cmsz.autoflow.engine.core.AutoEngine;
import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.entity.Task;
import cmsz.autoflow.engine.handler.IHandler;
import cmsz.autoflow.engine.helper.DateHelper;
import cmsz.autoflow.engine.helper.JsonHelper;
import cmsz.autoflow.engine.model.FieldModel;
import cmsz.autoflow.engine.model.TaskModel;

public class CreateTaskHandler implements IHandler {

	private TaskModel model;

	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public CreateTaskHandler(TaskModel target) {
		this.model = target;
	}

	@Override
	public void handle(Execution execution) throws Exception {
		AutoEngine engine = execution.getEngine();
		// 根据flowId和taskName从数据库查找task记录
		task = engine.task().getTask(execution.getFlow().getId(), model.getName());
		System.out.println("\n\n\n\n\n\n**********************************");
		System.out.println("model.getName()=" + model.getName());
		System.out.println("execution.getFlow().getId()=" + execution.getFlow().getId());
		System.out.println("task=" + task);
		// 若能查到记录，说明同一流程中该task已被创建过一次了
		if (task != null) {

			int maxTimes = task.getMaxTimes();
			int curTimes = task.getCurrentTimes();
			if (curTimes >= maxTimes) {
				task.setStatus(Constant.State.EXCEPTION);
				task.setUpdateTime(DateHelper.getTime());
				task.setFinishTime(DateHelper.getTime());
				task.setVariables(JsonHelper.toJson(execution.getArgs()));
				task.setMessage("该task执行已达到上限次数 " + curTimes);
				engine.task().updateTask(task);
				throw new Exception("该task " + task.getId() + "(" + task.getName() + ")执行已达到上限次数 " + curTimes);
			} //
			else {
				task.setCurrentTimes(curTimes + 1);

				task.setTaskModel(model);

				// get common map
				Map<String, Object> userMap = execution.getArgs();
				// set user map
				List<FieldModel> fieldList = model.getFieldModels();
				if (fieldList != null && !fieldList.isEmpty()) {
					for (FieldModel field : fieldList) {
						userMap.put(field.getKey(), field.getValue());
					} // for
				} // if

				task.setVariables(JsonHelper.toJson(execution.getArgs()));
				engine.task().updateTask(task);
			} // else
		} else
			task = engine.task().createTask(model, execution);
		// 此处将task添加到执行tasks中
		execution.addTask(task);
	}

}
