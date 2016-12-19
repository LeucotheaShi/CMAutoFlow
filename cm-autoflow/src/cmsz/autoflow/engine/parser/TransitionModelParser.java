/**
 * @Title: TransitionModelParser.java
 * @Description:
 * @Date:2016年12月9日 上午10:23:11
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.parser;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.model.TransitionModel;

/**
 * @ClassName:cmsz.autoflow.engine.parser.TransitionModelParser
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class TransitionModelParser {

	private static Logger logger = LoggerFactory.getLogger(TransitionModelParser.class);

	/**
	 * @Title: parse
	 * @Description:将xml element 解析成 TransitionModel 实例
	 * @param element
	 * @return
	 * @Date:2016年12月9日 上午10:23:11
	 * @Author:LeucotheaShi
	 */
	public static TransitionModel parse(Element element) {
		// TODO Auto-generated method stub

		TransitionModel transitionModel = new TransitionModel();

		transitionModel.setName(element.getAttribute(ConfigNameConstant.ATTR_NAME));
		transitionModel.setTo(element.getAttribute(ConfigNameConstant.ATTR_TO));

		logger.debug("parsed transitionModel : " + transitionModel.toString());

		return transitionModel;
	}

	public List<TransitionModel> getTransitionModelList(Element elem) {

		List<TransitionModel> transitionList = new ArrayList<TransitionModel>();

		return transitionList;

	}// getTransitionModelList

}
