package de.tudresden.bau.cib.vl.gui.core.service;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tudresden.bau.cib.vl.core.database.domain.User;
import de.tudresden.bau.cib.vl.gui.common.communication.CommunicationEvents;
import de.tudresden.bau.cib.vl.gui.core.controller.SessionManagementController;

/**
 * Note: Is no spring enabled service!
 * This is a session-coupled communication service which should be used by all controllers to send notifications 
 * through the publish/subscribe principle.
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class CommunicationService {

	private Logger LOG = LoggerFactory.getLogger(CommunicationService.class);


	private List<EventHandler> subscribers;
	
	/**
	 * Represents the key in the map when sending with 
	 * {@link #sendSync(CommunicationChannels, CommunicationEvents, Map)}
	 */
	public static final String PROPERTIES_KEY_DATA = "DATA";
	/**
	 * Represents additional information about the UI component which sends the event
	 */
	public static final String PROPERTIES_KEY_UI_DATA ="UI_DATA";
	
	/**
	 * Map which contains the previous sent data so that new subscribers will get the latest state.
	 */
	private ConcurrentHashMap<CommunicationEvents, Map<String,?>> eventDataMap;
	
	/**
	 * The shared instance.
	 */
	private static CommunicationService INSTANCE = new CommunicationService();

	/**
	 * Private constructor because of singleton pattern.
	 */
	private CommunicationService() {
		subscribers = new ArrayList<EventHandler>();
		setupCache();
	}

	/**
	 * Retrieves the single instance of CommunicationService.
	 * @return The instance of CommunicationService.
	 */
	public static CommunicationService getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CommunicationService();
		return INSTANCE;
	}
	
	private void setupCache() {
		LOG.debug("Initializing event data cache");

		eventDataMap = new ConcurrentHashMap<CommunicationEvents, Map<String,?>>();
	}
	
	private void addEventDataToCache(CommunicationEvents event, Map<String,?> data) {
		if(event != null && data != null) this.eventDataMap.put(event, data);
	}
	
	private Map<String, ?> getCachedData(CommunicationEvents event) {
		return this.eventDataMap.get(event);
	}
	
	/**
//	 * The event data is cached in the {@link #CommunicationService()} and therefore the current state can be retrieved.
	 * @param handler
	 */
	public void retrieveLatestData(EventHandler handler) {
		// send the cached data
		for (Map.Entry<CommunicationEvents, Map<java.lang.String, ?>> entry : eventDataMap.entrySet()) {
			CommunicationEvents key = entry.getKey();
			String eventName = key.getName();
			Map<java.lang.String, ?> eventData = entry.getValue();
			if(eventData != null) {
				LOG.debug("Subscriber {} get cached data {} from event {}", 
						new Object[]{handler, eventData, eventName});
				Event e = new Event(eventName, eventData);
				handler.handleEvent(e);
			}		
		}
	}
	
	/**
//	 * The event data is cached in the {@link #CommunicationService()} and therefore the current state can be retrieved.
	 * @param event The event for which the data is asked for.
	 * @param handler
	 */
	public void retrieveLatestData(CommunicationEvents event, EventHandler handler) {
		Map<String, ?> data = getCachedData(event);
		// send the cached data
		LOG.debug("Subscriber {} get cached data {} from event {}", 
				new Object[]{handler, data, event.getName()});
		Event e = new Event(event.getName(), data);
		handler.handleEvent(e);	
	}

	/**
	 * It is recommended to use the {@link CommunicationService#PROPERTIES_KEY_DATA} as key in the map
	 * but any key can be used. The subscriber have to know this key.
	 * @param event Event name
	 * @param data The real data that will be communicated.
	 */
	public void sendSync(CommunicationEvents event, Map<String,?> data) {
		LOG.debug("Send synchron event: {} with data: {}",
				new Object[]{event, data});
		Event e = new Event(event.getName(), data);
		// store it to have the latest event data cached
		addEventDataToCache(event, data);
		for(EventHandler subscriber : subscribers) {
			subscriber.handleEvent(e);
		}
	}

	/**
	 * Subscribe the event handler
	 * @param properties 
	 * @param handler
	 */
	public void subscribe(Dictionary<String,String[]> properties, EventHandler handler) {	     
		User user = SessionManagementController.getInstance().getUser();
		LOG.info("EventHandler {} subscribes to events in session {}", 
				new Object[]{handler, user});
		if(!subscribers.contains(handler))	{
			subscribers.add(handler);
		}
	}
	
	/*
	 * Old code which don't work for multiple users on the web
	 */
//	private Logger LOG = LoggerFactory.getLogger(CommunicationServiceImpl.class);
//
//
//	private EventAdmin eventAdmin;
//
//
//	private CommunicationServiceImpl() {
//		BundleContext ctx = Activator.getDefault().getContext();
//		ServiceReference<EventAdmin> ref = ctx.getServiceReference(EventAdmin.class);
//		eventAdmin = ctx.getService(ref);
//		LOG.debug("Built new instance of communication service {} with EventAdmin {}", 
//				new Object[]{this, eventAdmin});
//	}
//
//	public static CommunicationService getInstance() {
//		CommunicationServiceImpl service = SingletonUtil.getSessionInstance(
//				CommunicationServiceImpl.class);
//		return service;
//	}
//
//
//	@Override
//	public void sendAsync(CommunicationEvents event, Map<String,?> data) {
//		LOG.debug("Send asynchron event: {} with data: {}",
//				new Object[]{event, data});
//		Event e = new Event(event.getName(), data);
//		eventAdmin.postEvent(e);
//	}
//
//	@Override
//	public void sendSync(CommunicationEvents event, Map<String,?> data) {
//		LOG.debug("Send synchron event: {} with data: {}",
//				new Object[]{event, data});
//		Event e = new Event(event.getName(), data);
//		eventAdmin.sendEvent(e);
//	}
//
//	@Override
//	public void subscribe(Dictionary<String,String[]> properties, EventHandler handler, BundleContext ctx) {	     
//		User user = SessionManagementController.getInstance().getUser();
//		LOG.info("EventHandler {} from bundle {} subscribes to events in session {}", 
//				new Object[]{handler, ctx.getBundle().getSymbolicName(), user});
//		ctx.registerService(EventHandler.class, handler, properties);
//	}

//	public void setEventAdmin(EventAdmin eventAdmin) {
//		this.eventAdmin = eventAdmin;
//	}
}
