package de.tudresden.bau.cib.vl.core.simulation.energy.cloud;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.simulation.energy.cloud.CloudClient.Status;
import de.tudresden.bau.cib.vl.core.simulation.energy.cloud.CloudClient.Type;


public class ISESCloudClientTest {

	private static String PROJECT_NAME = "ises_cib_test";
	private static final String PATH_TO_PASSIVE_SIM_PROJECT = "test/resources/cloudapi/building_data.zip";
	private static final String PATH_TO_SENSITIVITY_ANALYSIS_MATRIX = "test/resources/cloudapi/SENSITIVITY_INPUT.zip";
	private static final String TOKEN = "4935146342542";
	private static final String URL = "http://vel.ises.eu-project.info/api/";
	private static final String OUTPUT_DIR = "target/output/cloud/";
	
	private CloudClient client;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// create a new project name
		PROJECT_NAME += "_"+System.currentTimeMillis();
		URI uri = new URI(URL);
		client = new CloudClient(uri, TOKEN);
		assertNotNull(client);
		File passiveSimZipFile = new File(PATH_TO_PASSIVE_SIM_PROJECT);
		assertTrue(passiveSimZipFile.exists());
		File outputDir = new File(OUTPUT_DIR);
		if(!outputDir.exists()) {
			assertTrue(outputDir.mkdirs());
		} else {
			// delete all files in the directory
			File[] files = outputDir.listFiles();
			for(File file : files) {
				assertTrue(file.delete());
			}
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateNewProject() throws Exception {
		String projectId = client.createNewProject(PROJECT_NAME);
		assertNotNull(projectId);
		System.out.println(projectId);
	}

	@Test
	public void testUploadPassiveSimulationData() throws Exception {
		String projectId = client.createNewProject(PROJECT_NAME);
		assertNotNull(projectId);
		System.out.println(projectId);
		File passiveSimZipFile = new File(PATH_TO_PASSIVE_SIM_PROJECT);
		boolean isUploaded = client.uploadPassiveSimulationData(projectId, passiveSimZipFile);
		assertTrue(isUploaded);
	}
	
	@Test
	public void testUploadSimulationMatrixData() throws Exception {
//		String projectId = client.createNewProject(PROJECT_NAME);
		String projectId = "d9f45907ac659b76f9839455b26a44a15f225bdb";
		assertNotNull(projectId);
		System.out.println(projectId);
		File passiveSimZipFile = new File(PATH_TO_SENSITIVITY_ANALYSIS_MATRIX);
		boolean isUploaded = client.uploadSimulationMatrix(projectId, passiveSimZipFile);
		assertTrue(isUploaded);
	}
	
	@Test
	public void tetsStartSensitivityAnalysis() throws Exception {
		String projectId = "d9f45907ac659b76f9839455b26a44a15f225bdb";
		Status status = client.startSensitivityAnalysis(projectId);
		assertTrue(status == Status.RUNNING);
	}

	@Test
	public void testStartPassiveSimulation() throws Exception {
		startPassiveSimulation();
	}
	
	private String startPassiveSimulation() throws InterruptedException {
		String projectId = client.createNewProject(PROJECT_NAME);
		assertNotNull(projectId);
		System.out.println(projectId);
		File passiveSimZipFile = new File(PATH_TO_PASSIVE_SIM_PROJECT);
		boolean isUploaded = client.uploadPassiveSimulationData(projectId, passiveSimZipFile);
		assertTrue(isUploaded);
		
		Status prepStatus = client.prepareSimulationdata(projectId);
		assertTrue(prepStatus == Status.OK);
		
		Status prepareStatus = null;
		int timeInterval = 1000*60*30; // 30 minutes waiting at all
		while((prepareStatus == null) || (prepareStatus != Status.DONE && timeInterval > 0)) {
			prepareStatus = client.checkCloudNode(projectId, Type.PREPARE_DATA);
			int counter = 1000*60*5;  // wait 5 minutes
			Thread.sleep(counter);
			timeInterval -= counter;
		}
		if(prepareStatus == Status.DONE) {		
			Status status = client.startPassiveSimulation(projectId);
			assertTrue(status == Status.OK);
			System.out.println(status);
		}
		return projectId;
	}

	@Test
	public void testStartAndGetPassiveSimulationResults() throws Exception {
		String projectId = startPassiveSimulation();
		InputStream stream = client.getPassiveSimulationResults(projectId);
		assertNotNull(stream);
		File outputFile = new File(OUTPUT_DIR+UUID.randomUUID().toString()+".zip");
		FileUtils.copyInputStreamToFile(stream, outputFile);
	}
	
	@Test
	public void testGetPassiveSimulationResults() throws Exception {
		InputStream stream = client.getPassiveSimulationResults("c2c440b09b04b5467f79eb7fdb9cf2f3492bbc83");
		assertNotNull(stream);
		File outputFile = new File(OUTPUT_DIR+UUID.randomUUID().toString()+".zip");
		FileUtils.copyInputStreamToFile(stream, outputFile);
		System.out.println("Results written to: "+outputFile);
	}
	
	@Test
	public void testGetSimulationStatus() throws Exception {
		while(true) {
			Status status = client.checkCloudNode("c2c440b09b04b5467f79eb7fdb9cf2f3492bbc83", Type.PASSIVE_SIMULATION);
			assertTrue("Not running: "+status, status == Status.RUNNING);
			System.out.println("["+System.currentTimeMillis()+"] Sim: is "+status);
			Thread.sleep(10000);
		}
	}
	
	@Test
	public void testStartPassiveSimulationAndCheckSimulationStatus() throws Exception {
		String projectId = startPassiveSimulation();
		
		Status status = client.checkCloudNode(projectId, Type.PASSIVE_SIMULATION);
		System.out.println("Is running?: "+status);
	}

}
