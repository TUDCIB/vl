package de.tudresden.bau.cib.vl.core.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.UserRepository;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject.STATUS;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;

public abstract class AbstractSimulationService implements SimulationService {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractSimulationService.class);
	
	protected UserService userService;
	
	
	@Override
	public void addSimulationResource(Integer userId, SimulationProject simProject, String relativeDirPath, InputStream stream, String inputFileName) throws IOException {		
		LOG.debug("Add resource: {} to simulation project: {} in directory: {}", 
				new Object[]{inputFileName, simProject, relativeDirPath});
		FileInformation fileInfo = userService.uploadFileAsStreamToSimulationProject(userId, simProject, relativeDirPath, stream, inputFileName);
		if(relativeDirPath.equalsIgnoreCase(SimulationProject.DEFAULT_RELATIVE_INPUT_DIR)) {
			simProject.addInputFiles(fileInfo);
		} else {
			simProject.addOutputFiles(fileInfo);
		}
		userService.updateSimulationProject(simProject);
	}
	
	/**
	 * Adds a resource (input or output) to the simulation project and copy the file to the corresponding folder.
	 * @param userId
	 * @param simProject
	 * @param relativeDirPath
	 * @param file
	 * @throws IOException
	 */
	@Override
	public FileInformation addSimulationResource(Integer userId, SimulationProject simProject, String relativeDirPath, File file) throws IOException {		
		LOG.debug("Add resource: {} to simulation project: {} in directory: {}", 
				new Object[]{file, simProject, relativeDirPath});
		FileInformation fileInfo = userService.uploadFileToSimulationProject(userId, simProject, relativeDirPath, file);
		if(relativeDirPath.equalsIgnoreCase(SimulationProject.DEFAULT_RELATIVE_INPUT_DIR)) {
			simProject.addInputFiles(fileInfo);
		} else {
			simProject.addOutputFiles(fileInfo);
		}
		userService.updateSimulationProject(simProject);
		return fileInfo;
	}

	/**
	 * Adds (input or output) resources to the simulation project.
	 * @param userId
	 * @param simProject
	 * @param relativeDirPath
	 * @param files
	 * @throws IOException
	 */
	@Override
	public List<FileInformation> addSimulationResources(Integer userId, SimulationProject simProject, 
			String relativeDirPath, Collection<File> files) throws IOException {		
		List<FileInformation> fileInfos = new ArrayList<FileInformation>();
		for(File file : files) {
			FileInformation fi = addSimulationResource(userId, simProject, relativeDirPath, file);
			fileInfos.add(fi);
		}
		return fileInfos;
	}
	
	@Override
	public void checkCompletedSimulationsAndStoreResults(int userId) {
		UserRepository ur = userService.getUserRepositoryByUserId(userId);
		Set<Project> projects = ur.getProjects();
		for(Project project : projects) {
			Set<SimulationProject> simProjects = project.getSimProjects();
			for(SimulationProject simProject : simProjects) {
				if(simProject.getSimulationStatus() != STATUS.COMPLETED) {
					STATUS status = checkSimulationStatus(simProject);	
					
					if(status == STATUS.COMPLETED) {
						LOG.info("Simulation: {} completed", new Object[]{simProject});
						// download results if not already stored
						try {
							storeSimulationResultsInSimulationProject(userId, simProject);
						} catch (IOException e) {
							LOG.error("Error when downloading the result files and store it in the simulation project: {}", 
									e.getMessage(), e);
							status = STATUS.ERROR;
						}
					}
					simProject.setSimulationStatus(status);
					userService.updateSimulationProject(simProject);
				}
			}
		}
	}
	
	@Override
	public SimulationProject createFromSimulationProject(Integer userId, String simProjectName, 
			Project parent, SimulationProject existingSimProject) throws IOException {
		SimulationProject newSimProject = userService.createSimulationProject(parent, simProjectName);
		// copy all necessary files from previous sim project
		Set<FileInformation> inputFiles = existingSimProject.getInputFiles();
		for(FileInformation fi : inputFiles) {
			addSimulationResource(userId, newSimProject, 
					SimulationProject.DEFAULT_RELATIVE_INPUT_DIR, new File(fi.getFilePath()));
		}
		return newSimProject;
	}
	
	@Override
	public void removeSimulationInputResource(Integer userId, SimulationProject simProject, File file) {
		LOG.debug("Remove resource: {} from simulation project: {} in directory: {}", 
				new Object[]{file, simProject, simProject.getPathInputDirectory()});
		Set<FileInformation> inputFiles = simProject.getInputFiles();
		for(FileInformation inputFile : new ArrayList<FileInformation>(inputFiles)) {
			if(inputFile.getFilePath().equals(file.getAbsolutePath())) {
				inputFiles.remove(inputFile);
				userService.deleteFile(inputFile);
			}
		}
		userService.updateSimulationProject(simProject);
	}
	
	@Override
	public void removeSimulationOutputResource(Integer userId, SimulationProject simProject, File file) {
		LOG.debug("Remove resource: {} from simulation project: {} in directory: {}", 
				new Object[]{file, simProject, simProject.getPathOutputDirectory()});
		Set<FileInformation> outputFiles = simProject.getOutputFiles();
		for(FileInformation outputFile : new ArrayList<FileInformation>(outputFiles)) {
			if(outputFile.getFilePath().equals(file.getAbsolutePath())) {
				outputFiles.remove(outputFile);
				userService.deleteFile(outputFile);
			}
		}
		userService.updateSimulationProject(simProject);
	}
	
	@Override
	public SimulationProject updateSimulationProject(SimulationProject simProject) throws SimulationException {
		userService.updateSimulationProject(simProject);
		return simProject;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
