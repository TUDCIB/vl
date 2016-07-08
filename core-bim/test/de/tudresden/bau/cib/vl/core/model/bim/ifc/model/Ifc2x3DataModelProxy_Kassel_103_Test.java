package de.tudresden.bau.cib.vl.core.model.bim.ifc.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.AIfcproperty;
import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingelement;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfccolumn;
import jsdai.SIfc2x3.EIfccomplexproperty;
import jsdai.SIfc2x3.EIfcdescriptivemeasure;
import jsdai.SIfc2x3.EIfcdoor;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcinternalorexternalenum;
import jsdai.SIfc2x3.EIfcmaterial;
import jsdai.SIfc2x3.EIfcmateriallayer;
import jsdai.SIfc2x3.EIfcopeningelement;
import jsdai.SIfc2x3.EIfcphysicalorvirtualenum;
import jsdai.SIfc2x3.EIfcproperty;
import jsdai.SIfc2x3.EIfcpropertysinglevalue;
import jsdai.SIfc2x3.EIfcrelfillselement;
import jsdai.SIfc2x3.EIfcrelspaceboundary;
import jsdai.SIfc2x3.EIfcslab;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcwall;
import jsdai.SIfc2x3.EIfcwallstandardcase;
import jsdai.SIfc2x3.EIfcwindow;
import jsdai.lang.SdaiException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.model.utility.IterableAggregate;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.parser.Ifc2x3Parser;



/**
 * This test is currently for the big Kassel model of HESMOS.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class Ifc2x3DataModelProxy_Kassel_103_Test {
	
	private static File fileToParse = new File("resources/ifc/103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB.ifc");
	private static File workingDirectory = new File("D:\\tmp_repo");
	private static Ifc2x3Parser parser;
	private static Ifc2x3DataModelProxy ifcDataModel;
	private static final String AREA_PATTERN = "#spaceboundary_area=";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if(!workingDirectory.exists()) workingDirectory.mkdir();
		assertTrue(workingDirectory.exists());
		assertTrue(fileToParse.exists());
		parser = (Ifc2x3Parser) Ifc2x3Parser.createParser(workingDirectory.getAbsolutePath());
		ifcDataModel = parser.parse(fileToParse.getAbsolutePath());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		parser.stop();
		File[] subFiles = workingDirectory.listFiles();
		for(File f: subFiles) {
			f.delete();
		
		}
		workingDirectory.delete();
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetBuildingIDs() throws Exception {
		List<String> buildingIDs = ifcDataModel.getBuildingsIDs();
		assertTrue(buildingIDs.size() == 1);
		assertEquals(buildingIDs.get(0),"1pfeQqqxfBtvRzniADCXt$");
	}
	
	@Test
	public void testGetAdjacentSpaces() throws Exception {
		EIfcspace[] spaces = ifcDataModel.getSpacesOfBuilding("1pfeQqqxfBtvRzniADCXt$");
		assertNotNull(spaces);
		Set<Class<? extends EIfcelement>> notAllowedTypes = new HashSet<Class<? extends EIfcelement>>();
		notAllowedTypes.add(EIfcslab.class);
		for(EIfcspace space : spaces) {
			if(space.getLongname(space).equalsIgnoreCase("kein raum"))continue;
			String spaceGuid = space.getGlobalid(space);
			Set<EIfcspace> adjacentSpaces = ifcDataModel.getAdjacentSpaces(spaceGuid, "kein Raum", notAllowedTypes);
			Set<String> spaceGuids = new HashSet<String>();
			for(EIfcspace adjacentSpace : adjacentSpaces) {
				boolean added = spaceGuids.add(adjacentSpace.getGlobalid(adjacentSpace));
				assertTrue(added);
			}
			System.out.println("Number of adjacent spaces of: "+spaceGuid+" are: "+adjacentSpaces.size()+" => "+spaceGuids);
		}
	}
	
	@Test
	public void testGetSpacesOfBuilding() throws Exception {
		EIfcspace[] spaces = ifcDataModel.getSpacesOfBuilding("1pfeQqqxfBtvRzniADCXt$");
		assertNotNull(spaces);
	}

	@Test
	public void testGetBoundingElementsOfSpace() throws Exception {
		EIfcspace space = ifcDataModel.getSpace("1ueFMxRuX8VOi3xnkXut4D");
		assertNotNull(space);
		Set<EIfcelement> boundingElements = ifcDataModel.getBoundingElementsOfSpace(space);
		assertNotNull(boundingElements);
		assertEquals(boundingElements.size(), 4);
	}
	
	@Test
	public void testOuterRoomsOfBuilding() throws Exception {
		Map<String, EIfcbuilding> buildings = ifcDataModel.getBuildings();
		assertTrue(buildings.size() > 0);
		for(Map.Entry<String, EIfcbuilding> entry : buildings.entrySet()) {
			String buildingGuid = entry.getKey();
			Set<String> roomIds = ifcDataModel.getOuterRoomsOfBuilding(buildingGuid);
			assertTrue(roomIds.size() > 0);
			for(String id : roomIds) {
				assertNotNull(id);
				System.out.println("Room = "+id);
			}
			
		}	
	}
	
	@Test
	public void testOuterRoomsOfStorey() throws Exception {
		Map<String, EIfcbuilding> buildings = ifcDataModel.getBuildings();
		assertTrue(buildings.size() > 0);
		
		for(Map.Entry<String, EIfcbuilding> entry : buildings.entrySet()) {
			String buildingGuid = entry.getKey();
			
			EIfcbuildingstorey[] storeys = ifcDataModel.getBuildingStoreys(entry.getValue());
			assertTrue(storeys.length > 0);
			for(EIfcbuildingstorey storey : storeys) {
				System.out.println("Storey '"+storey.getName(storey)+"'");
				Set<EIfcspace> outerSpaces = ifcDataModel.getOuterRoomsOfStorey(storey.getGlobalid(storey));
				for(EIfcspace outerSpace : outerSpaces) {
					assertNotNull(outerSpace);
					System.out.println(outerSpace);
				}
			}
			
		}	
	}
	
	@Test
	public void testGetCellarSeparatingSlabs() throws Exception {
		String buildingGuid = "1pfeQqqxfBtvRzniADCXt$";
		List<EIfcslab> cellarSeparatingSlabs = ifcDataModel.getCellarSeparatingSlabs(buildingGuid);
		System.out.println(cellarSeparatingSlabs);
	}
	
	@Test
	public void testGetRoofSeparatingSlabs() throws Exception {
		String buildingGuid = "1pfeQqqxfBtvRzniADCXt$";
		List<EIfcslab> roofSeparatingSlabs = ifcDataModel.getRoofSeparatingSlabs(buildingGuid);
		System.out.println(roofSeparatingSlabs);
	}
	
	@Test
	public void testOuterRoomsAsIfcSpace() throws Exception {
		Map<String, EIfcbuilding> buildings = ifcDataModel.getBuildings();
		assertTrue(buildings.size() > 0);
		for(Map.Entry<String, EIfcbuilding> entry : buildings.entrySet()) {
			String buildingGuid = entry.getKey();
			Set<EIfcspace> roomIds = ifcDataModel.getOuterRoomsAsIfcSpace(buildingGuid);
			assertTrue(roomIds.size() > 0);
			for(EIfcspace space : roomIds) {
				assertNotNull(space);
				System.out.println("Room = "+space);
			}			
		}	
	}
	
	@Test
	public void testGetInnerWallsOfBuildingString() throws Exception {
		Map<String, EIfcbuilding> buildings = ifcDataModel.getBuildings();
		assertTrue(buildings.size() > 0);
		for(Map.Entry<String, EIfcbuilding> entry : buildings.entrySet()) {
			String buildingGuid = entry.getKey();
			Set<EIfcwall> innerWalls = ifcDataModel.getInnerWallsOfBuilding(buildingGuid);
			assertTrue(innerWalls.size() > 0);
			for(EIfcwall wall : innerWalls) {
				assertNotNull(wall);
				System.out.println("Wall = "+wall);
			}			
		}
	}
	
	@Test
	public void testGetConstructionTypeAndTheirAreas() throws Exception {
		String locationIds = "resources/103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB_test_locationIds.txt";
		List<EIfcspace> rooms = getRoomsFromLocationFile(locationIds);
//		List<EIfcspace> rooms = Arrays.asList(ifcDataModel.getSpaces("1pfeQqqxfBtvRzniADCXt$"));
//		retrieve wall areas
		Map<String, Double> buildingElements = calculateBuildingShellArea(rooms);
		for (Map.Entry<String, Double> entry : buildingElements.entrySet()) {
			String key = entry.getKey();
			Double value = entry.getValue();
			value = (double)Math.round(value * 100)/100;
			System.out.println("Building element: "+key+" has area of: "+value);
			
		}
//		retrieve window areas
		Map<String, Double> windows = calculateWindowArea(rooms);
		for (Map.Entry<String, Double> entry : windows.entrySet()) {
			String key = entry.getKey();
			Double value = entry.getValue();
			value = (double)Math.round(value * 100)/100;
			System.out.println("Window: "+key+" has area of: "+value);
			
		}
	}
	
	@Test
	public void testGetElementArea() throws Exception {
		String locationIds = "resources/103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB_test_locationIds.txt";
		List<EIfcspace> rooms = getRoomsFromLocationFile(locationIds);
//		List<EIfcspace> rooms = Arrays.asList(ifcDataModel.getSpaces("1pfeQqqxfBtvRzniADCXt$"));
		double overallArea = 0.0;
		for(EIfcspace space : rooms) {
			Set<EIfcelement> elements = ifcDataModel.getBoundingElementsOfSpace(space);
			for(EIfcelement element : elements) {
				if(element instanceof EIfcwindow) {
					double area = ifcDataModel.getElementArea(element);
					overallArea += area;
				}
			}
		}
		System.out.println("Overall area: "+overallArea);
	}
	
	@Test
	public void testIsSeparatingSpaces() throws Exception {
//		String locationIds = "resources/103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB_test_locationIds.txt";
//		List<EIfcspace> rooms = getRoomsFromLocationFile(locationIds);
		// all rooms in the building
//		List<EIfcspace> rooms = Arrays.asList(ifcDataModel.getSpaces("1pfeQqqxfBtvRzniADCXt$"));
		List<EIfcspace> rooms = Arrays.asList(ifcDataModel.getIfcEntity("3gqg7lwnnAKxFSLjOh8J0w", EIfcspace.class));
		int i = 0;
		for(EIfcspace space : rooms) {
			Set<EIfcelement> elements = ifcDataModel.getBoundingElementsOfSpace(space);
			for(EIfcelement element : elements) {
				boolean isSeparating = ifcDataModel.isSeparatingSpaces(element);
				if(element.getGlobalid(element).equals("0FQuovDHL01gF$znVgsMnE")) assertTrue(isSeparating); else assertTrue(!isSeparating);
				if(element.getGlobalid(element).equals("2J9w1OV9L53hGPAF58T0c6")) assertTrue(isSeparating); else assertTrue(!isSeparating);
				if(element.getGlobalid(element).equals("3HIvHrst997fS1_8WlumVF")) assertTrue(isSeparating); else assertTrue(!isSeparating);
				if(element.getGlobalid(element).equals("16CT6ErUv7fRgY_jisrFEg")) assertTrue(isSeparating); else assertTrue(!isSeparating);
				if(element.getGlobalid(element).equals("3ipXELrjTCjecZeu2qUnwp")) assertTrue(!isSeparating); else assertTrue(isSeparating);
				if(element.getGlobalid(element).equals("0lW01WV1D6av9s2_UQ3_3I")) assertTrue(!isSeparating); else assertTrue(isSeparating);
				if(element.getGlobalid(element).equals("02l9ZFR5bEMwfX66gAW36c")) assertTrue(!isSeparating); else assertTrue(isSeparating);
			}
		}
	}
	
	@Test
	public void testGetConstructions() throws Exception {		
		//	Link building element => constructions			
		Set<EIfcbuildingelement> overallBuildingElements = new HashSet<EIfcbuildingelement>();

		EIfcbuildingelement[] elements = ifcDataModel.getIfcEntitiesOf(EIfcbuildingelement.class);
		for(EIfcelement be : elements) {						
			//	if(!(be instanceof EIfcbuildingelement)) continue;
			//	if(be instanceof EIfcdoor) continue;
			overallBuildingElements.add((EIfcbuildingelement) be);
		}
		
		Set<String> notAssignedTemplateCache = new HashSet<String>();

		for(EIfcbuildingelement be : overallBuildingElements) {					
			Set<EIfcmaterial> materials = ifcDataModel.getAssociatedMaterial(be);
			String templateName = getConstructionTemplateNameFromIfc(materials, be);
			if(templateName != null) {
				String mappedName = ConstructionTemplateMappingEnum.getIbkTemplate(templateName);
				if(mappedName == null)
					notAssignedTemplateCache.add(templateName);
			}
		}
		
		for(String s : notAssignedTemplateCache) {
			System.out.println("templateName: "+s);
		}
	}
	
	private List<EIfcspace> getRoomsFromLocationFile(String filePath) throws IOException, IfcException {
		List<EIfcspace> rooms = new ArrayList<EIfcspace>();
		String thisLine = null;
		FileReader reader = new FileReader(new File(filePath));
		BufferedReader br = new BufferedReader(reader);
		while ((thisLine = br.readLine()) != null) { // while loop begins here
			String guid = thisLine;
			EIfcspace space = ifcDataModel.getIfcEntity(guid, EIfcspace.class);
			if(space != null) rooms.add(space);
		} // end while 
		return rooms;
	}
	
	private Map<String, Double> calculateBuildingShellArea(List<EIfcspace> spaces) throws IfcException {
		double buildingShellArea = 0.0;
		Map<String, Double> elementsAndTheirAreas = new HashMap<String, Double>();
		Map<String, Set<String>> tplToAssignedBuildingElementsGuids = new HashMap<String, Set<String>>();
		// Set with GlobalIds to prohibit that one GlobalId is used multiple times
		Set<String> cachedGuids = new HashSet<String>();
		try{
			for(EIfcspace space : spaces) {						
				EIfcrelspaceboundary[] spaceBoundaries = ifcDataModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
					if(cachedGuids.add(spaceBoundary.getGlobalid(spaceBoundary))) {
						//	non-energy-related costs for external elements
						int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
						if(internalOrExternal == EIfcinternalorexternalenum.EXTERNAL) { // facade element: window or other concrete element
							EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
							if(element instanceof EIfcbuildingelement) {
								EIfcbuildingelement be = (EIfcbuildingelement) element;
	
								if(!(be instanceof EIfcwindow)) {
									Set<EIfcmaterial> materials = ifcDataModel.getAssociatedMaterial(be);
									double area	= 0.0;
									if(spaceBoundary.testDescription(spaceBoundary)) {
										String description = spaceBoundary.getDescription(spaceBoundary);								
										if(description != null) {
											description = description.replace(description.substring(0, description.indexOf(AREA_PATTERN)),"");
											String areaText = description.replaceFirst(AREA_PATTERN, "");
											if(areaText != null) {
												area = Double.valueOf(areaText);
											}
										}
									}
									String templateName = getConstructionTemplateNameFromIfc(materials, be);
									if(elementsAndTheirAreas.containsKey(templateName)) {
										double previousArea = elementsAndTheirAreas.get(templateName);
										elementsAndTheirAreas.put(templateName, previousArea+area);
									} else {
										elementsAndTheirAreas.put(templateName, area);
									}
									if(tplToAssignedBuildingElementsGuids.containsKey(templateName)) {
										tplToAssignedBuildingElementsGuids.get(templateName).add(element.getGlobalid(element));
									} else {
										Set<String> assignedGuidList = new HashSet<String>();
										assignedGuidList.add(element.getGlobalid(element));
										tplToAssignedBuildingElementsGuids.put(templateName, assignedGuidList);
									}
									buildingShellArea += area;
								}
							}
						}
					}
				}
			}
//			round building shell area
			buildingShellArea = Math.round(buildingShellArea*100)/100;
			System.out.println("Overall building shell area: "+buildingShellArea);
			
			for (Map.Entry<String, Set<java.lang.String>> entry : tplToAssignedBuildingElementsGuids.entrySet()) {
				String tplName = entry.getKey();
				Set<String> assignedGuids = entry.getValue();
				System.out.println("Number of assigned elements to template: "+tplName + " = "+assignedGuids.size()+" --- "+assignedGuids);			
			}
		} catch(SdaiException se) {
			throw new IfcException(se);
		}
		return elementsAndTheirAreas;
	}
	
	private Map<String,Double> calculateWindowArea(List<EIfcspace> spaces) throws IfcException {
		double windowArea = 0.0;
		Map<String, Double> elementsAndTheirAreas = new HashMap<String, Double>();
		Map<String, Set<String>> tplToAssignedBuildingElementsGuids = new HashMap<String, Set<String>>();
		// Set with GlobalIds to prohibit that one GlobalId is used multiple times
		Set<String> cachedGuids = new HashSet<String>();
		try {
			for(EIfcspace space : spaces) {						
				EIfcrelspaceboundary[] spaceBoundaries = ifcDataModel.getSpaceBoundaries(space, EIfcphysicalorvirtualenum.PHYSICAL);
				for(EIfcrelspaceboundary spaceBoundary : spaceBoundaries) {
					if(cachedGuids.add(spaceBoundary.getGlobalid(spaceBoundary))) {
						//	non-energy-related costs for external elements
						int internalOrExternal = spaceBoundary.getInternalorexternalboundary(spaceBoundary);
						if(internalOrExternal == EIfcinternalorexternalenum.EXTERNAL) { // facade element: window or other concrete element
							EIfcelement element = spaceBoundary.getRelatedbuildingelement(spaceBoundary);
							if(element instanceof EIfcwindow) {
								double area	= 0.0;
								if(spaceBoundary.testDescription(spaceBoundary)) {
									String description = spaceBoundary.getDescription(spaceBoundary);								
									if(description != null) {
										description = description.replace(description.substring(0, description.indexOf(AREA_PATTERN)),"");
										String areaText = description.replaceFirst(AREA_PATTERN, "");
										if(areaText != null) {
											area = Double.valueOf(areaText);
										}
									}
								}
								windowArea += area;
								
								String templateName = getConstructionTemplateNameFromIfc(null, (EIfcbuildingelement)element);
								if(elementsAndTheirAreas.containsKey(templateName)) {
									double previousArea = elementsAndTheirAreas.get(templateName);
									elementsAndTheirAreas.put(templateName, previousArea+area);
								} else {
									elementsAndTheirAreas.put(templateName, area);
								}
								if(tplToAssignedBuildingElementsGuids.containsKey(templateName)) {
									tplToAssignedBuildingElementsGuids.get(templateName).add(element.getGlobalid(element));
								} else {
									Set<String> assignedGuidList = new HashSet<String>();
									assignedGuidList.add(element.getGlobalid(element));
									tplToAssignedBuildingElementsGuids.put(templateName, assignedGuidList);
								}
							}
						}
					}
				}
			}
			// round window area
			windowArea = Math.round(windowArea*100)/100;
			System.out.println("Overall window area: "+windowArea);
			
			for (Map.Entry<String, Set<java.lang.String>> entry : tplToAssignedBuildingElementsGuids.entrySet()) {
				String tplName = entry.getKey();
				Set<String> assignedGuids = entry.getValue();
				System.out.println("Number of assigned elements to template: "+tplName + " = "+assignedGuids.size()+" --- "+assignedGuids);			
			}
		} catch (SdaiException se) {
			throw new IfcException(se);
		}
		return elementsAndTheirAreas;
	}
	
	private String getConstructionTemplateNameFromIfc(Set<EIfcmaterial> materials, EIfcbuildingelement be) throws SdaiException, IfcException {
		String description = null;
//		it is a slab or a column
		if(be instanceof EIfcslab || be instanceof EIfccolumn) {			
			for(EIfcmaterial material : materials) {
				String materialName = material.getName(material);
				if(materialName.length() < 1) 
					continue;			
				else 
					return materialName;	
			}
		}
		
		// it is a window or door => search PropertySet
		if((be instanceof EIfcwindow) || (be instanceof EIfcdoor)) {
			description = getDescriptionFromProperty(be);
//			LOG.debug("Description: {} retrieved from window or door: {}",
//					new Object[]{description, be});
			return description;
		}
		
//		it is an IfcWallStandardCase or something like that
		if(be.testName(be)) {
			description = be.getName(be);
//			LOG.debug("Description: {} retrieved from building element: {}",
//					new Object[]{description, be});
		}
		return description;
	}
	
	private String getDescriptionFromProperty(EIfcelement element) throws IfcException {
		String description = null;
		if(element instanceof EIfcwindow || element instanceof EIfcdoor) {
			try {
//				retrieve IfcRelFillsElement
				List<EIfcrelfillselement> relFillsElements = ifcDataModel.getRelFillsElement(element);
				if(relFillsElements != null) {
					for(EIfcrelfillselement relFillsElement : relFillsElements) {
//						retrieve the IfcOpeningElement
						EIfcopeningelement openingElement = relFillsElement.getRelatingopeningelement(relFillsElement);
						if(openingElement != null) {
							EIfcproperty[] properties = ifcDataModel.getProperties(openingElement);
							if(properties != null) {
								for(EIfcproperty property : properties) {
									if(property instanceof EIfccomplexproperty) {
										EIfccomplexproperty complexProperty = (EIfccomplexproperty) property;
										AIfcproperty aProperty = complexProperty.getHasproperties(complexProperty);
										for(EIfcproperty prop : new IterableAggregate<EIfcproperty>(aProperty)) {
											if(prop instanceof EIfcpropertysinglevalue) {
												EIfcpropertysinglevalue singleValue = (EIfcpropertysinglevalue) prop;
												if(singleValue.testName(singleValue)) {
													if(singleValue.getName(singleValue).equalsIgnoreCase("bezeichnung")) {
														String value = singleValue.getNominalvalue(singleValue, (EIfcdescriptivemeasure) null);
														description = value;
													}
												}
			
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (SdaiException se) {
				throw new IfcException(se);
			}
		}
		return description;
	}
	
	private enum ConstructionTemplateMappingEnum {

		IW_concrete ("IW_concrete", "walltypeConcrete"),
		IW_GYPSUM_BOARD ("IW_gypsum board", "walltypGypsumBoard"),
		OW_steel_cover ("OW_steel cover", "walltypeSteelCover"),
		OW_steel ("OW_steel", "walltypeSteel"),
		OW_sunprotection_case ("OW_sunprotection case", "walltypeSunprotectioncase"),
		OW_reinforced_concrete ("OW_reinforced concrete", "walltypeReinforcedConcrete"),
		OW_TICS ("OW_TICS", "walltypeTICS"),
		
		W_narrow_corridor_window_and_door_gf ("W_narrow corridor_gf", "Narrow corridor window and door ground floor"),
		W_narrow_corridor_window_and_door_uf ("W_narrow corridor_uf", "Narrow corridor window and door upper floors"),
		W_narrow_window_ground_floor ("W_narrow window_gf", "Narrow window ground floor"),
		W_standard_window_upper_floors ("W_standard window_uf", "Standard windows upper floors"),
		W_standard_window_gf ("W_standard window_gf", "Window without glas panel ground floor"),
		W_standard_window_with_glass_paneel_ground_floor ("W_standard_paneel_gf", "Standard windows with glass panel ground floor"),
		W_window_without_glass_panel_ground_floor ("W_standard window_gf", "Window without glass panel ground floor"),
		W_mullion_and_transom_upper_floors ("W_mullion and transom_uf", "Mullion and transom upper floors"),
		W_escape_door_upper_floors ("W_escape door_uf", "Window Escape door upper floors"),
		W_escape_door_upper_floors_large ("W_escape door_uf_large", "Window Escape door upper floors_large"),
		W_broad_corridor_window_and_door_upper_floors  ("W_broad corridor_uf", "Broad corridor window and door upper floors"),
		W_broad_corridor_window_and_door_ground_floors  ("W_broad corridor_gf", "Broad corridor window and door ground floor"),
		
		BP_concrete ("BP_concrete", "Concrete_baseplate_42cm"),
		C_concrete ("C_concrete", "concrete ceiling"),
		F_Bonded_Screed_33_base ("F_Bonded Screed_3.3_base", "Bonded_screed_3_3_technology_laboratory_34cm"),
		F_Carpet_23 ("F_Carpet_2.3", "Carpet_2_3_conference_room_36cm"),
		F_Industrial_Screed_35 ("F_Industrial Screed_3.5", "Industrial_screed_3_5_chemical_bacteriological_laboratory_35cm"),
		F_Industrial_Screed_33_uf ("F_Industrial Screed_3.3_uf", ""),
		F_Industrial_Screed_41 ("F_Industrial Screed_4.1", "Industrial_screed_4_1_stockroom_110cm"),
		F_Industrial_Screed_42 ("F_Industrial Screed_4.2", "Industrial_screed_4_2_library_archive_rooms_46cm"),
		F_Industrial_Screed_73 ("F_Industrial Screed_7.3", "Industrial_screed_7_3_storage_room_36cm"),
		F_Linoleum_21 ("F_Linoleum_2.1", "Linoleum_2_1_office_room_35cm"),
		F_Linoleum_28 ("F_Linoleum_2.8", "Linoleum_2_8_room_for_office_supply_36cm"),
		F_Linoleum_29 ("F_Linoleum_2.9", "Linoleum_2_9_miscellaneous_office_36cm"),
		F_Linoleum_21_gf ("F_Linoleum_2.1_gf", "Linoleum_36cm_01"),
		F_Linoleum_54 ("F_Linoleum_5.4", "Linoleum_5_4_Library_room_36cm"),
		F_Linoleum_84 ("F_Linoleum_8.4", "Linoleum_8_4_electrical_power_supply_36cm"),
		F_Linoleum_91 ("F_Linoleum_9.1", "Linoleum_9_1_floor_hall_37cm"),
		F_Linoleum_76_b ("F_Linoleum_7.6_b", "Linoleum_on_doublefloor_36cm"),
		F_Linoleum_76 ("F_Linoleum_7.6", "Linoleum_on_doublefloor_7_6_room_for_technical_supply_101cm"),
		F_Linoleum_85 ("F_Linoleum_8.5", "Linoleum_on_doublefloor_8_5_telecommunication_37cm"),
		F_Natural_Rubber_56 ("F_Natural Rubber_5.6", "Natural_rubber_5_6_assembly_room_36cm"),
		F_Natural_Stone_27 ("F_Natural Stone_2.7", "Natural_stone_2_7_controllership_room_38cm"),
		F_Natural_Stone_92 ("F_Natural Stone_9.2", "Natural_stone_24cm"),
		F_Natural_Stone_99_base ("F_Natural Stone_9.9_base", "Natural_stone_28cm"),
		F_Natural_Stone_92_podest ("F_Natural Stone_9.2_podest", "Natural_stone_32cm"),
		F_Natural_Stone_86 ("F_Natural Stone_8.6", "Natural_stone_8_6_lift_and_conveying_machinery_24cm"),
//		F_Natural_Stone_92 ("F_Natural Stone_9.2", "Natural_stone_9_2_stairs_35cm"),
		F_Natural_Stone_99 ("F_Natural Stone_9.9", "Natural_stone_9_9_miscellaneous_public_thoroughfare_37cm"),
		F_Tiles_38 ("F_Tiles_3.8", "Tiles_3_8_kitchen_36cm"),
		F_Tiles_71_gf ("F_Tiles_7.1_gf", "Tiles_36cm_01"),
		F_Tiles_71_disabled ("F_Tiles_7.1_disabled", "Tiles_37cm"),
		F_Tiles_71 ("F_Tiles_7.1", "Tiles_7_1_sanitary_installations_35cm"),
		
		C_suspended_gypsum_ceiling ("C_suspended gypsum ceiling", "ceilingtyp20"),
		C_suspended_alu_ceiling ("C_suspended alu ceiling", "ceilingtyp30"),
		C_suspended_cooling_ceiling ("C_suspended cooling ceiling", "ceilingtyp40"),
		
		R_extensive_planting_roof ("R_extensive planting roof", "rooftype 1")
		;
		
		private String name;
		private String ibkTemplate;
		public static final String SEARCH_PATTERN = "/Test/";
		
		private ConstructionTemplateMappingEnum(String name, String ibkTemplate) {
			this.name = name;
			this.ibkTemplate = ibkTemplate;
		}

		public static String getIbkTemplate(String ifcName) {
			ifcName = ifcName.toLowerCase();
			ConstructionTemplateMappingEnum[] mappings = values();
			for(ConstructionTemplateMappingEnum mapping : mappings) {
				String mName = mapping.name.toLowerCase();
				if(ifcName.matches(".*"+mName+".*")){ // contains the name
					return mapping.ibkTemplate;
				}
			}			
			return null;
		}
	}

}
