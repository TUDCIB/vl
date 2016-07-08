package de.tudresden.bau.cib.vl.core.simulation.energy.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcspatialstructureelement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation.TYPE;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.service.AbstractSimulationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SimulationMatrixException;
import de.tudresden.bau.cib.vl.core.simulation.energy.process.Bim2SimToolProcess;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationExceptionCode;
import de.tudresden.bau.cib.vl.core.tool.Tool;

public abstract class AbstractEnergySimulationService extends
		AbstractSimulationService implements EnergySimulationService {

	protected ConfigurationService configurationService;
	protected FileService fileService;
	protected TemplateService templateService;
	protected OntologyService ontologyService;
	protected SimulationMatrixService simulationMatrixService;
	
	public static final String IBK_FILENAME_PREFIX = "ISES_project";
	public static final String SIM_ENVIRONMENT_DIR = "simEnvironment";

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractEnergySimulationService.class);

	public abstract SimulationProject startEnergySimulationForZones(
			SimulationProject simulationProject, OntologyModel ontologyModel,
			Set<String> locationIds, Ifc2x3DataModelProxy ifcModel)
			throws SimulationException;

	protected File createNandradProjectFile(
			String simulationProjectDirectoryDestination,
			List<ConstructionTemplate> constructions,
			Ifc2x3DataModelProxy ifcModel) throws SimulationException {
		LOG.info("Starting energy simulation");

		final String JAR_BIM2SIM = "Ifc2Nandrad.jar";
		
		// TODO get only the used climate templates!

		try {
			String pathToIfcFile = FileService.findFirstFileByFileExtension(simulationProjectDirectoryDestination, "ifc").getAbsolutePath();

			String binDirectory = simulationProjectDirectoryDestination + "bin"+ File.separator;
			String binIfc2Nandrad = binDirectory + "Ifc2Nandrad"+ File.separator;

			// Start BIM2SIM tool
			Bim2SimToolProcess process = new Bim2SimToolProcess(binIfc2Nandrad, JAR_BIM2SIM, pathToIfcFile, null);
			// (3) START BIM2SIM CONVERSION
			Tool<Bim2SimToolProcess,File> tool = new Tool<Bim2SimToolProcess, File>(process, 10, 600);
			tool.startExecution();
			File nandradSimulationModelFile = tool.getOutput();
			if (!nandradSimulationModelFile.exists())
				throw new SimulationException(SimulationExceptionCode.NO_SIMULATION_MODEL);

			// File nandradSimulationModelFile = new
			// File(pathToIfcFile.replace("ifc", "nandrad"));
			// nandradSimulationModelFile.createNewFile();

			return nandradSimulationModelFile;
		} catch (Exception te) {
			LOG.error(te.getMessage(), te);
			throw new SimulationException(SimulationExceptionCode.CANNOT_CREATE_SIMULATION_MODEL, te);
		}
	}

	/**
	 * Get related storey and building of rooms.
	 * 
	 * @param guids
	 * @param ifcModel
	 * @return The "super" GUIDs of the requested locations. This means if only
	 *         a room will be simulated this method search the building storey
	 *         and the building in which the room is located. In case of a
	 *         requested simulation of a storey it returns the building etc.
	 * @throws IfcException
	 */
	protected Set<String> retrieveGlobalIdsForIFC2NandradConverter(
			Collection<String> guids, Ifc2x3DataModelProxy ifcModel)
			throws IfcException {
		Set<String> newGuidSet = new HashSet<String>();
		for (String guid : guids) {
			EIfcspatialstructureelement spatialStructureElement = ifcModel
					.getIfcEntity(guid, EIfcspatialstructureelement.class);
			if (spatialStructureElement instanceof EIfcspace) {
				EIfcspace space = (EIfcspace) spatialStructureElement;
				EIfcbuilding building = ifcModel.getBuildingOfSpace(space);
				String buildingGuid = ifcModel.getGlobalId(building);
				newGuidSet.add(buildingGuid);
				Set<EIfcbuildingstorey> buildingStoreys = ifcModel
						.getBuildingStoreysOfSpace(space);
				for (EIfcbuildingstorey storey : buildingStoreys) {
					String buildingStoreyGuid = ifcModel.getGlobalId(storey);
					newGuidSet.add(buildingStoreyGuid);
				}
			} else if (spatialStructureElement instanceof EIfcbuildingstorey) {
				EIfcbuildingstorey storey = (EIfcbuildingstorey) spatialStructureElement;
				EIfcbuilding building = ifcModel.getBuildingOfStorey(storey);
				String buildingGuid = ifcModel.getGlobalId(building);
				newGuidSet.add(buildingGuid);
			}
			newGuidSet.add(guid);
		}
		return newGuidSet;
	}
	
	/**
	 * Search an input file from a specific file type. 
	 * @param simProject
	 * @param fileType
	 * @return The resulting file.
	 * @throws IllegalStateException If there are multiple such files in the simulation project.
	 */
	protected File findInputFileByType(SimulationProject simProject, TYPE fileType) {
		Set<FileInformation> inputFiles = simProject.getInputFiles();
		File resultFile = null;
		for(FileInformation fi : inputFiles) {
			if(fi.getFileType() == fileType) {
				if(resultFile != null) {
					throw new IllegalStateException("Found multiple files from the kind: "+fileType+" in the simulation project");
				}
				resultFile = new File(fi.getFilePath());
			}
		}
		return resultFile;
	}
	
	@Override
	public FileInformation saveSimulationMatrixToSimulationProject(TSimulationMatrix matrix, Integer userId, SimulationProject simProject) throws URISyntaxException, SimulationMatrixException, IOException {
		String tempDirectory = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		String fileName = UUID.randomUUID().toString()+"."+SimulationMatrixService.EXTENSION;
		String filePath = tempDirectory+fileName;

		File temporaryfile = new File(filePath);
		simulationMatrixService.saveMatrix(temporaryfile.toURI(), matrix);
		FileInformation fi = addSimulationResource(userId, simProject, SimulationProject.DEFAULT_RELATIVE_INPUT_DIR, temporaryfile);
		temporaryfile.delete();
		return fi;
	}

	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public void setOntologyService(OntologyService ontologyService) {
		this.ontologyService = ontologyService;
	}

	public void setSimulationMatrixService(
			SimulationMatrixService simulationMatrixService) {
		this.simulationMatrixService = simulationMatrixService;
	}

}
