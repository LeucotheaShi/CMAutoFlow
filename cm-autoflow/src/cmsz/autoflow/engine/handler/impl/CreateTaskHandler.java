package cmsz.autoflow.engine.handler.impl;

import cmsz.autoflow.engine.core.AutoEngine;
import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.entity.Task;
import cmsz.autoflow.engine.handler.IHandler;
import cmsz.autoflow.engine.helper.JsonHelper;
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
	public void handle(Execution execution) {
		AutoEngine engine = execution.getEngine();
		task = engine.task().getTask(execution.getFlow().getId(), model.getName());
		if (task != null) {
			task.setTaskModel(model);

			/*
			 * // get common map Map<String, Object> userMap =
			 * execution.getArgs(); // set user map List<FieldModel> fieldList =
			 * model.getFieldModels(); if (fieldList != null &&
			 * !fieldList.isEmpty()) { for (FieldModel field : fieldList) {
			 * userMap.put(field.getKey(), field.getValue()); } // for } // if
			 */
			task.setVariables(JsonHelper.toJson(execution.getArgs()));
			engine.task().updateTask(task);
		} else
			task = engine.task().createTask(model, execution);
		execution.addTask(task);
	}

}
