package com.cmsz.cmup.commons.utils.constant;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cmsz.cmup.commons.logging.system.SystemLogHandler;


/**
 * 业务线代码与对应的应用名称英文简称
 * @author u
 *
 */
public class BusinessLineMapConstant {
	
	/**
	 * log
	 */
	private static final SystemLogHandler SYS_LOGGER = SystemLogHandler.getLogger(BusinessLineMapConstant.class);
	
	/**
	 * 业务线代码与对应的应用名称英文简称
	 */
	private static Map<String, String> businessAppMap= getBusinessAppMap();

	/*
	static{
		//以下只有0068业务线值为明确确定的
		businessAppMap.put("0051", "UPAY");//cmosp:浙江运营中心业务
		businessAppMap.put("0062", "UPAY");//互联网返利
		businessAppMap.put("0064", "UPAY");//咪咕
		businessAppMap.put("0065", "UPAY");//在线服务公司
		businessAppMap.put("0068", "ZXJF");//咪咕在线计费
		businessAppMap.put("0069", "UPAY");//cmosp:互联网支付
	}
	*/
	
	/** 
	 * 根据业务线代码返回业务线对应的应用名称简称
	 * @param businessLine
	 * @return　对应业务线的应用名称，如果找不到则返回业务线本身
	 */
	public static String getAppName(final String businessLine) {
		String businessLineName =  businessAppMap.get(businessLine);
		if (StringUtils.isEmpty(businessLineName)) {
			return businessLine;
		}
		return businessLineName;
	}
	
	/**
	 * 解析busiLineAppConf.xml得到业务线与应用名称的对应关系
	 * busiLineAppConf.xml存在于各个模块的应用classpath下
	 * @return Map<业务线,应用名称>
	 * @throws DocumentException
	 */
	public static  Map<String, String> getBusinessAppMap() {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		try {
			InputStream in = BusinessLineMapConstant.class.getClassLoader().getResourceAsStream("busiLineAppConf.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			List<Element> list = (List<Element>)root.elements("busiLine");
			for(Element e:list) {
			   String code = e.elementText("code");
			   Element elementApp = e.element("app");
			   String appName = elementApp.elementText("name");
			   map.put(code, appName);
			}
		} catch (DocumentException e) {
			SYS_LOGGER.error("解析busiLineAppConf.xml文件时发生异常。",e);
		}
		return map;
	} 
	
}
