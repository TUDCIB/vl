package de.tudresden.bau.cib.vl.core.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject.STATUS;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;

public interface SimulationService {

	/**
	 * Checks the status f the simulation project.
	 *
	 * @param simProject the sim id
	 * @return true, if simulation is running
	 */
	STATUS checkSimulationStatus(SimulationProject simProject);
	
	void checkCompletedSimulationsAndStoreResults(int userId);
	
	/**
	 * Stores the simulation results in the simulation project.
	 * @param userId
	 * @param simProject
	 */
	void storeSimulationResultsInSimulationProject(Integer userId, SimulationProject simProject) throws IOException;
	
	/**
	 * Updates the status of the simulation dependent if it is still running, completed or crashed.
	 * 
	 * @param simProject The simulation project which will be updated.
	 * @return The updated simulation information.
	 * @throws SimulationException 
	 */
	SimulationProject updateSimulationProject(SimulationProject simProject) throws SimulationException;

	void addSimulationResource(Integer userId, SimulationProject simProject,
			String relativeDirPath, InputStream stream, String inputFileName)
			throws IOException;

	FileInformation addSimulationResource(Integer userId, SimulationProject simProject,
			String relativeDirPath, File file) throws IOException;

	List<FileInformation> addSimulationResources(Integer userId, SimulationProject simProject,
			String relativeDirPath, Collection<File> files) throws IOException;

	/**
	 * Copies all input files from an existing simulation project to a new one.
	 * @param userId The user.
	 * @param simProjectName The name of the new simulation project.
	 * @param parent Parent project.
	 * @param existingSimProject The template.
	 * @return The new simulation project.
	 * @throws IOException When a copy error occurs.
	 */
	SimulationProject createFromSimulationProject(Integer userId,
			String simProjectName, Project parent,
			SimulationProject existingSimProject) throws IOException;

	void removeSimulationInputResource(Integer userId,
			SimulationProject simProject, File file);

	void removeSimulationOutputResource(Integer userId,
			SimulationProject simProject, File file);
	
}
