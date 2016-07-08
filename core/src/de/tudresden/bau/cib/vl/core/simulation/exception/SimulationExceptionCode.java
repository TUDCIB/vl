package de.tudresden.bau.cib.vl.core.simulation.exception;

public enum SimulationExceptionCode {

	CANNOT_CREATE_SIMULATION_MODEL("It is not possible to create the simulation model"),
	EXECUTION_PROBLEM("The execution failed"),
	NO_SIMULATION_MODEL("No Simulation Model"),
	NO_SIMULATION_RESULT("No simulation results available"),
	PROBLEM_BY_PREPARING_SIMPROJECT("A problem occurs by preparing the simulation project");

	private final String message;
	
	SimulationExceptionCode(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
