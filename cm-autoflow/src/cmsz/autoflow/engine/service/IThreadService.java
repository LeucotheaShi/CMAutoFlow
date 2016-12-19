/**
 * @Title: IThreadService.java
 * @Description:
 * @Date:2016年12月12日 上午10:18:49
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service;

import java.util.List;

import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.entity.Task;

/**
 * @ClassName:cmsz.autoflow.engine.service.IThreadService
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public interface IThreadService {

	public void runTask(Task task, Execution execution);

	public void runTasks(List<Task> tasks, Execution execution);
}// IThreadService
