package de.tudresden.bau.cib.vl.core.simulation.energy.database.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergyResultService;

/**
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 * @author <a href="mailto:Mario.Guertler@tu-dresden.de">Mario Guertler</a>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public class EnergyKeyPerformanceIndicators implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3040126322501234435L;
	
	private double 	plantExpeditureFigureHeating		= 1.1;
	private double 	netEnergyHeating 					= 0.0;
	
	private double 	plantExpeditureFigureCooling		= 1.1;
	private double 	netEnergyCooling 					= 0.0;	

	private int		years								= 30;	// Year
	private double 	priceIncrease						= 4.0;	// % e.g. 75.0
	private double	discountRate						= 6.0;	// % e.g. 35.0
	private double	windowArea							= 0.0;	// m2
	private Integer windowCleaningTimesPerYear			= 2;	// Integer
	private double	windowPerformanceValue				= 0.5;	// h/m2
	private double	windowHourlyRate					= 15.0;	// €/h
	private double	buildingShellArea					= 0.0;	// m2
	private Integer	buildingShellCleaningTimesPerYear	= 1;	// Integer
	private double	buildingShellPerformanceValue		= 0.25;	// h/m2
	private double	buildingShellHourlyRate				= 15.0;	// €/h
	
	private double 	finalEnergyHeating					= 0.0; // eKPI1.1a
	private double 	finalEnergyCooling					= 0.0; // eKPI1.2
	private Map<EnergyResultService.EnergyResourceType, Double> greenhouseGasEmissions = null; // eKPI 2.1
	private double 	investmentCosts						= 0.0; // eKPI6.1
	private double 	energyRelatedCosts					= 0.0; // eKPI6.2
	private double 	nonEnergyRelatedCosts				= 0.0; // eKPI6.3
	private double 	lifeCycleCosts						= 0.0; // eKPI6.ALL


	private double	maintenancePercentage				= 1.1; // % e.g. 1.1	


	private int		nonEnergyYears						= 30;	// Year
	private double 	nonEnergyPriceIncrease				= 4.0;	// % e.g. 75.0
	private double	nonEnergyDiscountRate				= 6.0;	// % e.g. 35.0
	
	
	/**
	 * List of Identifiers from {@link de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible}
	 */
	private List<String> combustibleIds;
	/**
	 * Percentage of use of each energy resource for heating
	 */
	Map<String, Double> percentagePerHeatingCombustible;
	/**
	 * Percentage of use of each energy resource for cooling
	 */
	Map<String, Double> percentagePerCoolingCombustible;
	
	/**
	 * Unique identifier
	 */
	private Integer id;
	/**
	 * Related simulation
	 */
	private Integer simulationId;
	
	// DEFAULT INITIALIZATION
	{
//		netEnergyHeating = Math.random()*100000;
//		netEnergyCooling = Math.random()*30000;
//		plantExpeditureFigureHeating = Math.random();
//		plantExpeditureFigureCooling = Math.random();
//		finalEnergyHeating = netEnergyHeating * plantExpeditureFigureHeating;
//		finalEnergyCooling = netEnergyCooling * plantExpeditureFigureCooling;
//		
		combustibleIds = new ArrayList<String>();
		combustibleIds.add("_G4TlQdQsEeK6Xdd4dOLERQ");
//		combustibleIds.add("_xXbwoOrbEeK2gM1Azt9urQ");
//		combustibleIds.add("_yaYbYOrbEeK2gM1Azt9urQ");
//		combustibleIds.add("_zMXR8OrbEeK2gM1Azt9urQ");
//		combustibleIds.add("_3KLLUOrbEeK2gM1Azt9urQ");
//		
//		setCombustibleIds(assignedCombustibleIds);
//																							// factors: CO2	|	SO2	|	NOX
		percentagePerHeatingCombustible = new HashMap<String, Double>(); 
		percentagePerHeatingCombustible.put("_G4TlQdQsEeK6Xdd4dOLERQ", 50.0);				//		302	|	0.455	0.227		
//		percentagePerHeatingCombustible.put("_yaYbYOrbEeK2gM1Azt9urQ", 10.0);				//		438	|	2,564	0,458
//		percentagePerHeatingCombustible.put("_3KLLUOrbEeK2gM1Azt9urQ", 5.0);				//		41	|	0,68	0,799
		percentagePerCoolingCombustible = new HashMap<String, Double>();
		percentagePerCoolingCombustible.put("_G4TlQdQsEeK6Xdd4dOLERQ", 50.0);				//		302	|	0.455	0.227
//		percentagePerCoolingCombustible.put("_zMXR8OrbEeK2gM1Azt9urQ", 5.0);				//		451	|	0,339	0,911
//		percentagePerCoolingCombustible.put("_xXbwoOrbEeK2gM1Azt9urQ", 20.0);				//		438	|	2,245	0.249
//		
//		setPercentagePerCoolingCombustible(percentagePerCoolingCombustible);
//		setPercentagePerHeatingCombustible(percentagePerHeatingCombustible);
//		
//		years								= 30;
//		priceIncrease						= 0.04;
//		discountRate						= 0.055;
//		
//		windowArea							= 15000.0;
//		windowCleaningTimesPerYear			= 4;
//		windowPerformanceValue				= 0.25; // 15 minutes for 1m²
//		windowHourlyRate					= 15.0;
//		buildingShellArea					= 55000.0;
//		buildingShellCleaningTimesPerYear	= 1;
//		buildingShellPerformanceValue		= 0.1; // 6 minutes for 1m² 
//		buildingShellHourlyRate				= 20.0;
////		### end of initial values
	}


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
    
    void setId(Integer id) {
    	this.id = id;
    }

	public Integer getSimulationId() {
		return simulationId;
	}

	public void setSimulationId(Integer simulationId) {
		this.simulationId = simulationId;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		EnergyKeyPerformanceIndicators eKPI = (EnergyKeyPerformanceIndicators) obj;
		return eKPI.hashCode() == this.hashCode();
	}
	
	@Override
	public String toString() {
		return "eKPI: (id: "+id+", simulationId: "+simulationId+", investment costs: "+investmentCosts+", plant expediture heating: "+plantExpeditureFigureHeating+
				", plant expediture cooling: "+plantExpeditureFigureCooling+", assigned combustibles: "+combustibleIds+", final heating energy: "+finalEnergyHeating+", final cooling energy: "+finalEnergyCooling+", net energy: <"+netEnergyHeating+","+netEnergyCooling+">)";
	}
	
	public int getNonEnergyYears() {
		return nonEnergyYears;
	}

	public void setNonEnergyYears(int nonEnergyYears) {
		this.nonEnergyYears = nonEnergyYears;
	}

	public double getNonEnergyPriceIncrease() {
		return nonEnergyPriceIncrease;
	}

	public void setNonEnergyPriceIncrease(double nonEnergyPriceIncrease) {
		this.nonEnergyPriceIncrease = nonEnergyPriceIncrease;
	}

	public double getNonEnergyDiscountRate() {
		return nonEnergyDiscountRate;
	}

	public void setNonEnergyDiscountRate(double nonEnergyDiscountRate) {
		this.nonEnergyDiscountRate = nonEnergyDiscountRate;
	}	
	
	
	public double getNetEnergyHeating() {
		return netEnergyHeating;
	}

	public void setNetEnergyHeating(double netEnergyHeating) {
		this.netEnergyHeating = netEnergyHeating;
	}

	public double getFinalEnergyHeating() {
		return finalEnergyHeating;
	}

	public void setFinalEnergyHeating(double finalEnergyHeating) {
		this.finalEnergyHeating = finalEnergyHeating;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public List<String> getCombustibleIds() {
		return combustibleIds;
	}

	public void setCombustibleIds(List<String> combustibleIds) {
		this.combustibleIds = combustibleIds;
	}

	public double getNetEnergyCooling() {
		return netEnergyCooling;
	}

	public void setNetEnergyCooling(double netEnergyCooling) {
		this.netEnergyCooling = netEnergyCooling;
	}

	public double getFinalEnergyCooling() {
		return finalEnergyCooling;
	}

	public void setFinalEnergyCooling(double finalEnergyCooling) {
		this.finalEnergyCooling = finalEnergyCooling;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public Map<String, Double> getPercentagePerHeatingCombustible() {
		return percentagePerHeatingCombustible;
	}

	public void setPercentagePerHeatingCombustible(
			Map<String, Double> percentagePerHeatingCombustible) {
		this.percentagePerHeatingCombustible = percentagePerHeatingCombustible;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public Map<String, Double> getPercentagePerCoolingCombustible() {
		return percentagePerCoolingCombustible;
	}

	public void setPercentagePerCoolingCombustible(
			Map<String, Double> percentagePerCoolingCombustible) {
		this.percentagePerCoolingCombustible = percentagePerCoolingCombustible;
	}

	@ElementCollection(fetch=FetchType.EAGER)
	public Map<EnergyResultService.EnergyResourceType, Double> getGreenhouseGasEmissions() {
		return greenhouseGasEmissions;
	}

	public void setGreenhouseGasEmissions(
			Map<EnergyResultService.EnergyResourceType, Double> greenhouseGasEmissions) {
		this.greenhouseGasEmissions = greenhouseGasEmissions;
	}

	public double getInvestmentCosts() {
		return investmentCosts;
	}

	public void setInvestmentCosts(double investmentCosts) {
		this.investmentCosts = investmentCosts;
	}

	public double getNonEnergyRelatedCosts() {
		return nonEnergyRelatedCosts;
	}

	public void setNonEnergyRelatedCosts(double nonEnergyRelatedCosts) {
		this.nonEnergyRelatedCosts = nonEnergyRelatedCosts;
	}

	public double getLifeCycleCosts() {
		return lifeCycleCosts;
	}

	public void setLifeCycleCosts(double lifeCycleCosts) {
		this.lifeCycleCosts = lifeCycleCosts;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	/**
	 * @return price increase in percentage
	 */
	public double getPriceIncrease() {
		return priceIncrease;
	}

	public void setPriceIncrease(double priceIncrease) {
		this.priceIncrease = priceIncrease;
	}

	/**
	 * @return price increase in percentage
	 */
	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public Integer getWindowCleaningTimesPerYear() {
		return windowCleaningTimesPerYear;
	}

	public void setWindowCleaningTimesPerYear(Integer windowCleaningTimesPerYear) {
		this.windowCleaningTimesPerYear = windowCleaningTimesPerYear;
	}

	public double getWindowPerformanceValue() {
		return windowPerformanceValue;
	}

	public void setWindowPerformanceValue(double windowPerformanceValue) {
		this.windowPerformanceValue = windowPerformanceValue;
	}

	public double getWindowHourlyRate() {
		return windowHourlyRate;
	}

	public void setWindowHourlyRate(double windowHourlyRate) {
		this.windowHourlyRate = windowHourlyRate;
	}

	public Integer getBuildingShellCleaningTimesPerYear() {
		return buildingShellCleaningTimesPerYear;
	}

	public void setBuildingShellCleaningTimesPerYear(
			Integer buildingShellCleaningTimesPerYear) {
		this.buildingShellCleaningTimesPerYear = buildingShellCleaningTimesPerYear;
	}

	public double getBuildingShellPerformanceValue() {
		return buildingShellPerformanceValue;
	}

	public void setBuildingShellPerformanceValue(
			double buildingShellPerformanceValue) {
		this.buildingShellPerformanceValue = buildingShellPerformanceValue;
	}

	public double getBuildingShellHourlyRate() {
		return buildingShellHourlyRate;
	}

	public void setBuildingShellHourlyRate(double buildingShellHourlyRate) {
		this.buildingShellHourlyRate = buildingShellHourlyRate;
	}

	public double getPlantExpeditureFigureHeating() {
		return plantExpeditureFigureHeating;
	}

	public void setPlantExpeditureFigureHeating(double plantExpeditureFigureHeating) {
		this.plantExpeditureFigureHeating = plantExpeditureFigureHeating;
	}

	public double getPlantExpeditureFigureCooling() {
		return plantExpeditureFigureCooling;
	}

	public void setPlantExpeditureFigureCooling(double plantExpeditureFigureCooling) {
		this.plantExpeditureFigureCooling = plantExpeditureFigureCooling;
	}

	public double getEnergyRelatedCosts() {
		return energyRelatedCosts;
	}

	public void setEnergyRelatedCosts(double energyRelatedCosts) {
		this.energyRelatedCosts = energyRelatedCosts;
	}

	public double getWindowArea() {
		return windowArea;
	}

	public void setWindowArea(double windowArea) {
		this.windowArea = windowArea;
	}

	public double getBuildingShellArea() {
		return buildingShellArea;
	}

	public void setBuildingShellArea(double buildingShellArea) {
		this.buildingShellArea = buildingShellArea;
	}

	public double getMaintenancePercentage() {
		return maintenancePercentage;
	}

	public void setMaintenancePercentage(double maintenancePercentage) {
		this.maintenancePercentage = maintenancePercentage;
	}

}
