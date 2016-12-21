package cmsz.autoflow.engine.handler.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmsz.autoflow.engine.constant.Constant;
import cmsz.autoflow.engine.core.AutoEngine;
import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.entity.Flow;
import cmsz.autoflow.engine.entity.Task;
import cmsz.autoflow.engine.handler.IHandler;
import cmsz.autoflow.engine.model.JoinModel;
import cmsz.autoflow.engine.model.TransitionModel;

public class MergeBranchHandler implements IHandler {

	private static final Logger logger=LoggerFactory.getLogger(MergeBranchHandler.class);
	
	private JoinModel model;
	
	private boolean isMerge = true;

	public MergeBranchHandler(JoinModel model) {
		this.model = model;
	}

	public boolean isMerged() {
		return this.isMerge;
	}

	@Override
	public void handle(Execution execution) {
		List<TransitionModel> list = model.getInputs();
		AutoEngine engine = execution.getEngine();
		Flow flow = execution.getFlow();
		for (TransitionModel tm : list) {
			Task task = engine.task().getTask(flow.getId(),
					tm.getSource().getName());
			
			logger.info(tm.getSource().getName() +" task:"+task);
			if (task == null ||  !task.getStatus().equals(Constant.State.SUCCESS)) {
				this.isMerge = false;
				return;
			}
		}
	}

}
