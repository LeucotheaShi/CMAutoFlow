/**
 * @Title: EventService.java
 * @Description:
 * @Date:2016年12月12日 上午11:04:17
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmsz.autoflow.engine.constant.EventEnum;
import cmsz.autoflow.engine.core.Execution;
import cmsz.autoflow.engine.handler.IEventHandler;
import cmsz.autoflow.engine.helper.ClassHelper;
import cmsz.autoflow.engine.service.IEventService;

/**
 * @ClassName:cmsz.autoflow.engine.service.impl.EventService
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class EventService implements IEventService {
	
	private static Logger logger = LoggerFactory.getLogger(EventService.class);

	private IEventHandler eventhandler = new InnerHandler();

	List<IEventHandler> hlist = new ArrayList<IEventHandler>();

	/**
	 * @Title: register
	 * @Description:
	 * @param clazz
	 * @Date:2016年12月12日 上午11:04:17
	 * @Author:LeucotheaShi
	 */
	@Override
	public void register(Class<? extends IEventHandler> clazz) {
		IEventHandler h = ClassHelper.instantiate(clazz);
		if (h != null)
			hlist.add(h);
		else
			logger.error("the class {} can not instantiate.", clazz);
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
		return eventhandler;
	}
	
	class InnerHandler implements IEventHandler {
		@Override
		public void handle(Execution execution, EventEnum eventEnum) {
			for (IEventHandler h : hlist) {
				h.handle(execution, eventEnum);
			}
		}
	}

}
