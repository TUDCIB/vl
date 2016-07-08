package de.tudresden.bau.cib.vl.core.simulation.energy.simmatrix;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

import de.tudresden.bau.cib.simmatrix.DocumentRoot;
import de.tudresden.bau.cib.simmatrix.MaterialUnit;
import de.tudresden.bau.cib.simmatrix.TCombination;
import de.tudresden.bau.cib.simmatrix.TCombinations;
import de.tudresden.bau.cib.simmatrix.TFloatWithUnits;
import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.simmatrix.TTarget;
import de.tudresden.bau.cib.simmatrix.TTargetList;
import de.tudresden.bau.cib.simmatrix.TTargets;
import de.tudresden.bau.cib.simmatrix.TVariables;
import de.tudresden.bau.cib.simmatrix.TVariant;
import de.tudresden.bau.cib.simmatrix.TWindowType;
import de.tudresden.bau.cib.simmatrix.TWindowTypeVariant;
import de.tudresden.bau.cib.simmatrix.TWindowTypes;
import de.tudresden.bau.cib.simmatrix.TargetType;
import de.tudresden.bau.cib.simmatrix.simmatrixFactory;
import de.tudresden.bau.cib.simmatrix.simmatrixPackage;
import de.tudresden.bau.cib.simmatrix.util.simmatrixResourceFactoryImpl;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.mock.StepParserMock;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.DataResourceService;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-service-context.xml")
public class SimMatrixTest {
	
	private static final String PACKAGE = "simmatrix/";
	private static final String FILE_PATH = PACKAGE+"my.simmatrix";
	private static final String PATH_TO_IFC_FILE = PACKAGE+"ISES_2ndLSB.ifc";
	private static final String workingDirectory = "target/repository";
	
	private static simmatrixFactory factory;
	
	
	@Autowired
	DataResourceService dataResourceServiceClient;
	
	private ResourceSet resourceSet;
	private Ifc2x3DataModelProxy ifcModel;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = simmatrixFactory.eINSTANCE;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// Create a resource set to hold the resources.
		resourceSet = new ResourceSetImpl();
		
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
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private static void printDiagnostic(Diagnostic diagnostic, String indent) {
		System.out.print(indent);
		System.out.println(diagnostic.getMessage());
		for (Diagnostic child : diagnostic.getChildren()) {
			printDiagnostic(child, indent + "  ");
		}
	}
	
	@Test
	public void testReadContent() throws Exception {
		// Construct the URI for the instance file.
		// The argument is treated as a file path only if it denotes an existing file.
		// Otherwise, it's directly treated as a URL.
		//
		URL url = this.getClass().getClassLoader().getResource(FILE_PATH);
		URI uri = URI.createURI(url.toURI().toString());

		// Demand load resource for this file.
		//
		Resource resource = resourceSet.getResource(uri, true);
		System.out.println("Loaded " + uri);

		// Validate the contents of the loaded resource.
		//
		for (EObject eObject : resource.getContents()) {
			Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				printDiagnostic(diagnostic, "");
			}
		}
	}
	
	private void loadIfcModel() throws ParsingException, IOException {
		Parser parser = StepParserMock.createIfcParser(workingDirectory);
		URL url = this.getClass().getClassLoader().getResource(PATH_TO_IFC_FILE);
		ifcModel = (Ifc2x3DataModelProxy) parser.parse(url.openStream());
		assertNotNull(ifcModel);
	}
	
	private List<String> retrieveSpaceGuids(int numberOfSpaces) throws IfcException {
		List<String> guids = new ArrayList<String>();
		Map<String, EIfcbuilding> buildings = ifcModel.getBuildings();
		assertTrue(buildings.size() > 0);
		EIfcspace[] spaces = ifcModel.getSpacesOfBuilding(buildings.keySet().iterator().next());
		for(int i = 0; i < spaces.length; i++) {
			if(i < numberOfSpaces) {
				String guid = ifcModel.getGlobalId(spaces[i]);
				guids.add(guid);
			}
		}
		return guids;
	}
	
	@Test
	public void testCreateSimMatrix() throws Exception {
		// load IFC file
		loadIfcModel();		
		
		// create simmatrix
		Resource resource = resourceSet.createResource(URI.createURI("http:///My.simmatrix"));
		
		DocumentRoot documentRoot = factory.createDocumentRoot();		
		
		TSimulationMatrix matrix = factory.createTSimulationMatrix();
		matrix.setId("1");
		matrix.setType("SensitivityAnalysis");
		
		List<String> spaceGuids = retrieveSpaceGuids(3);
		
		// Targets
		TTargets targets = factory.createTTargets();
		//targetlist 1
		TTargetList targetList1 = factory.createTTargetList();
		targetList1.setId("abcd");
		for(String spaceGuid : spaceGuids) {
			TTarget target = factory.createTTarget();
			target.setType(TargetType.SPACE);
			target.setValue(spaceGuid);
			targetList1.getTarget().add(target);
		}
		targets.getTargetList().add(targetList1);
		// set targets
		matrix.setTargets(targets);
		
		// Variables
		TVariables variables = factory.createTVariables();
		
		// Window
		TWindowTypes windowTypes = factory.createTWindowTypes();
		TWindowType windowType1 = factory.createTWindowType();
		windowType1.setName("http://window1.xml");
		
		// Window variant1
		TWindowTypeVariant windowVariant1 = factory.createTWindowTypeVariant();
		windowVariant1.setId("windowVariant1");
		TFloatWithUnits transmittance1 = factory.createTFloatWithUnits();
		transmittance1.setUnit(MaterialUnit.WM2K);
		transmittance1.setValue(1.2);
		windowVariant1.setThermalTransmittance(transmittance1);
		windowType1.getWindowTypeVariant().add(windowVariant1);
		
		// Window variant2
		TWindowTypeVariant windowVariant2 = factory.createTWindowTypeVariant();
		windowVariant2.setId("windowVariant2");
		TFloatWithUnits transmittance2 = factory.createTFloatWithUnits();
		transmittance2.setUnit(MaterialUnit.WM2K);
		transmittance2.setValue(2.5);
		windowVariant2.setThermalTransmittance(transmittance2);
		windowType1.getWindowTypeVariant().add(windowVariant2);
		
		// Window variant3
		TWindowTypeVariant windowVariant3 = factory.createTWindowTypeVariant();
		windowVariant3.setId("windowVariant3");
		TFloatWithUnits transmittance3 = factory.createTFloatWithUnits();
		transmittance3.setUnit(MaterialUnit.WM2K);
		transmittance3.setValue(1.8);
		windowVariant3.setThermalTransmittance(transmittance3);
		windowType1.getWindowTypeVariant().add(windowVariant3);
		
		// add window variants
		windowTypes.getWindowType().add(windowType1);
		// add window types
		variables.setWindowTypeVariables(windowTypes);
		
		// set variables
		matrix.setVariables(variables);
		
		// combinations
		TCombinations combinations = factory.createTCombinations();
		
		// combination1
		TCombination combination1 = factory.createTCombination();
		combination1.setId("C_0000001");
		TVariant combiVariant1 = factory.createTVariant();
		combiVariant1.setVREF("windowVariant1");	
		combination1.getVariant().add(combiVariant1);
		combinations.getCombination().add(combination1);
		
		// combination2
		TCombination combination2 = factory.createTCombination();
		combination2.setId("C_0000002");
		TVariant combiVariant2 = factory.createTVariant();
		combiVariant2.setVREF("windowVariant2");	
		combination2.getVariant().add(combiVariant2);
		combinations.getCombination().add(combination2);
		
		// combination3
		TCombination combination3 = factory.createTCombination();
		combination3.setId("C_0000003");
		TVariant combiVariant3 = factory.createTVariant();
		combiVariant3.setVREF("windowVariant3");	
		combination3.getVariant().add(combiVariant3);	
		combinations.getCombination().add(combination3);
		
		// set combinations
		matrix.setCombinations(combinations);
		
		documentRoot.setSimulationMatrix(matrix);
		resource.getContents().add(documentRoot);

		resource.save(System.out, null);
	}
}
