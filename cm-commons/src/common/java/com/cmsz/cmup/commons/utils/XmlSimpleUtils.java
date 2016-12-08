package com.cmsz.cmup.commons.utils;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml处理
 * @author vivi207
 *
 */
public class XmlSimpleUtils {

	/**
	 * 遍历节点加载到Map
	 * @param el
	 * @return
	 */
	public static Map nodeToMap(Element el) {
		return nodeToMap(el, null);
	}

	/**
	 * 遍历节点加载到Map
	 * @param el
	 * @param data
	 * @return
	 */
	public static Map nodeToMap(Element el, Map data) {
		if (el == null)
			return null;
		if (data == null) {
			data = new LinkedHashMap();
		}
		List<Element> childNodes = el.elements();
		if (childNodes.size() == 0) {
			data.put(el.getName(), el.getText());
		} else {
			Map cdata = new LinkedHashMap();
			for (Element e : childNodes) {
				nodeToMap(e, cdata);
			}
			data.put(el.getName(), cdata);
		}
		return data;
	}

	/**
	 * xml字符串转Map
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Map xmlToMap(String xml) throws DocumentException {
		Document doc = DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		Map map = nodeToMap(root);
		return map;
	}
	
	/**
	 * xml流转Map
	 * @param in
	 * @return
	 * @throws DocumentException
	 */
	public static Map xmlToMap(InputStream in) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(in);;
		Element root = doc.getRootElement();
		Map map = nodeToMap(root);
		return map;
	}
	
	/**
	 * xml文件转Map
	 * @param in
	 * @return
	 * @throws DocumentException
	 */
	public static Map xmlToMap(File file) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);;
		Element root = doc.getRootElement();
		Map map = nodeToMap(root);
		return map;
	}
}
