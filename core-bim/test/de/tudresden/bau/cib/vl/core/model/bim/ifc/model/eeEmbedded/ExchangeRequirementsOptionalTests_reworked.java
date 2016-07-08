package de.tudresden.bau.cib.vl.core.model.bim.ifc.model.eeEmbedded;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import jsdai.SIfc4.CIfccurtainwall;
import jsdai.SIfc4.CIfcderivedunit;
import jsdai.SIfc4.CIfcdoor;
import jsdai.SIfc4.CIfcelementquantity;
import jsdai.SIfc4.CIfcrelspaceboundary2ndlevel;
import jsdai.SIfc4.CIfcsiunit;
import jsdai.SIfc4.CIfcwindow;
import jsdai.SIfc4.EIfcbuilding;
import jsdai.SIfc4.EIfcbuildingelement;
import jsdai.SIfc4.EIfccommunicationsappliance;
import jsdai.SIfc4.EIfccommunicationsappliancetypeenum;
import jsdai.SIfc4.EIfccovering;
import jsdai.SIfc4.EIfccoveringtypeenum;
import jsdai.SIfc4.EIfcderivedunitenum;
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
import jsdai.SIfc4.EIfcproduct;
import jsdai.SIfc4.EIfcproject;
import jsdai.SIfc4.EIfcroot;
import jsdai.SIfc4.EIfcsite;
import jsdai.SIfc4.EIfcspace;
import jsdai.SIfc4.EIfcunitenum;
import jsdai.SIfc4.EIfcwall;
import jsdai.SIfc4.EIfcwindow;
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
import de.tudresden.bau.cib.model.StepDataModel;
import de.tudresden.bau.cib.parser.StepParser;
	
/**
 * Test class for testing a test file for exchange requirements 1.1,2.1,3.1 ARCH (optional) determined in the document 'Interoperability
 * and collaboration requirements'
 * @author Max Vollstaedt
 *
 */

/*Comments:
 *- 1.1 ARCH > Design Requirements > Pset_BuildingDesignRequirements > prove if the set template exists necessary?
 *- 1.1 ARCH > Building Information > Building > Building envelope geometry > Window position > prove if the local
 *  placement of each window is relative to the local placement of one IfcBuilding?
 *- 1.1 ARCH > Space related information > Space furniture > Heat capacity of material: How to prove?
 *- 2.1 ARCH > Design Requirements > HVAC > Efficiency > COP target: 558 IfcDistributionFlowElements which would
 *	need to be in a relation with one Pset_HVACDesignRequirements set  
 */

	public class ExchangeRequirementsOptionalTests_reworked {

		@Rule
		public ErrorCollector collector = new ErrorCollector();
		
		private static StepParser parser = new StepParser("D:\\repo");
		private static IfcDataModel ifcDataModel;
		private  static String pathTestFolder = "resources/ifc/";	
		
		//Often used variables
		
		EEntity[] isDefinedBy, propSet, boundedBy, hasProperties;

		EEntity[] hasPropSets, unitAssignm;
		EEntity[] properties, quantities;

		EEntity[] ifcProjectEntity, electrAppliances, commAppliances, walls, products;
		EEntity[] elementQuant, distSystems, buildingElements, distElements;
		EEntity[] entities, ifcSites, ifcBuildings, spaces, windows, distFlowElements;
		EEntity[] lightFixtures, lightFixTypes, lamps, lampTypes, coverings;
		
		Set<EEntity> allTemplates = new HashSet<EEntity>();
		Set<EEntity> transparents = new HashSet<EEntity>();
		Set<EEntity> opaques = new HashSet<EEntity>();
		Set<EEntity> floors = new HashSet<EEntity>();
		Set<EEntity> ceilings = new HashSet<EEntity>();
		Set<EEntity> doorsAndWind = new HashSet<EEntity>();
		Set<EIfcmaterial> assocMaterials = new HashSet<EIfcmaterial>();
		Set<EIfcmateriallayer> assocMaterialLayers = new HashSet<EIfcmateriallayer>();
		
		Object pset;
		String name, descr;
		boolean relExists;
		int valEx = 0, occType = 0, occNum = 0, occNumPeak = 0;
		int lengthEx = 0, areaEx = 0, volEx = 0, tmpEx = 0, flowEx = 0;
		int heatDemandEx = 0, coolDemandEx = 0, electrDemandEx = 0, renEnEx = 0, waterDemandEx = 0;
		int geomEx = 0, relCount = 0, relEx = 0;
		int tmpSMax = 0, tmpSMin = 0, humMax = 0, humMin = 0, tmpWMax = 0, tmpWMin = 0, natVen = 0, natVenRate = 0, mechVenRate = 0;
		int tmpTarg = 0, humTarg = 0, coTarg = 0, copTarg = 0, heatGainEx = 0;
		int doorWindCommEx = 0, doorWindGlazEx = 0, doorWindShadEx = 0, glassPropsEx = 0;
		int perEx = 0, psetEx = 0, checked = 0;
		

		//test file	
		private static String pathTestFile = "ModernHomePrototype_reworked_Max.ifc";	

		
		
		/**
		 * @throws java.lang.Exception
		 */
		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
//			String pathToTestFile = props.getProperty("testfile");
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
		unitAssignm = ifcDataModel.getBasicModel().getReferences(ifcProjectEntity[0], "unitsincontext");
		windows = ifcDataModel.getBasicModel().getEntitiesOf(EIfcwindow.class);
		products = ifcDataModel.getBasicModel().getEntitiesOf(EIfcproduct.class);
		distElements = ifcDataModel.getBasicModel().getEntitiesOf(EIfcdistributionelement.class);
			
		System.out.println("\n-------------------------------------Optional Items Test:-------------------------------------\n");
		//Includes 'recommended' exchange requirements (see exchange req. table, 2nd column)
		
		}

		/**
		 * @throws java.lang.Exception
		 */
		@After
		public void tearDown() throws Exception {
		}
	
		@Test
		public void Arch11() throws SdaiException{
			
			
			/*----------------------------------------------------Exchange Requirements 1.1 ARCH----------------------------------------------------*/
			
			System.out.println("\nExchange Requirements 1.1 ARCH\n");
			
			//----Project Information
			//-------Project Context
			//----------Project Attributes
			
			ifcProjectEntity = ifcDataModel.getBasicModel().getEntitiesOf(EIfcproject.class);
			
			System.out.println("----Ifcproject entity:");
			System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcProjectEntity[0]));
			
			//PHASE
			if (ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], "phase", false) == null)
				collector.addError(new Throwable("There must be a phase attribute!"));
			System.out.println("Phase: " + ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], "phase", false));
			
			//----------Project Units (prints out enum integer values only)
			
			//Are there any assignments at all?
			if (unitAssignm.length == 0)
				collector.addError(new Throwable("There are no unit assignments at all!"));
			
			for (EEntity ua : unitAssignm){
				
				EEntity[] units = ifcDataModel.getBasicModel().getReferences(ua, "units");
				
				for (EEntity u : units){
					
					if (u.getClass().equals(CIfcsiunit.class)){
			
						if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.LENGTHUNIT)){
								System.out.println("Length Unit: METRE");
								lengthEx = 1;
						}
						
						if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.AREAUNIT)){
							System.out.println("Area Unit: SQUARE METRE");
							areaEx = 1;
						}
						
						if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.VOLUMEUNIT)){
							System.out.println("Volume Unit: CUBIC METRE");
							volEx = 1;
						}
						
						if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.THERMODYNAMICTEMPERATUREUNIT)){
							System.out.println("Thermodynamic temperature Unit: KELVIN");
							tmpEx = 1;
						}
						
						//OTHERS
						else
						{
							System.out.println("Other Unit: " + ifcDataModel.getBasicModel().getValueOfAttribute(u, "name", false));
						}
					}
					
					int cmplxSi = 0;
					
					//Check if the derived unit only consists of SI unit elements
					
					if (u.getClass().equals(CIfcderivedunit.class)){
						
						for (EEntity elem : ifcDataModel.getBasicModel().getReferences(u, "elements")){
							
							if (ifcDataModel.getBasicModel().getReferences(elem, "unit")[0].getClass().equals(CIfcsiunit.class)) //only SI units allowed
								cmplxSi++;
							
						}
						
						if (cmplxSi == ifcDataModel.getBasicModel().getReferences(u, "elements").length)
						{
							 
							if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcderivedunitenum.VOLUMETRICFLOWRATEUNIT)){
								System.out.println("Volumetric flow rate unit: exists");
								flowEx = 1;
							}
						
							//OTHERS
							else
							{
								System.out.println("Other Unit: " + ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false));
							}
							
						}
						
					}
				}
				
				//LENGTH UNIT
				if (lengthEx == 0)
					collector.addError(new Throwable("No length unit found!"));
				//VOLUME UNIT
				if (volEx == 0)
					collector.addError(new Throwable("No volume unit found!"));
				//AREA UNIT
				if (areaEx == 0)
					collector.addError(new Throwable("No area unit found!"));
				//TEMPERATURE UNIT
				if (tmpEx == 0)
					collector.addError(new Throwable("No temperature unit found!"));
				//VOLUMETRIC FLOW UNIT
				if (flowEx == 0)
					collector.addError(new Throwable("No volumetric flow unit found!"));
			
			}
			
		System.out.println();
		
		//----Design Requirements
		//-------Building
		//----------Energy
		
		for (EEntity building : ifcBuildings){
			
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(building, "isdefinedby");
			
			for (EEntity rel : isDefinedBy){
				
				propSet = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute(propSet[0], "name", false) == "Pset_BuildingDesignRequirements"){
					
					System.out.println("Pset_BuildingDesignRequirements:");
					
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences(propSet[0])){
						
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "HeatDemand"){
							System.out.println("> HeatDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							heatDemandEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "CoolingDemand"){
							System.out.println("> CoolingDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							coolDemandEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "ElectricityDemand"){
							System.out.println("> ElectricityDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							electrDemandEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "LevelOfRenewableEnergy"){
							System.out.println("> LevelOfRenewableEnergy: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							renEnEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "WaterDemand"){
							System.out.println("> WaterDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							waterDemandEx = 1;
						}
					}
					
				}
				
				//MAXIMAL HEATING DEMAND PER YEAR
				if (heatDemandEx == 0)
					collector.addError(new Throwable("No HeatDemand property found!"));
				//MAXIMAL COOLING DEMAND PER YEAR
				if (coolDemandEx == 0)
					collector.addError(new Throwable("No CoolingDemand property found!"));
				//MAXIMAL ELECTRICITY DEMAND PER YEAR
				if (electrDemandEx == 0)
					collector.addError(new Throwable("No ElectricityDemand property found!"));
				//TARGET LEVEL OF RENEWABLE ENERGY
				if (renEnEx == 0)
					collector.addError(new Throwable("No LevelOfRenewableEnergy property found!"));
				//WATER DEMAND PER YEAR
				if (waterDemandEx == 0)
					collector.addError(new Throwable("No WaterDemand property found!"));
				
			}
		}
			
		System.out.println();
	
		
		//----Site Information
		//-------Site
		//----------Site Quantities
	
		valEx = 0;
		
		perEx = 0;
		areaEx = 0;
			
		for (EEntity site : ifcSites){
				
			System.out.println("----Ifcsite entity:");
			System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) site));
				
			valEx = 0;
				
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(site, "isdefinedby");
				
			//Are there any relations at all?
			if (isDefinedBy.length == 0)
				collector.addError(new Throwable("There are not isDefinedBy relations at all!"));
			else
			{
				for (EEntity rel : isDefinedBy){
						
							
					elementQuant = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
							
					if (elementQuant[0].getClass().equals(CIfcelementquantity.class)){
							
						System.out.println("Qto_SiteBaseQuantities:");
							
						quantities = ifcDataModel.getBasicModel().getReferences(elementQuant[0], "quantities");
						
						for (EEntity quant : ifcDataModel.getBasicModel().getReferences(elementQuant[0], "quantities")){ //1-to-N relationship
								
							if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "Perimeter"){
								System.out.println("> Perimeter: exists");
								perEx = 1;
							}
								
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "GrossArea"){
								System.out.println("> Gross Area: exists");
								areaEx = 1;
							}
						}
					}	
				}
					
				//GROSS PERIMETER
				if (perEx == 0)
					collector.addError(new Throwable("Every Ifcsite entity must have a perimeter attribute assigned!"));
				//GROSS AREA
				if (areaEx == 0)
					collector.addError(new Throwable("Every Ifcsite entity must have an area attribute assigned!"));
			}

			perEx = 0;
			areaEx = 0;
			
			
			System.out.println();
		}
		
		//----Building Information
		//-------Building
		//----------Building envelope geometry
		
		//WINDOW POSITION
		for (EEntity window : windows){
			
			//Looking for an Ifcbuilding entity which is used as a relative placement for window entities
			
			System.out.println("\nIfcwindow Global ID: " + ifcDataModel.getGlobalId((EIfcroot) window));
			
			relExists = false;
			
			Object localPlacement = ifcDataModel.getBasicModel().getValueOfAttribute(window, "objectplacement", false);
			
			EEntity[] placementRelTo = ifcDataModel.getBasicModel().getReferences((EEntity)localPlacement, "placementrelto");
			
			if (placementRelTo.length == 0)
				collector.addError(new Throwable("No relative local placement found!")); //avoiding NullPointerExceptions
			
			EEntity[] placesObject = ifcDataModel.getBasicModel().getInverseReferences(placementRelTo[0], "placesobject");
			
//			for (EEntity obj : placesObject){
//				
//				if (obj.getClass().equals(CIfcbuilding.class))
//					relExists = true;
//				
//			}
			
			if (placesObject.length == 0)
				collector.addError(new Throwable("Every Ifcwindow entity has to be placed relatively to another entity!"));

			System.out.println("Relative local placement: exists");
		}
		
			
		//----Space related information
		//-------Spaces
		//----------Spaces general
		
		relCount = 0;
		
		checked = 0;
		
		for (EEntity space : spaces){
		
			System.out.println("----------------Ifcspace Entity:----------------");
			
			//SPACE ID (GlobalID)
			if (ifcDataModel.getGlobalId((EIfcroot) space) == null)
				collector.addError(new Throwable("No Global space ID found!"));
			System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) space));
			
			//SPACE NUMBER (name)
			if (ifcDataModel.getBasicModel().getValueOfAttribute(space, "name", false) == null)
				collector.addError(new Throwable("No space name attribute found!"));
			System.out.println("Name: " + ifcDataModel.getBasicModel().getValueOfAttribute(space, "name", false));
			
			//SPACE NAME or DESCRIPTION
			name = (String) ifcDataModel.getBasicModel().getValueOfAttribute(space, "longname", false);
			descr = (String) ifcDataModel.getBasicModel().getValueOfAttribute(space, "description", false);
			if (name == null && descr == null)
				collector.addError(new Throwable("No description or long name found!"));
			
			if (name != null)
				System.out.println("Long name: " + ifcDataModel.getBasicModel().getValueOfAttribute(space, "longname", false));
			if (descr != null)
				System.out.println("Description: " + ifcDataModel.getBasicModel().getValueOfAttribute(space, "description", false));
			
			boundedBy = ifcDataModel.getSpaceBoundaries((EIfcspace) space, jsdai.SIfc2x3.EIfcphysicalorvirtualenum.PHYSICAL);
			
			//Any space boundaries at all?
			if (boundedBy.length == 0)
				collector.addError(new Throwable("No space boundaries found!"));
			
			//Any 2nd level space boundaries at all?
			relCount = 0;
			for (EEntity rel : boundedBy){
				if (rel.getClass().equals(CIfcrelspaceboundary2ndlevel.class))
					relCount++;
			}
			
			//SPACE BOUNDARIES
			if (relCount == 0)
				collector.addError(new Throwable("There has to be at least one RelSpaceBoundary2ndLevel for each Ifcspace entity!"));
			
			
			//----------Space quantities
			
			//SPACE FLOOR AREA
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(space, "isdefinedby");
			int netFloorEx = 0, netVolEx = 0, netHeightEx = 0;
			
			if (isDefinedBy.length != 0)
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
			if (netFloorEx == 0)
				collector.addError(new Throwable("No NetFloorArea quantity found!"));
			//NET SPACE VOLUME EXCL. ELEVATED FLOOR
			if (netVolEx == 0)
				collector.addError(new Throwable("No NetVolume quantity found!"));
			//NET HEIGHT
			if (netHeightEx == 0)
				collector.addError(new Throwable("No NetHeight quantity found!"));	
			
			//----------Suspended ceiling (Ifccovering.PredefinedType == .CEILING.)
			int widthEx = 0, netAreaEx = 0;
			
			for (EEntity cov : coverings){
				if (ifcDataModel.getBasicModel().getValueOfAttribute(cov, "predefinedtype", false).equals(EIfccoveringtypeenum.CEILING))
					ceilings.add(cov);
				else if (ifcDataModel.getBasicModel().getValueOfAttribute(cov, "predefinedtype", false).equals(EIfccoveringtypeenum.FLOORING))
					floors.add(cov);
			}
			
			//Are there any suspended ceilings or elevated floors at all?
			if (ceilings.size() == 0)
				collector.addError(new Throwable("There must be at least one suspended ceiling!"));
			if (floors.size() == 0)
				collector.addError(new Throwable("There must be at least one elevated floor!"));
			
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
				if (widthEx== 0)
					collector.addError(new Throwable("Every suspended ceiling has to have a width!"));
				//NET HEIGHT
				if (netAreaEx== 0)
					collector.addError(new Throwable("Every suspended ceiling has to have a netArea!"));
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
				if (widthEx== 0)
					collector.addError(new Throwable("Every elevated floor has to have a width!"));
				//NET HEIGHT
				if (netAreaEx== 0)
					collector.addError(new Throwable("Every elevated floor has to have a netArea!"));
				
			}
			
			
			//-------Space furniture/user specific equipment
			
			
	//		
	//		for (EEntity product : products){
	//			
	//			geomEx = 0;
	//			volEx = 0;
	//			
	//			EEntity[] representations = ifcDataModel.getBasicModel().getReferences(product, "representation");
	//			if (representations.length == 0)
    //					collector.addError(new Throwable("Every Ifcproduct entity must have a representation!"));
	//			
	//			
	//			if (ifcDataModel.getBasicModel().getValueOfAttribute(representations[0], "name", false) != null){
	//				//GEOMETRY OF FURNITURE
	//				if (ifcDataModel.getBasicModel().getValueOfAttribute(representations[0], "name", false).toString().toLowerCase().contains("geometry")){
	//					geomEx = 1;
	//				}
	//				
	//				//VOLUME OF FURNITURE
	//				if (ifcDataModel.getBasicModel().getValueOfAttribute(representations[0], "name", false).toString().toLowerCase().contains("volume")){
	//					volEx = 1;
	//				}
	//			}
	//					
	//		}
	//			
    //			if (geomEx == 0)
    //				collector.addError(new Throwable("Every Ifcproduct entity must have a geometric representation!"));
	//			if (volEx == 0)
	//	 			collector.addError(new Throwable("Every Ifcproduct entity must have a volume representation!"));
	//		}		
		
			if (products.length == 0)
				collector.addError(new Throwable("No Ifcproduct entities found!"));
				
			if (checked == 0){
				for (EEntity product : products){
					//POSITION OF FURNITURE
					if (ifcDataModel.getBasicModel().getValueOfAttribute(product, "objectplacement", false) == null)
						collector.addError(new Throwable("Every Ifcproduct entity must have an object placement attribute!"));
					
					//GEOMETRY OF FURNITURE and VOLUME OF FURNITURE
					if (ifcDataModel.getBasicModel().getValueOfAttribute(product, "representation", false) == null || ifcDataModel.getBasicModel().getValueOfAttribute(product, "representation", false) == "")
						collector.addError(new Throwable("Every Ifcproduct entity must have a representation!"));
				}
				checked = 1;
			}
			//HEAT CAPACITY OF MATERIAL
			//non provable
			
			
			//----------Space occupancy and use type
			
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(space, "isdefinedby");
			
			if (isDefinedBy.length != 0)
				System.out.println("Property Sets:");
			
			psetEx = 0;
			occNum = 0;
			occNumPeak = 0;
			occType = 0;
			
			for (EEntity e : isDefinedBy){
				
				entities = ifcDataModel.getBasicModel().getAllReferences(e);
				
				for (EEntity ps : entities){
					
					if (ifcDataModel.getBasicModel().getValueOfAttribute(ps, "name", false)=="Pset_SpaceOccupancyRequirements")
					{
						psetEx = 1;
						
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
			
			if (psetEx == 0)
				collector.addError(new Throwable("No Pset_SpaceOccupancyRequirements found!"));
			else
			{
				//TYPE OF THE ROOM
				if (occType == 0)
					collector.addError(new Throwable("No OccupancyType property found!"));
				//NUMBER OF USERS
				if (occNum == 0)
					collector.addError(new Throwable("No OccupancyNumber property found!"));
				//MAXIMAL NUMBER OF USERS
				if (occNumPeak == 0)
					collector.addError(new Throwable("No OccupancyNumberPeak property found!"));
			}
			
			System.out.println();
			
			psetEx = 0;
		}
			
		
		//-------Thermal set points	
		for (EEntity space : spaces){
		
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(space, "isdefinedby");
			System.out.println("\nPset_SpaceThermalRequirements:");
			tmpSMax = 0;
			tmpSMin = 0;
			humMax = 0;
			humMin = 0;
			tmpWMax = 0;
			tmpWMin = 0;
			natVen = 0;
			natVenRate = 0;
			mechVenRate = 0;
			psetEx = 0;
			
			for (EEntity e : isDefinedBy){
			
				entities = ifcDataModel.getBasicModel().getAllReferences(e);
			
				for (EEntity ps : entities){
				
					if (ifcDataModel.getBasicModel().getValueOfAttribute(ps, "name", false)=="Pset_SpaceThermalRequirements")
					{
						psetEx = 1;
						
						System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) ps));
						
						properties = ifcDataModel.getBasicModel().getAllReferences(ps);
					
						for (EEntity property : properties){
						
							if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "SpaceTemperatureSummerMax"){
								System.out.println("> SpaceTemperatureSummerMax: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								tmpSMax = 1;
							
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "SpaceTemperatureSummerMin"){
								System.out.println("> SpaceTemperatureSummerMin: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								tmpSMin = 1;
							
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "SpaceHumidityMax"){
								System.out.println("> SpaceHumidityMax: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								humMax = 1;
							
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "SpaceHumidityMin"){
								System.out.println("> SpaceHumidityMin: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								humMin = 1;
							
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "SpaceTemperatureWinterMax"){
								System.out.println("> SpaceTemperatureWinterMax: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								tmpWMax = 1;
							
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "SpaceTemperatureWinterMin"){
								System.out.println("> SpaceTemperatureWinterMin: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								tmpWMin = 1;
							
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "NaturalVentilation"){
								System.out.println("> NaturalVentilation: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								natVen = 1;
							
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "NaturalVentilationRate"){
								System.out.println("> NaturalVentilationRate: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								natVenRate = 1;
							
							}
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(property, "name", false) == "MechanicalVentilationRate"){
								System.out.println("> MechanicalVentilationRate: " + ifcDataModel.getBasicModel().getValueOfAttribute(property, "nominalvalue", false));
								mechVenRate = 1;
							
							}
							
							//No CO2 MAXIMUM property
							//No INLETAIRTEMPERATURE property
							//No FRESHAIRRATE property
						}
					}
				}
			}

			if (psetEx == 0)
				collector.addError(new Throwable("No Pset_SpaceThermalRequirements found!"));
			else
			{
				//DESIGN TEMPERATURE MAXIMUM - COOLING
				if (tmpSMax == 0)
					collector.addError(new Throwable("No SpaceTemperatureSummerMax property found!"));
				//DESIGN TEMPERATURE MININUM - COOLING
				if (tmpSMin == 0)
					collector.addError(new Throwable("No SpaceTemperatureSummerMin property found!"));
				//DESIGN HUMIDITY - MINIMUM
				if (humMin == 0)
					collector.addError(new Throwable("No SpaceHumidityMin property found!"));
				//DESIGN HUMIDITY - MAXIMUM
				if (humMax == 0)
					collector.addError(new Throwable("No SpaceHumidityMax property found!"));
				//DESIGN TEMPERATURE MAXIMUM - HEATING
				if (tmpWMax == 0)
					collector.addError(new Throwable("No SpaceTemperatureWinterMax property found!"));
				//DESIGN TEMPERATURE MINIMUM - HEATING
				if (tmpWMin == 0)
					collector.addError(new Throwable("No SpaceTemperatureWinterMin property found!"));
				//NATURAL VENILATION
				if (natVen == 0)
					collector.addError(new Throwable("No NaturalVentilation property found!"));
				//NATURAL VENILATION RATE
				if (natVenRate == 0)
					collector.addError(new Throwable("No NaturalVentilationRate property found!"));
				//MECHANICAL VENTIALTION RATE
				if (mechVenRate == 0)
					collector.addError(new Throwable("No MechanicalVentilationRate property found!"));
				
				psetEx = 0;
			}
		}
	
			
		//----------Lighting equipment (2x4 exclusive)
		
		//TYPE OF THE LIGHTING EQUIPMENT
		
		//1. Ifclamp.PredefinedType (no 'LIGHTING' enum value available)
		
		if (lamps.length == 0)
			collector.addError(new Throwable("No Ifclamp entities found!"));
		
		for (EEntity lamp : lamps){
			if (ifcDataModel.getBasicModel().getValueOfAttribute(lamp, "predefinedtype", false) == null)
				collector.addError(new Throwable("Ifclamps have to have assigned a predefined type!"));
			System.out.println("----Ifclamp entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lamp)); 
			System.out.println("PredefinedType: " + ifcDataModel.getBasicModel().getValueOfAttribute(lamp, "predefinedtype", false) + "\n");
		}
		
		//2. Ifclampfixture.PredefinedType (no 'LIGHTING' enum value available)
		
		if (lightFixtures.length == 0)
			collector.addError(new Throwable("No Ifclightfixture entities found!"));
		
		for (EEntity lightFix : lightFixtures){
			if (ifcDataModel.getBasicModel().getValueOfAttribute(lightFix, "predefinedtype", false) == null)
				collector.addError(new Throwable("Ifclightfixtures have to have assigned a predefined type!"));
			System.out.println("----Ifclighfixture entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lightFix)); 
			System.out.println("PredefinedType: " + ifcDataModel.getBasicModel().getValueOfAttribute(lightFix, "predefinedtype", false) + "\n");
		}
		
		//3. Ifcdistributionsystem.PredefinedType == .LIGHTING.
		
		if (distSystems.length == 0)
			collector.addError(new Throwable("No Ifcdistributionsystem entities found!"));
		else {
			valEx = 0;
			for (EEntity distSys : distSystems){
				 if(ifcDataModel.getBasicModel().getValueOfAttribute(distSys, "predefinedtype", false).equals(EIfcdistributionsystemenum.LIGHTING)){
				 	valEx = 1;
				 	System.out.println("----Ifcdistributionsystem entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) distSys)); 
					System.out.println("PredefinedType: " + ifcDataModel.getBasicModel().getValueOfAttribute(distSys, "predefinedtype", false) + "\n");
				 	break;
				 }
			}
			if (valEx == 0)
				collector.addError(new Throwable("No Ifcdistributionsystem assigned to the predefined type 'LIGHTING' found!"));
		}
			
		//NUMBER OF LIGHTING EQUIPMENT PER TYPE	
		
		if (lightFixTypes.length == 0)
			collector.addError(new Throwable("No Ifclightfixturetype entities found!"));
		
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
			
			if (valEx == 0)
				collector.addError(new Throwable("NumberOfSource property has to exist in all Pset_lightfixtureTypeCommon sets!"));
		}
		
		//INSTALLED ELECTRICAL POWER PER TYPE
		//1. Pset_ElectricalDeviceCommon -> assigned to 'IfcDistributionElement's (see IFC2x4 entities)
		
		if (distElements.length == 0)
			collector.addError(new Throwable("No Ifcdistributionelement entities found!"));
		
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
			
			if (psetEx == 0)
				collector.addError(new Throwable("There has to be at least one Pset_DistributionSystemCommon for each Ifcdistributionelement entity!"));
	
		}
		//2.Pset_LampTypeCommon -> assigned to 'Ifclamptype' entities
		
		if (lampTypes.length != 0)
			collector.addError(new Throwable("No Ifclamptype entities found!"));

		
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
			
			if (psetEx == 0)
				collector.addError(new Throwable("There must be a Pset_LampTypeCommon entity for each Ifclamptype!"));
			
		}
		
		//GEOMETRICAL POSITION OF INSTALLED
		for (EEntity lamp : lamps){
			assertNotNull("Every Ifclamp entity has to have an object placement attribute!", ifcDataModel.getBasicModel().getValueOfAttribute(lamp, "objectplacement", false));
			System.out.println("----Lamp entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) lamp));
			System.out.println("Object placement: exists");
			
		}
		
		System.out.println();
		
		//----------Technical Equipment of the room

		heatGainEx = 0;
		
		//TYPE OF EQUIPMENT WHICH PRODUCES HEAT GAINS
		if (commAppliances.length == 0)
			collector.addError(new Throwable("There are no communications appliances at all!"));
		else
		{
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
			if (heatGainEx == 0)
				collector.addError(new Throwable("There has to be at least one communications appliance with heat gain!"));
		}
		
		heatGainEx = 0;
		
		if (electrAppliances.length == 0)
			collector.addError(new Throwable("There are no electric appliances at all!"));
		else
		{
			for (EEntity appl : electrAppliances){
				
				Object num = ifcDataModel.getBasicModel().getValueOfAttribute(appl, "predefinedtype", false);
				
				if (!(num.equals(EIfcelectricappliancetypeenum.NOTDEFINED)))
				{
					System.out.println("----Ifcelectricappliance entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) appl));
					System.out.println("Predefined type: " + num + "\n");
					heatGainEx++;
				}
			}
			if (heatGainEx == 0)
				collector.addError(new Throwable("There has to be at least one electrical appliance with heat gain!"));
		}
		
		
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
		
		if (walls.length == 0)
			collector.addError(new Throwable("No wall entities found!"));
		
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
			
			if (valEx != 2)
				collector.addError(new Throwable("There has to be a reference and the IsExternal attribute assigned to all Ifcwall entities"));
			
			assocMaterials = ifcDataModel.getAssociatedMaterial((EIfcelement) wall);
			//BUILDING ELEMENT MATERIAL
			if (assocMaterials.size() == 0)
				collector.addError(new Throwable("No associated materials for that specific Ifcwall entity found!"));
		
			for (EIfcmaterial e : assocMaterials){
				psetEx = 0;
				
				//MATERIAL LAYER SET - NAME OF THE LAYER
				if (ifcDataModel.getBasicModel().getValueOfAttribute(e, "name", false) == null)
					collector.addError(new Throwable("Not all associated materials have got a name attribute assigned!"));
				
				hasProperties = ifcDataModel.getBasicModel().getReferences(e);
				
//				for (EEntity invRel : hasProperties){
//					if (invRel.getClass().equals(CIfcextendedmaterialproperties.class) && 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel,"name",false) == "Pset_GeneralMaterialProperties"){
//						//DENSITY OF THE LAYER MATERIAL
//						if (ifcDataModel.getBasicModel().getValueOfAttribute(invRel, "massdensity",false) == null)
//							collector.addError(new Throwable("There must be a mass density attribute for every Ifcmaterial entity"));
//						psetEx++;
//					}
//					
//					else if (invRel.getClass().equals(CIfcextendedmaterialproperties.class) && 
//						ifcDataModel.getBasicModel().getValueOfAttribute(invRel,"name",false) == "Pset_ThermalProperties"){
//						//THERMAL CONDUCTIVITY
//						if (ifcDataModel.getBasicModel().getValueOfAttribute(invRel, "thermalconductivity",false) == null)
//							collector.addError(new Throwable("There must be a thermal conductivity attribute for every Ifcmaterial entity"));
//
//						//SPECIFIC HEAT CAPACITY OF THE LAYER
//						if (ifcDataModel.getBasicModel().getValueOfAttribute(invRel, "specificheatcapacity",false) == null)
//							collector.addError(new Throwable("There must be a specific heat capacity attribute for every Ifcmaterial entity"));
//						psetEx++;
//					}
//				}
				
				if (psetEx != 2)
					collector.addError(new Throwable("Every Ifcmaterial has to have a Pset_GeneralMaterialProperties and Pset_ThermalProperties!"));
				
			}
			
//			assocMaterialLayers = ifcDataModel.getAssociatedMaterialLayers((EIfcelement) wall);
			//MATERIAL LAYER SET - POSITION OF THE LAYER (implicitly given by the set order)
			
			//MATERIAL LAYER SET - THICKNESS OF THE LAYER
			for (EIfcmateriallayer e : assocMaterialLayers){
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute(e, "layerthickness", false) == null)
					collector.addError(new Throwable("Not all associated material layers have got a layer thickness attribute assigned!"));	
			}
			
			//POSITION OF THE COMPONENT ON THE BUILDING
			if (ifcDataModel.getBasicModel().getReferences(wall, "objectplacement") == null)
				collector.addError(new Throwable("Every Ifcbuildingelement entity must have an object placement assigned!"));
			
			System.out.println("Object placement: exists");
			
			System.out.println();
		}
		
		//----------Transparent components
		//Ifcdoor and Ifcwindow (IfcCurtainWall is not necessary because it's only an aggregate of building elements already examined)
		
		for (EEntity tr : transparents){
			
			if (tr.getClass().equals(CIfcwindow.class) || tr.getClass().equals(CIfcdoor.class))
				doorsAndWind.add(tr);
			
		}
		
		if (transparents.size() == 0)
			collector.addError(new Throwable("No transparent components found!"));
		
		for (EEntity ent : doorsAndWind){
			
			System.out.println("----Ifcdoor/window entity:\nGlobal ID: " + ifcDataModel.getGlobalId((EIfcroot) ent));
			
			relEx = 0;
			valEx = 0;
			doorWindCommEx = 0;
			doorWindGlazEx = 0;
			doorWindShadEx = 0;
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(ent, "isdefinedby");
			
			if (isDefinedBy.length < 4)
				collector.addError(new Throwable("Not enough relDefinesByProperty relations found!"));//Pset_Door/WindowCommon, Pset_DoorWindowGlazingType,
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
					if (valEx != 3)
						collector.addError(new Throwable("Every transparent entity must have a contruction type, isExternal and GlazingAreaFraction attribute!"));
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
					if (valEx != 5)
						collector.addError(new Throwable("Every transparent entity must have a Pset_DoorWindowGlazingType with certain properties!"));
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
					
					if (valEx != 1)
						collector.addError(new Throwable("Every transparent entity must have a ExternalShadingCoefficient attribute!"));
				}	
			
			}
			
			//User defined property sets (IfcPropertysetTemplates) -> prove of number of properties (PROPORTION OF GLASS ON THE WHOLE AREA;
			//TYPE OF GLASS; THICKNESS OF EVERY SPACING BETWEEN PANES; POSITION AND NUMBER OF THERMAL AND OPTICAL COATINGS; SOLAR HEAT
			//GAIN TRANSMITTANCE; THERMAL TRANSMITTANCE RESISTANCE OF THE GLASS AND WINDOW FRAME; AIR TRANSMISSIVITY OF THE JOINT IN THE WINDOW FRAME)
			
			//Checking for existence of any Ifcpropertysettemplates
			if ( allTemplates.size() == 1){
				System.out.println("Ifcpropertysettemplates: exist");
				if (ifcDataModel.getBasicModel().getReferences(allTemplates.toArray(new EEntity[allTemplates.size()])[0], "haspropertytemplates").length < 7)
					collector.addError(new Throwable("Not enough property template attributes found!"));
			}
			else{
				collector.addError(new Throwable("Pset_GlassProperties is missing!"));
			}
		
			//LOCATION OF SHADING
			if (ifcDataModel.getBasicModel().getValueOfAttribute(ent, "objectplacement", false) == null)
				collector.addError(new Throwable("Every transparent element has to have an object placement attribute!"));
			
			System.out.println("Object placement: exists\n");
			
			//Pset_Door/WindowCommon
			if (doorWindCommEx == 0)
				collector.addError(new Throwable("Pset_Door/WindowCommon is missing!"));
			
			//Pset_DoorWindowGlazingType
			if (doorWindGlazEx == 0)
				collector.addError(new Throwable("Pset_DoorWindowGlazingType is missing!"));
			
			//Pset_DoorWindowShadingType
			if (doorWindShadEx == 0)
				collector.addError(new Throwable("Pset_DoorWindowShadingType is missing!"));
			
		}
	
	}
	
	@Ignore
	@Test
	public void Arch21() throws SdaiException{
		/*----------------------------------------------------Exchange Requirements 2.1 ARCH----------------------------------------------------*/

		System.out.println("\nExchange Requirements 2.1 ARCH\n");
		
		//----Project Information
		//-------Project Context
		//----------Project Attributes
		
		ifcProjectEntity = ifcDataModel.getBasicModel().getEntitiesOf(EIfcproject.class);
		
		System.out.println("----Ifcproject entity:");
		System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcProjectEntity[0]));
		
		//PHASE
		if (ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], "phase", false) == null)
			collector.addError(new Throwable("There must be a phase attribute!"));
		System.out.println("Phase: " + ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], "phase", false));
		
		//----------Project Units (prints out enum integer values only)
		
		//Are there any assignments at all?
		if (unitAssignm.length == 0)
			collector.addError(new Throwable("There are no unit assignments at all!"));
		
		for (EEntity ua : unitAssignm){
			
			EEntity[] units = ifcDataModel.getBasicModel().getReferences(ua, "units");
			
			for (EEntity u : units){
				
				if (u.getClass().equals(CIfcsiunit.class)){
		
					if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.LENGTHUNIT)){
							System.out.println("Length Unit: METRE");
							lengthEx = 1;
					}
					
					if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.AREAUNIT)){
						System.out.println("Area Unit: SQUARE METRE");
						areaEx = 1;
					}
					
					if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.VOLUMEUNIT)){
						System.out.println("Volume Unit: CUBIC METRE");
						volEx = 1;
					}
					
					if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.THERMODYNAMICTEMPERATUREUNIT)){
						System.out.println("Thermodynamic temperature Unit: KELVIN");
						tmpEx = 1;
					}
					
					//OTHERS
					else
					{
						System.out.println("Other Unit: " + ifcDataModel.getBasicModel().getValueOfAttribute(u, "name", false));
					}
				}
				
				int cmplxSi = 0;
				
				//Check if the derived unit only consists of SI unit elements
				
				if (u.getClass().equals(CIfcderivedunit.class)){
					
					for (EEntity elem : ifcDataModel.getBasicModel().getReferences(u, "elements")){
						
						if (ifcDataModel.getBasicModel().getReferences(elem, "unit")[0].getClass().equals(CIfcsiunit.class)) //only SI units allowed
							cmplxSi++;
						
					}
					
					if (cmplxSi == ifcDataModel.getBasicModel().getReferences(u, "elements").length)
					{
						 
						if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcderivedunitenum.VOLUMETRICFLOWRATEUNIT)){
							System.out.println("Volumetric flow rate unit: exists");
							flowEx = 1;
						}
					
						//OTHERS
						else
						{
							System.out.println("Other Unit: " + ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false));
						}
						
					}
					
				}
			}
			
			//LENGTH UNIT
			if (lengthEx == 0)
				collector.addError(new Throwable("No length unit found!"));
			//VOLUME UNIT
			if (volEx == 0)
				collector.addError(new Throwable("No volume unit found!"));
			//AREA UNIT
			if (areaEx == 0)
				collector.addError(new Throwable("No area unit found!"));
			//TEMPERATURE UNIT
			if (tmpEx == 0)
				collector.addError(new Throwable("No temperature unit found!"));
			//VOLUMETRIC FLOW UNIT
			if (flowEx == 0)
				collector.addError(new Throwable("No volumetric flow unit found!"));
		
		}
		
		//----Design Requirements
		//-------Building
		//----------Energy
		
		for (EEntity building : ifcBuildings){
			
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(building, "isdefinedby");
			
			for (EEntity rel : isDefinedBy){
				
				propSet = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute(propSet[0], "name", false) == "Pset_BuildingDesignRequirements"){
					
					System.out.println("Pset_BuildingDesignRequirements:");
					
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences(propSet[0])){
						
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "HeatDemand"){
							System.out.println("> HeatDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							heatDemandEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "CoolingDemand"){
							System.out.println("> CoolingDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							coolDemandEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "ElectricityDemand"){
							System.out.println("> ElectricityDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							electrDemandEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "LevelOfRenewableEnergy"){
							System.out.println("> LevelOfRenewableEnergy: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							renEnEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "WaterDemand"){
							System.out.println("> WaterDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							waterDemandEx = 1;
						}
					}
					
				}
				
				//MAXIMAL HEATING DEMAND PER YEAR
				if (heatDemandEx == 0)
					collector.addError(new Throwable("No HeatDemand property found!"));
				//MAXIMAL COOLING DEMAND PER YEAR
				if (coolDemandEx == 0)
					collector.addError(new Throwable("No CoolingDemand property found!"));
				//MAXIMAL ELECTRICITY DEMAND PER YEAR
				if (electrDemandEx == 0)
					collector.addError(new Throwable("No ElectricityDemand property found!"));
				//TARGET LEVEL OF RENEWABLE ENERGY
				if (renEnEx == 0)
					collector.addError(new Throwable("No LevelOfRenewableEnergy property found!"));
				//WATER DEMAND PER YEAR
				if (waterDemandEx == 0)
					collector.addError(new Throwable("No WaterDemand property found!"));
				
			}
		}
		
		System.out.println();
			
		//----------Komfort
				
		for (EEntity space : spaces){
					
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(space, "isdefinedby");
			
			tmpTarg = 0;
			humTarg = 0;
			coTarg = 0;
			
			System.out.println("\nPset_SpaceDesignRequirements: (Ifcspace Global ID: " + ifcDataModel.getGlobalId((EIfcroot) space) + ")");
					
			for (EEntity rel : isDefinedBy){
				
				propSet = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
						
				if (ifcDataModel.getBasicModel().getValueOfAttribute(propSet[0], "name", false) == "Pset_SpaceDesignRequirements"){
							
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences(propSet[0])){
							
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "TemperatureTarget"){
							System.out.println("> TemperatureTarget: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							tmpTarg = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "HumidityTarget"){
							System.out.println("> HumidityTarget: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							humTarg = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "CO2Target"){
							System.out.println("> CO2Target: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							coTarg = 1;
						}
					}
							
				}
						
				//TEMPERATURE TARGET
				if (tmpTarg == 0)
					collector.addError(new Throwable("No TemperatureTarget property found!"));
				//HUMIDTY TARGET
				if (humTarg == 0)
					collector.addError(new Throwable("No HumidityTarget property found!"));
				//CO2 TARGET
				if (coTarg == 0)
					collector.addError(new Throwable("No CO2Target property found!"));
						
			}
		}
		
		//-------HVAC
		//----------Efficiency
		
	//	distFlowElements = ifcDataModel.getBasicModel().getEntitiesOf(EIfcdistributionflowelement.class);
	//	
	//	for (EEntity elem : distFlowElements){
	//		
	//		copTarg = 0;
	//		
	//		isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(elem, "isdefinedby");
	//		
	//		System.out.println("\nPset_HVACDesignRequirements: (Ifcdistributionflowelement Global ID: " + ifcDataModel.getGlobalId((EIfcroot) elem) + ")");
	//				
	//		for (EEntity rel : isDefinedBy){
	//			
	//			propSet = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
	//					
	//			if (ifcDataModel.getBasicModel().getValueOfAttribute(propSet[0], "name", false) == "Pset_HVACDesignRequirements"){
	//						
	//				for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences(propSet[0])){
	//							
	//					//COP TARGET 
	//					if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "COPTarget"){
	//						System.out.println("> COPTarget: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
	//						copTarg = 1;
	//					}
	//				}
	//						
	//			}
	//					
	//			assertTrue("No COPTarget property found!", copTarg == 1);
	//					
	//		}
	//	}
		
		//----Site Information
		//-------Site
		//----------Site Quantities
	
		valEx = 0;
		
		perEx = 0;
		areaEx = 0;
			
		for (EEntity site : ifcSites){
				
			System.out.println("----Ifcsite entity:");
			System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) site));
				
			valEx = 0;
				
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(site, "isdefinedby");
				
			//Are there any relations at all?
			if (isDefinedBy.length == 0)
				collector.addError(new Throwable("There are not isDefinedBy relations at all!"));
			else
			{
				for (EEntity rel : isDefinedBy){
						
							
					elementQuant = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
							
					if (elementQuant[0].getClass().equals(CIfcelementquantity.class)){
							
						System.out.println("Qto_SiteBaseQuantities:");
							
						quantities = ifcDataModel.getBasicModel().getReferences(elementQuant[0], "quantities");
						
						for (EEntity quant : ifcDataModel.getBasicModel().getReferences(elementQuant[0], "quantities")){ //1-to-N relationship
								
							if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "Perimeter"){
								System.out.println("> Perimeter: exists");
								perEx = 1;
							}
								
							else if (ifcDataModel.getBasicModel().getValueOfAttribute(quant, "name", false) == "GrossArea"){
								System.out.println("> Gross Area: exists");
								areaEx = 1;
							}
						}
					}	
				}
					
				//GROSS PERIMETER
				if (perEx == 0)
					collector.addError(new Throwable("Every Ifcsite entity must have a perimeter attribute assigned!"));
				//GROSS AREA
				if (areaEx == 0)
					collector.addError(new Throwable("Every Ifcsite entity must have an area attribute assigned!"));
			}

			perEx = 0;
			areaEx = 0;
			
			
			System.out.println();
		}
		
		//----Space related information
		//-------Space furniture/user specific equipment
		
//		
//		for (EEntity product : products){
//			
//			geomEx = 0;
//			volEx = 0;
//			
//			EEntity[] representations = ifcDataModel.getBasicModel().getReferences(product, "representation");
//			if (representations.length == 0)
//					collector.addError(new Throwable("Every Ifcproduct entity must have a representation!"));
//			
//			
//			if (ifcDataModel.getBasicModel().getValueOfAttribute(representations[0], "name", false) != null){
//				//GEOMETRY OF FURNITURE
//				if (ifcDataModel.getBasicModel().getValueOfAttribute(representations[0], "name", false).toString().toLowerCase().contains("geometry")){
//					geomEx = 1;
//				}
//				
//				//VOLUME OF FURNITURE
//				if (ifcDataModel.getBasicModel().getValueOfAttribute(representations[0], "name", false).toString().toLowerCase().contains("volume")){
//					volEx = 1;
//				}
//			}
//					
//		}
//			
//			if (geomEx == 0)
//				collector.addError(new Throwable("Every Ifcproduct entity must have a geometric representation!"));
//			if (volEx == 0)
//	 			collector.addError(new Throwable("Every Ifcproduct entity must have a volume representation!"));
//		}		
		
		if (products.length == 0)
			collector.addError(new Throwable("No Ifcproduct entities found!"));
			
		for (EEntity product : products){
			//POSITION OF FURNITURE
			if (ifcDataModel.getBasicModel().getValueOfAttribute(product, "objectplacement", false) == null)
				collector.addError(new Throwable("Every Ifcproduct entity must have an object placement attribute!"));
			
			//GEOMETRY OF FURNITURE and VOLUME OF FURNITURE
			if (ifcDataModel.getBasicModel().getValueOfAttribute(product, "representation", false) == null || ifcDataModel.getBasicModel().getValueOfAttribute(product, "representation", false) == "")
				collector.addError(new Throwable("Every Ifcproduct entity must have a representation!"));
		}
		
		//HEAT CAPACITY OF MATERIAL
		//non provable
		
		//----Construction related information
		//-------Space construction
		//----------Shading
		
		//no Ifcshading entity type available
		
	}
	
	@Ignore
	@Test
	public void Arch31() throws SdaiException{
	
		/*----------------------------------------------------Exchange Requirements 3.1 ARCH----------------------------------------------------*/
	
		System.out.println("\nExchange Requirements 3.1 ARCH\n");
		
		//----Project Information
		//-------Project Context
		//----------Project Attributes
		
		ifcProjectEntity = ifcDataModel.getBasicModel().getEntitiesOf(EIfcproject.class);
		
		System.out.println("----Ifcproject entity:");
		System.out.println("Global ID: " + ifcDataModel.getGlobalId((EIfcroot) ifcProjectEntity[0]));
		
		//PHASE
		if (ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], "phase", false) == null)
			collector.addError(new Throwable("There must be a phase attribute!"));
		System.out.println("Phase: " + ifcDataModel.getBasicModel().getValueOfAttribute(ifcProjectEntity[0], "phase", false));
		
		//----------Project Units (prints out enum integer values only)
		
		//Are there any assignments at all?
		if (unitAssignm.length == 0)
			collector.addError(new Throwable("There are no unit assignments at all!"));
		
		for (EEntity ua : unitAssignm){
			
			EEntity[] units = ifcDataModel.getBasicModel().getReferences(ua, "units");
			
			for (EEntity u : units){
				
				if (u.getClass().equals(CIfcsiunit.class)){
		
					if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.LENGTHUNIT)){
							System.out.println("Length Unit: METRE");
							lengthEx = 1;
					}
					
					if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.AREAUNIT)){
						System.out.println("Area Unit: SQUARE METRE");
						areaEx = 1;
					}
					
					if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.VOLUMEUNIT)){
						System.out.println("Volume Unit: CUBIC METRE");
						volEx = 1;
					}
					
					if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcunitenum.THERMODYNAMICTEMPERATUREUNIT)){
						System.out.println("Thermodynamic temperature Unit: KELVIN");
						tmpEx = 1;
					}
					
					//OTHERS
					else
					{
						System.out.println("Other Unit: " + ifcDataModel.getBasicModel().getValueOfAttribute(u, "name", false));
					}
				}
				
				int cmplxSi = 0;
				
				//Check if the derived unit only consists of SI unit elements
				
				if (u.getClass().equals(CIfcderivedunit.class)){
					
					for (EEntity elem : ifcDataModel.getBasicModel().getReferences(u, "elements")){
						
						if (ifcDataModel.getBasicModel().getReferences(elem, "unit")[0].getClass().equals(CIfcsiunit.class)) //only SI units allowed
							cmplxSi++;
						
					}
					
					if (cmplxSi == ifcDataModel.getBasicModel().getReferences(u, "elements").length)
					{
						 
						if (ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false).equals(EIfcderivedunitenum.VOLUMETRICFLOWRATEUNIT)){
							System.out.println("Volumetric flow rate unit: exists");
							flowEx = 1;
						}
					
						//OTHERS
						else
						{
							System.out.println("Other Unit: " + ifcDataModel.getBasicModel().getValueOfAttribute(u, "unittype", false));
						}
						
					}
					
				}
			}
			
			//LENGTH UNIT
			if (lengthEx == 0)
				collector.addError(new Throwable("No length unit found!"));
			//VOLUME UNIT
			if (volEx == 0)
				collector.addError(new Throwable("No volume unit found!"));
			//AREA UNIT
			if (areaEx == 0)
				collector.addError(new Throwable("No area unit found!"));
			//TEMPERATURE UNIT
			if (tmpEx == 0)
				collector.addError(new Throwable("No temperature unit found!"));
			//VOLUMETRIC FLOW UNIT
			if (flowEx == 0)
				collector.addError(new Throwable("No volumetric flow unit found!"));
		
		}
		
		//----Design Requirements
		//-------Building
		//----------Energy
		
		for (EEntity building : ifcBuildings){
			
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(building, "isdefinedby");
			
			for (EEntity rel : isDefinedBy){
				
				propSet = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
				
				if (ifcDataModel.getBasicModel().getValueOfAttribute(propSet[0], "name", false) == "Pset_BuildingDesignRequirements"){
					
					System.out.println("Pset_BuildingDesignRequirements:");
					
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences(propSet[0])){
						
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "HeatDemand"){
							System.out.println("> HeatDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							heatDemandEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "CoolingDemand"){
							System.out.println("> CoolingDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							coolDemandEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "ElectricityDemand"){
							System.out.println("> ElectricityDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							electrDemandEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "LevelOfRenewableEnergy"){
							System.out.println("> LevelOfRenewableEnergy: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							renEnEx = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "WaterDemand"){
							System.out.println("> WaterDemand: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							waterDemandEx = 1;
						}
					}
					
				}
				
				//MAXIMAL HEATING DEMAND PER YEAR
				if (heatDemandEx == 0)
					collector.addError(new Throwable("No HeatDemand property found!"));
				//MAXIMAL COOLING DEMAND PER YEAR
				if (coolDemandEx == 0)
					collector.addError(new Throwable("No CoolingDemand property found!"));
				//MAXIMAL ELECTRICITY DEMAND PER YEAR
				if (electrDemandEx == 0)
					collector.addError(new Throwable("No ElectricityDemand property found!"));
				//TARGET LEVEL OF RENEWABLE ENERGY
				if (renEnEx == 0)
					collector.addError(new Throwable("No LevelOfRenewableEnergy property found!"));
				//WATER DEMAND PER YEAR
				if (waterDemandEx == 0)
					collector.addError(new Throwable("No WaterDemand property found!"));
				
			}
		}
		
		System.out.println();
			
		//----------Komfort
				
		for (EEntity space : spaces){
					
			isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(space, "isdefinedby");
			
			tmpTarg = 0;
			humTarg = 0;
			coTarg = 0;
			
			System.out.println("\nPset_SpaceDesignRequirements: (Ifcspace Global ID: " + ifcDataModel.getGlobalId((EIfcroot) space) + ")");
					
			for (EEntity rel : isDefinedBy){
				
				propSet = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
						
				if (ifcDataModel.getBasicModel().getValueOfAttribute(propSet[0], "name", false) == "Pset_SpaceDesignRequirements"){
							
					for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences(propSet[0])){
							
						if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "TemperatureTarget"){
							System.out.println("> TemperatureTarget: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							tmpTarg = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "HumidityTarget"){
							System.out.println("> HumidityTarget: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							humTarg = 1;
						}
						else if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "CO2Target"){
							System.out.println("> CO2Target: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
							coTarg = 1;
						}
					}
							
				}
						
				//TEMPERATURE TARGET
				if (tmpTarg == 0)
					collector.addError(new Throwable("No TemperatureTarget property found!"));
				//HUMIDTY TARGET
				if (humTarg == 0)
					collector.addError(new Throwable("No HumidityTarget property found!"));
				//CO2 TARGET
				if (coTarg == 0)
					collector.addError(new Throwable("No CO2Target property found!"));
						
			}
		}
		
		//-------HVAC
		//----------Efficiency
		
	//	distFlowElements = ifcDataModel.getBasicModel().getEntitiesOf(EIfcdistributionflowelement.class);
	//	
	//	for (EEntity elem : distFlowElements){
	//		
	//		copTarg = 0;
	//		
	//		isDefinedBy = ifcDataModel.getBasicModel().getInverseReferences(elem, "isdefinedby");
	//		
	//		System.out.println("\nPset_HVACDesignRequirements: (Ifcdistributionflowelement Global ID: " + ifcDataModel.getGlobalId((EIfcroot) elem) + ")");
	//				
	//		for (EEntity rel : isDefinedBy){
	//			
	//			propSet = ifcDataModel.getBasicModel().getReferences(rel, "relatingpropertydefinition");
	//					
	//			if (ifcDataModel.getBasicModel().getValueOfAttribute(propSet[0], "name", false) == "Pset_HVACDesignRequirements"){
	//						
	//				for (EEntity prop : ifcDataModel.getBasicModel().getAllReferences(propSet[0])){
	//							
	//					//COP TARGET 
	//					if (ifcDataModel.getBasicModel().getValueOfAttribute(prop, "name", false) == "COPTarget"){
	//						System.out.println("> COPTarget: " + ifcDataModel.getBasicModel().getValueOfAttribute(prop, "nominalvalue", false));
	//						copTarg = 1;
	//					}
	//				}
	//						
	//			}
	//					
	//			assertTrue("No COPTarget property found!", copTarg == 1);
	//					
	//		}
	//	}
		
		
		//----Space related information
		//-------Space furniture/user specific equipment
		
//		
//		for (EEntity product : products){
//			
//			geomEx = 0;
//			volEx = 0;
//			
//			EEntity[] representations = ifcDataModel.getBasicModel().getReferences(product, "representation");
//			if (representations.length == 0)
//					collector.addError(new Throwable("Every Ifcproduct entity must have a representation!"));
//			
//			
//			if (ifcDataModel.getBasicModel().getValueOfAttribute(representations[0], "name", false) != null){
//				//GEOMETRY OF FURNITURE
//				if (ifcDataModel.getBasicModel().getValueOfAttribute(representations[0], "name", false).toString().toLowerCase().contains("geometry")){
//					geomEx = 1;
//				}
//				
//				//VOLUME OF FURNITURE
//				if (ifcDataModel.getBasicModel().getValueOfAttribute(representations[0], "name", false).toString().toLowerCase().contains("volume")){
//					volEx = 1;
//				}
//			}
//					
//		}
//			
//			if (geomEx == 0)
//				collector.addError(new Throwable("Every Ifcproduct entity must have a geometric representation!"));
//			if (volEx == 0)
//	 			collector.addError(new Throwable("Every Ifcproduct entity must have a volume representation!"));
//		}		
		
		if (products.length == 0)
			collector.addError(new Throwable("No Ifcproduct entities found!"));
			
		for (EEntity product : products){
			//POSITION OF FURNITURE
			if (ifcDataModel.getBasicModel().getValueOfAttribute(product, "objectplacement", false) == null)
				collector.addError(new Throwable("Every Ifcproduct entity must have an object placement attribute!"));
			
			//GEOMETRY OF FURNITURE and VOLUME OF FURNITURE
			if (ifcDataModel.getBasicModel().getValueOfAttribute(product, "representation", false) == null || ifcDataModel.getBasicModel().getValueOfAttribute(product, "representation", false) == "")
				collector.addError(new Throwable("Every Ifcproduct entity must have a representation!"));
		}
		
		//HEAT CAPACITY OF MATERIAL
		//non provable
		
		
		//----Construction related information
		//-------Space construction
		//----------Shading
		
		//no Ifcshading entity type available
	}
}
