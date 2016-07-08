package de.tudresden.bau.cib.vl.core.model.processing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.ReadablePeriod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.time.DayHourTimePeriod;
import de.tudresden.bau.cib.vl.core.model.time.HolidayTimePeriod;




/**
 * Post-processing for {@link MeasuredUnit} to sum values or calculate maximum, minimum etc.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 * @author Meike Zehlike
 *
 */
public class PostProcessingEngine {
	
	private final static Long oneHourInMilliSeconds = new Long(3600000);
	
	private static final Logger LOG = LoggerFactory.getLogger(PostProcessingEngine.class);
	
	
	private PostProcessingEngine() {
		throw new AssertionError();
	}
	
	/**
	 * get all values of all MeasuredUnits between start time and end time
	 * */
	public static LinkedList<TreeMap<Long, Double>> searchValuesBetween(
			Date start, Date end, List<MeasuredUnit> units) {
		LinkedList<TreeMap<Long, Double>> ret = new LinkedList<TreeMap<Long, Double>>();		

		for (MeasuredUnit measuredUnit : units) {
			TreeMap<Long, Double> values = searchValuesBetween(start, end, measuredUnit);
			ret.add(values);
		}
		return ret;
	}
	
	/**
	 * Get all values of one unit between start time and end time
	 * 
	 * @param start
	 * @param end
	 * @param unit
	 * @return
	 */
	public static TreeMap<Long, Double> searchValuesBetween(Date start, Date end, MeasuredUnit unit) {
		TreeMap<Long, Double> values = new TreeMap<Long, Double>();
		Date counter = new Date(start.getTime());
		while (counter.before(end)) {
			Double value = unit.getValues().get(counter.getTime());
			if(value != null) {
				values.put(counter.getTime(), value);
			} else {
				LOG.trace("Can't find value for time: {} in milliseconds: {}", new Object[]{counter, counter.getTime()});
			}
			counter.setTime(counter.getTime() + oneHourInMilliSeconds);
		}
		return values;
	}

	/**
	 * get all values between start time and end time, but only of MeasuredUnits
	 * selected from list ids
	 * */
	public static LinkedList<TreeMap<Long, Double>> searchValuesBetween(
			Date start, Date end, List<MeasuredUnit> list,
			LinkedList<String> ids) {
		LinkedList<TreeMap<Long, Double>> ret = new LinkedList<TreeMap<Long, Double>>();

		for (String string : ids) {
			for (MeasuredUnit measuredUnit : list) {
				if (measuredUnit.getId().equals(string)) {
					TreeMap<Long, Double> values = new TreeMap<Long, Double>();
					Long oneHourInMilliSeconds = new Long(3600000);

					Date counter = (Date) start.clone();
					while (counter.before(end)) {
						values.put(counter.getTime(), measuredUnit.getValues()
								.get(counter.getTime()));
						counter.setTime(counter.getTime()
								+ oneHourInMilliSeconds);
					}
					ret.add(values);
				}
			}
		}

		return ret;
	}

	/**
	 * returns a list of MeasuredUnit with the maximum value at pointInTime
	 * (has to be a list, in case there are several units with the same max value)
	 * 
	 * */

	public static ArrayList<MeasuredUnit> getUnitWithMaxValue(Date pointInTime,
			List<MeasuredUnit> list) {
		ArrayList<MeasuredUnit> ret = new ArrayList<MeasuredUnit>();
		Double maxValueSoFar = new Double(-Double.MAX_VALUE);

		for (MeasuredUnit unit : list) {
			Double current = new Double(unit.getValues().get(
					pointInTime.getTime()));
			if (current > maxValueSoFar) {
				ret.clear();
				ret.add(unit);
				maxValueSoFar = unit.getValues().get(pointInTime.getTime());
				assert(maxValueSoFar == current);
			} else {
				if (current.equals(maxValueSoFar)) {
					ret.add(unit);
				}

				// if current < maxValueSoFar do nothing
			}
		}

		return ret;
	}

	/**
	 * returns a list of MeasuredUnits with the minimum value at pointInTime
	 * 
	 * */

	public static ArrayList<MeasuredUnit> getUnitWithMinValue(Date pointInTime,
			List<MeasuredUnit> list) {
		ArrayList<MeasuredUnit> ret = new ArrayList<MeasuredUnit>();
		Double minValueSoFar = new Double(Double.MAX_VALUE);

		for (MeasuredUnit unit : list) {
			Double current = new Double(unit.getValues().get(
					pointInTime.getTime()));
			if (current < minValueSoFar) {
				ret.clear();
				ret.add(unit);
				minValueSoFar = unit.getValues().get(pointInTime.getTime());
			} else {
				if (current.equals(minValueSoFar)) {
					ret.add(unit);
				}

				// if current > minValueSoFar do nothing
			}
		}

		return ret;
	}
	
	/**
	 * Removes values from unit which are not defined in a daily interval. Start time and end time are in inclusive range.
	 * @param unit Measurement unit with time-value pair.
	 * @param interval Daily interval. Start and end time are interpreted as inclusive range 
	 * (includes the first and last number and all numbers in between will not be removed). 
	 *  
	 * @return The measurement unit with all values which are not removed.
	 */
	public static MeasuredUnit removeValuesNotInDailyInterval(MeasuredUnit unit, DayHourTimePeriod interval) {
		TreeMap<Long, Double> values = unit.getValues();
		Iterator<Entry<Long,Double>> it = values.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Long,Double> entry = it.next();
			long totalMilliseconds = entry.getKey(); // milliseconds since 1970
			DateTime current = new DateTime(totalMilliseconds);
			Set<Integer> acceptedDays = interval.getDays();
			Set<Integer> acceptedHours = interval.getHours();
			
			if(!acceptedDays.contains(current.getDayOfWeek())) {
				LOG.trace("Out of day range: {} ", current);
				it.remove();
				continue;
			}	
			if(!acceptedHours.contains(current.getHourOfDay())) {
				LOG.trace("Out of hour range: {}", current);
				it.remove();
			}
		}
		return unit;
	}
	
	/**
	 * Removes values from unit which are defined in a time period. Start time and end time are in inclusive range.
	 * @param unit Measurement unit with time-value pair.
	 * @param period Start and end time are interpreted as inclusive range 
	 * (includes the first and last number and all numbers in between will not be removed). 
	 *  
	 * @return The measurement unit with all values which are not removed.
	 */
	public static MeasuredUnit removeValuesByTimePeriod(MeasuredUnit unit, DayHourTimePeriod period) {
		TreeMap<Long, Double> values = unit.getValues();
		Iterator<Entry<Long,Double>> it = values.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Long,Double> entry = it.next();
			long totalMilliseconds = entry.getKey(); // milliseconds since 1970
			DateTime current = new DateTime(totalMilliseconds);
			Set<Integer> acceptedDays = period.getDays();
			Set<Integer> acceptedHours = period.getHours();
			
			if(acceptedDays.contains(current.getDayOfWeek())) {
				it.remove();
				continue;
			}	
			if(acceptedHours.contains(current.getHourOfDay())) {
				it.remove();
			}
		}
		return unit;
	}
	
	/**
	 * Removes values from unit which are defined in a holiday time period. Start time and end time are in inclusive range.
	 * @param unit Measurement unit with time-value pair.
	 * @param period Start and end time are interpreted as inclusive range 
	 * (includes the first and last number and all numbers in between will not be removed). 
	 *  
	 * @return The measurement unit with all values which are not removed.
	 */
	public static MeasuredUnit removeValuesByHolidayTimePeriod(MeasuredUnit unit, HolidayTimePeriod period) {
		TreeMap<Long, Double> values = unit.getValues();
		Iterator<Entry<Long,Double>> it = values.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Long,Double> entry = it.next();
			long totalMilliseconds = entry.getKey(); // milliseconds since 1970
			
			if(!period.isInTimeRanges(totalMilliseconds)) {
				it.remove();
			}
		}
		return unit;
	}
	
	/**
	 * Search the next value which is not over a threshold or under the negative threshold.
	 * @param currentKey
	 * @param map
	 * @param threshold
	 * @return A value within the positive and negative threshold.
	 */
	private static double searchNextValue(long currentKey, TreeMap<Long, Double> map, int threshold) {
		double value = 0.0;
		Long nextKey = map.higherKey(currentKey);
		if(nextKey == null) {
			Entry<Long,Double> lowerEntry = map.lowerEntry(currentKey);
			if(lowerEntry == null) {
				return value;
			} else {
				value = lowerEntry.getValue();
			}
		} else {
			Double nextValue = map.get(nextKey);
			if( (nextValue > threshold) || (nextValue < (-threshold)) ) {
				value = searchNextValue(nextKey, map, threshold);
			} else {
				value = nextValue;
			}
		}
		return value;
	}
	
	/**
	 * Smooth values inside the map because some values can be erroneous.
	 * @param unit
	 * @param threshold To check the values.
	 * @return
	 */
	public static TreeMap<Long, Double> smoothValues(MeasuredUnit unit, int threshold) {
		TreeMap<Long, Double> smoothedValues = new TreeMap<Long, Double>();
		TreeMap<Long, Double> values = unit.getValues();
		Double previousValue = 0.0;
		Double nextValue = 0.0;
		for(Map.Entry<Long, Double> entry : values.entrySet()) {
			Long key = entry.getKey();
				
			Double value = entry.getValue();
				
			if( (value > threshold) || (value < (-threshold)) ) {
				nextValue = searchNextValue(key, values, threshold);
					
				value = (previousValue + nextValue) / 2;
				entry.setValue(value);
			}
			previousValue = value;
		}
		return smoothedValues;
	}
	
//	public static TreeMap<Long, Double> meanValueSmoothing(MeasuredUnit unit) {
//		TreeMap<Long, Double> smoothedValues = new TreeMap<Long, Double>();
//		TreeMap<Long, Double> values = unit.getValues();
//		Double previousValue = 0.0;
//		Double nextValue = 0.0;
//		
////		(1) find a threshold
//		
////		(2) now smooth it
//		for(Map.Entry<Long, Double> entry : values.entrySet()) {
//			Long key = entry.getKey();
//				
//			Double value = entry.getValue();
//				
//			if( (value > threshold) || (value < (-threshold)) ) {
//				nextValue = searchNextValue(key, values, threshold);
//					
//				value = (previousValue + nextValue) / 2;
//				entry.setValue(value);
//			}
//			previousValue = value;
//		}
//		return smoothedValues;
//	}
	
	/**
	 * Determines the minimum value of measurements.
	 * @param unit
	 * @return The minimum value.
	 */
	public static Double getMinimumValue(MeasuredUnit unit) {
		TreeMap<Long, Double> values = unit.getValues();
		if(values != null) {
			Collection<Double> collValues = values.values();
			return (collValues != null && collValues.size() > 0)  ? Collections.min(collValues) : null;
		}
		return null;
	}

	/**
	 * Determines the maximum value of measurements.
	 * @param unit
	 * @return The maximum value.
	 */
	public static Double getMaximumValue(MeasuredUnit unit) {
		TreeMap<Long, Double> values = unit.getValues();
		if(values != null) {
			Collection<Double> collValues = values.values();
			return (collValues != null && collValues.size() > 0) ? Collections.max(collValues) : null;
		}
		return null;
	}
	
	/**
	 * @deprecated JODA-TIME PROVIDES MORE DETAILED PERIODS
	 * Search the first value in a given period and add it to a map.
	 * 
	 * For example: If weekly values are needed for a whole year the output are 52-53 values.
	 * @param 	unit
	 * @param 	timePeriodInMillis All periods are possible and depends on the start time of the values of the unit, 
	 * 			e.g. weekly values are returned when specifying a period of (1000*60*60*24*7)
	 * @return
	 */
	@Deprecated
	public static MeasuredUnit aggregateValuesByTimeInterval(MeasuredUnit unit, long timePeriodInMillis) {
		TreeMap<Long,Double> aggregatedValueMap = new TreeMap<Long, Double>();
		TreeMap<Long,Double> values	= unit.getValues();
		int i = 0;		
		DateTime nextTimeIntervalThreshold = null;
		for (Map.Entry<Long, Double> entry : values.entrySet()) {
			Long key = entry.getKey();
			Double value = entry.getValue();
//			take first value
			if(i == 0) { // initialization
				aggregatedValueMap.put(key, value);
				DateTime startTime = new DateTime(key);
				nextTimeIntervalThreshold = startTime.plus(timePeriodInMillis);
			} else {			
				DateTime time = new DateTime(key);
	
				if(time.getMillis() >= nextTimeIntervalThreshold.getMillis()) { // take it
					aggregatedValueMap.put(key, value);
					nextTimeIntervalThreshold = nextTimeIntervalThreshold.plus(timePeriodInMillis);
				}
			}
			
			i++;
		}
		unit.setValues(aggregatedValueMap);
		return unit;
	}
	
	/**
	 * Search the first value in a given period and add it to a map.
	 * 
	 * For example: If weekly values are needed for a whole year the output are 52-53 values or if a period is monthly then it will be 12 values.
	 * @param 	unit
	 * @param 	timePeriodInMillis All periods are possible and depends on the start time of the values of the unit, 
	 * 			e.g. weekly values are returned when specifying a period org.joda.time.Weeks.ONE
	 * @return
	 */
	public static void separatePerPeriodBySearchingFirstValueOfPeriod(MeasuredUnit unit, ReadablePeriod period) {
		TreeMap<Long,Double> aggregatedValueMap = new TreeMap<Long, Double>();
		TreeMap<Long,Double> values	= unit.getValues();
		int i = 0;		
		DateTime nextTimeIntervalThreshold = null;
		for (Map.Entry<Long, Double> entry : values.entrySet()) {
			Long key = entry.getKey();
			Double value = entry.getValue();

//			take first value
			if(i == 0) { // initialization
				aggregatedValueMap.put(key, value);
				DateTime startTime = new DateTime(key);
				nextTimeIntervalThreshold = startTime.plus(period);
			} else {			
				DateTime time = new DateTime(key);
	
				if(time.getMillis() >= nextTimeIntervalThreshold.getMillis()) { // take it
					aggregatedValueMap.put(key, value);
					nextTimeIntervalThreshold = nextTimeIntervalThreshold.plus(period); // jump to next period
				}
			}
			
			i++;
		}		
		unit.setValues(aggregatedValueMap);
	}
	
	/**
	 * If hourly values are given than these will be add up for each month.
	 * @param unit
	 * @return Same unit with sum of values per month.
	 */
	public static void addUpValuesPerMonth(MeasuredUnit unit) {
		TreeMap<Long,Double> aggregatedValueMap = new TreeMap<Long, Double>();
		TreeMap<Long,Double> values	= unit.getValues();
		
		DateTime referenceTime = null;
		Double sumOfMonth = new Double(0.0);
		int i = 0;
		for (Map.Entry<Long, Double> entry : values.entrySet()) {
			Long key = entry.getKey();
			Double value = entry.getValue();
			
			DateTime time = new DateTime(key);	
			
			if(i == 0) referenceTime = time; // initialization
			int month = time.getMonthOfYear();
			if(referenceTime.getMonthOfYear() == month) {
				sumOfMonth += value;
			} else {
				aggregatedValueMap.put(referenceTime.getMillis(), sumOfMonth); // add it to the map
				referenceTime = time;
				sumOfMonth = value; 	// initialize new value for month			
			}
			
			i++;
			if(i == values.size()) { // add last value
				aggregatedValueMap.put(referenceTime.getMillis(), sumOfMonth);
			}
		}	
		unit.setValues(aggregatedValueMap);
	}
	
	/**
	 * If hourly values are given than these will be add up for each week.
	 * @param unit
	 * @return Same unit with sum of values per week.
	 */
	public static void addUpValuesPerWeek(MeasuredUnit unit) {
		TreeMap<Long,Double> aggregatedValueMap = new TreeMap<Long, Double>();
		TreeMap<Long,Double> values	= unit.getValues();
		
		DateTime previousTime = null;
		Double sumOfWeek = new Double(0.0);
		int i = 0;
		for (Map.Entry<Long, Double> entry : values.entrySet()) {
			Long key = entry.getKey();
			Double value = entry.getValue();
			
			DateTime time = new DateTime(key);	
			if(i == 0) previousTime = time; // initialization
			int week = time.getWeekOfWeekyear();
			if(previousTime.getWeekOfWeekyear() == week) {
				sumOfWeek += value;
			} else {
				aggregatedValueMap.put(previousTime.getMillis(), sumOfWeek); // add it to the map
				previousTime = time;
				sumOfWeek = value; 	// initialize new value
				
			}
			
			i++;
			if(i == values.size()) {
				aggregatedValueMap.put(previousTime.getMillis(), sumOfWeek);
			}
		}		
		unit.setValues(aggregatedValueMap);
	}
	
	/**
	 * Aggregate all values of all given units by adding then up in a new unit.
	 * @param units
	 * @return A new unit with aggregated values.
	 */
	public static MeasuredUnit aggregateUnits(List<MeasuredUnit> units) {
		MeasuredUnit aggregatedUnit 		= null;
		if(units != null) aggregatedUnit 	= new MeasuredUnit(null);
		String label 						= units.get(0).getLabel();
		String quantity 					= units.get(0).getQuantity();
		int resultCode						= units.get(0).getResultCode();
		int startYear						= units.get(0).getStartYear();
		String timeUnit						= units.get(0).getTimeUnit();
		String valueUnit					= units.get(0).getValueUnit();
		
		
		TreeMap<Long, Double> newValues		= new TreeMap<Long, Double>();
		for(MeasuredUnit unit : units) {
			if(!unit.getLabel().equals(label) || !unit.getQuantity().equals(quantity) 
					|| unit.getResultCode() != resultCode || unit.getStartYear() != startYear 
					|| !unit.getTimeUnit().equals(timeUnit) || !unit.getValueUnit().equals(valueUnit)) {
//				units should be equal
				throw new IllegalArgumentException("units are not equal");
			}
			TreeMap<Long, Double> values = unit.getValues();
			for (Map.Entry<Long, Double> entry : values.entrySet()) {
				Long key = entry.getKey();
				Double value = entry.getValue();

				Double currentVal = newValues.get(key);
				
				newValues.put(key, (currentVal != null ? (currentVal+value) : value));
			}		
		}
		aggregatedUnit.setValues(newValues);
		aggregatedUnit.setAggregated(true);
		aggregatedUnit.setLabel(label);
		aggregatedUnit.setQuantity(quantity);
		aggregatedUnit.setResultCode(resultCode);
		aggregatedUnit.setStartYear(startYear);
		aggregatedUnit.setTimeUnit(timeUnit);
		aggregatedUnit.setValueUnit(valueUnit);
		return aggregatedUnit;
	}
	
	/**
	 * Aggregates units by separating them by their result code.
	 * @param units
	 * @return
	 */
	public static Set<MeasuredUnit> aggregateMixedUnits(Set<MeasuredUnit> units) {
		Set<MeasuredUnit> result = null;
		Map<Integer, Set<MeasuredUnit>> mixedMap = new HashMap<Integer, Set<MeasuredUnit>>();
		
//		(1) separate units by result code
		for(MeasuredUnit unit : units) {
			Integer resultCode = unit.getResultCode();
			if(mixedMap.containsKey(resultCode)) {
				Set<MeasuredUnit> unitsOfResultCode = mixedMap.get(resultCode);
				unitsOfResultCode.add(unit);
			} else {
				Set<MeasuredUnit> unitsOfResultCode = new HashSet<MeasuredUnit>();
				unitsOfResultCode.add(unit);
				mixedMap.put(resultCode, unitsOfResultCode);
			}
		}
		
//		(2) aggregate the units with same result code
		for (Map.Entry<Integer, Set<MeasuredUnit>> entry : mixedMap.entrySet()) {
			Set<MeasuredUnit> value = entry.getValue();
			MeasuredUnit aggregatedUnit = aggregateUnits(new ArrayList<MeasuredUnit>(value));
			if(result == null) result = new HashSet<MeasuredUnit>();
			result.add(aggregatedUnit);
		}	
		return result;
	}
	
	/**
	 * Converts the values of the unit. For example, if a unit with Watt per hour is given and the conversionFactor is 1000
	 * it converts the values to KiloWatt (kW).
	 * @param unit The unit where the conversion factor will be applied
	 * @param conversionFactor Factor divides the value.
	 * @return The given unit with converted values.
	 */
	public static void convertValuesOfUnit(MeasuredUnit unit, float conversionFactor) {
		TreeMap<Long,Double> values = unit.getValues();
		TreeMap<Long, Double> newValues = new TreeMap<Long, Double>();
		for (Map.Entry<Long, Double> entry : values.entrySet()) {
			Long key = entry.getKey();
			Double value = entry.getValue();

			value /= conversionFactor;
			newValues.put(key, value);
		}
		unit.setValues(newValues);
	}
	
	/**
	 * Add up all values of the given unit.
	 * @param unit
	 * @return The sum of all values.
	 */
	public static Double createSumOfValues(MeasuredUnit unit) {
		Double result = new Double(0.0);
		TreeMap<Long, Double> values = unit.getValues();
		for (Map.Entry<Long, Double> entry : values.entrySet()) {
			Double value = entry.getValue();
			result += value;
		}
		return result;
	}
	
	/**
	 * If hourly values are given than these will be add up for each day.
	 * @param unit 
	 * @return Same unit with sum of values per day.
	 */
	public static void addUpValuesPerDay(MeasuredUnit unit) {
		TreeMap<Long,Double> aggregatedValueMap = new TreeMap<Long, Double>();
		TreeMap<Long,Double> values	= unit.getValues();
		
		DateTime previousTime = null;
		Double sumOfDay = new Double(0.0);
		int i = 0;
		for (Map.Entry<Long, Double> entry : values.entrySet()) {
			Long key = entry.getKey();
			Double value = entry.getValue();
			
			DateTime time = new DateTime(key);	
			if(i == 0) previousTime = time; // initialization
			int dayOfYear = time.getDayOfYear();
			if(previousTime.getDayOfYear() == dayOfYear) {
				sumOfDay += value;
			} else {
				aggregatedValueMap.put(previousTime.getMillis(), sumOfDay); // add it to the map
				previousTime = time;
				sumOfDay = value; 	// initialize new value
			}
			
			i++;
			if(i == values.size()) {
				aggregatedValueMap.put(previousTime.getMillis(), sumOfDay);
			}
		}		
		unit.setValues(aggregatedValueMap);
	}

	/**
	 * calculates a daily average of every measured unit values
	 * */
	
	public static LinkedList<Double> getDailyAverageOfAllUnits(Date start,
			Date end, List<MeasuredUnit> list) {
		LinkedList<Double> ret = new LinkedList<Double>();

		for (MeasuredUnit measuredUnit : list) {
			Double value = new Double(0);
			Date counter = new Date(start.getTime());
			while (counter.before(end)) {
				for (int dailyCounter = 0; dailyCounter < 24; dailyCounter++) {
					if (measuredUnit.getValues().get(counter.getTime()) == null) {
						ret.add(value);
						return ret;
					}
					value += measuredUnit.getValues().get(counter.getTime());
					counter.setTime(counter.getTime() + oneHourInMilliSeconds);
				}
				value = value / 24;
				ret.add(value);
			}
		}
		return ret;
	}
}
