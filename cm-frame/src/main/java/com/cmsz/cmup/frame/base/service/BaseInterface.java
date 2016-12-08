package com.cmsz.cmup.frame.base.service;

import java.util.Map;

import com.cmsz.cmup.frame.model.ReturnResult;

/**
 * 系统基础服务接口类,子系统所有的组件（Service）都必须实现此接口
 * 
 * @author JinChao
 * 
 * @date 2015年11月11日 上午10:10:18
 *
 */
public interface BaseInterface {
	/**
	 * 通用doService方法，所有组件，默认业务逻辑的执行入口方法
	 * 
	 * <p>特别注意：variableMap中以下6个参数为系统默认参数，使用过程中只可以读取，任何情况下都不可以更改这些key对应的值:</p>
	 * 1.province(省编码)、2.settleDate(账期日)、3.busiLine(业务线)、4.serviceName(系统组件名称)，5.flowId(流程实例id)，6.taskId（任务id）
	 * @param variableMap 参数map
	 * @return ReturnResult 执行结果
	 */
	public ReturnResult doService(Map<String, String> variableMap) throws Exception;
}
