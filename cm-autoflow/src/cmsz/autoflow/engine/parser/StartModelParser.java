/**
 * @Title: StartModelParser.java
 * @Description:
 * @Date:2016年12月9日 上午10:20:50
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.parser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.model.StartModel;
import cmsz.autoflow.engine.model.TransitionModel;

/**
 * @ClassName:cmsz.autoflow.engine.parser.StartModelParser
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class StartModelParser {

	private static Logger logger = LoggerFactory.getLogger(StartModelParser.class);

	/**
	 * @Title: parse
	 * @Description:
	 * @param element
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @Date:2016年12月9日 上午10:20:50
	 * @Author:LeucotheaShi
	 */
	public static StartModel parse(Element element) {
		// TODO Auto-generated method stub

		StartModel startModel = new StartModel();
		// get startModel's name
		startModel.setName(element.getAttribute(ConfigNameConstant.ATTR_NAME));

		// get startModel's transitionList
		List<TransitionModel> transitionList = ChildListParser.getTransitionModelList(element);
		for (TransitionModel transitionModel : transitionList) {
			transitionModel.setSource(startModel);
		} // for

		// set startMode's outputList
		startModel.getOutputs().addAll(transitionList);

		return startModel;
	}

}
