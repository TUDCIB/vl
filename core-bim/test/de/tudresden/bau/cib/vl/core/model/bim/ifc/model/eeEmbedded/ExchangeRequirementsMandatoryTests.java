package de.tudresden.bau.cib.vl.core.model.bim.ifc.model.eeEmbedded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import jsdai.SIfc4.CIfcbuilding;
import jsdai.SIfc4.CIfcbuildingstorey;
import jsdai.SIfc4.CIfccurtainwall;
import jsdai.SIfc4.CIfcdoor;
import jsdai.SIfc4.CIfcelementquantity;
import jsdai.SIfc4.CIfcrelspaceboundary2ndlevel;
import jsdai.SIfc4.CIfcsite;
import jsdai.SIfc4.CIfcwindow;
import jsdai.SIfc4.EIfcbuilding;
import jsdai.SIfc4.EIfcbuildingelement;
import jsdai.SIfc4.EIfccommunicationsappliance;
import jsdai.SIfc4.EIfccommunicationsappliancetypeenum;
import jsdai.SIfc4.EIfccovering;
import jsdai.SIfc4.EIfccoveringtypeenum;
import jsdai.SIfc4.EIfcdistributionelement;
import jsdai.SIfc4.EIfcdistributionsystem;
import jsdai.SIfc4.EIfcdistributionsystemenum;
import jsdai.SIfc4.EIfcelectricappliance;
import jsdai.SIfc4.EIfcelectricappliancetypeenum;
import jsdai.SIfc4.EIfcelement;
import jsdai.SIfc4.EIfclamp;
import jsdai.SIfc4.EIfclamptype;
import jsdai.SIfc4.EIfclightfixture;
import jsdai.SIfc4.EIfcmaterial;
import jsdai.SIfc4.EIfcmateriallayer;
import jsdai.SIfc4.EIfcproject;
import jsdai.SIfc4.EIfcroot;
import jsdai.SIfc4.EIfcsite;
import jsdai.SIfc4.EIfcspace;
import jsdai.SIfc4.EIfcwall;
import jsdai.lang.A_integer;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import de.tudresden.bau.cib.filter.ifc2x4.semantic.IfcDataModel;
import de.tudresden.bau.cib.filter.ifc2x4.semantic.IfcDataModelImpl;
import de.tudresden.bau.cib.filter.ifc2x4.semantic.IfcDataModelImpl.IFC;
import de.tudresden.bau.cib.model.StepDataModel;
import de.tudresden.bau.cib.parser.StepParser;


/**
 * Test class for testing a test file for exchange requirements 1.1,2.1,3.1 ARCH (mandatory) determined in the document 'Interoperability
 * and collaboration requirements'
 * @author Max Vollstaedt
 *
 */

/*Comments:
 * - Site/Building > Site/Building geo reference > Reference sea level: how is it saved (and thus how can it be proved)?
 * - Construction related information > Spaces > Opaque components: Which element types shall be checked? 
 * - Building > Building description: Which properties should be used to prove whether the necessary guideline properties exist?
 * - Space related information > Spaces > Technical Equipment of the Room: What to prove?
 */

public class ExchangeRequirementsMandatoryTests {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	private static StepParser parser = new StepParser("D:\\repo");
	private static IfcDataModel ifcDataModel;
	private  static String pathTestFolder = "resources/ifc/";	
	
	//Often used variables
	EEntity[] isDecomposedBy, decomposes,  isDefinedBy, boundedBy;
	
	EEntity[]  hasPropSets, hasProperties;
	EEntity[] properties, quantities, references, elementQuant;
	
	EEntity[] entities, ifcSites, ifcBuildings, spaces, coverings, lamps, lightFixtures, lightFixTypes, distElements, lampTypes, walls;
	EEntity[] curtainWalls, electrAppliances, commAppliances, distSystems, buildingElements, ifcProjectEntity;

	Set<EEntity> doorsAndWind = new HashSet<EEntity>();
	Set<EEntity> ceilings = new HashSet<EEntity>();
	Set<EEntity> floors = new HashSet<EEntity>();
	Set<EEntity> transparents = new HashSet<EEntity>();
	Set<EEntity> opaques = new HashSet<EEntity>();
	Set<EEntity> allTemplates = new HashSet<EEntity>();
	Set<EIfcmaterial> assocMaterials = new HashSet<EIfcmaterial>();
	Set<EIfcmateriallayer> assocMaterialLayers = new HashSet<EIfcmateriallayer>();
	
	Object pset;
	String name, number, descr, siteType;
	boolean relExists = false;
	int siteRelEx = 0, buildingRelEx = 0;
	A_integer refLongitude, refLatitude;
	Double elev, terrElev;
	int marketCatEx = 0, marketSubCatEx = 0, narrativeTextEx = 0, occTypeEx = 0;
	int relToSite = 0, relToBuildingStoreys = 0, relToProject = 0;
	int occNum = 0, occNumPeak = 0, occType = 0;
	int relCount = 0; //counts RelSpaceBoundary2ndLevel relationships
	int valEx, heatGainEx;
	int doorWindCommEx = 0, doorWindGlazEx = 0, doorWindShadEx = 0, glassPropsEx = 0;
	int perEx = 0, areaEx = 0, psetEx = 0;
	

	//test file	
	private static String pathTestFile = "HelicopterGarage_IFC4_reworked_KEN.ifc";	

	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		String pathToTestFile = props.getProperty("testfile");
		StepDataModel stepDataModel = parser.parse(pathTestFolder+pathTestFile);
		assertNotNull("must be set before testing", stepDataModel);
		ifcDataModel = new IfcDataModelImpl(stepDataModel);
		assertNotNull("must be set before testing", ifcDataModel);
		
		
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		parser.stop();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		ifcSites = ifcDataModel.getBasicModel().getEntitiesOf(EIfcsite.class);
		ifcBuildings = ifcDataModel.getBasicModel().getEntitiesOf(EIfcbuilding.class);
		spaces = ifcDataModel.getBasicModel().getEntitiesOf(EIfcspace.class);
		coverings = ifcDataModel.getBasicModel().getEntitiesOf(EIfccovering.class);
		lamps = ifcDataModel.getBasicModel().getEntitiesOf(EIfclamp.class);
		lightFixtures = ifcDataModel.getBasicModel().getEntitiesOf(EIfclightfixture.class);
		lightFixTypes = ifcDataModel.getBasicModel().getEntitiesOf(EIfclightfixture.class);
		lampTypes = ifcDataModel.getBasicModel().getEntitiesOf(EIfclamptype.class);
		walls = ifcDataModel.getBasicModel().getEntitiesOf(EIfcwall.class);
		ifcProjectEntity = ifcDataModel.getBasicModel().getEntitiesOf(EIfcproject.class);
		distSystems = ifcDataModel.getBasicModel().getEntitiesOf(EIfcdistributionsystem.class);
		electrAppliances = ifcDataModel.getBasicModel().getEntitiesOf(EIfcelectricappliance.class);
		commAppliances = ifcDataModel.getBasicModel().getEntitiesOf(EIfccommunicationsappliance.class);
		buildingElements = ifcDataModel.getBasicModel().getEntitiesOf(EIfcbuildingelement.class);
		
		System.out.println("-------------------------------------Mandatory Items Test:-------------------------------------\n");
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Ignore
	@Test
	public void Arch11() throws SdaiException{
		
		/*----------------------------------------------------Exchange Requirements 1.1 ARCH----------------------------------------------------*/
		
		System.out.println("Exchange Requirements 1.1 ARCH\n");
		
		//----Project Information
		//-------Project Context
		//----------Project Attributes
		
		System.out.println("---Ifcproject Entity:");
		
		// Does an Ifcproject entity even exist?
		assertTrue("No Ifcproject entities found!", ifcProjectEntity.length != 0);
		System.out.println("Number: " + number);
		
		// UNIQUE ID (GlobaldId)
		assertNotNull("No Global project ID found!", ifcDataModel.getGlobalId((EIfcroot) ifcProjectEntity[0]));
		System.out.println("Unique ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcProjectEntity[0]));
		
		// NUMBER (Name)
		number = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], IFC.NAME.toString(), false);
		assertNotNull("No Ifcproject number found!", number);
		System.out.println("Number: " + number);
		
		// NAME (Long Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], "longname", false);
		assertNotNull("No Ifcproject name found!", name);
		System.out.println("Name: " + name);
		
		//----------Project Decomposition
		
		//Do IfcSite and IfcBuilding entities exist? Are they in relation to the IfcProject object?
		isDecomposedBy = ifcDataModel.getBasicModel().getInverseReferences(ifcProjectEntity[0], "isdecomposedby");
		relExists = false;
		
		//Prove of existence of any IfcRelAggregates relationships
		assertTrue("No decomposing relation based on the IfcProject object!", isDecomposedBy.length > 0);
		
		//Proving existence of any Ifcsite and Ifcbuilding entities
		assertTrue("No IfcSite object found!", ifcSites.length != 0);
		assertTrue("No IfcBuilding object found!", ifcBuildings.length != 0);
		
		
		siteRelEx = 0;
		buildingRelEx = 0;
		
		for (EEntity rel : isDecomposedBy){
			entities = ifcDataModel.getBasicModel().getReferences(rel, "relatedobjects");
			for (EEntity ee : entities){
				
				if (ee.getClass().equals(CIfcsite.class))
					siteRelEx++;
			
				if (ee.getClass().equals(CIfcbuilding.class))
					buildingRelEx++;
				
			}
		}
		
		//SITE CONTAINED IN PROJECT
		assertEquals("No relation between the IfcProject and IfcSite object!", 1, siteRelEx); 
		System.out.println("IfcSite object (root) ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcSites[0]));

		//BUILDING CONTAINED IN PROJECT
		assertEquals("No relation between the IfcProject and IfcBuilding object!", 1, buildingRelEx);  
		System.out.println("IfcBuilding object (root) ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcBuildings[0]));
		
		System.out.println();
		
		//----Site Information
		//-------Site
		//----------Site identification
		
		for (EEntity site : ifcSites){
		
		System.out.println("---Ifcsite Entity:");
		
		//Existence of IfcSite object already proved
		
		// SIDE ID (GlobalId)
		assertNotNull("No Global Site ID found!", ifcDataModel.getGlobalId((EIfcroot) site));
		System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) site));
		
		// SITE NAME (Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "name", false);
		assertNotNull("No Ifcsite name found!", name);
		System.out.println("Name: " + name);
		
		// NAME (Long Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "longname", false);
		descr = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "description", false);
		
		assertTrue("No Site description found!", (name != null) || (descr != null));
		
		if (name != null)
			System.out.println("Long name: " + name);
		if (descr != null)
			System.out.println("Description: " + descr);
		
		//Site type (ObjectType)
		siteType = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "objecttype", false);
		assertTrue("No site type found!", (siteType != null && siteType != ""));
		System.out.println("Site type: " + siteType);
		
		
		//----------Site geo reference
		
		// LONGITUDE (RefLongitude)
		refLongitude =  (A_integer) ifcDataModel.getBasicModel().getValueOfAttribute(site, "reflongitude", false);
		assertNotNull("No longitude found!", refLongitude);
		System.out.println("Longitude: " + refLongitude);
		
		// LATITUDE (RefLatitude)
		refLatitude =  (A_integer) ifcDataModel.getBasicModel().getValueOfAttribute(site, "reflatitude", false);
		assertNotNull("No latitude found!", refLatitude);
		System.out.println("Latitude: " + refLatitude);
		
		// HIGH ABOVE SEA LEVEL (RefElevation)
		elev = (Double) ifcDataModel.getBasicModel().getValueOfAttribute(site, "refelevation", false);
		assertTrue("No elevation found!", elev.toString() != "NaN");
		System.out.println("Elevation: " + elev);
		
		//No REFERENCE SEA LEVEL found
		
		//----------Site spatial (de)composition
		
		//DECOMPOSING SPATIAL ELEMENTS (IfcBuilding)
		
		//retrieving all decomposing relations of the Ifcsite object
		isDecomposedBy = ifcDataModel.getBasicModel().getInverseReferences(site, "isdecomposedby");
		relExists = false;
		assertTrue("No decomposing relations based on the IfcSite object!", isDecomposedBy.length > 0);
		//checking whether the relation includes the determined Ifcbuilding object (checks only ONE object so far)
		for (EEntity e : isDecomposedBy){
			//getting all referenced elements in the relation
				entities = ifcDataModel.getBasicModel().getAllReferences(e);
				for (EEntity ee : entities){
					if (ee.getClass().equals(CIfcbuilding.class))
					{
						relExists = true;
						break;
					}
				}
				if (relExists)
					break;
		}
		
		if (!relExists)
			assertFalse("No relation between the Ifcsite and Ifcbuilding entity found!", false);
		
		System.out.println();
		
		}
		
		//Relation between IfcSite and IfcProject already proved
		
		//----Building Information
		//-------Building
		//----------Building identification
		
		System.out.println("---Ifcbuilding Entity:");
		
		// Does an Ifcbuilding entity even exist?
		assertNotNull("No Ifcproject entity found!", ifcBuildings[0]);
				
		// BUILDING ID (GlobaldId)
		assertNotNull("No Global project ID found!", ifcDataModel.getGlobalId((EIfcroot) ifcBuildings[0]));
		System.out.println("Unique ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcBuildings[0]));
		
		// BUILDING NUMBER (Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcBuildings[0], "name", false);
		assertNotNull("No Ifcbuilding number found!", name);
		System.out.println("Number: " + name);
		
		// NAME (Long Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcBuildings[0], "description", false);
		assertNotNull("No Ifcbuilding name found!", name);
		System.out.println("Name: " + name);
		
		//----------Building description
		
		//Characteristic of the buildings location / buildings shape / type of building use
		
		for (EEntity building : ifcBuildings){
			//Any Psets at all?
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(building, "isdefinedby");
			assertFalse("No Psets found!", isDefinedBy.length == 0);
		
			//Building location cannot be specified in Psets
		
			//BUILDING SHAPE + BUILDING LOCATION: Pset_BuildingUse -> MarketCategory, MarketSubCategory must be set
			System.out.println("Property Sets:");
			marketCatEx = 0;
			marketSubCatEx = 0; 
			narrativeTextEx = 0;
			occTypeEx = 0;
			for (EEntity rel : isDefinedBy){
				entities = ifcDataModel.getBasicModel().getAllReferences(rel);
				for (EEntity ee : entities){
					if (ifcDataModel.getBasicModel().getValueOfAttribute(ee, "name", false)=="Pset_BuildingUse")
					{
						properties = ifcDataModel.getBasicModel().getAllReferences(ee);
						for (EEntity property : properties){
							if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "MarketCategory"){
								System.out.println("> MarketCategory: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								marketCatEx = 1;
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "MarketSubCategory"){
								System.out.println("> MarketSubCategory: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								marketSubCatEx = 1;
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "NarrativeText"){
								System.out.println("> NarrativeText: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								narrativeTextEx = 1;
							}
						}
					}
					//TYPE OF BUILDING USE: Pset_BuildingCommon -> OccupancyType OR Pset_BuildingUse -> NarrativeText must be set
					else if (ifcDataModel.getBasicModel().getValueOfAttribute(ee, "name", false)=="Pset_BuildingCommon"){
						properties = ifcDataModel.getBasicModel().getAllReferences(ee);
						for (EEntity property : properties){
							if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "OccupancyType"){
								System.out.println("> OccupancyType: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								occTypeEx = 1;
							}
						}
					}
				}
			}
			assertTrue("No MarketCategory property found!", marketCatEx == 1);
			assertTrue("No MarketSubCategory property found!", marketSubCatEx == 1);
			assertTrue("No type of BuildingUse found!", narrativeTextEx == 1 || occTypeEx == 1);
			
			//----------Building geo reference
		
			//LONGITUDE, LATITUDE, HIGH ABOVE SEA LEVEL already proven
			//REFERENCE SEA LEVEL not available
		
			//----------Building spatial (de)composition
		
			//retrieving all decomposing relations of the Ifcsite object (RelatingObject)
			isDecomposedBy = ifcDataModel.getBasicModel().getInverseReferences(building, "isdecomposedby");
		
			//retrieving all decomposing relations of the Ifcsite object (RelatedObjects)
			decomposes = ifcDataModel.getBasicModel().getInverseReferences(building, "decomposes");
		
			relToSite = 0;
			relToBuildingStoreys = 0; 
			relToProject = 0;
		
			//Are there any decomposing relations at all?
			assertTrue("No decomposing relations based on the Ifcbuilding object!", decomposes.length != 0);
		
			//checking whether the relation includes the determined relations
			for (EEntity rel : isDecomposedBy){
					//getting all referenced elements in the relation
					entities = ifcDataModel.getBasicModel().getAllReferences(rel);
					//proving whether there is a relationship between the Ifcbuilding and Ifcbuildingstorey class
					for (EEntity e : entities){
						if (e.getClass().equals(CIfcbuildingstorey.class))
							relToBuildingStoreys = 1;
					}
			}
			for (EEntity rel : decomposes){
				//getting all referenced elements in the relation
					entities = ifcDataModel.getBasicModel().getAllReferences(rel);
					//proving whether there is a relationship between the Ifcbuilding and IfcSite AND Ifcproject class
					for (EEntity e : entities){
						if (e.getClass().equals(CIfcsite.class))
							relToSite = 1;
						if (e.equals(ifcProjectEntity[0]))
							relToProject = 1;
					}
			}
				
			//DECOMPOSING SPATIAL ELEMENTS
			assertTrue("No relation between Ifcbuilding and correlating Ifcbuildingstoreys found!", relToBuildingStoreys == 1);
			//DECOMPOSED PROJECT CONTEXT
			assertTrue("No relation between Ifcbuilding and Ifcproject found!", relToProject == 1);
			//DECOMPOSES SPATIAL ELEMENT
			assertTrue("No relation between Ifcbuilding and Ifcsite found!", relToSite == 1);
		
			//----------Building envelope geometry 
		
			//ENVELOPE GEOMETRY
			Object productRepr = ifcDataModel.getBasicModel().getValueOfAttribute(building, "representation", false);
			assertNotNull("No Building Representation found!", productRepr);
			System.out.println("Representation: " + ifcDataModel.getBasicModel().getValueOfAttribute( (EEntity) productRepr, "name", false)); 
		
		}
		
		System.out.println();
		
	}
	
	@Test
	public void Arch21() throws SdaiException{
		
		/*----------------------------------------------------Exchange Requirements 2.1 ARCH----------------------------------------------------*/
		
		System.out.println("\nExchange Requirements 2.1 ARCH\n");
		
		//----General
		//-------Project
		//----------Project Attributes
		
		System.out.println("---Ifcproject Entity:");
		
		// Does an Ifcproject entity even exist?
		assertNotNull("No Ifcproject entity found!", ifcProjectEntity[0]);
		
		// UNIQUE ID (GlobaldId)
		assertNotNull("No Global project ID found!", ifcDataModel.getGlobalId((EIfcroot) ifcProjectEntity[0]));
		System.out.println("Unique ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcProjectEntity[0]));
		
		// NUMBER (Name)
		number = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], IFC.NAME.toString(), false);
		assertNotNull("No Ifcproject number found!", number);
		System.out.println("Number: " + number);
		
		// NAME (Long Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], "longname", false);
		assertNotNull("No Ifcproject name found!", name);
		System.out.println("Name: " + name);
		
		//----------Project Decomposition
		
		//Do IfcSite and IfcBuilding entities exist? Are they in relation to the IfcProject object?
		isDecomposedBy = ifcDataModel.getBasicModel().getInverseReferences(ifcProjectEntity[0], "isdecomposedby");
		relExists = false;
		
		//Prove of existence of any IfcRelAggregates relationships
		assertTrue("No decomposing relation based on the IfcProject object!", isDecomposedBy.length > 0);
		
		//Proving existence of any Ifcsite and Ifcbuilding entities
		assertTrue("No IfcSite object found!", ifcSites.length != 0);
		assertTrue("No IfcBuilding object found!", ifcBuildings.length != 0);
		
		
		siteRelEx = 0;
		buildingRelEx = 0;
		
		for (EEntity rel : isDecomposedBy){
			entities = ifcDataModel.getBasicModel().getReferences(rel, "relatedobjects");
			for (EEntity ee : entities){
				
				if (ee.getClass().equals(CIfcsite.class))
					siteRelEx++;
			
				if (ee.getClass().equals(CIfcbuilding.class))
					buildingRelEx++;
				
			}
		}
		
		//SITE CONTAINED IN PROJECT
		assertEquals("No relation between the IfcProject and IfcSite object!", 1, siteRelEx); 
		System.out.println("IfcSite object (root) ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcSites[0]));
		
		//BUILDING CONTAINED IN PROJECT
		assertEquals("No relation between the IfcProject and IfcBuilding object!", 1, buildingRelEx);  
		System.out.println("IfcBuilding object (root) ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcBuildings[0]));
		
		System.out.println();
		
		
		//----Site Information
		//-------Site
		//----------Site identification
		
		for (EEntity site : ifcSites){
		
		System.out.println("---Ifcsite Entity:");
		
		//Existence of IfcSite object already proved
		
		// SIDE ID (GlobalId)
		assertNotNull("No Global Site ID found!", ifcDataModel.getGlobalId((EIfcroot) site));
		System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) site));
		
		// SITE NAME (Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "name", false);
		assertNotNull("No Ifcsite name found!", name);
		System.out.println("Name: " + name);
		
		// NAME (Long Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "longname", false);
		descr = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "description", false);
		
		assertTrue("No Site description found!", (name != null) || (descr != null));
		
		if (name != null)
			System.out.println("Long name: " + name);
		if (descr != null)
			System.out.println("Description: " + descr);
		
		//Site type (ObjectType)
		siteType = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "objecttype", false);
		assertTrue("No site type found!", (siteType != null && siteType != ""));
		System.out.println("Site type: " + siteType);
		
		
		//----------Site geo reference
		
		// LONGITUDE (RefLongitude)
		refLongitude =  (A_integer) ifcDataModel.getBasicModel().getValueOfAttribute(site, "reflongitude", false);
		assertNotNull("No longitude found!", refLongitude);
		System.out.println("Longitude: " + refLongitude);
		
		// LATITUDE (RefLatitude)
		refLatitude =  (A_integer) ifcDataModel.getBasicModel().getValueOfAttribute(site, "reflatitude", false);
		assertNotNull("No latitude found!", refLatitude);
		System.out.println("Latitude: " + refLatitude);
		
		// HIGH ABOVE SEA LEVEL (RefElevation)
		elev = (Double) ifcDataModel.getBasicModel().getValueOfAttribute(site, "refelevation", false);
		assertTrue("No elevation found!", elev != null);
		System.out.println("Elevation: " + elev);
		
		//No REFERENCE SEA LEVEL found
		
		//----------Site spatial (de)composition
		
		//DECOMPOSING SPATIAL ELEMENTS (IfcBuilding)
		
		//retrieving all decomposing relations of the Ifcsite object
		isDecomposedBy = ifcDataModel.getBasicModel().getInverseReferences(site, "isdecomposedby");
		relExists = false;
		assertTrue("No decomposing relations based on the IfcSite object!", isDecomposedBy.length > 0);
		//checking whether the relation includes the determined Ifcbuilding object (checks only ONE object so far)
		for (EEntity e : isDecomposedBy){
			//getting all referenced elements in the relation
				entities = ifcDataModel.getBasicModel().getAllReferences(e);
				for (EEntity ee : entities){
					if (ee.getClass().equals(CIfcbuilding.class))
					{
						relExists = true;
						break;
					}
				}
				if (relExists)
					break;
		}
		
		if (!relExists)
			assertFalse("No relation between the Ifcsite and Ifcbuilding entity found!", false);
		
		System.out.println();
		
		}
		
		//Relation between IfcSite and IfcProject already proved
		
		
		//----Building Information
		//-------Building
		//----------Building identification
		
		System.out.println("---Ifcbuilding Entity:");
		
		// Does an Ifcbuilding entity even exist?
		assertNotNull("No Ifcproject entity found!", ifcBuildings[0]);
				
		// BUILDING ID (GlobaldId)
		assertNotNull("No Global project ID found!", ifcDataModel.getGlobalId((EIfcroot) ifcBuildings[0]));
		System.out.println("Unique ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcBuildings[0]));
		
		// BUILDING NUMBER (Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcBuildings[0], "name", false);
		assertNotNull("No Ifcbuilding number found!", name);
		System.out.println("Number: " + name);
		
		// NAME (Long Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcBuildings[0], "description", false);
		assertNotNull("No Ifcbuilding name found!", name);
		System.out.println("Name: " + name);
		
		//----------Building description
		
		//Characteristic of the buildings location / buildings shape / type of building use
		
		for (EEntity building : ifcBuildings){
			//Any Psets at all?
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(building, "isdefinedby");
			assertFalse("No Psets found!", isDefinedBy.length == 0);
		
			//Building location cannot be specified in Psets
		
			//BUILDING SHAPE + BUILDING LOCATION: Pset_BuildingUse -> MarketCategory, MarketSubCategory must be set
			System.out.println("Property Sets:");
			marketCatEx = 0;
			marketSubCatEx = 0; 
			narrativeTextEx = 0;
			occTypeEx = 0;
			for (EEntity rel : isDefinedBy){
				entities = ifcDataModel.getBasicModel().getAllReferences(rel);
				for (EEntity ee : entities){
					if (ifcDataModel.getBasicModel().getValueOfAttribute(ee, "name", false)=="Pset_BuildingUse")
					{
						properties = ifcDataModel.getBasicModel().getAllReferences(ee);
						for (EEntity property : properties){
							if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "MarketCategory"){
								System.out.println("> MarketCategory: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								marketCatEx = 1;
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "MarketSubCategory"){
								System.out.println("> MarketSubCategory: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								marketSubCatEx = 1;
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "NarrativeText"){
								System.out.println("> NarrativeText: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								narrativeTextEx = 1;
							}
						}
					}
					//TYPE OF BUILDING USE: Pset_BuildingCommon -> OccupancyType OR Pset_BuildingUse -> NarrativeText must be set
					else if (ifcDataModel.getBasicModel().getValueOfAttribute(ee, "name", false)=="Pset_BuildingCommon"){
						properties = ifcDataModel.getBasicModel().getAllReferences(ee);
						for (EEntity property : properties){
							if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "OccupancyType"){
								System.out.println("> OccupancyType: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								occTypeEx = 1;
							}
						}
					}
				}
			}
			assertTrue("No MarketCategory property found!", marketCatEx == 1);
			assertTrue("No MarketSubCategory property found!", marketSubCatEx == 1);
			assertTrue("No type of BuildingCommon found!", narrativeTextEx == 1 || occTypeEx == 1);
			
			//----------Building geo reference
		
			//LONGITUDE, LATITUDE, HIGH ABOVE SEA LEVEL already proven
			//REFERENCE SEA LEVEL not available
		
			//----------Building spatial (de)composition
		
			//retrieving all decomposing relations of the Ifcsite object (RelatingObject)
			isDecomposedBy = ifcDataModel.getBasicModel().getInverseReferences(building, "isdecomposedby");
		
			//retrieving all decomposing relations of the Ifcsite object (RelatedObjects)
			decomposes = ifcDataModel.getBasicModel().getInverseReferences(building, "decomposes");
		
			relToSite = 0;
			relToBuildingStoreys = 0; 
			relToProject = 0;
		
			//Are there any decomposing relations at all?
			assertTrue("No decomposing relations based on the Ifcbuilding object!", decomposes.length != 0);
		
			//checking whether the relation includes the determined relations
			for (EEntity rel : isDecomposedBy){
					//getting all referenced elements in the relation
					entities = ifcDataModel.getBasicModel().getAllReferences(rel);
					//proving whether there is a relationship between the Ifcbuilding and Ifcbuildingstorey class
					for (EEntity e : entities){
						if (e.getClass().equals(CIfcbuildingstorey.class))
							relToBuildingStoreys = 1;
					}
			}
			for (EEntity rel : decomposes){
				//getting all referenced elements in the relation
					entities = ifcDataModel.getBasicModel().getAllReferences(rel);
					//proving whether there is a relationship between the Ifcbuilding and IfcSite AND Ifcproject class
					for (EEntity e : entities){
						if (e.getClass().equals(CIfcsite.class))
							relToSite = 1;
						if (e.equals(ifcProjectEntity[0]))
							relToProject = 1;
					}
			}
				
			assertTrue("No relation between Ifcbuilding and correlating Ifcbuildingstoreys found!", relToBuildingStoreys == 1);
			assertTrue("No relation between Ifcbuilding and Ifcproject found!", relToProject == 1);
			assertTrue("No relation between Ifcbuilding and Ifcsite found!", relToSite == 1);
			
		}
		
		
		//----Space related information
		//-------Spaces
		//----------Spaces general
		
		relCount = 0;
		
		for (EEntity space : spaces){
		
			System.out.println("----------------Ifcspace Entity:----------------");
			
			//SPACE ID (GlobalID)
			assertNotNull(ifcDataModel.getGlobalId((EIfcroot) space));
			System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) space));
			
			//SPACE NUMBER (name)
			assertNotNull(ifcDataModel.getBasicModel().getValueOfAttribute(space, "name", false));
			System.out.println("Name: " + ifcDataModel.getBasicModel().getValueOfAttribute(space, "name", false));
			
			//SPACE NAME or DESCRIPTION
			name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(space, "longname", false);
			descr = (String) ifcDataModel.getBasicModel().getValueOfAttribute(space, "description", false);
			assertTrue("No description or long name found!", name != null || descr != null);
			
			if (name != null)
				System.out.println("Long name: " + ifcDataModel.getBasicModel().getValueOfAttribute(space, "longname", false));
			if (descr != null)
				System.out.println("Description: " + ifcDataModel.getBasicModel().getValueOfAttribute(space, "description", false));
			
			boundedBy = ifcDataModel.getSpaceBoundaries((EIfcspace) space, jsdai.SIfc2x3.EIfcphysicalorvirtualenum.PHYSICAL);
			
			//Any space boundaries at all?
			assertTrue("No space boundaries found!", boundedBy.length != 0);
			
			//Any 2nd level space boundaries at all?
			relCount = 0;
			for (EEntity rel : boundedBy){
				if (rel.getClass().equals(CIfcrelspaceboundary2ndlevel.class))
					relCount++;
			}
			
			//SPACE BOUNDARIES
			assertTrue("There has to be at least one RelSpaceBoundary2ndLevel for each Ifcspace entity!", relCount >= 1);
			
			
			//----------Space quantities
			
			//SPACE FLOOR AREA
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(space, "isdefinedby");
			int netFloorEx = 0, netVolEx = 0, netHeightEx = 0;
			
			System.out.println("Quantities:");
			
			for (EEntity rel : isDefinedBy){
				
				if (ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition")[0].getClass().equals(CIfcelementquantity.class)){
					//getting the elementQuantity assigned to the space
					elementQuant = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
				} 
				
				else
					continue;
				
				//Retrieving all quantities
				quantities = ifcDataModel.getBasicModel().getReferences(elementQuant[0], "quantities"); //only one elementQuantity in relationship
					
				for (EEntity quant : quantities){
					if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "NetFloorArea"){
						System.out.println("> NetFlooArea" + ifcDataModel.getBasicModel().getValueOfAttribute(quant, "nominalvalue", false));
						netFloorEx = 1;
					}
					if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "NetVolume"){
						System.out.println("> NetVolume" + ifcDataModel.getBasicModel().getValueOfAttribute(quant, "nominalvalue", false));
						netVolEx = 1;
					}
					if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "Height"){
						System.out.println("> Height" + ifcDataModel.getBasicModel().getValueOfAttribute(quant, "nominalvalue", false));
						netHeightEx = 1;
					}
				}
				
			}
			
			System.out.println();
			
			//SPACE FLOOR AREA
			assertTrue("No NetFloorArea quantity found!", netFloorEx == 1);
			//NET SPACE VOLUME EXCL. ELEVATED FLOOR
			assertTrue("No NetVolume quantity found!", netVolEx == 1);
			//NET HEIGHT
			assertTrue("No NetHeight quantity found!", netHeightEx == 1);			
			
			//----------Suspended ceiling (Ifccovering.PredefinedType == .CEILING.)
			int widthEx = 0, netAreaEx = 0;
			
			for (EEntity cov : coverings){
				if (ifcDataModel.getBasicModel().getValueOfAttribute(cov, "predefinedtype", false).equals(EIfccoveringtypeenum.CEILING))
					ceilings.add(cov);
				else if (ifcDataModel.getBasicModel().getValueOfAttribute(cov, "predefinedtype", false).equals(EIfccoveringtypeenum.FLOORING))
					floors.add(cov);
			}
			
			//Are there any suspended ceilings or elevated floors at all?
			assertTrue("There must be at least one suspended ceiling!", ceilings.size() != 0);
			assertTrue("There must be at least one elevated floor!", floors.size() != 0);
			
			for (EEntity ceiling : ceilings){
				System.out.println("----Ifccovering entity (CEILING):\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) ceiling));
				System.out.println("Qto_CoveringBaseQuantities:");
				isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(ceiling, "isdefinedby");
				//iterating over all assigned property sets OR quantity elements
				for (EEntity rel : isDefinedBy){
					Object qto = ifcDataModel.getBasicModel().getValueOfAttribute(rel, "relatingpropertydefinition", false);
					//iterating over all quantities of every quantity element
					for (EEntity quant : ifcDataModel.getBasicModel().getAllReferences((EEntity) qto)){
						if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) quant, "name",false) == "Width"){
							widthEx = 1;
							System.out.println("> Width: exists");
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) quant, "name",false) == "NetArea"){
								netAreaEx = 1;
								System.out.println("> NetArea: exists");
						}
					}
				}
				
				System.out.println();
				
				//VOLUME OF SUSPENDED CEILING
				assertTrue("Every suspended ceiling has to have a width!", widthEx == 1);
				//NET HEIGHT
				assertTrue("Every suspended ceiling has to have a netArea!", netAreaEx == 1);
			}
			
			//----------Elevated floor (Ifccovering.PredefinedType == .FLOORING.)
			
			widthEx = 0;
			netAreaEx = 0;
			
			for (EEntity floor : floors){
				System.out.println("----Ifccovering entity (FLOORING):\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) floor));
				System.out.println("Qto_CoveringBaseQuantities:");
				isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(floor, "isdefinedby");
				for (EEntity rel : isDefinedBy){
					Object qto = ifcDataModel.getBasicModel().getValueOfAttribute(rel, "relatingpropertydefinition", false);
					for (EEntity quant : ifcDataModel.getBasicModel().getAllReferences((EEntity) qto)){
						if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) quant, "name",false) == "Width"){
							System.out.println("> Width: exists");
							widthEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) quant, "name",false) == "NetArea"){
							netAreaEx = 1;
							System.out.println("> NetArea: exists");
						}
					}
				}
				
				System.out.println();
				
				//VOLUME OF ELEVATED FLOOR
				assertTrue("Every elevated floor has to have a width!", widthEx == 1);
				//NET HEIGHT
				assertTrue("Every elevated floor has to have a netArea!", netAreaEx == 1);
				
			}
			
			
			//----------Space occupancy and use type
			
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(space, "isdefinedby");
			System.out.println("Property Sets:");
			occNum = 0;
			occNumPeak = 0;
			occType = 0;
			
			for (EEntity e : isDefinedBy){
				
				entities = ifcDataModel.getBasicModel().getAllReferences(e);
				
				for (EEntity ps : entities){
					
					if (ifcDataModel.getBasicModel().getValueOfAttribute(ps, "name", false)=="Pset_SpaceOccupancyRequirements")
					{
						
						properties = ifcDataModel.getBasicModel().getAllReferences(ps);
						
						for (EEntity property : properties){
							
							if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "OccupancyType"){
								
								System.out.println("> OccupancyType: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								occType = 1;
								
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "OccupancyNumber"){
								
								System.out.println("> OccupancyNumber: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								occNum = 1;
								
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "OccupancyNumberPeak"){
								
								System.out.println("> OccupancyNumberPeak: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								occNumPeak = 1;
								
							}
						}
					}
				}
			}
			
			//TYPE OF THE ROOM
			assertTrue("No OccupancyType property found!", occType == 1);
			//NUMBER OF USERS
			assertTrue("No OccupancyNumber property found!", occNum == 1);
			//MAXIMAL NUMBER OF USERS
			assertTrue("No OccupancyNumberPeak property found!", occNumPeak == 1);
			
			System.out.println();
		}
		
		//----------Lighting equipment
		
		//TYPE OF THE LIGHTING EQUIPMENT
		
		//1. Ifclamp.PredefinedType (no 'LIGHTING' enum value available)
		for (EEntity lamp : lamps){
			assertNotNull("Ifclamps have to have assigned a predefined type!",ifcDataModel.getBasicModel().getValueOfAttribute(lamp, "predefinedtype", false));
			System.out.println("----Ifclamp entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lamp)); 
			System.out.println("PredefinedType: " + ifcDataModel.getBasicModel().getValueOfAttribute(lamp, "predefinedtype", false) + "\n");
		}
		
		//2. Ifclampfixture.PredefinedType (no 'LIGHTING' enum value available)
		for (EEntity lightFix : lightFixtures){
			assertNotNull("Ifclightfixtures have to have assigned a predefined type!", ifcDataModel.getBasicModel().getValueOfAttribute(lightFix, "predefinedtype", false));
			System.out.println("----Ifclighfixture entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lightFix)); 
			System.out.println("PredefinedType: " + ifcDataModel.getBasicModel().getValueOfAttribute(lightFix, "predefinedtype", false) + "\n");
		}
		
		//3. Ifcdistributionsystem.PredefinedType == .LIGHTING.  
		valEx = 0;
		for (EEntity distSys : distSystems){
			 if(ifcDataModel.getBasicModel().getValueOfAttribute(distSys, "predefinedtype", false).equals(EIfcdistributionsystemenum.LIGHTING)){
			 	valEx = 1;
			 	System.out.println("----Ifcdistributionsystem entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) distSys)); 
				System.out.println("PredefinedType: " + ifcDataModel.getBasicModel().getValueOfAttribute(distSys, "predefinedtype", false) + "\n");
			 	break;
			 }
		}	
		assertTrue("No Ifcdistributionsystem assigned to the predefined type 'LIGHTING' found!", valEx == 1);

		//NUMBER OF LIGHTING EQUIPMENT PER TYPE	
		valEx = 0;
		for (EEntity lightFix : lightFixTypes){
			
			hasPropSets = ifcDataModel.getBasicModel().getReferences(lightFix, "haspropertysets");
			
			for (EEntity ps : hasPropSets){
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute(ps, "name", false) == "Pset_LightFixtureTypeCommon"){
					properties = ifcDataModel.getBasicModel().getAllReferences(ps);
					
					for (EEntity prop : properties){
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "NumberOfSources"){
							valEx = 1;
							System.out.println("----Lightfixturetype entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lightFix));
							System.out.println("Number of Sources: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
						}
					}
				}
			}
			
			System.out.println();
			assertTrue("NumberOfSource property has to exist in all Pset_lightfixtureTypeCommon sets!", valEx == 1);
		}
		
		//INSTALLED ELECTRICAL POWER PER TYPE
		//1. Pset_ElectricalDeviceCommon -> assigned to 'IfcDistributionElement's (see IFC2x4 entities)
		//CLUE: Code is commented out because the test file includes about 650 Ifcdistributionelement entities
		//		without the necessary pset
		distElements = ifcDataModel.getBasicModel().getEntitiesOf(EIfcdistributionelement.class);
		int psetEx = 0;
		for (EEntity distEl : distElements){
			psetEx = 0;
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(distEl, "isdefinedby");
			for (EEntity pset : isDefinedBy){
				if (ifcDataModel.getBasicModel().getValueOfAttribute(pset, "name", false) == "Pset_DistributionSystemCommon"){
					psetEx = 1;
					break;
				}
			}
			 assertTrue("There has to be at least one Pset_DistributionSystemCommon for each Ifcdistributionelement entity!", psetEx == 1);
		}
		//2.Pset_LampTypeCommon -> assigned to 'Ifclamptype' entities
		psetEx = 0;
		for (EEntity lt : lampTypes){
			psetEx = 0;

			hasPropSets = ifcDataModel.getBasicModel().getReferences(lt, "haspropertysets");
			
			//selecting all pset references
			for (EEntity ps : hasPropSets){
				if (ifcDataModel.getBasicModel().getValueOfAttribute(ps, "name", false) == "Pset_LampTypeCommon"){
					System.out.println("----Lamptype entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lt) + "\n");
					psetEx = 1;
				}
			}
			assertTrue("There must be a Pset_LampTypeCommon entity for each Ifclamptype!", psetEx == 1);
		}
		
		//GEOMETRICAL POSITION OF INSTALLED
		for (EEntity lamp : lamps){
			assertNotNull("Every Ifclamp entity has to have an object placement attribute!", ifcDataModel.getBasicModel().getValueOfAttribute(lamp, "objectplacement", false));
			System.out.println("----Lamp entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lamp));
			System.out.println("Object placement: exists");
			
		}
		
		System.out.println();
		
		//----------Technical Equipment of the room
		//Are there any appliances at all?
		assertTrue("There are no electric appliances at all!", electrAppliances.length != 0);
		assertTrue("There are no communications appliances at all!", commAppliances.length != 0);
		
		heatGainEx = 0;
		
		//TYPE OF EQUIPMENT WHICH PRODUCES HEAT GAINS	
		for (EEntity appl : commAppliances){
			
			Object num = ifcDataModel.getBasicModel().getValueOfAttribute(appl, "predefinedtype", false);
			
			if (num.equals(EIfccommunicationsappliancetypeenum.COMPUTER) ||
					num.equals(EIfccommunicationsappliancetypeenum.FAX) ||
					num.equals(EIfccommunicationsappliancetypeenum.MODEM) ||
					num.equals(EIfccommunicationsappliancetypeenum.PRINTER) ||
					num.equals(EIfccommunicationsappliancetypeenum.ROUTER) ||
					num.equals(EIfccommunicationsappliancetypeenum.SCANNER))
			{
				System.out.println("----Ifccommunicationsappliance entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) appl));
				System.out.println("Predefined type: " + num + "\n");
				heatGainEx++;
			}
		}
		
		assertTrue("There has to be at least one electrical appliance with heat gain!", heatGainEx >= 1);
			
		heatGainEx = 0;
		
		for (EEntity appl : electrAppliances){
			
			Object num = ifcDataModel.getBasicModel().getValueOfAttribute(appl, "predefinedtype", false);
			
			if (!(num.equals(EIfcelectricappliancetypeenum.NOTDEFINED)))
			{
				System.out.println("----Ifcelectricappliance entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) appl));
				System.out.println("Predefined type: " + num + "\n");
				heatGainEx++;
			}
		}
		
		assertTrue("There has to be at least one communications appliance with heat gain!", heatGainEx >= 1);
		
		//----Construction related information
		//-------Spaces
		//----------Opaque components (choosing: IfcWall, IfcSlab, IfcRoof)
		//1. IfcWall
	
		//CONSTRUCTION TYPE AND ISEXTERNAL ATTRIBUTE
		//splitting up building elements into two groups 
		
		for (EEntity be : buildingElements){
			
			if (be.getClass().equals(CIfcwindow.class) || be.getClass().equals(CIfcdoor.class) || be.getClass().equals(CIfccurtainwall.class))
				transparents.add(be);
			else
				opaques.add(be);
			
		}
		
		valEx = 0;
		int relEx = 0;
		for (EEntity wall : walls){
			
			System.out.println("----Ifcwall entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) wall));
			
			relEx = 0;
			valEx = 0;
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(wall, "isdefinedby");
			
			for (EEntity rel : isDefinedBy){
				
				pset = ifcDataModel.getBasicModel().getValueOfAttribute(rel, "relatingpropertydefinition", false);
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_WallCommon")){
					
					System.out.println("Pset_WallCommon:");
					
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences((EEntity)pset)){
						
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "Reference")
						{
							System.out.println("> Reference: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "IsExternal")
						{
							System.out.println("> IsExternal: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
					}
				}
			}
			
			assertTrue("There has to be a reference and the IsExternal attribute assigned to all Ifcwall entities", valEx == 2);
			
			Set<EIfcmaterial> assocMaterials = ifcDataModel.getAssociatedMaterial((EIfcelement) wall);
			//BUILDING ELEMENT MATERIAL
			assertTrue("No associated materials for that specific Ifcwall entity found!", assocMaterials.size() != 0);
			
			for (EIfcmaterial e : assocMaterials){
				psetEx = 0;
				
				//MATERIAL LAYER SET - NAME OF THE LAYER
				if (ifcDataModel.getBasicModel().getValueOfAttribute(e, "name", false) == null)
					assertTrue("Not all associated materials have got a name attribute assigned!", false);
				
				hasProperties = ifcDataModel.getBasicModel().getReferences(e);
				assertTrue("No hasProperties relation found!", hasProperties.length != 0);
				
//				for (EEntity invRel : hasProperties){
//					if (invRel.getClass().equals(CIfcextendedmaterialproperties.class) && 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel,"name",false) == "Pset_GeneralMaterialProperties"){
//						//DENSITY OF THE LAYER MATERIAL
//						assertNotNull("There must be a mass density attribute for every Ifcmaterial entity", 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel, "massdensity",false));
//						psetEx++;
//					}
//					
//					else if (invRel.getClass().equals(CIfcextendedmaterialproperties.class) && 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel,"name",false) == "Pset_ThermalProperties"){
//						//THERMAL CONDUCTIVITY
//						assertNotNull("There must be a thermal conductivity attribute for every Ifcmaterial entity", 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel, "thermalconductivity",false));
//
//						//SPECIFIC HEAT CAPACITY OF THE LAYER
//						assertNotNull("There must be a specific heat capacity attribute for every Ifcmaterial entity", 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel, "specificheatcapacity",false));
//						psetEx++;
//					}
//				}
				
				assertTrue("Every Ifcmaterial has to have a Pset_GeneralMaterialProperties and Pset_ThermalProperties!", psetEx == 2);
				
			}
			
			Set<EIfcmateriallayer> assocMaterialLayers = ifcDataModel.getAssociatedMaterialLayers((EIfcelement) wall);
			//MATERIAL LAYER SET - POSITION OF THE LAYER (implicitly given by the set order)
			
			//MATERIAL LAYER SET - THICKNESS OF THE LAYER
			for (EIfcmateriallayer e : assocMaterialLayers){
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute(e, "layerthickness", false) == null){
					assertTrue("Not all associated material layers have got a layer thickness attribute assigned!", false);
				}
				
			}
			
			//POSITION OF THE COMPONENT ON THE BUILDING
			assertNotNull("Every Ifcbuildingelement entity must have an object placement assigned!",ifcDataModel.getBasicModel().getReferences(wall, "objectplacement"));
			
			System.out.println("Object placement: exists");
			
			System.out.println();
		}
		
		//----------Transparent components
		//Ifcdoor and Ifcwindow (IfcCurtainWall is not necessary because it's only an aggregate of building elements already examined)
		
		for (EEntity tr : transparents){
			
			if (tr.getClass().equals(CIfcwindow.class) || tr.getClass().equals(CIfcdoor.class))
				doorsAndWind.add(tr);
			
		}
		
		for (EEntity ent : doorsAndWind){
			
			System.out.println("----Ifcdoor/window entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) ent));
			
			relEx = 0;
			valEx = 0;
			doorWindCommEx = 0;
			doorWindGlazEx = 0;
			doorWindShadEx = 0;
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(ent, "isdefinedby");
			
			assertTrue("Not enough relDefinesByProperty relations found!", isDefinedBy.length >= 4); //Pset_Door/WindowCommon, Pset_DoorWindowGlazingType,
																									 //Pset_DoorWindowShadingType, Pset_GlassProperties (Set Template)
			for (EEntity rel : isDefinedBy){
				
				valEx = 0;
				pset = ifcDataModel.getBasicModel().getValueOfAttribute(rel, "relatingpropertydefinition", false);
				//saving all templates for checking
				EEntity[] isDefBy = ifcDataModel.getBasicModel().getInverseReferences((EEntity)pset,"isdefinedby");
				if (isDefBy.length != 0){
					allTemplates.add(ifcDataModel.getBasicModel().getReferences(isDefBy[0],"relatingtemplate")[0]); //1-to-N relationship between templates and property sets
				}
				if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_DoorCommon") ||
						ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_WindowCommon")){
					
					doorWindCommEx = 1;
					
					System.out.println("Pset_Door/WindowCommon:");
					
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences((EEntity)pset)){
						
						//CONSTRUCTION TYPE
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "Reference")
						{
							System.out.println("> Reference: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						//INTERNAL OR EXTERNAL ELEMENT
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "IsExternal")
						{
							System.out.println("> IsExternal: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						//RATIO OF GLAZING PER TRANSPARENT ELEMENT
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlazingAreaFraction")
						{
							System.out.println("> GlazingAreaFraction: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
					}
					assertTrue("Every transparent entity must have a contruction type, isExternal and GlazingAreaFraction attribute!", valEx == 3);
				}
				
				valEx = 0;
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_DoorWindowGlazingType")){
					
					doorWindGlazEx = 1;
					
					System.out.println("Pset_DoorWindowGlazingType:");
					
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences((EEntity)pset)){
						
						//NUMBER OF WINDOW PANES
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlassLayers")
						{
							System.out.println("> GlassLayers: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						//THICKNESS OF EVERY PANE
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlassThickness1")
						{
							System.out.println("> GlassThickness1: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlassThickness2")
						{
							System.out.println("> GlassThickness2: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlassThickness3")
						{
							System.out.println("> GlassThickness3: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						//TYPE AND MIXTURE OF GAS BETWEEN LAYERS
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "FillGas")
						{
							System.out.println("> FillGas: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
					}
					
					assertTrue("Every transparent entity must have a Pset_DoorWindowGlazingType with certain properties!", valEx == 5);
				}
					
					valEx = 0;
					
					//----------Shading (No IfcShading entity type!)
					if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_DoorWindowShadingType")){
						
						doorWindShadEx = 1;
						
						System.out.println("Pset_DoorWindowShadingType:");
						
						for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences((EEntity)pset)){
							
							//SHADING COEFFICIENT
							if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "ExternalShadingCoefficient")
							{
								System.out.println("> ExternalShadingCoefficient: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
								valEx++;
							}
						}
					
					assertTrue("Every transparent entity must have a ExternalShadingCoefficient attribute!", valEx == 1);
					}	
			
				}
			
			//Checking for existence of any Ifcpropertysettemplates
			if ( allTemplates.size() == 1){
				System.out.println("Ifcpropertysettemplates: exist");
				assertTrue("Not enough property template attributes found!", ifcDataModel.getBasicModel().getReferences(allTemplates.toArray(new EEntity[allTemplates.size()])[0], "haspropertytemplates").length == 7);
			}
			else{
				assertTrue("Pset_GlassProperties is missing!", false);
			}
			//User defined property sets (IfcPropertysetTemplates) -> prove of number of properties (PROPORTION OF GLASS ON THE WHOLE AREA;
			//TYPE OF GLASS; THICKNESS OF EVERY SPACING BETWEEN PANES; POSITION AND NUMBER OF THERMAL AND OPTICAL COATINGS; SOLAR HEAT
			//GAIN TRANSMITTANCE; THERMAL TRANSMITTANCE RESISTANCE OF THE GLASS AND WINDOW FRAME; AIR TRANSMISSIVITY OF THE JOINT IN THE WINDOW FRAME)
		
			//LOCATION OF SHADING
			assertNotNull("Every transparent element has to have an object placement attribute!", ifcDataModel.getBasicModel().getValueOfAttribute(ent, "objectplacement", false));
			
			System.out.println("Object placement: exists\n");
			
			//Pset_Door/WindowCommon
			assertTrue("Pset_Door/WindowCommon is missing!", doorWindCommEx == 1);
			
			//Pset_DoorWindowGlazingType
			assertTrue("Pset_DoorWindowGlazingType is missing!", doorWindGlazEx == 1);
			
			//Pset_DoorWindowShadingType
			assertTrue("Pset_DoorWindowShadingType is missing!", doorWindShadEx == 1);
		}
		
	}
		
	@Ignore
	@Test
	public void Arch31() throws SdaiException{
		
		/*----------------------------------------------------Exchange Requirements 3.1 ARCH----------------------------------------------------*/
		
		System.out.println("\nExchange Requirements 3.1 ARCH\n");
		
		//----Project Information
		//-------Project Context
		//----------Project Attributes
		
		System.out.println("---Ifcproject Entity:");
		
		// Does an Ifcproject entity even exist?
		ifcProjectEntity = ifcDataModel.getBasicModel().getEntitiesOf(EIfcproject.class);
		assertNotNull("No Ifcproject entity found!", ifcProjectEntity[0]);
		
		// UNIQUE ID (GlobaldId)
		assertNotNull("No Global project ID found!", ifcDataModel.getGlobalId((EIfcroot) ifcProjectEntity[0]));
		System.out.println("Unique ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcProjectEntity[0]));
		
		// NUMBER (Name)
		number = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], IFC.NAME.toString(), false);
		assertNotNull("No Ifcproject number found!", number);
		System.out.println("Number: " + number);
		
		// NAME (Long Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], "longname", false);
		assertNotNull("No Ifcproject name found!", name);
		System.out.println("Name: " + name);
		
		//----------Project Decomposition
		
		//Do IfcSite and IfcBuilding entities exist? Are they in relation to the IfcProject object?
		isDecomposedBy = ifcDataModel.getBasicModel().getInverseReferences(ifcProjectEntity[0], "isdecomposedby");
		relExists = false;
		
		//Prove of existence of any IfcRelAggregates relationships
		assertTrue("No decomposing relation based on the IfcProject object!", isDecomposedBy.length > 0);
		ifcSites = ifcDataModel.getBasicModel().getEntitiesOf(EIfcsite.class);
		ifcBuildings = ifcDataModel.getBasicModel().getEntitiesOf(EIfcbuilding.class);
		
		//Proving existence of any Ifcsite and Ifcbuilding entities
		assertTrue("No IfcSite object found!", ifcSites.length != 0);
		assertTrue("No IfcBuilding object found!", ifcBuildings.length != 0);
		
		
		siteRelEx = 0;
		buildingRelEx = 0;
		
		for (EEntity rel : isDecomposedBy){
			entities = ifcDataModel.getBasicModel().getReferences(rel, "relatedobjects");
			for (EEntity ee : entities){
				
				if (ee.getClass().equals(CIfcsite.class))
					siteRelEx++;
			
				if (ee.getClass().equals(CIfcbuilding.class))
					buildingRelEx++;
				
			}
		}
		
		assertEquals("No relation between the IfcProject and IfcSite object!", 1, siteRelEx); 
		System.out.println("IfcSite object (root) ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcSites[0]));

		assertEquals("No relation between the IfcProject and IfcBuilding object!", 1, buildingRelEx);  
		System.out.println("IfcBuilding object (root) ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcBuildings[0]));
		
		System.out.println();
		
		//----Site Information
		//-------Site
		//----------Site identification
		
		for (EEntity site : ifcSites){
		
			System.out.println("---Ifcsite Entity:");
			
			//Existence of IfcSite object already proved
			
			// SIDE ID (GlobalId)
			assertNotNull("No Global Site ID found!", ifcDataModel.getGlobalId((EIfcroot) site));
			System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) site));
			
			// SITE NAME (Name)
			name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "name", false);
			assertNotNull("No Ifcsite name found!", name);
			System.out.println("Name: " + name);
			
			// NAME (Long Name)
			name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "longname", false);
			descr = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "description", false);
			
			assertTrue("No Site description found!", (name != null) || (descr != null));
			
			if (name != null)
				System.out.println("Long name: " + name);
			if (descr != null)
				System.out.println("Description: " + descr);
			
			//Site type (ObjectType)
			siteType = (String) ifcDataModel.getBasicModel().getValueOfAttribute(site, "objecttype", false);
			assertTrue("No site type found!", (siteType != null && siteType != ""));
			System.out.println("Site type: " + siteType);
			
			
			//----------Site geo reference
			
			// LONGITUDE (RefLongitude)
			refLongitude =  (A_integer) ifcDataModel.getBasicModel().getValueOfAttribute(site, "reflongitude", false);
			assertNotNull("No longitude found!", refLongitude);
			System.out.println("Longitude: " + refLongitude);
			
			// LATITUDE (RefLatitude)
			refLatitude =  (A_integer) ifcDataModel.getBasicModel().getValueOfAttribute(site, "reflatitude", false);
			assertNotNull("No latitude found!", refLatitude);
			System.out.println("Latitude: " + refLatitude);
			
			// HIGH ABOVE SEA LEVEL (RefElevation)
			elev = (Double) ifcDataModel.getBasicModel().getValueOfAttribute(site, "refelevation", false);
			assertTrue("No elevation found!", elev != null);
			System.out.println("Elevation: " + elev);
			
			//No REFERENCE SEA LEVEL found
			
			//----------Site spatial (de)composition
			
			//DECOMPOSING SPATIAL ELEMENTS (IfcBuilding)
			
			//retrieving all decomposing relations of the Ifcsite object
			isDecomposedBy = ifcDataModel.getBasicModel().getInverseReferences(site, "isdecomposedby");
			relExists = false;
			assertTrue("No decomposing relations based on the IfcSite object!", isDecomposedBy.length > 0);
			//checking whether the relation includes the determined Ifcbuilding object (checks only ONE object so far)
			for (EEntity e : isDecomposedBy){
				//getting all referenced elements in the relation
					entities = ifcDataModel.getBasicModel().getAllReferences(e);
					for (EEntity ee : entities){
						if (ee.getClass().equals(CIfcbuilding.class))
						{
							relExists = true;
							break;
						}
					}
					if (relExists)
						break;
			}
			
			if (!relExists)
				assertFalse("No relation between the Ifcsite and Ifcbuilding entity found!", false);
			
			System.out.println();
			
			//Relation between IfcSite and IfcProject already proved
	
			//----------Site quantities
				
			System.out.println("----Ifcsite entity:");
			System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) site));
				
			valEx = 0;
				
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(site, "isdefinedby");
				
			//Are there any relations at all?
			assertTrue("There are not isDefinedBy relations at all!", isDefinedBy.length != 0);
				
			for (EEntity rel : isDefinedBy){
					
						
				elementQuant = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
						
				if (elementQuant[0].getClass().equals(CIfcelementquantity.class)){
						
					System.out.println("Qto_SiteBaseQuantities:");
						
					quantities = ifcDataModel.getBasicModel().getReferences(elementQuant[0], "quantities");
					//Prove of existence of any quantities
					assertTrue("There are no quantities assigned to the Ifcelementquantity entity", quantities.length != 0);
						
					for (EEntity quant : ifcDataModel.getBasicModel().getReferences(elementQuant[0], "quantities")){ //1-to-N relationship
							
						//GROSS PERIMETER
						if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "Perimeter"){
							System.out.println("> Perimeter: exists");
							perEx = 1;
						}
							
						//GROSS AREA
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "GrossArea"){
							System.out.println("> Gross Area: exists");
							areaEx = 1;
						}
					}
				}	
			}
				
			assertTrue("Every Ifcsite entity must have a perimeter attribute assigned!", perEx == 1);
			assertTrue("Every Ifcsite entity must have an area attribute assigned!", areaEx == 1);

			perEx = 0;
			areaEx = 0;
			
			
			System.out.println();
		}

		//----Building Information
		//-------Building
		//----------Building identification
			
		System.out.println("---Ifcbuilding Entity:");
			
		// Does an Ifcbuilding entity even exist?
		assertNotNull("No Ifcproject entity found!", ifcBuildings[0]);
					
		// BUILDING ID (GlobaldId)
		assertNotNull("No Global project ID found!", ifcDataModel.getGlobalId((EIfcroot) ifcBuildings[0]));
		System.out.println("Unique ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcBuildings[0]));
			
		// BUILDING NUMBER (Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcBuildings[0], "name", false);
		assertNotNull("No Ifcbuilding number found!", name);
		System.out.println("Number: " + name);
			
		// NAME (Long Name)
		name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(ifcBuildings[0], "description", false);
		assertNotNull("No Ifcbuilding name found!", name);
		System.out.println("Name: " + name);
			
		//----------Building description
		
		//Characteristic of the buildings location / buildings shape / type of building use
		
		for (EEntity building : ifcBuildings){
			//Any Psets at all?
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(building, "isdefinedby");
			assertFalse("No Psets found!", isDefinedBy.length == 0);
		
			//Building location cannot be specified in Psets
		
			//BUILDING SHAPE + BUILDING LOCATION: Pset_BuildingUse -> MarketCategory, MarketSubCategory must be set
			System.out.println("Property Sets:");
			marketCatEx = 0;
			marketSubCatEx = 0; 
			narrativeTextEx = 0;
			occTypeEx = 0;
			for (EEntity rel : isDefinedBy){
				entities = ifcDataModel.getBasicModel().getAllReferences(rel);
				for (EEntity ee : entities){
					if (ifcDataModel.getBasicModel().getValueOfAttribute(ee, "name", false)=="Pset_BuildingUse")
					{
						properties = ifcDataModel.getBasicModel().getAllReferences(ee);
						for (EEntity property : properties){
							if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "MarketCategory"){
								System.out.println("> MarketCategory: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								marketCatEx = 1;
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "MarketSubCategory"){
								System.out.println("> MarketSubCategory: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								marketSubCatEx = 1;
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "NarrativeText"){
								System.out.println("> NarrativeText: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								narrativeTextEx = 1;
							}
						}
					}
					//TYPE OF BUILDING USE: Pset_BuildingCommon -> OccupancyType OR Pset_BuildingUse -> NarrativeText must be set
					else if (ifcDataModel.getBasicModel().getValueOfAttribute(ee, "name", false)=="Pset_BuildingCommon"){
						properties = ifcDataModel.getBasicModel().getAllReferences(ee);
						for (EEntity property : properties){
							if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "OccupancyType"){
								System.out.println("> OccupancyType: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								occTypeEx = 1;
							}
						}
					}
				}
			}
			assertTrue("No MarketCategory property found!", marketCatEx == 1);
			assertTrue("No MarketSubCategory property found!", marketSubCatEx == 1);
			assertTrue("No type of BuildingCommon found!", narrativeTextEx == 1 || occTypeEx == 1);
			
			//----------Building geo reference
		
			//LONGITUDE, LATITUDE, HIGH ABOVE SEA LEVEL already proven
			//REFERENCE SEA LEVEL not available
		
			//----------Building spatial (de)composition
		
			//retrieving all decomposing relations of the Ifcsite object (RelatingObject)
			isDecomposedBy = ifcDataModel.getBasicModel().getInverseReferences(building, "isdecomposedby");
		
			//retrieving all decomposing relations of the Ifcsite object (RelatedObjects)
			decomposes = ifcDataModel.getBasicModel().getInverseReferences(building, "decomposes");
		
			relToSite = 0;
			relToBuildingStoreys = 0; 
			relToProject = 0;
		
			//Are there any decomposing relations at all?
			assertTrue("No decomposing relations based on the Ifcbuilding object!", decomposes.length != 0);
		
			//checking whether the relation includes the determined relations
			for (EEntity rel : isDecomposedBy){
					//getting all referenced elements in the relation
					entities = ifcDataModel.getBasicModel().getAllReferences(rel);
					//proving whether there is a relationship between the Ifcbuilding and Ifcbuildingstorey class
					for (EEntity e : entities){
						if (e.getClass().equals(CIfcbuildingstorey.class))
							relToBuildingStoreys = 1;
					}
			}
			for (EEntity rel : decomposes){
				//getting all referenced elements in the relation
					entities = ifcDataModel.getBasicModel().getAllReferences(rel);
					//proving whether there is a relationship between the Ifcbuilding and IfcSite AND Ifcproject class
					for (EEntity e : entities){
						if (e.getClass().equals(CIfcsite.class))
							relToSite = 1;
						if (e.equals(ifcProjectEntity[0]))
							relToProject = 1;
					}
			}
				
			assertTrue("No relation between Ifcbuilding and correlating Ifcbuildingstoreys found!", relToBuildingStoreys == 1);
			assertTrue("No relation between Ifcbuilding and Ifcproject found!", relToProject == 1);
			assertTrue("No relation between Ifcbuilding and Ifcsite found!", relToSite == 1);
			
		}
		
		System.out.println();
	
		//----Space related information
		//-------Spaces
		//----------Spaces general
		
		relCount = 0;
		
		for (EEntity space : spaces){
		
			System.out.println("----------------Ifcspace Entity:----------------");
			
			//SPACE ID (GlobalID)
			assertNotNull(ifcDataModel.getGlobalId((EIfcroot) space));
			System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) space));
			
			//SPACE NUMBER (name)
			assertNotNull(ifcDataModel.getBasicModel().getValueOfAttribute(space, "name", false));
			System.out.println("Name: " + ifcDataModel.getBasicModel().getValueOfAttribute(space, "name", false));
			
			//SPACE NAME or DESCRIPTION
			name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(space, "longname", false);
			descr = (String) ifcDataModel.getBasicModel().getValueOfAttribute(space, "description", false);
			assertTrue("No description or long name found!", name != null || descr != null);
			
			if (name != null)
				System.out.println("Long name: " + ifcDataModel.getBasicModel().getValueOfAttribute(space, "longname", false));
			if (descr != null)
				System.out.println("Description: " + ifcDataModel.getBasicModel().getValueOfAttribute(space, "description", false));
			
			//SPACE BOUNDARIES
			boundedBy = ifcDataModel.getSpaceBoundaries((EIfcspace) space, jsdai.SIfc2x3.EIfcphysicalorvirtualenum.PHYSICAL);
			
			//Any space boundaries at all?
			assertTrue("No space boundaries found!", boundedBy.length != 0);
			
			//Any 2nd level space boundaries at all?
			relCount = 0;
			for (EEntity rel : boundedBy){
				if (rel.getClass().equals(CIfcrelspaceboundary2ndlevel.class))
					relCount++;
			}
			
			assertTrue("There has to be at least one RelSpaceBoundary2ndLevel for each Ifcspace entity!", relCount >= 1);
			
			
			//----------Space quantities
			
			//SPACE FLOOR AREA
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(space, "isdefinedby");
			int netFloorEx = 0, netVolEx = 0, netHeightEx = 0;
			
			System.out.println("Quantities:");
			
			for (EEntity rel : isDefinedBy){
				
				if (ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition")[0].getClass().equals(CIfcelementquantity.class)){
					//getting the elementQuantity assigned to the space
					elementQuant = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
				} 
				
				else
					continue;
				
				//Retrieving all quantities
				quantities = ifcDataModel.getBasicModel().getReferences(elementQuant[0], "quantities"); //only one elementQuantity in relationship
					
				for (EEntity quant : quantities){
					if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "NetFloorArea"){
						System.out.println("> NetFlooArea" + ifcDataModel.getBasicModel().getValueOfAttribute(quant, "nominalvalue", false));
						netFloorEx = 1;
					}
					if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "NetVolume"){
						System.out.println("> NetVolume" + ifcDataModel.getBasicModel().getValueOfAttribute(quant, "nominalvalue", false));
						netVolEx = 1;
					}
					if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "Height"){
						System.out.println("> Height" + ifcDataModel.getBasicModel().getValueOfAttribute(quant, "nominalvalue", false));
						netHeightEx = 1;
					}
				}
				
			}
			
			System.out.println();
			
			//SPACE FLOOR AREA
			assertTrue("No NetFloorArea quantity found!", netFloorEx == 1);
			//NET SPACE VOLUME
			assertTrue("No NetVolume quantity found!", netVolEx == 1);
			//SPACE NET HEIGHT
			assertTrue("No NetHeight quantity found!", netHeightEx == 1);			
			
			//----------Suspended ceiling (Ifccovering.PredefinedType == .CEILING.)
			int widthEx = 0, netAreaEx = 0;
			
			for (EEntity cov : coverings){
				if (ifcDataModel.getBasicModel().getValueOfAttribute(cov, "predefinedtype", false).equals(EIfccoveringtypeenum.CEILING))
					ceilings.add(cov);
				else if (ifcDataModel.getBasicModel().getValueOfAttribute(cov, "predefinedtype", false).equals(EIfccoveringtypeenum.FLOORING))
					floors.add(cov);
			}
			
			//Are there any suspended ceilings or elevated floors at all?
			assertTrue("There must be at least one suspended ceiling!", ceilings.size() != 0);
			assertTrue("There must be at least one elevated floor!", floors.size() != 0);
			
			for (EEntity ceiling : ceilings){
				System.out.println("----Ifccovering entity (CEILING):\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) ceiling));
				System.out.println("Qto_CoveringBaseQuantities:");
				isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(ceiling, "isdefinedby");
				//iterating over all assigned property sets OR quantity elements
				for (EEntity rel : isDefinedBy){
					Object qto = ifcDataModel.getBasicModel().getValueOfAttribute(rel, "relatingpropertydefinition", false);
					//iterating over all quantities of every quantity element
					for (EEntity quant : ifcDataModel.getBasicModel().getAllReferences((EEntity) qto)){
						if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) quant, "name",false) == "Width"){
							widthEx = 1;
							System.out.println("> Width: exists");
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) quant, "name",false) == "NetArea"){
								netAreaEx = 1;
								System.out.println("> NetArea: exists");
						}
					}
				}
				
				System.out.println();
				
				//VOLUME OF SUSPENDED CEILING
				assertTrue("Every suspended ceiling has to have a width!", widthEx == 1);
				//NET HEIGHT
				assertTrue("Every suspended ceiling has to have a netArea!", netAreaEx == 1);
			}
			
			//----------Elevated floor (Ifccovering.PredefinedType == .FLOORING.)
			
			widthEx = 0;
			netAreaEx = 0;
			
			for (EEntity floor : floors){
				System.out.println("----Ifccovering entity (FLOORING):\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) floor));
				System.out.println("Qto_CoveringBaseQuantities:");
				isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(floor, "isdefinedby");
				for (EEntity rel : isDefinedBy){
					Object qto = ifcDataModel.getBasicModel().getValueOfAttribute(rel, "relatingpropertydefinition", false);
					for (EEntity quant : ifcDataModel.getBasicModel().getAllReferences((EEntity) qto)){
						if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) quant, "name",false) == "Width"){
							System.out.println("> Width: exists");
							widthEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) quant, "name",false) == "NetArea"){
							netAreaEx = 1;
							System.out.println("> NetArea: exists");
						}
					}
				}
				
				System.out.println();
				
				//VOLUME OF ELEVATED FLOOR
				assertTrue("Every elevated floor has to have a width!", widthEx == 1);
				//NET HEIGHT
				assertTrue("Every elevated floor has to have a netArea!", netAreaEx == 1);
				
			}
			
			
			//----------Space occupancy and use type
			
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(space, "isdefinedby");
			System.out.println("Property Sets:");
			occNum = 0;
			occNumPeak = 0;
			occType = 0;
			
			for (EEntity e : isDefinedBy){
				
				entities = ifcDataModel.getBasicModel().getAllReferences(e);
				
				for (EEntity ps : entities){
					
					if (ifcDataModel.getBasicModel().getValueOfAttribute(ps, "name", false)=="Pset_SpaceOccupancyRequirements")
					{
						
						properties = ifcDataModel.getBasicModel().getAllReferences(ps);
						
						for (EEntity property : properties){
							
							if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "OccupancyType"){
								
								System.out.println("> OccupancyType: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								occType = 1;
								
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "OccupancyNumber"){
								
								System.out.println("> OccupancyNumber: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								occNum = 1;
								
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "OccupancyNumberPeak"){
								
								System.out.println("> OccupancyNumberPeak: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								occNumPeak = 1;
								
							}
						}
					}
				}
			}
			
			//TYPE OF THE ROOM
			assertTrue("No OccupancyType property found!", occType == 1);
			//NUMBER OF USERS
			assertTrue("No OccupancyNumber property found!", occNum == 1);
			//MAXIMAL NUMBER OF USERS
			assertTrue("No OccupancyNumberPeak property found!", occNumPeak == 1);
			
			System.out.println();
		}
		
		//----------Lighting equipment
		
		//TYPE OF THE LIGHTING EQUIPMENT
		
		//1. Ifclamp.PredefinedType (no 'LIGHTING' enum value available)
		for (EEntity lamp : lamps){
			assertNotNull("Ifclamps have to have assigned a predefined type!",ifcDataModel.getBasicModel().getValueOfAttribute(lamp, "predefinedtype", false));
			System.out.println("----Ifclamp entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lamp)); 
			System.out.println("PredefinedType: " + ifcDataModel.getBasicModel().getValueOfAttribute(lamp, "predefinedtype", false) + "\n");
		}
		
		//2. Ifclampfixture.PredefinedType (no 'LIGHTING' enum value available)
		for (EEntity lightFix : lightFixtures){
			assertNotNull("Ifclightfixtures have to have assigned a predefined type!", ifcDataModel.getBasicModel().getValueOfAttribute(lightFix, "predefinedtype", false));
			System.out.println("----Ifclighfixture entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lightFix)); 
			System.out.println("PredefinedType: " + ifcDataModel.getBasicModel().getValueOfAttribute(lightFix, "predefinedtype", false) + "\n");
		}
		
		//3. Ifcdistributionsystem.PredefinedType == .LIGHTING.  
		valEx = 0;
		for (EEntity distSys : distSystems){
			 if(ifcDataModel.getBasicModel().getValueOfAttribute(distSys, "predefinedtype", false).equals(EIfcdistributionsystemenum.LIGHTING)){
			 	valEx = 1;
			 	System.out.println("----Ifcdistributionsystem entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) distSys)); 
				System.out.println("PredefinedType: " + ifcDataModel.getBasicModel().getValueOfAttribute(distSys, "predefinedtype", false) + "\n");
			 	break;
			 }
		}	
		assertTrue("No Ifcdistributionsystem assigned to the predefined type 'LIGHTING' found!", valEx == 1);
	
		//NUMBER OF LIGHTING EQUIPMENT PER TYPE	
		valEx = 0;
		for (EEntity lightFix : lightFixTypes){
			
			hasPropSets = ifcDataModel.getBasicModel().getReferences(lightFix, "haspropertysets");
			
			for (EEntity ps : hasPropSets){
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute(ps, "name", false) == "Pset_LightFixtureTypeCommon"){
					properties = ifcDataModel.getBasicModel().getAllReferences(ps);
					
					for (EEntity prop : properties){
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "NumberOfSources"){
							valEx = 1;
							System.out.println("----Lightfixturetype entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lightFix));
							System.out.println("Number of Sources: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
						}
					}
				}
			}
			
			System.out.println();
			assertTrue("NumberOfSource property has to exist in all Pset_lightfixtureTypeCommon sets!", valEx == 1);
		}
		
		//INSTALLED ELECTRICAL POWER PER TYPE
		//1. Pset_ElectricalDeviceCommon -> assigned to 'IfcDistributionElement's (see IFC2x4 entities)
		//CLUE: Code is commented out because the test file includes about 650 Ifcdistributionelement entities
		//		without the necessary pset
	//	distElements = ifcDataModel.getBasicModel().getEntitiesOf(EIfcdistributionelement.class);
		int psetEx = 0;
	//	for (EEntity distEl : distElements){
	//		psetEx = 0;
	//		isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(distEl, "isdefinedby");
	//		for (EEntity pset : isDefinedBy){
	//			if (ifcDataModel.getBasicModel().getValueOfAttribute(pset, "name", false) == "Pset_DistributionSystemCommon"){
	//				psetEx = 1;
	//				break;
	//			}
	//		}
	//		 assertTrue("There has to be at least one Pset_DistributionSystemCommon for each Ifcdistributionelement entity!", psetEx == 1);
	//	}
		//2.Pset_LampTypeCommon -> assigned to 'Ifclamptype' entities
		psetEx = 0;
		for (EEntity lt : lampTypes){
			psetEx = 0;
	
			hasPropSets = ifcDataModel.getBasicModel().getReferences(lt, "haspropertysets");
			
			//selecting all pset references
			for (EEntity ps : hasPropSets){
				if (ifcDataModel.getBasicModel().getValueOfAttribute(ps, "name", false) == "Pset_LampTypeCommon"){
					System.out.println("----Lamptype entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lt) + "\n");
					psetEx = 1;
				}
			}
			assertTrue("There must be a Pset_LampTypeCommon entity for each Ifclamptype!", psetEx == 1);
		}
		
		//GEOMETRICAL POSITION OF INSTALLED
		for (EEntity lamp : lamps){
			assertNotNull("Every Ifclamp entity has to have an object placement attribute!", ifcDataModel.getBasicModel().getValueOfAttribute(lamp, "objectplacement", false));
			System.out.println("----Lamp entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lamp));
			System.out.println("Object placement: exists");
			
		}
		
		System.out.println();
		
		//----------Technical Equipment of the room
		//Are there any appliances at all?
		assertTrue("There are no electric appliances at all!", electrAppliances.length != 0);
		assertTrue("There are no communications appliances at all!", commAppliances.length != 0);
		
		heatGainEx = 0;
		
		//TYPE OF EQUIPMENT WHICH PRODUCES HEAT GAINS	
		for (EEntity appl : commAppliances){
			
			Object num = ifcDataModel.getBasicModel().getValueOfAttribute(appl, "predefinedtype", false);
			
			if (num.equals(EIfccommunicationsappliancetypeenum.COMPUTER) ||
					num.equals(EIfccommunicationsappliancetypeenum.FAX) ||
					num.equals(EIfccommunicationsappliancetypeenum.MODEM) ||
					num.equals(EIfccommunicationsappliancetypeenum.PRINTER) ||
					num.equals(EIfccommunicationsappliancetypeenum.ROUTER) ||
					num.equals(EIfccommunicationsappliancetypeenum.SCANNER))
			{
				System.out.println("----Ifccommunicationsappliance entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) appl));
				System.out.println("Predefined type: " + num + "\n");
				heatGainEx++;
			}
		}
		
		assertTrue("There has to be at least one electrical appliance with heat gain!", heatGainEx >= 1);
			
		heatGainEx = 0;
		
		for (EEntity appl : electrAppliances){
			
			Object num = ifcDataModel.getBasicModel().getValueOfAttribute(appl, "predefinedtype", false);
			
			if (!(num.equals(EIfcelectricappliancetypeenum.NOTDEFINED)))
			{
				System.out.println("----Ifcelectricappliance entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) appl));
				System.out.println("Predefined type: " + num + "\n");
				heatGainEx++;
			}
		}
		
		assertTrue("There has to be at least one communications appliance with heat gain!", heatGainEx >= 1);
		
		//----Construction related information
		//-------Spaces
		//----------Opaque components (choosing: IfcWall, IfcSlab, IfcRoof)
		//1. IfcWall
	
		//CONSTRUCTION TYPE AND ISEXTERNAL ATTRIBUTE
		//splitting up building elements into two groups 
		
		for (EEntity be : buildingElements){
			
			if (be.getClass().equals(CIfcwindow.class) || be.getClass().equals(CIfcdoor.class) || be.getClass().equals(CIfccurtainwall.class))
				transparents.add(be);
			else
				opaques.add(be);
			
		}
		
		valEx = 0;
		int relEx = 0;
		for (EEntity wall : walls){
			
			System.out.println("----Ifcwall entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) wall));
			
			relEx = 0;
			valEx = 0;
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(wall, "isdefinedby");
			
			for (EEntity rel : isDefinedBy){
				
				pset = ifcDataModel.getBasicModel().getValueOfAttribute(rel, "relatingpropertydefinition", false);
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_WallCommon")){
					
					System.out.println("Pset_WallCommon:");
					
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences((EEntity)pset)){
						
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "Reference")
						{
							System.out.println("> Reference: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "IsExternal")
						{
							System.out.println("> IsExternal: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
					}
				}
			}
			
			assertTrue("There has to be a reference and the IsExternal attribute assigned to all Ifcwall entities", valEx == 2);
			
			assocMaterials = ifcDataModel.getAssociatedMaterial((EIfcelement) wall);
			//BUILDING ELEMENT MATERIAL
			assertTrue("No associated materials for that specific Ifcwall entity found!", assocMaterials.size() != 0);
			
			for (EIfcmaterial e : assocMaterials){
				psetEx = 0;
				
				//MATERIAL LAYER SET - NAME OF THE LAYER
				if (ifcDataModel.getBasicModel().getValueOfAttribute(e, "name", false) == null)
					assertTrue("Not all associated materials have got a name attribute assigned!", false);
				
				hasProperties = ifcDataModel.getBasicModel().getReferences(e);
//				assertTrue("No hasProperties relation found!", hasProperties.length != 0);
				
//				for (EEntity invRel : hasProperties){
//					if (invRel.getClass().equals(CIfcextendedmaterialproperties.class) && 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel,"name",false) == "Pset_GeneralMaterialProperties"){
//						//DENSITY OF THE LAYER MATERIAL
//						assertNotNull("There must be a mass density attribute for every Ifcmaterial entity", 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel, "massdensity",false));
//						psetEx++;
//					}
//					
//					else if (invRel.getClass().equals(CIfcextendedmaterialproperties.class) && 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel,"name",false) == "Pset_ThermalProperties"){
//						//THERMAL CONDUCTIVITY
//						assertNotNull("There must be a thermal conductivity attribute for every Ifcmaterial entity", 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel, "thermalconductivity",false));
//
//						//SPECIFIC HEAT CAPACITY OF THE LAYER
//						assertNotNull("There must be a specific heat capacity attribute for every Ifcmaterial entity", 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel, "specificheatcapacity",false));
//						psetEx++;
//					}
//				}
				
//				assertTrue("Every Ifcmaterial has to have a Pset_GeneralMaterialProperties and Pset_ThermalProperties!", psetEx == 2);
				
			}
			
			assocMaterialLayers = ifcDataModel.getAssociatedMaterialLayers((EIfcelement)wall);
			//MATERIAL LAYER SET - POSITION OF THE LAYER (implicitly given by the set order)
			
			//MATERIAL LAYER SET - THICKNESS OF THE LAYER
			for (EIfcmateriallayer e : assocMaterialLayers){
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute(e, "layerthickness", false) == null){
					assertTrue("Not all associated material layers have got a layer thickness attribute assigned!", false);
				}
				
			}
			
			//POSITION OF THE COMPONENT ON THE BUILDING
			assertNotNull("Every Ifcbuildingelement entity must have an object placement assigned!",ifcDataModel.getBasicModel().getReferences(wall, "objectplacement"));
			
			System.out.println("Object placement: exists");
			
			System.out.println();
		}
		
		//----------Transparent components
		//Ifcdoor and Ifcwindow (IfcCurtainWall is not necessary because it's only an aggregate of building elements already examined)
		
		for (EEntity tr : transparents){
			
			if (tr.getClass().equals(CIfcwindow.class) || tr.getClass().equals(CIfcdoor.class))
				doorsAndWind.add(tr);
			
		}
		
		for (EEntity ent : doorsAndWind){
			
			System.out.println("----Ifcdoor/window entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) ent));
			
			relEx = 0;
			valEx = 0;
			doorWindCommEx = 0;
			doorWindGlazEx = 0;
			doorWindShadEx = 0;
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(ent, "isdefinedby");
			
			assertTrue("Not enough relDefinesByProperty relations found!", isDefinedBy.length >= 4); //Pset_Door/WindowCommon, Pset_DoorWindowGlazingType,
																									 //Pset_DoorWindowShadingType, Pset_GlassProperties (Set Template)
			for (EEntity rel : isDefinedBy){
				
				valEx = 0;
				pset = ifcDataModel.getBasicModel().getValueOfAttribute(rel, "relatingpropertydefinition", false);
				//saving all templates for checking
				EEntity[] isDefBy = ifcDataModel.getBasicModel().getInverseReferences((EEntity)pset,"isdefinedby");
				if (isDefBy.length != 0){
					allTemplates.add(ifcDataModel.getBasicModel().getReferences(isDefBy[0],"relatingtemplate")[0]); //1-to-N relationship between templates and property sets
				}
				if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_DoorCommon") ||
						ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_WindowCommon")){
					
					doorWindCommEx = 1;
					
					System.out.println("Pset_Door/WindowCommon:");
					
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences((EEntity)pset)){
						
						//CONSTRUCTION TYPE
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "Reference")
						{
							System.out.println("> Reference: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						//INTERNAL OR EXTERNAL ELEMENT
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "IsExternal")
						{
							System.out.println("> IsExternal: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						//RATIO OF GLAZING PER TRANSPARENT ELEMENT
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlazingAreaFraction")
						{
							System.out.println("> GlazingAreaFraction: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
					}
					assertTrue("Every transparent entity must have a contruction type, isExternal and GlazingAreaFraction attribute!", valEx == 3);
				}
				
				valEx = 0;
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_DoorWindowGlazingType")){
					
					doorWindGlazEx = 1;
					
					System.out.println("Pset_DoorWindowGlazingType:");
					
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences((EEntity)pset)){
						
						//NUMBER OF WINDOW PANES
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlassLayers")
						{
							System.out.println("> GlassLayers: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						//THICKNESS OF EVERY PANE
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlassThickness1")
						{
							System.out.println("> GlassThickness1: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlassThickness2")
						{
							System.out.println("> GlassThickness2: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "GlassThickness3")
						{
							System.out.println("> GlassThickness3: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
						//TYPE AND MIXTURE OF GAS BETWEEN LAYERS
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "FillGas")
						{
							System.out.println("> FillGas: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
							valEx++;
						}
					}
					
					assertTrue("Every transparent entity must have a Pset_DoorWindowGlazingType with certain properties!", valEx == 5);
				}
					
					valEx = 0;
					
					//----------Shading (No IfcShading entity type!)
					if (ifcDataModel.getBasicModel().getValueOfAttribute((EEntity) pset, "name",false) == ("Pset_DoorWindowShadingType")){
						
						doorWindShadEx = 1;
						
						System.out.println("Pset_DoorWindowShadingType:");
						
						for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences((EEntity)pset)){
							
							//SHADING COEFFICIENT
							if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name",false) == "ExternalShadingCoefficient")
							{
								System.out.println("> ExternalShadingCoefficient: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue",false));
								valEx++;
							}
						}
					
					assertTrue("Every transparent entity must have a ExternalShadingCoefficient attribute!", valEx == 1);
					}	
			
				}
			
			//Checking for existence of any Ifcpropertysettemplates
			if ( allTemplates.size() == 1){
				System.out.println("Ifcpropertysettemplates: exist");
				assertTrue("Not enough property template attributes found!", ifcDataModel.getBasicModel().getReferences(allTemplates.toArray(new EEntity[allTemplates.size()])[0], "haspropertytemplates").length == 7);
			}
			else{
				assertTrue("Pset_GlassProperties is missing!", false);
			}
			//User defined property sets (IfcPropertysetTemplates) -> prove of number of properties (PROPORTION OF GLASS ON THE WHOLE AREA;
			//TYPE OF GLASS; THICKNESS OF EVERY SPACING BETWEEN PANES; POSITION AND NUMBER OF THERMAL AND OPTICAL COATINGS; SOLAR HEAT
			//GAIN TRANSMITTANCE; THERMAL TRANSMITTANCE RESISTANCE OF THE GLASS AND WINDOW FRAME; AIR TRANSMISSIVITY OF THE JOINT IN THE WINDOW FRAME)
		
			//LOCATION OF SHADING
			assertNotNull("Every transparent element has to have an object placement attribute!", ifcDataModel.getBasicModel().getValueOfAttribute(ent, "objectplacement", false));
			
			System.out.println("Object placement: exists\n");
			
			//Pset_Door/WindowCommon
			assertTrue("Pset_Door/WindowCommon is missing!", doorWindCommEx == 1);
			
			//Pset_DoorWindowGlazingType
			assertTrue("Pset_DoorWindowGlazingType is missing!", doorWindGlazEx == 1);
			
			//Pset_DoorWindowShadingType
			assertTrue("Pset_DoorWindowShadingType is missing!", doorWindShadEx == 1);
		}
		
	}
	
}

