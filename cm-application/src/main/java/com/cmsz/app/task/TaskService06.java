/**
 * @Title: TaskService01.java
 * @Description:
 * @Date:2016年12月23日 下午3:53:35
 * @Author:LeucotheaShi
 */
package com.cmsz.app.task;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.cmsz.cmup.frame.base.service.BaseInterface;
import com.cmsz.cmup.frame.constant.Result;
import com.cmsz.cmup.frame.model.ReturnResult;

/**
 * @ClassName:cmsz.app.task.TaskService01
 * @Description: TODO
 * @Date: 2016年12月23日
 * @Author: LeucotheaShi
 */
@Component("TaskService06")
public class TaskService06 implements BaseInterface {

	/**
	 * @Title: doService
	 * @Description:
	 * @param variableMap
	 * @return
	 * @throws Exception
	 * @Date:2016年12月23日 下午3:53:35
	 * @Author:LeucotheaShi
	 */
	@Override
	public ReturnResult doService(Map<String, Object> variableMap) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n\n\n\n-----------------------------------");
		System.out.println(" TaskService06 is running successfully. ");

		// variableMap不能丢，否则后面的task拿不到同一格式的map，会报错
		ReturnResult result = new ReturnResult(Result.SUCCESS, "[成功]已执行TaskService01。", variableMap);
		return result;
	}

}
