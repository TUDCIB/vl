package de.tudresden.bau.cib.vl.core.simulation.energy.database.dao;

import java.util.List;
import java.util.Map;

import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyResults;

public interface EnergyResultsDao {

	EnergyResults getEnergyResults(Integer id);
	EnergyResults getEnergyResultsBySimulationId(Integer simulationId);
	Map<Long, Double> getResultsBetween(Integer simulationId, long startTime, long endTime);
	List<EnergyResults> getAllEnergyResults();
	Integer insertEnergyResults(EnergyResults results);
	void updateEnergyResults(EnergyResults results);
}
