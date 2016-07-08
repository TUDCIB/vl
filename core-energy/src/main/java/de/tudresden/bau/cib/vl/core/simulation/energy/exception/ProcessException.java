package de.tudresden.bau.cib.vl.core.simulation.energy.exception;

public class ProcessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8983856564886966875L;

	public ProcessException(String message) {
		super(message);
	}
	
	public ProcessException(Throwable t) {
		super(t);
	}
}
