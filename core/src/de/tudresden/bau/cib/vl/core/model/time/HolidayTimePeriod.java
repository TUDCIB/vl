package de.tudresden.bau.cib.vl.core.model.time;

import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;

public class HolidayTimePeriod implements TimePeriod {
	
	private Set<Integer> holidaysInMillis;
	
	
	public HolidayTimePeriod() {
		holidaysInMillis = new TreeSet<Integer>();
	}
	
	public void addHoliday(DateTime time) {
		int dayOfYear = time.getDayOfYear();
		holidaysInMillis.add(dayOfYear);
	}

	@Override
	public boolean isInTimeRanges(long timeInMillis) {
		DateTime checkTime = new DateTime(timeInMillis);
		return holidaysInMillis.contains(checkTime.getDayOfYear());
	}

}
