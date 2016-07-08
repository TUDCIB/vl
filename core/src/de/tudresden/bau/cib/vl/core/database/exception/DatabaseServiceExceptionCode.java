package de.tudresden.bau.cib.vl.core.database.exception;

public enum DatabaseServiceExceptionCode {

	NO_RESULTS_SET_FOUND("No result set was found"),
	DELETE_FAILED("The delete statement failed"),
	INSERT_FAILED("The insert statement failed"),
	UPDATE_FAILED("The update statement failed");

	private final String message;
	
	DatabaseServiceExceptionCode(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}