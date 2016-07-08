package de.tudresden.bau.cib.vl.core.simulation.energy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.configuration.Configuration;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesEnergyResults;
import de.tudresden.bau.cib.vl.core.simulation.energy.process.TheraklesSimulation;

public class TheraklesSimulationTest {

	private static final String CONFIG_PATH = "D:\\Daten\\tomcat-data\\vl\\config";
	private static Configuration configuration;
	private static TheraklesSimulation simController;
	private static String TEST_PROJECT = "resources/Therakles/Testproject.rmxml";
	private File testFile;
	private static final String DESTINATION_PATH = "D:\\Nutzer\\ken\\data\\Therakles_Test\\";
	
	private static final String PATH_TO_THERAKLES = "resources/tools/Therakles/";
	private static final String EXE_THERAKLES = "TheraklesSolver.exe";
	
	private SimulationProject si1;
	
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
		File testProjectFile = new File(TEST_PROJECT);
		assertNotNull(testProjectFile);
		assertTrue(testProjectFile.exists());
		
		si1 = new SimulationProject();
		si1.setId(1);
		testFile = new File(TEST_PROJECT);
		assertTrue(testFile.exists());
		si1.setPathDirectory(testFile.getParentFile().getAbsolutePath());
		
		simController = new TheraklesSimulation(PATH_TO_THERAKLES, EXE_THERAKLES, 10, 600);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartExecution() throws Exception {
		simController.start(si1, testFile.getAbsolutePath());
	}
	
	@Test
	public void testStartExecutionAndWaitForResults() throws Exception {
		simController.start(si1, testFile.getAbsolutePath());
		while(simController.isRunning(si1.getId())){}
		String outputDirPath = si1.getPathOutputDirectory();
		assertNotNull(outputDirPath);
		System.out.println("Path to first results = '"+outputDirPath+"'");

	}
	
	@Test
	public void testStartExecutionAndReadResults() throws Exception {		
		simController.start(si1, testFile.getAbsolutePath());
		while(simController.isRunning(si1.getId())){}
		String outputDirPath = si1.getPathOutputDirectory();
		File outputDir = new File(outputDirPath);
		assertNotNull(outputDir);
		File[] files = outputDir.listFiles();
		for(File file : files) {
			assertNotNull(file);
			if(!file.getName().equalsIgnoreCase(testFile.getName()+".txt")) continue;			
			System.out.println("Path to first results = '"+file+"'");
			
			TheraklesEnergyResults energyResults = new TheraklesEnergyResults(file);
			Map<Integer, List<Number>> resultMap = energyResults.getAllResults();
			assertNotNull(resultMap);
			assertTrue(resultMap.size()>0);
			System.out.println("----- Energy Results -----");
			for(Map.Entry<Integer, List<Number>> entry : resultMap.entrySet()) {
				assertNotNull(entry);
				Integer key = entry.getKey();
				List<Number> values = entry.getValue();
				assertNotNull(values);
				System.out.println("Key: '"+key+"' "+"values: '"+values+"'");
			}
		}

	}

}
