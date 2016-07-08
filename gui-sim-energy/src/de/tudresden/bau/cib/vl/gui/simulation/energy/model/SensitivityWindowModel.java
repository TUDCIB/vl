package de.tudresden.bau.cib.vl.gui.simulation.energy.model;



public class SensitivityWindowModel extends SensitivityModel {
	
	public enum WindowProperties {
		glassFractionValue,
		frameFractionValue,
		thermalTransmittanceValue,
		solarHeatGainValue,
		shadingFactorValue
	}
	
	Double glassFractionValue 			=	null;
	String glassFractionUnit 			= 	null;
	Double frameFractionValue 			=	null;
	String frameFractionUnit 			= 	null;
	Double thermalTransmittanceValue 	= 	null;
	String thermalTransmittanceUnit 	=	null;
	Double solarHeatGainValue 			= 	null;
	String solarHeatGainUnit 			= 	null;
	Double shadingFactorValue 			= 	null;
	String shadingFactorUnit 			= 	null;


	public Double getGlassFractionValue() {
		return glassFractionValue;
	}

	public void setGlassFractionValue(Double glassFractionValue) {
		firePropertyChange(WindowProperties.glassFractionValue.name(), this.glassFractionValue, this.glassFractionValue = glassFractionValue);
		// nullify the other parameters
		firePropertyChange(WindowProperties.frameFractionValue.name(), this.frameFractionValue, this.frameFractionValue = null);
		firePropertyChange(WindowProperties.thermalTransmittanceValue.name(), this.thermalTransmittanceValue, this.thermalTransmittanceValue = null);
		firePropertyChange(WindowProperties.solarHeatGainValue.name(), this.solarHeatGainValue, this.solarHeatGainValue = null);
		firePropertyChange(WindowProperties.shadingFactorValue.name(), this.shadingFactorValue, this.shadingFactorValue = null);
	}

	public String getGlassFractionUnit() {
		return glassFractionUnit;
	}

	public void setGlassFractionUnit(String glassFractionUnit) {
		firePropertyChange("glassFractionUnit", this.glassFractionUnit, this.glassFractionUnit = glassFractionUnit);
	}

	public Double getFrameFractionValue() {
		return frameFractionValue;
	}

	public void setFrameFractionValue(Double frameFractionValue) {
		firePropertyChange(WindowProperties.frameFractionValue.name(), this.frameFractionValue, this.frameFractionValue = frameFractionValue);
		// nullify the other parameters
		firePropertyChange(WindowProperties.glassFractionValue.name(), this.glassFractionValue, this.glassFractionValue = null);
		firePropertyChange(WindowProperties.thermalTransmittanceValue.name(), this.thermalTransmittanceValue, this.thermalTransmittanceValue = null);
		firePropertyChange(WindowProperties.solarHeatGainValue.name(), this.solarHeatGainValue, this.solarHeatGainValue = null);
		firePropertyChange(WindowProperties.shadingFactorValue.name(), this.shadingFactorValue, this.shadingFactorValue = null);
	}

	public String getFrameFractionUnit() {
		return frameFractionUnit;
	}

	public void setFrameFractionUnit(String frameFractionUnit) {
		firePropertyChange("frameFractionUnit", this.frameFractionUnit, this.frameFractionUnit = frameFractionUnit);
	}

	public Double getThermalTransmittanceValue() {
		return thermalTransmittanceValue;
	}

	public void setThermalTransmittanceValue(Double thermalTransmittanceValue) {
		firePropertyChange(WindowProperties.thermalTransmittanceValue.name(), this.thermalTransmittanceValue, this.thermalTransmittanceValue = thermalTransmittanceValue);
		// nullify the other parameters
		firePropertyChange(WindowProperties.frameFractionValue.name(), this.frameFractionValue, this.frameFractionValue = null);
		firePropertyChange(WindowProperties.glassFractionValue.name(), this.glassFractionValue, this.glassFractionValue = null);
		firePropertyChange(WindowProperties.solarHeatGainValue.name(), this.solarHeatGainValue, this.solarHeatGainValue = null);
		firePropertyChange(WindowProperties.shadingFactorValue.name(), this.shadingFactorValue, this.shadingFactorValue = null);
	}

	public String getThermalTransmittanceUnit() {
		return thermalTransmittanceUnit;
	}

	public void setThermalTransmittanceUnit(String thermalTransmittanceUnit) {
		firePropertyChange("thermalTransmittanceUnit", this.thermalTransmittanceUnit, this.thermalTransmittanceUnit = thermalTransmittanceUnit);
	}

	public Double getSolarHeatGainValue() {
		return solarHeatGainValue;
	}

	public void setSolarHeatGainValue(Double solarHeatGainValue) {
		firePropertyChange(WindowProperties.solarHeatGainValue.name(), this.solarHeatGainValue, this.solarHeatGainValue = solarHeatGainValue);
		// nullify the other parameters
		firePropertyChange(WindowProperties.frameFractionValue.name(), this.frameFractionValue, this.frameFractionValue = null);
		firePropertyChange(WindowProperties.glassFractionValue.name(), this.glassFractionValue, this.glassFractionValue = null);
		firePropertyChange(WindowProperties.thermalTransmittanceValue.name(), this.thermalTransmittanceValue, this.thermalTransmittanceValue = null);
		firePropertyChange(WindowProperties.shadingFactorValue.name(), this.shadingFactorValue, this.shadingFactorValue = null);
	}

	public String getSolarHeatGainUnit() {
		return solarHeatGainUnit;
	}

	public void setSolarHeatGainUnit(String solarHeatGainUnit) {
		firePropertyChange("solarHeatGainUnit", this.solarHeatGainUnit, this.solarHeatGainUnit = solarHeatGainUnit);
	}

	public Double getShadingFactorValue() {
		return shadingFactorValue;
	}

	public void setShadingFactorValue(Double shadingFactorValue) {
		firePropertyChange(WindowProperties.shadingFactorValue.name(), this.shadingFactorValue, this.shadingFactorValue = shadingFactorValue);
		// nullify the other parameters
		firePropertyChange(WindowProperties.frameFractionValue.name(), this.frameFractionValue, this.frameFractionValue = null);
		firePropertyChange(WindowProperties.glassFractionValue.name(), this.glassFractionValue, this.glassFractionValue = null);
		firePropertyChange(WindowProperties.thermalTransmittanceValue.name(), this.thermalTransmittanceValue, this.thermalTransmittanceValue = null);
		firePropertyChange(WindowProperties.solarHeatGainValue.name(), this.solarHeatGainValue, this.solarHeatGainValue = null);
	}

	public String getShadingFactorUnit() {
		return shadingFactorUnit;
	}

	public void setShadingFactorUnit(String shadingFactorUnit) {
		firePropertyChange("shadingFactorUnit", this.shadingFactorUnit, this.shadingFactorUnit = shadingFactorUnit);
	}

	@Override
	public double getMeanValue() {
		if(glassFractionValue != null) {
			meanValue = glassFractionValue;
		} else if(frameFractionValue != null) {
			meanValue = frameFractionValue;
		} else if(shadingFactorValue != null) {
			meanValue = shadingFactorValue;
		} else if(thermalTransmittanceValue != null) {
			meanValue = thermalTransmittanceValue;
		} else if(solarHeatGainValue != null) {
			meanValue = solarHeatGainValue;
		}
		return meanValue;
	}
}
