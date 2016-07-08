package de.tudresden.bau.cib.vl.core.simulation.energy.database;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.mock.StepParserMock;
import de.tudresden.bau.cib.vl.core.model.parser.Parser;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.dao.EnergyResultsDao;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyResults;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.ResultCodes;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergyResultService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:simulation-energy-test.xml")  
@TransactionConfiguration(defaultRollback=false,transactionManager="transactionManager")
public class EnergyResultsTest {

	@Autowired
	private EnergyResultsDao energyResultsDao;
	@Autowired
	private EnergyResultService energyResultService;

	private static final String TEST_RESOURCES_PATH		= "test/resources/nandrad/building/kassel/";
	private static final String RESULT_FILE_PATHS 		= TEST_RESOURCES_PATH+"results/";

	private static final String NANDRAD_PROJECT_DIR 	= "test/resources/nandrad/building/kassel/";
	private static final String IFC_MODEL_PATH 			= NANDRAD_PROJECT_DIR+"103_FZ_Kassel_Raeume_Waende_Boeden_Decken_Project_2ndLSB.ifc";
	
	private static Parser parser;
	private static final String workingDirectory 		= "target/test/jsdai/repository";
	
	private List<File> energyResults;
	private static Ifc2x3DataModelProxy ifcModel;
	private static final int simulationId = 1;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {	
		parser = StepParserMock.createIfcParser(workingDirectory);
//		Ifc2x3DataModelProxy proxyModel = (Ifc2x3DataModelProxy) parser.parse(IFC_MODEL_PATH);
//		ifcModel = proxyModel;
//		assertNotNull(ifcModel);
	}
	
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {		
		assertNotNull(energyResultService);
		assertNotNull(energyResultsDao);
		energyResults 					= new ArrayList<File>();
		File resultDir 					= new File(RESULT_FILE_PATHS);
		assertTrue(resultDir.exists());
		energyResults 					= Arrays.asList(resultDir.listFiles());
		
		assertNotNull(energyResults);
		assertTrue(energyResults.size() > 0);
	}
	
	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testGetSimulationResultSetForHeatingAndCooling() throws Exception {		
		Set<ResultCodes> codes = new HashSet<ResultCodes>();
		codes.add(ResultCodes.Heating_Net_Energy);
//		codes.add(ResultCodes.Cooling_Net_Energy);
//		codes.add(ResultCodes.Heating_And_Cooling_Net_Energy);
		
		final int startYear = 2013;
		long timeBeforeReadingResultFiles = System.currentTimeMillis();
		Map<ResultCodes,List<MeasuredUnit>> units = energyResultService.getNandradMeasuredUnits(codes, energyResults, startYear);
		assertNotNull(units);
		assertTrue(units.size() > 0);
		System.out.println("Nandrad result units: "+units);
		
		long timeBeforeInsertingResults = System.currentTimeMillis();
		System.out.println("Time taken for reading result files: "+sdf.format(new Date(timeBeforeInsertingResults-timeBeforeReadingResultFiles)));
		int queryID = 0;
		int i = 1;
		for(List<MeasuredUnit> resUnits : units.values()){
			for(MeasuredUnit unit : resUnits) {
				System.err.println(unit);
				TreeMap<Long, Double> values = unit.getValues();
				if(values.size()>2000 && i % 10 == 0) queryID = i;
				EnergyResults result = new EnergyResults();
//				result.setSimulationId(simulationId);
				result.setTimeToValue(values);
				energyResultsDao.insertEnergyResults(result);
				i++;
				break;
			}
			break;
		}
		long timeAfterInsertingResults = System.currentTimeMillis();
		System.out.println("Time taken for inserting result files: "+sdf.format(new Date(timeAfterInsertingResults-timeBeforeInsertingResults)));
//		EnergyResults result = energyResultsDao.getEnergyResults(queryID);
		List<EnergyResults> results = energyResultsDao.getAllEnergyResults();
		assertNotNull(results);
		EnergyResults result = results.get(0);
		Map<Long, Double> values = result.getTimeToValue();
		int size = values.size();
		System.err.println("Anzahl values: "+size);
	
		long timeAfterQuerying = System.currentTimeMillis();
		System.out.println("Time taken for querying result files: "+sdf.format(new Date(timeAfterQuerying-timeAfterInsertingResults)));
	}
}

