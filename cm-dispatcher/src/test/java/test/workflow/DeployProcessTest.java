package test.workflow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 部署流程
 *
 * @author JinChao
 * 
 * @date 2015年12月10日 下午4:03:00
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
public class DeployProcessTest {

//	@Autowired
//	@Qualifier("engine")
//	AutoEngine engine;

	@Test
	public void testProcessDeploy() {

//		String processFileName = "example_migu.xml";
//		InputStream in = StreamHelper.openStream("autoflow/" + processFileName);
//		String result = engine.process().deploy(in);
//		System.out.println("流程[" + result + "]已部署成功！");

	}

}
