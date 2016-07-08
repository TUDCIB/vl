package de.tudresden.bau.cib.vl.core.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import org.apache.commons.lang.time.DateUtils;

/**
 * The Class MeasuredUnit.
 * 
 * @author Ken 
 * @author Meike 
 */
public class MeasuredUnit implements Cloneable, Comparable<MeasuredUnit> {
	
	public static final SimpleDateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public enum TIME_MEASURE {
		MIN, HOUR, DAY, WORKDAY, WEEK, MONTH, YEAR
	}
	
	/**
	 * Attention: this ID is not unique! This can be for example the IFC GlobalId but if there are multiple results for this GlobalId
	 * then there are also multiple {@link MeasuredUnit} with this identifier.
	 */
	private String id; //ID comes from line INDICES, each index is taken as ID for one unit
	private TreeMap<Long, Double> values;
	private String timeUnit; // 'h'	or	'd'
	private int startYear;
	private String valueUnit; 
	private String quantity;
	private boolean aggregated;
	private int resultCode = 0;
	private Map<String, List<String>> keyPerformanceIndicator = null;
	private final Double SECONDS_PER_DAY = new Double(24 * 60 * 60);
	/**
	 * Optional description of this measurement unit.
	 */
	private String label;
	
	private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	
	
	public MeasuredUnit(String id) {
		this.id 		= id;
		this.values 	= new TreeMap<Long, Double>();
		this.timeUnit 	= new String();
		this.startYear 	= new Integer(calendar.get(Calendar.YEAR));
		this.valueUnit 	= new String();
		this.quantity 	= new String();
		this.label 		= new String();
	}
	
	public int addValue (String time, String value) {
		try {
			Double v = new Double(value);
			Long k = convertDateToMilliseconds(time);
	
			if (!(this.values.put(k, v) == null)) {
				return 1;
			}
		} catch(NumberFormatException e) {
			return 1;
		}
		return 0;
	}
	
	public int addValue (long time, String value) {
		try {
			Double v = new Double(value);
	
			if (!(this.values.put(time, v) == null)) {
				return 1;
			}
		} catch(NumberFormatException e) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * method converts a given string "time" into the UTC from 01.01.1970 in
	 * milliseconds 
	 * 
	 * if this.timeUnit == d, time is of format xx.yy (e.g.: 13.5
	 * -> represents the 13.5th day of the year given in this.startYear) 
	 * 
	 * if this.timeUnit == h, time is of format xx (e.g.: 234 -> represents the
	 * 234th hour of the year given in this.startYear)
	 * 
	 * throws IllegalArgumentException if this.timeUnit is neither "d" nor "h"
	 * */

	public Long convertDateToMilliseconds(String time)
			throws IllegalArgumentException {

		Double t = new Double(time);
		Integer secondOfDay = new Integer(0);
		Integer dayOfYear = new Integer(0);

		try {
			if (timeUnit.equals("d")) {
				secondOfDay = (int) Math.round(SECONDS_PER_DAY
						* (t - t.intValue())); // 12.042 - 12 = 0.042 => which means the time of the day
				dayOfYear = t.intValue() + 1; // because the year starts with '0'
				
				int hour = secondOfDay / 3600;
				int minute = (secondOfDay / 60) % 60;
				int second = secondOfDay % 60;
				
				calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND, second);
				calendar.set(Calendar.MILLISECOND, 0);
				
				calendar = DateUtils.round(calendar, Calendar.SECOND);
				calendar = DateUtils.round(calendar, Calendar.MINUTE);
				calendar = DateUtils.round(calendar, Calendar.HOUR_OF_DAY);
			} else {
				if (timeUnit.equals("h")) {				
					dayOfYear = (t.intValue() / 24) + 1;

					calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
					calendar.set(Calendar.HOUR_OF_DAY, t.intValue() % 24);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.MILLISECOND, 0);

				}
			}
		} catch (Exception e) {
//			LOGGER.warn("timeUnit was neither _d_ nor _h_", e);
		}
		return Long.valueOf(calendar.getTimeInMillis());
	}
	
	@Override
	public int hashCode() {
		int hash = id != null ? id.hashCode() : 0; 
		return hash + resultCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		MeasuredUnit other = (MeasuredUnit) obj;
		return hashCode() == other.hashCode() && values.size() == other.values.size() && quantity.equalsIgnoreCase(other.quantity);
	}

	public TreeMap<Long, Double> getValues() {
		return values;
	}

	public void setValues(TreeMap<Long, Double> values) {
		this.values = new TreeMap<Long, Double>(values);
	}

	/**
	 * Attention: this ID is not unique! This can be for example the IFC GlobalId but if there are multiple results for this GlobalId
	 * then there are also multiple {@link MeasuredUnit} with this identifier.
	 * @return The identifier for example the IFC GlobalId.
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	/**
	 * @param timeUnit Currently (2013-05-02) "h" for hours and "d" for days are permitted.
	 */
	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
		calendar.set(Calendar.YEAR, startYear);
	}

	public String getValueUnit() {
		return valueUnit;
	}

	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public Map<String, List<String>> getKeyPerformanceIndicator() {
		return keyPerformanceIndicator;
	}

	public void setKeyPerformanceIndicator(Map<String, List<String>> keyPerformanceIndicator) {
		this.keyPerformanceIndicator = new HashMap<String, List<String>>(keyPerformanceIndicator);
	}
	
	@Override
	public String toString() {
		return "ID="+id + " Quantity="+quantity+" ResultCode="+resultCode+" StartYear="+startYear+" TimeUnit="+timeUnit + " Label="+label;
	}

	public final boolean isAggregated() {
		return aggregated;
	}

	public final void setAggregated(boolean aggregated) {
		this.aggregated = aggregated;
	}
	
	@Override
	public MeasuredUnit clone() {
		try {
			return (MeasuredUnit) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public int compareTo(MeasuredUnit other) {
		return id.compareTo(other.getId());
	}
}
