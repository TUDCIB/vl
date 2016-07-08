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
public class NandradSimulation extends SimulationController<NandradSimulationProcess> {

	public static final String IDENTIFIER = "Energy/Nandrad";
	
	private static final Logger LOG = LoggerFactory.getLogger(NandradSimulation.class);

	
	public NandradSimulation(String pathToNandrad, String batchFileName, int maxNumberOfThreads, int waitMaxTimeInMinutes) {
		super(pathToNandrad, batchFileName, maxNumberOfThreads, waitMaxTimeInMinutes);
	}

	@Override
	public String getIdentifier() {
		return IDENTIFIER;
	}

	@Override
	protected List<NandradSimulationProcess> init(SimulationProject simProject, String pathToProjectFile) throws SimulationException {
		LOG.debug("Initialize Nandrad tool");
		List<NandradSimulationProcess> processes = new ArrayList<NandradSimulationProcess>();
		File simulationModelFile = new File(pathToProjectFile);
		NandradSimulationProcess process = new NandradSimulationProcess(pathToBatchFile, batchFileName, simProject, simulationModelFile);
		processes.add(process);
		return processes;
	}

}
