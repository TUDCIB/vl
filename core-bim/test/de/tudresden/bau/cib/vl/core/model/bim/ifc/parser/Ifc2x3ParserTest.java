package de.tudresden.bau.cib.vl.core.model.bim.ifc.parser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.Set;

import jsdai.SIfc2x3.AIfcproperty;
import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfccomplexproperty;
import jsdai.SIfc2x3.EIfcdescriptivemeasure;
import jsdai.SIfc2x3.EIfcdoor;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcopeningelement;
import jsdai.SIfc2x3.EIfcproperty;
import jsdai.SIfc2x3.EIfcpropertysinglevalue;
import jsdai.SIfc2x3.EIfcrelfillselement;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcwindow;
import jsdai.lang.SdaiException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.model.utility.IterableAggregate;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;

public class Ifc2x3ParserTest {

	private static File fileToParse = new File("resources/ifc/Quickstart_Project_2ndLSB.ifc");
	private static File workingDirectory = new File("target/jsdai/repository");
	private static Ifc2x3Parser parser;
	private Ifc2x3DataModelProxy ifcDataModel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		if(!workingDirectory.exists()) workingDirectory.mkdir();
		assertTrue(fileToParse.exists());
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

	@Test
	public void testParseString() throws ParsingException, IfcException, SdaiException {
		parser = (Ifc2x3Parser) Ifc2x3Parser.createParser(workingDirectory.getAbsolutePath());
		ifcDataModel = parser.parse(fileToParse.getAbsolutePath());
		assertNotNull(ifcDataModel);
		EIfcbuilding building = ifcDataModel.getBuildings().values().iterator().next();
		assertNotNull(building);
		
		EIfcbuildingstorey[] storeys = ifcDataModel.getBuildingStoreys(building);
		assertTrue(storeys.length > 0);
		
		for(EIfcbuildingstorey storey : storeys) {
			assertNotNull(storey);
			Set<String> spaceGuids = ifcDataModel.getSpacesOfSpatialStructure(storey.getGlobalid(storey));
			for(String spaceGuid : spaceGuids) {
				assertNotNull(spaceGuid);
				Set<EIfcelement> elementsOfSpace = ifcDataModel.getBoundingElementsOfSpace(ifcDataModel.getIfcEntity(spaceGuid, EIfcspace.class));
				assertNotNull(elementsOfSpace);
				for(EIfcelement elementOfSpace : elementsOfSpace) {
					assertNotNull(elementOfSpace);					
					if(elementOfSpace instanceof EIfcwindow || elementOfSpace instanceof EIfcdoor) {
						List<EIfcrelfillselement> relFillsElements = ifcDataModel.getRelFillsElement(elementOfSpace);
						if(relFillsElements != null) {
							for(EIfcrelfillselement relFillsElement : relFillsElements) {
								System.out.println("RELFILLELEMENT: "+relFillsElement);
								EIfcopeningelement openingElement = relFillsElement.getRelatingopeningelement(relFillsElement);
								EIfcproperty[] properties = ifcDataModel.getProperties(openingElement);
								for(EIfcproperty property : properties) {
									System.err.println(property);
									if(property instanceof EIfccomplexproperty) {
										EIfccomplexproperty complexProperty = (EIfccomplexproperty) property;
										AIfcproperty aProperty = complexProperty.getHasproperties(complexProperty);
										for(EIfcproperty prop : new IterableAggregate<EIfcproperty>(aProperty)) {
											if(prop instanceof EIfcpropertysinglevalue) {
												EIfcpropertysinglevalue singleValue = (EIfcpropertysinglevalue) prop;
												if(singleValue.testName(singleValue)) {
													if(singleValue.getName(singleValue).equalsIgnoreCase("bezeichnung")) {
														String value = singleValue.getNominalvalue(singleValue, (EIfcdescriptivemeasure) null);
														System.out.println(value);
													}
												}

											}
										}
									}
//									Object propertyValue = ifcDataModel.getPropertyValue(property);
//									System.out.println("VALUE: "+propertyValue);
								}
							}
						}
					}
				}
			}
		}
	}

	@Test
	public void testCreateParser() {
		parser = (Ifc2x3Parser) Ifc2x3Parser.createParser(workingDirectory.getAbsolutePath());
		assertNotNull(parser);
	}

}
