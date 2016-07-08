package de.tudresden.bau.cib.vl.core.model.time;

public enum TimeMeasure {

	MILLISECOND,
	SECOND,
	MIN, 
	HOUR, 
	DAY, 
	WORKDAY, 
	WEEK, 
	WORKWEEK,
	MONTH, 
	YEAR;
	
	private TimeMeasure() {
		
	}
	
	public static TimeMeasure findByAbbreviation(String abbreviation) throws IllegalArgumentException {
		if(abbreviation != null) {
		    for(TimeMeasure tm : values()){
		        if(tm.name().equalsIgnoreCase(abbreviation)){
		            return tm;
		        }
		    }
		}
	    throw new IllegalArgumentException("Can't find time measure with name: "+abbreviation);
	}
}
