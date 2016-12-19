/**
 * @Title: EndModelParser.java
 * @Description:
 * @Date:2016年12月9日 上午10:21:13
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.parser;

import org.w3c.dom.Element;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.model.EndModel;

/**
 * @ClassName:cmsz.autoflow.engine.parser.EndModelParser
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class EndModelParser {

	/**
	 * @Title: parse
	 * @Description:
	 * @param element
	 * @return
	 * @Date:2016年12月9日 上午10:21:13
	 * @Author:LeucotheaShi
	 */

	public static EndModel parse(Element element) {
		// TODO Auto-generated method stub

		EndModel endModel = new EndModel();

		// get name
		endModel.setName(element.getAttribute(ConfigNameConstant.ATTR_NAME));

		return endModel;
	}

}
