package cmsz.autoflow.engine.access.mybatis;

import cmsz.autoflow.engine.entity.Task;


public interface TaskMapper {
	
	public void insertTask(Task task);
	
	public Task selectTaskById(String Id);
	
//	public void updateTaskState(Task task);
	
	public Task selectTaskByFlowIdAndTaskName(Task task);
	
	public void deleteTasksByFlowId(String flowId);
	
	public void updateTask(Task task);
	
	
}
