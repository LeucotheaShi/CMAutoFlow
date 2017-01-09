package cmsz.autoflow.engine.core;

import java.util.List;
import java.util.Map;

import cmsz.autoflow.engine.model.ExceptionModel;

public interface DelegateTask {

	public String getId();

	public String getFlowId();

	public String getName();

	public String getCreateTime();

	public String getVariables();

	public String getStatus();

	public String getFlowName();

	public String getProcessId();

	public String getDubboId();

	public String getRefBean();

	public String getComponentId();

	public void updateVariables(Map<String, Object> args);

	public void putUpdateVariables(String key, Object value);

	public List<ExceptionModel> getOutExceptions();

	public List<ExceptionModel> getInExceptions();

	public int getCurrentTimes();

	public int getMaxTimes();

	public void addRunTimes(int times);

}
