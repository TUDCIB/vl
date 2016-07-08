package de.tudresden.bau.cib.vl.gui.simulation.energy.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.SIfc2x3.EIfcspatialstructureelement;
import jsdai.lang.SdaiException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.graph.Triple;

import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation.TYPE;
import de.tudresden.bau.cib.vl.core.database.domain.OptimizationSimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.Project;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject;
import de.tudresden.bau.cib.vl.core.database.domain.SimulationProject.STATUS;
import de.tudresden.bau.cib.vl.core.database.domain.User;
import de.tudresden.bau.cib.vl.core.model.Model;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.bim.service.BimFitService;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService;
import de.tudresden.bau.cib.vl.core.service.ConfigurationService.PROPERTIES;
import de.tudresden.bau.cib.vl.core.service.FileService;
import de.tudresden.bau.cib.vl.core.service.UserService;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicatorsToBe;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SimulationMatrixException;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.EnergySimulationService;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.SimulationMatrixService;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.SpaceBoundaryService;
import de.tudresden.bau.cib.vl.core.util.Pair;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCModelContentProvider;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;
import de.tudresden.bau.cib.vl.gui.core.view.dialog.NewSimulationProjectDialog;
import de.tudresden.bau.cib.vl.gui.simulation.energy.Activator;
import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.assignment.AssignmentHelperWithMonitor;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.ProcessView;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.GreenBuildingDesignSetupParameterDialog;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.IfcTreeSelectionDialog;

public class ProcessController extends AbstractViewController<ProcessView> {
	
	private Ifc2x3DataModelProxy ifcModel;
	private OntologyModel ontologyModel;
	private Project selectedProject;
	private SimulationProject selectedSimulationProject;
	private TSimulationMatrix matrix;
	private Display display;
	
	private Collection<EIfcroot> zonesOfInterest = new HashSet<EIfcroot>();
	
	private FileInformation ifcFile = null;
	private FileInformation ontologyFile = null;
	private FileInformation matrixFile = null;
	
	private ConfigurationService configurationService;
	private SpaceBoundaryService spaceBoundaryService;
	private OntologyService ontologyService;
	private TemplateService resourceService;
	private SimulationMatrixService simulationMatrixService;
	private EnergySimulationService energySimulationService;
	private BimFitService modelService;
	private FileService fileService;
	private UserService userService;
	private Integer userId;
	

	private static final Logger LOG = LoggerFactory.getLogger(ProcessController.class);

	
	/**
	 * The shared instance.
	 */
	private static ProcessController INSTANCE = new ProcessController();

	/**
	 * Private constructor because of singleton pattern.
	 */
	private ProcessController() {

	}

	/**
	 * Retrieves the single instance of ProcessController.
	 * @return The instance of ProcessController.
	 */
	public static ProcessController getInstance() {
		if (INSTANCE == null)
			INSTANCE = new ProcessController();
		return INSTANCE;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
		energySimulationService.checkCompletedSimulationsAndStoreResults(userId);
	}	

	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {
		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();		
		events.add(CommunicationEvents.WORKBENCH_READY);
		events.add(CommunicationEvents.IFC_FILE);
		events.add(CommunicationEvents.PROJECT_SELECTED);
		events.add(CommunicationEvents.RESOURCE_TO_IFC);
		events.add(CommunicationEvents.ONTOLOGY_SAVE);
		events.add(CommunicationEvents.ONTOLOGY_REMOVE_LINK);
		events.add(CommunicationEvents.SIMULATION_PROJECT_SELECTED);
		events.add(CommunicationEvents.SIMULATION_MATRIX_REMOVE_COMBINATION);
		events.add(CommunicationEvents.SIMULATION_MATRIX_REMOVE_VARIANT);
		events.add(CommunicationEvents.IFC_ZONESOFINTERESTS);	
		events.add(CommunicationEvents.CHECK_SIMULATION_STATUS);
		return events;
	}

	@Override
	protected BundleContext getBundleContext() {
		return Activator.getDefault().getContext();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void handleEvent(CommunicationEvents event,
			Map<String, Object> dataMap) {
		Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
		Object uiData = dataMap.get(CommunicationService.PROPERTIES_KEY_UI_DATA);
		
		LOG.debug("Retrieve event: {} with data: {} and uiData: {}", new Object[]{event, data, uiData});
		
		switch(event) {
		case WORKBENCH_READY:
			break;
		case IFC_FILE: 
			clearProcessData(true);
			ifcFile = (FileInformation) data;
			selectedProject = userService.getProjectByUserIdAndFileInformation(userId, ifcFile);
			if(selectedProject == null) throw new NullPointerException("Cannot find project!");
			createModels();
			setProcessStatus();
			break;
		case PROJECT_SELECTED:
//			clear all previous process data
			clearProcessData(true);
			Project project = (Project) data;
			if(selectedProject == null || !project.equals(selectedProject)) {
				selectedProject = project;
				loadProject();
				setProcessStatus();
			}
			break;
		case RESOURCE_TO_IFC:
			final Map<String, Set<EIfcroot>> resourcesToIfcEntities = (Map<String, Set<EIfcroot>>) data;			
			final Map<String, Object> uiDataMap = (Map<String, Object>) uiData;
			
			final AssignmentHelperWithMonitor assignmentHelper = AssignmentHelperWithMonitor.getInstance();
			assignmentHelper.setOntologyService(ontologyService);
			assignmentHelper.setResourceService(resourceService);
			assignmentHelper.setSimulationMatrixService(simulationMatrixService);
			final boolean isInStochasticPhase = isInStochasticPhase();
			
			
			UIJob job = new UIJob(Display.getDefault(), "Assignment job") {

				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					assignmentHelper.evaluateAssignment(userId, ifcModel, ontologyModel, isInStochasticPhase, matrix, 
							resourcesToIfcEntities, uiDataMap);				
				return org.eclipse.core.runtime.Status.OK_STATUS;
				}
				
			};
			job.setUser(true);
			job.schedule();	
			
			//publish new sim matrix
			sendSync(CommunicationEvents.SIMULATION_MATRIX_MODEL, matrix);
			break;
		case ONTOLOGY_SAVE:
			saveOntologyModel(ontologyModel);
			break;
		case ONTOLOGY_REMOVE_LINK:
			List<Triple> triples = (List<Triple>) data;
			removeLinks(triples);
			// set dirty status to ontology view if remove was successfully
			sendSync(CommunicationEvents.ONTOLOGY_MODEL, ontologyModel);
			break;
		case SIMULATION_PROJECT_SELECTED:
//			clear all previous process data
			clearProcessData(true);
			SimulationProject simulationProject = (SimulationProject) data;
			selectedProject = userService.getProjectOfSimulationProject(userId, simulationProject);
			loadIfcModelFromProject(selectedProject.getFiles());			
			
			if(selectedSimulationProject == null || !simulationProject.equals(selectedSimulationProject)) {
				selectedSimulationProject = simulationProject;
				loadOntologyFromProject(new HashSet<FileInformation>(selectedSimulationProject.getInputFiles()));
				loadSimulationMatrixFromSimulationProject(selectedSimulationProject.getInputFiles());
				setProcessStatus();
				// inform ontology view
				sendSync(CommunicationEvents.ONTOLOGY_MODEL, ontologyModel);				
			}
			
			Runnable waitForIfcAndOntologyParsingRunnable = new Runnable(){
				@Override
				public void run() {
					try {
						while(ifcModel == null || ontologyModel == null) {
							Thread.sleep(10000); // wait 10 seconds
						}
						sendSync(CommunicationEvents.PROJECT_LOADED, selectedSimulationProject);
					} catch(InterruptedException e) {
						LOG.error("Thread exception: {}", e.getMessage(), e);
					}
				}				
			};
			Display.getDefault().asyncExec(waitForIfcAndOntologyParsingRunnable);
			break;
		case SIMULATION_MATRIX_REMOVE_COMBINATION:
			List<String> combinationIds = (List<String>) data;
			simulationMatrixService.removeCombinationIds(combinationIds, matrix);
			sendSync(CommunicationEvents.SIMULATION_MATRIX_MODEL, matrix);
			break;
		case SIMULATION_MATRIX_REMOVE_VARIANT:
			List<Pair<String,String>> ids = (List<Pair<String,String>>) data;
			simulationMatrixService.removeVariantFromCombination(ids, matrix);
			sendSync(CommunicationEvents.SIMULATION_MATRIX_MODEL, matrix);
			break;				
		case IFC_ZONESOFINTERESTS:
			zonesOfInterest = (Collection<EIfcroot>) data;	
			try {
				Map<Color, Collection<String>> colorOfEntities = new HashMap<Color, Collection<String>>();				
				Set<String> problemGuids = new HashSet<String>();
				for(EIfcroot root : zonesOfInterest) {
					String guid = ifcModel.getGlobalId(root);
					problemGuids.add(guid);
				}	
				// critical rooms
				colorOfEntities.put(new Color(Display.getDefault(),255,0,0), problemGuids);
				List<EIfcspace> allRooms = ifcModel.getSpaces();
				Set<String> noProblemGuids = new HashSet<String>();
				noProblemGuids.addAll(ifcModel.getGlobalIds(allRooms));
				noProblemGuids.removeAll(problemGuids);
				colorOfEntities.put(new Color(Display.getDefault(),0,255,0), noProblemGuids);
				sendSync(CommunicationEvents.IFC_SELECTION_COLORED, colorOfEntities);
			} catch (IfcException e) {
				LOG.error("Problem by accessing GlobalIds: {}", new Object[]{e.getMessage()}, e);
			}
			break;
		case CHECK_SIMULATION_STATUS:
			User user = (User)data;
			if(user != null) {
				energySimulationService.checkCompletedSimulationsAndStoreResults(user.getId());	
				// send event to show the new status of the simulation in the project tree
				sendSync(CommunicationEvents.SIMULATION_STATUS, selectedSimulationProject);
			}
			break;
		default:break;
		}
	}

	private void saveOntologyModel(OntologyModel ontologyModel) {
		try {
			String tempDirectory = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
			if(ontologyFile == null) {		
				String fileName = ifcFile.getName().toLowerCase();
				fileName = fileName.replace("ifc", "rdf");
//				String modelId = ontologyModel.getId();
//				String fileName = userId+"_"+modelId+"_"+System.currentTimeMillis()+".rdf";
				//	save it in the temporary directory
				String tempPath = tempDirectory+fileName;
				//	save it before copying
				File file = ontologyService.saveRDF(ontologyModel, tempPath);
				LOG.debug("Saved ontology model: {} to temporary path: {} of user: {}", 
						new Object[]{ontologyModel.getId(), file, userId});
				//	upload it to the user repository
				if(selectedProject != null) {
					ontologyFile = userService.uploadFileToProject(userId, file, selectedProject);
				} else {
					throw new NullPointerException("No project selected");
				}
				LOG.info("Saved ontology model in user repository to {}", 
						new Object[]{ontologyFile.getFilePath()});
				// delete it in the temp directory
				FileService.deleteFile(file);

			} else {
				ontologyService.saveRDF(ontologyModel, ontologyFile.getFilePath());
			}
		} catch(Exception e) {
			LOG.error("{}", e.getMessage(), e);
			showMessage("Save ontology model error", ""+e.getMessage(), true);
		}
	}

	private void loadProject() {
		loadIfcModelFromProject(selectedProject.getFiles());
		loadOntologyFromProject(selectedProject.getFiles());
		// first load a default simulation matrix
		createDefaultSimulationMatrix();
		sendSync(CommunicationEvents.PROJECT_LOADED, selectedProject);
	}
	
	private void createDefaultSimulationMatrix() {
		matrix = simulationMatrixService.createMatrix("sm-"+UUID.randomUUID().toString());
	}
	
	private boolean isInStochasticPhase() {
		if(selectedSimulationProject != null) {
			return selectedSimulationProject.getSimulationTypeId() == SimulationProject.TYPE.NANDRAD_PASSIV;
		}
		return false;
	}

	private void clearProcessData(boolean nullifyIfc) {
		selectedProject = null;
		selectedSimulationProject = null;
		matrix = null;
		if(nullifyIfc) {
			ifcModel = null;
			ifcFile = null;
		}
		ontologyModel = null;
		ontologyFile = null;		
		matrixFile = null;
	}
	
	/**
	 * <ol>
	 * <li>Search IFC file</li> 
	 * <li>parse IFC file if the current IFC model is different from it</li>
	 * </ol>
	 * @param project
	 */
	private void loadIfcModelFromProject(Set<FileInformation> files) {
		FileInformation foundIfcFile = searchForFile(files, TYPE.IFC);
		
		if(foundIfcFile == null) {
			showMessage("Load IFC model error", "There is no IFC file", true);
			throw new RuntimeException("Illegal project state -> IFC file: "+ifcFile.getUrl()+" must be available!");
		}
		
		// compare the current IFC model with the IFC file found in the project
		if((ifcFile != null) && (foundIfcFile.getId().intValue() == ifcFile.getId().intValue())) {
			return; // do nothing
		} else {
			ifcFile = foundIfcFile;
		}
		
		
		// IFC model
		Job ifcJob = new Job("Load IFC model job") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Model model = null;
				try {
					URL fileUrl = ifcFile.getUrl();					
					model = modelService.parseIfc2x3File(fileUrl);
					model.setSourceFilePath(fileUrl.toString());
					model.setId(""+ifcFile.getId());
					if(monitor.isCanceled()) return org.eclipse.core.runtime.Status.CANCEL_STATUS;
					
					if(model instanceof Ifc2x3DataModelProxy) {
						ifcModel = (Ifc2x3DataModelProxy) model;
						
						// publish the new IFC model
						sendSync(CommunicationEvents.IFC_MODEL, ifcModel);
						return org.eclipse.core.runtime.Status.OK_STATUS;
					}
				} catch (final Exception e) {
					LOG.error(e.getMessage(), e);
					showMessage("Model loading problem", "Problem occurred: "+e.getMessage(), true);
					monitor.setCanceled(true);
				}
				return org.eclipse.core.runtime.Status.CANCEL_STATUS; 
			}
		};
		ifcJob.setPriority(Job.LONG);
		ifcJob.setUser(true);
		ifcJob.schedule(); 
	}

	/**
	 * <ol>
	 * <li>Search RDF file</li> 
	 * <li>parse RDF file</li>
	 * </ol>
	 * @param project
	 */
	private void loadOntologyFromProject(Set<FileInformation> files) {	
		FileInformation foundRDFFile = searchForFile(files, TYPE.RDF);
		
		if(foundRDFFile == null) {
			showMessage("Load models error", "There is no ontology file", true);
			throw new RuntimeException("Illegal project state -> an ontology file must be available!");
		}
		
		// compare the current ontology file with the ontology file found in the project
		if((ontologyFile != null) && (foundRDFFile.getId().intValue() == ontologyFile.getId().intValue())) {
			return; // do nothing
		} else {
			ontologyFile = foundRDFFile;
		}
		
		// ontology model
		Job ontoJob = new Job("Load ontology model job") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {	
					ontologyModel = ontologyService.loadOntologyModel(ontologyFile.getFilePath());
					ontologyModel.setId(""+ontologyFile.getId());
					if(monitor.isCanceled()) return org.eclipse.core.runtime.Status.CANCEL_STATUS;
						
					// publish the ontology model
					sendSync(CommunicationEvents.ONTOLOGY_MODEL, ontologyModel);	
					return org.eclipse.core.runtime.Status.OK_STATUS;
				} catch (final Exception e) {
					LOG.error(e.getMessage(), e);
					showMessage("Ontology model loading problem", "Problem occurred: "+e.getMessage(), true);
				}
				return org.eclipse.core.runtime.Status.CANCEL_STATUS; 
			}
		};
		ontoJob.setPriority(Job.SHORT);
		ontoJob.setUser(true);
		ontoJob.schedule(); 
	}
	
	/**
	 * Loads the simulation matrix from a simulation project.
	 * @throws SimulationMatrixException
	 */
	private void loadSimulationMatrixFromSimulationProject(Set<FileInformation> files) {
//		FileInformation foundSimMatrix = null;
//		for(FileInformation file : files) {
//			TYPE type = file.getFileType();
//			if(type == TYPE.SIMMATRIX) {
//				// there is only one simulation matrix
//				if(foundSimMatrix != null) {
//					throw new RuntimeException("Multiple matrix files in project!");
//				} else {
//					foundSimMatrix = file;
//				}			
//			}
//			if(foundSimMatrix != null) break;
//		}
//		
//		if(foundSimMatrix == null) return;
		
		FileInformation foundSimMatrix = searchForFile(files, TYPE.SIMMATRIX);
		
		// compare the current simmatrix file with the simmatrix file found in the project
		if((matrixFile != null) && (foundSimMatrix.getId().intValue() == matrixFile.getId().intValue())) {
			return; // do nothing
		} else {
			matrixFile = foundSimMatrix;
		}
		
		if(matrixFile != null) {
			// Load simulation matrix
			Job simMatrixJob = new Job("Load Simulation matrix job") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						matrix = simulationMatrixService.loadMatrix(matrixFile.getUrl());
	
						sendSync(CommunicationEvents.SIMULATION_MATRIX_MODEL, matrix);
						
						return org.eclipse.core.runtime.Status.OK_STATUS;
					} catch (final Exception e) {
						LOG.error(e.getMessage(), e);
						showMessage("Simulation matrix loading problem", "Problem occurred: "+e.getMessage(), true);
					}
					return org.eclipse.core.runtime.Status.CANCEL_STATUS; 
				}
			};
			simMatrixJob.setPriority(Job.SHORT);
			simMatrixJob.setUser(true);
			simMatrixJob.schedule(); 
		} else {
			createDefaultSimulationMatrix();
		}
	}
	
	private FileInformation searchForFile(Collection<FileInformation> files, TYPE cmpType) {
		FileInformation foundFile = null;
		for(FileInformation file : files) {
			TYPE type = file.getFileType();
			if(type == cmpType) {
				foundFile = file;
				break;
			}
		}
		return foundFile;
	}
	
//	private void createInitialISESProjects() throws IOException {
//		// ISES Bestand
//		final String ifcIsesBestand = "resources/projects/ises_bestand/ISES_Bestand_20131209.ifc";
//		final URL ifcIsesBestandUrl = Activator.getDefault().getBundle().getEntry(ifcIsesBestand);
//		final String rdfIsesBestand = "resources/projects/ises_bestand/ISES_Bestand.rdf";
//		final URL rdfIsesBestandUrl = Activator.getDefault().getBundle().getEntry(rdfIsesBestand);
//		
//		Project isesBestandProject = userService.createProject("ISES_Bestand", userService.getUserRepositoryByUserId(userId));
//		userService.uploadFileAsStreamToProject(userId, "ISES_Bestand_20131209.ifc", ifcIsesBestandUrl.openStream(), isesBestandProject);
//		userService.uploadFileAsStreamToProject(userId, "ISES_Bestand.rdf", rdfIsesBestandUrl.openStream(), isesBestandProject);
//		
//		// ISES Umbau
//		Project isesUmbauProject = userService.createProject("ISES_Umbau", userService.getUserRepositoryByUserId(userId));
//		final String ifcIsesUmbau = "resources/projects/ises_umbau/ISES_Umbau_20131209.ifc";
//		final URL ifcIsesUmbauUrl = Activator.getDefault().getBundle().getEntry(ifcIsesUmbau);
//		final String rdfIsesUmbau = "resources/projects/ises_umbau/ISES_Umbau.rdf";
//		final URL rdfIsesUmbauUrl = Activator.getDefault().getBundle().getEntry(rdfIsesUmbau);
//
//		userService.uploadFileAsStreamToProject(userId, "ISES_Umbau_20131209.ifc", ifcIsesUmbauUrl.openStream(), isesUmbauProject);
//		userService.uploadFileAsStreamToProject(userId, "ISES_Umbau.rdf", rdfIsesUmbauUrl.openStream(), isesUmbauProject);
//	}

	/**
	 * <ol>
	 * <li>Parse original IFC file</li>
	 * <li>Check if 2nd Level Space Boundaries</li>
	 * <li>Eventually convert the file to a file with second level space boundaries</li>
	 * <li>Create ontology individuals</li> 
	 * <li>Create an empty simulation matrix</li>
	 * <li>Publish IFC model and Ontology model</li>
	 * </ol>
	 * 
	 * @param file
	 */
	private void createModels() {
		final File file = new File(ifcFile.getFilePath());
		if(!file.exists()) throw new IllegalArgumentException("No file found for IFC model: "+ifcModel);
		final URL fileUrl = ifcFile.getUrl();
		
		// IFC model
		Job job = new Job("Model creation job") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				File secondLevelSBFile = null;
				Ifc2x3DataModelProxy model = null;
				try {
					monitor.beginTask("Creating models...", 7);
					
					monitor.subTask("Parse IFC file: "+fileUrl);	
					model = modelService.parseIfc2x3File(fileUrl);
					model.setId(""+ifcFile.getId());
					monitor.worked(1);
					if(monitor.isCanceled()) return org.eclipse.core.runtime.Status.CANCEL_STATUS;
					
					if(model instanceof Ifc2x3DataModelProxy) {
						ifcModel = (Ifc2x3DataModelProxy) model;
						monitor.subTask("Check if IFC file contains second level space boundaries");
						LOG.info("Check if IFC file contains second level space boundaries");
						if(!ifcModel.has2ndLevelSpaceBoundaries()) {
							monitor.worked(1);
							monitor.subTask("Convert IFC file from 1st level space boundaries to 2nd level space boundaries");
							secondLevelSBFile = spaceBoundaryService.convertSpaceBoundaries(ifcModel, file);
							if(!secondLevelSBFile.exists()) {
								showMessage("Second level space boundary problem", "Second level space boundary conversion problem occurred", true);
								monitor.setCanceled(true);
							} else {
								monitor.worked(1);
								model = modelService.parseIfc2x3File(secondLevelSBFile.toURI().toURL());
								model.setId(""+ifcFile.getId());
								ifcModel = (Ifc2x3DataModelProxy) model;
								monitor.worked(1);
							}
						} else {
							monitor.worked(3);
						}
						if(monitor.isCanceled()) return org.eclipse.core.runtime.Status.CANCEL_STATUS;
						monitor.subTask("Create ontology model");
						ontologyModel = ontologyService.createEmptyOntologyModel();
						monitor.worked(1);
						monitor.subTask("Create ontology individuals");
						if(monitor.isCanceled()) return org.eclipse.core.runtime.Status.CANCEL_STATUS;
						
						ontologyService.createArchitecturalDomain(ifcModel, ontologyModel);
						monitor.worked(1);
						if(monitor.isCanceled()) return org.eclipse.core.runtime.Status.CANCEL_STATUS;
						
						// replace IFC file
						if(secondLevelSBFile != null) userService.replaceFileInUserRepository(userId, secondLevelSBFile, ifcFile);
						
//						// save ontology model in project
						monitor.subTask("Save ontology in user repository");
						saveOntologyModel(ontologyModel);

						monitor.worked(1);
						monitor.done();
						
						showMessage("Model creation successfully", "Intelligent BIM prepared", false);
						// PUBLISH EVENTS
						// publish the new IFC model
						sendSync(CommunicationEvents.IFC_MODEL, ifcModel);
						// publish the ontology model
						sendSync(CommunicationEvents.ONTOLOGY_MODEL, ontologyModel);
						
						// update the repository
						sendSync(CommunicationEvents.USERREPOSITORY_UPDATED, null);
						
						sendSync(CommunicationEvents.PROJECT_LOADED, selectedProject);
						return org.eclipse.core.runtime.Status.OK_STATUS;
					}
				} catch (final Exception e) {
					LOG.error(e.getMessage(), e);
					showMessage("Model creation problem", "Problem occurred: "+e.getMessage(), true);
				}
				return org.eclipse.core.runtime.Status.CANCEL_STATUS; 
			}
		};
		// Start the Job
		job.setUser(true);
		job.schedule(); 
	}
	
	private void removeLinks(List<Triple> triples) {
		ontologyService.removeRelations(triples, ontologyModel);
		sendSync(CommunicationEvents.ONTOLOGY_MODEL, ontologyModel);
	}
	
	private void setProcessStatus() {
		display.syncExec(new Runnable() {

			@Override
			public void run() {
				final SimulationProject.TYPE type = selectedSimulationProject != null ? selectedSimulationProject.getSimulationTypeId() : SimulationProject.TYPE.BEGIN;
				switch(type) {
				case BEGIN:	// afterwards only passive sim permitted
					view.enablePassiveSimButton(true);
					view.enableSensitivityButton(false);
					view.enableBuildingSimButton(false);
					view.enableCFDButton(false);
					view.enableGreenBuildingOptimizationButton(false);
					break;
				case NANDRAD_PASSIV: // afterwards only Therakles permitted	
					view.enablePassiveSimButton(false);
					view.enableSensitivityButton(true);
					view.enableBuildingSimButton(false);
					view.enableCFDButton(false);
					view.enableGreenBuildingOptimizationButton(true);
					break;
				case THERAKLES: // afterwards only NANDRAD full sim or CFD permitted
					view.enablePassiveSimButton(false);
					view.enableSensitivityButton(false);
					view.enableBuildingSimButton(true);
					view.enableCFDButton(true);
					view.enableSensitivityButton(false);
					break;
				case NANDRAD: // afterwards only CFD permitted
					view.enablePassiveSimButton(false);
					view.enableSensitivityButton(false);
					view.enableBuildingSimButton(false);
					view.enableCFDButton(true);
					view.enableSensitivityButton(false);
					break;
				case CFD: // nothing can be done afterwards
					view.enablePassiveSimButton(false);
					view.enableSensitivityButton(false);
					view.enableBuildingSimButton(false);
					view.enableCFDButton(false);
					view.enableSensitivityButton(false);
					break;
				default: 
					break;
				}
			}		
		});
	}
	
	private void showMessage(final String title, final String message, final boolean isError) {	
		if(!display.isDisposed()) {
			display.asyncExec(new Runnable() {
				@Override
				public void run() {
					if(isError) {
						MessageDialog.openError(display.getActiveShell(), 
								title,
								message);
					} else {
						MessageDialog.openInformation(display.getActiveShell(), 
								title,
								message);
					}
				}
			});
		}
	}
	
	private SimulationProject createNewSimulationProject(SimulationProject.TYPE type) throws IOException {
		if(selectedProject == null) throw new IllegalArgumentException("No project is selected!");
		SimulationProject newSimProject = null;
		NewSimulationProjectDialog dialog = new NewSimulationProjectDialog(display.getActiveShell());
		if(dialog.open() == Dialog.OK) {
			String simProjectName = dialog.getStrProjectName();			
			newSimProject = type == 
					SimulationProject.TYPE.OPTIMIZATION 
						? userService.createOptimizationSimulationProject(selectedProject, simProjectName) 
						: userService.createSimulationProject(selectedProject, simProjectName);
			// set initial type
			newSimProject.setSimulationTypeId(SimulationProject.TYPE.BEGIN);
			// COPY ALL NEEDED RESOURCES FROM NANDRAD PASSIVE SIMULATION PROJECT TO NEW SENSITIVE ANALYSIS PROJECT
			if(type == SimulationProject.TYPE.THERAKLES && selectedSimulationProject != null) {
				if(selectedSimulationProject.getSimulationTypeId() == SimulationProject.TYPE.NANDRAD_PASSIV) {				
					energySimulationService.copyContentFromPassiveProjectToSensitiveProject(selectedSimulationProject, newSimProject);
				} else if(selectedSimulationProject.getSimulationTypeId() == SimulationProject.TYPE.THERAKLES) {
//					TODO Test it!
					energySimulationService.copyContentFromSensitiveProjectToSensitiveProject(selectedSimulationProject, newSimProject);
				} 
			}
			
			setProcessStatus();
		}
		return newSimProject;
	}
	
	private void addIfcAndOntologyModelToSimulationProject(SimulationProject simProject) throws IOException {
		energySimulationService.addSimulationResource(userId, simProject, 
				SimulationProject.DEFAULT_RELATIVE_INPUT_DIR, new File(ifcFile.getFilePath()));
		energySimulationService.addSimulationResource(userId, simProject, 
				SimulationProject.DEFAULT_RELATIVE_INPUT_DIR, new File(ontologyFile.getFilePath()));		
	}
	
//	/**
//	 * Checks the simulation project type by evaluating a current selected simulation project. 
//	 * <ul>
//	 * <li>If there is no selected simulation project it creates a new one and the check is passed. </li>
//	 * <li>If the selected simulation project is in a higher order than the requested simulation 
//	 * (for example the currently selected simulation project is from type 'sensitivity analysis' a question will appear 
//	 * which asks the user if he wants to create a new simulation project and set it as selected simulation project. 
//	 * If the user doesn't want to create a new simulation project the simulation cannot be started.</li>
//	 * <li>If the selected simulation project is in a lower order than the requested simulation it will pass the check</li>
//	 * </ul>
//	 * Finally, if the check passed the type which was checked against it is set to the simulation project.
//	 * @param type The simulation type to check against
//	 */
//	private SimulationProject askUserToCreateSimulationProjectIfNecessary(SimulationProject.TYPE type) {
//		// check if a selected simulation project can be started or if a new one shall be created
////		if(selectedSimulationProject == null ||
////				(selectedSimulationProject != null 	&& (selectedSimulationProject.getSimulationTypeId().getOrder() >= type.getOrder()))) {
//			// ask the user to create a new simulation project
//			boolean newSimProject = MessageDialog.openQuestion(display.getActiveShell(), 
//					"New simulation project", 
//					selectedSimulationProject == null 
//					? "No simulation project was selected. "
//						+ "Do you want to create a new simulation project? If not a simulation cannot be started."
//					: "The selected simulation project: "+selectedSimulationProject.getName()+" "
//						+ "is of type: "+selectedSimulationProject.getSimulationTypeId()+" but you wants to start a simulation of type: "+type+". "
//						+ "Do you want to create a new simulation project?  If not a simulation cannot be started.");
//			if(newSimProject) {
//				try {
//					return createNewSimulationProject(type);
//				} catch (IOException e) {
//					LOG.warn("The simulation cannot be started - {}", new Object[]{e.getMessage()}, e);					
//				}
//			}
//	//	}
//		return null;
//	}
	
	public void startBuildingSimulation() {
		final SimulationProject oldSimProject = selectedSimulationProject;
		try {
			SimulationProject newSimProject = createNewSimulationProject(SimulationProject.TYPE.NANDRAD);
			if(newSimProject != null) {
				this.selectedSimulationProject = newSimProject;
			} else {
				LOG.warn("The simulation cannot be started");
				showMessage("No simulation start", "The simulation cannot be started", true);
				return;
			}	
			
			// start building sim job
			Job job = new Job("Start building simulation job") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {			
						monitor.beginTask("Starting building simulation...", 3);	
						selectedSimulationProject.setSimulationTypeId(SimulationProject.TYPE.NANDRAD);
						
						// add the IFC, the ontology and the simulation matrix
						monitor.subTask("Add building model and link model to simulation project");
						addIfcAndOntologyModelToSimulationProject(selectedSimulationProject);
						monitor.worked(1);
						
						monitor.subTask("Save simulation matrix");
						energySimulationService.saveSimulationMatrixToSimulationProject(matrix, userId, selectedSimulationProject);
						monitor.worked(1);

						monitor.subTask("Start simulation");
						
						String isInTestMode = configurationService.getProperty(PROPERTIES.TEST_MODE);
						// TODO remove test mode condition
						if(StringUtils.isNotEmpty(isInTestMode)) {
							boolean testMode = Boolean.valueOf(isInTestMode);
							if(testMode) { // test mode
								createFakeBuildingSimulationResults();
							} else { // production mode
								
							}
						} else { // test mode nicht angegeben in der Konfiguration
							
						}
						
						monitor.worked(1);
						monitor.done();
						
						showMessage("Building simulation started successfully", "Building simulation started successfully", false);
						sendSync(CommunicationEvents.USERREPOSITORY_UPDATED, null);	
						// send event to show the new status of the simulation in the project tree
						sendSync(CommunicationEvents.SIMULATION_STATUS, selectedSimulationProject);
					} catch (Exception e) {
						LOG.error("{}", e.getMessage(), e);
						showMessage("Start building simulation problem", 
								"Following problem occurred when starting the building simulation: "+e.getMessage(), true);
						selectedSimulationProject = oldSimProject;
					}
					return org.eclipse.core.runtime.Status.OK_STATUS;
				}
			};
			// Start the Job
			job.setUser(true);
			job.schedule();
		} catch (IOException e) {
			LOG.warn("The simulation cannot be started - {}", new Object[]{e.getMessage()}, e);
			selectedSimulationProject = oldSimProject;
		}
	}
	
	/**
	 * Starts the passive simulation which uses the latest ontology model, the IFC model.
	 * It uses <b><span style="text-decoration:underline;">not</span></b> the simulation matrix.
	 */
	public void startPassiveSimulation() {	
		final SimulationProject oldSimProject = selectedSimulationProject;
		try {
			SimulationProject newSimProject = createNewSimulationProject(SimulationProject.TYPE.NANDRAD_PASSIV);
			if(newSimProject != null) {
				this.selectedSimulationProject = newSimProject;
			} else {
				LOG.warn("The simulation cannot be started");
				showMessage("No simulation start", "The simulation cannot be started", true);
				return;
			}		
	
			// start passive sim job
			Job job = new Job("Start passive simulation job") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {			
						monitor.beginTask("Starting passive simulation...", 2);	
						selectedSimulationProject.setSimulationTypeId(SimulationProject.TYPE.NANDRAD_PASSIV);
						
						// add the IFC, the ontology and the simulation matrix
						monitor.subTask("Add building model and link model to simulation project");
						addIfcAndOntologyModelToSimulationProject(selectedSimulationProject);
						monitor.worked(1);
	
						monitor.subTask("Start simulation");
						
						String isInTestMode = configurationService.getProperty(PROPERTIES.TEST_MODE);
						// TODO remove test mode condition
						if(StringUtils.isNotEmpty(isInTestMode)) {
							boolean testMode = Boolean.valueOf(isInTestMode);
							if(testMode) { // test mode
								createFakePassiveSimulationResults();
							} else { // production mode
								energySimulationService.startPassiveSimulation(userId, selectedSimulationProject);
							}
						} else { // test mode nicht angegeben in der Konfiguration
							energySimulationService.startPassiveSimulation(userId, selectedSimulationProject);
						}
						
						monitor.worked(1);
						monitor.done();
						
						showMessage("Passive simulation started successfully", "Passive simulation started successfully", false);
						sendSync(CommunicationEvents.USERREPOSITORY_UPDATED, null);	
						// send event to show the new status of the simulation in the project tree
						sendSync(CommunicationEvents.SIMULATION_STATUS, selectedSimulationProject);
					} catch (Exception e) {
						LOG.error("{}", e.getMessage(), e);
						showMessage("Start passive simulation problem", 
								"Following problem occurred when starting the passive simulation: "+e.getMessage(), true);
						selectedSimulationProject = oldSimProject;
					}
					return org.eclipse.core.runtime.Status.OK_STATUS;
				}
			};
			// Start the Job
			job.setUser(true);
			job.schedule();
		} catch (IOException e) {
			LOG.warn("The simulation cannot be started - {}", new Object[]{e.getMessage()}, e);
			selectedSimulationProject = oldSimProject;
		}
	}

	/**
	 * @param requestedLocationIds 
	 * @throws IOException
	 */
	public void startSensitivityAnalysis(final Set<EIfcspatialstructureelement> requestedLocationIds) throws IOException {
		final SimulationProject oldSimProject = selectedSimulationProject;
		try {
			// we need the old remoteId because it is used as one project in the cloud
			String remoteId = oldSimProject.getRemoteId();
			SimulationProject newSimProject = createNewSimulationProject(SimulationProject.TYPE.THERAKLES);
			if(newSimProject != null) {
				this.selectedSimulationProject = newSimProject;	
				selectedSimulationProject.setRemoteId(remoteId);
			} else {
				LOG.warn("The simulation cannot be started");
				showMessage("No simulation start", "The simulation cannot be started because its from the wrong type", true);
				return;
			}
			
			// start sensitivity analysis job
			Job job = new Job("Start sensitivity analysis job") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {	
						selectedSimulationProject.setSimulationTypeId(SimulationProject.TYPE.THERAKLES);
						monitor.beginTask("Starting sensitivity analysis...", 4);	
						
						monitor.subTask("Add building model and link model");	
						addIfcAndOntologyModelToSimulationProject(selectedSimulationProject);
						monitor.worked(1);					
						
						monitor.subTask("Add simulation targets");
						simulationMatrixService.addTargets(requestedLocationIds, matrix);
						monitor.worked(1);
						
						monitor.subTask("Save simulation matrix");	
						// before starting anything we save the matrix so that no variants are lost
						FileInformation matrixFi = energySimulationService.saveSimulationMatrixToSimulationProject(matrix, userId, selectedSimulationProject);
						monitor.worked(1);
						monitor.subTask("Start simulation");
						
						String isInTestMode = configurationService.getProperty(PROPERTIES.TEST_MODE);
						// TODO remove test mode condition
						if(StringUtils.isNotEmpty(isInTestMode)) {
							boolean testMode = Boolean.valueOf(isInTestMode);
							if(testMode) { // test mode
								createFakeSensitivityResults();
							} else { // production mode
								energySimulationService.startSensitivityAnalysis(selectedSimulationProject);
							}
						} else { // test mode nicht angegeben in der Konfiguration
							energySimulationService.startSensitivityAnalysis(selectedSimulationProject);
						}
						monitor.worked(1);
						monitor.done();
						
						showMessage("Sensitivity analysis started successfully", "Sensitivity analysis started successfully", false);
						sendSync(CommunicationEvents.USERREPOSITORY_UPDATED, null);	
						// send event to show the new status of the simulation in the project tree
						sendSync(CommunicationEvents.SIMULATION_STATUS, selectedSimulationProject);
					} catch (Exception e) {
						LOG.error("{}", e.getMessage(), e);
						showMessage("Start sensitivity analysis problem", 
								"Following problem occurred when starting the sensitivity analysis: "+e.getMessage(), true);
						selectedSimulationProject = oldSimProject;
					} 
					return org.eclipse.core.runtime.Status.OK_STATUS;
				}
			};
			// Start the Job
			job.setUser(true);
			job.schedule();
		} catch (IOException e) {
			LOG.warn("The simulation cannot be started - {}", new Object[]{e.getMessage()}, e);
			selectedSimulationProject = oldSimProject;
		}
	}
	
	public void startGreenBuildingDesignOptimization() {
		final SimulationProject oldSimProject = selectedSimulationProject;
		try {
			// we need the old remoteId because it is used as one project in the cloud
			String remoteId = oldSimProject.getRemoteId();
			final OptimizationSimulationProject newSimProject = (OptimizationSimulationProject) createNewSimulationProject(SimulationProject.TYPE.OPTIMIZATION);
			if(newSimProject != null) {
				this.selectedSimulationProject = newSimProject;	
				selectedSimulationProject.setRemoteId(remoteId);
			} else {
				LOG.warn("The simulation cannot be started");
				showMessage("No simulation start", "The simulation cannot be started because its from the wrong type", true);
				return;
			}
			Collection<EIfcroot> zonesOfInterest = getZonesOfInterest();
			if(zonesOfInterest == null || zonesOfInterest.isEmpty())
				zonesOfInterest = new HashSet<EIfcroot>();
			
			// user can select which rooms will be optimized
			final Collection<EIfcspatialstructureelement> requestedLocationIds = new HashSet<EIfcspatialstructureelement>();
			IfcTreeSelectionDialog roomDialog = new IfcTreeSelectionDialog(display.getActiveShell(), 
					new IFCLabelProvider(), new IFCModelContentProvider(ContentMode.ROOMS), zonesOfInterest );
			roomDialog.setInput(getIfcModel());
			if(roomDialog.open() == Window.OK) {
				Set<EIfcroot> entities = roomDialog.getCheckedEntities();
				for(EIfcroot ifcRoot : entities) {
					requestedLocationIds.add((EIfcspatialstructureelement) ifcRoot);
				}
			}
			
			final Map<String, EnergyKeyPerformanceIndicatorsToBe> roomGuidToEKPI = new HashMap<String, EnergyKeyPerformanceIndicatorsToBe>();
			for(EIfcspatialstructureelement room : requestedLocationIds) {
				String roomGuid = room.getGlobalid(room);
				String roomName = room.getName(room);
				EnergyKeyPerformanceIndicatorsToBe eKPIToBe = null;
				
				// first show dialog for setting up the simulation parameters
				GreenBuildingDesignSetupParameterDialog dialog = new GreenBuildingDesignSetupParameterDialog(
						Display.getDefault().getActiveShell(),
						roomName,
						roomGuid);
				if(dialog.open() == Window.OK) {
					eKPIToBe = new EnergyKeyPerformanceIndicatorsToBe();
					float outerWallThermalTransmittances = dialog.getOuterWallThermalTransmittances();
					float innerWallThermalTransmittances = dialog.getInnerWallThermalTransmittances();
					float slabThermalTransmittances = dialog.getSlabThermalTransmittances();
					float windowThermalTransmittances = dialog.getWindowThermalTransmittances();
					float doorThermalTransmittances = dialog.getDoorThermalTransmittances();
					
					float outerWallCosts = dialog.getOuterWallCosts();
					float innerWallCosts = dialog.getInnerWallCosts();
					float slabCosts = dialog.getSlabCosts();
					float windowCosts = dialog.getWindowCosts();
					float doorCosts = dialog.getDoorCosts();
					
					eKPIToBe.setOuterWallThermalTransmittances(outerWallThermalTransmittances);
					eKPIToBe.setOuterWallCosts(outerWallCosts);
					eKPIToBe.setInnerWallThermalTransmittances(innerWallThermalTransmittances);
					eKPIToBe.setInnerWallCosts(innerWallCosts);
					eKPIToBe.setSlabThermalTransmittances(slabThermalTransmittances);
					eKPIToBe.setSlabCosts(slabCosts);
					eKPIToBe.setWindowThermalTransmittances(windowThermalTransmittances);
					eKPIToBe.setWindowCosts(windowCosts);
					eKPIToBe.setDoorThermalTransmittances(doorThermalTransmittances);
					eKPIToBe.setDoorCosts(doorCosts);
					roomGuidToEKPI.put(roomGuid, eKPIToBe);
				} else {
					MessageDialog.openInformation(
							Display.getDefault().getActiveShell(), 
							"No optimization for room "+roomName, 
							"Green Building Design Optimization for room "+roomName+" cannot be started");
					continue;
				}
			}
			
			// start green building design optimization job
			Job job = new Job("Start green building design optimization job") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						selectedSimulationProject.setSimulationTypeId(SimulationProject.TYPE.OPTIMIZATION);
						monitor.beginTask("Starting green building design optimization...", 5);	
						
						monitor.subTask("Add building model and link model to optimization project");	
						addIfcAndOntologyModelToSimulationProject(selectedSimulationProject);
						monitor.worked(1);	
						
						// create default simulation matrix
						monitor.subTask("Create empty simulation matrix");	
						createDefaultSimulationMatrix();
						monitor.worked(1);
						
						monitor.subTask("Add simulation targets");
						simulationMatrixService.addTargets(requestedLocationIds, matrix);
						monitor.worked(1);
						
						energySimulationService.optimizeGreenBuildingDesign(userId, ifcModel, ontologyModel, newSimProject, roomGuidToEKPI);
						
						monitor.worked(1);	
						
												
//						Collection<SimulationProject> simulations = buildAllPossibleSensitivitySimulationProjects();
//						int numberOfSimulations = simulations.size();
//						
//						// create numberOfSimulations simulation projects
//						final SimulationProject[] simulationProjects = new SimulationProject[numberOfSimulations];
//						
//						monitor.subTask("Start "+numberOfSimulations+" simulation(s)");		
//						for(int i = 0; i < numberOfSimulations; i++) {
//							final SimulationProject currentSimulationProject = simulationProjects[i];
//							
//							// prepare each sensitivity project
//							// TODO 
//							
//							monitor.subTask("Save simulation matrix in optimization project");	
//							// before starting anything we save the matrix so that no variants are lost
//							FileInformation matrixFi = simulationMatrixService.saveSimulationMatrixToSimulationProject(matrix, userId, currentSimulationProject);
//							monitor.worked(1);
//							
//							Job optimizeJob = new Job("Start job nr.: "+(i+1)+" of "+numberOfSimulations+" jobs") {
//
//								@Override
//								protected IStatus run(IProgressMonitor monitor) {
//									try {
//										energySimulationService.startSensitivityAnalysis(currentSimulationProject);
//										return org.eclipse.core.runtime.Status.OK_STATUS;
//									} catch (Exception e) {
//										LOG.error(e.getMessage(), e);
//										return org.eclipse.core.runtime.Status.CANCEL_STATUS;
//									}
//								}
//								
//							};
//							// Start the Job
//							optimizeJob.setUser(false);
//							optimizeJob.schedule();
//							monitor.worked(1);	
//						}
						
						monitor.done();
						
						showMessage("Green building design optimization started successfully", 
								"Green building design optimization started successfully", false);
						sendSync(CommunicationEvents.USERREPOSITORY_UPDATED, null);	
						// send event to show the new status of the simulation in the project tree
						sendSync(CommunicationEvents.SIMULATION_STATUS, selectedSimulationProject);
						
						
						boolean started = energySimulationService.optimizeGreenBuildingDesign(userId, ifcModel, ontologyModel, newSimProject, roomGuidToEKPI);
						return (started ? org.eclipse.core.runtime.Status.OK_STATUS : org.eclipse.core.runtime.Status.CANCEL_STATUS);
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
						return org.eclipse.core.runtime.Status.CANCEL_STATUS;
					}
				}
			};
			// Start the Job
			job.setUser(true);
			job.schedule();
		} catch (IOException e) {
			LOG.warn("The simulation cannot be started - {}", new Object[]{e.getMessage()}, e);
			selectedSimulationProject = oldSimProject;
		} catch (SdaiException e) {
			LOG.warn("{}", new Object[]{e.getMessage()}, e);
			selectedSimulationProject = oldSimProject;
		}
	}
	
	private void createFakePassiveSimulationResults() throws IOException {
		final String tempDirectory = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		
		// load dependent on Project the appropriate results
		final String ISES_SMALL_BUILDING = "0fj6JmRC91lhUssOKcXMNY";
		final String ISES_OPERA_BUILDING = "3jt73qsBn9S8d4xgLh1k6W";
		final String ISES_OPERA_RENOVATION_BUILDING = "2Rq_X1ean0fhdsH1Cwtl9Q";
		final String ISES_KINDERGARDEN_BUILDING = "0xFpLR8lDF$vqrT$hgVAuB";
		
		String PATH_TO_PASSIVE_SIM_PROJECT_RESULTS = "resources/cloudapi/passive/";
		
		try {
			List<String> guids = ifcModel.getBuildingsIDs();
			if(guids.contains(ISES_SMALL_BUILDING)) {
				PATH_TO_PASSIVE_SIM_PROJECT_RESULTS += "ISES_small_results.zip";
			} else if(guids.contains(ISES_OPERA_BUILDING)) {
				PATH_TO_PASSIVE_SIM_PROJECT_RESULTS += "ISES_Oper_Bestand_results.zip";
			} else if(guids.contains(ISES_OPERA_RENOVATION_BUILDING)) {
				PATH_TO_PASSIVE_SIM_PROJECT_RESULTS += "ISES_Oper_Umbau_results.zip";
			} else if(guids.contains(ISES_KINDERGARDEN_BUILDING)) {
				PATH_TO_PASSIVE_SIM_PROJECT_RESULTS += "ISES_Kindergarten_results.zip";
			} else if(guids.contains("1leXU$jonFaRAAZAhjymnt")) {
				PATH_TO_PASSIVE_SIM_PROJECT_RESULTS += "D4E_results.zip";
			} else {
				throw new IllegalArgumentException("No GlobalId is given to upload fake results!");
			}
		} catch (IfcException e) {
			LOG.warn(e.getMessage(), e);
		}
			

//		copy results to output
		URL resultsZipUrl = Activator.getDefault().getBundle().getEntry(PATH_TO_PASSIVE_SIM_PROJECT_RESULTS);
		List<File> unzippedfiles = FileService.unzip(resultsZipUrl.openStream(), tempDirectory);
		for(File unzippedFile : unzippedfiles) {
			energySimulationService.addSimulationResource(userId, selectedSimulationProject, SimulationProject.DEFAULT_RELATIVE_OUTPUT_DIR, unzippedFile);
			// delete file
			FileService.deleteFile(unzippedFile);
		}

		selectedSimulationProject.setSimulationStatus(STATUS.COMPLETED);
		userService.updateSimulationProject(selectedSimulationProject);
	}
	
	private void createFakeBuildingSimulationResults() throws IOException {
		final String tempDirectory = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);
		
		// load dependent on Project the appropriate results
		final String ISES_SMALL_BUILDING = "0fj6JmRC91lhUssOKcXMNY";
		final String ISES_OPERA_BUILDING = "3jt73qsBn9S8d4xgLh1k6W";
		final String ISES_OPERA_RENOVATION_BUILDING = "2Rq_X1ean0fhdsH1Cwtl9Q";
		final String ISES_KINDERGARDEN_BUILDING = "0xFpLR8lDF$vqrT$hgVAuB";
		
		String PATH_TO_BUILDING_SIM_PROJECT_RESULTS = "resources/cloudapi/building/";
		
//		try {
//			List<String> guids = ifcModel.getBuildingsIDs();
//			if(guids.contains(ISES_SMALL_BUILDING)) {
//				PATH_TO_PASSIVE_SIM_PROJECT_RESULTS += "ISES_small_results.zip";
//			} else if(guids.contains(ISES_OPERA_BUILDING)) {
//				PATH_TO_PASSIVE_SIM_PROJECT_RESULTS += "ISES_Oper_Bestand_results.zip";
//			} else if(guids.contains(ISES_OPERA_RENOVATION_BUILDING)) {
//				PATH_TO_PASSIVE_SIM_PROJECT_RESULTS += "ISES_Oper_Umbau_results.zip";
//			} else if(guids.contains(ISES_KINDERGARDEN_BUILDING)) {
//				PATH_TO_PASSIVE_SIM_PROJECT_RESULTS += "ISES_Kindergarten_results.zip";
//			}
//		} catch (IfcException e) {
//			LOG.warn(e.getMessage(), e);
//		}
//			
//
////		copy results to output
//		URL resultsZipUrl = Activator.getDefault().getBundle().getEntry(PATH_TO_BUILDING_SIM_PROJECT_RESULTS);
//		List<File> unzippedfiles = fileService.unzip(resultsZipUrl.openStream(), tempDirectory);
//		for(File unzippedFile : unzippedfiles) {
//			energySimulationService.addSimulationResource(userId, selectedSimulationProject, SimulationProject.DEFAULT_RELATIVE_OUTPUT_DIR, unzippedFile);
//			// delete file
//			fileService.deleteFile(unzippedFile);
//		}

		selectedSimulationProject.setSimulationStatus(STATUS.COMPLETED);
		userService.updateSimulationProject(selectedSimulationProject);
	}

	private void createFakeSensitivityResults() throws IOException {
		final String tempDirectory = configurationService.getProperty(PROPERTIES.PATH_TEMP_DIRECTORY);

		// load dependent on Project the appropriate results
		final String ISES_SMALL_BUILDING = "0fj6JmRC91lhUssOKcXMNY";
		final String ISES_OPERA_BUILDING = "3jt73qsBn9S8d4xgLh1k6W";
		final String ISES_OPERA_RENOVATION_BUILDING = "2Rq_X1ean0fhdsH1Cwtl9Q";
		final String ISES_KINDERGARDEN_BUILDING = "0xFpLR8lDF$vqrT$hgVAuB";
		String PATH_TO_THERAKLES_SIM_PROJECT_RESULTS = "resources/cloudapi/sensi/";
		
		try {
			List<String> guids = ifcModel.getBuildingsIDs();
			if(guids.contains(ISES_SMALL_BUILDING)) {
				PATH_TO_THERAKLES_SIM_PROJECT_RESULTS += "ISES_small_TheraklesProjectsSummary.zip";
			} else if(guids.contains(ISES_OPERA_BUILDING)) {
				PATH_TO_THERAKLES_SIM_PROJECT_RESULTS += "ISES_Oper_Umbau_TheraklesProjectsSummary.zip";
			} else if(guids.contains(ISES_OPERA_RENOVATION_BUILDING)) {
				PATH_TO_THERAKLES_SIM_PROJECT_RESULTS += "ISES_Oper_Umbau_TheraklesProjectsSummary.zip";
			} else if(guids.contains(ISES_KINDERGARDEN_BUILDING)) {
				PATH_TO_THERAKLES_SIM_PROJECT_RESULTS += "ISES_Kiga_TheraklesProjectsSummary.zip";
			} else if(guids.contains("1leXU$jonFaRAAZAhjymnt")) {
				PATH_TO_THERAKLES_SIM_PROJECT_RESULTS += "D4EHouse_Summary.zip";
			}
		} catch (IfcException e) {
			LOG.warn(e.getMessage(), e);
		}
		
//		copy results to output
		URL resultsZipUrl = Activator.getDefault().getBundle().getEntry(PATH_TO_THERAKLES_SIM_PROJECT_RESULTS);
		List<File> unzippedfiles = FileService.unzip(resultsZipUrl.openStream(), tempDirectory);
		for(File unzippedFile : unzippedfiles) {
			energySimulationService.addSimulationResource(userId, selectedSimulationProject, SimulationProject.DEFAULT_RELATIVE_OUTPUT_DIR, unzippedFile);
			// delete file
			FileService.deleteFile(unzippedFile);
		}

		selectedSimulationProject.setSimulationStatus(STATUS.COMPLETED);
		userService.updateSimulationProject(selectedSimulationProject);
	}
	
	public Ifc2x3DataModelProxy getIfcModel() {
		return ifcModel;
	}
	
	public void setDisplay(Display display) {
		this.display = display;
	}
	
	public void setSpaceBoundaryService(SpaceBoundaryService spaceBoundaryService) {
		this.spaceBoundaryService = spaceBoundaryService;
	}

	public void setOntologyService(OntologyService ontologyService) {
		this.ontologyService = ontologyService;
	}

	public void setResourceService(TemplateService resourceService) {
		this.resourceService = resourceService;
	}

	public void setSimulationMatrixService(
			SimulationMatrixService simulationMatrixService) {
		this.simulationMatrixService = simulationMatrixService;
	}
	
	public void setEnergySimulationService(
			EnergySimulationService energySimulationService) {
		this.energySimulationService = energySimulationService;
	}

	public void setIfcModelService(BimFitService modelService) {
		this.modelService = modelService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public Collection<EIfcroot> getZonesOfInterest() {
		return zonesOfInterest;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
