/**
 * @Title: TaskModelParser.java
 * @Description:
 * @Date:2016年12月9日 上午10:22:16
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.parser;

import java.util.List;

import org.w3c.dom.Element;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.model.ExceptionModel;
import cmsz.autoflow.engine.model.FieldModel;
import cmsz.autoflow.engine.model.TaskModel;
import cmsz.autoflow.engine.model.TransitionModel;

/**
 * @ClassName:cmsz.autoflow.engine.parser.TaskModelParser
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class TaskModelParser {

	/**
	 * @Title: parse
	 * @Description:
	 * @param element
	 * @return
	 * @Date:2016年12月9日 上午10:31:31
	 * @Author:LeucotheaShi
	 */
	public static TaskModel parse(Element element) {
		// TODO Auto-generated method stub

		TaskModel taskModel = new TaskModel();

		taskModel.setName(element.getAttribute(ConfigNameConstant.ATTR_NAME));
		taskModel.setLaucher(element.getAttribute(ConfigNameConstant.ATTR_LAUNCHER));
		taskModel.setRefBean(element.getAttribute(ConfigNameConstant.ATTR_REFBEAN));
		taskModel.setRefClass(element.getAttribute(ConfigNameConstant.ATTR_REFCLASS));
		taskModel.setRefDubbo(element.getAttribute(ConfigNameConstant.ATTR_REFDUBBO));
		taskModel.setRefComponent(element.getAttribute(ConfigNameConstant.ATTR_COMPONENT));
		String maxTimesStr = element.getAttribute(ConfigNameConstant.ATTR_MAXTIMES);
		if (maxTimesStr == null || maxTimesStr.isEmpty()) {
			taskModel.setMaxTimes(99);
		} else {
			taskModel.setMaxTimes(Integer.parseInt(maxTimesStr));
		}

		// parse transition model
		List<TransitionModel> transitionList = ChildListParser.getTransitionModelList(element);
		for (TransitionModel transitionModel : transitionList) {
			transitionModel.setSource(taskModel);
		} // for

		taskModel.getOutputs().addAll(transitionList);

		// parse field model
		List<FieldModel> fieldList = ChildListParser.getFieldModelList(element);
		taskModel.setFieldModels(fieldList);

		// parse Exception Model
		List<ExceptionModel> exceptionList = ChildListParser.getExceptionModelList(element);
		for (ExceptionModel exceptionModel : exceptionList) {
			exceptionModel.setSource(taskModel);
		} // for
		taskModel.getOutExceptions().addAll(exceptionList);

		return taskModel;
	}

}
