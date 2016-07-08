package de.tudresden.bau.cib.vl.core.simulation.energy.database.dao;

import java.util.List;

import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;


public interface EnergyKeyPerformanceIndicatorDao {

	EnergyKeyPerformanceIndicators getEKPIBySimulationId(Integer simulationId);
	List<EnergyKeyPerformanceIndicators> getAllEnergyKeyPerformanceIndicators();
	Integer insertEnergyKeyPerformanceIndicators(EnergyKeyPerformanceIndicators eKPIs);
	void updateEnergyKeyPerformanceIndicators(EnergyKeyPerformanceIndicators eKPIs);
}
