package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import java.io.File;
import java.net.URL;
import java.util.Set;

public interface DecisionMakingService {

	/**
	 * <p>
	 * Upload a new case to the decision making app of Granlund and returns the URL where it can be visualized.
	 * </p>
	 * @param csvFile The CSV file with the sensitivity data.
	 * @param simulationName A title of the simulation.
	 * @param projectName The name of the current project.
	 * @return The URL to view the sensitivity data.
	 */
	URL uploadCase(File csvFile, String simulationName, String projectName);
	
	/**
	 * <p>
	 * Lists all cases filtered by the project name.
	 * </p>
	 * @param projectName The name of the project to which the cases are assigned. If null is given then all cases will be returned.
	 * @return The URLs of all cases.
	 */
	Set<URL> listAllCases(String projectName);

	String createToken();
}
