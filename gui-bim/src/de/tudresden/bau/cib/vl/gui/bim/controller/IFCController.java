package de.tudresden.bau.cib.vl.gui.bim.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcbuilding;
import jsdai.SIfc2x3.EIfcbuildingstorey;
import jsdai.SIfc2x3.EIfcelement;
import jsdai.SIfc2x3.EIfcproject;
import jsdai.SIfc2x3.EIfcroot;
import jsdai.SIfc2x3.EIfcspace;
import jsdai.lang.SdaiException;
import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.gui.bim.view.IFCMultiView;
import de.tudresden.bau.cib.vl.gui.bim.view.dialog.ContentMode;
import de.tudresden.bau.cib.vl.gui.bim.view.ifctreeviewer.IFCTreeNode;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;

public class IFCController extends FileController<IFCMultiView> {
	
	private List<IFCTreeNode> selectedNodes = new ArrayList<IFCTreeNode>();
	private Set<String> selectedIFCIds = new HashSet<String>();
	private Collection<EIfcroot> zonesOfInterest = new HashSet<EIfcroot>();
	
	
	@Override
	public void parseFile()
	{
		URL url = null;
		try {
			url = fileInformation.getUrl();
//			model = mainController.getModelService().parseFileURL(new URL(file.getUrl()));
			model = bimfitService.parseIfc2x3File(url);
		} catch (IfcException e) {
			LOG.error("Problem by parsing the model: {}", new Object[]{url}, e);
		}		
		
	}	

	
	public void setSelectionIds(Set<String> guids) {
		publishSelection(guids);	
	}
	
	public Set<EIfcelement> getBoundingElementsOfSpace(EIfcspace space) throws IfcException{
		return ((Ifc2x3DataModelProxy)model).getBoundingElementsOfSpace(space);
	}	
	
	
	public List<EIfcspace> getSpacesInStorey(EIfcbuildingstorey storey) throws IfcException {
		return Arrays.asList(((Ifc2x3DataModelProxy)model).getSpacesInStorey(storey));
	}
	
	public String getGlobalId(EIfcroot ifcRoot) throws SdaiException {
		return ifcRoot.getGlobalid(ifcRoot);
	}
	
	public void publishShow(List<String> guids) {
		sendSync(CommunicationEvents.IFC_SELECTION_SHOW, guids);
	}
	
	public void publishHide(Set<String> guids) {
		sendSync(CommunicationEvents.IFC_SELECTION_HIDE, guids);
		
	}	
	
	private void publishSelection(Set<String> guids) {
		sendSync(CommunicationEvents.IFC_SELECTION, guids);
	}
	
	public void publishShowAll() {
		sendSync(CommunicationEvents.IFC_SELECTION_SHOW_ALL, null);
	}

	public void publishMakeSolid(Set<String> selection) {
		sendSync(CommunicationEvents.IFC_SELECTION_SOLID, selection);
	}

	public void publishMakeTransparent(Set<String> selection) {
		sendSync(CommunicationEvents.IFC_SELECTION_TRANSPARENT, selection);
	}
	
	public List<EIfcbuildingstorey> getBuildingStoreys() throws IfcException {
		List<EIfcbuildingstorey> storeys = new ArrayList<EIfcbuildingstorey>();
		Map<String, EIfcbuilding> buildings = ((Ifc2x3DataModelProxy)model).getBuildings();
		for (Map.Entry<String, EIfcbuilding> entry : buildings.entrySet()) {
			EIfcbuilding value = entry.getValue();
			storeys.addAll(Arrays.asList(((Ifc2x3DataModelProxy)model).getBuildingStoreys(value)));
			
		}
		return storeys;
	}
	
	public EIfcproject getIfcProject() throws IfcException {
		return ((Ifc2x3DataModelProxy)model).getIfcProject();
		
	}
	
	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {
		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();
		//TODO remove
//		events.add(CommunicationEvents.SERVER_NEW_IFCMODEL);
		events.add(CommunicationEvents.IFC_MODEL);
		events.add(CommunicationEvents.IFC_SELECTION);
		events.add(CommunicationEvents.IFC_ZONESOFINTERESTS);	
	
		return events;
	}
	
	@Override
	protected void handleEvent(CommunicationEvents event, Map<String, Object> dataMap) {
		LOG.debug("Retrieving data: {} with topic: {}", 
				new Object[]{dataMap, event.getName()});
		Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
		
		switch(event) {
		//TODO remove
//		case SERVER_NEW_IFCMODEL: 
//		{
//			Ifc2x3DataModelProxy ifcModel = (Ifc2x3DataModelProxy) data;
//			setModel(ifcModel);
//		}
//			break;
		case IFC_MODEL: 
		{
			Ifc2x3DataModelProxy ifcModel = (Ifc2x3DataModelProxy) data;
			setModel(ifcModel);
		}			
			break;	
		case IFC_SELECTION: 
		{
			selectedIFCIds = (Set<String>) data;
			
		}			
			break;	
		case IFC_ZONESOFINTERESTS:
			zonesOfInterest = (Collection<EIfcroot>) data;	
			break;
		default:
			break;
		}
	}

	public List<IFCTreeNode> getSelectedNodes() {
		return selectedNodes;
	}


	public void setSelectedNodes(List<IFCTreeNode> selectedNodes) {
		this.selectedNodes = selectedNodes;	
		publishSelection(getSelectedNodesIFCIds());
	}
	
	
	public Set<String> getSelectedNodesIFCIds()
	{
		
		Set<String> help = new HashSet<String>();
		
		for(IFCTreeNode node : selectedNodes)
		{
			try 
			{
				help.addAll(node.getIFCIds(true));
			} catch (SdaiException e) {				
				e.printStackTrace();
			}
		}
		
		return help;
		
	}
	
	public void onDropEvent(Object eventData, ContentMode mode, Object target)
	{
		
		
			Map<String, Object> dataMap 						= new HashMap<String, Object>();
			Map<String, Set<EIfcroot>> resourceToIfcEntitiesMap = new HashMap<String, Set<EIfcroot>>();
			Set<EIfcroot> ifcEntities							= new HashSet<EIfcroot>();
			Map<String, Object> uiData 							= new HashMap<String, Object>();
			
			String resourceUri = (String) eventData;
			
			ifcEntities.add(((IFCTreeNode)target).getIfcElement());
			uiData.put("mode", mode);
			uiData.put("node", (IFCTreeNode)target);
			
//			for(IFCTreeNode node : selectedNodes)
//			{
//				ifcEntities.add(node.getIfcElement());
//				uiData.put("node", node);
//			}			
			
			resourceToIfcEntitiesMap.put(resourceUri, ifcEntities);		
			
			
			dataMap.put(CommunicationService.PROPERTIES_KEY_DATA, resourceToIfcEntitiesMap);
			dataMap.put(CommunicationService.PROPERTIES_KEY_UI_DATA, uiData);
			
			sendSync(CommunicationEvents.RESOURCE_TO_IFC, dataMap);
		
		
		
	}


	public Set<String> getSelectedIFCIds() {
		return selectedIFCIds;
	}


	public Collection<EIfcroot> getZonesOfInterest() {
		return zonesOfInterest;
	}

}
