package de.tudresden.bau.cib.vl.core.simulation.energy.optimization;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicatorsToBe;

public final class ConstructionSelector {
	
	public enum TYPE {
		OUTER_WALL,
		INTERNAL_WALL,
		WINDOW,
		SLAB,
		DOOR
		;
	}

	/**
	 * Retrieves the best suitable constructions regarding thermal transmittance values (ttv) and costs (c).
	 * 
	 * @param constructions
	 * @param constructionType
	 * @param eKPIToBe
	 * @return
	 */
	public static Set<ConstructionTemplate> retrieveOptimalConstructions(
			Collection<ConstructionTemplate> constructions, TYPE constructionType, 
			EnergyKeyPerformanceIndicatorsToBe eKPIToBe) {
		
		Set<ConstructionTemplate> possibleConstructions = new HashSet<>();
		final double maximumThermalTransmittance = getThermalTransmittanceValue(constructionType, eKPIToBe);
		final double maximumCosts = getCostsValue(constructionType, eKPIToBe);

		constructions.forEach(construction -> {
			double thermalTransmittanceValue = construction.getUValue();
			double costs = construction.getCostsPerSquareMeter();
			// take all constructions which are within the specified ranges
			if(thermalTransmittanceValue <= maximumThermalTransmittance && costs <= maximumCosts) {
				possibleConstructions.add(construction);
			}
		});

		return possibleConstructions;
	}
	
	/**
	 * Use this method for optimization if only the resources with minimum values of costs and 
	 * u-value are requested (and not all resources where the ranges fit).
	 * This means: it removes all the constructions where ttv and c are both higher than compared in constructions. 
	 * By removing it takes the following approach (x reference construction, y = compared construction):
	 * <p>
	 * 	<ul>
	 * 		<li>Remove y if: <code>ttv(x) < ttv(y) & c(x) < c(y) </code></li>
	 * </ul>
	 * </p>
	 * @param constructionType
	 * @param eKPIToBe
	 * @param possibleConstructions
	 * @return
	 */
	private static Set<ConstructionTemplate> selectMostAppropriateConstructions(
			TYPE constructionType,
			EnergyKeyPerformanceIndicatorsToBe eKPIToBe,
			Set<ConstructionTemplate> possibleConstructions) {
		Set<ConstructionTemplate> optimalConstructions = new HashSet<>();
		
		// take all constructions where value < eKPIToBe
		
		possibleConstructions.forEach(x -> {
			final double x_ttv = x.getUValue();
			final double x_c = x.getCostsPerSquareMeter();
			if(optimalConstructions.size() > 0) {
				
//				optimalConstructions
//				.stream()
//				.collect(Collectors.toMap(
//						y -> y.getUValue(),
//						y -> y.getCostsPerSquareMeter(),
//						(y_ttv, y_c) -> {
//							
//						}));
				
				optimalConstructions.forEach(y -> {
					double y_ttv = y.getUValue();
					double y_c = y.getCostsPerSquareMeter();
					if((x_ttv < y_ttv) && (x_c < y_c)) {
						// remove y from optimalConstructions and take x
						optimalConstructions.remove(y);
						optimalConstructions.add(x);
					} else {
						// don't take x
					}
				});
			} else {
				optimalConstructions.add(x);
			}
		});
		
		return optimalConstructions;
	}
	
	private static float getThermalTransmittanceValue(TYPE constructionType, EnergyKeyPerformanceIndicatorsToBe eKPIToBe) {
		switch(constructionType) {
		case OUTER_WALL: return eKPIToBe.getOuterWallThermalTransmittances();
		case INTERNAL_WALL: return eKPIToBe.getInnerWallThermalTransmittances();
		case SLAB: return eKPIToBe.getSlabThermalTransmittances();
		case WINDOW: return eKPIToBe.getWindowThermalTransmittances();
		case DOOR: return eKPIToBe.getDoorThermalTransmittances();
		default: return .0f;
		}
	}
	
	private static float getCostsValue(TYPE constructionType, EnergyKeyPerformanceIndicatorsToBe eKPIToBe) {
		switch(constructionType) {
		case OUTER_WALL: return eKPIToBe.getOuterWallCosts();
		case INTERNAL_WALL: return eKPIToBe.getInnerWallCosts();
		case SLAB: return eKPIToBe.getSlabCosts();
		case WINDOW: return eKPIToBe.getWindowCosts();
		case DOOR: return eKPIToBe.getDoorCosts();
		default: return .0f;
		}
	}
}
