/**
 * @Title: IEventService.java
 * @Description:
 * @Date:2016年12月12日 上午10:19:57
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service;

import cmsz.autoflow.engine.handler.IEventHandler;

/**
 * @ClassName:cmsz.autoflow.engine.service.IEventService
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public interface IEventService {

	public void register(Class<? extends IEventHandler> clazz);

	public IEventHandler getHandler();
}
