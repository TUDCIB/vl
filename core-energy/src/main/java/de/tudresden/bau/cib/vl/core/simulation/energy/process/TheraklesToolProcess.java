package de.tudresden.bau.cib.vl.core.simulation.energy.process;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.simulation.SimulationProcess;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;

public class TheraklesToolProcess extends SimulationProcess {

	private static final Logger LOG = LoggerFactory.getLogger(TheraklesToolProcess.class);
	
	
/*	############################	
	##		Conventions
	############################*/
	public static final String THERAKLES_FILE_EXTENSION = "rmxml";
	public static final String THERAKLES_RESULT_FILE_EXTENSION = "txt";
	
	private File simulationModelXMLFile = null;
	private Process process;
	
	
	public TheraklesToolProcess(
			String pathToBatchFile, 
			String batchFileName,
			File simulationModelXMLFile, 
			SimulationProject simulationInformation) {
		super(pathToBatchFile, batchFileName, simulationInformation);
		this.simulationModelXMLFile = simulationModelXMLFile;
	}

	@Override
	protected void checkPreConditions(
			SimulationProject simulationInformation) throws SimulationException {
		LOG.info("Using project file "+this.simulationModelXMLFile.getAbsolutePath()+" in Therakles...");
		if(!new File(pathToBatchFile+batchFileName).exists()) {
			LOG.error("Therakles Solver doesn't exist in directory "+pathToBatchFile+batchFileName);
			throw new SimulationException("Therakles Solver doesn't exist in directory "+pathToBatchFile+batchFileName);
		}
	}

	@Override
	protected void runInvariant(SimulationProject simulationInformation)
			throws SimulationException {
//		IMPORTANT: For getting the simulation status the /WAIT option must be added!!
		String command = "cmd /c start /WAIT "+pathToBatchFile + batchFileName + " " + this.simulationModelXMLFile;
//		String command = "cmd /c start "+pathToBatchFile + COMMAND_THERAKLES + " " + this.simulationModelXMLFile;
		LOG.info("Therakles Command > '"+command+"'");
				
		try {		
			process = Runtime.getRuntime().exec(command);
			
			int val = process.waitFor();
			LOG.info("Return value of Therakles process is "+val);
//			simulationInformation.setSimulationStatus(STATUS.COMPLETED);
			
		} catch (IOException e) {
			LOG.error("IOException: "+e.getMessage());
			throw new SimulationException(e);
		} 
		catch (InterruptedException e) {
			LOG.error("InterruptedException: "+e.getMessage());
			throw new SimulationException(e);
		}
	}

	@Override
	protected SimulationProject checkPostConditions(SimulationProject simulationInformation)
			throws SimulationException {
//		File resultFile = getSimulationResults();
//		if(resultFile != null)	{
//			LOG.info("set path to simulation results to: "+resultFile);
////			simulationInformation.addPathToResult(resultFile.getAbsolutePath());
//		} else {
//			LOG.warn("no simulation result file can be set");
//		}
		return simulationInformation;
	}

//	@Override
//	public File getSimulationResults() {
////		Result file wird von Therakles im Ordner des Projektes abgelegt
//		File resultFile = new File(this.simulationModelXMLFile.getAbsolutePath()+"."+THERAKLES_RESULT_FILE_EXTENSION);
//		if(!resultFile.exists()){
//			String s = "Result File "+resultFile+" doesn't exist!";
//			LOG.error(""+s);f
//			return null;
//		}
//		return resultFile;
//	}
	
}
