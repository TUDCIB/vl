package de.tudresden.bau.cib.vl.core.model.ontology.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcspace;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.test.ActivitiRule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Derivation;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;

import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.mock.StepParserMock;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.parser.OwlParser;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.Reasoner;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.RuleSet;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.BIMOntoVocabulary;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;
import de.tudresden.bau.cib.vl.core.service.BusinessProcessService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti.cfg.xml")
public class OntologyServiceImplTest {

	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private BusinessProcessService businessProcess;
	
	@Autowired
	private OntologyService service;
	
	@Autowired
	private ConfigurationService configurationService;
	
//	@Autowired
//	private EnergyModelFactoryBusinessProxy energyModel;

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	private static Parser parser = null;
	private static String workingDirectoryPath = "target/data/repository";
	private static final String EXPORT_PATH ="target/export/";
	
	private static OwlParser owlParser;
	private OntologyModel dataModel;
	
	private static final String RESOURCES = "resources/";
	private static final String TEST_RESOURCES = "test/resources/";
	
	private static final String IFCOWL_FILE = RESOURCES+"BIMOnto.owl";
	private static final String eeBIM_INTERFACE_FILE = RESOURCES+"eeBIMOnto.owl";
	
	private Reasoner reasoner;
	private InfModel inferenceModel;
	
	private static final String QUICKSTART = "Quickstart_Project_2ndLSB";
	private static final String JUNGE_OPER = "ISES_Bestand_20131209_2ndLSB";
	private static final String ISES = "ISES_2ndLSB";
	
	private static final String ifcFile_QuickStart = TEST_RESOURCES+QUICKSTART+".ifc";
	private static final String ifcFile_ISES = TEST_RESOURCES+ISES+".ifc";
	private static final String ifcFile_Junge_Oper = TEST_RESOURCES+JUNGE_OPER+".ifc";
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File exportDir = new File(EXPORT_PATH);
		if(!exportDir.exists()) exportDir.mkdirs();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
//		owlParser = OwlParser.createParser(workingDirectoryPath+File.separator+"ontology");
		owlParser = service.createParser(OntologyService.OWL, workingDirectoryPath+File.separator+"ontology");
		
//		service = new OntologyServiceImpl();
		assertNotNull(service);
	}

	@After
	public void tearDown() throws Exception {	

	}
	
	@Test
	@Ignore
	public void testReadN3AndSaveXML() throws Exception {
		String inputFile = TEST_RESOURCES+"ISESOperUmbau.rdf";
		String filePath = "D:/ISESOperUmbau.rdf";
		
		OntologyModel model = owlParser.parse(inputFile);
		
		File file = new File(filePath);
		FileOutputStream fos = new FileOutputStream(file);
			
		model.getUnderlyingModel().write(fos, "RDF/XML");	
	}
	
	private void loadFile(String path) throws Exception {	
		List<String> owlPaths = new ArrayList<String>();
		owlPaths.add(IFCOWL_FILE);
		owlPaths.add(eeBIM_INTERFACE_FILE);
		
		if(path != null) {
			System.out.println("Load File: "+path+" with OWLs: "+owlPaths);
			dataModel = owlParser.parse(path);
			service.loadSchemesOfLinkedModels(dataModel, owlPaths.toArray(new String[owlPaths.size()]));
		} else {
			System.out.println("Create new file with OWLs: "+owlPaths);
			dataModel = owlParser.createModel(owlPaths.toArray(new String[owlPaths.size()]));
		}
		dataModel.setId(UUID.randomUUID().toString());		
	}
	
	private void saveFile(String name) throws Exception {
		String exportPath = EXPORT_PATH+name+".rdf";
		new File(exportPath).createNewFile();
		service.saveRDF(this.dataModel, exportPath);	
		File testFile = new File(exportPath);
		assertTrue(testFile.exists());
		System.out.println("EXPORTED TO "+exportPath);
	}
	
	private void printStatements(Resource S, Property p, Resource O, PrintWriter writer) {
	    for (StmtIterator i = inferenceModel.listStatements(S, p, O); i.hasNext(); ) {
	        Statement s = i.nextStatement();
	        System.out.println("Statement is "+s);
	        for (Iterator<Derivation> id = inferenceModel.getDerivation(s); id.hasNext(); ) {
	            Derivation deriv = id.next();
	            deriv.printTrace(writer, true);
	        }
	    }
	}
	
	@Test
	public void testCreateArchitecturalDomain() throws Exception {	
		loadFile(null);
		Ifc2x3DataModelProxy ifcModel = loadIfc(ifcFile_Junge_Oper);
		
		Map<String, EIfcbuilding> buildings = ifcModel.getBuildings();
		Set<String> guids = buildings.keySet();
		assertTrue(guids.size()>0);
		String buildingGuid = guids.iterator().next();
		Set<EIfcelement> elements = ifcModel.getElementsInBuilding(buildingGuid);
		int numberOfElements = elements.size();
		assertTrue(numberOfElements>0);
		
		//	TEST METHOD
		service.createArchitecturalDomain(ifcModel, dataModel);

		saveFile("WithoutJastor");
	}

	@Test
	public void testCreateArchitecturalDomainView() throws Exception {	
		loadFile(null);
		Ifc2x3DataModelProxy ifcModel = loadIfc(ifcFile_Junge_Oper);
		
		Map<String, EIfcbuilding> buildings = ifcModel.getBuildings();
		Set<String> guids = buildings.keySet();
		assertTrue(guids.size()>0);
		String buildingGuid = guids.iterator().next();
		Set<EIfcelement> elements = ifcModel.getElementsInBuilding(buildingGuid);
		int numberOfElements = elements.size();
		assertTrue(numberOfElements>0);
		
		//	TEST METHOD
		service.createArchitecturalDomainView(elements,ifcModel, dataModel);
		
		EIfcspace[] spaces = ifcModel.getSpacesOfBuilding(buildingGuid);
		int numberOfSpaces = spaces.length;
		
		//	TEST METHOD
		service.createArchitecturalDomainView(Arrays.asList(spaces), ifcModel, dataModel);

		saveFile("WithoutJastor");
	}
	
	@Test
	public void testGetAreas() throws Exception {
		Ifc2x3DataModelProxy ifcModel = loadIfc(ifcFile_Junge_Oper);
		List<EIfcspace> rooms = Arrays.asList(ifcModel.getSpaces("3jt73qsBn9S8d4xgLh1k6W"));
//		retrieve wall areas
		double buildingShellArea = ifcModel.calculateBuildingShellArea(rooms);
		assertTrue(buildingShellArea > 0);
		System.out.println("BuildingShell-Area: "+buildingShellArea);
//		retrieve window areas
		double windowArea = ifcModel.calculateWindowArea(rooms);
		assertTrue(windowArea > 0);
		System.out.println("Window-Area: "+windowArea);
	}
	
	private void loadModel(String name) throws Exception {
		loadFile(EXPORT_PATH+name+".rdf");
	}

	private Ifc2x3DataModelProxy loadIfc(String pathToFile) throws ParsingException, FileNotFoundException {
		parser = StepParserMock.createIfcParser(workingDirectoryPath);
		assertNotNull(parser);
		Ifc2x3DataModelProxy ifcModel = (Ifc2x3DataModelProxy) parser.parse(pathToFile);
		ifcModel.setId("1204");
		assertNotNull(ifcModel);
		return ifcModel;
	}
	
	private void setupReasoner(Model data, String pathToRules) throws FileNotFoundException, MalformedURLException {
		List<String> owlPaths = new ArrayList<String>();
		owlPaths.add(IFCOWL_FILE);
		owlPaths.add(eeBIM_INTERFACE_FILE);
		service.loadSchemesOfLinkedModels(dataModel, owlPaths.toArray(new String[owlPaths.size()]));
		
//		Map<String,String> uriMap = new HashMap<String, String>();
//		uriMap.put("ifcOWL", ifcOwlURI);
		
		reasoner = new Reasoner(dataModel);
		reasoner.init(new URL(pathToRules));
		
		inferenceModel = reasoner.getInferenceModel();
	}
	
	@Test
	public void testReasoningForValidity() throws Exception {
		loadModel(QUICKSTART);
		setupReasoner(dataModel.getUnderlyingModel(), "file:resources/rules/roomMapping_Quickstart.rules");
		
		ValidityReport report = reasoner.getValidityReport();
		boolean isValid = report.isValid();
		if(!isValid) {
			Iterator<Report> reportIterator = report.getReports();
			while(reportIterator.hasNext()) {
				Report r = reportIterator.next();
		        System.out.println(" - " + r);
			}
		}
		assertTrue(isValid);
	}
	
	@Test
	public void testReasoning_Quickstart() throws Exception {
		loadFile(null);
		Model data = dataModel.getUnderlyingModel();
		setupReasoner(data, "file:resources/rules/roomMapping_Quickstart.rules");

		Resource livingRoom = data.getResource("http://iai.org/ifcOWL#LivingRoom");
		assertNotNull(livingRoom);
		Resource stair = data.getResource("http://iai.org/ifcOWL#Stair");
		assertNotNull(stair);
		Resource passage = data.getResource("http://iai.org/ifcOWL#Passage");
		assertNotNull(passage);
		Resource technicalRoom = data.getResource("http://iai.org/ifcOWL#TechnicalRoom");
		assertNotNull(technicalRoom);
		PrintWriter writer = new PrintWriter(System.out);
		printStatements(null, null, livingRoom, writer);
		printStatements(null, null, stair, writer);
		printStatements(null, null, passage, writer);
		printStatements(null, null, technicalRoom, writer);
		
		writer.flush();
		
		saveFile(QUICKSTART);
	}
	
	@Test
	public void testReasoning_JungeOper() throws Exception {
		loadFile("target/export/WithoutJastor.rdf");
//		loadModel(JUNGE_OPER);

//		createGUI(ifcFile_Junge_Oper);
		
		Ifc2x3DataModelProxy ifcModel = loadIfc(ifcFile_Junge_Oper);
		service.createArchitecturalDomain(ifcModel, dataModel);
		
		RuleSet ruleSet = service.loadRuleSet(new File("resources/rules/roomMapping_JungeOper.rules"));
		
		Reasoner reasoner = service.applyRuleSet(dataModel, ruleSet);
		
		inferenceModel = reasoner.getInferenceModel();
		
		Resource livingRoom = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"LivingRoom");
		assertNotNull(livingRoom);
		Resource stair = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"Stair");
		assertNotNull(stair);
		Resource passage = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"Passage");
		assertNotNull(passage);
		Resource technicalRoom = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"TechnicalRoom");
		assertNotNull(technicalRoom);
		Resource workRoom = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"WorkRoom");
		assertNotNull(workRoom);
		Resource storage = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"Storage");
		assertNotNull(storage);
		PrintWriter writer = new PrintWriter(System.out);
		printStatements(null, null, livingRoom, writer);
		printStatements(null, null, stair, writer);
		printStatements(null, null, passage, writer);
		printStatements(null, null, technicalRoom, writer);
		printStatements(null, null, workRoom, writer);
		printStatements(null, null, storage, writer);
	}
	
	@Test
	public void testReasoning_JungeOper_ENEV() throws Exception {
//		loadFile(null);
		loadModel(JUNGE_OPER);

//		createGUI(ifcFile_Junge_Oper);
		
		Ifc2x3DataModelProxy ifcModel = loadIfc(ifcFile_Junge_Oper);
		service.createArchitecturalDomain(ifcModel, dataModel);
		
		RuleSet ruleSet = service.loadRuleSet(new File(RESOURCES+"rules/enev.rules"));
		
		Reasoner reasoner = service.applyRuleSet(dataModel, ruleSet);
		
		inferenceModel = reasoner.getInferenceModel();
		
		Resource livingRoom = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"LivingRoom");
		assertNotNull(livingRoom);
		Resource passage = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"Passage");
		assertNotNull(passage);
		PrintWriter writer = new PrintWriter(System.out);
		printStatements(null, null, livingRoom, writer);
		printStatements(null, null, passage, writer);
	}
	
	@Test
	public void testReasoningISES() throws Exception {
		loadFile(null);
		
//		createGUI(ifcFile_ISES);
		
		Ifc2x3DataModelProxy ifcModel = loadIfc(ifcFile_ISES);
		service.createArchitecturalDomain(ifcModel, dataModel);
		
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		RuleSet roomMappingRuleSet = service.loadRuleSet(new File(RESOURCES+"rules/roomMapping_ISES.rules"));
		RuleSet inferEeBimElementsRuleSet = service.loadRuleSet(new File(RESOURCES+"rules/inferEeBimElement.rules"));
		ruleSets.add(roomMappingRuleSet);
		ruleSets.add(inferEeBimElementsRuleSet);
		
		Reasoner reasoner = service.applyRuleSets(dataModel, ruleSets);
		inferenceModel = reasoner.getInferenceModel();
		inferenceModel = reasoner.getInferenceModel();
		
		Resource livingRoom = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"LivingRoom");
		assertNotNull(livingRoom);
		Resource stair = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"Stair");
		assertNotNull(stair);
		Resource passage = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"Passage");
		assertNotNull(passage);
		Resource technicalRoom = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"TechnicalRoom");
		assertNotNull(technicalRoom);
		Resource workRoom = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"WorkRoom");
		assertNotNull(workRoom);
		Resource storage = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"Storage");
		assertNotNull(storage);
		PrintWriter writer = new PrintWriter(System.out);
		printStatements(null, null, livingRoom, writer);
		printStatements(null, null, stair, writer);
		printStatements(null, null, passage, writer);
		printStatements(null, null, technicalRoom, writer);
		printStatements(null, null, workRoom, writer);
		printStatements(null, null, storage, writer);
		
		Resource outdoorWindow = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"OutdoorWindowStandardCase");
		assertNotNull(outdoorWindow);
		printStatements(null, null, outdoorWindow, writer);
		
		Resource indoorWindow = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"IndoorWindowStandardCase");
		assertNotNull(indoorWindow);
		printStatements(null, null, indoorWindow, writer);
		
		Resource outdoorWall = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"OutdoorWallStandardCase");
		assertNotNull(outdoorWall);
		printStatements(null, null, outdoorWall, writer);
		
		Resource outdoorSlab = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"OutdoorSlabStandardCase");
		assertNotNull(outdoorSlab);
		printStatements(null, null, outdoorSlab, writer);
		
		Resource facadeElement = inferenceModel.getResource(EeBIMOntoVocabulary.getURI()+"FacadeElementStandardCase");
		assertNotNull(facadeElement);
		printStatements(null, null, facadeElement, writer);

//		Set<String> guids = new HashSet<String>();
//		OntModel om = ((OntModel) infModel);
//		ExtendedIterator<Individual> individualIterator = om.listIndividuals(facadeElement);
//		while(individualIterator.hasNext()) {
//			Individual ind = individualIterator.next();
//			String uri = ind.getURI();
//			String globalId = uri.substring(uri.indexOf("::")+1);
//			System.out.println(globalId);
//			guids.add(globalId);
//		}
		
		List<String> guids = findFacadeElements(inferenceModel);
		System.err.println(guids);
		
//		select(guids.toArray(new String[guids.size()]));
		
		saveFile(ISES);
	}
	
	private List<String> findFacadeElements(Model model) {
		List<QuerySolution> resultList = null;
		String queryString = 
		"PREFIX ifcOWL: <"+BIMOntoVocabulary.getURI()+"> " +
		"PREFIX eeBIM: <"+EeBIMOntoVocabulary.getURI()+"> " +
		"PREFIX owl: <http://www.w3.org/2002/07/owl> " +
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns> " +
		"SELECT ?beGuid " +
		"WHERE {" +
		"      ?x				a 								eeBIM:FacadeElementStandardCase ;" +
		"	   					ifcOWL:GlobalId					?beGuidClass ." +
		"	   ?beGuidClass		ifcOWL:StringValue				?beGuid ." +	
		"      }";
		
		Query query = QueryFactory.create(queryString);
//
		// Execute the query and obtain results
		QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
		ResultSet results = queryExecution.execSelect();
		if(results.hasNext()) resultList = new ArrayList<QuerySolution>();
		while(results.hasNext()) resultList.add(results.next());
		queryExecution.close();	
		
		List<String> guids = new ArrayList<String>();
			
		for(QuerySolution qs : resultList) {
			Iterator<String> iterator = qs.varNames();
			while(iterator.hasNext()) {				
				String varName = iterator.next();
				RDFNode node = qs.get(varName);
				String value = null;
				if(!node.isResource()) {
					value = node.asLiteral().getString();
				} else {
					value = node.asResource().getURI();
				}
				guids.add(value);
			}
		}
		return guids;
	}

}