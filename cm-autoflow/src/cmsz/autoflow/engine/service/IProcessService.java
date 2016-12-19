/**
 * @Title: IProcessService.java
 * @Description:
 * @Date:2016年12月11日 上午8:27:22
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service;

import java.io.InputStream;

import cmsz.autoflow.engine.entity.Process;

/**
 * @ClassName:cmsz.autoflow.engine.service.impl.IProcessService
 * @Description: TODO
 * @Date: 2016年12月11日
 * @Author: LeucotheaShi
 */
public interface IProcessService {

	/**
	 * 
	 * @Title: saveProcess
	 * @Description:保存流程定义对象
	 * @param process
	 * @return：void
	 * @Date:2016年12月11日 上午8:28:40
	 * @Author:LeucotheaShi
	 */
	void saveProcess(Process process);

	/**
	 * 
	 * @Title: getProcessById
	 * @Description:通过id获取Process对象, 并解析xml文件,设置ProcessModel对象.
	 * @param processId
	 * @return
	 * @return：Process
	 * @Date:2016年12月11日 上午8:28:51
	 * @Author:LeucotheaShi
	 */
	Process getProcessById(String processId);

	/**
	 * 
	 * @Title: deploy
	 * @Description:根據InputStream輸入流，部署流程定义
	 * @param input
	 * @return
	 * @return：String
	 * @Date:2016年12月11日 上午8:29:15
	 * @Author:LeucotheaShi
	 */
	String deploy(InputStream input);

}// IProcessService
