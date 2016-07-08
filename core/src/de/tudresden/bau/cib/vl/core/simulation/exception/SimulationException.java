package de.tudresden.bau.cib.vl.core.simulation.exception;


/**
 * @author Ken
 *
 */
public class SimulationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3875117221267949729L;

	public SimulationException(String msg) {
		super(msg);
	}
	
	public SimulationException(Throwable t) {
		super(t);
	}
	
	SimulationExceptionCode simulationExceptionCode;
	private Exception hiddenException;
	private String message;

	public SimulationException(String error, Exception exception) {
		super(error, exception);
		this.hiddenException = exception;
		this.message = error;
	}
	
	public SimulationException(SimulationExceptionCode simulationExceptionCode) {
		this.simulationExceptionCode = simulationExceptionCode;
	}
	
	public SimulationException(SimulationExceptionCode simulationExceptionCode, String details) {
		this(simulationExceptionCode);
		this.message = simulationExceptionCode.getMessage()+ ":: "+ details;
	}

	public SimulationException(
			SimulationExceptionCode simulationExceptionCode,
			Exception exception) {
		super(simulationExceptionCode.getMessage(), exception);
		this.simulationExceptionCode = simulationExceptionCode;
		this.hiddenException = exception;
	}

	public String getMessage() {
		return (message == null) ? (simulationExceptionCode.getMessage()) : message;
	}

	public Exception getHiddenException() {
		return (hiddenException);
	}
}
