//package de.tudresden.bau.cib.vl.gui.bim.controller;
//
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.osgi.framework.BundleContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.tudresden.bau.cib.vl.gui.bim.Activator;
//import de.tudresden.bau.cib.vl.gui.bim.view.nDNavigatorViewPart;
//import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
//import de.tudresden.bau.cib.vl.gui.common.controller.AbstractViewController;
//import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;
//
//public class nDNavigatorViewController extends AbstractViewController<nDNavigatorViewPart> {
//
//	
//	
//	
//	private Logger LOG = LoggerFactory.getLogger(nDNavigatorViewController.class);
//
//	
//	@Override
//	protected Set<CommunicationEvents> defineReceivedEvents() {
//		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();
//		//events.add(CommunicationEvents.CLIENT_WILDCARD);
////		events.add(CommunicationEvents.CLIENT_NEW_FILE);
////		events.add(CommunicationEvents.CLIENT_NEW_SELECTION);
////		events.add(CommunicationEvents.CLIENT_SHOW_ELEMENTS);
////		events.add(CommunicationEvents.CLIENT_HIDE_ELEMENTS);
//		return events;
//	}
//
//	@Override
//	protected BundleContext getBundleContext() {
//		return Activator.getDefault().getContext();
//	}
//
//	@Override
//	protected void handleEvent(CommunicationEvents event, Map<String, Object> dataMap) {
//		LOG.debug("Retrieving data with topic: {} and data: {}", new Object[]{event.getName(), dataMap});
//		Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
//		if(data != null) {
//			switch(event) {
////			case CLIENT_NEW_FILE:
////				String filePath = (String) data;
////				String[] array = filePath.split("file=");
////				if(array.length == 2)
////					view.loadProjectIn3DViewer(array[1]);	
////				break;
////			case CLIENT_NEW_SELECTION:
////				String[] guids = (String[]) data;
////				view.setSelection(Arrays.asList(guids));
////				break;
////			case CLIENT_SHOW_ELEMENTS:
////				guids = (String[]) data;
////				view.showElements(guids, true);
////				break;
////			case CLIENT_HIDE_ELEMENTS:
////				guids = (String[]) data;
////				view.showElements(guids, false);
////				break;
//			default:
//				break;
//			}
//		}
//	}
//}
