package de.tudresden.bau.cib.vl.core.model.ontology.sparql;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.individuals.IfcIndividualFactory;
import de.tudresden.bau.cib.vl.core.model.ontology.parser.OwlParser;
import de.tudresden.bau.cib.vl.core.model.ontology.sparql.EeBimQueryExecutor.VARIABLES;

public class QueryExecutorTest {

	private static String workingDirectoryPath = "target/repo";
//	private static final String OWL_FILE = "test/resources/ISES_Bestand_20131209_2ndLSB.rdf";
	private static final String OWL_FILE = "test/resources/140910Kindergartenneu2ndLSB.ifc.rdf";
//	private static final String OWL_FILE = "test/resources/103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB.rdf";
	
	private OntologyModel ontModel;
	private static OwlParser owlParser;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		owlParser = OwlParser.createParser(workingDirectoryPath+File.separator+"ontology", "N3");
		ontModel = owlParser.parse(OWL_FILE);
//		ontModel = owlParser.parse(METAMODEL_FILE);
		ontModel.setId(UUID.randomUUID().toString());
//		List<String> owlPaths = new ArrayList<String>();
//		owlPaths.add(IFCOWL_FILE);
//		owlPaths.add(CLIMATE_FILE);
//		ontModel.loadSchemesOfLinkedModels(owlPaths.toArray(new String[owlPaths.size()]));
	}

	@After
	public void tearDown() throws Exception {	
		ontModel = null;
	}

	@Test
	public void testIsIfcClass() {
		System.out.println("Test if the rooms are of type IfcSpace");
		List<Map<String, String>> rooms = EeBimQueryExecutor.findRooms(ontModel);
		for(Map<String, String> roomEntry : rooms) {
			String roomUri = roomEntry.get(VARIABLES.x);
			System.out.println(roomUri);
			boolean check = EeBimQueryExecutor.isIfcClass("IfcSpace", ontModel);
			assertTrue(check);
			System.out.println("Check1 (IfcSpace) => "+check);
			
			boolean check2 = EeBimQueryExecutor.isIfcClass("IfcRaum", ontModel);
			assertTrue(!check2);
			System.out.println("Check2 (IfcRaum) => "+check2);
			
			boolean check3 = EeBimQueryExecutor.isIfcClass("IfcRoot", ontModel);
			assertTrue(!check3);
			System.out.println("Check3 (IfcRoot) => "+check3);
		}
	}
	
	@Test
	public void testSubmodel() throws Exception {
		System.out.println("Create sub model");
//		String ifcPrefix = "http://iai.org/ifcOWL#";
		String ifcPrefix = Ifc2x3DataModelProxy.NS_IFCOWL;
		String query = 
		"PREFIX ifcOWL: <"+ifcPrefix+"> " +
		"PREFIX owl: <http://www.w3.org/2002/07/owl> " +
		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns> " +
		"SELECT ?"+VARIABLES.x+" " +
		"WHERE {" +
		"      ?"+VARIABLES.x+"	a 					ifcOWL:IfcSpace ." +
		"      }";
		List<QuerySolution> qsList = QueryExecutor.find(query, ontModel);
		List<String> constructQueries = new ArrayList<String>();
		for(QuerySolution qs : qsList) {
			String constructQuery = "construct where { <"+qs.get(VARIABLES.x.name())+"> a ?type }";
			constructQueries.add(constructQuery);
		}
		Model result = ModelFactory.createDefaultModel();
		for(String con : constructQueries) {
			Model sub = QueryExecutor.subModel(con, ontModel);
			assertNotNull(sub);
			System.out.println( "\n<!-- results of: " +query+" -->");
			sub.write( System.out, "RDF/XML-ABBREV" );
			result.add(sub);
		}
		
		File resultFile = new File("target/result.rdf");
		result.write(new FileWriter(resultFile), "RDF/XML");
		System.out.println("Result file written to: "+resultFile);
	}

	@Test
	public void testFindBuildings() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindRooms() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindSpaceTypeTemplate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClimateLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindConstructionTemplate() {
		Map<String,Set<String>> templateToGuids = new HashMap<String, Set<String>>();
		List<Map<String,String>> result = EeBimQueryExecutor.findConstructions(ontModel);
		for(Map<String,String> element : result) {
			String beGuid = element.get(VARIABLES.guid.name());
			String tplName = element.get(VARIABLES.name.name());
			
			if(templateToGuids.containsKey(tplName)) {
				templateToGuids.get(tplName).add(beGuid);
			} else {
				Set<String> guids = new HashSet<String>();
				guids.add(beGuid);
				templateToGuids.put(tplName, guids);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Set<java.lang.String>> entry : templateToGuids
				.entrySet()) {
			String key = entry.getKey();
			Set<java.lang.String> value = entry.getValue();
			sb.append(key+" => "+value+"\n");
		}
		assertTrue(templateToGuids.size() == 41);
		assertTrue(templateToGuids.get("Concrete_baseplate_42cm").size()==1);
		assertTrue(templateToGuids.get("Natural_stone_2_7_controllership_room_38cm").size()==1);
		assertTrue(templateToGuids.get("walltypeTICS").size()>1);
		assertTrue(templateToGuids.get("walltypeSunprotectionCase").size()>1);
		assertTrue(templateToGuids.get("Standard windows with glass panel ground floor").size()>1);
		assertTrue(templateToGuids.get("walltypeConcrete").size()>1);
		
		System.out.println(sb.toString());
	}
	
	@Test
	public void testFindConstructionTemplatePaths() {
		Map<String,Set<String>> templateToGuids = new HashMap<String, Set<String>>();
		List<Map<String,String>> result = EeBimQueryExecutor.findConstructions(ontModel);
		for(Map<String,String> element : result) {
			String beGuid = element.get(VARIABLES.guid.name());
			String tplName = element.get(VARIABLES.tplPath.name());
			
			System.out.println("GUID: "+beGuid);
			System.err.print("TPL: "+tplName);
			
			if(templateToGuids.containsKey(tplName)) {
				templateToGuids.get(tplName).add(beGuid);
			} else {
				Set<String> guids = new HashSet<String>();
				guids.add(beGuid);
				templateToGuids.put(tplName, guids);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Set<java.lang.String>> entry : templateToGuids
				.entrySet()) {
			String key = entry.getKey();
			Set<java.lang.String> value = entry.getValue();
			sb.append(key+" => "+value+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	@Test
	public void testCheckConstructionTemplateSearchFunctionsForEquality() {
		Map<String,Set<String>> templateToGuids = new HashMap<String, Set<String>>();
		// search all assigned construction templates
		List<Map<String,String>> sparqlResult = EeBimQueryExecutor.findConstructions(ontModel);
		for(Map<String,String> element : sparqlResult) {
			String beGuid = element.get(VARIABLES.guid.name());
			String tplName = element.get(VARIABLES.name.name());
			
			if(templateToGuids.containsKey(tplName)) {
				templateToGuids.get(tplName).add(beGuid);
			} else {
				Set<String> guids = new HashSet<String>();
				guids.add(beGuid);
				templateToGuids.put(tplName, guids);
			}
		}
		// now check by iterating over all guids if the returned template name is equal to the previous one 
		for (Map.Entry<String, Set<java.lang.String>> entry : templateToGuids.entrySet()) {
			String tplName = entry.getKey();
			Set<java.lang.String> guids = entry.getValue();
			for(String guid : guids) {
				List<Map<String, String>> results = EeBimQueryExecutor.findConstructionTemplate(guid, ontModel);
				for(Map<String, String> result : results) {
					String tplNameCheck = result.get(VARIABLES.name);
					assertTrue(tplName.equals(tplNameCheck));
				}			
			}			
		}
	}

	@Test
	public void testFindConstructionsRelatedToRoom() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindConstructions() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindMaterialTemplatesRelatedToBuildingElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindElementsInBuilding() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindGlobalId() {
		System.out.println("Searching the GlobalIds of all rooms");
		List<Map<String, String>> rooms = EeBimQueryExecutor.findRooms(ontModel);
		for(Map<String, String> roomEntry : rooms) {
			String roomUri = roomEntry.get(VARIABLES.x);
			System.out.println(roomUri);
			String guid = EeBimQueryExecutor.findGlobalId(roomUri, ontModel);
			System.out.println("GlobalId => "+guid);
		}		
	}

	@Test
	public void testFindHeatingModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCoolingModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindDefaultParametrization() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindSimulationParameter() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindSolverParameter() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetConstructionBased() {
		List<QuerySolution> solutions = EeBimQueryExecutor.getConstructionBased(ontModel);
		for(QuerySolution qs : solutions) {
			System.out.println(qs);
		}
	}
	
	@Test
	public void testGetElementsWithNoAssignments() throws Exception {
		System.out.println("Search elements with no assignments");
		List<QuerySolution> elementsWithNoAssignmentSolutions = EeBimQueryExecutor.getElementsWithNoAssignments(ontModel);
		System.out.println("Found "+elementsWithNoAssignmentSolutions.size()+" with no assignments");
		for(QuerySolution qs : elementsWithNoAssignmentSolutions) {
			System.out.println("IfcId: "+qs.getResource("IfcId"));
			System.out.println("IFC-Name: "+qs.getLiteral("IfcName"));
			System.out.println("IfcType: "+qs.getResource("IfcType"));
		}
	}
	

}
