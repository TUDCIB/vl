//package de.tudresden.bau.cib.vl.gui.bim.controller;
//
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;
//import de.tudresden.bau.cib.vl.gui.bim.view.X3DView;
//import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
//import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;
//
//public class X3DController extends FileController<X3DView> {
//	
//	@Override
//	protected Set<CommunicationEvents> defineReceivedEvents() {
//		Set<CommunicationEvents> events = new HashSet<CommunicationEvents>();
//		events.add(CommunicationEvents.X3D_FILE);
//		return events;
//	}
//	
//	@Override
//	public void handleEvent(CommunicationEvents event, Map<String, Object> dataMap) {
//		LOG.debug("Receiving data with topic: {}", event.getName());
//		Object data = dataMap.get(CommunicationService.PROPERTIES_KEY_DATA);
//		switch(event) {
//		case X3D_FILE: 				
//			setFileInformation( (FileInformation) data);
//			parseFile();
//			break;
//		default:
//			break;
//		}
//	}
//	
//	@Override
//	protected void parseFile()
//	{
//		view.loadAndParseX3DFile();
//	
//	}
//}
