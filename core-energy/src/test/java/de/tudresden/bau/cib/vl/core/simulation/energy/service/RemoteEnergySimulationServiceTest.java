package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

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

import de.tudresden.bau.cib.vl.core.simulation.energy.service.impl.RemoteEnergySimulationServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:simulation-energy-test.xml")  
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class RemoteEnergySimulationServiceTest {
	
	@Autowired
	RemoteEnergySimulationServiceImpl service;
	
	private static final String PROJECT_NAME = "ises_cib_test1";
	private static final String PASSIVE_SIM_PROJECT = "test/resources/cloudapi/Nandrad.zip";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		assertNotNull(service);
		File passiveSimZipFile = new File(PASSIVE_SIM_PROJECT);
		assertTrue(passiveSimZipFile.exists());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateNewProject() {

	}

}
