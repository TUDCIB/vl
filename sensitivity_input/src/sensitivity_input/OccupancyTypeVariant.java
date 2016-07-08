/**
 * 
 */
package sensitivity_input;

/**
 * This class represents a separate occupancy variant, but concerning the 
 * structure of the different variants it should be a ScheduleTypeVariant
 * with occupancy as the ScheduleQuantity (with an empty unit) field resp.
 * 
 * @author Frank Noack
 *
 */
public class OccupancyTypeVariant extends Variant {
	/**
	 * the type of the data file (csv, tsv ...)
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
	public OccupancyTypeVariant ( String variantiD, 
			                      String variantType, 
			                      String sType,
			                      String schedulePeriod, 
			                      String values ) {
		super( variantiD, variantType );
		scheduleType = sType;
		period = schedulePeriod;
		value = values;
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
