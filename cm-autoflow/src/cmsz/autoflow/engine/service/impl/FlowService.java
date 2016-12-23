/**
 * @Title: FlowService.java
 * @Description:
 * @Date:2016年12月10日 下午8:07:57
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service.impl;

import java.util.List;
import java.util.Map;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.entity.Flow;
import cmsz.autoflow.engine.entity.FlowAppend;
import cmsz.autoflow.engine.entity.Process;
import cmsz.autoflow.engine.helper.DateHelper;
import cmsz.autoflow.engine.helper.JsonHelper;
import cmsz.autoflow.engine.helper.SequenceHelper;
import cmsz.autoflow.engine.helper.StringHelper;
import cmsz.autoflow.engine.service.AccessService;
import cmsz.autoflow.engine.service.IFlowService;

/**
 * @ClassName:cmsz.autoflow.engine.service.impl.FlowService
 * @Description: TODO
 * @Date: 2016年12月10日
 * @Author: LeucotheaShi
 */
public class FlowService extends AccessService implements IFlowService {

	/**
	 * @Title: createFlow
	 * @Description:
	 * @param process
	 * @param args
	 * @return
	 * @Date:2016年12月10日 下午8:07:57
	 * @Author:LeucotheaShi
	 */
	@Override
	public Flow createFlow(Process process, Map<String, Object> args) {
		// TODO Auto-generated method stub
		return createFlow(process, args, null, null);
	}

	/**
	 * @Title: saveFlow
	 * @Description:
	 * @param flow
	 * @Date:2016年12月10日 下午8:07:57
	 * @Author:LeucotheaShi
	 */
	@Override
	public void saveFlow(Flow flow) {
		// TODO Auto-generated method stub
		getAccess().saveFlow(flow);
	}// saveFlow

	/**
	 * @Title: update
	 * @Description:
	 * @param flowId
	 * @param state
	 * @Date:2016年12月10日 下午8:07:57
	 * @Author:LeucotheaShi
	 */
	@Override
	public void update(String flowId, String state) {
		// TODO Auto-generated method stub
		Flow flow = getFlow(flowId);
		flow.setStatus(state);
		getAccess().updateFlow(flow);

	}

	/**
	 * @Title: getFlow
	 * @Description:
	 * @param flowId
	 * @return
	 * @Date:2016年12月10日 下午8:07:57
	 * @Author:LeucotheaShi
	 */
	@Override
	public Flow getFlow(String flowId) {
		// TODO Auto-generated method stub
		return getAccess().getFlow(flowId);
	}

	/**
	 * @Title: getFlow
	 * @Description:
	 * @param flowName
	 * @param createDate:后端用的是like，因此可以只用时间的一部分来查询
	 * @return
	 * @Date:2016年12月10日 下午8:07:57
	 * @Author:LeucotheaShi
	 */
	@Override
	public List<Flow> getFlow(String flowName, String createTime) {
		// TODO Auto-generated method stub
		return getAccess().getFlow(flowName, createTime);
	}

	/**
	 * @Title: createFlow
	 * @Description:
	 * @param process
	 * @param args
	 * @param flowId
	 * @param flowName
	 * @return
	 * @Date:2016年12月10日 下午8:07:57
	 * @Author:LeucotheaShi
	 */
	@Override
	public Flow createFlow(Process process, Map<String, Object> args, String flowId, String flowName) {
		// TODO Auto-generated method stub

		Flow flow = new Flow();

		// create new flow id
		if (StringHelper.isNotEmpty(flowId)) {
			flow.setId(flowId);
		} // if
		else {
			flow.setId("Flow_" + DateHelper.getDate(0) + "_" + SequenceHelper.getSequence());
		} // else

		// create new flow name
		if (StringHelper.isNotEmpty(flowName)) {
			flow.setName(flowName);
		} else {
			flow.setName(flow.getId());
		} // else

		flow.setProcessId(process.getId());
		flow.setStatus(ConfigNameConstant.STATUS_ACTIVE);
		flow.setVariables(JsonHelper.toJson(args));
		flow.setCreateTime(DateHelper.getTime());
		flow.setUpdateTime(flow.getCreateTime());

		saveFlow(flow);

		return flow;
	}
	
	@Override
	public void updateFlowAppend(FlowAppend flowAppend){
		getAccess().updateFlowAppend(flowAppend);
	}

}
