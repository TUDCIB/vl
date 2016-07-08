package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:simulation-energy-test.xml")  
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class DecisionMakingServiceTest {
	

	@Autowired
	DecisionMakingService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		assertNotNull(service);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUploadSensitivityData() throws URISyntaxException {
//		File csvFile					=	new File("test/resources/decisionmaking/test.csv");
		File csvFile					=	new File("test/resources/decisionmaking/dataEN.csv");
		assertTrue(csvFile.exists());
		csvFile = csvFile.getAbsoluteFile();
		assertNotNull(csvFile);
		String simulationName = "cib_test5";
		String projectName = "cib_project_ises";
		URL caseURL = service.uploadCase(csvFile, simulationName, projectName);
		assertNotNull(caseURL);
		System.out.println("Case-URL: "+caseURL);
	}
	
	@Test
	public void testListAllCases() {
		Set<URL> allCases = service.listAllCases(null);
		assertNotNull(allCases);
		assertTrue(allCases.size() > 0);
		System.out.println(allCases);
	}
	
	@Test
	public void testCreateToken() {
		String token = service.createToken();
		assertNotNull(token);
		System.out.println(token);
	}

}
