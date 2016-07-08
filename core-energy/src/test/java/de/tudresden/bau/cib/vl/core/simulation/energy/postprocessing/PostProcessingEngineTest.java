package de.tudresden.bau.cib.vl.core.simulation.energy.postprocessing;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.processing.PostProcessingEngine;

public class PostProcessingEngineTest {
	
	long millisOfHour = TimeUnit.HOURS.toMillis(1);
	
	private static NandradPostProcessingInputStream streamOperative;
	private static final String OPERATIVE_TEMPERATURES_PATH = "test/resources/nandrad/building/Zones_HeatingGains.d6o";
	
	private List<MeasuredUnit> operativeUnits;
	

	@Before
	public void setUp() throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		streamOperative = new NandradPostProcessingInputStream(0,OPERATIVE_TEMPERATURES_PATH, 2012);
		Future<List<MeasuredUnit>> future = executor.submit(streamOperative);
		
		operativeUnits 	= 	future.get();
		
		assertNotNull(operativeUnits);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}

	
	@Test
	public void testCreateSumOfValuesPerMonth() {
		for(MeasuredUnit unit : operativeUnits) {
			System.out.println("ID: "+unit.getId());
			TreeMap<Long,Double> valuesBeforeAggregation = unit.getValues();
			System.out.println("NUMBER OF VALUES BEFORE AGGREGATION: "+valuesBeforeAggregation.size());
			
			PostProcessingEngine.addUpValuesPerMonth(unit);
			TreeMap<Long,Double> valuesAfterAggregation = unit.getValues();
			System.out.println("NUMBER OF VALUES AFTER AGGREGATION: "+valuesAfterAggregation.size());
			System.out.println("VALUES: "+valuesAfterAggregation);
		}
	}
	
	@Test
	public void testCreateSumOfValuesPerWeek() {
		for(MeasuredUnit unit : operativeUnits) {
			TreeMap<Long,Double> valuesBeforeAggregation = unit.getValues();
			System.out.println("NUMBER OF VALUES BEFORE AGGREGATION: "+valuesBeforeAggregation.size());
			
			PostProcessingEngine.addUpValuesPerWeek(unit);
			TreeMap<Long,Double> valuesAfterAggregation = unit.getValues();
			System.out.println("NUMBER OF VALUES AFTER AGGREGATION: "+valuesAfterAggregation.size());
			System.out.println("VALUES: "+valuesAfterAggregation);
		}
	}
	
	@Test
	public void testCreateSumOfValuesPerDay() {
		for(MeasuredUnit unit : operativeUnits) {
			TreeMap<Long,Double> valuesBeforeAggregation = unit.getValues();
			System.out.println("NUMBER OF VALUES BEFORE AGGREGATION: "+valuesBeforeAggregation.size());
			
			PostProcessingEngine.addUpValuesPerDay(unit);
			TreeMap<Long,Double> valuesAfterAggregation = unit.getValues();
			System.out.println("NUMBER OF VALUES AFTER AGGREGATION: "+valuesAfterAggregation.size());
//			System.out.println("VALUES: "+valuesAfterAggregation);
		}
	}

}

