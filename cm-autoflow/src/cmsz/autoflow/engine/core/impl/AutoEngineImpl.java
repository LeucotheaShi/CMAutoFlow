/**
 * @Title: AutoEngineImpl.java
 * @Description:
 * @Date:2016年12月12日 上午11:53:56
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.core.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmsz.autoflow.engine.access.DBAccess;
import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.constant.Constant;
import cmsz.autoflow.engine.constant.EventEnum;
import cmsz.autoflow.engine.core.AutoEngine;
import cmsz.autoflow.engine.core.Configuration;
import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.core.ServiceContext;
import cmsz.autoflow.engine.entity.Flow;
import cmsz.autoflow.engine.entity.Process;
import cmsz.autoflow.engine.entity.Task;
import cmsz.autoflow.engine.helper.DateHelper;
import cmsz.autoflow.engine.model.NodeModel;
import cmsz.autoflow.engine.model.ProcessModel;
import cmsz.autoflow.engine.model.StartModel;
import cmsz.autoflow.engine.model.TaskModel;
import cmsz.autoflow.engine.service.AccessService;
import cmsz.autoflow.engine.service.IEventService;
import cmsz.autoflow.engine.service.IFlowService;
import cmsz.autoflow.engine.service.IProcessService;
import cmsz.autoflow.engine.service.IScheduleService;
import cmsz.autoflow.engine.service.ITaskService;
import cmsz.autoflow.engine.service.IThreadService;

/**
 * @ClassName:cmsz.autoflow.engine.core.impl.AutoEngineImpl
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class AutoEngineImpl implements AutoEngine {

	private static final Logger logger = LoggerFactory.getLogger(AutoEngineImpl.class);

	private Configuration config;
	private IProcessService processService;
	private IFlowService flowService;
	private ITaskService taskService;

	private IScheduleService scheduleService;
	private IThreadService threadService;
	private IEventService eventService;

	/**
	 * @Title: configure
	 * @Description:
	 * @param cfg
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	public AutoEngine configure(Configuration cfg) {
		// TODO Auto-generated method stub
		this.config = cfg;
		processService = ServiceContext.find(IProcessService.class);
		flowService = ServiceContext.find(IFlowService.class);
		taskService = ServiceContext.find(ITaskService.class);

		scheduleService = ServiceContext.find(IScheduleService.class);
		threadService = ServiceContext.find(IThreadService.class);
		eventService = ServiceContext.find(IEventService.class);

		DBAccess access = ServiceContext.find(DBAccess.class);
		setDBAccess(access);

		return this;
	}

	protected void setDBAccess(DBAccess access) {

		List<AccessService> services = ServiceContext.findList(AccessService.class);
		for (AccessService as : services) {
			as.setAccess(access);
		}
	}

	/**
	 * @Title: getProcessService
	 * @Description:
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	public IProcessService getProcessService() {
		// TODO Auto-generated method stub
		return this.processService;
	}

	/**
	 * @Title: flow
	 * @Description:
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	public IFlowService flow() {
		// TODO Auto-generated method stub
		return this.flowService;
	}

	/**
	 * @Title: task
	 * @Description:
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	public ITaskService task() {
		// TODO Auto-generated method stub
		return this.taskService;
	}

	/**
	 * @Title: getScheduleService
	 * @Description:
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	public IScheduleService getScheduleService() {
		// TODO Auto-generated method stub
		return this.scheduleService;
	}

	/**
	 * @Title: getThreadService
	 * @Description:
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	public IThreadService getThreadService() {
		// TODO Auto-generated method stub
		return this.threadService;
	}

	/**
	 * @Title: getEventService
	 * @Description:
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	public IEventService getEventService() {
		// TODO Auto-generated method stub
		return this.eventService;
	}

	/**
	 * @Title: startInstanceById
	 * @Description:
	 * @param id
	 * @param args
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	public Flow startInstanceById(String id, Map<String, Object> args) {
		// TODO Auto-generated method stub
		return this.startInstanceById(id, args, null, null);
	}

	/**
	 * @Title: startInstanceById
	 * @Description:
	 * @param id
	 * @param args
	 * @param flowId
	 * @param flowName
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	public Flow startInstanceById(String id, Map<String, Object> args, String flowId, String flowName) {
		// TODO Auto-generated method stub

		if (args == null) {
			args = new HashMap<String, Object>();
		} // if

		Process process = this.processService.getProcessById(id);
		if (process == null) {
			logger.debug("process {} is null.", id);
			return null;
		}

		return this.startProcess(process, args, flowId, flowName);
	}

	/**
	 * 
	 * @Title: startProcess
	 * @Description:
	 * @param process
	 * @param args
	 * @param flowId
	 * @param flowName
	 * @return
	 * @return：Flow
	 * @Date:2016年12月13日 下午2:58:33
	 * @Author:LeucotheaShi
	 */
	private Flow startProcess(Process process, Map<String, Object> args, String flowId, String flowName) {

		/** 待添加流程控制 **/

		Execution execution = execute(process, args, flowId, flowName);

		if (process.getModel() != null) {

			StartModel startModel = process.getModel().getStart();

			try {
				startModel.execute(execution);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("startModel执行错误");
				return null;
			}
		}

		this.getEventService().getHandler().handle(execution, EventEnum.FLOW_STARTED);
		this.getEventService().getHandler().handle(execution, EventEnum.TASK_STARTED);

		this.getThreadService().runTasks(execution.getTasks(), execution);

		return execution.getFlow();
	}// startProcess

	/**
	 * 
	 * @Title: execute
	 * @Description: 创建flow对象、可执行对象
	 * @param process
	 * @param args
	 * @param flowId
	 * @param flowName
	 * @return
	 * @return：Execution
	 * @Date:2016年12月13日 下午2:58:46
	 * @Author:LeucotheaShi
	 */
	private Execution execute(Process process, Map<String, Object> args, String flowId, String flowName) {
		// create a new flow
		Flow flow = this.getFlowService().createFlow(process, args, flowId, flowName);

		// instance a Execution
		Execution execution = new Execution(this, process, flow, args);

		return execution;
	}

	/**
	 * @Title: complete
	 * @Description:
	 * @param id
	 * @param state
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	synchronized public Task complete(String id, String state) {
		// TODO Auto-generated method stub
		return this.complete(id, state, null);
	}

	/**
	 * @Title: complete
	 * @Description:
	 * @param id
	 * @param state
	 * @param args:获取的是task的updateVaribs
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	// synchronized
	public Task complete(String id, String state, Map<String, Object> args) {
		// TODO Auto-generated method stub

		Task task = this.getTaskService().getTask(id);

		if (task == null) {
			logger.error("task {} is null.", id);
			return null;
		} // if

		if (StringUtils.isEmpty(state) || StringUtils.isBlank(state)) {
			logger.error("compelete task, the return state {} is null or empty. {}", state, task);
			state = ConfigNameConstant.Status.EXCEPTION;
		} // if

		task.setFinishTime(DateHelper.getTime());
		task.setStatus(state);
		task.setCurrentTimes(Integer.parseInt(args.get(Constant.CURRENTTIMES).toString()));
		this.getTaskService().updateTask(task);

		if (args != null) {
			task.updateVariables(args);
		} // if

		Flow flow = this.getFlowService().getFlow(task.getFlowId());
		Process process = this.getProcessService().getProcessById(flow.getProcessId());
		Execution execution = new Execution(this, process, flow, task.getVariableMap());
		execution.setTask(task);

		this.getEventService().getHandler().handle(execution, EventEnum.TASK_COMPLETED);

		// 若返回的结果不是成功的，则该流程失败
		if (!ConfigNameConstant.Status.SUCCESS.equals(state) && !ConfigNameConstant.Status.RUNNING.equals(state)) {

			// 走异常分支
			if (ConfigNameConstant.Status.EXCEPTIONBRANCH.equals(state)) {
				Task exptask = this.doExceptionBranch(execution, args);
				return exptask;
			} // if
			else {
				logger.error(
						"the state {} is not Constant.State.Success or Constant.state.RUNNING, the flow failed.  task:{}",
						state, task);
				flow().update(task.getFlowId(), ConfigNameConstant.Status.FAILED);
				return task;
			}
		} // if

		ProcessModel processModel = process.getModel();

		// why not next??
		if (processModel != null) {
			NodeModel nodemodel = processModel.getNode(task.getName());
			task.setTaskModel((TaskModel) nodemodel);
			try {
				nodemodel.execute(execution);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("nodemodel执行错误");
				return null;
			}
		} // if

		//the statement below is related to specified business, using for updating settleDate,province,busLine.
		//Overwrite it when you need.
		/*this.getEventService().getHandler().handle(execution, EventEnum.TASK_STARTED);*/
		
		// where did it set tasks??
		this.getThreadService().runTasks(execution.getTasks(), execution);

		return null;
	}

	/**
	 * @Title: redoFlow
	 * @Description:
	 * @param id
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	synchronized public Flow redoFlow(String id) {
		// TODO Auto-generated method stub
		Flow flow = this.getFlowService().getFlow(id);

		if (flow == null) {
			logger.error("can not find flow {}.", id);
			return null;
		} // if

		if (ConfigNameConstant.STATUS_ACTIVE.equals(flow.getStatus())) {
			logger.error("the flow {} is active, cannot redo it.", id);
			return null;
		} // if

		this.getTaskService().deleteTasks(id);

		this.getFlowService().update(id, ConfigNameConstant.STATUS_ACTIVE);

		Process process = this.getProcessService().getProcessById(flow.getId());

		Execution execution = new Execution(this, process, flow, flow.getVariableMap());

		ProcessModel processModel = process.getModel();
		if (processModel != null) {
			StartModel startModel = processModel.getStart();
			try {
				startModel.execute(execution);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				logger.error("startModel执行错误");
				return null;
			}
		} // if

		this.getThreadService().runTasks(execution.getTasks(), execution);

		return flow;
	}

	/**
	 * @Title: redoTask
	 * @Description:
	 * @param id
	 * @return
	 * @Date:2016年12月12日 上午11:53:56
	 * @Author:LeucotheaShi
	 */
	@Override
	synchronized public Task redoTask(String id) {
		// TODO Auto-generated method stub

		Task task = this.getTaskService().getTask(id);

		if (task == null) {
			logger.error("the task {} is null.", id);
			return null;
		} // if

		String taskStatus = task.getStatus();
		if (taskStatus.equals(ConfigNameConstant.Status.ACTIVE) || taskStatus.equals(ConfigNameConstant.Status.RUNNING)
				|| taskStatus.equals(ConfigNameConstant.Status.START)) {
			logger.error("the task {} is running, cannot redo it.", id);
			return null;
		}

		Flow flow = this.getFlowService().getFlow(task.getFlowId());
		Process process = this.getProcessService().getProcessById(flow.getProcessId());
		Execution execution = new Execution(this, process, flow, task.getVariableMap());

		task.setTaskModel((TaskModel) process.getModel().getNode(task.getName()));

		task.setStatus(ConfigNameConstant.Status.ACTIVE);
		this.getTaskService().updateTask(task);

		this.getThreadService().runTask(task, execution);

		return task;
	}

	/**
	 * 
	 * @Title: doExceptionBranch
	 * @Description:走异常分支
	 * @param task
	 * @param args
	 * @return
	 * @return：Task
	 * @Date:2016年12月28日 下午3:20:25
	 * @Author:LeucotheaShi
	 */
	private Task doExceptionBranch(Execution execution, Map<String, Object> args) {

		if (args == null || args.isEmpty()) {
			logger.error("doExceptionBranch方法出错，args不能为空");
			return null;
		} // if

		String exceptionBranch = args.get(ConfigNameConstant.Status.EXCEPTIONBRANCH).toString();

		ProcessModel processModel = execution.getProcess().getModel();

		Task task = execution.getTask();

		// why not next??
		if (processModel != null && exceptionBranch != null) {
			NodeModel nodemodel = processModel.getNode(task.getName());
			task.setTaskModel((TaskModel) nodemodel);
			// 走exceptionBranch对应的异常分支路线
			// 此处createtask时在execution 的tasks中添加了task
			try {
				nodemodel.runOutException(execution, exceptionBranch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("nodemodel 执行 runOutException 出错");
				return null;
			}
		} // if

		this.getEventService().getHandler().handle(execution, EventEnum.TASK_STARTED);
		// where did it set tasks??
		this.getThreadService().runTasks(execution.getTasks(), execution);

		return null;
	}// doExceptionBranch

	/**
	 * @return the config
	 */
	public Configuration getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(Configuration config) {
		this.config = config;
	}

	/**
	 * @return the flowService
	 */
	public IFlowService getFlowService() {
		return flowService;
	}

	/**
	 * @param flowService
	 *            the flowService to set
	 */
	public void setFlowService(IFlowService flowService) {
		this.flowService = flowService;
	}

	/**
	 * @return the taskService
	 */
	public ITaskService getTaskService() {
		return taskService;
	}

	/**
	 * @param taskService
	 *            the taskService to set
	 */
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * @param processService
	 *            the processService to set
	 */
	public void setProcessService(IProcessService processService) {
		this.processService = processService;
	}

	/**
	 * @param scheduleService
	 *            the scheduleService to set
	 */
	public void setScheduleService(IScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	/**
	 * @param threadService
	 *            the threadService to set
	 */
	public void setThreadService(IThreadService threadService) {
		this.threadService = threadService;
	}

	/**
	 * @param eventService
	 *            the eventService to set
	 */
	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

}
