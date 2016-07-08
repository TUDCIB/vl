package activiti;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:simulation-energy-test.xml")  
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class SpaceBoundaryConversionProcessTest {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private SpaceBoundaryConversionService scs;
	
	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;

	private String filename = "src\\activiti\\spaceboundaryconversion.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	@Before
	public void setUp() throws Exception {
		assertNotNull(runtimeService);
		assertNotNull(repositoryService);
	}

	@Test
	public void testStartProcess() throws Exception {		
		repositoryService.createDeployment().addInputStream("spaceboundaryconversion.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		Map<String, Object> variableMap = new HashMap<String, Object>();
//		variableMap.put("scs", new SpaceBoundaryConversionService());
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("spaceboundaryconversion", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
	}
}