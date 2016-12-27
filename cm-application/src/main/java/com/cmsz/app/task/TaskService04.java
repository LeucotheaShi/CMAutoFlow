/**
 * @Title: TaskService01.java
 * @Description:
 * @Date:2016��12��23�� ����3:53:35
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
 * @Date: 2016��12��23��
 * @Author: LeucotheaShi
 */
@Component("TaskService04")
public class TaskService04 implements BaseInterface {

	/**
	 * @Title: doService
	 * @Description:
	 * @param variableMap
	 * @return
	 * @throws Exception
	 * @Date:2016��12��23�� ����3:53:35
	 * @Author:LeucotheaShi
	 */
	@Override
	public ReturnResult doService(Map<String, Object> variableMap) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n\n\n\n-----------------------------------");
		System.out.println(" TaskService04 is running successfully. ");

		// variableMap���ܶ�����������task�ò���ͬһ��ʽ��map���ᱨ��
		ReturnResult result = new ReturnResult(Result.SUCCESS, "[�ɹ�]��ִ��TaskService01��", variableMap);
		return result;
	}

}
