/**
 * @Title: IEventHandler.java
 * @Description:
 * @Date:2016年12月12日 上午10:20:51
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.handler;

import cmsz.autoflow.engine.constant.EventEnum;
import cmsz.autoflow.engine.core.Execution;

/**
 * @ClassName:cmsz.autoflow.engine.handler.IEventHandler
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public interface IEventHandler {

	public void handle(Execution execution, EventEnum eventEnum);

}
