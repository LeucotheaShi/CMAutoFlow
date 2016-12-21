package cmsz.autoflow.engine.handler.impl;

import cmsz.autoflow.engine.constant.Constant;
import cmsz.autoflow.engine.core.AutoEngine;
import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.entity.Flow;
import cmsz.autoflow.engine.handler.IHandler;

public class EndProcessHandler implements IHandler {

	@Override
	public void handle(Execution execution) {
		Flow flow=execution.getFlow();
		AutoEngine engine=execution.getEngine();
		engine.flow().update(flow.getId(), Constant.State.FINISH);
	}

}
