/**
 * @Title: JoinModel.java
 * @Description:
 * @Date:2016年12月8日 下午6:14:23
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.handler.impl.MergeBranchHandler;

/**
 * @ClassName:cmsz.autoflow.engine.model.JoinModel
 * @Description: TODO
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public class JoinModel extends NodeModel {

	/**
	 * @Title: JoinModel.java
	 * @Description:
	 * @Date:2016年12月8日 下午6:14:30
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 1745773219375655386L;

	/**
	 * @Title: exec
	 * @Description:
	 * @param execution
	 * @throws Exception
	 * @Date:2016年12月8日 下午6:14:23
	 * @Author:LeucotheaShi
	 */
	@Override
	synchronized protected void exec(Execution execution) throws Exception {
		// TODO Auto-generated method stub
		MergeBranchHandler handler = new MergeBranchHandler(this);

		System.out.println("%%%%%% handler hash " + handler.hashCode());
		System.out.println("%%%%%% handler hash " + this.hashCode());

		fire(handler, execution);// call handle method

		// if (handler.isMerged()) {
		// // handler.setIsRun(true);
		// runOutTransition(execution);
		// }
		if (handler.isMerged() && !handler.isRun()) {
			handler.setIsRun(true);
			runOutTransition(execution);
		}

	}

}
