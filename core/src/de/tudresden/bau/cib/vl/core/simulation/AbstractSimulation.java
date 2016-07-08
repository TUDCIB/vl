package de.tudresden.bau.cib.vl.core.simulation;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;

/**
 *
 * @author Ken Baumgaertel
 *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public abstract class AbstractSimulation {

	protected abstract void checkPreConditions(SimulationProject simulationProject) throws SimulationException;
	
	protected abstract void runInvariant(SimulationProject simulationProject) throws SimulationException;
	
	protected abstract SimulationProject checkPostConditions(SimulationProject simulationProject) throws SimulationException;
}
