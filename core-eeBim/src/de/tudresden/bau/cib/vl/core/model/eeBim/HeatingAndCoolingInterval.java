/*
 * TU Dresden
 */
package de.tudresden.bau.cib.vl.core.model.eeBim;

import de.tudresden.bau.cib.vl.core.model.time.DayHourTimePeriod;

/**
 * The Class HeatingAndCoolingInterval.
 */
public class HeatingAndCoolingInterval extends DayHourTimePeriod {

	/** The heating set point. */
	private Float heatingSetPoint;
	
	/** The heating unit. */
	private String heatingUnit = "C";
	
	/** The cooling unit. */
	private String coolingUnit = "C";
	
	/** The cooling set point. */
	private Float coolingSetPoint;
	
	
	/**
	 * Gets the cooling set point.
	 *
	 * @return the cooling set point
	 */
	public Float getCoolingSetPoint() {
		return coolingSetPoint;
	}


	/**
	 * Sets the cooling set point.
	 *
	 * @param coolingSetPoint the new cooling set point
	 */
	public void setCoolingSetPoint(Float coolingSetPoint) {
		this.coolingSetPoint = coolingSetPoint;
	}


	/**
	 * Gets the heating set point.
	 *
	 * @return the heating set point
	 */
	public Float getHeatingSetPoint() {
		return heatingSetPoint;
	}


	/**
	 * Sets the heating set point.
	 *
	 * @param heatingsetPoint the new heating set point
	 */
	public void setHeatingSetPoint(Float heatingsetPoint) {
		this.heatingSetPoint = heatingsetPoint;
	}
	
	/**
	 * Gets the heating unit.
	 *
	 * @return the heating unit
	 */
	public String getHeatingUnit() {
		return heatingUnit;
	}


	/**
	 * Sets the heating unit.
	 *
	 * @param heatingUnit the new heating unit
	 */
	public void setHeatingUnit(String heatingUnit) {
		this.heatingUnit = heatingUnit;
	}


	/**
	 * Gets the cooling unit.
	 *
	 * @return the cooling unit
	 */
	public String getCoolingUnit() {
		return coolingUnit;
	}


	/**
	 * Sets the cooling unit.
	 *
	 * @param coolingUnit the new cooling unit
	 */
	public void setCoolingUnit(String coolingUnit) {
		this.coolingUnit = coolingUnit;
	}
	
	/* (non-Javadoc)
	 * @see de.tudresden.bau.cib.vl.core.extension.model.time.DayHourTimePeriod#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ", heating set point: " + heatingSetPoint +", cooling set point: " + coolingSetPoint;
	}
}
