/**
 * @Title: ForkModelParser.java
 * @Description:
 * @Date:2016年12月9日 上午10:21:33
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.parser;

import java.util.List;

import org.w3c.dom.Element;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.model.ForkModel;
import cmsz.autoflow.engine.model.TransitionModel;

/**
 * @ClassName:cmsz.autoflow.engine.parser.ForkModelParser
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class ForkModelParser {

	/**
	 * @Title: parse
	 * @Description:
	 * @param element
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @Date:2016年12月9日 上午10:21:33
	 * @Author:LeucotheaShi
	 */

	public static ForkModel parse(Element element) {
		// TODO Auto-generated method stub

		ForkModel forkModel = new ForkModel();

		// get name
		forkModel.setName(element.getAttribute(ConfigNameConstant.ATTR_NAME));

		// get transition list
		List<TransitionModel> transitionModelList = ChildListParser.getTransitionModelList(element);
		for (TransitionModel transitionModel : transitionModelList) {
			transitionModel.setSource(forkModel);
		} // for

		// get outputs
		forkModel.getOutputs().addAll(transitionModelList);

		return forkModel;
	}

}
