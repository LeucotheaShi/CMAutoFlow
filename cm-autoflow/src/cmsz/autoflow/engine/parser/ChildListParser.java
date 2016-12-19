/**
 * @Title: NextListParser.java
 * @Description:
 * @Date:2016年12月9日 上午11:07:04
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.parser;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.model.ExceptionModel;
import cmsz.autoflow.engine.model.FieldModel;
import cmsz.autoflow.engine.model.TransitionModel;

/**
 * @ClassName:cmsz.autoflow.engine.parser.NextListParser
 * @Description: 解析每个node的下级标签
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class ChildListParser {

	public static List<TransitionModel> getTransitionModelList(Element element) {

		List<TransitionModel> transitionModelList = new ArrayList<TransitionModel>();
		List<Element> elementModelList = traverseChildList(element, ConfigNameConstant.ELEM_TRAN);

		// 逐个解析elementModelList中的对象
		int elementModelListSize = elementModelList.size();
		for (int i = 0; i < elementModelListSize; i++) {

			transitionModelList.add(TransitionModelParser.parse(elementModelList.get(i)));

		} // for

		return transitionModelList;
	}// getTransitionModelList

	public static List<FieldModel> getFieldModelList(Element element) {

		List<FieldModel> fieldModelList = new ArrayList<FieldModel>();
		List<Element> elementModelList = traverseChildList(element, ConfigNameConstant.ATTR_FIELD);

		// 逐个解析elementModelList中的对象
		int elementModelListSize = elementModelList.size();
		for (int i = 0; i < elementModelListSize; i++) {

			fieldModelList.add(FieldModelParser.parse(elementModelList.get(i)));

		} // for

		return fieldModelList;
	}// getTransitionModelList

	public static List<ExceptionModel> getExceptionModelList(Element element) {

		List<ExceptionModel> exceptionModelModelList = new ArrayList<ExceptionModel>();
		List<Element> elementModelList = traverseChildList(element, ConfigNameConstant.ATTR_FIELD);

		// 逐个解析elementModelList中的对象
		int elementModelListSize = elementModelList.size();
		for (int i = 0; i < elementModelListSize; i++) {

			exceptionModelModelList.add(ExceptionModelParser.parse(elementModelList.get(i)));

		} // for

		return exceptionModelModelList;
	}// getTransitionModelList

	/**
	 * 
	 * @Title: traverseChildList
	 * @Description: 遍历子节点Element列表，筛选所需类型的节点集合
	 * @param element
	 * @param childType
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @return：List<Element>
	 * @Date:2016年12月9日 下午1:03:14
	 * @Author:LeucotheaShi
	 */
	public static List<Element> traverseChildList(Element element, String childType) {

		List<Element> elementList = new ArrayList<Element>();

		// 获取该节点的子节点列表
		NodeList nodeList = element.getChildNodes();

		int nodeSize = nodeList.getLength();

		for (int i = 0; i < nodeSize; i++) {

			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element childElement = (Element) node;

				if (childElement.getTagName().equals(childType)) {
					// 如果是childType类型的节点则加入节点列表
					elementList.add(childElement);
				}
			} // if

		} // for

		return elementList;

	}// traverseChildList

}
