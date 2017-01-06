/**
 * @Title: FieldModelParser.java
 * @Description:
 * @Date:2016年12月9日 上午10:27:09
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.model.FieldModel;

/**
 * @ClassName:cmsz.autoflow.engine.parser.FieldModelParser
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class FieldModelParser {
	private static Logger logger = LoggerFactory.getLogger(FieldModelParser.class);

	/**
	 * @Title: parse
	 * @Description:
	 * @param element
	 * @return
	 * @Date:2016年12月9日 上午10:27:09
	 * @Author:LeucotheaShi
	 */

	public static FieldModel parse(Element element) {
		// TODO Auto-generated method stub

		FieldModel fieldModel = new FieldModel();
		fieldModel.setKey(element.getAttribute(ConfigNameConstant.ATTR_KEY));
		fieldModel.setValue(element.getAttribute(ConfigNameConstant.ATTR_VALUE));

		logger.debug("parsed transitionModel : (" + fieldModel.getKey() + "," + fieldModel.getValue() + ")");

		return fieldModel;
	}

}
