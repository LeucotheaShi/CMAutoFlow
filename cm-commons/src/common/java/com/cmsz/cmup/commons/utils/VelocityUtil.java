package com.cmsz.cmup.commons.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.NumberTool;

import com.cmsz.cmup.commons.logging.system.SystemLogHandler;

/**
 * velocity相关工具类
 * 
 * 
 * @author yaoQingCan
 * @version 创建时间：2016年1月28日 下午2:04:37
 */
public class VelocityUtil {

	private static SystemLogHandler systemLogger = SystemLogHandler.getLogger(VelocityUtil.class);

	private VelocityEngine ve;

	public VelocityUtil() {
		super();
		init();
	}

	public void init() {
		// 初始化模板引擎
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
		ve.setProperty("runtime.log.logsystem.log4j.category", "velocity");
		ve.setProperty("runtime.log.logsystem.log4j.logger", "velocity");
		ve.setProperty("runtime.log.error.stacktrace", false);
		ve.setProperty("runtime.log.warn.stacktrace", false);
		ve.setProperty("runtime.log.info.stacktrace", false);
		ve.setProperty("userdirective", "com.cmsz.cmup.commons.utils.FixLengthDirective," + "com.cmsz.cmup.commons.utils.TransReturnCodeDirective");
		ve.init();
	}

	/**
	 * 
	 * @Title: getStringFromVelocity
	 * @Description: 将contextMap中的内容按templePath中的格式生成字符串
	 * @param templePath
	 *            velocity模板的路径
	 * @param contextMap
	 *            模板的入口参数
	 * @return：String 模板生成的目标字符串
	 * @Date:Dec 23, 2015 9:30:47 AM
	 * @Author:LeucotheaShi
	 */
	public String getStringFromVelocity(String templePath, Map<String, String> contextMap) {

		// 获取模板
		Template template = ve.getTemplate(templePath);

		// 实例化字符编写器和模板上下文
		StringWriter sWriter = new StringWriter();
		VelocityContext context = new VelocityContext(contextMap);

		// 合并字符编写器和模板上下文
		template.merge(context, sWriter);

		// 生成结果字符串
		String resString = sWriter.toString();

		// 返回结果字符串
		return resString;
	}

	/**
	 * 
	 * @Title: writeContentIntoFile
	 * @Description:将list中的一段内容写到文件中
	 * @param fileToBeWritten
	 *            目标文件
	 * @param fileAppend
	 *            是否以追加的形式写入文件中，true：追加；false：从头写
	 * @param contentList
	 *            内容集合
	 * @param startIndex
	 *            写入文件的内容起点
	 * @param endIndex
	 *            写入文件的内容终点
	 * @throws IOException
	 * @return：File 已写入内容的文件
	 * @Date:Dec 23, 2015 10:01:07 AM
	 * @Author:LeucotheaShi
	 */
	public File writeContentIntoFile(File fileToBeWritten, boolean fileAppend, List<String> contentList, int startIndex, int endIndex)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {

		try (
		// 文件输出流
		// 将文件头写入文件
		// 写入文件头的时候，从文件头部开始写
		FileOutputStream fileOutputStream = new FileOutputStream(fileToBeWritten, fileAppend);
				// 输出流编写器
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
				// 缓冲编写器
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) // try
		{

			for (int i = startIndex; i < endIndex; i++) {

				String fileBody = contentList.get(i);

				bufferedWriter.write(fileBody);// 写入文件头
				bufferedWriter.flush();// 刷新缓冲
			}// for file body
		} catch (FileNotFoundException | UnsupportedEncodingException exception) {
			// 记录日志
			systemLogger.error("文件内容写入文件 " + fileToBeWritten.getName() + " 时异常，可能是目标文件没有找到或者是编码格式不支持，请进一步查找原因。", exception);
			// 向上抛出异常
			throw exception;
		} catch (IOException ioException) {
			// 记录日志
			systemLogger.error("文件内容写入文件 " + fileToBeWritten.getName() + " 时异常，可能是文件流异常，请进一步查找原因。", ioException);
			// 向上抛出异常
			throw ioException;
		}// catch

		// 返回结果
		return fileToBeWritten;

	}// writeContentIntoFile

	/**
	 * 
	 * @Title: writeContentIntoFile
	 * @Description:将list中的所有内容写到文件中
	 * @param fileToBeWritten
	 *            目标文件
	 * @param fileAppend
	 *            是否以追加的形式写入文件中，true：追加；false：从头写
	 * @throws IOException
	 * @return：File 已写入内容的文件
	 * @Date:Dec 23, 2015 10:01:31 AM
	 * @Author:LeucotheaShi
	 */
	public File writeContentIntoFile(File fileToBeWritten, boolean fileAppend, List<String> contentList) throws UnsupportedEncodingException, FileNotFoundException,
			IOException {

		try (
		// 文件输出流
		// 将文件头写入文件
		// 写入文件头的时候，从文件头部开始写
		FileOutputStream fileOutputStream = new FileOutputStream(fileToBeWritten, fileAppend);
				// 输出流编写器
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
				// 缓冲编写器
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) // try
		{

			for (int i = 0; i < contentList.size(); i++) {

				String fileBody = contentList.get(i);

				bufferedWriter.write(fileBody);// 写入文件头
				bufferedWriter.flush();// 刷新缓冲
			}// for file body

		} catch (FileNotFoundException | UnsupportedEncodingException exception) {
			// 记录日志
			systemLogger.error("文件内容写入文件 " + fileToBeWritten.getName() + " 时异常，可能是目标文件没有找到或者是编码格式不支持，请进一步查找原因。", exception);
			// 向上抛出异常
			throw exception;
		} catch (IOException ioException) {
			// 记录日志
			systemLogger.error("文件内容写入文件 " + fileToBeWritten.getName() + " 时异常，可能是文件流异常，请进一步查找原因。", ioException);
			// 向上抛出异常
			throw ioException;
		}// catch

		return fileToBeWritten;

	}// writeContentIntoFile

	/**
	 * 
	 * @Title: writeContentIntoFile
	 * @Description:将一个字符串写到文件中
	 * @param fileToBeWritten
	 *            目标文件
	 * @param fileAppend
	 *            是否以追加的形式写入文件中，true：追加；false：从头写
	 * @throws IOException
	 * @return：File 已写入内容的文件
	 * @Date:Dec 23, 2015 10:01:31 AM
	 * @Author:LeucotheaShi
	 */
	public File writeContentIntoFile(File fileToBeWritten, boolean fileAppend, String content) throws UnsupportedEncodingException, FileNotFoundException, IOException {

		try (
		// 文件输出流
		// 将文件头写入文件
		// 写入文件头的时候，从文件头部开始写
		FileOutputStream fileOutputStream = new FileOutputStream(fileToBeWritten, fileAppend);
				// 输出流编写器
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
				// 缓冲编写器
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) // try
		{

			bufferedWriter.write(content);// 写入文件
			bufferedWriter.flush();// 刷新缓冲
		} catch (FileNotFoundException | UnsupportedEncodingException exception) {
			// 记录日志
			systemLogger.error("文件内容写入文件 " + fileToBeWritten.getName() + " 时异常，可能是目标文件没有找到或者是编码格式不支持，请进一步查找原因。", exception);
			// 向上抛出异常
			throw exception;
		} catch (IOException ioException) {
			// 记录日志
			systemLogger.error("文件内容写入文件 " + fileToBeWritten.getName() + " 时异常，可能是文件流异常，请进一步查找原因。", ioException);
			// 向上抛出异常
			throw ioException;
		}// catch

		return fileToBeWritten;

	}// writeContentIntoFile

	/**
	 * 
	 * @Title: writeFileByVelocity
	 * @Description:
	 * @param fileTemplePath
	 * @param fileDataMaps
	 * @param fileToBeWritten
	 * @return：void
	 * @Date:Dec 24, 2015 7:47:31 PM
	 * @Author:LeucotheaShi
	 */
	public File writeFileByVelocity(String fileTemplePath, Map<String, String> fileHeadTailMap, List<Map<String, Object>> fileDataMaps, String fileToBeWrittenPath,
			int fileDataStartIndex, int fileDataEndIndex) throws IOException {

		// 获取模板
		Template template = ve.getTemplate(fileTemplePath);

		// 实例化字符编写器和模板上下文
		VelocityContext context = new VelocityContext(fileHeadTailMap);

		// 创建文件路径
		// 拆分文件夹路径和文件名
		String fileTempPath = fileToBeWrittenPath.substring(0, fileToBeWrittenPath.lastIndexOf(File.separator));
		File fileTempPathDir = new File(fileTempPath);
		// 若文件夹不存在则一次创建多级路径
		if (!fileTempPathDir.isDirectory()) {
			fileTempPathDir.mkdirs();
			systemLogger.info("[成功]创建暂存路径成功，路径为：" + fileTempPathDir.getAbsolutePath());
		}// if
		else {
			systemLogger.info("[成功]暂存路径已存在：" + fileTempPathDir.getAbsolutePath());
		}

		File fileToBeWritten;

		// 将生成文档需要的数据fileDataMaps放入模板引擎的上下文中
		context.put("fileDataMaps", fileDataMaps.subList(fileDataStartIndex, fileDataEndIndex));
		// 将文件头写入文件
		// 写入文件头的时候，从文件头部开始写
		fileToBeWritten = new File(fileToBeWrittenPath);

		if (!fileToBeWritten.exists()) {
			fileToBeWritten.createNewFile();
		}// if

		try (
		// 文件输出流
		// 将文件头写入文件
		// 写入文件头的时候，从文件头部开始写
		FileOutputStream fileOutputStream = new FileOutputStream(fileToBeWritten, false);
				// 输出流编写器
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
				// 缓冲编写器
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) // try
		{

			// 将数据与模板merge，并写入到静态文档
			template.merge(context, bufferedWriter);
			// 添加到结果文件集
			bufferedWriter.flush();

		} catch (IOException ioException) {
			// 记录日志
			systemLogger.error("文件内容写入文件 " + fileToBeWritten.getName() + " 时异常，可能是文件流异常，请进一步查找原因。", ioException);
			// 往上抛出异常
			throw ioException;
		}

		return fileToBeWritten;

	}// writeFileByVelocity

	/**
	 * 将模板生成的内容写入已经创建好的空文件
	 * 
	 * @param fileTemplePath
	 *            生成文件所用模板路径
	 * @param fileDataMap
	 *            数据map
	 * @param file
	 *            模板和数据生成的内容写入的空文件
	 * @return 已经写好内容的文件
	 * @throws IOException
	 */
	public boolean writeFileByVelocity(String fileTemplePath, Map<String, Object> fileDataMap, File file) throws IOException {

		try (

		FileOutputStream fileOutputStream = new FileOutputStream(file, false);
		// 输出流编写器
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
				// 缓冲编写器
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) // try
		{

			// 获取模板
			Template template = ve.getTemplate(fileTemplePath);

			// 实例化字符编写器和模板上下文
			VelocityContext context = new VelocityContext(fileDataMap);

			// 将数据与模板merge，并写入到静态文档
			template.merge(context, bufferedWriter);
			// 添加到结果文件集
			bufferedWriter.flush();

		} catch (IOException ioException) {
			// 记录日志
			systemLogger.error("文件内容写入文件 " + file.getName() + " 时异常，可能是文件流异常，请进一步查找原因。", ioException);
			// 往上抛出异常
			throw ioException;
		}

		return true;

	}

	/**
	 * 根据velocity 模板字符串和数据map生成数据字符串
	 * 
	 * @param templateContent
	 *            velocity 模板字符串
	 * @param contextMap
	 *            数据map
	 * @return 生成的数据字符串
	 */
	public String getDateByVelocityTplAndMap(String templateContent, Map<String, Object> contextMap) {

		// 实例化字符编写器和模板上下文
		VelocityContext context = new VelocityContext(contextMap);
		//velocity　数据格式处理工具，velocity-tools-2.0.jar
		context.put("numberTool", new NumberTool());

		// 输出流
		StringWriter writer = new StringWriter();
		// 转换输出
		ve.evaluate(context, writer, "", templateContent);

		// 返回结果字符串
		return writer.toString();
	}

}
