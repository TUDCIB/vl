package de.tudresden.bau.cib.vl.gui.simulation.energy.controller.assignment;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcbuildingelement;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcsite;
import jsdai.SIfc2x3.EIfcwindow;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.simmatrix.TSimulationMatrix;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.core.model.eeBim.service.TemplateService;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ClimateLocationTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.ConstructionTemplate;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialLayer;
import de.tudresden.bau.cib.vl.core.model.eeBim.template.MaterialTemplate;
import de.tudresden.bau.cib.vl.core.model.ontology.OntologyModel;
import de.tudresden.bau.cib.vl.core.model.ontology.exception.FactoryException;
import de.tudresden.bau.cib.vl.core.model.ontology.service.OntologyService;
import de.tudresden.bau.cib.vl.core.simulation.energy.exception.SimulationMatrixException;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.SimulationMatrixService;
import de.tudresden.bau.cib.vl.core.simulation.energy.service.SimulationMatrixService.AssignmentGroup;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCLabelProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCModelContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IfcContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IfcEntityCollectionContentProvider;
import de.tudresden.bau.cib.vl.gui.bim.view.ontologytreeviewer.OntologyTreeNode;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.core.util.TreeNode;
import de.tudresden.bau.cib.vl.gui.simulation.energy.controller.ProcessController;
import de.tudresden.bau.cib.vl.gui.simulation.energy.model.SensitivityClimateModel;
import de.tudresden.bau.cib.vl.gui.simulation.energy.model.SensitivityConstructionModel;
import de.tudresden.bau.cib.vl.gui.simulation.energy.model.SensitivityWindowModel;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment.ClimateResourceAssignmentDialog;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment.ConstructionResourceAssignmentDialog;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment.StochasticClimateResourceAssignmentDialog;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment.StochasticConstructionResourceAssignmentDialog;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment.StochasticWindowResourceAssignmentDialog;
import de.tudresden.bau.cib.vl.gui.simulation.energy.view.dialog.assignment.WindowResourceAssignmentDialog;

public class AssignmentHelper {
	
	private boolean isStochasticPhase = false;
	private Ifc2x3DataModelProxy ifcModel;
	private OntologyModel ontologyModel;
	private TSimulationMatrix matrix;
	private Integer userId;
	
	private TemplateService resourceService;
	private SimulationMatrixService simulationMatrixService;
	private OntologyService ontologyService;
	
	private static final Logger LOG = LoggerFactory.getLogger(AssignmentHelper.class);
	
	
	/**
	 * The shared instance.
	 */
	private static AssignmentHelper INSTANCE = new AssignmentHelper();

	/**
	 * Private constructor because of singleton pattern.
	 */
	private AssignmentHelper() {

	}

	/**
	 * Retrieves the single instance of AssignmentHelper.
	 * @return The instance of AssignmentHelper.
	 */
	public static AssignmentHelper getInstance() {
		if (INSTANCE == null)
			INSTANCE = new AssignmentHelper();
		return INSTANCE;
	}

	
	public void evaluateAssignment(Integer userId, Ifc2x3DataModelProxy ifcModel, OntologyModel ontologyModel, 
			boolean isInStochasticPhase, TSimulationMatrix matrix, 
			Map<String, Set<EIfcroot>> resourcesToIfcEntities, Map<String, Object> uiDataMap) {
		this.userId = userId;
		this.ifcModel = ifcModel;
		this.ontologyModel = ontologyModel;
		this.matrix = matrix;
		this.isStochasticPhase = isInStochasticPhase;
		if(resourcesToIfcEntities.size() != 1) {
			throw new IllegalArgumentException("Cannot handle multiple resource assignments to multiple IFC entities (m:n) currently (only 1:n) : "+resourcesToIfcEntities);
		}
		final String resourceUri = resourcesToIfcEntities.keySet().iterator().next();
		try {
			List<ConstructionTemplate> constructionResources = resourceService.listConstructionResources(userId);

			final ConstructionTemplate construction = resourceService.findConstructionTemplateByUri(resourceUri, constructionResources);
			
			// construction resource
			if(construction != null) {
				if(uiDataMap != null) { // DnD on the IFC tree
					Object objMode = uiDataMap.get("mode");
					Object objTreeNode = uiDataMap.get("node");
					if(objMode instanceof ContentMode) {
						ContentMode mode = (ContentMode) objMode;
						if(mode == ContentMode.NOT_ASSIGNED) {
							OntologyTreeNode ontoTreeNode = (OntologyTreeNode) objTreeNode;
							evaluateConstructionAssignment(resourceUri, mode, ontoTreeNode, construction);
						} else {
							IFCTreeNode ifcTreeNode = (IFCTreeNode)objTreeNode;
							evaluateConstructionAssignment(resourceUri, mode, ifcTreeNode, construction);
						}
					}
				} else { // DnD on the IFC Viewer
					ContentMode mode = ContentMode.SPATIALSTRUCTURE;
					Set<EIfcroot> ifcEntities = resourcesToIfcEntities.get(resourceUri);
					evaluateConstructionAssignment(resourceUri, mode, ifcEntities, construction, false);
				}			
			}
			// no construction -> occupancy or climate
			else {
				// climate
				List<ClimateLocationTemplate> climateResources = resourceService.listClimateResources(userId);
				final ClimateLocationTemplate climate = resourceService.findClimate(resourceUri, climateResources);
				if(climate != null) {
					evaluateClimateAssignment(climate);
				} else { // occupancy
				
				// TODO occupancy
				
//				ResourceAssignmentDialog dialog = new ResourceAssignmentDialog(
//						display.getActiveShell(), new IFCLabelProvider(), 
//						new IFCContentProvider(ContentMode.ROOMS), resourceUri, ifcTreeNode);
//				dialog.setInput(ifcModel);
//				if(dialog.open() == Window.OK) {
//					Set<EIfcroot> entities = dialog.getCheckedEntities();
//					// assign data in ontology
//					Map<String, Set<EIfcroot>> dialogResourceAssignments = new HashMap<String, Set<EIfcroot>>();
//					dialogResourceAssignments.put(resourceUri, entities);
//					assignResourcesToIfcEntities(dialogResourceAssignments);
				
//				Set<String> spaceGuids = evaluateAssignmentGroupFromTreeNode(ifcTreeNode, EIfcspace.class);
//				String assignmentGroupId = simulationMatrixService.createAssignmentGroup(spaceGuids, AssignmentGroup.SpaceGroup, matrix);
//				}
				}
			}
		} catch (Exception e) {
			LOG.error("{}", new Object[]{e.getMessage()}, e);
			showMessage("Assignment problem", e.getMessage(), true);
		} 
		// send event
		ProcessController.getInstance().sendSync(CommunicationEvents.SIMULATION_MATRIX_MODEL, matrix);
	}

	private void evaluateClimateAssignment(final ClimateLocationTemplate climate)
			throws IfcException, FactoryException, SimulationMatrixException {
		if(isStochasticPhase) { // stochastic
			StochasticClimateResourceAssignmentDialog dialog = new StochasticClimateResourceAssignmentDialog(Display.getDefault().getActiveShell(), climate);
			SensitivityClimateModel sensitivityClimateModel = new SensitivityClimateModel();
			dialog.setModel(sensitivityClimateModel);
			if(dialog.open() == Window.OK) {	
				Set<String> varIds = retrieveStochasticClimateData(sensitivityClimateModel, climate);
				for(String varId : varIds) {
					// add combination
					simulationMatrixService.addCombinations(varId, null, matrix);
				}
			}
		} else { // deterministic
			ClimateResourceAssignmentDialog dialog = new ClimateResourceAssignmentDialog(Display.getDefault().getActiveShell(), climate);
			if(dialog.open() == Window.OK) {
				// assign climate to IfcSite
				assignClimate(climate);
			}
		}
	}

	private void assignClimate(ClimateLocationTemplate climate) throws IfcException, FactoryException {
		Set<String> assignToGuidsSet = new HashSet<String>();
//		String uri = climate.getSourceFileUri();
		EIfcsite site = ifcModel.getIfcSite();
		String guid = ifcModel.getGlobalId(site);
		
		if(site != null) {
			assignToGuidsSet.add(guid);
		} else {
			LOG.warn("No IfcSite in BIM, it will be assigned to the building(s)");
			throw new NullPointerException("No IfcSite to assign climate "+climate);
//			Map<String, EIfcbuilding> building = ifcModel.getBuildings();
//			for (Map.Entry<String, EIfcbuilding> entry : building.entrySet()) {
//				String key = entry.getKey();	
//				assignToGuidsSet.add(key);
//			}
		}
		
		// delete a previous relation if existing
		String previousAssignment = ontologyService.existsRelation(ifcModel, guid, climate, ontologyModel);
		boolean removeAssignment = false;
		if(previousAssignment != null) {
			EIfcroot entity = ifcModel.getIfcEntity(guid, null);
			removeAssignment = MessageDialog.openQuestion(
					Display.getDefault().getActiveShell(), 
					"Remove previous assignment?", 
					"Do you want to remove the previous assignment: "+previousAssignment+" from: "+entity+"?");
		
		}
		
		for(String id : assignToGuidsSet) {
			if(removeAssignment) {
				ontologyService.removeHasClimate(ifcModel, id, ontologyModel);
			}			
			ontologyService.addHasClimate(ifcModel, id, climate, ontologyModel);
			
		}
		
	}
	
	private void assignConstructionResourcesToIfcEntitiesInOntology(
			final Map<String, Set<EIfcroot>> resourcesToIfcEntities) throws IfcException, FactoryException, IOException {
		try {			
			List<ConstructionTemplate> constructionResources = resourceService.listConstructionResources(userId);
			resource: for(Map.Entry<String, Set<jsdai.SIfc2x3.EIfcroot>> entry : resourcesToIfcEntities.entrySet()) {
				String key = entry.getKey();
				Set<jsdai.SIfc2x3.EIfcroot> value = entry.getValue();
				ConstructionTemplate resource = resourceService.findConstructionTemplateByUri(key, constructionResources);	
				if(resource != null && resource instanceof ConstructionTemplate) {						
					if(value != null && value.size() > 0) {
						boolean assignResourceToAllIfcEntities = false;
						ifc: for(EIfcroot ifcRoot : value) {
							String name = ifcRoot.testName(ifcRoot) ? ifcRoot.getName(ifcRoot) : "[Name unknown]";								
							String guid = ifcModel.getGlobalId(ifcRoot);
							if(!assignResourceToAllIfcEntities) {
								if(ifcRoot instanceof EIfcbuildingelement) {
									// delete a previous relation if existing
									String previousAssignment = ontologyService.existsRelation(ifcModel, guid, resource, ontologyModel);
									if(previousAssignment != null) {
										MessageDialog md = new MessageDialog(Display.getCurrent().getActiveShell(), 
												"Remove previous assignment from: "+name+"?", null, 
												"Do you want to remove the previous assignment: "+previousAssignment+"?", 
												MessageDialog.QUESTION_WITH_CANCEL, 
												new String[]{"Yes", "No", "Yes to all", "Cancel"}, 0);
										int open = md.open();
										if(open == 3) continue resource; // stop the assignment of the current resource
										if(open == 2) assignResourceToAllIfcEntities = true;
										if(open == 1) continue ifc; // this IFC entity get not the assignment
	//									boolean removeAssignment = MessageDialog.openQuestion(
	//											Display.getCurrent().getActiveShell(),
	//											"Remove previous assignment from: "+name+"?", 
	//											"Do you want to remove the previous assignment: "+previousAssignment+"?");
	//									if(removeAssignment) {
	//										ontologyService.removeHasConstruction(ifcModel, guid, ontologyModel);
	//										ontologyService.addHasConstruction(ifcModel, guid, resource, ontologyModel);
	//									}
										if(open == 0 || open == 2) {
											ontologyService.removeHasConstruction(ifcModel, guid, ontologyModel);
											ontologyService.addHasConstruction(ifcModel, guid, resource, ontologyModel);
										}
									}
									else {
										ontologyService.addHasConstruction(ifcModel, guid, resource, ontologyModel);
									}
								}
							} else { // assign resource to all IFC entities
								ontologyService.removeHasConstruction(ifcModel, guid, ontologyModel);
								ontologyService.addHasConstruction(ifcModel, guid, resource, ontologyModel);
							}
						}
					}				
				}			
			}			
		} catch (Exception e) {
			LOG.error("{}", e.getMessage(), e);
			showMessage("Assignment problem", 
					"Following problem occurred when applying the assignment: "+e.getMessage(), true);
		}
	}
	
	private Set<EIfcroot> retrieveIfcEntitiesFromTreeNode(IFCTreeNode ifcTreeNode) {
		EIfcroot ifcEntity = ifcTreeNode.getIfcElement();	
		Set<EIfcroot> ifcEntities = new HashSet<EIfcroot>();
		if(ifcEntity != null) { // its a leaf
			ifcEntities.add(ifcEntity);
		} else { // its a node
			TreeNode[] children = ifcTreeNode.getChildren();
			for(TreeNode tn : children) {
				if(tn instanceof IFCTreeNode) {
					Set<EIfcroot> furtherIfcEntities = retrieveIfcEntitiesFromTreeNode((IFCTreeNode)tn);
					if(furtherIfcEntities != null) {
						ifcEntities.addAll(furtherIfcEntities);
					}
				}
			}
		}
		return ifcEntities;
	}
	
	private Set<EIfcroot> retrieveIfcEntitiesFromOntologyTreeNode(OntologyTreeNode treeNode) throws IfcException {
		Set<EIfcroot> ifcEntities = new HashSet<EIfcroot>();
		String uri = treeNode.getStrURI();
		if(StringUtils.isNotEmpty(uri)) { // its a leaf
			String guid = OntologyModel.getElementIdentifierFromUri(uri);
			EIfcroot ifcEntity = ifcModel.getIfcEntity(guid, null);
			if(ifcEntity != null) {
				ifcEntities.add(ifcEntity);
			}
		} else { // its a node
			TreeNode[] children = treeNode.getChildren();
			for(TreeNode tn : children) {
				if(tn instanceof OntologyTreeNode) {
					Set<EIfcroot> furtherIfcEntities = retrieveIfcEntitiesFromOntologyTreeNode((OntologyTreeNode)tn);
					if(furtherIfcEntities != null) {
						ifcEntities.addAll(furtherIfcEntities);
					}
				}
			}
		}
		return ifcEntities;
	}
	
	private void evaluateConstructionAssignment(String resourceUri, ContentMode objMode, TreeNode treeNode,
			ConstructionTemplate construction) throws IfcException, FactoryException, IOException, SimulationMatrixException {
		// distinction of cases			
		String nodeName = treeNode.getName();
		boolean isWindow = false;
		Set<EIfcroot> ifcEntities = null;
		if(treeNode instanceof IFCTreeNode) {
			IFCTreeNode ifcTreeNode = (IFCTreeNode)treeNode;
			ifcEntities = retrieveIfcEntitiesFromTreeNode(ifcTreeNode);
			EIfcroot ifcEntity = ifcTreeNode.getIfcElement();	
			// window 
			if(nodeName.contains("window") || ifcEntity instanceof EIfcwindow) {
				isWindow = true;
			}
		} else if(treeNode instanceof OntologyTreeNode) {
			ifcEntities = retrieveIfcEntitiesFromOntologyTreeNode((OntologyTreeNode)treeNode);
		}
		evaluateConstructionAssignment(resourceUri, objMode, ifcEntities, construction, isWindow);
	}
	
	private void evaluateConstructionAssignment(String resourceUri, ContentMode mode, Set<EIfcroot> ifcEntities,
			ConstructionTemplate construction, boolean isWindow) throws SimulationMatrixException, IfcException, FactoryException, IOException {
		// window 
		if(isWindow) {
			if(isStochasticPhase) { // stochastic
				StochasticWindowResourceAssignmentDialog dialog = new StochasticWindowResourceAssignmentDialog(
						Display.getDefault().getActiveShell(), new IFCLabelProvider(), 
						new IFCModelContentProvider(mode), construction, ifcEntities);
				dialog.setInput(ifcModel);
				SensitivityWindowModel sensitivityWindowModel = new SensitivityWindowModel();
				dialog.setModel(sensitivityWindowModel);
				if(dialog.open() == Window.OK) {	
					Set<EIfcroot> entities = dialog.getCheckedEntities();
					// assignment group
					Set<String> elementGuids = ifcModel.getGlobalIds(entities);
					String assignmentGroupId = simulationMatrixService.createAssignmentGroup(elementGuids, AssignmentGroup.ElementGroup, matrix);
					
					Set<String> varIds = retrieveStochasticWindowData(sensitivityWindowModel, construction);
					for(String varId : varIds) {
						// add combination
						simulationMatrixService.addCombinations(varId, assignmentGroupId, matrix);
					}
				}
			} else { // deterministic
				WindowResourceAssignmentDialog dialog = new WindowResourceAssignmentDialog(
						Display.getDefault().getActiveShell(), new IFCLabelProvider(), 
						new IFCModelContentProvider(mode), construction, ifcEntities);
				dialog.setInput(ifcModel);
				if(dialog.open() == Window.OK) {
					Set<EIfcroot> entities = dialog.getCheckedEntities();
					// assign data in ontology
					Map<String, Set<EIfcroot>> dialogResourceAssignments = new HashMap<String, Set<EIfcroot>>();
					dialogResourceAssignments.put(resourceUri, entities);
	
					assignConstructionResourcesToIfcEntitiesInOntology(dialogResourceAssignments);
				}
			}
		}						
		// other constructions
		else {		
			if(isStochasticPhase) { // stochastic
				final List<MaterialTemplate> materialTemplates = resourceService.listMaterialsOfConstruction(construction);
				StochasticConstructionResourceAssignmentDialog dialog = new StochasticConstructionResourceAssignmentDialog(
						Display.getDefault().getActiveShell(), new IFCLabelProvider(), 
						new IFCModelContentProvider(mode), construction, ifcEntities, materialTemplates);
				dialog.setInput(ifcModel);
				SensitivityConstructionModel sensitivityModel = new SensitivityConstructionModel();
				dialog.setModel(sensitivityModel);
				if(dialog.open() == Window.OK) {
					Set<EIfcroot> entities = dialog.getCheckedEntities();				
					// assignment group
					Set<String> elementGuids = ifcModel.getGlobalIds(entities);
					String assignmentGroupId = simulationMatrixService.createAssignmentGroup(elementGuids, AssignmentGroup.ElementGroup, matrix);
					
					Set<String> varIds = retrieveStochasticConstructionData(sensitivityModel, construction);
					for(String varId : varIds) {	
						// add combination
						simulationMatrixService.addCombinations(varId, assignmentGroupId, matrix);
					}
				}
			} else { // deterministic
				IfcContentProvider contentProvider = null;
				if(mode == ContentMode.NOT_ASSIGNED) {
					contentProvider = new IfcEntityCollectionContentProvider(ContentMode.NOT_ASSIGNED);
					ConstructionResourceAssignmentDialog dialog = new ConstructionResourceAssignmentDialog(
							Display.getCurrent().getActiveShell(), new IFCLabelProvider(), 
							contentProvider, construction, ifcEntities);	
					
					
					dialog.setInput(ifcEntities);
					if(dialog.open() == Window.OK) {
						Set<EIfcroot> entities = dialog.getCheckedEntities();
						// assign data in ontology
						Map<String, Set<EIfcroot>> dialogResourceAssignments = new HashMap<String, Set<EIfcroot>>();
						dialogResourceAssignments.put(resourceUri, entities);
		
						assignConstructionResourcesToIfcEntitiesInOntology(dialogResourceAssignments);
					}
				} else {
					contentProvider = new IFCModelContentProvider(mode);
					ConstructionResourceAssignmentDialog dialog = new ConstructionResourceAssignmentDialog(
							Display.getCurrent().getActiveShell(), new IFCLabelProvider(), 
							contentProvider, construction, ifcEntities);	
									
					dialog.setInput(ifcModel);
					if(dialog.open() == Window.OK) {
						Set<EIfcroot> entities = dialog.getCheckedEntities();
						// assign data in ontology
						Map<String, Set<EIfcroot>> dialogResourceAssignments = new HashMap<String, Set<EIfcroot>>();
						dialogResourceAssignments.put(resourceUri, entities);
		
						assignConstructionResourcesToIfcEntitiesInOntology(dialogResourceAssignments);
					}
				}

			}
		}
	}
	
	private Set<String> retrieveStochasticConstructionData(SensitivityConstructionModel sensitivityModel, final ConstructionTemplate construction) {
		Set<String> ids = new HashSet<String>();
		Map<Integer, MaterialLayer> layers = sensitivityModel.getLayers();
		double meanValue = sensitivityModel.getMeanValue();
		int sampleNumber = sensitivityModel.getSampleNumber();
		int materialIndex = sensitivityModel.getSelectedMaterialIndex();
		double standardDeviation = sensitivityModel.getStandardDeviation();
		
		double[] samples = resourceService.generateSamples(
				meanValue, 
				standardDeviation, 
				sampleNumber);
		
		for(int i = 0; i < samples.length; i++) {
			double sample = samples[i];
			layers.get(materialIndex).setThickness((float)sample);
			// add window type to matrix
			String id = simulationMatrixService.addConstructionTypeVariant(construction.getSourceFileUri(), layers, matrix);
			ids.add(id);
		}		
		return ids;
	}	
	
	private Set<String> retrieveStochasticClimateData(SensitivityClimateModel sensitivityClimateModel, ClimateLocationTemplate climate) {
		Set<String> ids = new HashSet<String>();
		
		String id = simulationMatrixService.addWeatherDataSetVariant(climate.getSourceFileUri(), matrix);
		ids.add(id);
		
		return ids;
	}
	
	private Set<String> retrieveStochasticWindowData(SensitivityWindowModel sensitivityWindowModel, final ConstructionTemplate construction) {
		Set<String> ids = new HashSet<String>();
		double meanValue = sensitivityWindowModel.getMeanValue();
		double[] samples = resourceService.generateSamples(
				meanValue, 
				sensitivityWindowModel.getStandardDeviation(), 
				sensitivityWindowModel.getSampleNumber());
		for(int i = 0; i < samples.length; i++) {
			double sample = samples[i];
			String id = null;
			// add window type to matrix
			if(sensitivityWindowModel.getFrameFractionValue() != null) {
				id = simulationMatrixService.addWindowTypeVariant(construction.getSourceFileUri(), 
						null, null, 
						""+sample, sensitivityWindowModel.getFrameFractionUnit(),
						null, null,
						null, null,
						null, null,
						matrix);
			} else if(sensitivityWindowModel.getGlassFractionValue() != null) {
				id = simulationMatrixService.addWindowTypeVariant(construction.getSourceFileUri(), 
						""+sample, sensitivityWindowModel.getGlassFractionUnit(),
						null, null, 
						null, null,
						null, null,
						null, null,
						matrix);
			} else if(sensitivityWindowModel.getThermalTransmittanceValue() != null) {
				id = simulationMatrixService.addWindowTypeVariant(construction.getSourceFileUri(), 
						null, null, 
						null, null,
						""+sample, sensitivityWindowModel.getThermalTransmittanceUnit(),
						null, null,
						null, null,
						matrix);
			} else if(sensitivityWindowModel.getSolarHeatGainValue() != null) {
				id = simulationMatrixService.addWindowTypeVariant(construction.getSourceFileUri(), 
						null, null, 
						null, null,
						null, null,
						""+sample, sensitivityWindowModel.getSolarHeatGainUnit(),
						null, null,
						matrix);
			} else if(sensitivityWindowModel.getShadingFactorValue() != null) {
				id = simulationMatrixService.addWindowTypeVariant(construction.getSourceFileUri(), 
						null, null, 
						null, null,
						null, null,
						null, null,
						""+sample, sensitivityWindowModel.getShadingFactorUnit(),
						matrix);
			}
			ids.add(id);
		}
		return ids;
	}
	
	private void showMessage(final String title, final String message, final boolean isError) {	
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				if(isError) {
					MessageDialog.openError(Display.getDefault().getActiveShell(), 
							title,
							message);
				} else {
					MessageDialog.openInformation(Display.getDefault().getActiveShell(), 
							title,
							message);
				}
			}
		});
	}


	public void setResourceService(TemplateService resourceService) {
		this.resourceService = resourceService;
	}


	public void setSimulationMatrixService(
			SimulationMatrixService simulationMatrixService) {
		this.simulationMatrixService = simulationMatrixService;
	}


	public void setOntologyService(OntologyService ontologyService) {
		this.ontologyService = ontologyService;
	}
}
