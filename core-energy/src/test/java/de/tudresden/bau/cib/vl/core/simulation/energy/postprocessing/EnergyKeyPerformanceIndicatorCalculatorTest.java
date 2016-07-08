package de.tudresden.bau.cib.vl.core.simulation.energy.postprocessing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.emf.common.util.EList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.eeBim.serdes.CombustibleContainerDeserializer;
import de.tudresden.bau.cib.vl.core.model.processing.PostProcessingEngine;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.EnergyKeyPerformanceIndicatorException;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergyResultService.EnergyResourceType;

public class EnergyKeyPerformanceIndicatorCalculatorTest {

	private static NandradPostProcessingInputStream streamOperative, streamSetpoint, streamHeatingGains, streamCoolingGains;
	
	private static final String RESULT_DIR =							"test/resources/nandrad/building/";
	
	private static final String OPERATIVE_TEMPERATURES_PATH = 			RESULT_DIR+"Zones_OperativeTemperature.d6o";
	private static final String HEATING_SETPOINT_TEMPERATURES_PATH = 	RESULT_DIR+"Zones_HeatingSetPointTemperature.d6o";
	private static final String HEATING_GAINS_PATH = 					RESULT_DIR+"Zones_HeatingGains.d6o";
	private static final String COOLING_GAINS_PATH = 					RESULT_DIR+"Zones_CoolingGains.d6o";
	private static final String COMBUSTIBLES_PATH ="test/resources/combustibles.xml";
	
	private List<MeasuredUnit> operativeUnits;
	private List<MeasuredUnit> setpointUnits;
	private List<MeasuredUnit> heatingGainsUnits;
	private List<MeasuredUnit> coolingGainsUnits;	

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		streamOperative = new NandradPostProcessingInputStream(0,OPERATIVE_TEMPERATURES_PATH,2013);
		streamSetpoint = new NandradPostProcessingInputStream(1,HEATING_SETPOINT_TEMPERATURES_PATH,2013);
		streamHeatingGains = new NandradPostProcessingInputStream(2,HEATING_GAINS_PATH,2013);
		streamCoolingGains = new NandradPostProcessingInputStream(3,COOLING_GAINS_PATH,2013);		

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<List<MeasuredUnit>> operativeFuture = executor.submit(streamOperative);
		Future<List<MeasuredUnit>> setpointFuture = executor.submit(streamSetpoint);
		Future<List<MeasuredUnit>> heatingGainsFuture = executor.submit(streamHeatingGains);
		Future<List<MeasuredUnit>> coolingGainsFuture = executor.submit(streamCoolingGains);

		operativeUnits 		= 	operativeFuture.get();
		setpointUnits 		= 	setpointFuture.get();
		heatingGainsUnits	=	heatingGainsFuture.get();
		coolingGainsUnits	=	coolingGainsFuture.get();
		
		assertNotNull(operativeUnits);
		assertNotNull(setpointUnits);
		assertNotNull(heatingGainsUnits);
		assertNotNull(coolingGainsUnits);
	}

	@After
	public void tearDown() throws Exception {}
	
	@Test
	public void testCalculateFinalHeating() {
		final double plantExpeditureFigure = 1.23;
		for(MeasuredUnit unit : heatingGainsUnits) {
			PostProcessingEngine.convertValuesOfUnit(unit, 1000);
			double netEnergyHeating = PostProcessingEngine.createSumOfValues(unit);			
			System.out.println("Net-Energy-Heating: "+netEnergyHeating);
			double finalEnergyHeating = EnergyKeyPerformanceIndicatorCalculator.calculateFinalEnergy(netEnergyHeating, plantExpeditureFigure);
			System.out.println("Final-Energy-Heating: "+finalEnergyHeating);
			assertTrue((netEnergyHeating*plantExpeditureFigure) == finalEnergyHeating);
		}	
	}
	
	@Test
	public void testCalculateFinalCooling() {
		final double plantExpeditureFigure = 1.1;
		for(MeasuredUnit unit : coolingGainsUnits) {
			PostProcessingEngine.convertValuesOfUnit(unit, 1000);
			double netEnergyCooling = PostProcessingEngine.createSumOfValues(unit);			
			System.out.println("Net-Energy-Cooling: "+netEnergyCooling);
			double finalEnergyCooling = EnergyKeyPerformanceIndicatorCalculator.calculateFinalEnergy(netEnergyCooling, plantExpeditureFigure);
			System.out.println("Final-Energy-Cooling: "+finalEnergyCooling);
			assertTrue((netEnergyCooling*plantExpeditureFigure) == finalEnergyCooling);
		}	
	}

	@Test
	public void testCalculateEcologicGreenhouseGasEmissionValue() {
		final double eKPI11a 				= 23122.5;
		final double eKPI12 				= 51789.8;		
//		Summe : 74912.3
		
		List<Double> conversionFactorsCO2 	= new ArrayList<Double>();
		conversionFactorsCO2.add(302.0);
		conversionFactorsCO2.add(244.0);
		List<Double> conversionFactorsSO2 	= new ArrayList<Double>();
		conversionFactorsSO2.add(.455);
		List<Double> conversionFactorsNOX 	= new ArrayList<Double>();
		conversionFactorsNOX.add(.227);
		conversionFactorsNOX.add(.200);
		
		Map<EnergyResourceType, List<Double>> conversionFactorsOfEnergyResource = new HashMap<EnergyResourceType, List<Double>>();
		conversionFactorsOfEnergyResource.put(EnergyResourceType.CO2, conversionFactorsCO2);
		conversionFactorsOfEnergyResource.put(EnergyResourceType.SO2, conversionFactorsSO2);
		conversionFactorsOfEnergyResource.put(EnergyResourceType.NOX, conversionFactorsNOX);
		
		List<Double> percentagesCO2			= new ArrayList<Double>();
		List<Double> percentagesSO2			= new ArrayList<Double>();
		List<Double> percentagesNOX			= new ArrayList<Double>();
		
		percentagesCO2.add(35.0);
		percentagesCO2.add(15.0);
		percentagesSO2.add(55.0);
		percentagesNOX.add(10.0);
		percentagesNOX.add(20.0);
		
		Map<EnergyResourceType, List<Double>> percentagesPerEnergyResource = new HashMap<EnergyResourceType, List<Double>>();
		percentagesPerEnergyResource.put(EnergyResourceType.CO2, percentagesCO2);
		percentagesPerEnergyResource.put(EnergyResourceType.SO2, percentagesSO2);
		percentagesPerEnergyResource.put(EnergyResourceType.NOX, percentagesNOX);
		
		Map<EnergyResourceType, Double> eKPI21 = EnergyKeyPerformanceIndicatorCalculator.calculateEcologicGreenhouseGasEmissionValue(
				eKPI11a, eKPI12, conversionFactorsOfEnergyResource, percentagesPerEnergyResource);
		for (Map.Entry<EnergyResourceType, Double> entry : eKPI21.entrySet()) {
			EnergyResourceType key = entry.getKey();
			Double value = entry.getValue();

			switch(key) {
			case CO2: assertTrue(""+value,value > 10660020.20 && value < 10660020.30);
				break;
			case SO2: assertTrue(""+value, value > 18746.80 && value < 18746.81);
				break;
			case NOX: assertTrue(""+value, value > 4697.00 && value < 4697.01);
				break;
			default: throw new AssertionError();
			}
		}
	}

	@Test
	public void testCalculateSocioCulturalRoomTemperatureValue() throws EnergyKeyPerformanceIndicatorException {
		MeasuredUnit overrun 	= 	EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureOverrun(operativeUnits.get(0), setpointUnits.get(0));
		MeasuredUnit underrun 	= 	EnergyKeyPerformanceIndicatorCalculator.calculateTemperatureUnderrun(operativeUnits.get(0), setpointUnits.get(0));
		System.out.println(overrun.getValues());
		System.out.println(underrun.getValues());
	}
	
	@Test
	public void testCalculateInvestmentCostsOfBuildingElement() {
		final double costsPerSquareMeter	= 6.5;
		final double area					= 62.5;
		double result 						= EnergyKeyPerformanceIndicatorCalculator.calculateInvestmentCostsOfBuildingElement(costsPerSquareMeter, area);
		assertTrue(result == (costsPerSquareMeter * area));
	}

	
	@Test
	public void testCalculateEnergyRelatedOperationalCosts() {
		
		String idHeat = "_8ai4wOrbEeK2gM1Azt9urQ"; //Heizstoff = Nah/fernwärme
		String idCool = "_41caUOrbEeK2gM1Azt9urQ"; //Kühlstoff = Strom
		
		EnergyKeyPerformanceIndicators eKPIs = new EnergyKeyPerformanceIndicators();
		
		eKPIs.setFinalEnergyHeating(219171);
		eKPIs.setFinalEnergyCooling(18004);
		
		eKPIs.setYears(30);
		eKPIs.setPriceIncrease(2);
		eKPIs.setDiscountRate(5.5);
		
		List<String> combustibleIds = new ArrayList<String>();
		combustibleIds.add(idHeat); 
		combustibleIds.add(idCool); 
		eKPIs.setCombustibleIds(combustibleIds);
		
		Map<String, Double> mapHeating = new HashMap<String,Double>(); 
		Map<String, Double> mapCooling = new HashMap<String,Double>(); 
		
		mapHeating.put(idHeat, 100.0);
		mapHeating.put(idCool, 0.0);
		
		mapCooling.put(idHeat, 0.0);
		mapCooling.put(idCool, 100.0);
		
		
		eKPIs.setPercentagePerHeatingCombustible(mapHeating );
		eKPIs.setPercentagePerCoolingCombustible(mapCooling );
		
		List<Combustible> allAvailableCombustibles = getAllAvailableCombustible();
		
		// result should be 328055
		double result = EnergyKeyPerformanceIndicatorCalculator.calculateEnergyRelatedOperationalCosts(allAvailableCombustibles, eKPIs);		
	}
	
	@Test
	public void testCalculateNonEnergyRelatedOperationalCosts() {	
		EnergyKeyPerformanceIndicators eKPIs = new EnergyKeyPerformanceIndicators();
		
		//eKPIs.setFinalEnergyHeating(219171);
		//eKPIs.setFinalEnergyCooling(18004);
		
		eKPIs.setYears(30);
		eKPIs.setPriceIncrease(2);
		eKPIs.setDiscountRate(5.5);
		
		eKPIs.setWindowArea(270.63);
		eKPIs.setWindowCleaningTimesPerYear(2);
		eKPIs.setWindowHourlyRate(15);
		eKPIs.setWindowPerformanceValue(0.5);
		
		eKPIs.setBuildingShellArea(5854.26);
		eKPIs.setBuildingShellCleaningTimesPerYear(1);
		eKPIs.setBuildingShellHourlyRate(15);
		eKPIs.setBuildingShellPerformanceValue(0.25);
		
		
		eKPIs.setInvestmentCosts(334977);
		eKPIs.setMaintenancePercentage(1.1);		

		// result should be 550928
		double result =EnergyKeyPerformanceIndicatorCalculator.calculateNonEnergyRelatedOperationalCosts(eKPIs);
		System.out.println(result);
		
		
	}
	
	
	/**
	 * Reads configured combustibles and theirs properties out of XML file and
	 * and returns them in a list.	 
	 * @return List of combustibles 
	 */
	private List<Combustible> getAllAvailableCombustible() {
		File combustibleFile = new File(COMBUSTIBLES_PATH);
		List<Combustible> list = new ArrayList<Combustible>();		
		if(combustibleFile.exists()) {
			try {
				EList<Combustible> combustibles = CombustibleContainerDeserializer.deserialize(COMBUSTIBLES_PATH);
				
				for(Combustible c : combustibles) {
					list.add(c);
				}				
			} catch (IOException io) {
				System.out.println(io.getMessage());
			}
		}
		
		return list;
		
	}
}
