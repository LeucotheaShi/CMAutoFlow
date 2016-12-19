/**
 * @Title: IScheduleService.java
 * @Description:
 * @Date:2016年12月12日 上午9:55:12
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.service;

import java.util.List;

import cmsz.autoflow.engine.entity.Schedule;

/**
 * @ClassName:cmsz.autoflow.engine.service.IScheduleService
 * @Description: TODO
 * @Date: 2016年12月12日
 * @Author: LeucotheaShi
 */
public interface IScheduleService {

	public List<Schedule> getAllSchedule();

}// IScheduleService
