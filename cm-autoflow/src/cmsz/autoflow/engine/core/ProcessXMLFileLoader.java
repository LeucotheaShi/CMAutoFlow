/**
 * @Title: ProcessXMLFileLoader.java
 * @Description:
 * @Date:2016年12月12日 上午11:07:12
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cmsz.autoflow.engine.entity.Process;
import cmsz.autoflow.engine.helper.StreamHelper;
import cmsz.autoflow.engine.model.ProcessModel;
import cmsz.autoflow.engine.parser.ProcessModelParser;

/**
 * @ClassName:cmsz.autoflow.engine.core.ProcessXMLFileLoader
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class ProcessXMLFileLoader {

	private static final Logger logger = LoggerFactory.getLogger(ProcessXMLFileLoader.class);

	private List<String> files;

	@Autowired
	private AutoEngine engine;

	private boolean override = false;

	/**
	 * 
	 * @Title: load
	 * @Description: 加载流程定义文件,即把流程定义文件解析后保存在process 表中
	 * @throws IOException
	 * @return：void
	 * @Date:2016年12月12日 上午11:10:07
	 * @Author:LeucotheaShi
	 */
	public void load() throws IOException {
		for (String fileName : files) {

			if (StringUtils.isEmpty(fileName) || StringUtils.isBlank(fileName)) {
				continue;
			} // if

			// 该流程已存在于库表中且不覆盖
			if (this.isProcessExist(fileName) && (!this.override)) {
				logger.debug("the process with file name {} has exist in db, don't override the record", fileName);
				continue;
			}

			InputStream inputStream = StreamHelper.openStream(fileName);
			if (inputStream == null) {
				logger.debug("{} file's input stream is null.", fileName);
				throw new IOException("can not open file " + fileName + " , its input stream is null.");
			}
			String processId = this.engine.getProcessService().deploy(inputStream);

			inputStream.close();

			if (processId == null) {
				logger.debug("load the process file {} failed.", fileName);
				throw new IOException("can not load file " + fileName + " , its  processid returned is null.");
			} else {
				logger.debug("load the process file {} successfully.", fileName);
			}

		} // for

	}// load

	// 根据文件名获取其输入流
	// Class.getResourceAsStream(String path) ： path
	// 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从
	// ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
	private InputStream getInputStream(String fname) {
		return this.getClass().getResourceAsStream("/" + fname);
	}

	/**
	 * 
	 * @Title: isProcessExist
	 * @Description:判断流程是否存在于数据库中
	 * @param file
	 * @return
	 * @throws IOException
	 * @return：boolean
	 * @Date:2016年12月12日 上午11:14:26
	 * @Author:LeucotheaShi
	 */
	private boolean isProcessExist(String file) throws IOException {

		InputStream inputStream = this.getInputStream(file);

		byte[] bytes = StreamHelper.readBytes(inputStream);

		inputStream.close();

		ProcessModel processModel = ProcessModelParser.parse(bytes);
		Process process = this.engine.getProcessService().getProcessById(processModel.getId());

		if (process == null) {
			return false;
		} else {
			return true;
		} // else

	}// isProcessExist

	/**
	 * @return the files
	 */
	public List<String> getFiles() {
		return files;
	}

	/**
	 * @return the engine
	 */
	public AutoEngine getEngine() {
		return engine;
	}

	/**
	 * @param engine
	 *            the engine to set
	 */
	public void setEngine(AutoEngine engine) {
		this.engine = engine;
	}

	/**
	 * @param files
	 *            the files to set
	 */
	public void setFiles(List<String> files) {
		this.files = files;
	}

	/**
	 * @return the override
	 */
	public boolean isOverride() {
		return override;
	}

	/**
	 * @param override
	 *            the override to set
	 */
	public void setOverride(boolean override) {
		this.override = override;
	}

}// ProcessXMLFileLoader
