package de.tudresden.bau.cib.vl.core.exception.code;

public enum ToolExceptionCode {

	EXECUTION_ERROR("There was an error in the execution"),
	
	INITIALIZATION_ERROR("The tool can not be initialized"),
	
	NO_OUTPUT_AVAILABLE("No output available"),
	
	THREAD_ERROR("There occurred a thread error"),
	
	TIMEOUT("The timeout was reached"),
	
	TOOL_NOT_FOUND("The tool was not found");

	private final String message;
	
	ToolExceptionCode(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
