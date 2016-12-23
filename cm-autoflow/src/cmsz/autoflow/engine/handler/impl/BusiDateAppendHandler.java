/**
 * @Title: BusiDateAppendHandler.java
 * @Description:
 * @Date:2016年12月12日 下午2:52:57
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.handler.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmsz.autoflow.engine.constant.Constant;
import cmsz.autoflow.engine.constant.EventEnum;
import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.entity.FlowAppend;
import cmsz.autoflow.engine.entity.Task;
import cmsz.autoflow.engine.entity.TaskAppend;
import cmsz.autoflow.engine.handler.IEventHandler;
import cmsz.autoflow.engine.helper.JsonHelper;

/**
 * @ClassName:cmsz.autoflow.engine.handler.impl.BusiDateAppendHandler
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class BusiDateAppendHandler implements IEventHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(BusiDateAppendHandler.class);

	/**
	 * @Title: handle
	 * @Description:
	 * @param execution
	 * @param eventEnum
	 * @Date:2016年12月12日 下午2:52:57
	 * @Author:LeucotheaShi
	 */
	@Override
	public void handle(Execution execution, EventEnum eventEnum) {
		// TODO Auto-generated method stub
		logger.info("execution event {}", eventEnum);

		switch (eventEnum) {
		case FLOW_STARTED:
			this.appendFlowData(execution);
			break;
		case TASK_STARTED:
			this.appendTaskData_Launched(execution);
			break;
		case TASK_COMPLETED:
			this.appendTaskData_Complete(execution);
			break;
		default:
			logger.info("can't handle this event : " + eventEnum);
		}
	}

	private void appendFlowData(Execution execution) {
		String strcomm = JsonHelper.toJson(execution.getArgs().get(Constant.ARGS_COMMON));
		logger.info("appendFlowData strcomm:{}", strcomm);

		Map<String, String> argcom = JsonHelper.fromJson(strcomm, Map.class);
		if (null == argcom) {
			logger.error("Cant't parse {} to ArgCommon ", strcomm);
			return;
		}

		FlowAppend flowAppend = new FlowAppend();
		flowAppend.setSettleDate(argcom.get("settleDate"));
		flowAppend.setBusiLine(argcom.get("busiLine"));
		flowAppend.setProvince(argcom.get("province"));
		flowAppend.setId(execution.getFlow().getId());

		logger.info("append date to flow : " + argcom.get("busiLine") + " " + argcom.get("province") + " "
				+ argcom.get("settleDate"));

		execution.getEngine().flow().updateFlowAppend(flowAppend);

	}
	
	private void appendTaskData_Launched(Execution execution) {
		String strcomm = JsonHelper.toJson(execution.getArgs().get(Constant.ARGS_COMMON));
		Map<String, String> argcom = JsonHelper.fromJson(strcomm, Map.class);
		logger.info("appendTaskData strcomm:" + strcomm);

		if (null == argcom) {
			String msgContext = "Cant't parse " + strcomm + " to ArgCommon ";
			logger.error(msgContext);
			return;
		}
		
		TaskAppend taskAppend = new TaskAppend();
		taskAppend.setSettleDate(argcom.get("settleDate"));
		taskAppend.setBusiLine(argcom.get("busiLine"));
		taskAppend.setProvince(argcom.get("province"));
		for (Task t : execution.getTasks()) {
			taskAppend.setId(t.getId());
			execution.getEngine().task().updateTaskAppend(taskAppend);
		}
	
	}
	
	private void appendTaskData_Complete(Execution execution) {

		String strcomm = JsonHelper.toJson(execution.getArgs().get(Constant.ARGS_COMMON));
		String strnode = JsonHelper.toJson(execution.getArgs().get(Constant.ARGS_R_NODE));
		String strmsg = JsonHelper.toJson(execution.getArgs().get(Constant.ARGS_R_MESSAGE));

		execution.getArgs().remove(Constant.ARGS_R_MESSAGE);
		execution.getArgs().remove(Constant.ARGS_R_NODE);

		logger.info("appendTaskData_Complete  appendTaskData strcomm:" + strcomm);

		Map<String, String> argcom = JsonHelper.fromJson(strcomm, Map.class);
		if (null == argcom) {
			String msgContext = "Cant't parse " + strcomm + " to ArgCommon ";
			logger.error(msgContext);
			return;
		}

		TaskAppend taskAppend = new TaskAppend();
		taskAppend.setSettleDate(argcom.get("settleDate"));
		taskAppend.setBusiLine(argcom.get("busiLine"));
		taskAppend.setProvince(argcom.get("province"));
		taskAppend.setNode(strnode);
		taskAppend.setMessage(strmsg);
		taskAppend.setId(execution.getTask().getId());

		// 打印日志
		logger.info("appendTaskData: settledata=" + argcom.get("settleDate") + " province="
				+ argcom.get("province") + " busiline=" + argcom.get("busiLine"));
		logger.info("appendTaskData  is :" + taskAppend);

		execution.getEngine().task().updateTaskAppend(taskAppend);

	}// appendTaskData_Complete
}
