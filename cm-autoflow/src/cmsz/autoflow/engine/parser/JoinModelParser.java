/**
 * @Title: JoinModelParser.java
 * @Description:
 * @Date:2016年12月9日 上午10:21:59
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.parser;

import java.util.List;

import org.w3c.dom.Element;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.model.JoinModel;
import cmsz.autoflow.engine.model.TransitionModel;

/**
 * @ClassName:cmsz.autoflow.engine.parser.JoinModelParser
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class JoinModelParser {

	/**
	 * @Title: parse
	 * @Description:
	 * @param element
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @Date:2016年12月9日 上午10:21:59
	 * @Author:LeucotheaShi
	 */
	public static JoinModel parse(Element element) {
		// TODO Auto-generated method stub
		JoinModel joinModel = new JoinModel();

		joinModel.setName(element.getAttribute(ConfigNameConstant.ATTR_NAME));

		List<TransitionModel> transitionList = ChildListParser.getTransitionModelList(element);
		for (TransitionModel transitionModel : transitionList) {
			transitionModel.setSource(joinModel);
		} // for

		joinModel.getOutputs().addAll(transitionList);

		return joinModel;
	}

}
