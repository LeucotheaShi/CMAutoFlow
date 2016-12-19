/**
 * @Title: AutoEngineFactory.java
 * @Description:
 * @Date:2016年12月12日 上午11:58:02
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.core;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmsz.autoflow.engine.access.mybatis.MyBatisAccess;
import cmsz.autoflow.engine.access.mybatis.MybatisSqlSessionFactory;
import cmsz.autoflow.engine.constant.Constant;
import cmsz.autoflow.engine.core.impl.AutoEngineImpl;
import cmsz.autoflow.engine.core.impl.SimpleContext;
import cmsz.autoflow.engine.service.IEventService;
import cmsz.autoflow.engine.service.impl.EventService;
import cmsz.autoflow.engine.service.impl.FlowService;
import cmsz.autoflow.engine.service.impl.ProcessService;
import cmsz.autoflow.engine.service.impl.ScheduleService;
import cmsz.autoflow.engine.service.impl.TaskService;
import cmsz.autoflow.engine.service.impl.ThreadService;

/**
 * 
 * @ClassName:cmsz.autoflow.engine.core.Configuration
 * @Description: 建造流程引擎的工厂
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class Configuration {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 * @Description:
	 * @Date:2016年12月12日 下午2:38:40
	 * @Author:LeucotheaShi
	 */
	public Configuration() {
		// TODO Auto-generated constructor stub
		this(new SimpleContext());
	}

	/**
	 * 
	 * @Description:
	 * @Date:2016年12月12日 下午2:38:40
	 * @Author:LeucotheaShi
	 */
	public Configuration(Context context) {
		// TODO Auto-generated constructor stub
		ServiceContext.setContext(context);
	}

	public AutoEngine buildAutoEngine() {

		logger.debug("loading service class into servicecontext.");

		ServiceContext.put("cmsz.autoflow.engine.service.impl.FlowService", FlowService.class);
		ServiceContext.put("cmsz.autoflow.engine.service.impl.ProcessService", ProcessService.class);
		ServiceContext.put("cmsz.autoflow.engine.service.impl.TaskService", TaskService.class);
		ServiceContext.put("cmsz.autoflow.engine.access.MyBatisAccess", MyBatisAccess.class);
		ServiceContext.put("cmsz.autoflow.engine.core.impl.AutoEngineImpl", AutoEngineImpl.class);
		ServiceContext.put("cmsz.autoflow.engine.service.impl.ThreadService", ThreadService.class);
		ServiceContext.put("cmsz.autoflow.engine.service.impl.ScheduleService", ScheduleService.class);

		ServiceContext.put("cmsz.autoflow.engine.eventhandlers.EventService", EventService.class);

		IEventService eventService = ServiceContext.find(EventService.class);

		SqlSessionFactory sqlSessionFactory = ServiceContext.findByName(Constant.DEFAULT_SQLSESSIONFACTORY_ID,
				SqlSessionFactory.class);

		if (sqlSessionFactory != null) {
			MybatisSqlSessionFactory.setSqlSessionFactory(sqlSessionFactory);
		} else {
			sqlSessionFactory = ServiceContext.find(SqlSessionFactory.class);
			if (sqlSessionFactory != null) {
				MybatisSqlSessionFactory.setSqlSessionFactory(sqlSessionFactory);
			}
		}

		AutoEngine autoEngine = ServiceContext.getEngine();

		return autoEngine.configure(this);

	}// buildAutoEngine

}// AutoEngineFactory
