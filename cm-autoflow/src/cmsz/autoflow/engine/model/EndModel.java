/**
 * @Title: EndModel.java
 * @Description:
 * @Date:2016年12月8日 下午6:10:15
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.model;

import java.util.Collections;
import java.util.List;

import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.handler.impl.EndProcessHandler;

/**
 * @ClassName:cmsz.autoflow.engine.model.EndModel
 * @Description: TODO
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public class EndModel extends NodeModel {

	/**
	 * @Title: EndModel.java
	 * @Description:
	 * @Date:2016年12月8日 下午6:10:24
	 * @Author:LeucotheaShi
	 */
	private static final long serialVersionUID = 1072693245932189788L;

	/**
	 * 
	 * @Title: getOutputs
	 * @Description: EndModel没有输出，直接返回空列表
	 * @return
	 * @Date:2016年12月8日 下午6:11:42
	 * @Author:LeucotheaShi
	 */
	@Override
	public List<TransitionModel> getOutputs() {
		return Collections.emptyList();
	}// getOutputs

	/**
	 * @Title: exec
	 * @Description:
	 * @param execution
	 * @throws Exception
	 * @Date:2016年12月8日 下午6:10:15
	 * @Author:LeucotheaShi
	 */
	@Override
	protected void exec(Execution execution) throws Exception {
		// TODO Auto-generated method stub
		fire(new EndProcessHandler(), execution);
	}

}
