package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcspace;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.simmatrix.TimePeriod;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;
import de.tudresden.bau.cib.simmatrix.util.simmatrixResourceFactoryImpl;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.mock.StepParserMock;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.DataResourceService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:simulation-energy-test.xml")
public class SimulationMatrixServiceTest {

	private static final String PACKAGE = "simmatrix/";
	private static final String FILE_PATH = PACKAGE+"my.simmatrix";
	private static final String PATH_TO_IFC_FILE = PACKAGE+"ISES_2ndLSB.ifc";
	private static final String workingDirectory = "target/repository";
	
	
	@Autowired
	SimulationMatrixService simulationMatrixService;
	
	@Autowired
	DataResourceService dataResourceServiceClient;
	
	
	private Ifc2x3DataModelProxy ifcModel;
	private List<ConstructionTemplate> constructions;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

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
	
	private void loadIfcModel() throws ParsingException, IOException {
		Parser parser = StepParserMock.createIfcParser(workingDirectory);
		URL url = this.getClass().getClassLoader().getResource(PATH_TO_IFC_FILE);
		ifcModel = (Ifc2x3DataModelProxy) parser.parse(url.openStream());
		assertNotNull(ifcModel);
	}
	
	private void loadConstructions() {
		constructions = dataResourceServiceClient.listConstructionResources();
		assertTrue(constructions.size() > 0);
	}
	
	private List<EIfcspace> retrieveSpaces(int numberOfSpaces) throws IfcException {
		Map<String, EIfcbuilding> buildings = ifcModel.getBuildings();
		assertTrue(buildings.size() > 0);
		EIfcspace[] spaces = ifcModel.getSpacesOfBuilding(buildings.keySet().iterator().next());
		List<EIfcspace> list = Arrays.asList(spaces);
		Collections.shuffle(list);
		
		return list.subList(0, numberOfSpaces);
	}
	
	private List<ConstructionTemplate> retrieveRandomConstructions(int numberOfConstructions) {
		Collections.shuffle(constructions);
		return constructions.subList(0, numberOfConstructions);
	}

	@Test
	public void testBuildEmptyMatrix() throws Exception {
		TSimulationMatrix matrix = simulationMatrixService.createMatrix("1");
		assertNotNull(matrix);
	}
	
	@Test
	public void testBuildMatrixWithTargets() throws Exception {
		loadIfcModel();
		
		URL url = this.getClass().getClassLoader().getResource(FILE_PATH);
		
		java.net.URI uri = url.toURI();
		TSimulationMatrix matrix = simulationMatrixService.createMatrix("1");
		assertNotNull(matrix);
		
		List<EIfcspace> spaces = retrieveSpaces(3);		
		simulationMatrixService.addTargets(spaces, matrix);
		
		List<EIfcspace> spaces2 = retrieveSpaces(5);		
		simulationMatrixService.addTargets(spaces2, matrix);

		simulationMatrixService.saveMatrix(uri, matrix);
		testReadContent();
	}
	
	@Test
	public void testCreateStochasticConstructionTypeVariants() throws Exception {
		System.out.println("####################");
		System.out.println("Create stochastic construction type variants");
		System.out.println();
		TSimulationMatrix matrix = simulationMatrixService.createMatrix("1");
		assertNotNull(matrix);
		
		double meanValue = 1.2;
		double[] samples = dataResourceServiceClient.generateSamples(
				meanValue, 
				0.3, 
				13);
		MaterialTemplate tpl1 = new MaterialTemplate();
		tpl1.setSourceFileUri("http://mat1");
		MaterialTemplate tpl2 = new MaterialTemplate();
		tpl2.setSourceFileUri("http://mat2");
		MaterialLayer layer1 = new MaterialLayer();
		layer1.setThickness(0.3f);
		layer1.setUnit("m");
		layer1.setTemplate(tpl1);
		MaterialLayer layer2 = new MaterialLayer();
		layer2.setThickness(0.15f);
		layer2.setUnit("m");
		layer2.setTemplate(tpl2);
		Map<Integer, MaterialLayer> layers = new HashMap<Integer, MaterialLayer>();
		layers.put(0, layer1);
		layers.put(2, layer2);
		for(int i = 0; i < samples.length; i++) {
			double sample = samples[i];
			layer1.setThickness((float)sample);
			// add window type to matrix
			simulationMatrixService.addConstructionTypeVariant("http://blablubb.de", layers, matrix);
		}
		URL url = this.getClass().getClassLoader().getResource(FILE_PATH);
		
		java.net.URI uri = url.toURI();
		simulationMatrixService.saveMatrix(uri, matrix);
		File outputFile = new File(uri.getPath());
		assertTrue(outputFile.exists());
		System.out.println("Saved to: "+uri);
		System.out.println("#####################");
	}
	
	@Test
	public void testCreateStochasticWindowTypeVariants() throws Exception {
		System.out.println("####################");
		System.out.println("Create stochastic window type variants");
		System.out.println();
		TSimulationMatrix matrix = simulationMatrixService.createMatrix("1");
		assertNotNull(matrix);
		
		double meanValue = 1.2;
		double[] samples = dataResourceServiceClient.generateSamples(
				meanValue, 
				0.3, 
				13);
		for(int i = 0; i < samples.length; i++) {
			double sample = samples[i];
			// add window type to matrix
			String ctvId = simulationMatrixService.addWindowTypeVariant("http://blablubb.de", 
					""+sample, "Frac", 
					null, null,
					null, null,
					null, null,
					null, null,
					matrix);
		}
		URL url = this.getClass().getClassLoader().getResource(FILE_PATH);
		
		java.net.URI uri = url.toURI();
		simulationMatrixService.saveMatrix(uri, matrix);
		File outputFile = new File(uri.getPath());
		assertTrue(outputFile.exists());
		System.out.println("Saved to: "+uri);
		System.out.println("#####################");
	}
	
	@Test
	public void testBuildMatrixWithConstructionVariants() throws Exception {
		loadConstructions();
		
		URL url = this.getClass().getClassLoader().getResource(FILE_PATH);
		
		java.net.URI uri = url.toURI();
		TSimulationMatrix matrix = simulationMatrixService.createMatrix("1");
		assertNotNull(matrix);

		List<ConstructionTemplate> randomConstructions = retrieveRandomConstructions(2);
		for(ConstructionTemplate constructionResource : randomConstructions) {
			simulationMatrixService.addConstructionTypeVariant(constructionResource, matrix);
		}

		simulationMatrixService.saveMatrix(uri, matrix);
		testReadContent();
	}

	@Test
	public void testReadContent() throws Exception {
		// Create a resource set to hold the resources.
		ResourceSet resourceSet = new ResourceSetImpl();
		
		// Register the appropriate resource factory to handle all file extensions.
		//
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
			(Resource.Factory.Registry.DEFAULT_EXTENSION, 
			 new simmatrixResourceFactoryImpl());

		// Register the package to ensure it is available during loading.
		//
		resourceSet.getPackageRegistry().put
			(simmatrixPackage.eNS_URI, 
			 simmatrixPackage.eINSTANCE);
		
		// Construct the URI for the instance file.
		// The argument is treated as a file path only if it denotes an existing file.
		// Otherwise, it's directly treated as a URL.
		//
		URL url = this.getClass().getClassLoader().getResource(FILE_PATH);
		URI uri = URI.createURI(url.toURI().toString());

		// Demand load resource for this file.
		//
		Resource resource = resourceSet.getResource(uri, true);
		System.out.println("Loaded " + uri + " to validate it against the scheme");

		// Validate the contents of the loaded resource.
		boolean diagnosticCheckSuccessfully = true;
		for (EObject eObject : resource.getContents()) {
			Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				printDiagnostic(diagnostic, "");
				diagnosticCheckSuccessfully = false;
			}
		}
		if(!diagnosticCheckSuccessfully) {
			fail("Check failed --> look in diagnostics output");
		}
	}
	
	@Test
	public void testBuildMatrixWithOccupancyVariants() throws Exception {
		System.out.println("####################");
		System.out.println("Create occupancy type variants");
		System.out.println();
		TSimulationMatrix matrix = simulationMatrixService.createMatrix("1");
		assertNotNull(matrix);
		
		double meanValue = 1.2;
		double[] samples = dataResourceServiceClient.generateSamples(
				meanValue, 
				0.3, 
				13);
		for(int i = 0; i < samples.length; i++) {
			double sample = samples[i];
			String id = simulationMatrixService.addOccupancyTypeVariant("http://blablubb.de", TimePeriod.DAILY, null, matrix);
		}
		URL url = this.getClass().getClassLoader().getResource(FILE_PATH);
		
		java.net.URI uri = url.toURI();
		simulationMatrixService.saveMatrix(uri, matrix);
		File outputFile = new File(uri.getPath());
		assertTrue(outputFile.exists());
		System.out.println("Saved to: "+uri);
		System.out.println("#####################");
	}
	
	private static void printDiagnostic(Diagnostic diagnostic, String indent) {
		System.out.print(indent);
		System.out.println(diagnostic.getMessage());
		for (Diagnostic child : diagnostic.getChildren()) {
			printDiagnostic(child, indent + "  ");
		}
	}
}
