/**
 * @Title: MyBatisAccess.java
 * @Description:
 * @Date:2016年12月6日 下午4:07:38
 * @Author:LeucotheaShi
 */
package cmsz.autoflow.engine.access.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmsz.autoflow.engine.access.DBAccess;
import cmsz.autoflow.engine.entity.Flow;
import cmsz.autoflow.engine.entity.FlowAppend;
import cmsz.autoflow.engine.entity.Process;
import cmsz.autoflow.engine.entity.Schedule;
import cmsz.autoflow.engine.entity.Task;
import cmsz.autoflow.engine.entity.TaskAppend;

/**
 * @ClassName:cmsz.autoflow.engine.access.MyBatisAccess
 * @Description: TODO
 * @Date: 2016年12月6日
 * @Author: LeucotheaShi
 */
public class MyBatisAccess implements DBAccess {

	private static Logger logger = LoggerFactory.getLogger(MyBatisAccess.class);

	@Override
	public void saveTask(Task task) {
		logger.debug("Insert Task:{}", task.toString());
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
			taskMapper.insertTask(task);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void saveFlow(Flow flow) {

		logger.debug("Insert Flow:{}", flow.toString());
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);
			flowMapper.insertFlow(flow);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	@Override
	public void saveProcess(Process process) {
		logger.debug("Insert Process : {}", process.toString());
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			ProcessMapper processMapper = sqlSession.getMapper(ProcessMapper.class);
			processMapper.insertProcess(process);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void updateTask(Task task) {
		logger.debug("Update Task:{}", task.toString());
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
			taskMapper.updateTask(task);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void updateFlow(Flow flow) {

		logger.debug("Update Flow:{}", flow.toString());
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);
			flowMapper.updateFlowStatus(flow);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void updateProcess(Process process) {
		// TODO Auto-generated method stub

	}

	@Override
	public Task getTask(String taskId) {

		logger.debug("Select task, id : {}", taskId);
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		Task task = null;
		try {
			TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
			task = taskMapper.selectTaskById(taskId);
			sqlSession.commit();
			if (task != null)
				logger.debug("Selected task:{}", task.toString());
		} finally {
			sqlSession.close();
		}
		return task;
	}

	@Override
	public Flow getFlow(String flowId) {

		logger.debug("Select flow, id : {}", flowId);
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		Flow flow = null;
		try {
			FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);
			flow = flowMapper.selectFlowById(flowId);
			sqlSession.commit();
			if (flow != null)
				logger.debug("Selected flow:{}", flow.toString());
		} finally {
			sqlSession.close();
		}
		return flow;
	}

	@Override
	public Process getProcess(String processId) {

		logger.debug("Select Process by ID : {}", processId);
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		Process process = null;
		try {
			ProcessMapper processMapper = sqlSession.getMapper(ProcessMapper.class);
			process = processMapper.findProcessById(processId);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		return process;
	}

	@Override
	public void deleteProcess(Process process) {
		logger.debug("Delete Process by ID : {}", process.getId());
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			ProcessMapper processMapper = sqlSession.getMapper(ProcessMapper.class);
			processMapper.deleteProcessById(process.getId());
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Task getTask(Task task) {

		logger.debug("Select task : {}", task.toString());
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		Task ntask = null;
		try {
			TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
			ntask = taskMapper.selectTaskByFlowIdAndTaskName(task);
			sqlSession.commit();
			if (ntask != null)
				logger.debug("Selected task:{}", ntask.toString());
		} finally {
			sqlSession.close();
		}
		return ntask;
	}

	@Override
	public void deleteTasksByFlowId(String flowId) {
		logger.debug("delete tasks : flowId = {}", flowId);
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
			taskMapper.deleteTasksByFlowId(flowId);
			sqlSession.commit();
			logger.debug("delete tasks : flowId={} success", flowId);
		} finally {
			sqlSession.close();
		}

	}

	@Override
	public List<Schedule> getAllSchedule() {
		logger.debug("Get all schedules ");
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			ScheduleMapper scheduleMapper = sqlSession.getMapper(ScheduleMapper.class);
			List<Schedule> list = scheduleMapper.selectAll();
			sqlSession.commit();
			return list;
		} finally {
			sqlSession.close();
		}

	}

	@Override
	public List<Flow> getFlow(String flowName, String createTime) {
		logger.debug("Get flows by flowName and settleDate");
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("name", flowName);
			paraMap.put("createTime", createTime);
			List<Flow> list = flowMapper.getFlowSelective(paraMap);
			sqlSession.commit();
			return list;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateFlowAppend(FlowAppend flowAppend) {

		logger.debug("update flowAppend data : flowId =  " + flowAppend.getId());
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			FlowMapper flowMapper = sqlSession.getMapper(FlowMapper.class);
			flowMapper.updateFlowAppend(flowAppend);
			sqlSession.commit();
			logger.debug("update flowAppend : flowId= " + flowAppend.getId() + " success ");
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public void updateTaskAppend(TaskAppend taskAppend) {
		logger.debug("update taskAppend data : taskId = " + taskAppend.getId());
		logger.debug("update taskAppend data : taskAppend= " + taskAppend);
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
			taskMapper.updateTaskAppend(taskAppend);
			sqlSession.commit();
			logger.debug("update taskAppend : taskId=" + taskAppend.getId() + " success");
		} finally {
			sqlSession.close();
		}

	}

}// MyBatisAccess
