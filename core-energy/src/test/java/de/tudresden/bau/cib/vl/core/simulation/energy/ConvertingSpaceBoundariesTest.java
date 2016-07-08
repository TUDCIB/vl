package de.tudresden.bau.cib.vl.core.simulation.energy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import jsdai.SIfc2x3.EIfcrelspaceboundary;
import jsdai.lang.SdaiException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.exceptions.parser.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.mock.StepParserMock;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SpaceBoundaryConversionException;
import de.tudresden.bau.cib.vl.core.simulation.energy.transformation.sb.ConvertingSpaceBoundaries;

public class ConvertingSpaceBoundariesTest {
	
	private ConvertingSpaceBoundaries conversion;
	private Ifc2x3DataModelProxy ifcModel;
	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\IFC3\\SpaceBoundaries\\1stLevel\\PIDO_1800F.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\Quickstart_Project.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\rac_basic_sample_project.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\HESMOS_Schule-A1_one_building.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\Kassel_V2.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\Kassel_5th_Floor_6.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\HESMOS_Schule.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\Alfons-Kern-Schule_GebD_V13.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\FZ_Kassel_Raeume_Waende_Boeden_Decken_Project.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\20111031_Eingabeplan_Nachbar_VAR2a_Dach.ifc";
	
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\ISES_Bestand_20131209.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\ISES\\ISES_Umbau_20131209.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\ISES\\ISES_Umbau_ohne_SB_ohne_openings2.ifc";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\ISES.IFC";
//	private static final String fileToConvert = "D:\\Nutzer\\ken\\data\\ifcFiles\\SpaceBoundaries\\1stLevel\\ISES\\140910_Kindergarten_neu.ifc";
//	private static final String fileToConvert = "D:\\ModernHomePrototype.ifc";
	
	
	private static final String workingDirectory = "D:\\Nutzer\\ken\\data\\repository";
	private String tempDirectory = "D:\\Nutzer\\ken\\data\\ifcFiles\\temp";
	private String saveDirectory = "D:\\Nutzer\\ken\\data\\ifcFiles\\export";

	private static final String PATH_SPACEBOUNDARYREAD = "resources/tools/SpaceBoundaryReadAfterUpdate.exe";
	
	private static Parser parser;
	private Collection<EIfcrelspaceboundary> firstLevelSpaceBoundaries;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		assertTrue(new File(workingDirectory).exists());
		assertTrue(new File(fileToConvert).exists());
//		parser = parserFactory.createParser("ifc", workingDirectory);
		parser = StepParserMock.createIfcParser(workingDirectory);
//		parser = Ifc2x3Parser.createParser(workingDirectory);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		parser.stop();
	}

	@Before
	public void setUp() throws Exception {		
		assertNotNull(fileToConvert);
		assertNotNull(tempDirectory);
		assertNotNull(saveDirectory);
		Ifc2x3DataModelProxy proxyModel = (Ifc2x3DataModelProxy) parser.parse(fileToConvert);
		ifcModel = proxyModel;
		assertNotNull(ifcModel);
		System.out.println("Loaded IFC Model: "+fileToConvert);
		
		firstLevelSpaceBoundaries = ifcModel.getSpaceBoundaries();
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testStartConversion() throws SdaiException, ParsingException, IOException, ParsingException, IfcException, SpaceBoundaryConversionException {
		conversion = new ConvertingSpaceBoundaries(ifcModel.getOrigin(), new File(saveDirectory), new File(fileToConvert), new File(tempDirectory));
		System.out.println("Starting conversion...");
		boolean success = conversion.startConversion(PATH_SPACEBOUNDARYREAD);
		assertTrue(success);
		File convertedFile = conversion.getConvertedIfcFile();
		assertNotNull(convertedFile);
		System.out.println("Exported to "+convertedFile);
	}
	
	@Test
	public void testStartConversionAndCompareSpaceBoundaries() throws Exception {
		conversion = new ConvertingSpaceBoundaries(ifcModel.getOrigin(), new File(saveDirectory), new File(fileToConvert), new File(tempDirectory));
		System.out.println("Starting conversion...");
		boolean success = conversion.startConversion(PATH_SPACEBOUNDARYREAD);
		assertTrue(success);
		File convertedFile = conversion.getConvertedIfcFile();
		assertNotNull(convertedFile);
		System.out.println("Exported to "+convertedFile);
		parser.stop();
		parser = StepParserMock.createIfcParser(workingDirectory);
		
		System.out.println("Now comparing space boundaries...");
		Ifc2x3DataModelProxy proxyModel = (Ifc2x3DataModelProxy) parser.parse(fileToConvert);
		Set<EIfcrelspaceboundary> secondLevelSpaceBoundaries = proxyModel.getSpaceBoundaries();
		assertTrue("2nd LSB = "+secondLevelSpaceBoundaries.size() + " 1st LSB = "+firstLevelSpaceBoundaries.size(), 
				secondLevelSpaceBoundaries.size() >= firstLevelSpaceBoundaries.size());
		System.out.println("Conversion successfully! \nThere are now "+secondLevelSpaceBoundaries.size()
				+" 2nd LSB (before there are "+firstLevelSpaceBoundaries.size()+" 1st LSB)");
	}

}
