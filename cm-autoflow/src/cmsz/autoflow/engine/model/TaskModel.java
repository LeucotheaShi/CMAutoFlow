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

	private int maxTimes;

	/**
	 * 
	 * @Description:
	 * @Date:2016年12月28日 下午4:41:39
	 * @Author:LeucotheaShi
	 */
	public TaskModel() {
		// TODO Auto-generated constructor stub
		super();
		// 设置默认值
		this.maxTimes = 99;
	}

	/**
	 * @Title: exec
	 * @Description:
	 * @param execution
	 * @throws Exception
	 * @Date:2016年12月8日 下午6:14:57
	 * @Author:LeucotheaShi
	 */
	@Override
	protected void exec(Execution execution) throws Exception {
		// TODO Auto-generated method stub
		runOutTransition(execution);

	}

	/**
	 * @return the maxTimes
	 */
	public int getMaxTimes() {
		return maxTimes;
	}

	/**
	 * @param maxTimes
	 *            the maxTimes to set
	 */
	public void setMaxTimes(int maxTimes) {
		this.maxTimes = maxTimes;
	}

}
