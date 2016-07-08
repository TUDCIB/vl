package de.tudresden.bau.cib.vl.core.simulation.energy.exception;

public enum SpaceBoundaryConversionExceptionCode {

	FIRST_LEVEL_SPACE_BOUNDARIES_NOT_FOUND("1st LEvel Space Boundaries can not be found in the IFC model"),
	
	BSPRO_PROBLEM("There occurred a problem in the BSPro software"),
	
	EXECUTION_PROBLEM("The conversion execution failed"),
	
	MODEL_ERROR("There was a problem with the IFC model"),
	
	NO_RESULT_FILE_AVAILABLE("No result file available");

	private final String message;
	
	SpaceBoundaryConversionExceptionCode(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
