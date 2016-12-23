/**
 * @Title: IFlowService.java
 * @Description:
 * @Date:2016年12月10日 下午7:58:36
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service;

import java.util.List;
import java.util.Map;

import cmsz.autoflow.engine.entity.Flow;
import cmsz.autoflow.engine.entity.FlowAppend;
import cmsz.autoflow.engine.entity.Process;

/**
 * @ClassName:cmsz.autoflow.engine.service.IFlowService
 * @Description: TODO
 * @Date: 2016年12月10日
 * @Author: LeucotheaShi
 */
public interface IFlowService {

	/**
	 * 
	 * @Title: createFlow
	 * @Description:根据流程定义和参数创建流程实例,并在数据库中插入数据.
	 * @param process
	 * @param args
	 * @return
	 * @return：Flow
	 * @Date:2016年12月10日 下午8:06:24
	 * @Author:LeucotheaShi
	 */
	Flow createFlow(Process process, Map<String, Object> args);

	/**
	 * 
	 * @Title: saveFlow
	 * @Description:将流程实例保存到数据库
	 * @param flow
	 * @return：void
	 * @Date:2016年12月10日 下午8:05:59
	 * @Author:LeucotheaShi
	 */
	void saveFlow(Flow flow);

	/**
	 * 
	 * @Title: update
	 * @Description:根据流程实例的ID，更新状态
	 * @param flowId
	 * @param state
	 * @return：void
	 * @Date:2016年12月10日 下午8:05:15
	 * @Author:LeucotheaShi
	 */
	void update(String flowId, String state);

	/**
	 * 
	 * @Title: getFlow
	 * @Description: 从流程实例表中读出数据
	 * @param flowId：流程实例id
	 * @return
	 * @return：Flow
	 * @Date:2016年12月10日 下午8:04:52
	 * @Author:LeucotheaShi
	 */
	Flow getFlow(String flowId);

	/**
	 * 
	 * @Title: getFlow
	 * @Description:
	 * @param flowName
	 *            流程名称
	 * @param createDate
	 *            创建时间 例如2016-12-10
	 * @return
	 * @return：List<Flow>
	 * @Date:2016年12月10日 下午8:01:32
	 * @Author:LeucotheaShi
	 */
	List<Flow> getFlow(String flowName, String createDate);

	/**
	 * 
	 * @Title: createFlow
	 * @Description: 创建一个工作流实例
	 * @param process
	 * @param args
	 * @param flowId
	 * @param flowName
	 * @return
	 * @return：Flow
	 * @Date:2016年12月10日 下午8:01:41
	 * @Author:LeucotheaShi
	 */
	Flow createFlow(Process process, Map<String, Object> args, String flowId, String flowName);
	
	/**
	 * 
	 * @Title: updateFlowAppend
	 * @Description: 更新一个工作流实例
	 * @param flowAppend
	 * @return void
	 * @Date:2016年12月10日 下午8:01:41
	 * @Author:MichaelZhenglihua
	 */
	void updateFlowAppend(FlowAppend flowAppend);

}// IFlowService
