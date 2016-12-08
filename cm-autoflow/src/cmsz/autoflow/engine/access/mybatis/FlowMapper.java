package cmsz.autoflow.engine.access.mybatis;

import java.util.List;
import java.util.Map;

import cmsz.autoflow.engine.entity.Flow;

public interface FlowMapper {

	public void insertFlow(Flow flow);

	public Flow selectFlowById(String flowId);

	public void updateFlowStatus(Flow flow);

	public List<Flow> getFlowSelective(Map<String, Object> paraMap);

}
