package com.cmsz.cmup.commons.utils;

import java.io.StringReader;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;


/**
 * 获得XML文件
 * @author lxf
 *
 */
public class GetXmlFile {

	private String fileName = "";
	private Document document = null;

	/**
	 * @author cmt
	 * @param fileName
	 * @throws DocumentException 
	 */
	public GetXmlFile(String fileName) throws DocumentException {
		URL fileUrl=GetXmlFile.class.getResource(fileName);
		if (fileUrl!=null){
			this.fileName = GetXmlFile.class.getResource(fileName).toString();
		}
		else{
			this.fileName=fileName;
		}
		
		try {
			SAXReader reader = new SAXReader();
			this.document = reader.read(this.fileName);
		} catch (DocumentException e) {
			throw e;
		}
	}

	public GetXmlFile(){}
	
	
	
	/**
	 * 设置XML格式的字符串
	 * @author cmt
	 * @param xmlString
	 * @return
	 * @throws DocumentException 
	 */
	public Document setXmlString(String xmlString) throws DocumentException{
		StringReader sr = new StringReader(xmlString);   
		InputSource is = new InputSource(sr); 
		
		SAXReader reader = new SAXReader();
		try {
			this.document =reader.read(is);
		}
		catch (DocumentException e) {
			throw e;
		}
		return this.document;
	}
	
	public Document GetDocument() {
		return this.document;
	}

	@SuppressWarnings("unchecked")
	public List<Element> GetListByXpath(String xpath) {
		return this.document.selectNodes(xpath);
	}
	public Element GetElementByXpath(String xpath) {
		return (Element) this.document.selectSingleNode(xpath);
	}
}
