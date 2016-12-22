/**
 * @Title: TaskModel.java
 * @Description:
 * @Date:2016年12月8日 下午6:14:57
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import cmsz.autoflow.engine.core.Execution;

/**
 * @ClassName:cmsz.autoflow.engine.model.TaskModel
 * @Description: TODO
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public class TaskModel extends NodeModel {

	/**
	 * @Title: TaskModel.java
	 * @Description:
	 * @Date:2016年12月8日 下午6:15:03
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 3049007863852997099L;

	/**
	 * @Title: exec
	 * @Description:
	 * @param execution
	 * @Date:2016年12月8日 下午6:14:57
	 * @Author:LeucotheaShi
	 */
	@Override
	protected void exec(Execution execution) {
		// TODO Auto-generated method stub
		runOutTransition(execution);

	}

}
