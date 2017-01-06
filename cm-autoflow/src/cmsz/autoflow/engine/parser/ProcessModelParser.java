/**
 * @Title: ProcessModelParser.java
 * @Description:
 * @Date:2016年12月9日 上午10:33:23
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.parser;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.helper.XmlHelper;
import cmsz.autoflow.engine.model.NodeModel;
import cmsz.autoflow.engine.model.ProcessModel;
import cmsz.autoflow.engine.model.TransitionModel;

/**
 * @ClassName:cmsz.autoflow.engine.parser.ProcessModelParser
 * @Description: TODO
 * @Date: 2016年12月9日
 * @Author: LeucotheaShi
 */
public class ProcessModelParser {

	private static Logger logger = LoggerFactory.getLogger(ProcessModelParser.class);

	/**
	 * @Title: parse
	 * @Description:
	 * @param element
	 * @return
	 * @Date:2016年12月9日 上午10:33:23
	 * @Author:LeucotheaShi
	 */

	public static ProcessModel parse(byte[] bytes) {
		// TODO Auto-generated method stub

		DocumentBuilder documentBuilder = XmlHelper.createDocumentBuilder();
		if (documentBuilder != null) {
			Document document = null;
			try {
				// create document
				document = documentBuilder.parse(new ByteArrayInputStream(bytes));
				// get root element
				Element processElement = document.getDocumentElement();

				ProcessModel processModel = new ProcessModel();
				// get root element attributes
				processModel.setName(processElement.getAttribute(ConfigNameConstant.ATTR_NAME));
				processModel.setId(processElement.getAttribute(ConfigNameConstant.ATTR_ID));

				// get process's child nodes
				NodeList nodeList = processElement.getChildNodes();

				// parse each child node
				int nodeListSize = nodeList.getLength();
				for (int i = 0; i < nodeListSize; i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						NodeModel childNodeModel = parseChildNode(node);
						processModel.getNodes().add(childNodeModel);

					} // if
				} // for

				// set each child node's inputs
				// set each transition node's target
				for (NodeModel node1 : processModel.getNodes()) {
					List<TransitionModel> transitions = node1.getOutputs();
					for (TransitionModel transition : transitions) {
						String to = transition.getTo();
						for (NodeModel node2 : processModel.getNodes()) {
							if (node2.getName().equals(to)) {
								transition.setTarget(node2);
								node2.getInputs().add(transition);
							} // if
						} // for
					} // for
				} // for

				return processModel;

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // if

		return null;
	}// parse

	private static NodeModel parseChildNode(Node node) {

		// get the node's nodeName,such as task,start,end.
		String nodeName = node.getNodeName();
		logger.debug("parsing {} node . ", nodeName);

		Element element = (Element) node;
		switch (nodeName) {
		case ConfigNameConstant.ELEM_START:
			return StartModelParser.parse(element);
		case ConfigNameConstant.ELEM_END:
			return EndModelParser.parse(element);
		case ConfigNameConstant.ELEM_FORK:
			return ForkModelParser.parse(element);
		case ConfigNameConstant.ELEM_JOIN:
			return JoinModelParser.parse(element);
		case ConfigNameConstant.ELEM_TASK:
			return TaskModelParser.parse(element);
		default:
			logger.debug("there is not {} element type.", nodeName);
			return null;

		}// switch

	}// parseChildNode

}// ProcessModelParser
