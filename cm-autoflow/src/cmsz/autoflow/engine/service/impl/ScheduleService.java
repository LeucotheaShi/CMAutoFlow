/**
 * @Title: ScheduleService.java
 * @Description:
 * @Date:2016年12月12日 上午9:56:59
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service.impl;

import java.util.List;

import cmsz.autoflow.engine.entity.Schedule;
import cmsz.autoflow.engine.service.AccessService;
import cmsz.autoflow.engine.service.IScheduleService;

/**
 * @ClassName:cmsz.autoflow.engine.service.impl.ScheduleService
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public class ScheduleService extends AccessService implements IScheduleService {

	/**
	 * @Title: getAllSchedule
	 * @Description:
	 * @return
	 * @Date:2016年12月12日 上午9:56:59
	 * @Author:LeucotheaShi
	 */
	@Override
	public List<Schedule> getAllSchedule() {
		// TODO Auto-generated method stub
		return getAccess().getAllSchedule();
	}

}
