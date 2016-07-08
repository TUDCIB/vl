package de.tudresden.bau.cib.vl.gui.common.controller;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.core.service.CommunicationService;

/**
 * Abstract controller class when applying the MVC concept.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 */
public abstract class AbstractController implements EventHandler {
	
	protected CommunicationService communicationService;
	protected Logger LOG = LoggerFactory.getLogger(AbstractController.class);
	
	
	/**
	 * Get bundle context of the view which means to return the bundle context of the plugin activator 
	 * (e.g. <code>Activator.getDefault().getContext()</code>)
	 * @return The bundle context of the plugin in which the view is declared.
	 */
	protected abstract BundleContext getBundleContext();

	/**
	 * Subscribe the controller to listen on events in the communication service.
	 */
	private void prepareCommunication() {
	    Dictionary<String, String[]> properties = new Hashtable<String, String[]>();
	    Set<CommunicationEvents> events = defineReceivedEvents();
	    if(events != null && getBundleContext() != null) {
	    	List<String> eventTopics = new ArrayList<String>();
	    	for(CommunicationEvents event : events) {
	    		eventTopics.add(event.getName());
	    	}
		    properties.put(EventConstants.EVENT_TOPIC, eventTopics.toArray(new String[eventTopics.size()]));
			communicationService.subscribe(properties, this);
	    }
	}

	/**
	 * Send a synchronous event.
	 * @param event The event taken from {@link de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents}.
	 * @param data The data to send.
	 */
	protected void sendSync(CommunicationEvents event, Map<String, Object> dataMap) {
		communicationService.sendSync(event, dataMap);
	}
	
	/**
	 * Send a synchronous event.
	 * @param event The event taken from {@link de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents}.
	 * @param data The data to send.
	 */
	public void sendSync(CommunicationEvents event, Object data) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put(CommunicationService.PROPERTIES_KEY_DATA, data);
		communicationService.sendSync(event, dataMap);
	}
	
	/**
	 * Define events on which the controller listen.
	 * @return A set with events taken from {@link de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents}.
	 */
	protected abstract Set<CommunicationEvents> defineReceivedEvents();
	
	@Override
	public final void handleEvent(Event event) {
		// no event will be handled if none is registered
		if(defineReceivedEvents() == null || defineReceivedEvents().size() == 0) return;
		
		String topic = event.getTopic();
		LOG.debug("Controller {} retrieves the event with topic {}", 
				new Object[]{this, topic});
		Map<String, Object> dataMap = new HashMap<String, Object>();
		for(String propName : event.getPropertyNames()){
			Object data = event.getProperty(propName);
			if(data != null) {
				dataMap.put(propName, data);
			}
		}
		
		CommunicationEvents commEvent = CommunicationEvents.findByAbbreviation(topic);
		if(commEvent != null && dataMap.size() > 0) {
			if(defineReceivedEvents().contains(commEvent)) {
				handleEvent(commEvent, dataMap);
			}
		}
		
	}
	
	/**
	 * Control method to handle each event. The best way is to define a switch case where each event is handled.
	 * @param event
	 * @param dataMap
	 */
	protected abstract void handleEvent(CommunicationEvents event, Map<String,Object> dataMap);
	

	public void setCommunicationService(CommunicationService communicationService) {
		this.communicationService = communicationService;
		prepareCommunication();
	}

}
