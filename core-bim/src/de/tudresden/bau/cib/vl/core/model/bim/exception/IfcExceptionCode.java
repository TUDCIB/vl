package de.tudresden.bau.cib.vl.core.model.bim.exception;

public enum IfcExceptionCode {

	PARSE_FILE("File parsing failed");

	private final String message;
	
	IfcExceptionCode(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
