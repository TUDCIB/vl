package de.tudresden.bau.cib.vl.core.simulation.energy.postprocessing;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Months;
import org.joda.time.ReadablePeriod;
import org.joda.time.Weeks;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.processing.PostProcessingEngine;

@RunWith(value=Parameterized.class)
public class ParameterizedPostProcessingEngineTest {
	
	long millisOfHour = TimeUnit.HOURS.toMillis(1);
	
	private static NandradPostProcessingInputStream streamOperative;
	private static final String OPERATIVE_TEMPERATURES_PATH = "D:\\Nutzer\\ken\\data\\VirtualLaboratory\\temp\\1_1372939124939\\project\\HESMOS_Schule-A1_2ndLSB\\results\\Zones_OperativeTemperature.d6o";
	
	private List<MeasuredUnit> operativeUnits;
	
	private ReadablePeriod period;
	

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
		
	 
	public ParameterizedPostProcessingEngineTest(ReadablePeriod period) {
		this.period = period;
	}
	
	@Parameters
	public static Collection<ReadablePeriod[]> data() {
		final ReadablePeriod TIME_STEP_MONTHLY 	= Months.ONE; 				// 1000* 60 * 60 * 24 * 30 | monthly
		final ReadablePeriod TIME_STEP_WEEKLY 	= Weeks.ONE;			 	// 1000 * 60 * 60 * 24 * 7 | weekly
		final ReadablePeriod TIME_STEP_DAILY 	= Days.ONE; 				// 1000 * 60 * 60 * 24 | daily
		final ReadablePeriod TIME_STEP_HOURLY 	= Hours.ONE; 				// 1000 * 60 * 60 hourly
		ReadablePeriod[][] data 				= new ReadablePeriod[][] { 
				{TIME_STEP_MONTHLY},
				{TIME_STEP_WEEKLY}, 
				{TIME_STEP_DAILY}, 
				{TIME_STEP_HOURLY} 
				};
		return Arrays.asList(data);
	}
	
//	@Test
//	@Ignore
//	public void testAggregateValuesByTimeStep() {
//		for(MeasuredUnit unit : operativeUnits) {
//			TreeMap<Long,Double> valuesBeforeAggregation = unit.getValues();
//			System.out.println("NUMBER OF VALUES BEFORE AGGREGATION: "+valuesBeforeAggregation.size());
//			
//			MeasuredUnit newUnit = PostProcessingEngine.aggregateValuesByTimeInterval(unit, timeInMillis);
//			TreeMap<Long,Double> valuesAfterAggregation = newUnit.getValues();
//			System.out.println("NUMBER OF VALUES AFTER AGGREGATION: "+valuesAfterAggregation.size());
//		}
//	}
	
	@Test
	@Parameters
	public void testAggregateValuesByPeriod() {
		for(MeasuredUnit unit : operativeUnits) {
			TreeMap<Long,Double> valuesBeforeAggregation = unit.getValues();
			System.out.println("NUMBER OF VALUES BEFORE AGGREGATION: "+valuesBeforeAggregation.size());
			
			PostProcessingEngine.separatePerPeriodBySearchingFirstValueOfPeriod(unit, period);
			TreeMap<Long,Double> valuesAfterAggregation = unit.getValues();
			System.out.println("NUMBER OF VALUES AFTER AGGREGATION: "+valuesAfterAggregation.size());
		}
	}

}
