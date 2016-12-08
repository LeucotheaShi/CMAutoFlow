package com.cmsz.cmup.commons.utils;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.sql.CLOB;

/**
 * @ClassName:com.cmsz.cmup.commons.utils.OracleTypeUtil
 * @Description: TODO
 * @Date: Jun 3, 2016
 * @Author: LeucotheaShi
 */
public class OracleTypeUtil {

	/**
	 * 
	 * @Description:
	 * @Date:Jun 3, 2016 3:57:01 PM
	 * @Author:LeucotheaShi
	 */
	public OracleTypeUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @Title: clobToString
	 * @Description: 将CLOB类型转换成String类型
	 * @param clob
	 *            Oracle CLOB类型
	 * @return
	 * @throws Exception
	 * @return：String 返回String类型
	 * @Date:Jun 4, 2016 9:54:23 AM
	 * @Author:LeucotheaShi
	 */
	public String clobToString(CLOB clob) throws Exception {

		// 结果字符串
		String reString = "";

		Reader reader = clob.getCharacterStream();// 得到流
		BufferedReader bufferedReader = new BufferedReader(reader);

		String tempString = bufferedReader.readLine();
		StringBuffer stringBuffer = new StringBuffer();

		while (tempString != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
			stringBuffer.append(tempString);
			tempString = bufferedReader.readLine();
		}

		// 获取结果字符串
		reString = stringBuffer.toString();

		// 返回结果字符串
		return reString;

	}// clobToString

	/**
	 * 
	 * @Title: embodySqlPara
	 * @Description: 将通用的Sql替换成具体的sql，例如 settleDate = {settleDate} 转换成
	 *               settleDate = 16441209
	 * @param genericSql
	 *            通用的sql
	 * @param variableMap
	 *            含有具体的参数
	 * @return
	 * @return：String 具体的sql
	 * @Date:Jun 4, 2016 3:41:54 PM
	 * @Author:LeucotheaShi
	 */
	public String embodySqlPara(String genericSql, Map<String, Object> variableMap) throws Exception {

		Pattern paraPattern = Pattern.compile("\\{.*?\\}");
		Matcher paraMatcher = paraPattern.matcher(genericSql);

		// 存储参数名
		String paraName = "";
		String concreteSql = genericSql;

		while (paraMatcher.find()) {

			paraName = paraMatcher.group().replaceAll("\\{", "").replaceAll("\\}", "");
			concreteSql = concreteSql.replace(paraMatcher.group(), variableMap.get(paraName).toString());

		}// while

		if (concreteSql == null || concreteSql == "") {
			throw new Exception("需要被转换的sql语句为空！");
		}// if
		else {
			// 返回
			return concreteSql;
		}

	}// embodySqlPara

}// OracleTypeUtil
