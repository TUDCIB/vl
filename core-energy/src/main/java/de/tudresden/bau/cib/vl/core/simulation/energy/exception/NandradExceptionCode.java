package de.tudresden.bau.cib.vl.core.simulation.energy.exception;

public enum NandradExceptionCode {

	CAN_NOT_COPY_PROJECT_FILES("Can't copy simulation model or database directories to Nandrad simulation project"),
	EXECUTION_PROBLEM("The Nandrad execution failed");

	private final String message;
	
	NandradExceptionCode(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
