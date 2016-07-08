package de.tudresden.bau.cib.vl.core.process.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import de.tudresden.bau.cib.vl.core.process.BusinessProcessDataContainer;

/**
 * <p>
 * The container manager holds the data containers of all current runnable business processes. A data container is used to provide
 * non-serializable data for the process and is therefore used in a static way.
 * </p>
 * <p>
 * <b>This means that the data container should be added before a process begins and removed after the process finished.</b>
 * </p>
 * 
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class BusinessProcessDataContainerManager {
	
	private Map<String,BusinessProcessDataContainer> objectDataCache;
	private static BusinessProcessDataContainerManager SINGLETON;
	
	
	private BusinessProcessDataContainerManager() {
		initializeCache();
	}
	
	public static synchronized BusinessProcessDataContainerManager getInstance() {
		if(SINGLETON == null) SINGLETON = new BusinessProcessDataContainerManager();
		return SINGLETON;
	}
	
	private void initializeCache() {
		objectDataCache = new ConcurrentHashMap<String, BusinessProcessDataContainer>();
	}
	
	public synchronized BusinessProcessDataContainer getData(String processId) throws ExecutionException {
		BusinessProcessDataContainer data = objectDataCache.get(processId); 
		return data;
	}
	
	public synchronized void addDataToCache(String processId, BusinessProcessDataContainer data) {
		this.objectDataCache.put(processId, data);
	}
	
	public synchronized void removeFromCache(String processId) {
		this.objectDataCache.remove(processId);
	}
	
	public synchronized void clearCache() {
		initializeCache();
	}
}
