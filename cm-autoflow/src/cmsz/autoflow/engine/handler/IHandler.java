/**
 * @Title: IHandler.java
 * @Description:
 * @Date:2016年12月8日 下午5:01:54
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.handler;

import cmsz.autoflow.engine.core.Execution;

/**
 * @ClassName:cmsz.autoflow.engine.handler.IHandler
 * @Description: 所有handler类的接口
 * @Date: 2016年12月8日
 * @Author: LeucotheaShi
 */
public interface IHandler {

	public void handle(Execution execution);

}// IHandler
