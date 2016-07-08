package de.tudresden.bau.cib.vl.core.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.exception.ToolException;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;

/**
 * @author Ken
 * 
 * Controls all simulations by storing them in a map and provides cancellation, waiting and starting of executions.
 *
 */
public abstract class SimulationController<T extends SimulationProcess> implements Simulation {
	
	protected ExecutorService executorService;
	protected int waitMaxTimeInMinutes = 60 * 24;
	protected String pathToBatchFile;
	protected String batchFileName;
	private Map<Integer, List<Future<SimulationProject>>> futureOfSimulationIdMap;
	protected UserService userService;
	
	protected static final Logger LOG = LoggerFactory.getLogger(SimulationController.class);
	
	
	public SimulationController(String pathToBatchFile, String batchFileName, int maxNumberOfThreads, int waitMaxTimeInMinutes) {
		this.pathToBatchFile = pathToBatchFile;
		this.batchFileName = batchFileName;
		this.waitMaxTimeInMinutes = waitMaxTimeInMinutes;
		executorService = Executors.newFixedThreadPool(maxNumberOfThreads);	
		futureOfSimulationIdMap = new HashMap<Integer, List<Future<SimulationProject>>>();
	}

	protected abstract  List<T> init(SimulationProject simulationInformation, String pathToProjectFile) throws SimulationException;
	
	public final boolean cancel(SimulationProject simulationInformation, boolean mayInterruptIfRunning) {
		boolean isCancelled = false;
		Integer simulationId = simulationInformation.getId();
		List<Future<SimulationProject>> futures = futureOfSimulationIdMap.get(simulationId);
		for(Future<SimulationProject> future : futures) {
			isCancelled &= future.cancel(mayInterruptIfRunning);
			if(isCancelled) {
				futureOfSimulationIdMap.remove(simulationId);
				LOG.info("Simulation with ID {} cancelled.", simulationId);
			}
		}
		return isCancelled;
	}
	
	/**
	 * Starts the execution and waits for the given time by {@link #waitTimeInMinutes}
	 * @throws ToolException
	 */
	public final void start(SimulationProject simulationInformation, String pathToProjectFile) throws SimulationException {
		List<T> processes = init(simulationInformation, pathToProjectFile);
		List<Future<SimulationProject>> futures = new ArrayList<Future<SimulationProject>>();
		for(T process : processes) {
			LOG.info("Start execution of simulation with ID '{}", simulationInformation.getId());
			Future<SimulationProject> future = executorService.submit(process);	
			futures.add(future);			
		}
		futureOfSimulationIdMap.put(simulationInformation.getId(), futures);
	}
	
	public final void stopImmediately() {
		executorService.shutdownNow();
		StringBuilder sb = new StringBuilder();
		sb.append("Executor service stopped immediately. Not executed simulations:\n");
		for(Map.Entry<Integer, List<Future<SimulationProject>>> entry : futureOfSimulationIdMap.entrySet()) {
			sb.append("'"+entry.getKey()+"'\n");
		}
		LOG.info(sb.toString());
		futureOfSimulationIdMap.clear();
		futureOfSimulationIdMap = new HashMap<Integer, List<Future<SimulationProject>>>();
	}
	
	public final void waitAndStop() throws ToolException {
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		    	executorService.shutdown();
		        try {
					if (!executorService.awaitTermination(waitMaxTimeInMinutes, TimeUnit.MINUTES)) { //optional *
						LOG.warn("Executor did not terminate in the specified time."); //optional *
					    List<Runnable> droppedTasks = executorService.shutdownNow(); //optional **
					    LOG.warn("Executor was abruptly shut down. {} tasks will not be executed.", droppedTasks.size()); //optional **
					}
				} catch (InterruptedException e) {
					LOG.error(e.getMessage(),e);
				} finally {
					StringBuilder sb = new StringBuilder();
					sb.append("Executor service stopped. Not executed simulations:\n");
					for(Map.Entry<Integer, List<Future<SimulationProject>>> entry : futureOfSimulationIdMap.entrySet()) {
						sb.append("'"+entry.getKey()+"'\n");
					}
					LOG.info(sb.toString());
					futureOfSimulationIdMap.clear();
					futureOfSimulationIdMap = new HashMap<Integer, List<Future<SimulationProject>>>();
				}
		    }
		});
	}
	
	public final SimulationProject getResult(SimulationProject simulationInformation) throws SimulationException {
		Integer simulationId = simulationInformation.getId();
		List<Future<SimulationProject>> futures = futureOfSimulationIdMap.get(simulationId);
		if(futures == null) throw new SimulationException("No futures for simulation identifier '"+simulationId+"'");
		for(Future<SimulationProject> future : futures) {
			try {
				SimulationProject result = future.get(waitMaxTimeInMinutes, TimeUnit.MINUTES);
	//			if(result != null) {
	//				futureOfSimulationIdMap.remove(simulationId);
	//			}
				return result;
			} catch (InterruptedException e) {
				throw new SimulationException(e);
			} catch (ExecutionException e) {
				throw new SimulationException(e);
			} catch (TimeoutException e) {
				throw new SimulationException(e);
			}
		}
		return null;
	}
	
	public final boolean isRunning(int simId) {
		boolean isRunning = false;
		List<Future<SimulationProject>> futures = futureOfSimulationIdMap.get(simId);
		for(Future<SimulationProject> future : futures) {
			isRunning = isRunning || (!future.isDone());
		}
		return isRunning;
	}
	
	public Set<Integer> listSimulations() {
		return futureOfSimulationIdMap.keySet();
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}

