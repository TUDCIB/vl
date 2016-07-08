package de.tudresden.bau.cib.vl.core.process;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import de.tudresden.bau.cib.vl.core.process.internal.BusinessProcessDataContainerManager;


/**
 * <p>
 * Template class for a business process delegate. Classes which provides service task functionalities should extend from this class
 * to enable a consistent process behaviour.
 * </p>
 *
 * @author <a href="mailto:Ken.Baumgaertel@tu-dresden.de">Ken Baumgaertel</a>
 *
 */
public class AbstractBusinessProcessDelegate implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2942156299411878414L;
	protected final String processId;
	
	
	public AbstractBusinessProcessDelegate(String processId) {
		this.processId = processId;
	}
	
	protected BusinessProcessDataContainer getContainer() throws ExecutionException {			
		return BusinessProcessDataContainerManager.getInstance().getData(processId);
	}
}
