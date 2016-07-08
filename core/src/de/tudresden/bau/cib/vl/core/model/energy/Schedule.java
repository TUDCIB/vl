package de.tudresden.bau.cib.vl.core.model.energy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tudresden.bau.cib.vl.core.model.time.DayHourTimePeriod;

/**
 * Describes schedules defined in Schedule Templates.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public class Schedule {

	public static enum TYPES {
		AllDays, WeekEnd, Holiday
	}
	
	public static enum SCHEDULE_CYCLE {
		DailyCycle
	}
	
	private TYPES type;
	private Map<SCHEDULE_CYCLE, List<DayHourTimePeriod>> intervals;
	
	
	public Schedule(TYPES type) {
		this.type = type;
		this.intervals = new HashMap<SCHEDULE_CYCLE, List<DayHourTimePeriod>>();
	}
	
	public void addInterval(SCHEDULE_CYCLE cycle, DayHourTimePeriod interval) {
		List<DayHourTimePeriod> ints = intervals.get(cycle);
		if(ints != null) {
			ints.add(interval);
		} else {
			ints = new ArrayList<DayHourTimePeriod>();
			ints.add(interval);
			intervals.put(cycle, ints);
		}
	}
	
	public void addIntervals(SCHEDULE_CYCLE cycle, List<DayHourTimePeriod> moreIntervals) {
		List<DayHourTimePeriod> ints = intervals.get(cycle);
		if(ints != null) {
			ints.addAll(moreIntervals);
		} else {
			ints = new ArrayList<DayHourTimePeriod>();
			ints.addAll(moreIntervals);
			intervals.put(cycle, ints);
		}
	}
	
	public Map<SCHEDULE_CYCLE, List<DayHourTimePeriod>> getIntervals() {
		return intervals;
	}
	
	public List<DayHourTimePeriod> getIntervals(SCHEDULE_CYCLE cycle) {
		return intervals.get(cycle);
	}

	public TYPES getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return type.toString() + " " + intervals;
	}
	
}
