/**
 * @Title: ForkModel.java
 * @Description:
 * @Date:2016年12月8日 下午6:13:56
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import cmsz.autoflow.engine.core.Execution;

/**
 * @ClassName:cmsz.autoflow.engine.model.ForkModel
 * @Description: TODO
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public class ForkModel extends NodeModel {

	/**
	 * @Title: ForkModel.java
	 * @Description:
	 * @Date:2016年12月8日 下午6:14:04
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = -3149030224257840611L;

	/**
	 * @Title: exec
	 * @Description:
	 * @param execution
	 * @Date:2016年12月8日 下午6:13:56
	 * @Author:LeucotheaShi
	 */
	@Override
	protected void exec(Execution execution) {
		// TODO Auto-generated method stub
		runOutTransition(execution);
	}

}
