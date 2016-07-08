package de.tudresden.bau.cib.vl.gui.bim.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jsdai.SIfc2x3.EIfcroot;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.model.bim.exception.IfcException;
import de.tudresden.bau.cib.vl.core.model.bim.ifc.Ifc2x3DataModelProxy;
import de.tudresden.bau.cib.vl.gui.bim.Activator;
import de.tudresden.bau.cib.vl.gui.bim.view.Ifc3DView;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;

public class Ifc3DViewController extends AbstractViewController<Ifc3DView> {

	private static Logger LOG = LoggerFactory.getLogger(Ifc3DViewController.class);	

	private Ifc2x3DataModelProxy ifcModel;


	@Override
	protected BundleContext getBundleContext() {
		return Activator.getDefault().getContext();
	}

	@Override
	protected Set<CommunicationEvents> defineReceivedEvents() {
		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();
		events.add(CommunicationEvents.IFC_MODEL);
		events.add(CommunicationEvents.IFC_SELECTION);
		events.add(CommunicationEvents.IFC_SELECTION_TRANSPARENT);
		events.add(CommunicationEvents.IFC_UNSELECTION);
		events.add(CommunicationEvents.IFC_SELECTION_SHOW);
		events.add(CommunicationEvents.IFC_SELECTION_HIDE);
		events.add(CommunicationEvents.IFC_SELECTION_SOLID);
		events.add(CommunicationEvents.IFC_SELECTION_SHOW_ALL);
		events.add(CommunicationEvents.IFC_SELECTION_COLORED);
		events.add(CommunicationEvents.IFC_SELECTION_UNCOLORED);
		return events;
	}

	@Override
	public void setView(Ifc3DView view) {
		super.setView(view);
	}
	
	public void retrieveLatestState(int waitTime) {
		if(ifcModel == null) {
			Runnable r = new Runnable() {		
				@Override
				public void run() {
					CommunicationService service = CommunicationService.getInstance();
					service.retrieveLatestData(CommunicationEvents.IFC_MODEL, Ifc3DViewController.this);
				}
			};
			// wait 30s until loading
			Display.getDefault().timerExec(waitTime, r);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void handleEvent(CommunicationEvents event,
			Map<String, Object> dataMap) {
		LOG.debug("Receiving data with topic: {}", event.getName());
		Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
		try {
			if(data != null) {
				switch(event) {
				case IFC_MODEL: 	
					ifcModel = (Ifc2x3DataModelProxy) data;
					if(ifcModel != null) {
						String path = ifcModel.getSourceFilePath();
						try {
							URL url = URI.create(path).toURL();
							view.setIfcFile(url);
						} catch(MalformedURLException e) {
							LOG.error(e.getMessage(), e);
						}
					}
					break;
				case IFC_SELECTION:
					Collection<String> guids = (Collection)data;
					view.setSelection(guids);
					break;
				case IFC_SELECTION_COLORED:
					Map<Color, Collection<String>> colorOfEntities = (Map<Color, Collection<String>>) data;
					view.colorizeEntities(colorOfEntities);
					break;
				case IFC_SELECTION_UNCOLORED:
					guids = (Collection<String>) data;
					view.uncolorizeEntities(guids);
					break;
				case IFC_SELECTION_SHOW_ALL: 
					List<EIfcroot> allIfcEntities = ifcModel.getIfcEntities();
					Set<String> allGuids = ifcModel.getGlobalIds(allIfcEntities);
					view.showOrHideEntities(allGuids, true);
					break;
				case IFC_UNSELECTION:
					guids = (Collection)data;
					Collection<String> currentSelection = view.getSelection();
					currentSelection.removeAll(guids);
					view.setSelection(currentSelection);
					break;
				case IFC_SELECTION_SHOW:
					guids = (Collection)data;
					view.showOrHideEntities(guids, true);
					break;
				case IFC_SELECTION_HIDE:
					guids = (Collection)data;
					view.showOrHideEntities(guids, false);
					break;
				case IFC_SELECTION_SOLID:
					guids = (Collection)data;
					view.setTransparency(guids, false);
					break;
				case IFC_SELECTION_TRANSPARENT:
					guids = (Collection)data;
					view.setTransparency(guids, true);
					break;
				default:
					break;
				}
			}
		} catch (IfcException e) {
			LOG.error(e.getMessage(), e);
		}

	}

	public void onDropEvent(Object eventData){		
		try {
			if(view.getSelection() != null && !view.getSelection().isEmpty()){
				Map<String, Object> dataMap = new HashMap<String, Object>();
				LOG.debug("affected GlobalIds: {}", new Object[]{view.getSelection()});

				String resourceUri = (String) eventData;

				Map<String, Set<EIfcroot>> resourceToIfcEntitiesMap = new HashMap<String, Set<EIfcroot>>();
				Set<EIfcroot> ifcEntities = ifcModel.getIfcEntitiesByGlobalIds(view.getSelection());
				resourceToIfcEntitiesMap.put(resourceUri, ifcEntities);

				dataMap.put(CommunicationService.PROPERTIES_KEY_DATA, resourceToIfcEntitiesMap);

				// IfcViewer overlaps everything so hide it temporarily
				view.hideIfcViewer(true);
				sendSync(CommunicationEvents.RESOURCE_TO_IFC, dataMap);
				// show again IfcViewer
				view.hideIfcViewer(false);

			}
		} catch (IfcException e) {
			LOG.error("{}", new Object[]{e.getMessage()}, e);
		}

	}

	public Ifc2x3DataModelProxy getIfcModel() {
		return ifcModel;
	}

}
