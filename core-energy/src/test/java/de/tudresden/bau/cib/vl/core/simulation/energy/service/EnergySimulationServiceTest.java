package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import jsdai.SIfc2x3.EIfcspace;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.mock.StepParserMock;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustibleContainer;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.CombustiblePackage;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.util.CombustibleResourceFactoryImpl;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.mock.OntologyParserMock;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;
import de.tudresden.bau.cib.vl.core.model.time.TimeMeasure;
import de.tudresden.bau.cib.vl.core.model.visualization.VisualizationJsonFormatter;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.dao.EnergyKeyPerformanceIndicatorDao;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.ResultCodes;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergyResultService.DetailLevel;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:simulation-energy-test.xml")  
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class EnergySimulationServiceTest {

	@Autowired
	private EnergySimulationService service;
	@Autowired
	private EnergyResultService resultService;
	@Autowired
	private EnergyKeyPerformanceIndicatorDao energyKeyPerformanceIndicatorDao;
	@Autowired
	private TemplateService templateService;
	
	private static Ifc2x3DataModelProxy ifcModel;
	private static OntologyModel ontologyModel;
	
	private static final String NANDRAD_PROJECT_DIR 	= "test/resources/nandrad/building/kassel/";
//	private static final String NANDRAD_PROJECT_DIR 	= "C:\\Users\\Ken\\Desktop\\project\\";

	private static final String RESULT_FILE_PATHS 		= NANDRAD_PROJECT_DIR+"results/";
	
//	private static final String IFC_MODEL_PATH 			= NANDRAD_PROJECT_DIR+"FZKassel-Räume+Wände+Böden+Decken_Project-5+Roof_2ndLSB.ifc";
	private static final String IFC_MODEL_PATH 			= NANDRAD_PROJECT_DIR+"103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB.ifc";
	private static final String ONTOLOGY_PATH			= IFC_MODEL_PATH.replace("ifc", "rdf");
	
	
	private static final String workingDirectory 		= "target/test/jsdai/repository";
	private static final String COMBUSTIBLE_PATH		= "test/resources/combustibles.xml";
	
	private static Parser parser;
	private static Parser ontologyParser;
	
	private List<File> energyResults;
	private SimulationProject simulation;
	private static List<Combustible> combustibles;
	private EnergyKeyPerformanceIndicators eKPIs;
	private List<ConstructionTemplate> constructionTemplates;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		parser = StepParserMock.createIfcParser(workingDirectory);
		Ifc2x3DataModelProxy proxyModel = (Ifc2x3DataModelProxy) parser.parse(IFC_MODEL_PATH);
		ifcModel = proxyModel;
		assertNotNull(ifcModel);
		
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
		ontologyParser = OntologyParserMock.createParser(workingDirectory, "RDF/XML");
		ontologyModel = (OntologyModel) ontologyParser.parse(ONTOLOGY_PATH);
		assertNotNull(ontologyModel);
		
		
//		service 						= new EnergySimulationServiceImpl();
		assertNotNull(service);
	}
	
	@After
	public void tearDown() throws Exception {
		eKPIs = null;
	}
	
	private void initializePostProcessing() {
		assertNotNull(templateService);
		assertNotNull(energyKeyPerformanceIndicatorDao);
		energyResults 					= new ArrayList<File>();
		File resultDir 					= new File(RESULT_FILE_PATHS);
		energyResults 					= Arrays.asList(resultDir.listFiles());
		
		assertNotNull(energyResults);
		assertTrue(energyResults.size() > 0);
		
		simulation = new SimulationProject();
		simulation.setId(1);
		
		eKPIs = new EnergyKeyPerformanceIndicators();
		eKPIs.setSimulationId(simulation.getId());
		
		eKPIs.setPlantExpeditureFigureHeating(1.23);
		eKPIs.setPlantExpeditureFigureCooling(1.1);
		
		List<String> assignedCombustibleIds = new ArrayList<String>();
		assignedCombustibleIds.add("_G4TlQdQsEeK6Xdd4dOLERQ");
		assignedCombustibleIds.add("_xXbwoOrbEeK2gM1Azt9urQ");
		assignedCombustibleIds.add("_yaYbYOrbEeK2gM1Azt9urQ");
		assignedCombustibleIds.add("_zMXR8OrbEeK2gM1Azt9urQ");
		assignedCombustibleIds.add("_5ckEsOrbEeK2gM1Azt9urQ"); // basic charge available
		eKPIs.setCombustibleIds(assignedCombustibleIds);
																						// factors: CO2	|	SO2	|	NOX
		Map<String, Double> percentagePerHeatingCombustible = new HashMap<String, Double>(); 
		percentagePerHeatingCombustible.put("_G4TlQdQsEeK6Xdd4dOLERQ", 70.0);				//		302	|	0.455	0.227		
		percentagePerHeatingCombustible.put("_yaYbYOrbEeK2gM1Azt9urQ", 20.0);				//		438	|	2,564	0,458
		percentagePerHeatingCombustible.put("_5ckEsOrbEeK2gM1Azt9urQ", 10.0);				//		41	|	0,68	0,799
		Map<String, Double> percentagePerCoolingCombustible = new HashMap<String, Double>();
		percentagePerCoolingCombustible.put("_G4TlQdQsEeK6Xdd4dOLERQ", 35.0);				//		302	|	0.455	0.227
		percentagePerCoolingCombustible.put("_zMXR8OrbEeK2gM1Azt9urQ", 25.0);				//		451	|	0,339	0,911
		percentagePerCoolingCombustible.put("_xXbwoOrbEeK2gM1Azt9urQ", 40.0);				//		438	|	2,245	0.249
		eKPIs.setPercentagePerCoolingCombustible(percentagePerCoolingCombustible);
		eKPIs.setPercentagePerHeatingCombustible(percentagePerHeatingCombustible);
		
		Integer eKPIId = energyKeyPerformanceIndicatorDao.insertEnergyKeyPerformanceIndicators(eKPIs);
		assertTrue(eKPIId > 0);
		
		constructionTemplates = templateService.listConstructionResources();
		assertNotNull(constructionTemplates);
	}
	
	@Test
	public void testGetSimulationResultForGreenhouseGasEmissions() throws Exception {
		initializePostProcessing();
//		prepare eKPIs
		eKPIs.setFinalEnergyHeating(23002.56);
		eKPIs.setFinalEnergyCooling(12670.3);
		
		List<String> assignedCombustibleIds = new ArrayList<String>();
		assignedCombustibleIds.add("_G4TlQdQsEeK6Xdd4dOLERQ");
		assignedCombustibleIds.add("_xXbwoOrbEeK2gM1Azt9urQ");
		assignedCombustibleIds.add("_yaYbYOrbEeK2gM1Azt9urQ");
		assignedCombustibleIds.add("_zMXR8OrbEeK2gM1Azt9urQ");
		assignedCombustibleIds.add("_3KLLUOrbEeK2gM1Azt9urQ");
		eKPIs.setCombustibleIds(assignedCombustibleIds);
																						// factors: CO2	|	SO2	|	NOX
		Map<String, Double> percentagePerHeatingCombustible = new HashMap<String, Double>(); 
		percentagePerHeatingCombustible.put("_G4TlQdQsEeK6Xdd4dOLERQ", 70.0);				//		302	|	0.455	0.227		
		percentagePerHeatingCombustible.put("_yaYbYOrbEeK2gM1Azt9urQ", 20.0);				//		438	|	2,564	0,458
		percentagePerHeatingCombustible.put("_3KLLUOrbEeK2gM1Azt9urQ", 10.0);				//		41	|	0,68	0,799
		Map<String, Double> percentagePerCoolingCombustible = new HashMap<String, Double>();
		percentagePerCoolingCombustible.put("_G4TlQdQsEeK6Xdd4dOLERQ", 35.0);				//		302	|	0.455	0.227
		percentagePerCoolingCombustible.put("_zMXR8OrbEeK2gM1Azt9urQ", 25.0);				//		451	|	0,339	0,911
		percentagePerCoolingCombustible.put("_xXbwoOrbEeK2gM1Azt9urQ", 40.0);				//		438	|	2,245	0.249
		eKPIs.setPercentagePerCoolingCombustible(percentagePerCoolingCombustible);
		eKPIs.setPercentagePerHeatingCombustible(percentagePerHeatingCombustible);
		
		Integer eKPIId = energyKeyPerformanceIndicatorDao.insertEnergyKeyPerformanceIndicators(eKPIs);
		assertTrue(eKPIId > 0);
		
//		calculations:
//			_G4TlQdQsEeK6Xdd4dOLERQ
//		CO2:	20536,397 * KC = 6201991,894
//		SO2:	20536,397 * KS = 9344,060635
//		NOX:	20536,397 * KN = 4661,762119
//			_yaYbYOrbEeK2gM1Azt9urQ
//		CO2:	4600,512 * KC = 2015024,256
//		SO2:	4600,512 * KS = 11795,712768
//		NOX:	4600,512 * KN = 2107,034496
//			_3KLLUOrbEeK2gM1Azt9urQ
//		CO2:	2300,256 * KC = 94310,496
//		SO2:	2300,256 * KS = 1564,17408
//		NOX:	2300,256 * KN =	1837,904544
//			_zMXR8OrbEeK2gM1Azt9urQ
//		CO2:	3167,575 * KC = 1428576,325
//		SO2:	3167,575 * KS = 1073,807925
//		NOX:	3167,575 * KN = 2885,660825
//			_xXbwoOrbEeK2gM1Azt9urQ
//		CO2:	5068,12 * KC = 2219836,56
//		SO2:	5068,12 * KS = 11377,9294
//		NOX:	5068,12 * KN = 1261,96188
//		final result
//		CO2: 11959739,531
//		SO2: 35155,684808
//		NOX: 12754,323864
		
		Set<ResultCodes> codes = new HashSet<ResultCodes>();
		codes.add(ResultCodes.Greenhouse_Gas_Emissions);
		
		final int startYear = 2012;
		Map<ResultCodes,List<MeasuredUnit>> units = resultService.getNandradMeasuredUnits(codes, energyResults, startYear);
		assertNotNull(units);
		assertTrue(units.size() > 0);
		
		List<String> guids = new ArrayList<String>();
		guids.add("3CFBx9qkP2CwpjgaTk2q33"); // Building - A1
		
		final String startTime 			= "2012-01-01T00:00:00";		
		final String endTime 			= "2012-12-31T23:59:59";
		
		Date startDate 					= ConfigurationService.ISO_DATE_FORMAT.parse(startTime);
		Date endDate 					= ConfigurationService.ISO_DATE_FORMAT.parse(endTime);
		
		TimeMeasure timeMeasure 		= TimeMeasure.WEEK;
		final int unitTime 				= 1;
		final DetailLevel detailLevel 	= DetailLevel.AGGREGATED;
		
		List<MeasuredUnit> unitsAfterPostProcessing = resultService.doPostProcessing(simulation, units, guids, startDate, endDate, unitTime, timeMeasure, detailLevel, ifcModel);
		assertNotNull(unitsAfterPostProcessing);
		assertTrue(unitsAfterPostProcessing.size() == 1);
		
		MeasuredUnit finalUnit = unitsAfterPostProcessing.iterator().next();
		Map<String,List<String>> eKPIs = finalUnit.getKeyPerformanceIndicator();
		List<String> co2s = eKPIs.get("CO2");
		List<String> so2s = eKPIs.get("SO2");
		List<String> noxs = eKPIs.get("NOX");
		assertTrue(co2s.get(0).equals("1.1959739531E7"));
		assertTrue(so2s.get(0).equals("35155.684808"));
		assertTrue(noxs.get(0).equals("12754.323864"));

		
		String json = VisualizationJsonFormatter.toJson(new ArrayList<MeasuredUnit>(unitsAfterPostProcessing));
		assertNotNull(json);
		System.out.println(json);
	}
	
	@Test
	public void testGetSimulationResultSetForTemperatureOverAndUnderruns() throws Exception {
		initializePostProcessing();
		Set<ResultCodes> codes = new HashSet<ResultCodes>();
//		codes.add(ResultCodes.Temperature_Over_Under_Runs);
//		codes.add(ResultCodes.Thermal_Comfort);
		codes.add(ResultCodes.Thermal_Comfort_Global_Values);
		
		final int startYear = 2012;
		Map<ResultCodes,List<MeasuredUnit>> units = resultService.getNandradMeasuredUnits(codes, energyResults, startYear);
		assertNotNull(units);
		assertTrue(units.size() > 0);
//		System.out.println(units);
		
		List<String> guids = new ArrayList<String>();
//		guids.add("0SF3lVjyj6yPlBfSHtaVAM"); // Raum in Geschoss A1
//		guids.add("29N_BnBvTCc81bL76C5C7J"); // Raum in Geschoss A0
//		guids.add("2P2cFObzvDo9ZYwtWtcGxS"); // Raum in Geschoss A0
//		guids.add("0sAYS_oH1BTAUIg9MMuMg5"); // Raum 'Flur' fuer Cooling
//		guids.add("1c8MM$cXL2K94iihFTkFHY"); // Raum 'Serverraum' fuer Heating
//		guids.add("2diUBOqGz0oOxpboGZD_QL"); // Raum 'Werkstatt' fuer Cooling
		guids.add("1GUyJjxof6GQCnTXtOpIc$"); // Raum 'Archiv' fuer Thermal Comfort -> sehr warm
//		guids.add("3F9hozN5P16OMlrj0Dt_DK"); // Raum 'Klassenzimmer' fuer Thermal Comfort
//		guids.add("2k4zl$GKD7F89YfaBWTm5X"); // Raum 'Sicherheitsbeleuchtung' fuer Thermal Comfort
//		guids.add("1c8MM$cXL2K94iihFTkFHY"); // Raum 'Serverraum' fuer Thermal Comfort
//		guids.add("2v7lsH3gL11RU8oR83O9Cp"); // Raum in Geschoss A2
//		guids.add("0zZPtfoOf9mu0kn69qO0yY"); // Storey - A2
//		guids.add("2v7lsH3gL11RU8oR83O9Cp"); // Raum in Geschoss A2
//		guids.add("0zZPtfoOf9mu0kn69qO0yY"); // Storey - A2
//		guids.add("3CFBx9qkP2CwpjgaTk2q33"); // Building - A1
		

		final String startTime 			= "2012-01-01T00:00:00";		
		final String endTime 			= "2012-12-31T23:59:59";
		
		Date startDate 					= ConfigurationService.ISO_DATE_FORMAT.parse(startTime);
		Date endDate 					= ConfigurationService.ISO_DATE_FORMAT.parse(endTime);
		
		TimeMeasure timeMeasure 		= TimeMeasure.HOUR;
		final int unitTime 				= 1;
		final DetailLevel detailLevel 	= DetailLevel.AGGREGATED;
		
		List<MeasuredUnit> unitsAfterPostProcessing = resultService.doPostProcessing(simulation, units, guids, startDate, endDate, unitTime, timeMeasure, detailLevel, ifcModel);
		assertNotNull(unitsAfterPostProcessing);
		assertTrue(unitsAfterPostProcessing.size() > 0);
		System.out.println(unitsAfterPostProcessing);
		System.out.println(unitsAfterPostProcessing.size());
		for(MeasuredUnit unit : unitsAfterPostProcessing) {
			TreeMap<Long, Double> values = unit.getValues();
			if(unit.getResultCode() == ResultCodes.Positive_Deviations_Set_Point_Room_Air_Temperature.getCode()) System.out.println(values);
//			if(unit.getResultCode() == ResultCodes.Negative_Deviations_Set_Point_Room_Air_Temperature.getCode()) System.out.println(values);
			if(unit.getResultCode() == ResultCodes.Thermal_Comfort_Global_Values.getCode()) System.out.println(unit.getKeyPerformanceIndicator());
		}
		
//		String json = VisualizationJsonFormatter.toJson(new ArrayList<MeasuredUnit>(unitsAfterPostProcessing));
//		System.out.println(json);
	}
	
	@Test
	public void getSimulationResultSet() throws Exception {
		initializePostProcessing();
		Set<ResultCodes> codes = new HashSet<ResultCodes>();
//		codes.add(ResultCodes.Room_Air_Temperature);
//		codes.add(ResultCodes.Heating);
//		codes.add(ResultCodes.Heating_Net_Energy);
//		codes.add(ResultCodes.Cooling_Net_Energy);
//		codes.add(ResultCodes.Cooling_Final_Energy);
//		codes.add(ResultCodes.Heating_Final_Energy);
//		codes.add(ResultCodes.Set_Point_Room_Temperature_Heating);
//		codes.add(ResultCodes.Positive_Deviations_Set_Point_Room_Air_Temperature);
//		codes.add(ResultCodes.Negative_Deviations_Set_Point_Room_Air_Temperature);
//		codes.add(ResultCodes.Heating_And_Cooling_Net_Energy);
		codes.add(ResultCodes.Heating_And_Cooling_Final_Energy);
		
		final int startYear = 2012;
		Map<ResultCodes,List<MeasuredUnit>> units = resultService.getNandradMeasuredUnits(codes, energyResults, startYear);
		assertNotNull(units);
		assertTrue(units.size() > 0);
//		System.out.println(units);
		
		List<String> guids = new ArrayList<String>();
		guids.add("1pfeQqqxfBtvRzniADCXt$");
		

		final String startTime 			= "2012-01-01T00:00:00";		
		final String endTime 			= "2012-12-31T23:59:59";
		
		Date startDate 					= ConfigurationService.ISO_DATE_FORMAT.parse(startTime);
		Date endDate 					= ConfigurationService.ISO_DATE_FORMAT.parse(endTime);
		
		TimeMeasure timeMeasure 		= TimeMeasure.WEEK;
		final int unitTime 				= 1;
		final DetailLevel detailLevel 	= DetailLevel.AGGREGATED;
		
		List<MeasuredUnit> unitsAfterPostProcessing = resultService.doPostProcessing(simulation, units, guids, startDate, endDate, unitTime, timeMeasure, detailLevel, ifcModel);
		assertNotNull(unitsAfterPostProcessing);
		assertTrue(unitsAfterPostProcessing.size() > 0);
		System.out.println("Count of units after processing: "+unitsAfterPostProcessing.size());
		for(MeasuredUnit unit : unitsAfterPostProcessing) {
			System.out.println("Unit: "+unit);
			TreeMap<Long, Double> values = unit.getValues();
//			assertTrue("Number of values are wrong ("+values.size()+")", values.size() > 0);
//			System.out.println(values);
			System.out.println("Unit values: "+values.size());
			if(detailLevel == DetailLevel.AGGREGATED) System.out.println(values);
//			System.out.println("EKPI: "+unit.getKeyPerformanceIndicator());
		}
		
		String json = VisualizationJsonFormatter.toJson(new ArrayList<MeasuredUnit>(unitsAfterPostProcessing));
		System.out.println(json);
	}
	
	@Test
	public void testGetSimulationResultSetForHeatingAndCooling() throws Exception {		
		initializePostProcessing();
		Set<ResultCodes> codes = new HashSet<ResultCodes>();
//		codes.add(ResultCodes.Heating_Net_Energy);
//		codes.add(ResultCodes.Cooling_Net_Energy);
//		codes.add(ResultCodes.Cooling_Final_Energy);
		codes.add(ResultCodes.Heating_Final_Energy);
//		codes.add(ResultCodes.Heating_And_Cooling_Net_Energy);
//		codes.add(ResultCodes.Heating_And_Cooling_Final_Energy);
		
		final int startYear = 2013;
		Map<ResultCodes,List<MeasuredUnit>> units = resultService.getNandradMeasuredUnits(codes, energyResults, startYear);
		assertNotNull(units);
		assertTrue(units.size() > 0);
		System.out.println("Nandrad result units: "+units);
		
		List<String> guids = new ArrayList<String>();
		guids.add("1pfeQqqxfBtvRzniADCXt$");
		

		final String startTime 			= "2013-01-01T00:00:00";		
		final String endTime 			= "2013-12-31T23:59:59";
		
		Date startDate 					= ConfigurationService.ISO_DATE_FORMAT.parse(startTime);
		Date endDate 					= ConfigurationService.ISO_DATE_FORMAT.parse(endTime);
		
		TimeMeasure timeMeasure 		= TimeMeasure.MONTH;
		final int unitTime 				= 1;
		final DetailLevel detailLevel 	= DetailLevel.AGGREGATED;
		
		List<MeasuredUnit> unitsAfterPostProcessing = resultService.doPostProcessing(simulation, units, guids, startDate, endDate, unitTime, timeMeasure, detailLevel, ifcModel);
		assertNotNull(unitsAfterPostProcessing);
		assertTrue(unitsAfterPostProcessing.size() > 0);
		System.out.println(unitsAfterPostProcessing.size());
		for(MeasuredUnit unit : unitsAfterPostProcessing) {
			TreeMap<Long, Double> values = unit.getValues();
//			assertTrue("Number of values are wrong ("+values.size()+")", values.size() > 0);
//			System.out.println(values);
			System.out.println(values.size());
			if(detailLevel == DetailLevel.AGGREGATED) System.out.println(values);
			System.out.println("EKPI: "+unit.getKeyPerformanceIndicator());
			
//			double finalHeating = EnergyKeyPerformanceIndicatorCalculator.calculateHeatingFinalEnergy(unit, eKPIs);
//			System.out.println("Final energy heating: "+finalHeating);
		}
		
//		String json = VisualizationJsonFormatter.toJson(new ArrayList<MeasuredUnit>(unitsAfterPostProcessing));
//		System.out.println(json);
	}
	
	@Test
	public void testGetSimulationResultSetForLifecycleCosts() throws Exception {
		initializePostProcessing();
		testGetSimulationResultSetForHeatingAndCooling();
		
//		ResultCodes code = ResultCodes.Investment_Costs;
//		ResultCodes code = ResultCodes.Energy_Related_Operational_Costs;
		ResultCodes code = ResultCodes.Lifecycle_Costs;
		
		List<String> guids = new ArrayList<String>();
		guids.add("3CFBx9qkP2CwpjgaTk2q33"); // Building - A1

		MeasuredUnit unit = resultService.doPostProcessingForCosts(
				guids, code, ifcModel, ontologyModel, simulation, constructionTemplates);
		assertNotNull(unit);
		
		System.out.println("KPI: "+unit.getKeyPerformanceIndicator());
		
//		String json = VisualizationJsonFormatter.toJson(new ArrayList<MeasuredUnit>(unitsAfterPostProcessing));
//		System.out.println(json);
	}
	
	String NO_ROOM_REGEX_PATTERN = "no room|kein raum";
	
	final List<String> getAllSpaceGUIDsInAllBuildings() throws Exception {
		List<String> locations = new ArrayList<String>();
		
		List<String> buildingGUIDs = ifcModel.getBuildingsIDs();
		
//		EXAMPLE 1: use all used spaces in all buildings
		for(String buildingGUID : buildingGUIDs) {
			EIfcspace[] spaces = ifcModel.getSpacesOfBuilding(buildingGUID);
			for(EIfcspace space : spaces) {
				if(space.testName(space)) {
					String roomName = space.getName(space);
					if(roomName.matches(NO_ROOM_REGEX_PATTERN) || StringUtils.isEmpty(roomName)) continue; // don't use "no rooms" or rooms without names
				
					locations.add(space.getGlobalid(space));
				}
			}
		}
		return locations.size() > 0 ? locations : null;
	}
	
	final List<String> getSpaceGUIDsByNames(String... roomNames) throws Exception {
		assertNotNull(roomNames);
		List<String> locations = new ArrayList<String>();
		
		List<String> allSpaceGUIDs = getAllSpaceGUIDsInAllBuildings();

		for(String spaceGUID : allSpaceGUIDs) {
			EIfcspace space = ifcModel.getSpace(spaceGUID);
			assertNotNull(space);
			if(space.testName(space)) {
				String roomName = space.getName(space);
				if(roomName.matches(NO_ROOM_REGEX_PATTERN) || StringUtils.isEmpty(roomName)) continue; // don't use "no rooms" or rooms without names
				for(String compareName : roomNames) {
					assertTrue(StringUtils.isNotEmpty(compareName));
					if(roomName.equalsIgnoreCase(compareName)) locations.add(spaceGUID);
				}
			}
		}
		return locations.size() > 0 ? locations : null;
	}
}
