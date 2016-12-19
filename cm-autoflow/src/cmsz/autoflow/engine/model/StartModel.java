/**
 * @Title: StartModel.java
 * @Description:
 * @Date:2016年12月8日 下午6:04:23
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import java.util.Collections;
import java.util.List;

import cmsz.autoflow.engine.core.Execution;

/**
 * @ClassName:cmsz.autoflow.engine.model.StartModel
 * @Description: TODO
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public class StartModel extends NodeModel {

	/**
	 * @Title: StartModel.java
	 * @Description:
	 * @Date:2016年12月8日 下午6:04:29
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 2589817549044882891L;

	/**
	 * 
	 * @Title: getInputs
	 * @Description:StartModel是没有输入列表的
	 * @return
	 * @Date:2016年12月8日 下午6:09:09
	 * @Author:LeucotheaShi
	 */
	@Override
	public List<TransitionModel> getInputs() {
		return Collections.emptyList();
	}// getInputs

	/**
	 * @Title: exec
	 * @Description:
	 * @param execution
	 * @Date:2016年12月8日 下午6:06:26
	 * @Author:LeucotheaShi
	 */
	@Override
	protected void exec(Execution execution) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title: execute
	 * @Description:
	 * @param execution
	 * @Date:2016年12月8日 下午6:04:23
	 * @Author:LeucotheaShi
	 */

}
