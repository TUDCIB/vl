package de.tudresden.bau.cib.vl.core.tool;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.exception.ToolException;
import de.tudresden.bau.cib.vl.core.exception.code.ToolExceptionCode;

/**
 * General class for external tools e.g. BSPro or simulation solvers to start it on the command line.
 * 
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 * @param <P> process type
 * @param <T> process result type
 *
 */
public final class Tool<P extends ToolProcess<T>, T> {
	
	protected ExecutorService executorService;
	protected T result = null;
	protected int waitMaxTimeInMinutes = 60 * 24;
	private Future<T> future;
	protected P process;
	
	protected static final Logger LOG = LoggerFactory.getLogger(Tool.class);
	
	public Tool(P process, int maxNumberOfThreads, int waitMaxTimeInMinutes) {
		this.process = process;
		this.waitMaxTimeInMinutes = waitMaxTimeInMinutes;
		executorService = Executors.newFixedThreadPool(maxNumberOfThreads);	
	}
	
	/**
	 * Starts the execution 
	 * @throws ToolException
	 */
	public void startExecution() throws ToolException {		
		future = executorService.submit(process);			
	}
	
	public void stopExecutionImmediately() {
		executorService.shutdownNow();
	}
	
	/**
	 * Wait until tool is ready. No new process can be started.
	 * @throws ToolException
	 */
	public void waitAndStopExecution() throws ToolException {
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		    	executorService.shutdown();
		        try {
					if (!executorService.awaitTermination(waitMaxTimeInMinutes, TimeUnit.MINUTES)) { //optional *
						LOG.warn("Executor did not terminate in the specified time."); //optional *
					    List<Runnable> droppedTasks = executorService.shutdownNow(); //optional **
					    LOG.warn("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed."); //optional **
					}
				} catch (InterruptedException e) {
					LOG.error(e.getMessage());
				}
		    }
		});
	}
	
	/**
	 * Get the output and wait for the given time by {@link #waitTimeInMinutes}
	 * @return The result
	 * @throws ToolException
	 */
	public T getOutput() throws ToolException {
		if(result != null) return result;
		try {
			result = future.get(waitMaxTimeInMinutes, TimeUnit.MINUTES);
			return result;
		} catch (InterruptedException e) {
			throw new ToolException(ToolExceptionCode.THREAD_ERROR, e);
		} catch (ExecutionException e) {
			throw new ToolException(ToolExceptionCode.EXECUTION_ERROR, e);
		} catch (TimeoutException e) {
			throw new ToolException(ToolExceptionCode.TIMEOUT, e);
		}		
	}
}
