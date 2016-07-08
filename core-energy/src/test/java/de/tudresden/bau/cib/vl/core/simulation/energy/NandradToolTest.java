package de.tudresden.bau.cib.vl.core.simulation.energy;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.configuration.Configuration;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.simulation.energy.process.NandradSimulation;

public class NandradToolTest {

	private static final String CONFIG_PATH = "D:/Nutzer/ken/Workspaces/VirtualLaboratory/master/core-configuration/config";
	private static final String PATH_TO_ISES_PASSIVE_DATA_DIR = "test/resources/nandrad/building/ises/passive/";
	private static Configuration configuration;
	private static NandradSimulation controller;
	private static final String DESTINATION_PATH = "target/ises/passive/";
	private SimulationProject simProject;
	private File SIMULATION_PROJECT_FILE = new File(PATH_TO_ISES_PASSIVE_DATA_DIR+"FZKassel-Raume+Wande+Boden+Decken_Project-5+Roof_2ndLSB.nandrad");

	private static final String PATH_TO_NANDRAD = "resources/tools/Nandrad/";
	private static final String NANDRAD_EXE = "NandradSolver.exe";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		configuration = new Configuration();
		configuration.setConfigDirectoryPath(CONFIG_PATH);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {				
		assertTrue(SIMULATION_PROJECT_FILE.exists());
		controller = new NandradSimulation(PATH_TO_NANDRAD, NANDRAD_EXE, 10, 600);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartExecution() throws Exception {
		controller.start(simProject, SIMULATION_PROJECT_FILE.getAbsolutePath());

	}

}
