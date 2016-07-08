package de.tudresden.bau.cib.vl.core.simulation.energy.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.FileInformation.TYPE;
import de.tudresden.bau.cib.vl.core.database.domain.OptimizationSimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject.STATUS;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.simulation.energy.cloud.CloudClient;
import de.tudresden.bau.cib.vl.core.simulation.energy.cloud.CloudClient.Status;
import de.tudresden.bau.cib.vl.core.simulation.energy.cloud.CloudClient.Type;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicatorsToBe;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.AbstractEnergySimulationService;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationExceptionCode;

public class RemoteEnergySimulationServiceImpl extends AbstractEnergySimulationService {


	private static final Logger LOG = LoggerFactory.getLogger(RemoteEnergySimulationServiceImpl.class);

	private CloudClient client;


	@Override
	public SimulationProject startEnergySimulationForZones(SimulationProject simProject,
			OntologyModel eeBIM, Set<String> locationIds,
			Ifc2x3DataModelProxy ifcModel) throws SimulationException {
		throw new UnsupportedOperationException("Currently not supported");
	}



	/**
	 * Creates a new remote simulation project in the cloud if not already present and set a cloud ID in the database.
	 * @param projectName
	 * @return Remote ID of the created project.
	 * @throws SimulationException 
	 */
	public SimulationProject createNewRemoteSimulationProject(SimulationProject simProject) throws SimulationException {
		if(StringUtils.isEmpty(simProject.getRemoteId())) {
			String name = simProject.getName()+"_"+UUID.randomUUID().toString()+"_"+simProject.getId();
			String remoteId = client.createNewProject(name);
			simProject.setRemoteId(remoteId);
			LOG.info("Created remote simulation project [{}] in the cloud", simProject);
			return updateSimulationProject(simProject);	
		} else {
			throw new IllegalArgumentException("Simulation project is already created in the cloud");
		}
	}

	@Override
	public void storeSimulationResultsInSimulationProject(Integer userId, SimulationProject simProject) throws IOException {
		if(simProject.getOutputFiles().size() > 0) return; // results are available
		String projectId = simProject.getRemoteId();
		STATUS status = checkSimulationStatus(simProject);
		if(status != STATUS.COMPLETED) return;
		InputStream stream = client.getPassiveSimulationResults(projectId);
		if(stream != null) {
			// first: copy it to the temp directory
			String tempDirectory = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY)+"/"+simProject.getId();
			File outputZipFile = new File(tempDirectory+UUID.randomUUID().toString()+".zip");
			FileUtils.copyInputStreamToFile(stream, outputZipFile);
			
			List<File> resultFiles = FileService.unzip(outputZipFile.getAbsolutePath(), tempDirectory);
			for(File resultFile : resultFiles) {
				// second: upload each file to the user repository
				addSimulationResource(userId, simProject, "/"+SimulationProject.DEFAULT_RELATIVE_OUTPUT_DIR, resultFile);		
	//			userService.uploadFileToSimulationProject(userId, simProject, "/"+SimulationProject.DEFAULT_RELATIVE_OUTPUT_DIR, resultFile);
			}
			// delete archive and all files in temp directory
			boolean isDeleted = outputZipFile.delete();
			for(File f : resultFiles) {
				isDeleted &= f.delete();
			}
			// delete temp dir
			isDeleted &= new File(tempDirectory).delete();
			LOG.debug("Temporary archive file: {} with simulation results was deleted {}", 
					new Object[]{outputZipFile, isDeleted ? "successfully" : "not successfully"});
		} else {
			LOG.warn("No result files for simulation project: {}", new Object[]{simProject});
		}
	}

	@Override
	public void startPassiveSimulation(final Integer userId, final SimulationProject simProject) throws SimulationException, IOException {
		if(simProject.getSimulationTypeId() != SimulationProject.TYPE.NANDRAD_PASSIV) {
			throw new IllegalArgumentException("It is not a passive simulation");
		}
		LOG.info("Starting passive simulation process -> preparing data for cloud");
		File tmpArchive = null;
		final String PATH_TO_TEMP_DIR = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		Integer simId = simProject.getId();
		
		File ifcFile = findInputFileByType(simProject, TYPE.IFC);
		File rdfFile = findInputFileByType(simProject, TYPE.RDF);

		if(ifcFile == null || rdfFile == null) throw new IllegalStateException("There must be at least 1 IFC file & 1 RDF file in the simulation project");
		File tmpDir = new File(PATH_TO_TEMP_DIR+simId);
		FileService.createDirectory(tmpDir);
		String ifcTempFilePath = FileService.copyFileToDirectory(ifcFile, tmpDir);
		String rdfTempFilePath = FileService.copyFileToDirectory(rdfFile, tmpDir);
		String newIfcName = IBK_FILENAME_PREFIX+".ifc";
		String newRdfName = IBK_FILENAME_PREFIX+".rdf";
		Path movedIfcFilePath = Files.move(Paths.get(ifcTempFilePath), Paths.get(tmpDir+File.separator+newIfcName));
		Path movedRdfFilePath = Files.move(Paths.get(rdfTempFilePath), Paths.get(tmpDir+File.separator+newRdfName));
		String zipFilePath = PATH_TO_TEMP_DIR+simId+".zip";
		List<File> projectFiles = Arrays.asList(movedIfcFilePath.toFile(), movedRdfFilePath.toFile());
		FileService.zip(zipFilePath, projectFiles.toArray(new File[projectFiles.size()]));
		tmpArchive = new File(zipFilePath);
		try {
			LOG.info("[Start passive simulation] - create project in cloud");
			// create it in the cloud
			createNewRemoteSimulationProject(simProject);
			final String simProjectId = simProject.getRemoteId();
			if(StringUtils.isEmpty(simProjectId)) throw new NullPointerException("Simulation project have no remote identifier!");
			// upload building data
			boolean isUploaded = client.uploadPassiveSimulationData(simProjectId, tmpArchive);
			if(!isUploaded) throw new IllegalArgumentException("Problem occurred by uploading data to cloud");
			LOG.info("[Start passive simulation] - Uploaded  input data to cloud for simulation: {}", 
					new Object[]{simProject});
			LOG.info("[Start passive simulation] - prepare project in cloud");
			// prepare simulation (BIM2SIM)
			Status isPrepared = client.prepareSimulationdata(simProjectId);
			if(isPrepared != Status.OK) throw new SimulationException(SimulationExceptionCode.EXECUTION_PROBLEM, isPrepared.name());
			
			// simulation preparation needs some time but the application should not freeze
			Runnable cloudPreparationRunnable = new Runnable() {

				@Override
				public void run() {
					Status status = Status.NOTSTARTED;
					// wait for ready preparation
					Status prepareStatus = null;
					try {
						Integer timeInterval = 1000*60*60*24; // default 1 day waiting at all if non configuration time available
						int counter = 1000*60*5;  // wait 5 minutes
						while((prepareStatus == null) || (prepareStatus != Status.DONE && timeInterval > 0)) {
							Thread.sleep(counter);
							timeInterval -= counter;
							prepareStatus = client.checkCloudNode(simProjectId, Type.PREPARE_DATA);
							LOG.debug("[Start passive simulation] - checked project [{}] status in cloud -> {}", 
									new Object[]{simProject, prepareStatus});
						}
						LOG.info("[Start passive simulation] - preparation of project [{}] complete", 
								new Object[]{simProject});
					} catch(InterruptedException e) {
						LOG.error("[Start passive simulation] - Thread problem: {}", e.getMessage(), e);
					}
					if(prepareStatus == Status.DONE) {	
						try {
							LOG.info("[Start passive simulation] - run project in cloud");
							// START PASSIVE SIMULATION
							status = client.startPassiveSimulation(simProjectId);
							if(status == Status.ERROR) throw new SimulationException(SimulationExceptionCode.EXECUTION_PROBLEM, status.name());
							LOG.info("[Start passive simulation] - Started passive simulation: {}", simProject);
						} catch(SimulationException e) {
							LOG.error("[Start passive simulation] - Problem occured by starting the passive simulation: {} {}", 
									new Object[]{SimulationExceptionCode.EXECUTION_PROBLEM, status.name()}, e);
						}
					}
				}				
			};
			// start it
			new Thread(cloudPreparationRunnable).start();
			// set simulation status
			simProject.setSimulationStatus(STATUS.RUNNING);
			// update the status in the database
			updateSimulationProject(simProject);
		} catch (Exception e) {
			throw e;
		} finally {
			FileService.deleteFile(tmpArchive);
			FileService.deleteFile(tmpDir);
		}
	}
	
	@Override
	public void startPassiveSimulation(SimulationProject simulationProject,
			String nandradModelFilePath) throws SimulationException {
		throw new UnsupportedOperationException("Currently not implemented");
	}

	@Override
	public STATUS checkSimulationStatus(SimulationProject project) {
		String id = project.getRemoteId();
		if(StringUtils.isNotEmpty(id)) {
			SimulationProject.TYPE type = project.getSimulationTypeId();
			CloudClient.Type cloudType = null;
			switch(type) {
			case NANDRAD_PASSIV:
				cloudType = CloudClient.Type.PASSIVE_SIMULATION;
				break;
			case THERAKLES:
				cloudType = CloudClient.Type.SENSITIVITY_ANALYSIS;
				break;
			case BEGIN:
				break;
			case CFD:
				break;
			case NANDRAD:
				break;
			default:
				break;
			}
			
			Status status = client.checkCloudNode(id, cloudType);
			if(status == null) return SimulationProject.STATUS.NEW;
			SimulationProject.STATUS simStatus = null;
			switch(status) {
			case IDLE:
				simStatus = SimulationProject.STATUS.NEW;
				break;
			case NOTSTARTED:
				simStatus = SimulationProject.STATUS.NEW;
				break;
			case RUNNING:
				simStatus = SimulationProject.STATUS.RUNNING;
				break;
			case ERROR:
				simStatus = SimulationProject.STATUS.ERROR;
				break;
			case DONE:
				simStatus = SimulationProject.STATUS.COMPLETED;
				break;
			default: break;
			}
			return simStatus;
		} else {
			return SimulationProject.STATUS.ERROR;
		}
	}

	@Override
	public SimulationProject startEnergySimulationForBuilding(SimulationProject simProject, List<ConstructionTemplate> constructions,
			OntologyModel ontologyModel, Ifc2x3DataModelProxy ifcModel) throws SimulationException {
		throw new UnsupportedOperationException("Currently not supported");
	}
	
	@Override
	public void startSensitivityAnalysis(SimulationProject sensitivityProject) throws SimulationException, IOException {
		if(sensitivityProject.getSimulationTypeId() != SimulationProject.TYPE.THERAKLES) {
			throw new IllegalArgumentException("It is not a sensitivity analysis simulation");
		}
		if(StringUtils.isEmpty(sensitivityProject.getRemoteId())) throw new SimulationException("The simulation project needs a remote identifier!");
		String projectId = sensitivityProject.getRemoteId();
		LOG.info("Starting sensitivity analaysis process -> preparing data for cloud");
		
		File tmpArchive = null;
		final String PATH_TO_TEMP_DIR = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		Integer simId = sensitivityProject.getId();
		LOG.debug("[Start sensitivity analysis] - create temporary directory"); 
		File tmpDir = new File(PATH_TO_TEMP_DIR+simId);
		File matrixFile = findInputFileByType(sensitivityProject, TYPE.SIMMATRIX);
		FileService.createDirectory(tmpDir);
		String matrixFilePath = FileService.copyFileToDirectory(matrixFile, tmpDir);
		String newMatrixName = IBK_FILENAME_PREFIX+".simmatrix";
		Path movedMatrixFilePath = Files.move(Paths.get(matrixFilePath), Paths.get(tmpDir+File.separator+newMatrixName));
		String zipFilePath = PATH_TO_TEMP_DIR+simId+".zip";
		List<File> projectFiles = Arrays.asList(movedMatrixFilePath.toFile());
		FileService.zip(zipFilePath, projectFiles.toArray(new File[projectFiles.size()]));
		tmpArchive = new File(zipFilePath);
		LOG.info("[Start sensitivity analysis] - upload simulation matrix to project: {}", sensitivityProject); 
		boolean isUploaded = client.uploadSimulationMatrix(projectId, tmpArchive);
		if(!isUploaded) throw new IllegalArgumentException("Problem occurred by uploading sensitivity data to cloud");
		LOG.info("[Start sensitivity analysis] - run sensitivity project [{}] in cloud", sensitivityProject); 
		Status status = client.startSensitivityAnalysis(projectId);
		if(status == Status.ERROR) throw new SimulationException(SimulationExceptionCode.EXECUTION_PROBLEM, status.name());
		LOG.info("[Start sensitivity analysis] - Started sensitivity analysis: {}", sensitivityProject);
	}

	@Override
	public void copyContentFromPassiveProjectToSensitiveProject(SimulationProject passiveSimProject,
			SimulationProject sensitivityProject) throws IOException {
		// nothing to do here
		LOG.debug("The remote web service have to copy the resources to the new project");
	}
	
	@Override
	public void copyContentFromSensitiveProjectToSensitiveProject(
			SimulationProject oldSimProject,
			SimulationProject newSimProject) throws IOException {
		// nothing to do here
		LOG.debug("The remote web service have to copy the resources to the new project");
	}
	
	@Override
	public boolean optimizeGreenBuildingDesign(Integer userId, Ifc2x3DataModelProxy ifcModel,
			OntologyModel ontologyModel, OptimizationSimulationProject simProject,
			Map<String, EnergyKeyPerformanceIndicatorsToBe> roomGuidToeKPIToBe) {
		throw new UnsupportedOperationException("Currently not implemented");
	}
	
	private String getToken() {
		String token = configurationService.getProperty(PROPERTIES.CLOUD_TOKEN);
		return token;
	}

	private URL getBaseURL() throws MalformedURLException {
		String url = configurationService.getProperty(PROPERTIES.CLOUD_URL);
		return new URL(url);
	}

	@Override
	public void setConfigurationService(
			ConfigurationService configurationService) {
		super.setConfigurationService(configurationService);
		// create Cloud client
		URL url = null;
		String token = getToken();
		try {
			url = getBaseURL();
			URI uri = url.toURI();
			client = new CloudClient(uri, token);
		} catch (Exception e) {
			LOG.error("URL: {} leads to problems", new Object[]{url}, e);
		}
	}
}
