package de.tudresden.bau.cib.vl.gui.simulation.energy.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class SensitivityModel {

	
	public enum Properties {
		sampleNumber,
		standardDeviation
	}
	
	/**
	 * Important for Databinding
	 */
	protected PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	/**
	 * Important for Databinding
	 */
	public void addPropertyChangeListener(PropertyChangeListener 
			listener) {
		changeSupport.addPropertyChangeListener(listener);
	}
	
	/**
	 * Important for Databinding
	 */
	public void removePropertyChangeListener(PropertyChangeListener 
			listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * Important for Databinding
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Important for Databinding
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Important for Databinding
	 */
	protected void firePropertyChange(String propertyName, 
			Object oldValue,
			Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	protected int sampleNumber;
	
	protected double standardDeviation;
	
	protected double meanValue;

	public int getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(int sampleNumber) {
		firePropertyChange(Properties.sampleNumber.name(), this.sampleNumber, this.sampleNumber = sampleNumber);
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(double standardDeviation) {
		firePropertyChange(Properties.standardDeviation.name(), this.standardDeviation, this.standardDeviation = standardDeviation);
	}

	/**
	 * @return The mean value which mostly is an existent value which will be varied.
	 */
	public double getMeanValue() {
		return meanValue;
	}

	public void setMeanValue(double meanValue) {
		this.meanValue = meanValue;
	}
	
}
