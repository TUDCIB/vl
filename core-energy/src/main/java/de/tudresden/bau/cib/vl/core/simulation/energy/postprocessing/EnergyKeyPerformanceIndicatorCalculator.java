package de.tudresden.bau.cib.vl.core.simulation.energy.postprocessing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.tudresden.bau.cib.vl.core.model.MeasuredUnit;
import de.tudresden.bau.cib.vl.core.model.eeBim.combustible.Combustible;
import de.tudresden.bau.cib.vl.core.model.processing.PostProcessingEngine;
import de.tudresden.bau.cib.vl.core.model.time.TimeMeasure;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.EnergyKeyPerformanceIndicatorException;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.ResultCodes;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergyResultService.EnergyResourceType;

/**
 * Based on HESMOS Deliverable 5.2 with modifications.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 * @author <a href="mailto:Mario.Guertler@tu-dresden.de">Mario Guertler</a>
 *
 */
public final class EnergyKeyPerformanceIndicatorCalculator {
	

	private EnergyKeyPerformanceIndicatorCalculator() {
		throw new AssertionError();
	}
	
	public static double calculateFinalEnergy(double netEnergyValue, double plantExpeditureFigure) {
		double result = netEnergyValue * plantExpeditureFigure;
//		return roundToReadableValue(result);
		return result;
	}

	/**
	 * eKPI1.1
	 * Final energy for heating of the building for the whole year.
	 * @param heatingFinalEnergy
	 * @param eKPI
	 * @return
	 */
	public static double calculateHeatingFinalEnergy(
			MeasuredUnit heatingFinalEnergy, 
			EnergyKeyPerformanceIndicators  eKPI) {		
		double finalHeating = 0.0;
		if(heatingFinalEnergy != null && eKPI != null) {
			double plantExpeditureFigure = eKPI.getPlantExpeditureFigureHeating();
			TreeMap<Long, Double> values = heatingFinalEnergy.getValues();
			for (Map.Entry<Long, Double> entry : values.entrySet()) {
				Double value = entry.getValue();

				finalHeating += value;
			}	
//			until now its the net energy
			eKPI.setNetEnergyHeating(finalHeating);
			return calculateFinalEnergy(finalHeating,plantExpeditureFigure);
		}
		
		return finalHeating;
	}
	
	public static double convertToKiloWattHour(double kiloWatts, int numberOfHours) {
		double value = 0.0;
		value = value * numberOfHours;
		return value;
	}
	
	/**
	 * Round to a specific post-decimal place
	 * @param value
	 * @return
	 */
	public static double roundToReadableValue(double value) {
		value *= 100;
		value = Math.round(value) / 100;
		return value;
	}
	
	/**
	 * eKPI1.2
	 * Final energy for cooling of the building for the whole year.
	 * @param coolingFinalEnergy
	 * @param eKPI
	 * @return
	 */
	public static double calculateCoolingFinalEnergy(
			MeasuredUnit coolingFinalEnergy, 
			EnergyKeyPerformanceIndicators  eKPI) {		
		double finalCooling = 0.0;
		if(coolingFinalEnergy != null && eKPI != null) {
			double plantExpeditureFigure = eKPI.getPlantExpeditureFigureCooling();
			TreeMap<Long, Double> values = coolingFinalEnergy.getValues();
			for (Map.Entry<Long, Double> entry : values.entrySet()) {
				Double value = entry.getValue();

				finalCooling += value;
			}		
			finalCooling = Math.abs(finalCooling);
//			until now its the net energy
			eKPI.setNetEnergyCooling(finalCooling);
			return calculateFinalEnergy(finalCooling,plantExpeditureFigure);
		}
		return finalCooling;
	}
	
	/**
	 * eKPI 1.1a for Zone
	 * @param heatingUnits
	 * @param timeMeasure
	 * @return
	 */
	@Deprecated
	public static MeasuredUnit calculateHeatingNetEnergyForZones(
			List<MeasuredUnit> heatingUnits, 
			TimeMeasure timeMeasure) {
		MeasuredUnit heatingNetEnergyUnit = new MeasuredUnit("eKPI1.1NZ");
		heatingNetEnergyUnit.setLabel(ResultCodes.Heating_Net_Energy.getLabel());
		heatingNetEnergyUnit.setResultCode(ResultCodes.Heating_Net_Energy.getCode());
		heatingNetEnergyUnit.setQuantity(ResultCodes.Heating_Net_Energy.getQuantity());
		heatingNetEnergyUnit.setValueUnit(ResultCodes.Heating_Net_Energy.getValueUnit());
		heatingNetEnergyUnit.setStartYear(heatingUnits.get(0).getStartYear());
		
		switch(timeMeasure) {
		case YEAR:
			double Qh_year = 0.0;
			for(MeasuredUnit unit : heatingUnits) {
				double Qh_year_unit = PostProcessingEngine.createSumOfValues(unit);			
				Qh_year += Qh_year_unit;
			}
			TreeMap<Long, Double> values = new TreeMap<Long, Double>();
			values.put(heatingUnits.get(0).getValues().firstKey(), Qh_year);
			heatingNetEnergyUnit.setTimeUnit(TimeMeasure.YEAR.name());
			heatingNetEnergyUnit.setAggregated(true);
			break;
		case MONTH:
			TreeMap<Long,Double> Qh_month = new TreeMap<Long, Double>();
			for(MeasuredUnit unit : heatingUnits) {
				PostProcessingEngine.addUpValuesPerMonth(unit);
				TreeMap<Long,Double> Qh_month_unit = unit.getValues();
				for (Map.Entry<Long, Double> entry : Qh_month_unit.entrySet()) {
					Long key = entry.getKey();
					Double value = entry.getValue();
					if(Qh_month.containsKey(key)) {
						Double valSum = Qh_month.get(key) + value;
						Qh_month.put(key, valSum);
					} else {
						Qh_month.put(key, value);
					}
				}
			}
			heatingNetEnergyUnit.setValues(Qh_month);
			heatingNetEnergyUnit.setTimeUnit(TimeMeasure.MONTH.name());
			heatingNetEnergyUnit.setAggregated(true);
			break;
		case WEEK:
			TreeMap<Long,Double> Qh_week = new TreeMap<Long, Double>();
			for(MeasuredUnit unit : heatingUnits) {
				PostProcessingEngine.addUpValuesPerWeek(unit);
				TreeMap<Long,Double> Qh_week_unit = unit.getValues();
				for (Map.Entry<Long, Double> entry : Qh_week_unit.entrySet()) {
					Long key = entry.getKey();
					Double value = entry.getValue();
					if(Qh_week.containsKey(key)) {
						Double valSum = Qh_week.get(key) + value;
						Qh_week.put(key, valSum);
					} else {
						Qh_week.put(key, value);
					}
				}
			}
			heatingNetEnergyUnit.setValues(Qh_week);
			heatingNetEnergyUnit.setTimeUnit(TimeMeasure.WEEK.name());
			heatingNetEnergyUnit.setAggregated(true);
			break;
		case HOUR:
			TreeMap<Long,Double> Qh_hour = new TreeMap<Long, Double>();
			for(MeasuredUnit unit : heatingUnits) {
				TreeMap<Long,Double> Qh_hour_unit = unit.getValues();
				for (Map.Entry<Long, Double> entry : Qh_hour_unit.entrySet()) {
					Long key = entry.getKey();
					Double value = entry.getValue();
					if(Qh_hour.containsKey(key)) {
						Double valSum = Qh_hour.get(key) + value;
						Qh_hour.put(key, valSum);
					} else {
						Qh_hour.put(key, value);
					}
				}
			}
			heatingNetEnergyUnit.setValues(Qh_hour);
			heatingNetEnergyUnit.setTimeUnit(TimeMeasure.HOUR.name());
			heatingNetEnergyUnit.setAggregated(false);
			break;
		default: 
			throw new AssertionError();
		}

		return heatingNetEnergyUnit;
	}
	
	/**
	 * Greenhouse Gas Emissions (eKPI2.1)
	 * @param eKPIs
	 * @param combustibles
	 * @return
	 */
	public static Map<EnergyResourceType, Double> calculateEcologicGreenhouseGasEmissionValue(
			EnergyKeyPerformanceIndicators eKPIs, List<Combustible> combustibles) {
		Map<EnergyResourceType, Double> greenhouseGasEmissions = new HashMap<EnergyResourceType, Double>();
		double eKPI11a 	= eKPIs.getFinalEnergyHeating();
		double eKPI12 	= eKPIs.getFinalEnergyCooling();
		
		List<String> assignedCombustibleIds = eKPIs.getCombustibleIds();
		Map<String, Double> percentagesPerHeatingCombustible = eKPIs.getPercentagePerHeatingCombustible();
		Map<String, Double> percentagesPerCoolingCombustible = eKPIs.getPercentagePerCoolingCombustible();
		
//		separated results for CO2, SO2 and NOX
		double eKPI21CO2 = 0.0;
		double eKPI21SO2 = 0.0;
		double eKPI21NOX = 0.0;
		
		for(Combustible combustible : combustibles) { // iterate over all energy resources
			String id = combustible.getId();
			if(assignedCombustibleIds.contains(id)) { // check if energy resource is selected
				double percentagePerHeating = percentagesPerHeatingCombustible.containsKey(id) ? percentagesPerHeatingCombustible.get(id) : 0.0;
				double percentagePerCooling = percentagesPerCoolingCombustible.containsKey(id) ? percentagesPerCoolingCombustible.get(id) : 0.0;
				
				double conversionFactorCO2 = combustible.getEmissionCO2();
				double conversionFactorSO2 = combustible.getEmissionSO2();
				double conversionFactorNOX = combustible.getEmissionNOX();
				
//				consumption of final energy heating and cooling with
				double intermediateSum = (((percentagePerHeating/100) * eKPI11a) + ((percentagePerCooling /100) * eKPI12));
				
				eKPI21CO2 += conversionFactorCO2 * intermediateSum;
				eKPI21SO2 += conversionFactorSO2 * intermediateSum;
				eKPI21NOX += conversionFactorNOX * intermediateSum;

			}
		}
		
		greenhouseGasEmissions.put(EnergyResourceType.CO2, eKPI21CO2);
		greenhouseGasEmissions.put(EnergyResourceType.SO2, eKPI21SO2);
		greenhouseGasEmissions.put(EnergyResourceType.NOX, eKPI21NOX);
		return greenhouseGasEmissions;		
	}
	
	/**
	 * eKPI 2.1
	 * @param eKPI11a
	 * @param eKPI12
	 * @param conversionFactorsOfEnergyResource
	 * @param percentagesCO2 list of percentages with values per hundred (e.g. 55)
	 * @param percentagesSO2 list of percentages with values per hundred (e.g. 15)
	 * @param percentagesNOX list of percentages with values per hundred (r.g. 30)
	 * @return A map with three key-value pairs: 0 = CO2; 1 = SO2; 2 = NOX
	 */
	@Deprecated
	public static Map<EnergyResourceType, Double> calculateEcologicGreenhouseGasEmissionValue(
			double eKPI11a, double eKPI12, 
			Map<EnergyResourceType, List<Double>> conversionFactorsOfEnergyResource,
			Map<EnergyResourceType, List<Double>> percentagesPerEnergyResource) {
		Map<EnergyResourceType, Double> greenhouseGasEmissions = new HashMap<EnergyResourceType, Double>();
		double eKPISum = eKPI11a + eKPI12;
		
		for (Map.Entry<EnergyResourceType, List<Double>> entry : conversionFactorsOfEnergyResource.entrySet()) {
			EnergyResourceType energyResource = entry.getKey();
			List<Double> conversionFactors = entry.getValue();
			
//			Formel hat sich geaendert
			switch(energyResource) {
			case CO2:
				double eKPI21CO2 = 0.0;
				for(int i = 0; i < conversionFactors.size(); i++) {
					eKPI21CO2 += (conversionFactors.get(i) * (percentagesPerEnergyResource.get(EnergyResourceType.CO2).get(i) / 100) * eKPISum);
					eKPI21CO2 = roundToReadableValue(eKPI21CO2);
				}
				greenhouseGasEmissions.put(energyResource, eKPI21CO2);
				break;
			case SO2:
				double eKPI21SO2 = 0.0;
				for(int i = 0; i < conversionFactors.size(); i++) {
					eKPI21SO2 += (conversionFactors.get(i) * (percentagesPerEnergyResource.get(EnergyResourceType.SO2).get(i) / 100) * eKPISum);
					eKPI21SO2 = roundToReadableValue(eKPI21SO2);
				}
				greenhouseGasEmissions.put(energyResource, eKPI21SO2);
				break;
			case NOX:
				double eKPI21NOX = 0.0;
				for(int i = 0; i < conversionFactors.size(); i++) {
					eKPI21NOX += (conversionFactors.get(i) * (percentagesPerEnergyResource.get(EnergyResourceType.NOX).get(i) / 100) * eKPISum);
					eKPI21NOX = roundToReadableValue(eKPI21NOX);
				}
				greenhouseGasEmissions.put(energyResource, eKPI21NOX);
				break;
			default: throw new AssertionError("The energy resource ("+energyResource+") is not supported");
			}
		}	
		return greenhouseGasEmissions;
	}
	
	public static double calculateInvestmentCostsOfBuildingElement(double costsPerSquareMeter, double area) {
		double result = costsPerSquareMeter * area;
		//return roundToReadableValue(result);
		return result;
	}
	
	/**
	 * eKPI 6.1
	 * @param costsPerBuildingElements
	 * @return
	 */
	public static double calculateInvestmentCosts(List<Double> costsPerBuildingElements) {
		double sum = 0.0;
		for(Double costsPerBuildingElement : costsPerBuildingElements) {
			sum += costsPerBuildingElement;
		}
		//return roundToReadableValue(sum);
		return sum;
	}
	
	/**
	 * eKPI6.2
	 * @param allAvailableCombustibles
	 * @param eKPIs
	 * @return
	 */
	public static double calculateEnergyRelatedOperationalCosts(
			List<Combustible> allAvailableCombustibles,
			EnergyKeyPerformanceIndicators eKPIs) {
		double operationalCosts 			= 0.0;
		
		double sumEnergyCosts 				= 0.0;
		
		double eKPI11a 						= eKPIs.getFinalEnergyHeating();
		double eKPI12 						= eKPIs.getFinalEnergyCooling();
		Integer years						= eKPIs.getYears();
		double priceIncreaseInPercentage	= eKPIs.getPriceIncrease();
		double discountRateInPercentage		= eKPIs.getDiscountRate();
		
		List<String> assignedCombustibleIds = eKPIs.getCombustibleIds();
		Map<String, Double> percentagesPerHeatingCombustible = eKPIs.getPercentagePerHeatingCombustible();
		Map<String, Double> percentagesPerCoolingCombustible = eKPIs.getPercentagePerCoolingCombustible();
		
		double ec	= 0.0;
		double bc	= 0.0;
		
		//Calculation of cost per combustible for one year		
		for(Combustible combustible : allAvailableCombustibles) { // iterate over all energy resources
			String id = combustible.getId();
			if(assignedCombustibleIds.contains(id)) { // check if energy resource is selected
				double percentagePerHeating = percentagesPerHeatingCombustible.containsKey(id) ? percentagesPerHeatingCombustible.get(id) : 0.0;
				double percentagePerCooling = percentagesPerCoolingCombustible.containsKey(id) ? percentagesPerCoolingCombustible.get(id) : 0.0;
				double pi 	= ((percentagePerHeating/100) * eKPI11a) + ((percentagePerCooling /100) * eKPI12);
				double cvi	= pi / combustible.getHeatingValueHi();
				double eci	= cvi * combustible.getPricePerUnit();
				double bci	= combustible.getBasePricePerAnno()*100; //marioG price is given in €, but we need a uniform calculation in cent first
				
				ec += eci;
				bc += bci;

			}
		}
		
		//Calculation of cost for all combustible for one year
		sumEnergyCosts	= ec + bc;
		

		operationalCosts = calculatePriceIncreaseAndDiscountRate(sumEnergyCosts, priceIncreaseInPercentage,
																discountRateInPercentage, years);		
		
		
//		energy price is in Cent (ct) => convert to Euro (€)
		operationalCosts = operationalCosts/100;
		
//		return roundToReadableValue(operationalCosts);
		return operationalCosts;
	}
	
	public static double calculateNonEnergyRelatedOperationalCostsForBuildingShellPerYear(
			double buildingShellArea, int timesPerYear, double performanceValue, double hourlyRate) {
		double result = (timesPerYear * (buildingShellArea * performanceValue * hourlyRate));
		//return roundToReadableValue(result);
		return result;
	}
	
	public static double calculateNonEnergyRelatedOperationalCostsForWindowsPerYear(
			double windowArea, int timesPerYear, double performanceValue, double hourlyRate) {
		double result = (timesPerYear * (windowArea * performanceValue * hourlyRate));
		//return roundToReadableValue(result);
		return result;
	}
	
	public static double calculateNonEnergyRelatedOperationalCostsForMaintenancePerYear(double investmentCosts, double maintenancePercentage){
		//return roundToReadableValue(investmentCosts * maintenancePercentage/100);
		return investmentCosts * maintenancePercentage/100;
	}
	
	
	public static double calculateLifecycleCosts(double investmentCosts, double energyRelatedCosts, double nonEnergyRelatedCosts) {
		double lifecycleCosts 			= investmentCosts + energyRelatedCosts + nonEnergyRelatedCosts;	
		//return roundToReadableValue(lifecycleCosts);
		return lifecycleCosts;
	}
	
	/**
	 * eKPI6.3
	 * @return
	 */
	public static double calculateNonEnergyRelatedOperationalCosts(EnergyKeyPerformanceIndicators eKPIs) {
		double 	cleaningSum 						= 0.0;
		double 	windowArea							= eKPIs.getWindowArea();
		int 	windowCleaningTimesPerYear 			= eKPIs.getWindowCleaningTimesPerYear();
		double 	windowHourlyRate 					= eKPIs.getWindowHourlyRate();
		double 	windowPerformanceValue 				= eKPIs.getWindowPerformanceValue();
		
		double 	buildingShellArea					= eKPIs.getBuildingShellArea();
		int 	buildingShellCleaningTimesPerYear 	= eKPIs.getBuildingShellCleaningTimesPerYear();
		double 	buildingShellHourlyRate 			= eKPIs.getBuildingShellHourlyRate();
		double 	buildingShellPerformanceValue 		= eKPIs.getBuildingShellPerformanceValue();
		
		double maintenanceSum						= 0.0;
		double investmentCosts						= eKPIs.getInvestmentCosts();
		double maintenancePercentage				= eKPIs.getMaintenancePercentage();
		
		double oneYearPureSum						= 0.0;		
		double priceIncreaseInPercent				= eKPIs.getNonEnergyPriceIncrease();
		double discountRateInPercent				= eKPIs.getNonEnergyDiscountRate();
		double years 								= eKPIs.getNonEnergyYears();
		
		cleaningSum = 	calculateNonEnergyRelatedOperationalCostsForWindowsPerYear(
								windowArea, windowCleaningTimesPerYear, windowPerformanceValue, windowHourlyRate)
						+ calculateNonEnergyRelatedOperationalCostsForBuildingShellPerYear(
								buildingShellArea, buildingShellCleaningTimesPerYear, buildingShellPerformanceValue, buildingShellHourlyRate);
		maintenanceSum = calculateNonEnergyRelatedOperationalCostsForMaintenancePerYear(investmentCosts, maintenancePercentage);
		
		oneYearPureSum = cleaningSum + maintenanceSum;
		
		
		//return roundToReadableValue( calculatePriceIncreaseAndDiscountRate(oneYearPureSum, priceIncreaseInPercent, discountRateInPercent, years));
		return calculatePriceIncreaseAndDiscountRate(oneYearPureSum, priceIncreaseInPercent, discountRateInPercent, years);	
		
	}
	 
	
	/**
	 * eKPI 3.1a - Overrun
	 * 
	 * @param operativeTemperatureUnits Operative temperatures of space (utilization period previously filtered)
	 * @param coolingSetPointUnit Cooling set point temperatures of space (utilization period previously filtered), if this unit is null then there is no overrun
	 * @return MeasuredUnit temperature overrun
	 * @throws EnergyKeyPerformanceIndicatorException 
	 */
	public static MeasuredUnit calculateTemperatureOverrun(
			MeasuredUnit temperatureUnit, 
			MeasuredUnit coolingSetPointUnit) throws EnergyKeyPerformanceIndicatorException {
		
//		delta T -> time step -> agreement: always hourly
		final int timeStepInHours 					= 	1;
		
		int startYear								=	0;	
		TreeMap<Long, Double> overrunValues			=	new TreeMap<Long,Double>();
		
//		set start year once
		startYear									=	startYear == 0 ? temperatureUnit.getStartYear() : startYear;
			
		if(coolingSetPointUnit != null) {		
			String timeUnit							=	temperatureUnit.getTimeUnit();
			String setpointTimeUnit 					= 	coolingSetPointUnit.getTimeUnit();
			if(!(timeUnit.equalsIgnoreCase(setpointTimeUnit))) throw new EnergyKeyPerformanceIndicatorException("Time steps are not equal!");
	//		delta T -> time step
	//		final int timeStepInHours 					= 	operativeTemperatureUnit.getTimeUnit().equalsIgnoreCase("h") ? 1 : 24;
				
			TreeMap<Long, Double> temperatureValues		=	temperatureUnit.getValues();
			TreeMap<Long, Double> setpointValues		=	coolingSetPointUnit.getValues();
			if(temperatureValues.size() != setpointValues.size()) throw new EnergyKeyPerformanceIndicatorException("Value maps are not equal");
				
	//		iterate over values
			for (Map.Entry<Long, Double> entry : temperatureValues.entrySet()) {
				Long time 				= 	entry.getKey();
					
				Double tempValue 		= 	entry.getValue();
				Double setpointValue	=	setpointValues.get(time);
	
	//			Overrun 	- 	3.1a
				double overrunStep 		=	Math.max(0, (tempValue - setpointValue)) * timeStepInHours;		
					
	//			add it to the values for each time step
				overrunValues.put(time, overrunValues.get(time) != null ? overrunValues.get(time) + overrunStep : overrunStep);	
			}
		} else { // no overruns		
			TreeMap<Long, Double> tempValues		=	temperatureUnit.getValues();				
	//		iterate over values
			for (Map.Entry<Long, Double> entry : tempValues.entrySet()) {
				Long time 				= 	entry.getKey();				
				overrunValues.put(time, 0.0);	
			}	
		}
		MeasuredUnit overrun 	= 	new MeasuredUnit("eKPI3.1-overrun");
		overrun.setAggregated(true);
		overrun.setLabel("eKPI3.1-overrun");
		overrun.setResultCode(23054);
		overrun.setStartYear(startYear);
		overrun.setValueUnit("Kh/deltaT");
		overrun.setValues(overrunValues);
		overrun.setTimeUnit(TimeMeasure.YEAR.name());
		
		return overrun;
	}
	
	/**
	 * eKPI3.1.c
	 * @param temperatureUnit
	 * @param coolingSetPointUnit
	 * @return
	 * @throws EnergyKeyPerformanceIndicatorException
	 */
	public static TreeMap<Integer, Integer> calculateTemperatureOverrunCountStaggered(
			MeasuredUnit temperatureUnit, 
			MeasuredUnit coolingSetPointUnit) throws EnergyKeyPerformanceIndicatorException {
		MeasuredUnit overrunUnit = calculateTemperatureOverrun(temperatureUnit, coolingSetPointUnit);
		TreeMap<Long, Double> values = overrunUnit.getValues();
		
		TreeMap<Integer, Integer> countStaggered = new TreeMap<Integer, Integer>();
		
		final int TWO 	= 2;
		final int FOUR 	= 4;
		final int SIX 	= 6;
		
		for(Map.Entry<Long, Double> entry : values.entrySet()) {
			double value = entry.getValue();
			if(value > 0.0) {
				int key = (int) (Math.round(value));
				if(key == 0) continue; // not interesting
				if(key > TWO && key < FOUR) key = TWO;
				else if(key > FOUR && key < SIX) key = FOUR;
				else if(key > SIX) key = SIX;
				else continue; // < 2 not interesting
				if(countStaggered.containsKey(key)) {
					int newVal = countStaggered.get(key)+1;
					countStaggered.put(key, newVal);
				} else {
					countStaggered.put(key, 1);
				}
			}
		}
		return countStaggered;
	}
	
	/**
	 * eKPI3.1.c
	 * @param temperatureUnit
	 * @param heatingSetPointUnit
	 * @return
	 * @throws EnergyKeyPerformanceIndicatorException
	 */
	public static TreeMap<Integer, Integer> calculateTemperatureUnderrunCountStaggered(
			MeasuredUnit temperatureUnit, 
			MeasuredUnit heatingSetPointUnit) throws EnergyKeyPerformanceIndicatorException {
		MeasuredUnit underrunUnit = calculateTemperatureUnderrun(temperatureUnit, heatingSetPointUnit);
		TreeMap<Long, Double> values = underrunUnit.getValues();
		
		TreeMap<Integer, Integer> countStaggered = new TreeMap<Integer, Integer>();
		
		final int TWO 	= 2;
		final int FOUR 	= 4;
		final int SIX 	= 6;
		for(Map.Entry<Long, Double> entry : values.entrySet()) {
			double value = entry.getValue();
			if(value < 0.0) { // underrun occurs
				int key = (int) (Math.round(Math.abs(value)));
				if(key == 0) continue; // not interesting
				if(key > TWO && key < FOUR) key = TWO;
				else if(key > FOUR && key < SIX) key = FOUR;
				else if(key > SIX) key = SIX;
				else continue; // < 2 not interesting
				if(countStaggered.containsKey(key)) {
					int newVal = countStaggered.get(key)+1;
					countStaggered.put(key, newVal);
				} else {
					countStaggered.put(key, 1);
				}
			}
		}
		return countStaggered;
	}
	
	/**
	 * eKPI 3.1b - Underrun
	 * 
	 * @param operativeTemperatureUnits Operative temperatures of space (utilization period previously filtered)
	 * @param heatingSetPointUnit Heating set point temperatures of space (utilization period previously filtered), if this unit is null then there is no underrun
	 * @return MeasuredUnit temperature underrun
	 * @throws EnergyKeyPerformanceIndicatorException 
	 */
	public static MeasuredUnit calculateTemperatureUnderrun(
			MeasuredUnit temperatureUnit, 
			MeasuredUnit heatingSetPointUnit) throws EnergyKeyPerformanceIndicatorException {
		
//		delta T -> time step -> agreement: always hourly
		final int timeStepInHours 						= 	1;
		
		int startYear									=	0;	
		TreeMap<Long, Double> underrunValues			=	new TreeMap<Long,Double>();
		
//		set start year once
		startYear										=	startYear == 0 ? temperatureUnit.getStartYear() : startYear;
		if(heatingSetPointUnit != null) {	
			String timeUnit								=	temperatureUnit.getTimeUnit();
			String setpointTimeUnit 					= 	heatingSetPointUnit.getTimeUnit();
			if(!(timeUnit.equalsIgnoreCase(setpointTimeUnit))) throw new EnergyKeyPerformanceIndicatorException("Time steps are not equal!");
	//		delta T -> time step
	//		final int timeStepInHours 					= 	operativeTemperatureUnit.getTimeUnit().equalsIgnoreCase("h") ? 1 : 24;
				
			TreeMap<Long, Double> tempValues			=	temperatureUnit.getValues();
			TreeMap<Long, Double> setpointValues		=	heatingSetPointUnit.getValues();
			if(tempValues.size() != setpointValues.size()) throw new EnergyKeyPerformanceIndicatorException("Value maps are not equal");
				
	//		iterate over values
			for (Map.Entry<Long, Double> entry : tempValues.entrySet()) {
				Long time 						= 	entry.getKey();
					
				Double tempValue 				= 	entry.getValue();
				Double setpointValue			=	setpointValues.get(time);
		
	//			Underrun 	- 	3.1b
				double underrunStep 			=	Math.min(0, (tempValue - setpointValue)) * timeStepInHours;
					
	//			add it to the values for each time step
				underrunValues.put(time, underrunValues.get(time) != null ? underrunValues.get(time) + underrunStep : underrunStep);	
			}
		} else { // no overruns		
			TreeMap<Long, Double> tempValues	=	temperatureUnit.getValues();				
	//		iterate over values
			for (Map.Entry<Long, Double> entry : tempValues.entrySet()) {
				Long time 				= 	entry.getKey();				
				underrunValues.put(time, 0.0);	
			}	
		}
		
		MeasuredUnit underrun	=	new MeasuredUnit("eKPI3.1-underrun");
		underrun.setAggregated(true);
		underrun.setLabel("eKPI3.1-underrun");
		underrun.setResultCode(23064);
		underrun.setStartYear(startYear);
		underrun.setValueUnit("Kh/deltaT");
		underrun.setValues(underrunValues);
		underrun.setTimeUnit(TimeMeasure.YEAR.name());
		
		return underrun;
	}
	
	/**
	 * Calculates change in value (v) over the years (t) including a price increase (p) and discount rate (d) using following
	 * formula:
	 * 
	 * 		= v * SUM[ (1+p)^n / (1+d)^n ] 		n=1..t
	 * 
	 * @param baseValue					v
	 * @param priceIncreaseInPercent	p
	 * @param dicountRateInPercent		d
	 * @param years						t
	 * @return
	 */
	private static double calculatePriceIncreaseAndDiscountRate(double baseValue, double priceIncreaseInPercent, 
																double discountRateInPercent, double years)
	{
		double factor 	= 0.0;		
		
		for(int t = 1; t <= years; t++)
		{
			factor += (Math.pow(1+priceIncreaseInPercent/100, t)) / (Math.pow(1+discountRateInPercent/100, t));				
		}
		
		return baseValue * factor;
		
		
	}

}
