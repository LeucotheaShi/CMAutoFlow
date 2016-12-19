/**
 * @Title: EngineProcessTest.java
 * @Description:
 * @Date:2016年12月6日 上午10:07:01
 * @Author:LeucotheaShi
 */
package test.workflow.myflow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cmsz.autoflow.engine.access.DBAccess;
import cmsz.autoflow.engine.access.mybatis.MyBatisAccess;
import cmsz.autoflow.engine.core.AutoEngine;
import cmsz.autoflow.engine.entity.Flow;
import cmsz.autoflow.engine.entity.Process;
import cmsz.autoflow.engine.helper.DateHelper;
import cmsz.autoflow.engine.model.ProcessModel;

/**
 * @ClassName:test.workflow.myflow.EngineProcessTest
 * @Description: TODO
 * @Date: 2016年12月6日
 * @Author: LeucotheaShi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class EngineProcessTest {

	DBAccess myBatisAccess = new MyBatisAccess();

	@Autowired
	@Qualifier("engine")
	AutoEngine engine;

	/**
	 * 
	 * @Description:
	 * @Date:2016年12月7日 上午11:58:35
	 * @Author:LeucotheaShi
	 */
	public EngineProcessTest() {
		// TODO Auto-generated constructor stub
		// ApplicationContext ac1 = new
		// ClassPathXmlApplicationContext("classpath:spring.xml");
		//
		// SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)
		// ac1.getBean("sqlSessionFactory");
		//
		// MybatisSqlSessionFactory.setSqlSessionFactory(sqlSessionFactory);
	}

	@Test
	public void testLoadSpring() {
		System.out.println("\n\n\n\nspring is load successfully!");

	}// testLoadSpring

	@Test
	public void testFlowInsert() {
		Flow flow = new Flow();
		flow.setId("Flow-20161207-002");
		flow.setName("myTestFlow");
		flow.setProcessId("Process-002");
		flow.setStatus("ACTIVE");
		flow.setVariables("");
		flow.setCreateTime(DateHelper.getTime());
		flow.setUpdateTime(DateHelper.getTime());

		myBatisAccess.saveFlow(flow);
	}// testFlowInsert

	@Test
	public void testFlowQuary() {
		Flow myFlow = myBatisAccess.getFlow("Flow-20161207-002");
		System.out.println("\n\n\n******************************");
		System.out.println(myFlow.toString());
	}

	@Test
	public void testFlowUpdate() {
		Flow flow = new Flow();
		flow.setId("Flow-20161207-002");
		flow.setName("myTestFlow");
		flow.setProcessId("Process-002");
		flow.setStatus("FINISH");
		flow.setVariables("");
		flow.setUpdateTime(DateHelper.getTime());

		myBatisAccess.updateFlow(flow);

		Flow myFlow = myBatisAccess.getFlow("Flow-20161207-002");
		System.out.println("\n\n\n******************************");
		System.out.println(myFlow.toString());

	}

	@Test
	public void testProcessQuary() {

		Process process = myBatisAccess.getProcess("Proc_Develop_Test_Pre");
		ProcessModel processModel = process.getModel();

		System.out.println("\n\n\n**********************");
		System.out.println(process.toString());

	}// testProcessQuary

	/**
	 * @Title: main
	 * @Description:
	 * @param args
	 * @return：void
	 * @Date:2016年12月6日 上午10:07:01
	 * @Author:LeucotheaShi
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
