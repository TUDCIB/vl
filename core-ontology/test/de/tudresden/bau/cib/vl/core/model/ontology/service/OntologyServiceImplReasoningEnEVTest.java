package de.tudresden.bau.cib.vl.core.model.ontology.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jsdai.SIfc2x3.AIfcobject;
import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfccolumn;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcelementquantity;
import jsdai.SIfc2x3.EIfcinternalorexternalenum;
import jsdai.SIfc2x3.EIfcobject;
import jsdai.SIfc2x3.EIfcphysicalquantity;
import jsdai.SIfc2x3.EIfcpropertysetdefinition;
import jsdai.SIfc2x3.EIfcquantityarea;
import jsdai.SIfc2x3.EIfcreldefinesbyproperties;
import jsdai.SIfc2x3.EIfcrelspaceboundary;
import jsdai.SIfc2x3.EIfcslab;
import jsdai.SIfc2x3.EIfcwall;
import jsdai.SIfc2x3.EIfcwindow;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Derivation;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import de.tudresden.bau.cib.model.utility.IterableAggregate;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.mock.StepParserMock;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.parser.OwlParser;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.Reasoner;
import de.tudresden.bau.cib.vl.core.model.ontology.reasoning.RuleSet;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.EeBIMOntoVocabulary;
import de.tudresden.bau.cib.vl.core.model.ontology.vocabulary.BIMOntoVocabulary;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti.cfg.xml")
public class OntologyServiceImplReasoningEnEVTest {

	
	@Autowired
	private OntologyService service;
	
	@Autowired
	private ConfigurationService configurationService;
	
	private static Parser parser = null;
	private static String workingDirectoryPath = "target/data/repository";
	private static final String EXPORT_PATH ="target/export/";
	
	private static OwlParser owlParser;
	private OntologyModel dataModel;
	
	private static final String RESOURCES = "resources/";
	private static final String TEST_RESOURCES = "test/resources/";
	private static final String RULES = RESOURCES+"rules/";
	
	private static final String IFCOWL_FILE = RESOURCES+"BIMOnto.owl";
	private static final String EEBIMOWL_FILE = RESOURCES+"eeBIMOnto.owl";
	
	private Reasoner reasoner;
	private InfModel inferenceModel;
	
	private static final String ifcFile_ISES = TEST_RESOURCES+"ISES_2ndLSB.ifc";
	private static final String owlFile_ISES = TEST_RESOURCES+"ises.rdf";
	private static final String IFC_MODEL_ID = "57";
	private static final String BUILDING_GUID = "0fj6JmRC91lhUssOKcXMNY";
	private static final String WALL_GUID = "3QU4OQnQ9FUw4KtJgRe4Hs";
	private static final String WINDOW_GUID = "3L$Gioz8P6AA2YhRozx9cc";
	private static final String SPACE_GUID = "3UsjHDOJHATgTz43LEqtic";
	
//	private static final String ifcFile_ISES = TEST_RESOURCES+"140910Kindergartenneu2ndLSB.ifc";
//	private static final String owlFile_ISES = EXPORT_PATH+"140910Kindergartenneu2ndLSB.rdf";
//	private static final String IFC_MODEL_ID = "1304";
//	private static final String BUILDING_GUID = "0xFpLR8lDF$vqrT$hgVAuB";
//	private static final String WALL_GUID = "2gAYRNCMT4zBDWEH4sIrSS";
//	private static final String WINDOW_GUID = "3LkgWxforBjxId6zIFJEvB";
//	private static final String SPACE_GUID = "2b7gL72598LurRWlx3RsoP";
	
	private static final String ENERGY_SAVINGS_POSSIBLE_CHECK_RULES = RULES+"energySavingsPossibleCheck.rules";
	private static final String SIMPLIFIED_ENEV_CHECK_RULES = RULES+"enev.rules";
	private static final String AREA_RULES = RULES+"area.rules";
	private static final String UVALUE_CHECK_RULES = RULES+"constructionCheckEnEV.rules";
	
	
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
	
	private void loadFile(String path) throws Exception {	
		List<String> owlPaths = new ArrayList<String>();
		owlPaths.add(IFCOWL_FILE);
		owlPaths.add(EEBIMOWL_FILE);
		
		if(path != null) {
			System.out.println("Load File: "+path);
			dataModel = owlParser.parse(path);
//			dataModel.loadSchemesOfLinkedModels(owlPaths.toArray(new String[owlPaths.size()]));
		} else {
			System.out.println("Create new file");
			dataModel = owlParser.createModel(owlPaths.toArray(new String[owlPaths.size()]));
		}
		dataModel.setId(UUID.randomUUID().toString());		
	}

	private Ifc2x3DataModelProxy loadIfc(String pathToFile) throws ParsingException, FileNotFoundException {
		parser = StepParserMock.createIfcParser(workingDirectoryPath);
		assertNotNull(parser);
		Ifc2x3DataModelProxy ifcModel = (Ifc2x3DataModelProxy) parser.parse(pathToFile);
		ifcModel.setId(IFC_MODEL_ID);
		assertNotNull(ifcModel);
		return ifcModel;
	}
	
	private void saveFile(String exportPath) throws Exception {
		new File(exportPath).createNewFile();
		service.saveRDF(this.dataModel, exportPath);	
		File testFile = new File(exportPath);
		assertTrue(testFile.exists());
		System.out.println("EXPORTED TO "+exportPath);
	}
	
	private void setupReasoner(Model data, String pathToRules) throws FileNotFoundException, MalformedURLException {
		List<String> owlPaths = new ArrayList<String>();
		owlPaths.add(EEBIMOWL_FILE);
		owlPaths.add(IFCOWL_FILE);
		service.loadSchemesOfLinkedModels(dataModel, owlPaths.toArray(new String[owlPaths.size()]));
		
//		final String ifcOwlURI = "http://iai.org/ifcOWL#";	
//		Map<String,String> uriMap = new HashMap<String, String>();
//		uriMap.put("ifcOWL", ifcOwlURI);
		
		reasoner = new Reasoner(dataModel);
		reasoner.init(new URL(pathToRules));	
		inferenceModel = reasoner.getInferenceModel();
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
	
	private void saveInferredModel(String name) throws IOException {
		String exportPath = EXPORT_PATH+name+".rdf";
		new File(exportPath).createNewFile();
		File file = new File(exportPath);
		FileOutputStream fos = new FileOutputStream(file);
			
		inferenceModel.write(fos, "N3");	
		System.out.println("INF-MODEL EXPORTED TO "+exportPath);
	}
	
	@Test
	public void testCreateTestData() throws Exception {	
		loadFile(null);
		Ifc2x3DataModelProxy ifcModel = loadIfc(ifcFile_ISES);
		
		Map<String, EIfcbuilding> buildings = ifcModel.getBuildings();
		Set<String> guids = buildings.keySet();
		assertTrue(guids.size()>0);
		String buildingGuid = guids.iterator().next();
		Set<EIfcelement> elements = ifcModel.getElementsInBuilding(buildingGuid);
		int numberOfElements = elements.size();
		assertTrue(numberOfElements>0);
		
		//	TEST METHOD
		service.createArchitecturalDomain(ifcModel, dataModel);
		
		createEnEVTestData();

		saveFile(owlFile_ISES);
	}
	
	@Test
	public void testReasoningD4E() throws Exception {	
		loadFile(owlFile_ISES);
		assertNotNull(dataModel);
		
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		RuleSet areaRuleSet = service.loadRuleSet(new File(AREA_RULES));
		ruleSets.add(areaRuleSet);
		
		Reasoner reasoner = service.applyRuleSets(dataModel, ruleSets);
		
		inferenceModel = reasoner.getInferenceModel();
		assertNotNull(inferenceModel);
		ValidityReport validityReport = reasoner.getValidityReport();
		Iterator<Report> reportIterator = validityReport.getReports();
		while(reportIterator.hasNext()) {
			Report report = reportIterator.next();
			System.err.println("Description: "+report.getDescription());
		}
		assertTrue(validityReport.isValid());
	}
	
	@Test
	public void testExpression() throws Exception {
//		String data = "2b , #spaceboundary_area=22.49";
		String data = "2a , #spaceboundary_area=13.75";
		String patternText = "2[ab] , #spaceboundary_area=(.*)";
		Pattern pattern = Pattern.compile(patternText);
		Matcher matcher = pattern.matcher(data);
//		assertTrue(matcher.find());
		assertTrue(matcher.matches());
		for (int i = 0; i < Math.min(1, matcher.groupCount()); i++) {
			String gm = matcher.group(i+1);
			System.out.println(gm);
		}
	}
	
	@Test
	public void testReasoningAreas() throws Exception {	
		loadFile(owlFile_ISES);
		assertNotNull(dataModel);
		
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		RuleSet areaRuleSet = service.loadRuleSet(new File(AREA_RULES));
		ruleSets.add(areaRuleSet);
		
		Reasoner reasoner = service.applyRuleSets(dataModel, ruleSets);
		
		inferenceModel = reasoner.getInferenceModel();
		assertNotNull(inferenceModel);
		ValidityReport validityReport = reasoner.getValidityReport();
		Iterator<Report> reportIterator = validityReport.getReports();
		while(reportIterator.hasNext()) {
			Report report = reportIterator.next();
			System.err.println("Description: "+report.getDescription());
		}
		assertTrue(validityReport.isValid());
		
		String BUILDING_URI = BIMOntoVocabulary.getURI()+"IFC?modelId="+IFC_MODEL_ID+"&elementId="+BUILDING_GUID;		
		Resource res = inferenceModel.getResource(BUILDING_URI);
		printStatements(res, null, null, new PrintWriter(System.out));
		
		System.out.println("Information --- IfcWallStandardCase");
		Resource resWall = inferenceModel.getResource(BIMOntoVocabulary.getURI()+"IFC?modelId="+IFC_MODEL_ID+"&elementId="+WALL_GUID);
		assertNotNull(resWall);
		printStatements(resWall, null, null, new PrintWriter(System.out));
		
		System.out.println("Information --- IfcWindow");
		Resource resWindow = inferenceModel.getResource(BIMOntoVocabulary.getURI()+"IFC?modelId="+IFC_MODEL_ID+"&elementId="+WINDOW_GUID);
		assertNotNull(resWindow);
		printStatements(resWindow, null, null, new PrintWriter(System.out));
		
		System.out.println("Information --- IfcSpace");
		Resource resSpace = inferenceModel.getResource(BIMOntoVocabulary.getURI()+"IFC?modelId="+IFC_MODEL_ID+"&elementId="+SPACE_GUID);
		assertNotNull(resSpace);
		printStatements(resSpace, null, null, new PrintWriter(System.out));				
		
		saveInferredModel("kindergarten_areas");
	}
	
	@Test
	public void testReasoningUValueCheck() throws Exception {	
		loadFile(owlFile_ISES);
		assertNotNull(dataModel);
		
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		RuleSet areaRuleSet = service.loadRuleSet(new File(UVALUE_CHECK_RULES));
		ruleSets.add(areaRuleSet);
		
		Reasoner reasoner = service.applyRuleSets(dataModel, ruleSets);
		
		inferenceModel = reasoner.getInferenceModel();
		assertNotNull(inferenceModel);
		ValidityReport validityReport = reasoner.getValidityReport();
		Iterator<Report> reportIterator = validityReport.getReports();
		while(reportIterator.hasNext()) {
			Report report = reportIterator.next();
			System.err.println("Description: "+report.getDescription());
		}
		assertTrue(validityReport.isValid());
		
		
		System.out.println("Information --- IfcWallStandardCase");
		Resource resWall = inferenceModel.getResource(BIMOntoVocabulary.getURI()+"IFC:"+IFC_MODEL_ID+"&elementId="+WALL_GUID);
		assertNotNull(resWall);
		printStatements(resWall, null, null, new PrintWriter(System.out));
		
		System.out.println("Information --- IfcWindow");
		Resource resWindow = inferenceModel.getResource(BIMOntoVocabulary.getURI()+"IFC:"+IFC_MODEL_ID+"&elementId="+WINDOW_GUID);
		assertNotNull(resWindow);
		printStatements(resWindow, null, null, new PrintWriter(System.out));			
		
		saveInferredModel("ises_uvalue");
	}
	
	@Test
	public void testReasoningISES_EnergySavingsPossible() throws Exception {	
		loadFile(owlFile_ISES);
		assertNotNull(dataModel);
		String BUILDING_URI = BIMOntoVocabulary.getURI()+"IFC?modelId="+IFC_MODEL_ID+"&elementId="+BUILDING_GUID;
		Individual building = dataModel.getIndividualByURI(BUILDING_URI);
		assertNotNull(building);
		
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		RuleSet enevRuleSet = service.loadRuleSet(new File(ENERGY_SAVINGS_POSSIBLE_CHECK_RULES));
		ruleSets.add(enevRuleSet);
		
		Reasoner reasoner = service.applyRuleSets(dataModel, ruleSets);
		
		inferenceModel = reasoner.getInferenceModel();
		assertNotNull(inferenceModel);
		ValidityReport validityReport = reasoner.getValidityReport();
		Iterator<Report> reportIterator = validityReport.getReports();
		while(reportIterator.hasNext()) {
			Report report = reportIterator.next();
			System.err.println("Description: "+report.getDescription());
		}
		assertTrue(validityReport.isValid());
		Resource res = inferenceModel.getResource(BUILDING_URI);
		printStatements(res, null, null, new PrintWriter(System.out));
		
		Statement summaryStmt = inferenceModel.getProperty(
				inferenceModel.getResource(BUILDING_URI), EeBIMOntoVocabulary.HAS_SUMMARY);
		assertNotNull(summaryStmt.getObject());
		System.out.println("Summary for building: "+summaryStmt.getObject());
		
		saveInferredModel("ises_energy_savings_reasoning");
	}
	
	@Test
	public void testReasoningISES_SimplifiedEnEV() throws Exception {	
		loadFile(owlFile_ISES);
		assertNotNull(dataModel);
		String BUILDING_URI = BIMOntoVocabulary.getURI()+"IFC?modelId="+IFC_MODEL_ID+"&elementId="+BUILDING_GUID;
		Individual building = dataModel.getIndividualByURI(BUILDING_URI);
		assertNotNull(building);
		
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		RuleSet energySavingsRuleSet = service.loadRuleSet(new File(ENERGY_SAVINGS_POSSIBLE_CHECK_RULES)); // needed because of some already defined variables
		System.out.println("Rule set loaded: "+ENERGY_SAVINGS_POSSIBLE_CHECK_RULES);
		RuleSet enevRuleSet = service.loadRuleSet(new File(SIMPLIFIED_ENEV_CHECK_RULES));
		System.out.println("Rule set loaded: "+SIMPLIFIED_ENEV_CHECK_RULES);

		ruleSets.add(energySavingsRuleSet);
		ruleSets.add(enevRuleSet);
		
		Reasoner reasoner = service.applyRuleSets(dataModel, ruleSets);
		
		inferenceModel = reasoner.getInferenceModel();
		assertNotNull(inferenceModel);
		ValidityReport validityReport = reasoner.getValidityReport();
		Iterator<Report> reportIterator = validityReport.getReports();
		while(reportIterator.hasNext()) {
			Report report = reportIterator.next();
			System.err.println("Description: "+report.getDescription());
		}
		assertTrue(validityReport.isValid());
		
		Resource resBuilding = inferenceModel.getResource(BUILDING_URI);
		
		NodeIterator summaryIter = inferenceModel.listObjectsOfProperty(resBuilding, EeBIMOntoVocabulary.HAS_SUMMARY);
		while(summaryIter.hasNext()){
			System.out.println("Information --- Summary");
			printStatements((Resource)summaryIter.next(), null, null, new PrintWriter(System.out));
		}
		
		System.out.println("Information --- IfcBuilding");
		printStatements(resBuilding, null, null, new PrintWriter(System.out));
		
		System.out.println("Information --- IfcWallStandardCase");
		Resource resWall = inferenceModel.getResource(BIMOntoVocabulary.getURI()+"IFC?modelId="+IFC_MODEL_ID+"&elementId="+WALL_GUID);
		printStatements(resWall, null, null, new PrintWriter(System.out));
		
		saveInferredModel("ises_enev_reasoning");
	}
	
	@Test
	public void testCalculateAreaOfAllBuildingElements() throws Exception {
		// calculates the overall building element area (not only aligned to spaces) that means it is the real area and it will be higher than taking the space boundary area algorithm
		Ifc2x3DataModelProxy ifcModel = loadIfc(ifcFile_ISES);
		boolean takeOnlyExternalBoundaries = true;
		
		double wallArea = .0d;
		double slabArea = .0d;
		double windowArea = .0d;
		double columnArea = .0d;
		double otherArea = .0d;
		Set<EIfcwall> walls = new HashSet<>();
		Set<EIfcslab> slabs = new HashSet<>();
		Set<EIfcwindow> windows = new HashSet<>();
		Set<EIfccolumn> columns = new HashSet<>();
		EIfcreldefinesbyproperties[] relDefinesByProperties = ifcModel.getIfcEntitiesOf(EIfcreldefinesbyproperties.class);
		
		EIfcrelspaceboundary[] relSpaceBoundaries = ifcModel.getIfcEntitiesOf(EIfcrelspaceboundary.class);
		Map<String, Boolean> guidsToExternalOrInternal = new HashMap<>();
		for(EIfcrelspaceboundary sb : relSpaceBoundaries) {
			EIfcelement relatedElement = sb.getRelatedbuildingelement(sb);
			int internalOrExternal = sb.getInternalorexternalboundary(sb);
				guidsToExternalOrInternal.put(
						ifcModel.getGlobalId(relatedElement), 
						internalOrExternal == EIfcinternalorexternalenum.EXTERNAL ? true : false);
			
		}	
		
		for(EIfcreldefinesbyproperties relDefinesByProperty : relDefinesByProperties) {
			AIfcobject relatedObjects = relDefinesByProperty.getRelatedobjects(relDefinesByProperty);
			if(relatedObjects.getMemberCount() != 1) {
				Assert.fail("There is not exactly 1 object related ["+relatedObjects.getMemberCount()+" for: "+relDefinesByProperty+"]");
			}
			String guid = ifcModel.getGlobalId(new IterableAggregate<EIfcobject>(relatedObjects).iterator().next());
			if(takeOnlyExternalBoundaries) {// if only external boundaries requested
				if(!guidsToExternalOrInternal.containsKey(guid) || guidsToExternalOrInternal.get(guid) != true) { // than check if it is an external boundary or continue
					System.err.println(guid);
					continue;
				}
			}
			double areaValue = .0d;
			EIfcpropertysetdefinition propertySetDefinition = relDefinesByProperty.getRelatingpropertydefinition(relDefinesByProperty);
			if(propertySetDefinition instanceof EIfcelementquantity) {
				EIfcelementquantity elementQuantity = (EIfcelementquantity) propertySetDefinition;
				for(EIfcphysicalquantity physicalQuantity : new IterableAggregate<EIfcphysicalquantity>(elementQuantity.getQuantities(elementQuantity))) {
					if(physicalQuantity instanceof EIfcquantityarea) {
						EIfcquantityarea quantityArea = (EIfcquantityarea)physicalQuantity;
						if(quantityArea.testName(quantityArea)) {
							if(quantityArea.getName(quantityArea).equalsIgnoreCase("GrossSideArea")) {
								areaValue = quantityArea.getAreavalue(quantityArea);
								
							}
						}
					}
				}
			}
			for(EIfcobject ifcObject : new IterableAggregate<EIfcobject>(relatedObjects)) {
				if(ifcObject instanceof EIfcwall) {
					walls.add((EIfcwall)ifcObject);
					wallArea += areaValue;
				}
				else if(ifcObject instanceof EIfcslab) {
					slabs.add((EIfcslab)ifcObject); 
					slabArea += areaValue;
				}
				else if(ifcObject instanceof EIfcwindow) {
					windows.add((EIfcwindow)ifcObject);
					windowArea += areaValue;
				}
				else if(ifcObject instanceof EIfccolumn) {
					columns.add((EIfccolumn)ifcObject);
					columnArea += areaValue;
				}
				else {
					System.err.println("Other object: "+ifcObject);
					otherArea += areaValue;
				}
			}
		}
		
		System.out.println("Wall Area: "+wallArea);
		System.out.println("Slab Area: "+slabArea);
		System.out.println("Window Area: "+windowArea);
		System.out.println("Column Area: "+columnArea);
		System.out.println("Other Area: "+otherArea);
	}
	
	@Test
	public void testCalculateAreasBySpaceBoundaries() throws Exception {
		// calculates the building element area (only aligned to spaces) that means it is lower than the real area because the surfaces to the spaces are taken
		Ifc2x3DataModelProxy ifcModel = loadIfc(ifcFile_ISES);
		boolean takeOnlyExternalBoundaries = true;

		double wallArea = .0d;
		double slabArea = .0d;
		double windowArea = .0d;
		double columnArea = .0d;
		double otherArea = .0d;
		Set<EIfcwall> walls = new HashSet<>();
		Set<EIfcslab> slabs = new HashSet<>();
		Set<EIfcwindow> windows = new HashSet<>();
		Set<EIfccolumn> columns = new HashSet<>();
		EIfcrelspaceboundary[] relSpaceBoundaries = ifcModel.getIfcEntitiesOf(EIfcrelspaceboundary.class);
		
		for(EIfcrelspaceboundary sb : relSpaceBoundaries) {
			int internalOrExternal = sb.getInternalorexternalboundary(sb);
			if(takeOnlyExternalBoundaries) { // if only external boundaries requested
				if(internalOrExternal != EIfcinternalorexternalenum.EXTERNAL) continue; // than check if it is an external boundary or continue
			}
			EIfcelement relatedElement = sb.getRelatedbuildingelement(sb);
			String desc = sb.getDescription(sb);
			int foundPatternPos = desc.indexOf(Ifc2x3DataModelProxy.BSPRO_AREA_PATTERN);
			desc = desc.substring(foundPatternPos);
			String areaString = desc.replace(Ifc2x3DataModelProxy.BSPRO_AREA_PATTERN, "");
			double areaValue = Double.valueOf(areaString);
			if(areaValue <= 0) {
				System.err.println("Area is not higher than 0: "+areaString+" => "+sb);
			}
			
			if(relatedElement instanceof EIfcwall) {
				walls.add((EIfcwall)relatedElement);
				wallArea += areaValue;
			}
			else if(relatedElement instanceof EIfcslab) {
				slabs.add((EIfcslab)relatedElement); 
				slabArea += areaValue;
			}
			else if(relatedElement instanceof EIfcwindow) {
				windows.add((EIfcwindow)relatedElement);
				windowArea += areaValue;
			}
			else if(relatedElement instanceof EIfccolumn) {
				columns.add((EIfccolumn)relatedElement);
				columnArea += areaValue;
			}
			else {
				System.err.println("Other object: "+relatedElement);
				otherArea += areaValue;
			}
		}
		
		System.out.println("Wall Area: "+wallArea);
		System.out.println("Slab Area: "+slabArea);
		System.out.println("Window Area: "+windowArea);
		System.out.println("Column Area: "+columnArea);
		System.out.println("Other Area: "+otherArea);
	}

	private void createEnEVTestData() {
		final double combustibleConsumptionValue = 5200.0;
		final int personLoadValue = 4;
		final double dwellingAreaValue = 400.0;
		final double windowAreaValue = 54.0;
		final double buildingEnvelopeAreaValue = 440.0;
		final float decreasedThermalBridgeOverheadValue = 0.05f;
		final double overallUValue = 0.0;
		
		Individual building = dataModel.getIndividualByURI(BIMOntoVocabulary.getURI()+"IFC?modelId="+IFC_MODEL_ID+"&elementId="+BUILDING_GUID);
		assertNotNull(building);
				
		// add combustibleConsumption	
		DatatypeProperty combustibleConsumption = EeBIMOntoVocabulary.COMBUSTIBLE_CONSUMPTION;
		building.addLiteral(combustibleConsumption, combustibleConsumptionValue);
		
		// add HeatingSystem
		Individual heatingSystem1 = dataModel.createIndividual(EeBIMOntoVocabulary.HEATING_SYSTEM, 
				EeBIMOntoVocabulary.getURI()+UUID.randomUUID().toString()+"_enev_heatingSystem_1");
		assertNotNull(heatingSystem1);		
		ObjectProperty hasHeatingSystem = EeBIMOntoVocabulary.HAS_HEATING_SYSTEM;
		// building has heating system
		dataModel.addRelation(building, hasHeatingSystem, heatingSystem1);
		
		// get combustible
		Individual petroleumGas = EeBIMOntoVocabulary._PETROLEUM_GAS;
		assertNotNull(petroleumGas);
		// add combustible to heating system
		ObjectProperty hasCombustible = EeBIMOntoVocabulary.HAS_COMBUSTIBLE;		
		dataModel.addRelation(heatingSystem1, hasCombustible, petroleumGas);		
		
		// add central hot water preparation		
		Individual centralHotWaterPrep = EeBIMOntoVocabulary._CENTRAL_HOT_WATER_PREPARATION;
		assertNotNull(centralHotWaterPrep);
		ObjectProperty hasWaterPrep = EeBIMOntoVocabulary.HAS_WATER_PREPARATION;
		dataModel.addRelation(building, hasWaterPrep, centralHotWaterPrep);
		
		// add person load
		DatatypeProperty personLoadProperty = EeBIMOntoVocabulary.PERSON_LOAD;
		building.addLiteral(personLoadProperty, personLoadValue);
		
		// add dwellingArea
		DatatypeProperty dwellingAreaProperty = EeBIMOntoVocabulary.DWELLING_AREA;
		building.addLiteral(dwellingAreaProperty, dwellingAreaValue);
		
		// add windowArea
		DatatypeProperty windowAreaProperty = EeBIMOntoVocabulary.WINDOW_AREA;
		building.addLiteral(windowAreaProperty, windowAreaValue);
		
		// add buildingEnvelopeArea
		DatatypeProperty buildingEnvelopeAreaProperty = EeBIMOntoVocabulary.BUILDING_ENVELOPE_AREA;
		building.addLiteral(buildingEnvelopeAreaProperty, buildingEnvelopeAreaValue);
		
		// add decreasedThermalBridge
		DatatypeProperty decreasedThermalBridgeProperty = EeBIMOntoVocabulary.DECREASED_THERMAL_BRIDGE_OVERHEAD;
		building.addLiteral(decreasedThermalBridgeProperty, decreasedThermalBridgeOverheadValue);
		
		// add initial overall uValue for building element area of 0
		DatatypeProperty overallUValueProductProperty = EeBIMOntoVocabulary.OVERALL_AREA_UVALUE_PRODUCT;
		building.addLiteral(overallUValueProductProperty, overallUValue);
		
		addUValues();
		
		// add isolation
		DatatypeProperty isIsolatedProperty = EeBIMOntoVocabulary.IS_ISOLATED;
		building.addLiteral(isIsolatedProperty, true);
	}

	private void addUValues() {
		// add uValue to each building element TODO calculate real uValue from construction templates
		DatatypeProperty uValueProperty = EeBIMOntoVocabulary.U_VALUE;
		
		// walls
		ExtendedIterator<Individual> standardWallIndividualsIterator = dataModel.getUnderlyingModel().listIndividuals(BIMOntoVocabulary.IFC_WALL_STANDARD_CASE);
		List<Individual> standardWalls = standardWallIndividualsIterator.toList();
//		while(standardWallIndividualsIterator.hasNext()) {
		for(Individual standardWall : standardWalls) {
//			Individual standardWall = standardWallIndividualsIterator.next();
			System.out.println("standardWall--"+standardWall);
			
			double uValue = Math.random();
			standardWall.addLiteral(uValueProperty, uValue);
		}
		
		ExtendedIterator<Individual> wallIndividualsIterator = dataModel.getUnderlyingModel().listIndividuals(BIMOntoVocabulary.IFC_WALL);
		List<Individual> walls = wallIndividualsIterator.toList();
		for(Individual wall : walls) {
//			Individual wall = wallIndividualsIterator.next();
			System.out.println("wall--"+wall);
			
			double uValue = Math.random();
			wall.addLiteral(uValueProperty, uValue);
		}
		
		// slabs
		ExtendedIterator<Individual> slabIndividualsIterator = dataModel.getUnderlyingModel().listIndividuals(BIMOntoVocabulary.IFC_SLAB);
		List<Individual> slabs = slabIndividualsIterator.toList();
		for(Individual slab : slabs) {
//		while(slabIndividualsIterator.hasNext()) {
//			Individual slab = slabIndividualsIterator.next();
			System.out.println("slab--"+slab);
			
			double uValue = Math.random();
			slab.addLiteral(uValueProperty, uValue);
		}
		
		// columns
		ExtendedIterator<Individual> columnIndividualsIterator = dataModel.getUnderlyingModel().listIndividuals(BIMOntoVocabulary.IFC_COLUMN);
		List<Individual> columns = columnIndividualsIterator.toList();
		for(Individual column : columns) {
//		while(columnIndividualsIterator.hasNext()) {
//			Individual column = columnIndividualsIterator.next();
			System.out.println("column--"+column);
			
			double uValue = Math.random();
			column.addLiteral(uValueProperty, uValue);
		}
		
		// windows
		ExtendedIterator<Individual> windowIndividualsIterator = dataModel.getUnderlyingModel().listIndividuals(BIMOntoVocabulary.IFC_WINDOW);
		List<Individual> windows = windowIndividualsIterator.toList();
		for(Individual window : windows) {
//		while(windowIndividualsIterator.hasNext()) {
//			Individual window = windowIndividualsIterator.next();
			System.out.println("window--"+window);
			
			double uValue = 1+Math.random()*2;
			window.addLiteral(uValueProperty, uValue);
		}
	}
}
