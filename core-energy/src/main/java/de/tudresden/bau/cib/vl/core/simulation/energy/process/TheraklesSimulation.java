package de.tudresden.bau.cib.vl.core.simulation.energy.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.simulation.SimulationController;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;

/**
 * @author Ken
 *
 */
public class TheraklesSimulation extends SimulationController<TheraklesToolProcess> {
	
	public static final String IDENTIFIER = "Energy/Therakles";
	
	private static final Logger LOG = LoggerFactory.getLogger(TheraklesSimulation.class);
	
	
	public TheraklesSimulation(String pathToTherakles, String batchFileName, int maxNumberOfThreads, int waitMaxTimeInMinutes) {
		super(pathToTherakles, batchFileName, maxNumberOfThreads, waitMaxTimeInMinutes);
	}
	
	@Override
	public String getIdentifier() {
		return IDENTIFIER;
	}

	@Override
	protected List<TheraklesToolProcess> init(SimulationProject simulationInformation, String pathToProjectFile) throws SimulationException {
		LOG.debug("Initialize Therakles tool");
		List<TheraklesToolProcess> processes = new ArrayList<TheraklesToolProcess>();
		File simulationModelFile = new File(pathToProjectFile);
		TheraklesToolProcess process = new TheraklesToolProcess(pathToBatchFile, batchFileName, simulationModelFile, simulationInformation);
		processes.add(process);
		return processes;
	}

}
