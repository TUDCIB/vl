package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.mock.StepParserMock;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.util.CombustibleResourceFactoryImpl;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.ConstructionTemplateDeserializer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.mock.OntologyParserMock;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.impl.EnergyResultServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:simulation-energy-test.xml")  
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class PostProcessingServiceTest {

//	@Autowired
	private static EnergyResultServiceImpl service;
//	@Autowired
//	private static TemplateService templateService;
//	@Autowired
//	private static EnergyKeyPerformanceIndicatorDao energyKeyPerformanceIndicatorDao;
//	@Autowired
//	private static Configuration configuration;
	
	private static Ifc2x3DataModelProxy ifcModel;
	private static OntologyModel ontologyModel;

	private static final String CONSTRUCTION_TPL_PATH	= "D:\\Daten\\tomcat-data\\vl\\resources\\DB_Constructions\\";
	private static final String TEST_RESOURCES_PATH		= "test/resources/nandrad/building/kassel/";
	private static final String RESULT_FILE_PATHS 		= TEST_RESOURCES_PATH+"103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB\\results\\";
	

	private static final String IFC_MODEL_PATH			= TEST_RESOURCES_PATH+"103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB.ifc";
//	private static final String IFC_MODEL_PATH			= TEST_RESOURCES_PATH+"104_FZKassel-Räume+Wände+Böden+Decken_Project-5+Roof-section_2ndLSB.ifc";
	private static final String ONTOLOGY_PATH 			= TEST_RESOURCES_PATH+"103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB.rdf";
	
	private static final String workingDirectory 		= "target/test/jsdai/repository";
	private static final String COMBUSTIBLE_PATH		= "test/resources/combustibles.xml";
	
	private static final String KASSEL_BUILDING_GUID 	= "1pfeQqqxfBtvRzniADCXt$";
	
	private static Parser parser;
	private static Parser ontologyParser;
	
	private List<File> energyResults;
	private SimulationProject simulation;
	private static List<Combustible> combustibles;
	private EnergyKeyPerformanceIndicators eKPIs;
	private List<ConstructionTemplate> constructionTemplates;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {	
		service = new EnergyResultServiceImpl();
		
		
		parser = StepParserMock.createIfcParser(workingDirectory);
		Ifc2x3DataModelProxy proxyModel = (Ifc2x3DataModelProxy) parser.parse(IFC_MODEL_PATH);
		ifcModel = proxyModel;
		assertNotNull(ifcModel);
		
		ontologyParser = OntologyParserMock.createParser(workingDirectory, "RDF/XML");
		ontologyModel = (OntologyModel) ontologyParser.parse(ONTOLOGY_PATH);
		
//		combustibles = CombustibleContainerDeserializer.deserialize(COMBUSTIBLE_PATH);
//		assertNotNull(combustibles);
//		assertTrue(combustibles.size() > 0);
		
		File file = new File(COMBUSTIBLE_PATH);
		ResourceSet resourceSet = new ResourceSetImpl();
//		programmatically registration of Ecore like in plugin.xml
		resourceSet.getPackageRegistry().put(CombustiblePackage.eINSTANCE.getNsURI(), CombustiblePackage.eINSTANCE);
		CombustiblePackage.eINSTANCE.eClass();
		CombustibleResourceFactoryImpl factory = new CombustibleResourceFactoryImpl();
		Map<String,Object> factoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		factoryMap.put("xmi", factory);
		
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource res = factory.createResource(uri);
		res.load(new FileInputStream(file), null);
//		Resource resource = resourceSet.getResource(uri, true);
		EList<EObject> contents = res.getContents();
		CombustibleContainer combustibleContainer = (CombustibleContainer) contents.get(0); //alle Brennwerte = Container | Combustible ein Brennelement

		combustibles = combustibleContainer.getCombustibles();
		assertNotNull(combustibles);
		assertTrue(combustibles.size() > 0);
	}
	
	public static void tearDownAfterClass() throws Exception {
		parser.stop();
	}

	@Before
	public void setUp() throws Exception {		
//		energyResults 					= new ArrayList<File>();
//		File resultDir 					= new File(RESULT_FILE_PATHS);
//		energyResults 					= Arrays.asList(resultDir.listFiles());
//		
//		assertNotNull(energyResults);
//		assertTrue(energyResults.size() > 0);
//		
//		simulation = new SimulationInformation();
//		simulation.setId(1);
//		
//		eKPIs = new EnergyKeyPerformanceIndicators();
//		eKPIs.setSimulationId(simulation.getId());
//		
//		eKPIs.setPlantExpeditureFigureHeating(1.23);
//		eKPIs.setPlantExpeditureFigureCooling(1.1);
//		
//		List<String> assignedCombustibleIds = new ArrayList<String>();
//		assignedCombustibleIds.add("_G4TlQdQsEeK6Xdd4dOLERQ");
//		assignedCombustibleIds.add("_xXbwoOrbEeK2gM1Azt9urQ");
//		assignedCombustibleIds.add("_yaYbYOrbEeK2gM1Azt9urQ");
//		assignedCombustibleIds.add("_zMXR8OrbEeK2gM1Azt9urQ");
//		assignedCombustibleIds.add("_5ckEsOrbEeK2gM1Azt9urQ"); // basic charge available
//		eKPIs.setCombustibleIds(assignedCombustibleIds);
//																						// factors: CO2	|	SO2	|	NOX
//		Map<String, Double> percentagePerHeatingCombustible = new HashMap<String, Double>(); 
//		percentagePerHeatingCombustible.put("_G4TlQdQsEeK6Xdd4dOLERQ", 70.0);				//		302	|	0.455	0.227		
//		percentagePerHeatingCombustible.put("_yaYbYOrbEeK2gM1Azt9urQ", 20.0);				//		438	|	2,564	0,458
//		percentagePerHeatingCombustible.put("_5ckEsOrbEeK2gM1Azt9urQ", 10.0);				//		41	|	0,68	0,799
//		Map<String, Double> percentagePerCoolingCombustible = new HashMap<String, Double>();
//		percentagePerCoolingCombustible.put("_G4TlQdQsEeK6Xdd4dOLERQ", 35.0);				//		302	|	0.455	0.227
//		percentagePerCoolingCombustible.put("_zMXR8OrbEeK2gM1Azt9urQ", 25.0);				//		451	|	0,339	0,911
//		percentagePerCoolingCombustible.put("_xXbwoOrbEeK2gM1Azt9urQ", 40.0);				//		438	|	2,245	0.249
//		eKPIs.setPercentagePerCoolingCombustible(percentagePerCoolingCombustible);
//		eKPIs.setPercentagePerHeatingCombustible(percentagePerHeatingCombustible);
//		
//		Integer eKPIId = energyKeyPerformanceIndicatorDao.insertEnergyKeyPerformanceIndicators(eKPIs);
//		assertTrue(eKPIId > 0);
		
		constructionTemplates = listConstructionTemplates();
//		constructionTemplates = templateService.listConstructionTemplates();
		assertNotNull(constructionTemplates);
	}
	
	@After
	public void tearDown() throws Exception {
		eKPIs = null;
	}
	
	private static List<ConstructionTemplate> listConstructionTemplates() throws ParsingException, IOException {
		String constructionTemplatesDirectory = CONSTRUCTION_TPL_PATH;
		Set<File> files = getFiles(new File(constructionTemplatesDirectory), "d6p", false);
		List<ConstructionTemplate> templates = null;
		for(File f : files) {
			ConstructionTemplate template = ConstructionTemplateDeserializer.deserialize(f.getAbsolutePath());
			if(template != null) {
				if(templates == null) templates = new ArrayList<ConstructionTemplate>();
				templates.add(template);
			}	
		}
		return templates;
	}
	
	private static Set<File> getFiles(File directory, String fileFormat, boolean recursively) {
		System.out.println("Retrieving files in directory: "+directory+" of file format: "+fileFormat+" " + (recursively ? " with sub sub directories" :""));
		Set<File> result = new HashSet<File>();
		if(!recursively) { // no recursion
//			search only in folders with the name of the file extension
//			File searchDir = new File(directory+File.separator+fileFormat.toString());
			if(directory.exists()){
				File[] files = directory.listFiles();
				for(File file : files) {
					String fileName = file.getName();
					int lastDot = fileName.lastIndexOf(".")+1;
					String extension = fileName.substring(lastDot);
					if(extension.equalsIgnoreCase(fileFormat)) {
						result.add(file);
					}
				}
			}
		} else { //		with recursion - it searches all folders to find the files with the given file format
			if(directory.exists()){
				File[] files = directory.listFiles();
				if(files != null) {
					for(File file : files) {
						if(file.isDirectory()) {
							result.addAll(getFiles(file, fileFormat, recursively));
						} else {
							String fileName = file.getName();
							int lastDot = fileName.lastIndexOf(".")+1;
							String extension = fileName.substring(lastDot);
							if(extension.equalsIgnoreCase(fileFormat)) {
								result.add(file);
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	@Test
	public void testHandleInvestmentCostsFor104_FZKassel() throws Exception {
		System.out.println("Used templates: "+constructionTemplates);
		for(int i = 0; i < 5; i++) {
		Double investmentCosts = service.calculateInvestmentCosts(KASSEL_BUILDING_GUID, ifcModel, ontologyModel, constructionTemplates);
		assertEquals(33276.9, investmentCosts.doubleValue(), 0.1);
		System.out.println("Costs = "+investmentCosts);
		}
	}
	
	@Test
	public void testHandleInvestmentCostsForLargeKassel() throws Exception {
		System.out.println("Used templates: "+constructionTemplates);
//		for(int i = 0; i < 5; i++) {
		Double investmentCosts = service.calculateInvestmentCosts(KASSEL_BUILDING_GUID, ifcModel, ontologyModel, constructionTemplates);
//		assertEquals(33276.9, investmentCosts.doubleValue(), 0.1);
		System.out.println("Costs = "+investmentCosts);
//		}
	}
}
