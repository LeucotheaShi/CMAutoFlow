/**
 * @Title: ProcessService.java
 * @Description:
 * @Date:2016年12月11日 上午8:35:05
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service.impl;

import java.io.IOException;
import java.io.InputStream;

import cmsz.autoflow.engine.constant.ConfigNameConstant;
import cmsz.autoflow.engine.entity.Process;
import cmsz.autoflow.engine.helper.DateHelper;
import cmsz.autoflow.engine.helper.StreamHelper;
import cmsz.autoflow.engine.model.ProcessModel;
import cmsz.autoflow.engine.parser.ProcessModelParser;
import cmsz.autoflow.engine.service.AccessService;
import cmsz.autoflow.engine.service.IProcessService;

/**
 * @ClassName:cmsz.autoflow.engine.service.impl.ProcessService
 * @Description: TODO
 * @Date: 2016年12月11日
 * @Author: LeucotheaShi
 */
public class ProcessService extends AccessService implements IProcessService {

	/**
	 * @Title: saveProcess
	 * @Description:
	 * @param process
	 * @Date:2016年12月11日 上午8:35:05
	 * @Author:LeucotheaShi
	 */
	@Override
	public void saveProcess(Process process) {
		// TODO Auto-generated method stub
		getAccess().deleteProcess(process);
		getAccess().saveProcess(process);

	}

	/**
	 * @Title: getProcessById
	 * @Description:
	 * @param processId
	 * @return
	 * @Date:2016年12月11日 上午8:35:05
	 * @Author:LeucotheaShi
	 */
	@Override
	public Process getProcessById(String processId) {
		// TODO Auto-generated method stub
		Process process = getAccess().getProcess(processId);

		if (process != null) {
			process.setModel(ProcessModelParser.parse(process.getContent()));
		} // if

		return process;
	}// getProcessById

	/**
	 * @Title: deploy
	 * @Description:
	 * @param input
	 * @return
	 * @throws @Date:2016年12月11日
	 *             上午8:35:05
	 * @Author:LeucotheaShi
	 */
	@Override
	public String deploy(InputStream input) {
		// TODO Auto-generated method stub

		try {
			byte[] bytes;
			// parse the xml file to stream
			bytes = StreamHelper.readBytes(input);

			// parse the bytes to ProcessModel instance
			ProcessModel processModel = ProcessModelParser.parse(bytes);

			// save the ProcessModel instance into process table
			Process process = new Process();
			process.setId(processModel.getId());
			process.setModel(processModel);
			process.setName(processModel.getName());
			process.setStatus(ConfigNameConstant.STATUS_ACTIVE);
			process.setCreateTime(DateHelper.getTime());
			process.setUpdateTime(DateHelper.getTime());
			process.setContent(bytes);

			this.saveProcess(process);

			return process.getId();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
