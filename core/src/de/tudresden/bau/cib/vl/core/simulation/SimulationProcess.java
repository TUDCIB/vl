package de.tudresden.bau.cib.vl.core.simulation;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;

/**
 *
 * @author Ken Baumgaertel
 *	{@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
public abstract class SimulationProcess extends AbstractSimulation implements Callable<SimulationProject> {
	
	protected String pathToBatchFile;
	protected String batchFileName;
	protected SimulationProject simulationInformation;
	
	private static final Logger LOG = LoggerFactory.getLogger(SimulationProcess.class);
	
	
	public SimulationProcess(String pathToBatchFile, String batchFileName, SimulationProject simulationInformation) {
		this.pathToBatchFile = pathToBatchFile;
		this.batchFileName = batchFileName;
		this.simulationInformation = simulationInformation;
	}
	
	/**
	 * Factory method for Simulations.
	 * @throws SimulationException
	 */
	@Override
	public SimulationProject call() throws Exception {
		LOG.debug("Checking simulation preconditions");
		checkPreConditions(simulationInformation);
		LOG.debug("Run simulation invariant");
		runInvariant(simulationInformation);
		LOG.debug("Checking simulation postconditions");
		SimulationProject result = checkPostConditions(simulationInformation);
		LOG.debug("Simulation completed. Result --> {}", 
				new Object[]{result.getPathOutputDirectory()});
		return result;
	}
}
