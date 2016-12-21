/**
 * @Title: Execution.java
 * @Description:
 * @Date:2016年12月8日 下午5:10:25
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cmsz.autoflow.engine.entity.Flow;
import cmsz.autoflow.engine.entity.Process;
import cmsz.autoflow.engine.entity.Task;

/**
 * @ClassName:cmsz.autoflow.engine.execute.Execution
 * @Description: TODO
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public class Execution implements Serializable {

	/**
	 * @Title: Execution.java
	 * @Description: 封装可执行参数，如引擎实例、process实例、flow实例、task实例
	 * @Date:2016年12月8日 下午5:10:43
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = -5556691167750580707L;

	/**
	 * QuickEnagin holder
	 * 
	 */
	private AutoEngine engine;

	/**
	 * 流程定义
	 */
	private Process process;

	/**
	 * 流程实例
	 */
	private Flow flow;

	/**
	 * 执行参数
	 */
	private Map<String, Object> args;

	/**
	 * 已被执行任务
	 */
	private Task task;

	/**
	 * 返回的任务列表， 需要执行的任务，已触发。
	 */
	private List<Task> tasks = new ArrayList<Task>();

	public Execution(AutoEngine engine, Process process, Flow flow, Map<String, Object> args) {
		this.engine = engine;
		this.process = process;
		this.flow = flow;
		this.args = args;
	}

	/**
	 * @return the engine
	 */
	public AutoEngine getEngine() {
		return engine;
	}

	/**
	 * @param engine
	 *            the engine to set
	 */
	public void setEngine(AutoEngine engine) {
		this.engine = engine;
	}

	/**
	 * @return the process
	 */
	public Process getProcess() {
		return process;
	}

	/**
	 * @param process
	 *            the process to set
	 */
	public void setProcess(Process process) {
		this.process = process;
	}

	/**
	 * @return the flow
	 */
	public Flow getFlow() {
		return flow;
	}

	/**
	 * @param flow
	 *            the flow to set
	 */
	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	/**
	 * @return the args
	 */
	public Map<String, Object> getArgs() {
		return args;
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(Map<String, Object> args) {
		this.args = args;
	}

	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task
	 *            the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks
	 *            the tasks to set
	 */
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void addTask(Task task) {
		this.tasks.add(task);
	}

}// Execution
