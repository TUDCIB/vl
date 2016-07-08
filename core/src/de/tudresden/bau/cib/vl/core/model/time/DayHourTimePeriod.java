package de.tudresden.bau.cib.vl.core.model.time;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;

/**
 * Time period for given accepted days and hours.
 * 
 * Example:
 * <p>
 * 		Days: 1=MONDAY, 2=TUESDAY, 3=WEDNESDAY, 4=THURSDAY, 5=FRIDAY
 * 		From 6 - 17
 * </p>
 * 
 * [Ken: Mir gefaellt diese Klasse noch nicht. Mit JODA time muss es doch moeglich sein etwas einfacheres zu erstellen]
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class DayHourTimePeriod implements TimePeriod {

	private Set<Integer> days; // 1-7
	private Set<Integer> hours; // 0-23
	
	
	public DayHourTimePeriod() {
		days = new TreeSet<Integer>();
		hours = new TreeSet<Integer>();
	}

	public void addAcceptedDay(Integer day) {
		days.add(day);
	}
	
	/**
	 * @param range Format: 1-5 means from Monday till Friday.
	 */
	public void addAcceptedDays(String range) {
		String[] time = range.split("-");
		int start = Integer.valueOf(time[0]);
		int end = Integer.valueOf(time[1]);		
		for(int i = start; i <= end; i++) {
			days.add(i);
		}
	}
	
	public void addAcceptedDays(Set<Integer> moreDays) {
		days.addAll(moreDays);
	}

	public void addAcceptedHour(Integer hour) {
		hours.add(hour);
	}
	
	/**
	 * @param range Format: 6-17 means from 6:00 till 17:59. Maximum is 23:59
	 */
	public void addAcceptedHours(String range) {
		String[] time = range.split("-");
		int start = Math.round(Float.valueOf(time[0]));
		int end = Math.round(Float.valueOf(time[1]));	
		for(int i = start; i <= end; i++) {
			hours.add(i);
		}
	}
	
	public void addAcceptedHours(Set<Integer> moreHours) {
		hours.addAll(moreHours);
	}
	
	public void setAcceptedDays(String range) {
		days = new TreeSet<Integer>();
		addAcceptedDays(range);
	}
	
	public void setAcceptedHours(String range) {
		hours = new TreeSet<Integer>();
		addAcceptedHours(range);
	}

	public Set<Integer> getDays() {
		return days;
	}

	public Set<Integer> getHours() {
		return hours;
	}
	
	public int getMinimumHour() {
		return Collections.min(hours);
	}
	
	public int getMinimumDay() {
		return Collections.min(days);
	}
	
	public int getMaximumHour() {
		return Collections.max(hours);
	}
	
	public int getMaximumDay() {
		return Collections.max(days);
	}

	@Override
	public boolean isInTimeRanges(long timeInMillis) {
		DateTime time = new DateTime(timeInMillis);	
		if(days.contains(time.getDayOfWeek())) {
			return true;
		}	
		if(hours.contains(time.getHourOfDay())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "days: "+days + " and hours: "+hours;
	}
}
