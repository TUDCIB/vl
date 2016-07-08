/**
 * 
 */
package sensitivity_input;

import java.net.URL;

/**
 * @author Frank Noack
 *
 */
public class ScheduleQuantity {
	/** name of the quantity */
	private String name;
	/** unit of the quantity */
	private String unit;
	/** 
	 * type of the quantity = file format or value
	 */
	private String scheduleType;
	/**
	 * period = day||week||year
	 */
	private String period;
	/**
	 * value = URL of the schedule
	 */
	private String value;
	

	/**
	 * constructor
	 */
	public ScheduleQuantity( String n, String u, String t, String p, String v ) {
		name = n;
		unit = u;
		scheduleType = t;
		period = p;
		value = v;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the type
	 */
	public String getScheduleType() {
		return scheduleType;
	}
	/**
	 * @param type the type to set
	 */
	public void setScheduleType(String type) {
		this.scheduleType = type;
	}
	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
