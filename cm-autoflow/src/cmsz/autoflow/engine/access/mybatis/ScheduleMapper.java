package cmsz.autoflow.engine.access.mybatis;

import java.util.List;

import cmsz.autoflow.engine.entity.Schedule;

public interface ScheduleMapper {
	List<Schedule>	selectAll();
}
