package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation.TYPE;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject.STATUS;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.mock.StepParserMock;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.impl.LocalEnergySimulationService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:simulation-energy-test.xml")  
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class LocalEnergySimulationServiceTest {
	
	final static String PROJECT_DIR = "D:\\Nutzer\\ken\\Workspaces\\VirtualLaboratory\\master\\core-energy\\resources\\IBK\\project\\";
	final static String SCRIPT_DIR = "D:\\Nutzer\\ken\\Workspaces\\VirtualLaboratory\\master\\core-energy\\resources\\IBK\\scripts\\";
	final static String BIN_DIR = "D:\\Nutzer\\ken\\Workspaces\\VirtualLaboratory\\master\\core-energy\\resources\\IBK\\bin\\";
	
	final static String RDF_GRAPH_PATH = PROJECT_DIR+"ISES_project.rdf";
	final static String IFC_FILE_PATH = PROJECT_DIR+"ISES_project.ifc";

	final static String NANDRAD_PASSIVE_PROJECT_PATH = PROJECT_DIR+"ISES_project_passive.xml";
	final static String SIMULATION_MATRIX_PATH = PROJECT_DIR+"SimMa/ISES_project.simmatrix";
	
	final static String TEST_PASSIVESIM_PROJECT = "test/resources/passivesimproject";
	final static String TEST_SENSITIVESIM_PROJECT = "test/resources/sensitivesimproject";
	
	final static String TEST_PASSIVESIM_SIMENVIRONMENT = TEST_PASSIVESIM_PROJECT+"/INPUT/simEnvironment/";
	final static String TEST_SENSITIVESIM_SIMENVIRONMENT = TEST_SENSITIVESIM_PROJECT+"/INPUT/simEnvironment/";
	
	final static String PASSIVESIM_PROJECT_RDF_GRAPH_PATH = TEST_PASSIVESIM_PROJECT+"/INPUT/Small.rdf";
	final static String PASSIVESIM_PROJECT_IFC_FILE_PATH = TEST_PASSIVESIM_PROJECT+"/INPUT/Small.ifc";
	
	final static String SENSITIVESIM_PROJECT_NANDRAD_MODEL_PATH = TEST_SENSITIVESIM_PROJECT+"/INPUT/ISES_project.xml";
	final static String SENSITIVESIM_PROJECT_RDF_GRAPH_PATH = TEST_SENSITIVESIM_PROJECT+"/INPUT/Small.rdf";
	final static String SENSITIVESIM_PROJECT_IFC_FILE_PATH = TEST_SENSITIVESIM_PROJECT+"/INPUT/Small.ifc";
	final static String SENSITIVESIM_PROJECT_MATRIX_FILE_PATH = TEST_SENSITIVESIM_PROJECT+"/INPUT/25ec292d-9e33-4ad1-96a6-6aecaee72ced.simmatrix";
	
	@Autowired
	LocalEnergySimulationService energyService;
	@Autowired
	OntologyService ontologyService;
	@Autowired
	TemplateService templateService;
	@Autowired
	FileService fileService;
	
	private static final String workingDirectory = "D:\\Nutzer\\ken\\data\\repository";
	private static Parser parser;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		parser = StepParserMock.createIfcParser(workingDirectory);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		assertNotNull(energyService);
		assertNotNull(ontologyService);
		assertNotNull(templateService);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testStartBim2SimAndPassivePreparation() throws InterruptedException, IOException {	
		final File nandradProjectFile = new File(SENSITIVESIM_PROJECT_NANDRAD_MODEL_PATH);
		// delete Nandrad project file
		if(nandradProjectFile.exists()) nandradProjectFile.delete();
		
		String command = "cmd /c start /WAIT java -jar Bim2Sim.jar";
		Process bim2simProcess = Runtime.getRuntime().exec(command, null, new File(BIN_DIR+File.separator+"Bim2Sim"));						

		InputStream in = bim2simProcess.getInputStream();
		InputStream err = bim2simProcess.getErrorStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while((line = reader.readLine())!= null) {
			System.out.println(line);
		}
		BufferedReader errreader = new BufferedReader(new InputStreamReader(err));
		String errline = null;
		while((errline = errreader.readLine())!= null) {
			System.err.println(errline);
		}
		bim2simProcess.waitFor();
		
		assertTrue(nandradProjectFile.exists());
		System.out.println("Nandrad Project file stored on: "+nandradProjectFile);
		
		final File nandradPassiveProjectFile = new File(NANDRAD_PASSIVE_PROJECT_PATH);
		// delete Nandrad passive project file
		if(nandradPassiveProjectFile.exists()) nandradPassiveProjectFile.delete();
		
		// start passive simulation preparation
		final String scriptFile = SCRIPT_DIR+"passive_simulation_start.py";
		assertTrue(new File(scriptFile).exists());
		
		String mainFile = scriptFile;
		
		ProcessBuilder pb = new ProcessBuilder("python", mainFile);
		pb.directory(new File(PROJECT_DIR));
		
		Process process = pb.start();
		process.waitFor();
		
		assertTrue(nandradPassiveProjectFile.exists());
		System.out.println("Nandrad passive project file stored on: "+nandradPassiveProjectFile);
		// delete Nandrad project file
//		nandradProjectFile.deleteOnExit();
	}
	
	@Test
	public void testStartPassiveSimulation() throws Exception {
		SimulationProject simulationProject = createTestPassiveSimulationProject();
		energyService.startPassiveSimulation(9999, simulationProject);
	}
	
	@Test
	public void testStartSensitivityAnalysis() throws Exception {	
		// first delete already existing sensitivity project
		File sensitiveSimProject = new File(TEST_SENSITIVESIM_SIMENVIRONMENT);
		FileService.deleteFile(sensitiveSimProject);
		SimulationProject passiveProject = createTestPassiveSimulationProject();
		assertNotNull(passiveProject);
		
		SimulationProject sensitivityProject = createTestSensitivitySimulationProject();
		assertNotNull(sensitivityProject);
		
		energyService.copyContentFromPassiveProjectToSensitiveProject(passiveProject, sensitivityProject);
		
		energyService.startSensitivityAnalysis(sensitivityProject);
	}
	
	private SimulationProject createTestPassiveSimulationProject() {
		FileInformation ifcFile = new FileInformation();
		ifcFile.setFilePath(PASSIVESIM_PROJECT_IFC_FILE_PATH);
		ifcFile.setId(1);
		ifcFile.setFileType(TYPE.IFC);
		
		FileInformation rdfFile = new FileInformation();
		rdfFile.setFilePath(PASSIVESIM_PROJECT_RDF_GRAPH_PATH);
		rdfFile.setId(2);
		rdfFile.setFileType(TYPE.RDF);
		
		SimulationProject simulationProject = new SimulationProject();
		simulationProject.setId(1);
		simulationProject.addInputFiles(ifcFile);
		simulationProject.addInputFiles(rdfFile);
		simulationProject.setPathDirectory(TEST_PASSIVESIM_PROJECT);
		return simulationProject;
	}
	
	private SimulationProject createTestSensitivitySimulationProject() {
		FileInformation ifcFile = new FileInformation();
		ifcFile.setFilePath(SENSITIVESIM_PROJECT_IFC_FILE_PATH);
		ifcFile.setId(1);
		ifcFile.setFileType(TYPE.IFC);
		
		FileInformation rdfFile = new FileInformation();
		rdfFile.setFilePath(SENSITIVESIM_PROJECT_RDF_GRAPH_PATH);
		rdfFile.setId(2);
		rdfFile.setFileType(TYPE.RDF);
		
		FileInformation matrixFile = new FileInformation();
		matrixFile.setFilePath(SENSITIVESIM_PROJECT_MATRIX_FILE_PATH);
		matrixFile.setId(3);
		matrixFile.setFileType(TYPE.SIMMATRIX);
		
		FileInformation nandradSimModelFile = new FileInformation();
		nandradSimModelFile.setFilePath(SENSITIVESIM_PROJECT_NANDRAD_MODEL_PATH);
		nandradSimModelFile.setId(4);
		nandradSimModelFile.setFileType(TYPE.NANDRAD);
		
		SimulationProject simulationProject = new SimulationProject();
		simulationProject.setId(1);
		simulationProject.addInputFiles(ifcFile);
		simulationProject.addInputFiles(rdfFile);
		simulationProject.addInputFiles(matrixFile);
		simulationProject.addInputFiles(nandradSimModelFile);
		simulationProject.setPathDirectory(TEST_SENSITIVESIM_PROJECT);
		return simulationProject;
	}
	
	@Test
	public void testStartPassiveSimulationAndMakePostProcessing() throws Exception {
		int userId = 9999;
		SimulationProject simulationProject = createTestPassiveSimulationProject();
		
		energyService.startPassiveSimulation(userId, simulationProject);
		System.out.println("["+new Date()+"] Started simulation");
		while(simulationProject.getSimulationStatus() != STATUS.COMPLETED) {
			Thread.sleep(10000*6);
			energyService.storeSimulationResultsInSimulationProject(userId, simulationProject);
			System.out.println("["+new Date()+"] Waited for one minute");
		}
	}
	
	@Test
	public void testStartPostProcessing() throws Exception {
		FileInformation ifcFile = new FileInformation();
		ifcFile.setFilePath(PASSIVESIM_PROJECT_IFC_FILE_PATH);
		ifcFile.setId(1);
		ifcFile.setFileType(TYPE.IFC);
		
		FileInformation rdfFile = new FileInformation();
		rdfFile.setFilePath(PASSIVESIM_PROJECT_RDF_GRAPH_PATH);
		rdfFile.setId(2);
		rdfFile.setFileType(TYPE.RDF);
		
		SimulationProject simulationProject = new SimulationProject();
		simulationProject.setId(1);
		simulationProject.setRemoteId("D:\\Daten\\tomcat-data\\vl\\temp\\1429087441905_1");
//		simulationProject.addInputFiles(ifcFile);
//		simulationProject.addInputFiles(rdfFile);
		simulationProject.setPathDirectory("D:\\Daten\\tomcat-data\\vl\\temp\\1429087441905_1");
		
		String tmpDirPath = simulationProject.getRemoteId();
		final String SCRIPT_DIR = tmpDirPath+File.separator+"scripts";	

		final String scriptFile = SCRIPT_DIR+File.separator+"passive_simulation_end.py";
		
		ProcessBuilder pb = new ProcessBuilder("python", scriptFile);
		
		Process process = pb.start();
		process.waitFor();
	}

	@Test
	public void testExecutePython() {
		PythonInterpreter python = new PythonInterpreter();
		 
		int number1 = 10;
		int number2 = 32;
		 
		python.set("number1", new PyInteger(number1));
		python.set("number2", new PyInteger(number2));
		python.exec("number3 = number1+number2");
		PyObject number3 = python.get("number3");
		System.out.println("val : "+number3.toString());
		
		// script file
		python.execfile("test/resources/python/test.py");
	}

}
