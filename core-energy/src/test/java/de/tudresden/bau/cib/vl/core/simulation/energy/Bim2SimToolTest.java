package de.tudresden.bau.cib.vl.core.simulation.energy;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.configuration.Configuration;
import de.tudresden.bau.cib.vl.core.simulation.energy.process.Bim2SimToolProcess;
import de.tudresden.bau.cib.vl.core.tool.Tool;

public class Bim2SimToolTest {

	private static final String CONFIG_PATH = "D:/Nutzer/ken/Workspaces/VirtualLaboratory/master/core-configuration/config";
	private static Configuration configuration;
	private static final String DESTINATION_PATH = "D:\\Nutzer\\ken\\data\\Nandrad_Test\\";
//	private static final String PATH_TO_IFC_FILE = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\2ndLevel\\Quickstart_Project_2ndLSB.ifc";
	private static final String PATH_TO_IFC_FILE = "D:\\Nutzer\\ken\\data\\VirtualLaboratory\\temp\\1_1370619969484\\project\\HESMOS_Schule-A1_2ndLSB.ifc";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		configuration = new Configuration();
//		configuration.setConfigDirectoryPath(CONFIG_PATH);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStartExecution() throws Exception {		
//		String pathToBim2Sim = configuration.getProperty(Configuration.PROPERTIES.PATH_TO_BIM2SIM);
		String pathToBim2Sim = "D:\\Nutzer\\ken\\data\\VirtualLaboratory\\temp\\1_1370619969484\\bin\\Ifc2Nandrad\\";
		String batchFileName = "Ifc2Nandrad.jar";
//		final String PATH_TO_TEMP_DIR = configuration.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		
		String simulationModel = PATH_TO_IFC_FILE;
		int lastDot = simulationModel.lastIndexOf(".");
		simulationModel = simulationModel.substring(0, lastDot)+".nandrad";
		File simulationModelFile = new File(simulationModel);
		if(simulationModelFile.exists()) simulationModelFile.delete();
//		
//		Set<String> locationIds = new HashSet<String>();
//
//		String exportDir = new File(pathToBim2Sim).getParentFile().getAbsolutePath();
//		LocationIdentifierWriter.writeLocationIdentifiers(exportDir, locationIds);
//		
//		File[] databaseDirectories = new File[3];
//		File dbClimate = new File(configuration.getProperty(Configuration.PROPERTIES.PATH_TO_DB_CLIMATE));
//		File dbConstructions = new File(configuration.getProperty(Configuration.PROPERTIES.PATH_TO_DB_CONSTRUCTIONS));
//		File dbMaterials = new File(configuration.getProperty(Configuration.PROPERTIES.PATH_TO_DB_MATERIALS));
//		databaseDirectories[0] = dbClimate;
//		databaseDirectories[1] = dbConstructions;
//		databaseDirectories[2] = dbMaterials;
//		
//		String PATH_TO_HESMOS_TEMPLATES_AND_MAPPINGS = configuration.getProperty(PROPERTIES.PATH_TO_HESMOS_TEMPLATES_AND_MAPPINGS);
		Bim2SimToolProcess process = new Bim2SimToolProcess(pathToBim2Sim, batchFileName, PATH_TO_IFC_FILE, null);
		Tool<Bim2SimToolProcess, File> tool = new Tool<Bim2SimToolProcess, File>(process, 10, 600);
		
		tool.startExecution();
//		wait until tool finished
		tool.getOutput();
		assertTrue(simulationModelFile.exists());

	}

}
