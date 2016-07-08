package de.tudresden.bau.cib.vl.core.simulation.energy.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcdoor;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcphysicalquantity;
import jsdai.SIfc2x3.EIfcpostaladdress;
import jsdai.SIfc2x3.EIfcquantityarea;
import jsdai.SIfc2x3.EIfcquantityvolume;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcsite;
import jsdai.SIfc2x3.EIfcslab;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcspatialstructureelement;
import jsdai.SIfc2x3.EIfcwall;
import jsdai.SIfc2x3.EIfcwindow;
import jsdai.lang.SdaiException;

import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation.TYPE;
import de.tudresden.bau.cib.vl.core.database.domain.OptimizationSimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject.STATUS;
import de.tudresden.bau.cib.vl.core.exception.ParsingException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy.Quantities;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicatorsToBe;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SimulationMatrixException;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesClimateLocation;
import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesConstruction;
import de.tudresden.bau.cib.vl.core.simulation.energy.optimization.ConstructionSelector;
import de.tudresden.bau.cib.vl.core.simulation.energy.process.NandradSimulation;
import de.tudresden.bau.cib.vl.core.simulation.energy.process.TheraklesSimulation;
import de.tudresden.bau.cib.vl.core.simulation.energy.process.TheraklesToolProcess;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.AbstractEnergySimulationService;
import de.tudresden.bau.cib.vl.core.simulation.energy.tool.LocationIdentifierWriter;
import de.tudresden.bau.cib.vl.core.simulation.energy.transformation.TheraklesSimulationModelGenerator;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationException;
import de.tudresden.bau.cib.vl.core.simulation.exception.SimulationExceptionCode;

/**
 * TODO JavaDoc
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class LocalEnergySimulationService extends AbstractEnergySimulationService {
	
	private NandradSimulation nandradSimulation;
	private Collection<TheraklesSimulation> theraklesSimulations;
	
	private static final String BUNDLE_SIMULATION_IBK_TEMPLATE_DIR = "IBK/";
	private static final String BUNDLE_SIMULATION_PROJECT_TEMPLATE_DIR = BUNDLE_SIMULATION_IBK_TEMPLATE_DIR+"project/";
	private static final String BUNDLE_SIMULATION_BINARIES_TEMPLATE_DIR = BUNDLE_SIMULATION_IBK_TEMPLATE_DIR+"bin/";
	private static final String BUNDLE_SIMULATION_SCRIPTS_TEMPLATE_DIR = BUNDLE_SIMULATION_IBK_TEMPLATE_DIR+"scripts/";
	
	private static final String BUNDLE_NANDRAD_SIMULATION_EXE = BUNDLE_SIMULATION_BINARIES_TEMPLATE_DIR+"Nandrad/NandradSolver.exe";
	private static final String BUNDLE_THERAKLES = BUNDLE_SIMULATION_BINARIES_TEMPLATE_DIR+"Therakles/";
	private static final String BUNDLE_THERAKLES_SIMULATION_EXE = BUNDLE_THERAKLES+"TheraklesSolver.exe";

	private static final String SCRIPT_START_PASSIVE_SIMULATION_MODEL_GENERATION = "passive_simulation_start.py";
	private static final String SCRIPT_NANDRAD_POSTPROCESSING = "passive_simulation_end.py";
	private static final String SCRIPT_THERAKLES_POSTPROCESSING = "analyze_simulation_calculation_eKPI.py";	
	private static final String SCRIPT_CREATE_THERAKLES_PROJECTS = "analyze_simulation_start.py";
	
	private static final String IBK_PASSIVESIM_PROJECT_NAMING_CONVENTION = "ISES_project_passive";
	
	private static final String THERAKLES_PROJECTS_NAMING_CONVENTION = "TheraklesProjects";
	
	private static final Logger LOG = LoggerFactory.getLogger(LocalEnergySimulationService.class);
	
	
	private void initNandradSimulation() throws IOException {
		int maxNumberOfThreads = Integer.valueOf(configurationService.getProperty(PROPERTIES.NUMBER_OF_THREADS));
		int waitMaxTimeInMinutes = Integer.valueOf(configurationService.getProperty(PROPERTIES.WAIT_TIME_IN_MINUTES));
		String tmpDir = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		
		// copy binaries to tmp dir
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		if(bundle != null) { // production mode
			URL nandradSimulationExe = bundle.getResource(BUNDLE_NANDRAD_SIMULATION_EXE);
			FileService.copyFileFromBundle(nandradSimulationExe, tmpDir, bundle);
		} else { // test mode
			FileService.copyFileToDirectory(new File(BUNDLE_NANDRAD_SIMULATION_EXE), new File(tmpDir));
		}
		nandradSimulation = new NandradSimulation(tmpDir, "NandradSolver.exe", maxNumberOfThreads, waitMaxTimeInMinutes);
	}
	
	private TheraklesSimulation initTheraklesSimulation(String pathToBatchFile) throws IOException {
		int maxNumberOfThreads = Integer.valueOf(configurationService.getProperty(PROPERTIES.NUMBER_OF_THREADS));
		int waitMaxTimeInMinutes = Integer.valueOf(configurationService.getProperty(PROPERTIES.WAIT_TIME_IN_MINUTES));
		TheraklesSimulation theraklesSimulation = new TheraklesSimulation(pathToBatchFile, "TheraklesSolver.exe", maxNumberOfThreads, waitMaxTimeInMinutes);
		if(theraklesSimulations == null) theraklesSimulations = new ArrayList<>();
		theraklesSimulations.add(theraklesSimulation);
		return theraklesSimulation;
	}
	
	
	@Override
	public SimulationProject startEnergySimulationForBuilding(SimulationProject simulationProject,
			List<ConstructionTemplate> constructions, OntologyModel ontologyModel,
			Ifc2x3DataModelProxy ifcModel) throws SimulationException {
		LOG.debug("Start energy simulation: {} based on ontology model: {}",
				new Object[]{simulationProject, ontologyModel});		
		try {			
			final String PATH_TO_TEMP_DIR = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
			Integer simId = simulationProject.getId();
			String fileName = simId + "_" + System.currentTimeMillis();
			String simulationProjectDirectory = PATH_TO_TEMP_DIR + fileName + File.separator;
			FileService.createDirectory(new File(simulationProjectDirectory));
			List<String> requestedLocationIds = ifcModel.getBuildingsIDs();
//			create the NANDRAD project structure
			createBIM2SIMPreparationDirectory(simulationProjectDirectory, constructions, ontologyModel.getSourceFilePath(), 
					requestedLocationIds, ifcModel);
			// create NANDRAD project file
			File nandradSimulationModelFile = createNandradProjectFile(simulationProjectDirectory, constructions, ifcModel);	

//			START SIMULATION
			if(nandradSimulation == null) initNandradSimulation();
			nandradSimulation.start(simulationProject, nandradSimulationModelFile.getAbsolutePath());
			simulationProject.setSimulationStatus(STATUS.RUNNING);
			//	update result set path etc. in database
			userService.updateSimulationProject(simulationProject);
			return simulationProject;
		} catch (Exception te) {
			LOG.error(te.getMessage(), te);
			throw new SimulationException(SimulationExceptionCode.CANNOT_CREATE_SIMULATION_MODEL, te);
		}	
	}

	@Override
	public SimulationProject startEnergySimulationForZones(SimulationProject simProject, OntologyModel ontologyModel, Set<String> locationIds, 
			Ifc2x3DataModelProxy ifcModel) throws SimulationException {
		LOG.info("Starting energy simulation for zones of location identifiers: {}", locationIds);

		String pathToTempDir = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		
//		String pathToSimModel = configurationService.getProperty("TEST_THERAKLES_SIM_FILE");

		try {
	//		CREATE THERAKLES CONSTRUCTIONS FROM IFC
			Collection<EIfcbuilding> buildings = ifcModel.getBuildings().values();	
//			FIXME
			List<TheraklesConstruction> constructions = null;
			
			Set<String> pathToSimModelSet = new HashSet<String>();
			
			locationIds = listAllSpaceGuids(locationIds, ifcModel);
			try {				
				List<TheraklesConstruction> constructionsOfRoom = new ArrayList<TheraklesConstruction>();
		//		CREATE THERAKLES SIMULATION MODEL
				for(String locId : locationIds) {	
					EIfcspace space = ifcModel.getSpace(locId);
					if(space == null) throw new SimulationException("No IFC entity with GUID ('"+locId+"')");
					double spaceAreaValue = 0.0;
					double spaceHeightValue = 0.0;
		
					String pathToSimModel = pathToTempDir+"theraklesRoom_"+locId+"."+TheraklesToolProcess.THERAKLES_FILE_EXTENSION;
					LOG.debug("Creating simulation model: {}", new Object[]{pathToSimModel});
					
//					get the quantities
					List<EIfcphysicalquantity> grossVolumeQuantity = ifcModel.getElementQuantity(space, Quantities.GrossVolume);			
					List<EIfcphysicalquantity> grossAreaQuantity = ifcModel.getElementQuantity(space, Quantities.GrossSideArea);
					
					if(grossVolumeQuantity.size() > 0 && grossAreaQuantity.size() > 0) {
						EIfcquantityvolume quantityVolume = (EIfcquantityvolume) grossVolumeQuantity.get(0);
						double volume = quantityVolume.getVolumevalue(quantityVolume);
						LOG.debug("Volume of Space: {} || is: {}", new Object[]{space, volume});
						EIfcquantityarea quantityArea = (EIfcquantityarea) grossAreaQuantity.get(0);
						spaceAreaValue = quantityArea.getAreavalue(quantityArea);
						spaceHeightValue = volume/spaceAreaValue;
					} else {				
						List<EIfcphysicalquantity> grossVolumeQuantity2 = ifcModel.getElementQuantity(space, Quantities.GrossVolume);			
						List<EIfcphysicalquantity> grossAreaQuantity2 = ifcModel.getElementQuantity(space, Quantities.GrossFloorArea);
						if(grossVolumeQuantity2.size() > 0 && grossAreaQuantity2.size() > 0) {
							EIfcquantityvolume quantityVolume = (EIfcquantityvolume) grossVolumeQuantity2.get(0);
							double volume = quantityVolume.getVolumevalue(quantityVolume);
							LOG.debug("Second standardized Volume of Space: {} || is: {}", new Object[]{space, volume});
							EIfcquantityarea quantityArea = (EIfcquantityarea) grossAreaQuantity2.get(0);
							spaceAreaValue = quantityArea.getAreavalue(quantityArea);
							LOG.debug("Second standardized Area of Space: {} || is: {}", new Object[]{space, spaceAreaValue});
							spaceHeightValue = volume/spaceAreaValue;
						}
					}
		
//					site location
					EIfcsite site = ifcModel.getSite();
					String location = null;
		
					if(site.testSiteaddress(null)){
						EIfcpostaladdress address = site.getSiteaddress(null);
						String town = address.getTown(null).toString();
		
						location = town.toLowerCase();
		
					} else {
//						default
						location = "dresden";
					}
		
					TheraklesClimateLocation tcl = new TheraklesClimateLocation("");
					tcl.setTown(location);
					
					for(TheraklesConstruction tc : constructions) {
						if(locId.equals(tc.getIfcSpaceId())) {
							constructionsOfRoom.add(tc);
						}
					}
		
					TheraklesSimulationModelGenerator generator = new TheraklesSimulationModelGenerator(pathToSimModel, tcl, spaceAreaValue, spaceHeightValue);
					generator.buildSimulationModel(constructionsOfRoom.toArray(new TheraklesConstruction[constructionsOfRoom.size()]));
					
					LOG.debug("Adding Simulation model path: {}", new Object[]{pathToSimModel});
					pathToSimModelSet.add(pathToSimModel);
					
					String tmpDir = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
					
					// copy binaries to tmp dir
					Bundle bundle = FrameworkUtil.getBundle(this.getClass());
					if(bundle != null) { // production mode
						URL theraklesSimulationExe = bundle.getResource(BUNDLE_THERAKLES_SIMULATION_EXE);
						FileService.copyFileFromBundle(theraklesSimulationExe, tmpDir, bundle);
					} else { // test mode
						FileService.copyFileToDirectory(new File(BUNDLE_THERAKLES_SIMULATION_EXE), new File(tmpDir));
					}
//					START SIMULATION
					TheraklesSimulation simulation = initTheraklesSimulation(tmpDir);
					simulation.start(simProject, pathToSimModel);
				}
				
//				TODO multiple simulation projects??
				userService.updateSimulationProject(simProject);
				
				return simProject;
			} catch(Exception e) {
				throw new SimulationException(e);
			}
			
		} catch (IfcException ie) {
			LOG.error(ie.getMessage());
		} catch (SdaiException e) {
			LOG.error(e.getMessage());
		}
		return null;				
	}
	
	private Set<String> listAllSpaceGuids(Set<String> locationIds, Ifc2x3DataModelProxy ifcModel) throws IfcException, SdaiException {
//		FILL LOCATIONS
		for(String locId : locationIds) {
			EIfcroot spatialStructureElement = ifcModel.getIfcEntity(locId, EIfcspatialstructureelement.class);
			if(spatialStructureElement instanceof EIfcbuilding) {
				EIfcbuilding building = (EIfcbuilding) spatialStructureElement;
				String buildingGuid = building.getGlobalid(building);
				locationIds.remove(buildingGuid);
				EIfcbuildingstorey[] storeys = ifcModel.getBuildingStoreys(building);
				for(EIfcbuildingstorey storey : storeys) {
					String storeyGuid = storey.getGlobalid(storey);
					if(locationIds.contains(storeyGuid)) locationIds.remove(storeyGuid);
					EIfcspace[] spaces = ifcModel.getSpacesInStorey(storey);
					for(EIfcspace space : spaces) {
						String spaceGuid = space.getGlobalid(space);
						if(!locationIds.contains(spaceGuid)) locationIds.add(spaceGuid);
					}
				}
			} else if(spatialStructureElement instanceof EIfcbuildingstorey) {
				EIfcbuildingstorey storey = (EIfcbuildingstorey) spatialStructureElement;
				String storeyGuid = storey.getGlobalid(storey);
				locationIds.remove(storeyGuid);
				EIfcspace[] spaces = ifcModel.getSpacesInStorey(storey);
				for(EIfcspace space : spaces) {
					String spaceGuid = space.getGlobalid(space);
					if(!locationIds.contains(spaceGuid)) locationIds.add(spaceGuid);
				}
			} else if(spatialStructureElement instanceof EIfcspace) {
//				EIfcspace space = (EIfcspace) spatialStructureElement;
				locationIds.add(locId); 
			}
		}
		return locationIds;
	}

	@Override
	public STATUS checkSimulationStatus(SimulationProject simProject) {	
		Integer simId = simProject.getId();
		STATUS currentStatus = simProject.getSimulationStatus();
		// if it is not running anymore
		if(currentStatus == STATUS.COMPLETED) return STATUS.COMPLETED;
		// if it is running check it and then update the simulation information in the database
		if(nandradSimulation != null) {
			Set<Integer> nandradSimulations = nandradSimulation.listSimulations();
			if(nandradSimulations.contains(simId)) {
				currentStatus = nandradSimulation.isRunning(simId) ? STATUS.RUNNING : STATUS.COMPLETED;
			}
		}
		if(theraklesSimulations != null) {
			for(TheraklesSimulation theraklesSimulation : theraklesSimulations) {
				Set<Integer> theraklesSimulations = theraklesSimulation.listSimulations();
				if(theraklesSimulations.contains(simProject.getId())) {
					currentStatus = theraklesSimulation.isRunning(simId) ? STATUS.RUNNING : STATUS.COMPLETED;
				}
			}
		}
		// the program was closed or another error occurred
		if(nandradSimulation == null && theraklesSimulations == null && currentStatus != STATUS.COMPLETED) {
			currentStatus = STATUS.ERROR;
		}
		simProject.setSimulationStatus(currentStatus);
		userService.updateSimulationProject(simProject);
		return currentStatus;
	}
	
	/**
	 * Creates the needed directory structure for Ifc2Nandrad (or BIM2SIM) and NANDRAD.
	 * @param destinationPath
	 * @param constructions
	 * @param ontologyModel
	 * @param requestedLocationIds
	 * @param ifcModel
	 * @return Path to the IFC file in the created project structure.
	 * @throws IOException
	 * @throws IfcException
	 * @deprecated Veraltet durch neue BIM2SIM Version (since 01.11.2014)
	 */
	@Deprecated
	private String createBIM2SIMPreparationDirectory(
			String destinationPath,
			List<ConstructionTemplate> constructions,
			String ontologyModelFilePath, Collection<String> requestedLocationIds,
			Ifc2x3DataModelProxy ifcModel) throws IOException, IfcException {	
		LOG.debug("Create BIM2SIM project directory with all input files, location identifiers:{} based on ontology model: {}",
				new Object[] {requestedLocationIds, ontologyModelFilePath });
		final String JAR_BIM2SIM = "Ifc2Nandrad.jar";
		final String PATH_TO_DB_CLIMATE = "resources/IBK/resources/DB_Climate/";
		final String PATH_TO_DB_MATERIALS = "resources/IBK/resources/DB_Materials/";
		final String PATH_TO_DB_TEMPLATES = "resources/IBK/resources/DB_Templates/";
				
		final String PATH_TO_BIM2SIM 				=	"resources/tools/Ifc2Nandrad(old)/";
		final String PATH_TO_IFC2NANDRAD_SETTINGS 	=	PATH_TO_BIM2SIM+"/settings";

		LOG.debug("Using {} constructions",
				new Object[] { constructions.size() });

		FileService.createDirectory(new File(destinationPath));
		// (1) WRITE Location identifiers to text file - includes always the
		// GUID of the building
		Set<String> locationIds = new HashSet<String>();
		locationIds = retrieveGlobalIdsForIFC2NandradConverter(requestedLocationIds, ifcModel);
		LOG.debug("Retrieved all needed location identifiers: {} to start the energy simulation from requested locations: {}",
				new Object[] { locationIds, requestedLocationIds });
		File locationIdFile = LocationIdentifierWriter.writeLocationIdentifiers(destinationPath, locationIds);

		if (!locationIdFile.exists())
			throw new FileNotFoundException("Cannot write Location identifiers");

		// (2) Create simulation project directory in temp folder
		String tempDir = fileService.getTempDirectoryPath();
		// copy RDF 
		String rdfFilePath = FileService.copyFileToDirectory(new File(ontologyModelFilePath), new File(tempDir));

		String pathToIfcFile = ifcModel.getSourceFilePath();

		/*		COPY DIRECTORIES WITH FOLLOWING STRUCTURE:
		 *		directory
		 *		|-	bin 
		 *		|	|	-	Ifc2Nandrad
		 *		|	|			|	-	Ifc2Nandrad.jar
		 *		|-	project
		 *		|	|	-	IfcModel1.ifc
		 *		|	|	-	IfcModel1.rdf
		 *		|
		 *		|-	resources
		 *		|	|	-	DB_Climate
		 *		|	|	-	DB_Constructions
		 *		|	|	-	DB_Materials
		 *		|	|	-	DB_Templates
		 *		|	|				|	-	HESMOS_Outputs.xml
		 *		|	|				|	-	HESMOS_Schedules.xml
		 *		|	|				|	-	HESMOS_SpaceTypes.xml
		 *		|	|	-	Ifc2Nandrad
		 *		|	|				|	-	export-datafilter.txt
		 *		|	|				|	-	logging.properties
		 * 		|	|				|	-	ifcconv-config.xml
		 *  	|	|				|	-	mappings-material.txt
		 * 		|	|				|	-	mappings-spacetype.txt
		 * 		|	|				|	-	template.d6p
		 * 		|	|				|	-	template.xml
		 */

		String binDirectory 	=	destinationPath+"bin"+File.separator;
		String projectDir 		=	destinationPath+"project"+File.separator;
		String resourcesDir 	=	destinationPath+"resources"+File.separator;

		String binIfc2Nandrad	=	binDirectory+"Ifc2Nandrad"+File.separator;

		//		-> BIN Ifc2Nandrad directory
		File ifc2NandradDir		=	new File(binIfc2Nandrad);
		//		copy 	Ifc2Nandrad.jar 	to 	bin/Ifc2Nandrad
		FileService.copyFileToDirectory(new File(PATH_TO_BIM2SIM+JAR_BIM2SIM), ifc2NandradDir);

		//		-> PROJECT directory
		File projectDirFile		=	new File(projectDir);
		//		copy 	IfcModel1.ifc 	to 	project dir
		File ifcFile 			= 	null;
		File linkFile 			=	new File(rdfFilePath);

		projectDirFile.mkdir();

		if(pathToIfcFile.startsWith("http")) {
			// download it
			URL url 			= 	new URL(pathToIfcFile);
			InputStream in 		= 	url.openStream();
			String newIfcFileName 	=	pathToIfcFile.substring(pathToIfcFile.lastIndexOf("=")+1);
			String newFilePath 	=	projectDir+File.separator+newIfcFileName; 
			ifcFile				=	new File(newFilePath);
			FileService.copyToFile(in, ifcFile, false);
			pathToIfcFile 		= 	newFilePath;
		} else {
			pathToIfcFile		=	FileService.copyFileToDirectory(new File(pathToIfcFile), projectDirFile);
			ifcFile 			= 	new File(pathToIfcFile);
		}
		//		copy 	IfcModel1.rdf 	to 	project dir
		String linkModelPath	=	FileService.copyFileToDirectory(linkFile, projectDirFile);
		linkFile				=	new File(linkModelPath);
		String ifcFileName 		=	ifcFile.getName();
		int lastDot				=	ifcFileName.lastIndexOf(".");
		String newLinkFileName	=	ifcFileName.substring(0, lastDot)+".rdf";

		linkFile.renameTo(new File(projectDir+newLinkFileName));

		//		-> RESOURCES
		//		File resourcesDirFile	=	new File(resourcesDir);
		File resourcesIfc2NandradDirFile	=	new File(resourcesDir+"Ifc2Nandrad");
		//		copy the whole directory of Ifc2Nandrad settings
		FileService.copyDirectory(new File(PATH_TO_IFC2NANDRAD_SETTINGS), resourcesIfc2NandradDirFile);

		//		copy 	DB_CLimate
		FileService.copyDirectory(PATH_TO_DB_CLIMATE, resourcesDir+"DB_Climate");
		////		copy 	DB_Constructions
		//		String dbConstructionsDir	=	resourcesDir+"DB_Constructions";
		//		for(ConstructionTemplate ctpl : constructionTemplates) {
		//			String ctplPath 	= 	ctpl.getSourceFilePath();
		//			if(ctplPath == null) throw new RuntimeException("Template path is null");
		//			FileManager.copyFileToDirectory(new File(ctplPath), new File(dbConstructionsDir));
		//		}
		//		copy 	DB_Materials
		FileService.copyDirectory(PATH_TO_DB_MATERIALS, resourcesDir+"DB_Materials");
		//		copy	DB_Templates
		FileService.copyDirectory(PATH_TO_DB_TEMPLATES, resourcesDir+"DB_Templates");

		//		copy 	export-datafilter.txt 	to 	resources/Ifc2Nandrad
		File exportDataFilterFile	=	new File(locationIdFile.getAbsolutePath());
		FileService.copyFileToDirectory(exportDataFilterFile, resourcesIfc2NandradDirFile);
		exportDataFilterFile.delete();

		//		COPY DATABASE ALSO TO PROJECT
		//		copy 	DB_CLimate
		FileService.copyDirectory(PATH_TO_DB_CLIMATE, projectDir+"DB_Climate");
		//		copy 	DB_Materials
		FileService.copyDirectory(PATH_TO_DB_MATERIALS, projectDir+"DB_Materials");
		//		copy	DB_Templates
		FileService.copyDirectory(PATH_TO_DB_TEMPLATES, projectDir+"DB_Templates");	

		return pathToIfcFile;
	}
	
	@Override
	public SimulationProject updateSimulationProject(SimulationProject information) throws SimulationException {
		Set<Integer> nandradSimulations = nandradSimulation.listSimulations();
		if(nandradSimulations.contains(information.getId())) {
			information = nandradSimulation.getResult(information);
		} else if(theraklesSimulations != null) {
			for(TheraklesSimulation theraklesSimulation : theraklesSimulations) {
				Set<Integer> theraklesSimulations = theraklesSimulation.listSimulations();
				if(theraklesSimulations.contains(information.getId())) {
					information = theraklesSimulation.getResult(information);
				}
			}
		}
		userService.updateSimulationProject(information);
		return information;
	}

//	public void setNandradSimulation(NandradSimulation nandradSimulation) {
//		this.nandradSimulation = nandradSimulation;
//	}
//
//	public void setTheraklesSimulation(TheraklesSimulation theraklesSimulation) {
//		this.theraklesSimulation = theraklesSimulation;
//	}

	@Override
	public void startPassiveSimulation(SimulationProject simulationProject,
			String nandradModelFilePath)
			throws SimulationException {
		if(!new File(nandradModelFilePath).exists()) throw new SimulationException(SimulationExceptionCode.NO_SIMULATION_MODEL);
		LOG.debug("Start energy simulation: {}",
				new Object[]{simulationProject});		
		try {					
//			START SIMULATION
			if(nandradSimulation == null) initNandradSimulation();
			nandradSimulation.start(simulationProject, nandradModelFilePath);
			simulationProject.setSimulationStatus(STATUS.RUNNING);
			//	update result set path etc. in database
			userService.updateSimulationProject(simulationProject);
		} catch (Exception te) {
			LOG.error(te.getMessage(), te);
			throw new SimulationException(SimulationExceptionCode.CANNOT_CREATE_SIMULATION_MODEL, te);
		}
	}

//	@Override
//	public void startPassiveSimulation(Integer userId,
//			final SimulationProject simProject) throws SimulationException,
//			IOException {
//		LOG.info("Starting passive simulation process -> preparing data for local execution");
//		if(nandradSimulation == null) initNandradSimulation();
//		// simulation preparation needs some time but the application should not freeze
//		Runnable startSimulationRunnable = new Runnable() {
//
//			@Override
//			public void run() {
//				try {
//					final String PATH_TO_TEMP_DIR = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
//					Integer simId = simProject.getId();
//					
//					// COPY all input files to temporary project directory
//					LOG.debug("[Passive simulation] - Copy all required files to temporary directory");
//					File ifcFile = findInputFileByType(simProject, TYPE.IFC);
//					File rdfFile = findInputFileByType(simProject, TYPE.RDF);
//	
//					if(ifcFile == null || rdfFile == null) {
//						throw new IllegalStateException("There must be 1 IFC file & 1 RDF file in the simulation project!");
//					}
//					String id = System.currentTimeMillis()+"_"+simId;					
//					String tmpProjectDirPath = PATH_TO_TEMP_DIR+id;
//					File tmpDir = new File(tmpProjectDirPath);
//					fileService.createDirectory(tmpDir);
//					// we use the temporary directory path as remote identifier for the local machine
//					simProject.setRemoteId(tmpProjectDirPath);
//					
//					// COPY ALL NEEDED RESOURCES TO TEMPORARY SIMULATION PROJECT DIRECTORY
//					preparePassiveSimulationProject(tmpProjectDirPath);
//					
//					final String PROJECT_DIR = tmpProjectDirPath+File.separator+"project";
//					final String SCRIPT_DIR = tmpProjectDirPath+File.separator+"scripts";	
//					final String BIN_DIR = tmpProjectDirPath+File.separator+"bin";	
//					if(!new File(PROJECT_DIR).exists() || !new File(SCRIPT_DIR).exists() || !new File(BIN_DIR).exists()) {
//						throw new SimulationException(SimulationExceptionCode.PROBLEM_BY_PREPARING_SIMPROJECT);
//					}
//					
//					// copy IFC & RDF in /project
//					String ifcTempFilePath = fileService.copyFileToDirectory(ifcFile, tmpDir);
//					String rdfTempFilePath = fileService.copyFileToDirectory(rdfFile, tmpDir);
//					String newIfcName = IBK_FILENAME_PREFIX+".ifc";
//					String newRdfName = IBK_FILENAME_PREFIX+".rdf";
//					Files.move(Paths.get(ifcTempFilePath), Paths.get(PROJECT_DIR+File.separator+newIfcName));
//					Files.move(Paths.get(rdfTempFilePath), Paths.get(PROJECT_DIR+File.separator+newRdfName));
//				
//					// START BIM2SIM
//					LOG.info("[Passive simulation] - start BIM to SIM");
//					String command = "cmd /c start /WAIT java -jar Bim2Sim.jar";
//					Process bim2simProcess = Runtime.getRuntime().exec(command, null, new File(BIN_DIR+File.separator+"Bim2Sim"));						
//					bim2simProcess.waitFor();
//					LOG.info("[Passive simulation] - BIM to SIM ended");
//					
//					// START PASSIVE SIMULATION PREPARATION
//					LOG.info("[Passive simulation] - start Passive Simulation generation");
//					final String scriptFile = SCRIPT_DIR+File.separator+SCRIPT_START_PASSIVE_SIMULATION_MODEL_GENERATION;
//					
//					ProcessBuilder pb = new ProcessBuilder("python", scriptFile);				
//					Process process = pb.start();
//					process.waitFor();
//					LOG.info("[Passive simulation] - Passive Simulation generation ended");
//					
//					final String nandradModelFilePath = PROJECT_DIR+File.separator+IBK_FILENAME_PREFIX+"_passive"+".xml";
//					File nandradFile = new File(nandradModelFilePath);
//					if(nandradFile.exists()) {
//						// START SIMULATION
//						LOG.info("[Passive simulation] - start Nandrad");
//						nandradSimulation.start(simProject, nandradModelFilePath);
//						simProject.setSimulationStatus(STATUS.RUNNING);
//						//	update result set path etc. in database
//						LOG.info("[Passive simulation] - update simulation: {} to Running", new Object[]{simProject.getId()});
//						userService.updateSimulationProject(simProject);
//					} else {
//						throw new SimulationException(SimulationExceptionCode.NO_SIMULATION_MODEL);
//					}
//				
//				} catch (Exception te) {
//					LOG.error(te.getMessage(), te);
//				}
//			}				
//		};
//		// start it
//		Thread simulationThread = new Thread(startSimulationRunnable);
//		simulationThread.start();
//		try {
//			simulationThread.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void startPassiveSimulation(Integer userId,
			final SimulationProject simProject) throws SimulationException,
			IOException {
		LOG.info("Starting passive simulation process -> preparing data for local execution");
		if(nandradSimulation == null) initNandradSimulation();
		// simulation preparation needs some time but the application should not freeze
		Runnable startSimulationRunnable = new Runnable() {

			@Override
			public void run() {
				try {
					// COPY all input files to temporary project directory
					LOG.debug("[Passive simulation] - Copy all required files to temporary directory");
					File ifcFile = findInputFileByType(simProject, TYPE.IFC);
					File rdfFile = findInputFileByType(simProject, TYPE.RDF);
	
					if(ifcFile == null || rdfFile == null) {
						throw new IllegalStateException("There must be 1 IFC file & 1 RDF file in the simulation project!");
					}
					
					// COPY ALL NEEDED RESOURCES TO TEMPORARY SIMULATION PROJECT DIRECTORY
					preparePassiveSimulationProject(simProject);
					
					final String environmentPath = simProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
					
					final String PROJECT_DIR = environmentPath+File.separator+"project";
					final String SCRIPT_DIR = environmentPath+File.separator+"scripts";	
					final String BIN_DIR = environmentPath+File.separator+"bin";	
					if(!new File(PROJECT_DIR).exists() || !new File(SCRIPT_DIR).exists() || !new File(BIN_DIR).exists()) {
						throw new SimulationException(SimulationExceptionCode.PROBLEM_BY_PREPARING_SIMPROJECT);
					}
					
					// copy IFC & RDF in /project
					String newIfcName = IBK_FILENAME_PREFIX+".ifc";
					String newRdfName = IBK_FILENAME_PREFIX+".rdf";
					Files.copy(Paths.get(ifcFile.getAbsolutePath()), Paths.get(PROJECT_DIR+File.separator+newIfcName));
					Files.copy(Paths.get(rdfFile.getAbsolutePath()), Paths.get(PROJECT_DIR+File.separator+newRdfName));					
					// ABSOLUTELY NEEDED! 
					// CHANGE CONFIG to OLD IBK prototype URLs (we don't have the source code for 
					// changing these hard-coded parameters) and saves the overwrite file
					change_RDF_File_To_IBK_Configuration_And_Save(PROJECT_DIR+File.separator+newRdfName);
				
					// START BIM2SIM
					String command = "cmd /c start /WAIT java -jar Bim2Sim.jar";
					LOG.info("[Passive simulation] - start BIM to SIM with command: java -jar "+BIN_DIR+File.separator+"Bim2Sim"+File.separator+"Bim2Sim.jar");
					Process bim2simProcess = Runtime.getRuntime().exec(command, null, new File(BIN_DIR+File.separator+"Bim2Sim"));						
					bim2simProcess.waitFor();
					LOG.info("[Passive simulation] - BIM to SIM ended");
					
					// START PASSIVE SIMULATION PREPARATION
					final String scriptFile = SCRIPT_DIR+File.separator+SCRIPT_START_PASSIVE_SIMULATION_MODEL_GENERATION;
					ProcessBuilder pb = new ProcessBuilder("python", scriptFile);	
					LOG.info("[Passive simulation] - start Passive Simulation generation with script: {}", new Object[]{scriptFile});
					Process process = pb.start();
					process.waitFor();
					LOG.info("[Passive simulation] - Passive Simulation generation ended");
					
					final String nandradModelFilePath = PROJECT_DIR+File.separator+IBK_FILENAME_PREFIX+"_passive"+".xml";
					File nandradFile = new File(nandradModelFilePath);
					if(nandradFile.exists()) {
						// START SIMULATION
						LOG.info("[Passive simulation] - start Nandrad");
						nandradSimulation.start(simProject, nandradModelFilePath);
						simProject.setSimulationStatus(STATUS.RUNNING);
						//	update result set path etc. in database
						LOG.info("[Passive simulation] - update simulation: {} to Running", new Object[]{simProject.getId()});
						userService.updateSimulationProject(simProject);
					} else {
						throw new SimulationException(SimulationExceptionCode.NO_SIMULATION_MODEL);
					}
				
				} catch (Exception te) {
					LOG.error(te.getMessage(), te);
				}
			}				
		};
		// start it
		Thread simulationThread = new Thread(startSimulationRunnable);
		simulationThread.start();
//		try {
//			simulationThread.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	/**
	 * Takes the RDF file from the given path and transform it into the IBK configuration which cannot be changed anymore.
	 * The given file will be overwritten.
	 * <p>
	 * <br/>BIMOnto -> http://141.30.143.53/ontology/ifcOWL
	 * <br/>eeBIMOnto -> http://141.30.143.53/ontology/eeBim
	 * <br/>FORMAT -> N3
	 * </p>
	 * @param filePath The given RDF file
	 * @throws ParsingException If the RDF file cannot be parsed.
	 * @throws IOException If a file cannot be found or created.
	 */
	private void change_RDF_File_To_IBK_Configuration_And_Save(String filePath) 
			throws IOException, ParsingException {
		File oldFile = new File(filePath);
		final String BIMOntoURL = configurationService.getProperty(PROPERTIES.ONTOLOGY_IFC_REMOTE);
		final String eeBIMOntoURL = configurationService.getProperty(PROPERTIES.ONTOLOGY_EEBIM_REMOTE);
		final String FORMAT = configurationService.getProperty(PROPERTIES.SETTING_ONTOLOGY_LANGUAGE);
		if(!StringUtils.equalsIgnoreCase(FORMAT, "N3")) {
			OntologyModel ontoModel = ontologyService.loadOntologyModel(filePath);
			// save the file in the target format
			oldFile = ontologyService.saveRDFToDisk(ontoModel, filePath, "N3", false);
		}
		File newFile = new File(oldFile+"_tmp");

		if(newFile.createNewFile()) {
			// now change all URLs
			BufferedReader br = null;
			BufferedWriter bw = null;
			try {
				br = new BufferedReader(new FileReader(oldFile));
				bw = new BufferedWriter(new FileWriter(newFile));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.contains(BIMOntoURL)) {
						line = line.replace(BIMOntoURL, "http://141.30.143.53/ontology/ifcOWL");
					} else if(line.contains(eeBIMOntoURL)) {
						line = line.replace(eeBIMOntoURL, "http://141.30.143.53/ontology/eeBim");
					}
					bw.write(line+"\n");
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				return;
			} finally {
				try {
					if(br != null) br.close();
				} catch (IOException e) {LOG.debug(e.getMessage(), e);}
				try {
					if(bw != null) bw.close();
				} catch (IOException e) {LOG.debug(e.getMessage(), e);}
			}
		}
		oldFile.delete();
		newFile.renameTo(new File(filePath));
	}
	
	@Override
	public void copyContentFromPassiveProjectToSensitiveProject(
			SimulationProject passiveSimProject,
			SimulationProject sensitivityProject) throws IOException {		
		String srcEnvironmentPath = passiveSimProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
		String tgtEnvironmentPath = sensitivityProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
		FileService.copyDirectory(srcEnvironmentPath, tgtEnvironmentPath);
		
		final String PROJECT_DIR = tgtEnvironmentPath+File.separator+"project";	
		
		// create empty Simulation Matrix directory
		final String SIMMATRIX_DIR = PROJECT_DIR+File.separator+"SimMa";	
		File simmatrixDir = new File(SIMMATRIX_DIR);
		FileService.createDirectory(simmatrixDir);
		
		final String THERAKLES_TARGET_PATH = tgtEnvironmentPath+File.separator+"bin"+File.separator+"Therakles";
		// copy TheraklesSolver in sensitive simulation project
		contextSensitiveCopying(BUNDLE_THERAKLES, THERAKLES_TARGET_PATH);
	}	
	
	@Override
	public void copyContentFromSensitiveProjectToSensitiveProject(
			SimulationProject oldSimProject,
			SimulationProject newSimProject) throws IOException {		
		String srcEnvironmentPath = oldSimProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
		String tgtEnvironmentPath = newSimProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
		FileService.copyDirectory(srcEnvironmentPath, tgtEnvironmentPath);
		
		final String PROJECT_DIR = tgtEnvironmentPath+File.separator+"project";	
		
		// create empty Simulation Matrix directory
		final String SIMMATRIX_DIR = PROJECT_DIR+File.separator+"SimMa";	
		File simmatrixDir = new File(SIMMATRIX_DIR);
		FileService.createDirectory(simmatrixDir);
		
		final String THERAKLES_TARGET_PATH = tgtEnvironmentPath+File.separator+"bin"+File.separator+"Therakles";
		// copy TheraklesSolver in sensitive simulation project
		contextSensitiveCopying(BUNDLE_THERAKLES, THERAKLES_TARGET_PATH);
	}	
	
	@Override
	public boolean optimizeGreenBuildingDesign(Integer userId, Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel,
			OptimizationSimulationProject simProject, Map<String, EnergyKeyPerformanceIndicatorsToBe> roomGuidToeKPIToBe) {
		File ifcFile = findInputFileByType(simProject, TYPE.IFC);
		File rdfFile = findInputFileByType(simProject, TYPE.RDF);
		
		try {		
			// find appropriate energy resources dependent on eKPI TO-BE
			Collection<ConstructionTemplate> constructions = templateService.listConstructionResources();
			
//			OuterWallConstructionOptimizer outerWallOptimizer = new OuterWallConstructionOptimizer(ifcModel, ontologyModel, constructions);
//			outerWallOptimizer.setOntologyService(ontologyService);
//			InnerWallConstructionOptimizer innerWallOptimizer = new InnerWallConstructionOptimizer(ifcModel, ontologyModel, constructions);
//			innerWallOptimizer.setOntologyService(ontologyService);
//			WindowConstructionOptimizer windowOptimizer = new WindowConstructionOptimizer(ifcModel, ontologyModel, constructions);
//			windowOptimizer.setOntologyService(ontologyService);
//			SlabConstructionOptimizer slabOptimizer = new SlabConstructionOptimizer(ifcModel, ontologyModel, constructions);
//			slabOptimizer.setOntologyService(ontologyService);
//			DoorConstructionOptimizer doorOptimizer = new DoorConstructionOptimizer(ifcModel, ontologyModel, constructions);
//			doorOptimizer.setOntologyService(ontologyService);
//			DefaultConstructionOptimizer defaultOptimizer = new DefaultConstructionOptimizer(ifcModel, ontologyModel, constructions);
//			defaultOptimizer.setOntologyService(ontologyService);
//			
//			// build chain of responsibility -> outerWallOptimizer is general accessor
//			outerWallOptimizer.setSuccessor(innerWallOptimizer);
//			innerWallOptimizer.setSuccessor(windowOptimizer);
//			windowOptimizer.setSuccessor(slabOptimizer);
//			slabOptimizer.setSuccessor(doorOptimizer);
//			doorOptimizer.setSuccessor(defaultOptimizer);
			
			// map with affected building elements and constructions which fits best to requirements
			Map<String, Set<ConstructionTemplate>> buildingElementToNewConstructions = new HashMap<>();
			
			// iterate over rooms
			for(Map.Entry<String, EnergyKeyPerformanceIndicatorsToBe> roomToEKPI : roomGuidToeKPIToBe.entrySet()) {
				
				try {
					String roomGuid = roomToEKPI.getKey();					
					EIfcspace space = ifcModel.getIfcEntity(roomGuid, EIfcspace.class);										
					// get requirements
					EnergyKeyPerformanceIndicatorsToBe eKPIToBe = roomToEKPI.getValue();
					
					Map<ConstructionSelector.TYPE, Set<ConstructionTemplate>> constructionsForType = 
							retrieveConstructionsForEachType(constructions, eKPIToBe);
					
					// search the building elements where the reference to the construction will be changed 
					// => search the building elements which are the boundaries of the room
					// FIXME Search in ontology not in IFC model
					Set<EIfcelement> affectedBuildingElements = ifcModel.getBoundingElementsOfSpace(space);
					affectedBuildingElements.forEach(affectedBuildingElement -> {
						try {
							String beGuid = ifcModel.getGlobalId(affectedBuildingElement);
							
							Set<ConstructionTemplate> possibleConstructions = null;
							
							// start possible construction selection algorithm
							if(affectedBuildingElement instanceof EIfcwall) {
								if(ifcModel.isOutdoorElement(affectedBuildingElement)) {
									possibleConstructions = constructionsForType.get(ConstructionSelector.TYPE.OUTER_WALL);
								} else {
									possibleConstructions = constructionsForType.get(ConstructionSelector.TYPE.INTERNAL_WALL);
								}
							} else if(affectedBuildingElement instanceof EIfcwindow) {
								possibleConstructions = constructionsForType.get(ConstructionSelector.TYPE.WINDOW);
							} else if(affectedBuildingElement instanceof EIfcslab) {
								possibleConstructions = constructionsForType.get(ConstructionSelector.TYPE.SLAB);
							} else if(affectedBuildingElement instanceof EIfcdoor) {
								possibleConstructions = constructionsForType.get(ConstructionSelector.TYPE.DOOR);
							} else {
								
							}
							buildingElementToNewConstructions.put(beGuid, possibleConstructions);
						} catch (IfcException e) {
							LOG.error(e.getMessage(),e);
						}
					});
					
					// start simulation
					
					// evaluate eKPI AS-IS
				} catch (IfcException e) {
					LOG.error("IFC problem occurred: "+e.getMessage(), e);
					return false;
				}
			}
			
			// create construction assignments in SimMatrix
			TSimulationMatrix optimizationMatrix = createAllPossibilitiesInSimulationMatrix(
					buildingElementToNewConstructions);	
			// save SimMatrix in simulation project
			saveSimulationMatrixToSimulationProject(optimizationMatrix, userId, simProject);
			
			// use Therakles with created simulation matrix assignments
			startSensitivityAnalysis(simProject);
		
		} catch (SimulationException e) {
			LOG.error("Simulation problem occurred: "+e.getMessage(), e);
			return false;
		} catch (IOException e) {
			LOG.error("IO problem occurred: "+e.getMessage(), e);
			return false;
		} catch (URISyntaxException e) {
			LOG.error("URI problem occurred: "+e.getMessage(), e);
			return false;
		} catch (SimulationMatrixException e) {
			LOG.error("Simulation matrix problem occurred: "+e.getMessage(), e);
			return false;
		}
		return true;
	}
	
	private Map<ConstructionSelector.TYPE, Set<ConstructionTemplate>> retrieveConstructionsForEachType(
			Collection<ConstructionTemplate> constructions, EnergyKeyPerformanceIndicatorsToBe eKPIToBe) {
		Map<ConstructionSelector.TYPE, Set<ConstructionTemplate>> typeWithConstructions = new HashMap<>();
		typeWithConstructions.put(
				ConstructionSelector.TYPE.OUTER_WALL, 
				ConstructionSelector.retrieveOptimalConstructions(
						constructions, ConstructionSelector.TYPE.OUTER_WALL, eKPIToBe));
		typeWithConstructions.put(
				ConstructionSelector.TYPE.INTERNAL_WALL, 
				ConstructionSelector.retrieveOptimalConstructions(
						constructions, ConstructionSelector.TYPE.INTERNAL_WALL, eKPIToBe));
		typeWithConstructions.put(
				ConstructionSelector.TYPE.SLAB, 
				ConstructionSelector.retrieveOptimalConstructions(
						constructions, ConstructionSelector.TYPE.SLAB, eKPIToBe));
		typeWithConstructions.put(
				ConstructionSelector.TYPE.WINDOW, 
				ConstructionSelector.retrieveOptimalConstructions(
						constructions, ConstructionSelector.TYPE.WINDOW, eKPIToBe));
		typeWithConstructions.put(
				ConstructionSelector.TYPE.DOOR, 
				ConstructionSelector.retrieveOptimalConstructions(
						constructions, ConstructionSelector.TYPE.DOOR, eKPIToBe));
		return typeWithConstructions;
	}
	
	private List<ConstructionTemplate> selectPossibleConstructions(Ifc2x3DataModelProxy ifcModel,
			EIfcelement affectedBuildingElement, 
			EnergyKeyPerformanceIndicatorsToBe eKPIToBe, 
			Collection<ConstructionTemplate> constructions) throws IfcException {
		List<ConstructionTemplate> possibleConstructions = new ArrayList<>();
		double maximumThermalTransmittance = .0;
		double maximumCosts = .0;
		if(affectedBuildingElement instanceof EIfcwall) {
			if(ifcModel.isOutdoorElement(affectedBuildingElement)) {
				maximumThermalTransmittance = eKPIToBe.getOuterWallThermalTransmittances();
				maximumCosts = eKPIToBe.getOuterWallCosts();
			} else {
				maximumThermalTransmittance = eKPIToBe.getInnerWallThermalTransmittances();
				maximumCosts = eKPIToBe.getInnerWallCosts();
			}
		} else if(affectedBuildingElement instanceof EIfcwindow) {
			maximumThermalTransmittance = eKPIToBe.getWindowThermalTransmittances();
			maximumCosts = eKPIToBe.getWindowCosts();
		} else if(affectedBuildingElement instanceof EIfcslab) {
			maximumThermalTransmittance = eKPIToBe.getSlabThermalTransmittances();
			maximumCosts = eKPIToBe.getSlabCosts();
		} else if(affectedBuildingElement instanceof EIfcdoor) {
			maximumThermalTransmittance = eKPIToBe.getDoorThermalTransmittances();
			maximumCosts = eKPIToBe.getDoorCosts();
		} else {
			
		}
		
		final double mtt = maximumThermalTransmittance;
		final double mc = maximumCosts;
		constructions.forEach(construction -> {
			double thermalTransmittanceValue = construction.getUValue();
			double costs = construction.getCostsPerSquareMeter();
			// take all constructions which are within the specified ranges
			if(thermalTransmittanceValue <= mtt && costs <= mc) {
				possibleConstructions.add(construction);
			}
		});
		return possibleConstructions;
	}
	
	private Set<ConstructionTemplate> filterBestSuitableConstructions(
			EnergyKeyPerformanceIndicatorsToBe eKPIToBe,
			Collection<ConstructionTemplate> constructions) {
		// iterate over all constructions and take only that one which fits best to requirements
		Set<ConstructionTemplate> optimalConstructions = new HashSet<>();
		constructions.forEach(construction -> {
			
		});
		return optimalConstructions;
	}
	
	private TSimulationMatrix createAllPossibilitiesInSimulationMatrix(
			Map<String, Set<ConstructionTemplate>> buildingElementToNewConstructions) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * <p>
	 * Copy all needed resources to a target directory in the right structure. 	<br/>
	 * 		COPY DIRECTORIES WITH FOLLOWING STRUCTURE:							<br/>
	 *		directory															<br/>
	 *		|-	bin 															<br/>
	 *		|	|	-	Bim2Sim													<br/>
	 *		|	|			|	-	Bim2Sim.jar									<br/>
	 *		|	|	-	Nandrad													<br/>
	 *		|	|			|	-	NandradSolver.exe							<br/>
	 *		|	|	-	ResCon													<br/>
	 *		|	|			|	-	ResCon.jar									<br/>
	 *	 	|	|	-	Therakles												<br/>
	 *		|	|			|	-	TheraklesSolver.exe							<br/>
	 *		|	|			|	-	...											<br/>
	 *		|-	project															<br/>
	 *		|	|	-	DB_Climate												<br/>
	 *		|	|			|	-	...											<br/>
	 *		|	|	-	DB_Templates											<br/>
	 *		|	|				|	-	HESMOS_Outputs.xml						<br/>
	 *		|	|				|	-	HESMOS_Schedules.xml					<br/>
	 *		|	|				|	-	HESMOS_SpaceTypes.xml					<br/>
	 *		|	|	-	SimMa													<br/>
	 *		|	|			|	-	...											<br/>
	 *		|	|	-	ISES_project.ifc										<br/>
	 *		|	|	-	ISES_project.rdf										<br/>
	 *		|																	<br/>
	 *		|-	resources														<br/>
	 *		|	|	-	...														<br/>
	 *		|-	scripts															<br/>
	 *		|	|	-	...														<br/>
	 *	</p>
	 *
	 * @throws IOException 
	 */
	private void preparePassiveSimulationProject(SimulationProject simProject) throws IOException {
		final String targetPath = simProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
		final String PROJECT_DIR = targetPath+File.separator+"project";	
		final String SCRIPT_DIR = targetPath+File.separator+"scripts";	
		final String BIN_DIR = targetPath+File.separator+"bin";
		final String RES_DIR = targetPath+File.separator+"resources";
		final String DB_CLIMATE_DIR = PROJECT_DIR+File.separator+"DB_Climate";
		final String DB_TEMPLATES_DIR = PROJECT_DIR+File.separator+"DB_Templates";
		
		File projectDir = new File(PROJECT_DIR);
		
		// create project
		FileService.createDirectory(projectDir);
		
		// copy /scripts to {targetPath}/scripts
		contextSensitiveCopying(BUNDLE_SIMULATION_SCRIPTS_TEMPLATE_DIR, SCRIPT_DIR);
		// copy /bin to {targetPath}/bin
		contextSensitiveCopying(BUNDLE_SIMULATION_BINARIES_TEMPLATE_DIR, BIN_DIR);
		
		// copy all IBK resources to project		
		contextSensitiveCopying(BUNDLE_SIMULATION_PROJECT_TEMPLATE_DIR+"DB_Climate", DB_CLIMATE_DIR);
		contextSensitiveCopying(BUNDLE_SIMULATION_PROJECT_TEMPLATE_DIR+"DB_Templates", DB_TEMPLATES_DIR);
		// Materials & Constructions will be created by Bim2Sim
//		contextSensitiveCopying(BUNDLE_SIMULATION_PROJECT_TEMPLATE_DIR+"DB_Constructions", PROJECT_DIR);
//		contextSensitiveCopying(BUNDLE_SIMULATION_PROJECT_TEMPLATE_DIR+"DB_Materials", PROJECT_DIR);
		
		// copy /resources to {targetPath}/resources
		contextSensitiveCopying(BUNDLE_SIMULATION_IBK_TEMPLATE_DIR+"resources", RES_DIR);
	}
	
	private void contextSensitiveCopying(String sourcePath, String targetPath) throws IOException {
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		if(bundle != null) { // production mode
			FileService.copyDirectoryFromBundle(sourcePath, targetPath, bundle);
		} else { // test mode
			FileService.copyFileToDirectory(new File(sourcePath), new File(targetPath));
		}
	}

	@Override
	public void storeSimulationResultsInSimulationProject(
			final Integer userId, final SimulationProject simProject) throws IOException {
		if(simProject.getOutputFiles().size() > 0) return; // results are available
		final String tmpProjectDirPath = simProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
		STATUS status = checkSimulationStatus(simProject);
		final SimulationProject.TYPE simType = simProject.getSimulationTypeId();
		if(status != STATUS.COMPLETED) return;
		
		Runnable startPostProcessingRunnable = new Runnable() {

			@Override
			public void run() {
				try {
					Collection<File> resultFiles = null;
					switch (simType) {
					case NANDRAD_PASSIV: 
						// FIRST START A PASSIVE SIMULATION POST-PROCESSING
						startPassiveSimulationPostProcessing(simProject);					
						resultFiles = listPassiveSimulationResults(tmpProjectDirPath);
						break;
					case BEGIN:
						break;
					case CFD:
						// TODO CFD
						break;
					case NANDRAD:
						// TODO Full building sim
						break;
					case THERAKLES:
						startSensitivityAnalysisPostProcessing(simProject);
						resultFiles = listSensitivityAnalysisSimulationResults(tmpProjectDirPath);
						break;
					default:
						break;
					}
					
					if(resultFiles != null) {
						// second: upload all files to the user repository
						addSimulationResources(userId, simProject, SimulationProject.DEFAULT_RELATIVE_OUTPUT_DIR, resultFiles);
					} else {
						LOG.warn("No result files for simulation project: {}", new Object[]{simProject});
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		};
		
		// start it
		Thread startPostProcessingThread = new Thread(startPostProcessingRunnable);
		startPostProcessingThread.start();
	}

	private void startPassiveSimulationPostProcessing(final SimulationProject simProject) throws SimulationException {
		LOG.info("[Passive simulation] - start post-processing phase");
		try {
			String tmpDirPath = simProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
			final String SCRIPT_DIR = tmpDirPath+File.separator+"scripts";			
//			final String scriptFile = SCRIPT_DIR+File.separator+SCRIPT_NANDRAD_POSTPROCESSING;
			final String scriptFile = SCRIPT_NANDRAD_POSTPROCESSING;
					
			ProcessBuilder pb = new ProcessBuilder("python", scriptFile);
			pb.directory(new File(SCRIPT_DIR));		
			Process process = pb.start();
			process.waitFor();
			LOG.info("[Passive simulation] - post-processing phase ended");
		} catch (Exception te) {
			LOG.error(te.getMessage(), te);
			throw new SimulationException(SimulationExceptionCode.NO_SIMULATION_RESULT);
		}
		// TODO clean the simulation project (delete all input files binaries etc.)
	}
	
	private void startSensitivityAnalysisPostProcessing(SimulationProject simProject) throws SimulationException {
		LOG.info("[Sensitivity Analysis] - start post-processing phase");
		try {
			String tmpDirPath = simProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
			final String SCRIPT_DIR = tmpDirPath+File.separator+"scripts";			
//			final String scriptFile = SCRIPT_DIR+File.separator+SCRIPT_THERAKLES_POSTPROCESSING;
			final String scriptFile = SCRIPT_THERAKLES_POSTPROCESSING;
					
			ProcessBuilder pb = new ProcessBuilder("python", scriptFile);
			pb.directory(new File(SCRIPT_DIR));
			Process process = pb.start();
			process.waitFor();
			LOG.info("[Sensitivity Analysis] - post-processing phase ended");
		} catch (Exception te) {
			LOG.error(te.getMessage(), te);
			throw new SimulationException(SimulationExceptionCode.NO_SIMULATION_RESULT);
		}
	}
	
	private Collection<File> listPassiveSimulationResults(String tmpProjectDirPath) {
		File resultDir = new File(tmpProjectDirPath + File.separator + "project" + File.separator + 
				IBK_PASSIVESIM_PROJECT_NAMING_CONVENTION + File.separator + "results");
		return FileService.getFiles(resultDir, false);
	}
	
	private Collection<File> listSensitivityAnalysisSimulationResults(String tmpProjectDirPath) {
		File resultDir = new File(tmpProjectDirPath + File.separator + "project" + File.separator + 
				THERAKLES_PROJECTS_NAMING_CONVENTION);
		return FileService.getFiles(resultDir, "txt", false);
	}

	@Override
	public void startSensitivityAnalysis(final SimulationProject simProject) throws SimulationException, IOException {
		LOG.info("Starting sensitivity analysis process -> preparing data for local execution");
		
		// simulation preparation needs some time but the application should not freeze
		Runnable startSimulationRunnable = new Runnable() {

			@Override
			public void run() {
				try {
					// COPY all input files to temporary project directory
					LOG.debug("[Sensitivity Analysis] - Prepare copying of all required files");
					File ifcFile = findInputFileByType(simProject, TYPE.IFC);
					File rdfFile = findInputFileByType(simProject, TYPE.RDF);
					File matrixFile = findInputFileByType(simProject, TYPE.SIMMATRIX);
					File nandradModelFile = findInputFileByType(simProject, TYPE.NANDRAD);
	
					if(ifcFile == null || rdfFile == null || matrixFile == null) {
						throw new IllegalStateException("There must be 1 IFC file, 1 RDF file and 1 Matrix file in the simulation project!");
					}
					
					final String environmentPath = simProject.getPathInputDirectory()+File.separator+SIM_ENVIRONMENT_DIR;
					
					LOG.debug("[Sensitivity Analysis] - Copy all required files to temporary project directory: {}", 
							new Object[]{environmentPath});									
					
					LOG.debug("[Sensitivity Analysis] - Copy of all required files completed");
					final String pathToTheraklesSolver = environmentPath+File.separator+"bin"+File.separator+"Therakles"+File.separator;
					
					final String PROJECT_DIR = environmentPath+File.separator+"project";
					final String SCRIPT_DIR = environmentPath+File.separator+"scripts";	
					final String SIMMATRIX_DIR = PROJECT_DIR+File.separator+"SimMa";	
					if(!new File(PROJECT_DIR).exists() || !new File(SCRIPT_DIR).exists() || !new File(SIMMATRIX_DIR).exists()) {
						throw new SimulationException(SimulationExceptionCode.PROBLEM_BY_PREPARING_SIMPROJECT);
					}

					String newMatrixName = IBK_FILENAME_PREFIX+".simmatrix";
					// move Simulation Matrix from {PROJECT_ID}/ to /project/SimMa
					Files.copy(Paths.get(matrixFile.getAbsolutePath()), Paths.get(SIMMATRIX_DIR+File.separator+newMatrixName));
					// move Nandrad simulation model from {PROJECT_ID}/ to /project
//					Files.copy(Paths.get(nandradModelFile.getAbsolutePath()), Paths.get(PROJECT_DIR+File.separator+newNandradModelName));
					
					// Create Therakles projects
					LOG.info("[Sensitivity Analysis] - create Therakles projects");
//					final String scriptFile = SCRIPT_DIR+File.separator+SCRIPT_CREATE_THERAKLES_PROJECTS;
					final String scriptFile = SCRIPT_CREATE_THERAKLES_PROJECTS;
					
					ProcessBuilder pb = new ProcessBuilder("python", scriptFile);	
					pb.directory(new File(SCRIPT_DIR));
					Process process = pb.start();
					
					InputStream in = process.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					String line = null;
					while((line = reader.readLine())!= null) {
						System.out.println(line);
					}
					
					process.waitFor();
					LOG.info("[Sensitivity Analysis] - Therakles projects created");
								
					File theraklesProjectsDir = new File(PROJECT_DIR+File.separator+THERAKLES_PROJECTS_NAMING_CONVENTION);
					Set<File> theraklesProjects = FileService.getFiles(theraklesProjectsDir, false);
					for(File theraklesProjectFile : theraklesProjects) {
						if(theraklesProjectFile.exists()) {
							TheraklesSimulation theraklesSimulation = initTheraklesSimulation(pathToTheraklesSolver);
							// START SIMULATION
							LOG.info("[Sensitivity Analysis] - start Therakles");
							theraklesSimulation.start(simProject, theraklesProjectFile.getAbsolutePath());
							simProject.setSimulationStatus(STATUS.RUNNING);
							//	update result set path etc. in database
							LOG.info("[Passive simulation] - update simulation: {} to Running", new Object[]{simProject.getId()});
							userService.updateSimulationProject(simProject);	
						} else {
							throw new SimulationException(SimulationExceptionCode.NO_SIMULATION_MODEL);
						}
					}			
				} catch (Exception te) {
					LOG.error(te.getMessage(), te);
				}
			}
			
		};
		// start it
		Thread simulationThread = new Thread(startSimulationRunnable);
		simulationThread.start();
		
//		try {
//			simulationThread.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
