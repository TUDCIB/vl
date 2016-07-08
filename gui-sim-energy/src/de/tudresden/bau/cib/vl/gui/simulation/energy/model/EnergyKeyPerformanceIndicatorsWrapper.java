package de.tudresden.bau.cib.vl.gui.simulation.energy.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergyResultService.EnergyResourceType;
import de.tudresden.bau.cib.vl.core.simulation.energy.postprocessing.EnergyKeyPerformanceIndicatorCalculator;

/**
 * TODO JavaDoc
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 * @author <a href="mailto:Mario.Guertler@tu-dresden.de">Mario Guertler</a>
 *
 */
public class EnergyKeyPerformanceIndicatorsWrapper {
	
	
	private EnergyKeyPerformanceIndicators eKPIs;

	private double nonEnergyRelatedCostsForWindows				= 0.0;
	private double nonEnergyRelatedCostsForBuildingShell		= 0.0;
	private double nonEnergyRelatedCostsForMaintenance			= 0.0;



	private double netEnergyHeating								= 0.0;
	private double netEnergyCooling								= 0.0;
	private double plantExpeditureFigureHeating					= 0.0;
	private double plantExpeditureFigureCooling					= 0.0;
	private List<Combustible> assignedCombustibles;
	
	
	public EnergyKeyPerformanceIndicatorsWrapper(EnergyKeyPerformanceIndicators eKPIs) {
		this.eKPIs = eKPIs;		
	}	
	
	public void prepareData() {
		setNetEnergyCooling(eKPIs.getNetEnergyCooling());
		setNetEnergyHeating(eKPIs.getNetEnergyHeating());
		setPlantExpeditureFigureHeating(eKPIs.getPlantExpeditureFigureHeating());
		setPlantExpeditureFigureCooling(eKPIs.getPlantExpeditureFigureCooling());
		
		setNonEnergyRelatedCostsForWindows();
		setNonEnergyRelatedCostsForBuildingShell();		
		seteKPI61(eKPIs.getInvestmentCosts());
		seteKPI62();
		seteKPI63();
		seteKPI6All();
		
		setNonEnergyRelatedCostsForMaintenance();
	}
	
	public double geteKPI11a() {
		return eKPIs.getFinalEnergyHeating();
	}
	
	public void seteKPI11a() {
		double previousValue 		= eKPIs.getFinalEnergyHeating();
		double netEnergy 			= eKPIs.getNetEnergyHeating();
		double plantExpeditureFigure= eKPIs.getPlantExpeditureFigureHeating();
		double value 				= EnergyKeyPerformanceIndicatorCalculator.calculateFinalEnergy(netEnergy, plantExpeditureFigure);
		eKPIs.setFinalEnergyHeating(value);
		firePropertyChange("eKPI11a", previousValue, value);
	}

	public double geteKPI12() {
		return eKPIs.getFinalEnergyCooling();
	}

	public void seteKPI12() {
		double previousValue 		= eKPIs.getFinalEnergyCooling();
		double netEnergy 			= eKPIs.getNetEnergyCooling();
		double plantExpeditureFigure= eKPIs.getPlantExpeditureFigureCooling();
		double value 				= EnergyKeyPerformanceIndicatorCalculator.calculateFinalEnergy(netEnergy, plantExpeditureFigure);
		eKPIs.setFinalEnergyCooling(value);
		firePropertyChange("eKPI12", previousValue, value);
	}

	public Map<EnergyResourceType, Double> geteKPI21() {
		return eKPIs.getGreenhouseGasEmissions();
	}

	public void seteKPI21() {
		if(assignedCombustibles != null) {
			Map<EnergyResourceType, Double> previousValues = eKPIs.getGreenhouseGasEmissions();		
			Map<EnergyResourceType, Double> newValues = EnergyKeyPerformanceIndicatorCalculator.calculateEcologicGreenhouseGasEmissionValue(eKPIs, assignedCombustibles);
			eKPIs.setGreenhouseGasEmissions(newValues);
			firePropertyChange("eKPI21", previousValues, newValues);
		}
	}

	public double geteKPI61() {
		return eKPIs.getInvestmentCosts();
	}

	public void seteKPI61(double value) {
		double previousValue = eKPIs.getInvestmentCosts();
		eKPIs.setInvestmentCosts(value);
		firePropertyChange("eKPI61", previousValue, value);
//		Influences all costs
		seteKPI6All();
	}
	
	public int getYears() {
		return eKPIs.getYears();
	}

	public void setYears(int years) {
		int previousYears = eKPIs.getYears();
		eKPIs.setYears(previousYears);
		firePropertyChange("years", previousYears, years);
//		Influences the energy-related costs
		seteKPI62();
	}

	public double getPriceIncrease() {
		return eKPIs.getPriceIncrease();
	}

	public void setPriceIncrease(double priceIncrease) {
		double previousValue = eKPIs.getPriceIncrease();
		eKPIs.setPriceIncrease(priceIncrease);
		firePropertyChange("priceIncrease", previousValue, priceIncrease);
//		Influences the energy-related costs
		seteKPI62();
	}

	public double getDiscountRate() {
		return eKPIs.getDiscountRate();
	}

	public void setDiscountRate(double discountRate) {
		double previousValue = eKPIs.getDiscountRate();
		eKPIs.setDiscountRate(discountRate);
		firePropertyChange("discountRate", previousValue, discountRate);
//		Influences the energy-related costs
		seteKPI62();
	}	

	public int getNonEnergyYears() {
		return eKPIs.getNonEnergyYears();
	}

	public void setNonEnergyYears(int years) {
		int previousYears = eKPIs.getNonEnergyYears();
		eKPIs.setNonEnergyYears(previousYears);
		firePropertyChange("nonEnergyYears", previousYears, years);
//		Influences the non-energy-related costs
		seteKPI63();
	}

	public double getNonEnergyPriceIncrease() {
		return eKPIs.getNonEnergyPriceIncrease();
	}

	public void setNonEnergyPriceIncrease(double priceIncrease) {
		double previousValue = eKPIs.getNonEnergyPriceIncrease();
		eKPIs.setNonEnergyPriceIncrease(priceIncrease);
		firePropertyChange("nonEnergyPriceIncrease", previousValue, priceIncrease);
//		Influences the non-energy-related costs
		seteKPI63();
	}

	public double getNonEnergyDiscountRate() {
		return eKPIs.getNonEnergyDiscountRate();
	}

	public void setNonEnergyDiscountRate(double discountRate) {
		double previousValue = eKPIs.getNonEnergyDiscountRate();
		eKPIs.setNonEnergyDiscountRate(discountRate);
		firePropertyChange("nonEnergyDiscountRate", previousValue, discountRate);
//		Influences the non-energy-related costs
		seteKPI63();
	}

	public double geteKPI62() {
//		double costs = eKPIs.getEnergyRelatedCosts();
//		if(costs == 0.0 && assignedCombustibles != null) {
//			costs = EnergyKeyPerformanceIndicatorCalculator.calculateEnergyRelatedOperationalCosts(assignedCombustibles, eKPIs);
//			eKPIs.setEnergyRelatedCosts(costs);
//		}
//		return costs;
		return eKPIs.getEnergyRelatedCosts();
	}

	public void seteKPI62() {
		if(assignedCombustibles != null) {
			double previousValue = eKPIs.getEnergyRelatedCosts();
			double newValue = EnergyKeyPerformanceIndicatorCalculator.calculateEnergyRelatedOperationalCosts(assignedCombustibles, eKPIs);
			eKPIs.setEnergyRelatedCosts(newValue);			
			firePropertyChange("eKPI62", previousValue, newValue);
			
//			Influences all costs
			seteKPI6All();
		}
	}
	
	public double geteKPI63() {
//		double costs = eKPIs.getNonEnergyRelatedCosts();
//		if(costs == 0.0){
//			costs = EnergyKeyPerformanceIndicatorCalculator.calculateNonEnergyRelatedOperationalCosts(eKPIs);
//			eKPIs.setNonEnergyRelatedCosts(costs);
//		}
//		return costs;
		return eKPIs.getNonEnergyRelatedCosts();
	}

	public void seteKPI63() {
		double previousValue = eKPIs.getNonEnergyRelatedCosts();		
		double newValue = EnergyKeyPerformanceIndicatorCalculator.calculateNonEnergyRelatedOperationalCosts(eKPIs);
		eKPIs.setNonEnergyRelatedCosts(newValue);
		firePropertyChange("eKPI63", previousValue, newValue);
//		Influences all costs
		seteKPI6All();
	}
	
	public double geteKPI6All() {
//		double costs = eKPIs.getLifeCycleCosts();
//		if(costs == 0.0) {
//			costs = EnergyKeyPerformanceIndicatorCalculator.calculateLifecycleCosts(geteKPI61(), geteKPI62(), geteKPI63());
//			eKPIs.setLifeCycleCosts(costs);
//		}
//		return costs;
		return eKPIs.getLifeCycleCosts();
	}
	
	public void seteKPI6All() {
		double previousValue = eKPIs.getLifeCycleCosts();
		double newValue = EnergyKeyPerformanceIndicatorCalculator.calculateLifecycleCosts(geteKPI61(), geteKPI62(), geteKPI63());		
		eKPIs.setLifeCycleCosts(newValue);
		firePropertyChange("eKPI6All", previousValue, newValue);
	}

	public List<String> getCombustibleIds() {
		return eKPIs.getCombustibleIds();
	}

	public void setCombustibleIds(List<String> combustibleIds) {
		List<String> previousSelection = eKPIs.getCombustibleIds();
		eKPIs.setCombustibleIds(combustibleIds);
		firePropertyChange("combustibleSelection", previousSelection, combustibleIds);
	}
	
	public void setAssignedCombustibles(List<Combustible> combustibles) {
		this.assignedCombustibles = combustibles;
		List<String> selectedCombustibleIds = new ArrayList<String>();
		for(Combustible c : combustibles) selectedCombustibleIds.add(c.getId());
		eKPIs.setCombustibleIds(selectedCombustibleIds);
//		Influences the greenhouse gas emissions
//		seteKPI21();
		prepareData();
	}

	public Map<String, Double> getPercentagePerHeatingCombustible() {
		return eKPIs.getPercentagePerHeatingCombustible();
	}

	public void setPercentagePerHeatingCombustible(
			Map<String, Double> percentagePerHeatingCombustible) {
		Map<String, Double> previousMap = eKPIs.getPercentagePerHeatingCombustible();
		eKPIs.setPercentagePerHeatingCombustible(percentagePerHeatingCombustible);
		firePropertyChange("percentagePerHeatingCombustible", previousMap, percentagePerHeatingCombustible);
//		Influences the greenhouse gas emissions
		seteKPI21();
//		Influences the energy-related costs
		seteKPI62();
	}

	public Map<String, Double> getPercentagePerCoolingCombustible() {
		return eKPIs.getPercentagePerCoolingCombustible();
	}

	public void setPercentagePerCoolingCombustible(
			Map<String, Double> percentagePerCoolingCombustible) {
		Map<String, Double> previousMap = eKPIs.getPercentagePerCoolingCombustible();
		eKPIs.setPercentagePerCoolingCombustible(percentagePerCoolingCombustible);
		firePropertyChange("percentagePerCoolingCombustible", previousMap, percentagePerCoolingCombustible);
//		Influences the greenhouse gas emissions
		seteKPI21();
//		Influences the energy-related costs
		seteKPI62();
	}
	
	public Integer getWindowCleaningTimesPerYear() {
		return eKPIs.getWindowCleaningTimesPerYear();
	}

	public void setWindowCleaningTimesPerYear(Integer windowCleaningTimesPerYear) {
		int previousValue = eKPIs.getWindowCleaningTimesPerYear();
		eKPIs.setWindowCleaningTimesPerYear(windowCleaningTimesPerYear);
		firePropertyChange("windowCleaningTimesPerYear", previousValue, windowCleaningTimesPerYear);
		setNonEnergyRelatedCostsForWindows();
	}

	public double getWindowPerformanceValue() {
		return eKPIs.getWindowPerformanceValue();
	}

	public void setWindowPerformanceValue(double windowPerformanceValue) {
		double previousValue = eKPIs.getWindowPerformanceValue();
		eKPIs.setWindowPerformanceValue(windowPerformanceValue);
		firePropertyChange("windowPerformanceValue", previousValue, windowPerformanceValue);
		setNonEnergyRelatedCostsForWindows();
	}

	public double getWindowHourlyRate() {
		return eKPIs.getWindowHourlyRate();
	}

	public void setWindowHourlyRate(double windowHourlyRate) {
		double previousValue = eKPIs.getWindowHourlyRate();
		eKPIs.setWindowHourlyRate(windowHourlyRate);
		firePropertyChange("windowHourlyRate", previousValue, windowHourlyRate);
		setNonEnergyRelatedCostsForWindows();
	}

	public Integer getBuildingShellCleaningTimesPerYear() {
		return eKPIs.getBuildingShellCleaningTimesPerYear();
	}

	public void setBuildingShellCleaningTimesPerYear(
			Integer buildingShellCleaningTimesPerYear) {
		double previousValue = eKPIs.getBuildingShellCleaningTimesPerYear();
		eKPIs.setBuildingShellCleaningTimesPerYear(buildingShellCleaningTimesPerYear);
		firePropertyChange("buildingShellCleaningTimesPerYear", previousValue, buildingShellCleaningTimesPerYear);
		setNonEnergyRelatedCostsForBuildingShell();
	}

	public double getBuildingShellPerformanceValue() {
		return eKPIs.getBuildingShellPerformanceValue();
	}

	public void setBuildingShellPerformanceValue(
			double buildingShellPerformanceValue) {
		double previousValue = eKPIs.getBuildingShellPerformanceValue();
		eKPIs.setBuildingShellPerformanceValue(buildingShellPerformanceValue);
		firePropertyChange("buildingShellPerformanceValue", previousValue, buildingShellPerformanceValue);
		setNonEnergyRelatedCostsForBuildingShell();
	}

	public double getBuildingShellHourlyRate() {
		return eKPIs.getBuildingShellHourlyRate();
	}

	public void setBuildingShellHourlyRate(double buildingShellHourlyRate) {
		double previousValue = eKPIs.getBuildingShellHourlyRate();
		eKPIs.setBuildingShellHourlyRate(buildingShellHourlyRate);
		firePropertyChange("buildingShellHourlyRate", previousValue, buildingShellHourlyRate);
		setNonEnergyRelatedCostsForBuildingShell();
	}

	public double getWindowArea() {
		return eKPIs.getWindowArea();
	}

	public void setWindowArea(double windowArea) {	
		double previousValue = eKPIs.getWindowArea();
		eKPIs.setWindowArea(windowArea);
		firePropertyChange("windowArea", previousValue, windowArea);
		setNonEnergyRelatedCostsForWindows();
	}

	public double getBuildingShellArea() {
		return eKPIs.getBuildingShellArea();
	}

	public void setBuildingShellArea(double buildingShellArea) {
		double previousValue = eKPIs.getBuildingShellArea();
		eKPIs.setBuildingShellArea(buildingShellArea);
		firePropertyChange("buildingShellArea", previousValue, buildingShellArea);
		setNonEnergyRelatedCostsForBuildingShell();
	}

	public double getNonEnergyRelatedCostsForWindows() {
		return nonEnergyRelatedCostsForWindows;
	}

	public void setNonEnergyRelatedCostsForWindows() {
		firePropertyChange("nonEnergyRelatedCostsForWindows", nonEnergyRelatedCostsForWindows, 
				this.nonEnergyRelatedCostsForWindows = EnergyKeyPerformanceIndicatorCalculator.calculateNonEnergyRelatedOperationalCostsForWindowsPerYear(
						getWindowArea(), getWindowCleaningTimesPerYear(), getWindowPerformanceValue(), getWindowHourlyRate()));
	}
	
	public double getNetEnergyCooling() {
		return netEnergyCooling;
//		return eKPIs.getNetEnergyCooling();
	}
	
	public void setNetEnergyHeating(double netEnergyHeating) {
		firePropertyChange("netEnergyHeating", this.netEnergyHeating, this.netEnergyHeating = netEnergyHeating);
	}

	public void setNetEnergyCooling(double netEnergyCooling) {
		firePropertyChange("netEnergyCooling", this.netEnergyCooling, this.netEnergyCooling = netEnergyCooling);
	}

	public double getNetEnergyHeating() {
		return netEnergyHeating;
//		return eKPIs.getNetEnergyHeating();
	}

	public double getNonEnergyRelatedCostsForBuildingShell() {
		return nonEnergyRelatedCostsForBuildingShell;
	}

	public void setNonEnergyRelatedCostsForBuildingShell() {
		firePropertyChange("nonEnergyRelatedCostsForBuildingShell", nonEnergyRelatedCostsForBuildingShell, 
				this.nonEnergyRelatedCostsForBuildingShell = EnergyKeyPerformanceIndicatorCalculator.calculateNonEnergyRelatedOperationalCostsForBuildingShellPerYear(
						getBuildingShellArea(), getBuildingShellCleaningTimesPerYear(), getBuildingShellPerformanceValue(), getBuildingShellHourlyRate()));
	}

	public EnergyKeyPerformanceIndicators geteKPIs() {
		return eKPIs;
	}

	public double getPlantExpeditureFigureHeating() {
//		return eKPIs.getPlantExpeditureFigureHeating();
		return plantExpeditureFigureHeating;
	}

	public void setPlantExpeditureFigureHeating(double plantExpeditureFigureHeating) {
		double previousValue = eKPIs.getPlantExpeditureFigureHeating();
		firePropertyChange("plantExpeditureFigureHeating", previousValue, this.plantExpeditureFigureHeating = plantExpeditureFigureHeating);
		eKPIs.setPlantExpeditureFigureHeating(plantExpeditureFigureHeating);
		seteKPI11a();
	}

	public double getPlantExpeditureFigureCooling() {
//		return eKPIs.getPlantExpeditureFigureCooling();
		return plantExpeditureFigureCooling;
	}

	public void setPlantExpeditureFigureCooling(double plantExpeditureFigureCooling) {
		double previousValue = eKPIs.getPlantExpeditureFigureCooling();
		firePropertyChange("plantExpeditureFigureCooling", previousValue, this.plantExpeditureFigureCooling = plantExpeditureFigureCooling);
		eKPIs.setPlantExpeditureFigureCooling(plantExpeditureFigureCooling);
		seteKPI12();
	}
	
	
	public double getMaintenancePercentage()
	{
		return eKPIs.getMaintenancePercentage();
	}
	
	public void setMaintenancePercentage(double value)
	{
		eKPIs.setMaintenancePercentage(value);
		setNonEnergyRelatedCostsForMaintenance();
	}
	
	public double getNonEnergyRelatedCostsForMaintenance() {
		return nonEnergyRelatedCostsForMaintenance;
	}

	public void setNonEnergyRelatedCostsForMaintenance( ) {
		firePropertyChange("nonEnergyRelatedCostsForMaintenance", nonEnergyRelatedCostsForMaintenance, 
				this.nonEnergyRelatedCostsForMaintenance = 
					EnergyKeyPerformanceIndicatorCalculator.calculateNonEnergyRelatedOperationalCostsForMaintenancePerYear(
							eKPIs.getInvestmentCosts(), getMaintenancePercentage()));
	}
	
	
	
	/**
	 * Important for Databinding
	 */
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

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

	public void seteKPIs(EnergyKeyPerformanceIndicators eKPIs) {
		this.eKPIs = eKPIs;
	}
}
