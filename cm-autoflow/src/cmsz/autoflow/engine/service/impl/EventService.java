/**
 * @Title: EventService.java
 * @Description:
 * @Date:2016年12月12日 上午11:04:17
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service.impl;

import cmsz.autoflow.engine.handler.IEventHandler;
import cmsz.autoflow.engine.service.IEventService;

/**
 * @ClassName:cmsz.autoflow.engine.service.impl.EventService
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class EventService implements IEventService {

	/**
	 * @Title: register
	 * @Description:
	 * @param clazz
	 * @Date:2016年12月12日 上午11:04:17
	 * @Author:LeucotheaShi
	 */
	@Override
	public void register(Class<? extends IEventHandler> clazz) {
		// TODO Auto-generated method stub

	}

	/**
	 * @Title: getHandler
	 * @Description:
	 * @return
	 * @Date:2016年12月12日 上午11:04:17
	 * @Author:LeucotheaShi
	 */
	@Override
	public IEventHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
