package de.tudresden.bau.cib.vl.core.model.processing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.time.DayHourTimePeriod;

public class PostProcessingEngineTest {
	
	long millisOfHour = TimeUnit.HOURS.toMillis(1);

	
	private MeasuredUnit createMeasuredUnitWithValuesOfOneFullDay() {
		MeasuredUnit unit = new MeasuredUnit(UUID.randomUUID().toString());
		TreeMap<Long, Double> values = new TreeMap<Long, Double>();
		for(int i = 0; i < 24; i++) {
			values.put(millisOfHour*i, Math.random()*10);
		}
		assertNotNull(values);
		assertTrue(values.size() > 0);
		unit.setValues(values);
		System.out.println("Setup values: "+values);
		return unit;
	}
	
	private MeasuredUnit createMeasuredUnitWithMinuteValuesForJanuraryInYear2013() {
		MeasuredUnit unit = new MeasuredUnit(UUID.randomUUID().toString());
		TreeMap<Long, Double> values = new TreeMap<Long, Double>();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, 0);
		for(int i = 1; i <= 31; i++) {
			cal.set(Calendar.DAY_OF_MONTH, i);
			for(int j = 1; j <= 24; j++) {
				cal.set(Calendar.HOUR_OF_DAY, j);
				for(int k = 1; k <= 60; k++) {
					cal.set(Calendar.MINUTE, k);
					values.put(cal.getTimeInMillis(), Math.random()*10);
				}
				
			}
		}
		assertTrue(values.size() == 44640);
		unit.setValues(values);
		return unit;
	}
	
	private MeasuredUnit createMeasuredUnitWithMinuteValuesForWholeYear2013() {
		MeasuredUnit unit = new MeasuredUnit(UUID.randomUUID().toString());
		TreeMap<Long, Double> values = new TreeMap<Long, Double>();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		for(int m = 0; m < 12; m++) {
			cal.set(Calendar.MONTH, m);
			for(int i = 1; i <= 31; i++) {
				cal.set(Calendar.DAY_OF_MONTH, i);
				for(int j = 0; j < 24; j++) {
					cal.set(Calendar.HOUR_OF_DAY, j);
					for(int k = 0; k < 60; k++) {
						cal.set(Calendar.MINUTE, k);
//						for(int s = 0; s < 60; s++) {
//							cal.set(Calendar.SECOND, s);
							double randomValue = Math.random()*10;
							randomValue += Math.random()*5;
							values.put(cal.getTimeInMillis(), randomValue);
//						}
						
					}
					
				}
			}
		}
		unit.setValues(values);
		return unit;
	}

	@Test
	@Ignore
	public void testSearchValuesBetweenDateDateListOfMeasuredUnit() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testSearchValuesBetweenDateDateMeasuredUnit() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testSearchValuesBetweenDateDateListOfMeasuredUnitLinkedListOfString() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetUnitWithMaxValue() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetUnitWithMinValue() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRemoveWeekEndAndByDailyInterval_From_8h_To_18h_In_2013() {
		MeasuredUnit unit = createMeasuredUnitWithMinuteValuesForWholeYear2013();
		int numberOfValuesBefore = unit.getValues().size();
		System.out.println("Start testRemoveByDailyInterval_From_8h_To_18h_In_2013()");
		DayHourTimePeriod interval = new DayHourTimePeriod();
		interval.addAcceptedDays("1-5");
		interval.addAcceptedHours("8-18");
		unit = PostProcessingEngine.removeValuesNotInDailyInterval(unit, interval);
		TreeMap<Long,Double> values = unit.getValues();
		int numberOfValuesAfter = values.size();
		assertTrue(numberOfValuesBefore > numberOfValuesAfter);
		System.out.println(values.size());
//		assertTrue(values.toString(), values.size() == 5);
//		System.out.println("Values after removal: "+values);
	}
	
	@Test
	public void testRemoveByDailyInterval_From_6h_To_18h_In_January_2013() {
		MeasuredUnit unit = createMeasuredUnitWithMinuteValuesForJanuraryInYear2013();
		int numberOfValuesBefore = unit.getValues().size();

		System.out.println("Start testRemoveByDailyInterval_From_6h_To_18h_In_January_2013()");
		DayHourTimePeriod interval = new DayHourTimePeriod();
		interval.addAcceptedDays("1-7");
		interval.addAcceptedHours("6-18");
		unit = PostProcessingEngine.removeValuesNotInDailyInterval(unit, interval);
		TreeMap<Long,Double> values = unit.getValues();
		int numberOfValuesAfter = values.size();
		assertTrue(numberOfValuesBefore > numberOfValuesAfter);
		System.out.println(values.size());
//		assertTrue(values.toString(), values.size() == 5);
//		System.out.println("Values after removal: "+values);
	}
	
	@Test
	public void testByJoda() {
		MeasuredUnit unit = createMeasuredUnitWithMinuteValuesForJanuraryInYear2013();
		
		DayHourTimePeriod interval = new DayHourTimePeriod();
		interval.addAcceptedDays("1-5");
		interval.addAcceptedHours("6-17");
		
		unit = PostProcessingEngine.removeValuesNotInDailyInterval(unit, interval);
		TreeMap<Long,Double> values = unit.getValues();
		for(Long time : values.keySet()) {
			DateTime dt = new DateTime(time);
			assertTrue(interval.getDays().contains(dt.getDayOfWeek()));
			assertTrue(interval.getHours().contains(dt.getHourOfDay()));
//			System.out.println(new Date(time));
		}
	}

	@Test
	public void testRemoveByInterval_1h_to_5h() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		System.out.println("Monday > "+cal.getTime().getTime());
		
		MeasuredUnit unit = createMeasuredUnitWithValuesOfOneFullDay();
	
		System.out.println("Start testRemoveByInterval_1h_to_5h()");
		DayHourTimePeriod interval = new DayHourTimePeriod();
		interval.addAcceptedDays("1-7");
		interval.addAcceptedHours("1-5");
		unit = PostProcessingEngine.removeValuesNotInDailyInterval(unit, interval);
		TreeMap<Long,Double> values1 = unit.getValues();
		
		assertTrue(values1.toString(), values1.size() == 5);
		System.out.println("Values after removal: "+values1);
	}
	
	@Test
	public void testRemoveByInterval_0h_to_7h() {
		MeasuredUnit unit = createMeasuredUnitWithValuesOfOneFullDay();
		
		System.out.println("Start testRemoveByInterval_0h_to_7h()");
		DayHourTimePeriod interval = new DayHourTimePeriod();
		interval.addAcceptedDays("1-7");
		interval.addAcceptedHours("0-7");
		unit = PostProcessingEngine.removeValuesNotInDailyInterval(unit, interval);
		TreeMap<Long,Double> values = unit.getValues();
		assertTrue(values.toString(), values.size() == 8);
		System.out.println("Values after removal: "+values);
	}
	
	@Test
	public void testRemoveByInterval_0h_to_24h_Which_Means_That_Last_Value_Is_Removed() {
		MeasuredUnit unit = createMeasuredUnitWithValuesOfOneFullDay();
		
		System.out.println("Start testRemoveByInterval_0h_to_24h_Which_Means_That_Last_Value_Is_Removed()");
		DayHourTimePeriod interval = new DayHourTimePeriod();
		interval.addAcceptedDays("1-7");
		interval.addAcceptedHours("0-23");
		unit = PostProcessingEngine.removeValuesNotInDailyInterval(unit, interval);
		TreeMap<Long,Double> values = unit.getValues();
		assertTrue(""+values.size(), values.size() == 24); // 86400000 is not valid
		System.out.println("Values after removal: "+values);
	}

	@Test
	@Ignore
	public void testSmoothValues() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetMinimumValue() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetMaximumValue() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetValuesDependentOnTimeConstraints() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetDailyAverageOfAllUnits() {
		fail("Not yet implemented");
	}

}
