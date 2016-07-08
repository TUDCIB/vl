package de.tudresden.bau.cib.vl.core.process;

import java.util.concurrent.ConcurrentHashMap;

import org.activiti.engine.HistoryService;

import de.tudresden.bau.cib.vl.core.process.internal.BusinessProcessDataContainerManager;

/**
 * <p>
 * Data container for a business process. This container should be used to temporary hold process data which cannot be serialized.
 * Therefore it should be added to the {@link BusinessProcessDataContainerManager} 
 * so that a process have static access to the data.
 * </p>
 * <p>
 * <b>When the process end is reached the container should be removed from the manager!</b>
 * </p>
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class BusinessProcessDataContainer implements Cloneable {
	

	/** The Constant INPUT_SEPARATOR. */
	public static final String INPUT_SEPARATOR = ",";
	
	/** The Constant OUTPUT_SEPARATOR. */
	public static final String OUTPUT_SEPARATOR = ",";

	/** The process id. */
	private String processId; 

	/** The cache map. */
	private ConcurrentHashMap<String, Object> cacheMap;

	/** The history service. */
	private HistoryService historyService;


	/**
	 * Instantiates a new business process container.
	 *
	 * @param id the id
	 * @param history the history
	 */
	public BusinessProcessDataContainer(String id, HistoryService history) {
		this.processId = id;
		this.historyService = history;
		cacheMap = new ConcurrentHashMap<String, Object>();

	}	

	/**
	 * Gets the process id.
	 *
	 * @return the process id
	 */
	public String getProcessId() {
		return processId;
	}

	public void putData(String key, Object data) {
		cacheMap.put(key, data);
	}
	
	public Object getData(String key) {
		return cacheMap.get(key);
	}

	public ConcurrentHashMap<String, Object> getCacheMap() {
		return cacheMap;
	}
	
	@Override
	public BusinessProcessDataContainer clone() throws CloneNotSupportedException {
		return (BusinessProcessDataContainer) super.clone();
		
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

}
