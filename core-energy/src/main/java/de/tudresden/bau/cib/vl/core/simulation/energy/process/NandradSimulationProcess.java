package de.tudresden.bau.cib.vl.core.simulation.energy.process;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.simulation.SimulationProcess;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationExceptionCode;

public class NandradSimulationProcess extends SimulationProcess {
		
	private File simulationModelXMLFile;
	
	private static Logger LOG = LoggerFactory.getLogger(NandradSimulationProcess.class);
	public static final String DIRECTORY_LOG = "log";
	public static final String DIRECTORY_RESULTS = "results";
	private static final String LOG_FILE_PROGRESS = "progress.txt";
	private Process process;
	
	
	public NandradSimulationProcess(String pathToNandrad, String batchFileName, SimulationProject simulationInformation, File simulationModelXMLFile) {
		super(pathToNandrad, batchFileName, simulationInformation);
		this.simulationModelXMLFile = simulationModelXMLFile;
	}
	
//	@Override
//	public File getSimulationResults() {
//		String simModelFileName = simulationModelXMLFile.getName();
//		String simModelName = simModelFileName.substring(0,simModelFileName.lastIndexOf("."));
////		this directory is created in the directory of the SimModel XML file
//		String newCreatedOutputDirectoryWithResultsAndLog = simulationModelXMLFile.getParentFile()+File.separator+simModelName;
//		String resultsDirectoryPath = newCreatedOutputDirectoryWithResultsAndLog+File.separator+DIRECTORY_RESULTS;
//		File resultsDirectory = new File(resultsDirectoryPath);
//		return resultsDirectory;
//	}

	@Override
	protected void checkPreConditions(
			SimulationProject simulationInformation) throws SimulationException {
		LOG.info("Using project file: {} in Nandrad", this.simulationModelXMLFile.getAbsolutePath());
		if(!new File(pathToBatchFile+batchFileName).exists()) {
			LOG.error("Nandrad Solver doesn't exist in directory "+pathToBatchFile+batchFileName);
			throw new SimulationException("Nandrad Solver doesn't exist in directory "+pathToBatchFile+batchFileName);
		}
//		File simulationResultDir = getSimulationResults();
//		if(simulationResultDir != null)	{
//			LOG.info("set path to simulation results to: "+simulationResultDir);
////			simulationInformation.addPathToResult(simulationResultDir.getAbsolutePath());
//		} else {
//			LOG.warn("no simulation result directory can be set");
//		}
	}

	@Override
	protected void runInvariant(SimulationProject simulationInformation)
			throws SimulationException {

//		..\bin\NandradSolver NandradTestBuilding_5_3_4.xml --les-solver=GMRES(1000) --precond=BAND(1)
//		OPTIONS:
//		--integrator=<integrator-ID> Specify an alternative ODE/DAE integrator.
//		--les-solver=<les-solver-ID> Specify an alternative linear equation system solver.
//		--precond=<preconditioner-ID> Specify an alternative preconditioner for iterative solver.
		
//		IMPORTANT: For getting the simulation status the /WAIT option must be added!!
//		String command = "cmd /c start "+pathToBatchFile+" "+simulationModelXMLFile.getAbsolutePath()+" --les-solver=GMRES(1000) --precond=BAND(1)";
		String command = "cmd /c start /WAIT "+pathToBatchFile+batchFileName+" "+simulationModelXMLFile.getAbsolutePath()+" --les-solver=GMRES(1000) --precond=BAND(1)";
				
		try {		
			LOG.debug("Using Model: {} in directory: {}",
					new Object[]{simulationModelXMLFile.getAbsolutePath(), simulationModelXMLFile.getParentFile().getAbsolutePath()});
			LOG.debug("The used Nandrad command is: {}", command);

			process = Runtime.getRuntime().exec(command);
			
			int val = process.waitFor();
			if(val != 0) {
				LOG.error("NANDRAD crashed");
				throw new SimulationException(SimulationExceptionCode.EXECUTION_PROBLEM);				
			}
			LOG.info("Nandrad finished (result code: {})", val);
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
//		File resultsDirectory = getSimulationResults();
//		if(resultsDirectory.exists()) {
//			TODO do some check that files have results
			
//			String path = resultsDirectory.getAbsolutePath();
//			LOG.info("Simulation results saved in '"+path+"'");
//			File resultDirectory = new File(resultsDirectory.getAbsolutePath());
//			File[] results = resultDirectory.listFiles();
//			for(File resultFile : results) {
//				simulationInformation.addPathToResult(resultFile.getAbsolutePath());
//			}
//		}
		return simulationInformation;
	}
	
    class StreamGobbler extends Thread {
    	InputStream is;
    	String outputPath;

    	StreamGobbler(InputStream is, String outputPath) {
    		this.is = is;
    		this.outputPath = outputPath;
    	}

    	public void run() {
    		try { 			
    			FileOutputStream fos = new FileOutputStream(new File(outputPath));
    			byte buf[] = new byte[1024];
    			int len;
    			while((len = is.read(buf))>0) {
    				fos.write(buf, 0, len);
    			}
    			fos.close();
    		} catch (IOException ioe) {
    			LOG.error(ioe.getMessage());
    		}
    	}
    }

}
