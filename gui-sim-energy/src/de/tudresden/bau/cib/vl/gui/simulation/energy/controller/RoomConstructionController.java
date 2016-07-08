//package de.tudresden.bau.cib.vl.gui.simulation.energy.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeSet;
//
//import javax.xml.parsers.ParserConfigurationException;
//
//import jsdai.SIfc2x3.EIfcbuilding;
//import jsdai.SIfc2x3.EIfcbuildingelement;
//import jsdai.SIfc2x3.EIfcbuildingstorey;
//import jsdai.SIfc2x3.EIfcdoor;
//import jsdai.SIfc2x3.EIfcelement;
//import jsdai.SIfc2x3.EIfcmaterial;
//import jsdai.SIfc2x3.EIfcmateriallayer;
//import jsdai.SIfc2x3.EIfcobject;
//import jsdai.SIfc2x3.EIfcphysicalquantity;
//import jsdai.SIfc2x3.EIfcrelspaceboundary;
//import jsdai.SIfc2x3.EIfcroot;
//import jsdai.SIfc2x3.EIfcsite;
//import jsdai.SIfc2x3.EIfcspace;
//import jsdai.SIfc2x3.EIfcwall;
//import jsdai.SIfc2x3.EIfcwindow;
//import jsdai.lang.EEntity;
//import jsdai.lang.SdaiException;
//
//import org.apache.log4j.Logger;
//import org.eclipse.rwt.SessionSingletonBase;
//import org.xml.sax.SAXException;
//
//import de.tudresden.bau.cib.vl.core.extension.exception.SimulationException;
//import de.tudresden.bau.cib.vl.core.extension.simulation.SimulationController;
//import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
//import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
//import de.tudresden.bau.cib.vl.core.platform.distribution.registry.ServiceRegistry;
//import de.tudresden.bau.cib.vl.core.simulation.energy.controller.EnergySimulationController;
//import de.tudresden.bau.cib.vl.core.simulation.energy.exception.MaterialNotFoundException;
//import de.tudresden.bau.cib.vl.core.simulation.energy.model.Construction;
//import de.tudresden.bau.cib.vl.core.simulation.energy.model.Material;
//import de.tudresden.bau.cib.vl.core.simulation.energy.model.RoomSimulationModel;
//import de.tudresden.bau.cib.vl.core.simulation.energy.model.therakles.TheraklesConstruction;
//import de.tudresden.bau.cib.vl.gui.core.management.SessionManagementController;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.MaterialView;
//import de.tudresden.bau.cib.vl.gui.simulation.energy.view.RoomConstructionView;
//
//
//
//public class RoomConstructionController extends SessionSingletonBase {
//	
//	private TreeSet<Material> databaseMaterials;
//	private TheraklesConstruction selectedConstruction;
//	private Map<String, List<TheraklesConstruction>> constructionsInSpace;
//	
//	private static Logger LOGGER = Logger.getLogger(RoomConstructionController.class);
//	
//	private SimulationController simController = ServiceRegistry.getInstance().getSimulationserviceRegistry().
//			getSimulationController(EnergySimulationController.IDENTIFIER);
//
//	private MaterialView materialView;
//	private RoomConstructionView roomConstructionView;
//	
//	private Ifc2x3DataModelProxy ifcModel;
//	private String ifcFilePath;
//	
//
//	private RoomConstructionController() {
//		callDatabase();
//		
//		databaseMaterials = ServiceRegistry.getInstance().getIbkDatabases().getMaterials();
////		currentMaterialOfRoomConstruction = new TreeSet<Material>();
//	}
//
//	public static RoomConstructionController getInstance() {
//		return (RoomConstructionController) getInstance(RoomConstructionController.class);
//	}
//	
//	
//	private void callDatabase(){
//		try {
//			ServiceRegistry.getInstance().getIbkDatabases().readMaterials();
//		} catch (ParserConfigurationException e) {
//			LOGGER.error(e.getMessage());
//		} catch (SAXException e) {
//			LOGGER.error(e.getMessage());
//		} catch (IOException e) {
//			LOGGER.error(e.getMessage());
//		}
//	}
//	
//	private Material mapFromIfcAllplanToIBKDatabase(EIfcmaterial ifcMaterial) throws SdaiException {	
//		Material material = null;	
//		if(ifcMaterial.testName(ifcMaterial)){
//			String ifcMaterialName = ifcMaterial.getName(ifcMaterial);
//			try {
//				material = ServiceRegistry.getInstance().getMaterialMapper().getIBKMaterial(ifcMaterialName);		
//			} catch (MaterialNotFoundException e) {
//				LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//				material = new Material("Nicht vorhanden");
//			}
//		}
//		return material;		
//	}
//	
//	private List<TheraklesConstruction> createIBKConstructionsOfBuilding(EIfcbuilding building) {
//		this.constructionsInSpace = new HashMap<String, List<TheraklesConstruction>>();
//		List<TheraklesConstruction> constructions = new ArrayList<TheraklesConstruction>();
//		
//		try {
//			EIfcbuildingstorey[] storeys = getBuildingStoreys(building);
//			for(EIfcbuildingstorey storey : storeys) {
//					
//				EIfcspace[] spaces = getSpacesInStorey(storey);
//				for(EIfcspace space : spaces) {
//					Set<EIfcelement> ifcElements = getBuildingElementsInSpace(space);
//					for(EIfcelement ifcElement : ifcElements) {
////						TODO zur Zeit ohne Türen - wie damit verfahren???
//						if(ifcElement instanceof EIfcbuildingelement && !(ifcElement instanceof EIfcdoor)) {
//							TheraklesConstruction construction = createIBKConstructionOfBuildingElement((EIfcbuildingelement) ifcElement);
//							construction.setIfcSpaceId(space.getGlobalid(space));
//							construction.setIfcBuildingStoreyId(storey.getGlobalid(storey));
//							construction.setIfcBuildingId(building.getGlobalid(building));
//							
//							constructions.add(construction);
//							
//							if(constructionsInSpace.containsKey(space.getGlobalid(space))) {
//								int constructionTypeId = -1;
//								List<TheraklesConstruction> constructionList = constructionsInSpace.get(space.getGlobalid(space));
//								for(TheraklesConstruction c : constructionList) {
//									Map<Material, Double> materials = c.getMaterials();
//									boolean sameMaterial = Arrays.equals(construction.getMaterials().values().toArray(), materials.values().toArray());
//									if(sameMaterial) {
//										constructionTypeId = c.getConstructionTypeId();
//									}
//								}
//	//							construction type already exists
//								if(constructionTypeId > 0) {
//									construction.setConstructionTypeId(constructionTypeId);
//								} 
//	//							it is a new construction type
//								else {
//									int newConstructionTypeId = construction.getConstructionTypeId()+1;
//									construction.setConstructionTypeId(newConstructionTypeId);
//								}
//								constructionList.add(construction);
//							} else {
//								List<TheraklesConstruction> constructionList = new ArrayList<TheraklesConstruction>();
//								constructionList.add(construction);
//								constructionsInSpace.put(space.getGlobalid(space), constructionList);
//							}
//						}
//					}
//				}
//			}
//		} catch(SdaiException se) {
//			LOGGER.error(se.getMessage());
//		}
//		return constructions;
//	}
//	
//	/**
//	 * @param assignedId
//	 * @param element
//	 * @return
//	 */
//	private TheraklesConstruction createIBKConstructionOfBuildingElement(EIfcbuildingelement element) {	
//		TheraklesConstruction construction = null;
//		try {
//			construction = new TheraklesConstruction(element.getGlobalid(element));
//			construction.setName(getNameOfIfcRoot(element));
//				
////			search material layer from IFC
//			Set<EIfcmateriallayer> ifcMaterialLayers = getAssociatedMaterialLayers(element);
//			for(EIfcmateriallayer layer : ifcMaterialLayers) {
//				EIfcmaterial ifcMaterial = layer.getMaterial(layer);
//				double thickness = layer.getLayerthickness(layer);
//				Material ibkMaterial = mapFromIfcAllplanToIBKDatabase(ifcMaterial);
//
//				construction.addMaterial(ibkMaterial, thickness);
//			}
//		} catch(SdaiException e) {
//			LOGGER.error(e.getMessage());
//		}
//					
////		<R_ue> - Wärmeübergangswiderstand an der Außenoberfläche
//		construction.setR_ue(Double.parseDouble(EnergySimulationController.R_UE));
//					
////		<R_ui> - Wärmeübergangswiderstand an der Innenoberfläche
//		construction.setR_ui(Double.parseDouble(EnergySimulationController.R_UI));
////		<ad>
//		construction.setAd(Double.parseDouble(EnergySimulationController.AD));
////		<zoneT>
//		construction.setZoneT(Integer.parseInt(EnergySimulationController.ZONET));
//			
////		<shadingTypeId>
//		construction.setShadingTypeId(0);
//			
////		constructionsInRoom.add(construction);
//		
////		put it in global mapping
////		ifcToIBKConstructionMapForRoom.put(element, construction);
//		
////		set it as current selection 
//		selectedConstruction = construction;
//		
//		return construction;
//	}
//	
//	public TreeSet<Material> getSortedMaterialsFromDatabase() {
//		return databaseMaterials;		
//	}
//	
//	public Material getMaterialFromIndex(TreeSet<Material> materials, int index) {
//		Material material = null;
//		int i = 0;
//		for(Material m : materials) {
//			if(i == index) {
//				material = m;
//			}
//			i++;
//		}
//		return material;
//	}
//	
//	public void saveConstruction(Map<Material, Double> materials) {
//		selectedConstruction.setMaterials(materials);
//		List<TheraklesConstruction> constructions = constructionsInSpace.get(selectedConstruction.getIfcSpaceId());
//		int typeId = 0;
//		for(TheraklesConstruction c : constructions){
//			int id = c.getConstructionTypeId();
//			if(id > typeId) {
//				typeId = id;
//			}
//		}
////		increase id
//		typeId++;
//		selectedConstruction.setConstructionTypeId(typeId);
//	}
//	
//	public void startEnergyPerformanceSimulation() throws SimulationException {
//		if(selectedConstruction != null) {
//			List<TheraklesConstruction> constructions = constructionsInSpace.get(selectedConstruction.getIfcSpaceId());
//			if(constructions != null) {
//				RoomSimulationModel roomSimulationModel = new RoomSimulationModel();
//				roomSimulationModel.setConstructions(constructions);
//				roomSimulationModel.setIfcModel(ifcModel);
//				roomSimulationModel.setIfcFile(new File(ifcFilePath));
//				simController.startSimulation(
//						ServiceRegistry.getInstance().getSessionManagementService().getSessionIdByUserId(
//								SessionManagementController.getInstance().getUser().getId()),
//						roomSimulationModel);
//				
////			StatusDialog dialog = new StatusDialog(Display.getCurrent().getActiveShell(), energyProcess);	
//			}
//		}
//		else {
////			MessageDialog.openInformation(Display.getCurrent().getActiveShell(), 
////					Messages.get().MSG_ERR_SELECT_ROOM_CONSTRUCTION, 
////					Messages.get().MSG_ERR_SELECT_ROOM_CONSTRUCTION);
//		}
//	}
//
//	public TheraklesConstruction getSelectedConstruction() {
//		return selectedConstruction;
//	}
//	
//	public void setSelectedConstruction(TheraklesConstruction selectedConstruction) {
//		if(selectedConstruction != null) {
//			this.selectedConstruction = selectedConstruction;
//			materialView.renderConstruction(selectedConstruction);
//		}
//	}
//
//	public void setConstructions(List<TheraklesConstruction> constructionsInSpace) {
//		this.constructionsInSpace = new HashMap<String, List<TheraklesConstruction>>();
//		this.constructionsInSpace.put("temp", constructionsInSpace);
//		roomConstructionView.renderConstructions(constructionsInSpace.toArray(new TheraklesConstruction[constructionsInSpace.size()]));
//	}
//
//	public void setMaterialView(MaterialView materialView) {
//		this.materialView = materialView;
//	}
//	
//	
//	
//	
//	public EIfcroot getIfcBuilding(String guid) {
//		try {
//			return ifcModel.getIfcEntity(guid, EIfcbuilding.class);
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public EIfcroot getIfcBuildingStorey(String guid) {
//		try {
//			return ifcModel.getIfcEntity(guid, EIfcbuildingstorey.class);
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public List<EIfcphysicalquantity> getElementQuantity(EIfcobject object, String quantity) {
//		try {
//			return ifcModel.getElementQuantity(object, quantity);
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public Set<EIfcelement> getBuildingElementsInSpace(EIfcspace space) {
//		return ifcModel.getPhysicalSpaceBoundary(space);
//	}
//	
//	public EIfcspace getSpace(String guid) {
//		try {
//			return (EIfcspace) ifcModel.getIfcEntity(guid, EIfcspace.class);
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public String getNameOfIfcMaterial(EIfcmaterial material) {
//		try {
//			if(material.testName(material)) {
//				return material.getName(material);
//			}
//		} catch (SdaiException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public String getNameOfIfcRoot(EIfcroot root) {
//		try {
//			if(root.testName(root)) {
//				return root.getName(root);
//			}
//		} catch (SdaiException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return root.getClass().getSimpleName();
//	}
//	
//	public String getNameOfIfcSpace(EIfcspace space) {
//		try {
//			if(space.testLongname(space)) {
//				return space.getLongname(space);
//			}
//		} catch (SdaiException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return space.getClass().getSimpleName();
//	}
//	
//	public EIfcwall[] getWallBoundaries(EIfcspace space) {
//		return ifcModel.getWallBoundaries(space);
//	}
//	
//	public Set<EIfcelement> getWindows(EIfcbuildingelement wall) {
//		return ifcModel.getFillingElement(wall, EIfcwindow.class);
//	}
//	
//	public Set<EIfcmaterial> getAssociatedMaterial(EIfcbuildingelement element) {
//		return ifcModel.getAssociatedMaterial(element);
//	}
//	
//	public Set<EIfcmateriallayer> getAssociatedMaterialLayers(EIfcbuildingelement element) {
//		return ifcModel.getAssociatedMaterialLayers(element);
//	}
//	
//	public EIfcspace[] getSpacesInStorey(EIfcbuildingstorey storey) {
//		try {
//			return ifcModel.getSpacesInStorey(storey);
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public EIfcbuildingstorey[] getBuildingStoreys(EIfcbuilding building) {
//		try {
//			return ifcModel.getBuildingStoreys(building);
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public EIfcsite getSite() {
//		EEntity[] entities;
//		try {
//			entities = ifcModel.getIfcEntitiesOf(EIfcsite.class);
//			if(entities.length > 0) {
//				return (EIfcsite)entities[0];
//			}
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public EIfcrelspaceboundary[] getSpaceBoundariesBetweenSpaceAndElement(EIfcspace space, EIfcelement element) {
//		try {
//			return ifcModel.getSpaceBoundariesBetweenSpaceAndElement(space, element);
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public String getGlobalUniqueId(EIfcroot root) {
//		try {
//			if(root.testGlobalid(root)){
//				return root.getGlobalid(root);
//			}
//		} catch (SdaiException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public Map<String, EIfcbuilding> getIfcBuildings() {
//		try {
//			return ifcModel.getBuildings();
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//	
//	public Set<EIfcrelspaceboundary> getSpaceBoundaries() {
//		Set<EIfcrelspaceboundary> spaceBoundaries = new HashSet<EIfcrelspaceboundary>();
//		try {
//			EEntity[] entities = ifcModel.getIfcEntitiesOf(EIfcrelspaceboundary.class);
//			for(EEntity e : entities) {
//				if(e instanceof EIfcrelspaceboundary) {
//					EIfcrelspaceboundary sb = (EIfcrelspaceboundary)e;
//					spaceBoundaries.add(sb);
//				}
//			}
//			return spaceBoundaries;
//		} catch (IfcException e) {
//			LOGGER.error("[RoomConstructionController]  "+e.getMessage());
//		}
//		return null;
//	}
//
//	public Ifc2x3DataModelProxy getIfcModel() {
//		return ifcModel;
//	}
//
//	public void setIfcModel(Ifc2x3DataModelProxy ifcModel) {
//		this.ifcModel = ifcModel;
//		
//		try {
//			List<TheraklesConstruction> constructions = new ArrayList<TheraklesConstruction>();
//			Collection<EIfcbuilding> buildings = ifcModel.getBuildings().values();
//			Iterator<EIfcbuilding> it = buildings.iterator();
//			while(it.hasNext()){
//				EIfcbuilding b = it.next();
//				List<TheraklesConstruction> constructionsOfBuilding = createIBKConstructionsOfBuilding(b);
//				constructions.addAll(constructionsOfBuilding);
//			}
//			roomConstructionView.renderConstructions(constructions.toArray(new TheraklesConstruction[constructions.size()]));
//		} catch(IfcException se) {
//			
//		}
//	}
//
//	public void setRoomConstructionView(RoomConstructionView roomConstructionView) {
//		this.roomConstructionView = roomConstructionView;
//	}
//
//	public String getIfcFilePath() {
//		return ifcFilePath;
//	}
//
//	public void setIfcFilePath(String ifcFilePath) {
//		this.ifcFilePath = ifcFilePath;
//	}
//
//}
