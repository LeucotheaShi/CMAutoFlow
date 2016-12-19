package com.cmsz.cmup.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 * XML转换工具类
 * 
 * @author zeng.j
 * 
 */
public class XmlStringUtil {
	public static final String UTF8_ENCODING = "UTF-8";

	/**
	 * 支付结果通知查询的返回信息是XML格式的，需要转换成Map
	 * 
	 * @param xmlStr
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String, String> switchXml2Map(String xmlStr) {
		Map<String, String> responseMap = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlStr);
			Element rootE = doc.getRootElement();
			putData(rootE, responseMap);
		} catch (DocumentException e) {
			throw new RuntimeException("str转换成XML的Document时异常"+e.getMessage());
		}
		return responseMap;
	}

	/**
	 * 
	 * @param e
	 * @param map
	 */
	private static void putData(Element e, Map<String, String> map) {
		if (e == null) {
			return;
		}
		for (Object element : e.elements()) {
			Element e1 = (Element) element;
			if (!e1.elements().isEmpty()) {
				putData(e1, map);
			} else if (!e1.attributes().isEmpty()) {
				map.put(e1.attribute(0).getValue().trim(), e1.getTextTrim());
			} else {
				map.put(e1.getName().trim(), e1.getTextTrim());
			}
		}
	}

	/**
	 * url请求的参数转MAP
	 * 
	 * @param queryString
	 *            请求的参数串
	 * @param enc
	 *            编码
	 * @return
	 */
	public static Map<String, String> getParamsMap(String queryString,
			String enc) {
		Map<String, String> paramsMap = new LinkedHashMap<String, String>();
		if (queryString != null && queryString.length() > 0) {
			int ampersandIndex, lastAmpersandIndex = 0;
			String subStr, param, value;
			do {
				ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
				if (ampersandIndex > 0) {
					subStr = queryString.substring(lastAmpersandIndex,
							ampersandIndex - 1);
					lastAmpersandIndex = ampersandIndex;
				} else {
					subStr = queryString.substring(lastAmpersandIndex);
				}

				int index = subStr.indexOf("=");
				param = subStr.substring(0, index);
				value = subStr.length() == index + 1 ? "" : subStr
						.substring(index + 1);
				try {
					value = URLDecoder.decode(value.trim(), enc);
					paramsMap.put(param, value);
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			} while (ampersandIndex > 0);
		}
		return paramsMap;
	}

	/**
	 * 获取xml文本中节点的值
	 * 
	 * @param nodeStart
	 *            节点开始标签 eg :<TransactionID>
	 * @param nodeEnd
	 *            节点结束标签 eg :</TransactionID>
	 * @param src
	 *            原字符串
	 * @return
	 */
	public static String parseNodeValueFromXml(String nodeStart,
			String nodeEnd, String src) {
		int nodeStartLength = nodeStart.length();
		int start = src.indexOf(nodeStart);
		int end = src.indexOf(nodeEnd);
		if (start > -1 && end > -1) {
			return src.substring(start + nodeStartLength, end);
		}
		return "";
	}

	/**
	 * 替换xml中节点的值，只适合替换报文中只有一个指定名字的节点
	 * 
	 * @param nodeStart
	 *            节点开始标签 eg :<TransactionID>
	 * @param nodeEnd
	 *            节点结束标签 eg :</TransactionID>
	 * @param relacement
	 *            节点替换的内容
	 * @param src
	 *            原字符串
	 * @return
	 */
	public static String relaceNodeContent(String nodeStart, String nodeEnd,
			String relacement, String src) {
		int nodeStartLength = nodeStart.length();
		int start = src.indexOf(nodeStart);
		int end = src.indexOf(nodeEnd);

		if (start > -1 && end > -1) {
			String segStart = src.substring(0, start + nodeStartLength);
			String segEnd = src.substring(end, src.length());
			return segStart + relacement + segEnd;
		}
		return src;
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 把Map转换成Http请求参数的键值连接字串，经utf8 encode
	 * 
	 * @param mapData
	 *            要转换的参数
	 * @return
	 * @throws Exception
	 */
	public static String switchMapToHttpReqStr(Map<String, String> mapData) {
		if (mapData == null || mapData.size() == 0) {
			return "";
		}

		StringBuffer sBuffer = new StringBuffer();
		Iterator<String> it = mapData.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			sBuffer.append("&").append(key).append("=")
					.append(mapData.get(key));
		}

		if (sBuffer.length() > 0) {
			try {
				return URLEncoder.encode(sBuffer.substring(1), UTF8_ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("字符编码异常"+e.getMessage());
			}
		}

		return "";
	}

	/**
	 * 去除xmlStr报文中A节点下的数据用B节点替换，主要用于去除“<![CDATA[<?xml version="1.0"
	 * encoding="UTF-8"?>”和 “]]>”
	 * 
	 * @param xmlStr
	 * @param fromSta
	 *            开始节点
	 * @param fromEnd
	 *            结束节点
	 * @param toSta
	 *            开始节点
	 * @param toEnd
	 *            结束节点
	 * @return
	 */
	public static String parseElement(String xmlStr, String fromSta,
			String fromEnd, String toSta, String toEnd) {
		// 替换的数据
		String rspStr = XmlStringUtil.parseNodeValueFromXml(fromSta, fromEnd,
				xmlStr);
		rspStr = fromSta + rspStr + fromEnd;
		// 将xmlStr数据下to节点中的数据用中rspStr数据替换
		String newXmlStr = XmlStringUtil.relaceNodeContent(toSta, toEnd,
				rspStr, xmlStr);
		return newXmlStr;
	}

	/**
	 * 去除报文中的空格、回车、换行符、制表符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
			dest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + dest.substring(36);
		}
		return dest;
	}
}
