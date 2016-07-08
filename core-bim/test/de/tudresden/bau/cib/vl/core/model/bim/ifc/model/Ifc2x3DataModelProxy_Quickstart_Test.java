package de.tudresden.bau.cib.vl.core.model.bim.ifc.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.AIfcobject;
import jsdai.SIfc2x3.AIfcproperty;
import jsdai.SIfc2x3.EIfcboolean;
import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcpropertyset;
import jsdai.SIfc2x3.EIfcpropertysinglevalue;
import jsdai.SIfc2x3.EIfcreal;
import jsdai.SIfc2x3.EIfcreldefinesbyproperties;
import jsdai.SIfc2x3.EIfcslab;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcstair;
import jsdai.SIfc2x3.EIfctext;
import jsdai.SIfc2x3.EIfcwall;
import jsdai.SIfc2x3.EIfcwallstandardcase;
import jsdai.SIfc2x3.SIfc2x3;
import jsdai.lang.EEntity;
import jsdai.lang.SdaiModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.parser.Ifc2x3Parser;



/**
 * This test is currently for the big Kassel model of HESMOS.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class Ifc2x3DataModelProxy_Quickstart_Test {
	
	private static File fileToParse = new File("resources/ifc/Quickstart_Project_2ndLSB.ifc");
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
		assertEquals(buildingIDs.get(0),"0Z3sXNXmjD8ReGD5X6wi6Z");
	}
	
	@Test
	public void testGetAdjacentSpaces() throws Exception {
		EIfcspace[] spaces = ifcDataModel.getSpacesOfBuilding("0Z3sXNXmjD8ReGD5X6wi6Z");
		assertNotNull(spaces);
		Set<Class<? extends EIfcelement>> notAllowedTypes = new HashSet<Class<? extends EIfcelement>>();
		notAllowedTypes.add(EIfcslab.class);
		for(EIfcspace space : spaces) {
			String spaceGuid = space.getGlobalid(space);
			Set<EIfcspace> adjacentSpaces = ifcDataModel.getAdjacentSpaces(spaceGuid, null, notAllowedTypes);
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
		EIfcspace[] spaces = ifcDataModel.getSpacesOfBuilding("0Z3sXNXmjD8ReGD5X6wi6Z");
		assertNotNull(spaces);
		assertEquals(spaces.length,34);
	}

	@Test
	public void testGetBoundingElementsOfSpace() throws Exception {
		EIfcspace space = ifcDataModel.getSpace("1ueFMxRuX8VOi3xnkXut4D");
		assertNotNull(space);
		Set<EIfcelement> boundingElements = ifcDataModel.getBoundingElementsOfSpace(space);
		assertNotNull(boundingElements);
		assertTrue(boundingElements.size() > 2);
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
	public void testGetAreas() throws Exception {
		List<EIfcspace> rooms = Arrays.asList(ifcDataModel.getSpaces("0Z3sXNXmjD8ReGD5X6wi6Z"));
//		retrieve wall areas
		double buildingShellArea = ifcDataModel.calculateBuildingShellArea(rooms);
		assertTrue(buildingShellArea > 0);
		System.out.println("BuildingShell-Area: "+buildingShellArea);
//		retrieve window areas
		double windowArea = ifcDataModel.calculateWindowArea(rooms);
		assertTrue(windowArea > 0);
		System.out.println("Window-Area: "+windowArea);
	}
	
	@Test
	public void testIsSeparatingSpaces() throws Exception {
		// all rooms in the building
		List<EIfcspace> rooms = Arrays.asList(ifcDataModel.getIfcEntity("1OtkvKr7P9rPcRNvZqgDba", EIfcspace.class));

		for(EIfcspace space : rooms) {
			Set<EIfcelement> elements = ifcDataModel.getBoundingElementsOfSpace(space);
			for(EIfcelement element : elements) {
				String guid = element.getGlobalid(element);
				boolean isSeparating = ifcDataModel.isSeparatingSpaces(element);
				// separating
				if(guid.equals("2$icKcpkvBAxF7XsR2tMtU")) {
					assertTrue(isSeparating); 
				} else if(guid.equals("08SIkVhRv1awTy16eWMHCZ")) {
					assertTrue(isSeparating);
				} else if(guid.equals("0A63Emn5b6tf_VW9feiRvw")) {
					assertTrue(isSeparating); 
				} else if(guid.equals("31lE5oHtf1PvL3tDCm2ixO")) {
					assertTrue(isSeparating);
				} else if(guid.equals("0I$itM92H8hBBfJU7JQiUQ")) {
					assertTrue(isSeparating); 
				} else if(guid.equals("2adXfHQ0fDZBotCOv7bvFV")) {
					assertTrue(isSeparating);
				}
				// not separating 
				else if(guid.equals("03fp8X74D8JQLFlA9hwxVc")) {
					assertTrue(!isSeparating);
				} else if(guid.equals("2yxT1V0ozCKgnmub0v9GSY")) {
					assertTrue(!isSeparating);
				} else {
					fail("element: "+element+" not specified");
				}
			}
		}
	}
	
	@Test
	public void testGetCellarSeparatingSlabs() throws Exception {
		String buildingGuid = "0Z3sXNXmjD8ReGD5X6wi6Z";
		List<EIfcslab> cellarSeparatingSlabs = ifcDataModel.getCellarSeparatingSlabs(buildingGuid);
		assertTrue(""+cellarSeparatingSlabs, cellarSeparatingSlabs.size() == 2);
		
		for(EIfcslab slab : cellarSeparatingSlabs) {
			String guid = slab.getGlobalid(slab);
			if(guid.equals("1IdM0gD0D8YhlnTACvclsp")) {
				
			} else if(guid.equals("1DMLVoBez8FeHd8YWWXZen")) {
				
			} else {
				fail(guid);
			}
		}
	}
	
	@Test
	public void testGetRoofSeparatingSlabs() throws Exception {
		String buildingGuid = "0Z3sXNXmjD8ReGD5X6wi6Z";
		List<EIfcslab> roofSeparatingSlabs = ifcDataModel.getRoofSeparatingSlabs(buildingGuid);
		assertTrue(""+roofSeparatingSlabs, roofSeparatingSlabs.size() == 2);
		
		for(EIfcslab slab : roofSeparatingSlabs) {
			String guid = slab.getGlobalid(slab);
			if(guid.equals("3epsV4cHj1bAR405yO9g4K")) {
				
			} else {
				fail(guid);
			}
		}
	}
	
	@Test
	public void testGetStairs() throws Exception {
		List<EIfcstair> allStairs = new ArrayList<EIfcstair>();
		EIfcbuilding building = ifcDataModel.getIfcEntity("0Z3sXNXmjD8ReGD5X6wi6Z", EIfcbuilding.class);
		EIfcbuildingstorey[] storeys  = ifcDataModel.getBuildingStoreys(building);
		for(EIfcbuildingstorey storey : storeys) {
			List<EIfcstair> stairs = ifcDataModel.getStairsInBuildingStorey(storey);
			allStairs.addAll(stairs);
		}
		assertTrue(""+allStairs, allStairs.size()==4);
	}
	
	@Test
	public void testCreatePropertySingleValueAndExportExtendedModel() throws Exception {
		SdaiModel model = ifcDataModel.getUnderlyingModel().getModel();
		List<EEntity> entities = new ArrayList<EEntity>(Arrays.asList(ifcDataModel.getUnderlyingModel().getEntities()));
		
		EIfcwallstandardcase wall = ifcDataModel.getIfcEntity("3ypZHz8$T6yf1GITnpX0v0", EIfcwallstandardcase.class);
		
		EIfcpropertysinglevalue property1 = (EIfcpropertysinglevalue) model.createEntityInstance(EIfcpropertysinglevalue.class);
		property1.setName(property1, "Kosten");
		property1.setDescription(property1, "unit:€/m²,Comment:projektbezogen");
		property1.setNominalvalue(property1, 10.5, (EIfcreal)null);
		
		System.out.println(property1);
		
		EIfcpropertysinglevalue property2 = (EIfcpropertysinglevalue) model.createEntityInstance(EIfcpropertysinglevalue.class);
		property2.setName(property2, "beheizt");
		property2.setNominalvalue(property2, true, (EIfcboolean)null);
		
		System.out.println(property2);
		
		EIfcpropertysinglevalue property3 = (EIfcpropertysinglevalue) model.createEntityInstance(EIfcpropertysinglevalue.class);
		property3.setName(property3, "BIM-Snippet");
		property3.setNominalvalue(property3, "<snippet><guid>efjh43jhjknsdf$m</guid></snippet>", (EIfctext)null);
		
		System.out.println(property3);
		
		EIfcpropertyset propertySet = (EIfcpropertyset) model.createEntityInstance(EIfcpropertyset.class);
		propertySet.setGlobalid(propertySet, "222222222");
		propertySet.setName(propertySet, "Eigenschaften");
		AIfcproperty properties = propertySet.createHasproperties(propertySet);
		properties.addUnordered(property1);
		properties.addUnordered(property2);
		properties.addUnordered(property3);
		
		System.out.println(propertySet);
		
		EIfcreldefinesbyproperties relProp = (EIfcreldefinesbyproperties) model.createEntityInstance(EIfcreldefinesbyproperties.class);
		relProp.setGlobalid(relProp, "1111111111");
		relProp.setName(relProp, "Eigenschaften-Relation");
		AIfcobject relatedObjects = relProp.createRelatedobjects(relProp);
		relatedObjects.addUnordered(wall);
		relProp.setRelatingpropertydefinition(relProp, propertySet);
		
		System.out.println(relProp);
		
		entities.add(relProp);
		entities.add(propertySet);
		entities.add(property3);
		entities.add(property2);
		entities.add(property1);
		File outputDir = new File("target/output/");
		outputDir.mkdirs();
		ifcDataModel.getUnderlyingModel().exportModel(entities.toArray(new EEntity[entities.size()]), "target/output/prop.ifc", SIfc2x3.class);
	}

}
